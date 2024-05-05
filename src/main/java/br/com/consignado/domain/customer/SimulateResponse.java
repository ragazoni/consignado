package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimulateResponse {
    @JsonProperty(value = "emprestimo")
    List<LoanResponse> loans;
    @JsonProperty(value = "contratos")
    List<ContractResponse> contractResponses;

    public List<LoanResponse> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanResponse> loans) {
        this.loans = loans;
    }

    public List<ContractResponse> getContracts() {
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
