package br.com.consignado.domain.service.impl;

import br.com.consignado.api.exception.AffiliationInvalidException;
import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.api.exception.LoanAmountIsZeroException;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.entity.SuspdContract;
import br.com.consignado.data.entity.SuspdLoan;
import br.com.consignado.data.repository.ContractRepository;
import br.com.consignado.data.repository.CustomerRepository;
import br.com.consignado.data.repository.LoanRepository;
import br.com.consignado.domain.constants.ConsignadoConstants;
import br.com.consignado.domain.service.Simulate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class SimulateServiceImpl implements Simulate {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final ContractRepository contractRepository;

    private static final Logger logger = LoggerFactory.getLogger(SimulateServiceImpl.class);

    public SimulateServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository, ContractRepository contractRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    public SuspdLoan saveSimulate(SuspdLoan suspdLoan) throws InstallmenstNotFoundException,
            AffiliationInvalidException, LoanAmountIsZeroException {
        Customer customer = customerRepository.findByCpf(suspdLoan.getUserDocument());
        double taxaMensal = getTaxaMensal(customer);
        taxaMensal = getTaxaMensalIdCorrentista(customer, taxaMensal);
        double feeValue = getFeeValue(taxaMensal);
        int maxParcelas = getMaxParcelas(customer);
        int numParcelas = getNumParcelasIsValido(suspdLoan, maxParcelas);
        double valorTotal = getValorTotal(suspdLoan, taxaMensal, numParcelas);
        double valorParcela = getValorParcela(valorTotal, numParcelas);
        suspdLoan.setSimulationDate(LocalDateTime.now());
        suspdLoan.setFeeValue(feeValue);
        suspdLoan.setAmount(valorTotal);
        suspdLoan.setInstallmentValue(valorParcela);
        loanRepository.save(suspdLoan);
        SuspdContract contract = saveContract(suspdLoan);
        logger.info(String.format("simulacao e contrato salvo com sucesso. loan=%s - contract=%s", suspdLoan, contract));
        return suspdLoan;
    }

    private static double getFeeValue(double taxaMensal) {
        double value = taxaMensal * 100;
        BigDecimal originalFeeValue = BigDecimal.valueOf(value);
        BigDecimal roundFeeValue = originalFeeValue.setScale(2, RoundingMode.HALF_UP);
        return Double.parseDouble(String.valueOf(roundFeeValue));
    }

    /**
     * Verifica se o número de parcelas solicitado é válido
     */
    private static int getNumParcelasIsValido(SuspdLoan suspdLoan, int maxParcelas) throws InstallmenstNotFoundException {
        int numParcelas = suspdLoan.getTotalInstallments();
        if (numParcelas <= 0) {
            throw new InstallmenstNotFoundException("Number of installments must be greater than zero");
        }
        if (numParcelas > maxParcelas) {
            throw new InstallmenstNotFoundException("Number of installments exceeds the allowed limit");
        }
        return numParcelas;
    }

    /**
     * Obtém o segmento do cliente e define o número máximo de parcelas
     */
    private static int getMaxParcelas(Customer customer) {
        String segmento = customer.getSegment();
        return switch (segmento) {
            case ConsignadoConstants.SEGMENT_VAREJO -> 24;
            case ConsignadoConstants.SEGMENT_UNICLASS -> 36;
            case ConsignadoConstants.SEGMENT_PERSON -> 48;
            default -> 12;
        };
    }

    /**
     * Calcula o valor total a ser pago com juros simples
     */
    private static double getValorTotal(SuspdLoan suspdLoan, double taxaMensal, int numParcelas)
            throws LoanAmountIsZeroException {
        double valorSolicitado = suspdLoan.getCurrentLoanValue();
        if (valorSolicitado <= 0) {
            throw new LoanAmountIsZeroException(" Loan value must be greater than zero");
        }
        return valorSolicitado * (1 + taxaMensal * numParcelas);
    }

    /**
     * Calcula o valor da parcela
     */
    private static double getValorParcela(double valorTotal, int numParcelas) {
        return valorTotal / numParcelas;
    }

    /**
     * Verifica se o cliente é correntista e aplica o desconto
     * Aplica 5% de desconto
     */
    private static double getTaxaMensalIdCorrentista(Customer customer, double taxaMensal) {
        boolean isCorrentista = customer.getAccountType().equals(ConsignadoConstants.CORRENTISTA);
        if (isCorrentista) {
            taxaMensal *= 0.95;
        }
        return taxaMensal;
    }

    /**
     * Obtém o convênio do cliente
     */
    private static double getTaxaMensal(Customer customer) throws AffiliationInvalidException {
        String convenio = customer.getAffiliation();
        return switch (convenio) {
            case ConsignadoConstants.AFFILIATION_EP -> 0.026;
            case ConsignadoConstants.AFFILIATION_OP -> 0.022;
            case ConsignadoConstants.AFFILIATION_INSS -> 0.016;
            default -> throw new AffiliationInvalidException("Affiliation invalid");
        };
    }

    private SuspdContract saveContract(SuspdLoan loan) {
        SuspdContract contract = new SuspdContract();
        contract.setDateCreatedContract(LocalDateTime.now());
        contract.setSimulateLoanId(loan.getId());
        contractRepository.save(contract);
        return contract;
    }

}
