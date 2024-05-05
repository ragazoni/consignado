package br.com.consignado.domain.service.impl;

import br.com.consignado.data.entity.SuspdContract;
import br.com.consignado.data.entity.SuspdLoan;
import br.com.consignado.domain.converter.ContractConverter;
import br.com.consignado.domain.converter.SimulateResponseConverter;
import br.com.consignado.domain.converter.SuspdLoanConverter;
import br.com.consignado.domain.customer.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulateService {

    public SimulateResponse simulate(SimulateRequest request) {

        //pensar em uma forma de chamar validação cliente aqui

        SimulateResponse simulateResponse = new SimulateResponseConverter();

        try {
            validateSimulateResponse(simulateResponse);

        } catch (Exception e) {
            throw e;
        }


        return simulateResponse;
    }

    private double calculateRate(CustomerResponse customerResponse) {
        double rate = 0;
        if (customerResponse.getAffiliation().equals("EP")) {
            rate = 0.026;
        }
        if (customerResponse.getAffiliation().equals("OP")) {
            rate = 0.022;
        }
        if (customerResponse.getAffiliation().equals("INSS")) {
            rate = 0.016;
        }
        if (customerResponse.getAccountType().equals("S")) {
            rate -= rate * 0.05;
        }
        return rate;
    }

    private int calcularDuracaoSimulacao(CustomerResponse customerResponse) {

        if (customerResponse.getSegment().equals("Varejo")) {
            return 24;
        } else if (customerResponse.getSegment().equals("Uniclass")) {
            return 36;
        } else if (customerResponse.getSegment().equals("Person")) {
            return 48;
        } else {
            if (customerResponse.getAccountType().equals("N"))
                return 12;
        }
        return 0;
    }

    // Método para calcular o valor total a ser pago
    private double calcularValorTotal(LoanResponse loanResponse, CustomerResponse response) {
        double valorSolicitado = loanResponse.getCurrentLoanValue();
        double taxa = calculateRate(response);
        int quantidadeParcelas = loanResponse.getTotalInstallments();
        return valorSolicitado * (1 + taxa * quantidadeParcelas);
    }

    // Método para calcular o valor da parcela
    private double calcularValorParcela(LoanResponse loanResponse) {
        double valorTotal = loanResponse.getAmount();
        int quantidadeParcelas = loanResponse.getTotalInstallments();
        return valorTotal / quantidadeParcelas;
    }

    private void validateSimulateResponse(SimulateResponse simulateResponse) {
        if (Objects.nonNull(simulateResponse) && CollectionUtils.isEmpty(simulateResponse.getLoans())) ;
        for (LoanResponse loan : simulateResponse.getLoans()) {
            if (loan.getAmount() <= 0) {
                throw new IllegalArgumentException("Valor invalido para simulação de emprestimo");
            }
        }
    }

    private SimulateResponse saveLoan(SimulateRequest simulateRequest, SimulateResponse simulateResponse,
                                      ContractResponse contractResponse) {
        List<SuspdLoan> suspdLoans = new ArrayList<>();
        for (LoanResponse loanResponse : simulateResponse.getLoans()) {
            final SuspdLoan suspdLoan = generateLoan(simulateRequest, loanResponse);
            createContract(contractResponse);
            suspdLoans.add(suspdLoan);
        }

        return simulateResponse;
    }

    private void createContract(ContractResponse contractResponse) {
        final SuspdContract suspdContract = new ContractConverter().convert(contractResponse);
        suspdContract.setId(contractResponse.getIdContract());
        suspdContract.setDateCreatedContract(contractResponse.getDateContract());
        suspdContract.setLoan(contractResponse.getLoan());
    }

    private SuspdLoan generateLoan(SimulateRequest simulateRequest, LoanResponse loanResponse) {
        final SuspdLoan suspdLoan = new SuspdLoanConverter().convert(loanResponse);
        double taxa = calculateRate(simulateRequest.getCustomer());
        //int duracao = calcularDuracaoSimulacao(simulateRequest.getCustomer());
        double valorToral = calcularValorTotal(loanResponse, simulateRequest.getCustomer());
        double valorparcela = calcularValorParcela(loanResponse);

        suspdLoan.setId(loanResponse.getId());
        suspdLoan.setUserDocument(loanResponse.getUserDocument());
        suspdLoan.setSimulationDate(loanResponse.getSimulationDate());
        suspdLoan.setAffiliation(loanResponse.getAffiliation());
        suspdLoan.setCurrentLoanValue(loanResponse.getCurrentLoanValue());
        suspdLoan.setFeeValue(taxa);
        suspdLoan.setTotalInstallments(loanResponse.getTotalInstallments());
        suspdLoan.setAmount(valorToral);
        suspdLoan.setInstallmentValue(valorparcela);

        return suspdLoan;
    }


}
