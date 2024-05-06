package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulateRequest {
    @JsonProperty(value = "referrslId")
    private String referralId;

    private CustomerResponse customer;
    private ContractResponse contractResponse;
    private LoanResponse loanResponse;

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public ContractResponse getContractResponse() {
        return contractResponse;
    }

    public void setContractResponse(ContractResponse contractResponse) {
        this.contractResponse = contractResponse;
    }

    public LoanResponse getLoanResponse() {
        return loanResponse;
    }

    public void setLoanResponse(LoanResponse loanResponse) {
        this.loanResponse = loanResponse;
    }

    @Override
    public String toString() {
        return "SimulateRequest{" +
                "referralId='" + referralId + '\'' +
                ", customer=" + customer +
                ", contractResponse=" + contractResponse +
                ", loanResponse=" + loanResponse +
                '}';
    }
}
