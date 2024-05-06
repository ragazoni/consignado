package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

public class SimulateResponse {
    @JsonProperty(value = "emprestimo")
    List<LoanResponse> loans;
    @JsonProperty(value = "contratos")
    List<ContractResponse> contractResponses;

    public SimulateResponse(List<LoanResponse> loans, List<ContractResponse> contractResponses) {
        this.loans = loans;
        this.contractResponses = contractResponses;
    }

    public List<LoanResponse> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanResponse> loans) {
        this.loans = loans;
    }

    public Collection<?> getContracts() {
        return contractResponses;
    }

    public void setContracts(List<ContractResponse> contractResponses) {
        this.contractResponses = contractResponses;
    }

    @Override
    public String toString() {
        return "SimulateResponse{" +
                "loans=" + loans +
                ", contracts=" + contractResponses +
                '}';
    }
}
