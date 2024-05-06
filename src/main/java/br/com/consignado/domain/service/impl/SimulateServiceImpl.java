package br.com.consignado.domain.service.impl;

import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.entity.SuspdLoan;
import br.com.consignado.data.repository.CustomerRepository;
import br.com.consignado.data.repository.LoanRepository;
import br.com.consignado.domain.service.Simulate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SimulateServiceImpl implements Simulate {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public SimulateServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public SuspdLoan saveSimulate(SuspdLoan suspdLoan) throws InstallmenstNotFoundException {
        Customer customer = customerRepository.findByCpf(suspdLoan.getUserDocument());
        suspdLoan.setSimulationDate(LocalDateTime.now());
        double taxaMensal = getTaxaMensal(customer);
        taxaMensal = getTaxaMensalIdCorrentista(customer, taxaMensal);
        int maxParcelas = getMaxParcelas(customer);
        int numParcelas = getNumParcelasIsValido(suspdLoan, maxParcelas);
        double valorTotal = getValorTotal(suspdLoan, taxaMensal, numParcelas);
        double valorParcela = getValorParcela(valorTotal, numParcelas);
        suspdLoan.setFeeValue(taxaMensal);
        suspdLoan.setAmount(valorTotal);
        suspdLoan.setInstallmentValue(valorParcela);
        loanRepository.save(suspdLoan);

        return suspdLoan;
    }

    /**
     * Verifica se o número de parcelas solicitado é válido
     */

    private static int getNumParcelasIsValido(SuspdLoan suspdLoan, int maxParcelas) throws InstallmenstNotFoundException {
        int numParcelas = suspdLoan.getTotalInstallments();
        if (numParcelas > maxParcelas) {
            throw new InstallmenstNotFoundException("Number of installments exceeds the allowed limit");
        }
        return numParcelas;
    }

    /**
     * Calcula o valor total a ser pago com juros simples
     */

    private static double getValorTotal(SuspdLoan suspdLoan, double taxaMensal, int numParcelas) {
        double valorSolicitado = suspdLoan.getCurrentLoanValue();
        double valorTotal = valorSolicitado * (1 + taxaMensal * numParcelas);
        return valorTotal;
    }

    /**
     * Calcula o valor da parcela
     */

    private static double getValorParcela(double valorTotal, int numParcelas) {
        double valorParcela = valorTotal / numParcelas;
        return valorParcela;
    }

    /**
     * Obtém o segmento do cliente e define o número máximo de parcelas
     */

    private static int getMaxParcelas(Customer customer) {
        String segmento = customer.getSegment();
        int maxParcelas;
        switch (segmento) {
            case "Varejo":
                maxParcelas = 24;
                break;
            case "Uniclass":
                maxParcelas = 36;
                break;
            case "Person":
                maxParcelas = 48;
                break;
            default:
                maxParcelas = 12;
        }
        return maxParcelas;
    }

    /**
     * Verifica se o cliente é correntista e aplica o desconto
     */

    private static double getTaxaMensalIdCorrentista(Customer customer, double taxaMensal) {
        boolean isCorrentista = customer.getAccountType().equals("S");
        if (isCorrentista) {
            taxaMensal *= 0.95; // Aplica 5% de desconto
        }
        return taxaMensal;
    }

    /**
     * Obtém o convênio do cliente
     */
    private static double getTaxaMensal(Customer customer) {
        String convenio = customer.getAffiliation();
        // Obtém a taxa ao mês com base no convênio
        double taxaMensal;
        switch (convenio) {
            case "EP":
                taxaMensal = 0.026;
                break;
            case "OP":
                taxaMensal = 0.022;
                break;
            case "INSS":
                taxaMensal = 0.016;
                break;
            default:
                throw new IllegalArgumentException("Convênio inválido");
        }
        return taxaMensal;
    }

}
