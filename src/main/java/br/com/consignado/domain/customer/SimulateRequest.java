package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SimulateRequest {
    @JsonProperty(value = "referrslId")
    private String referralId;

    private CustomerResponse customer;
    private Date simulateDate;

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public Date getSimulateDate() {
        return simulateDate;
    }

    public void setSimulateDate(Date simulateDate) {
        this.simulateDate = simulateDate;
    }

    @Override
    public String toString() {
        return "SimulateRequest{" +
                "customer=" + customer +
                ", simulateDate=" + simulateDate +
                '}';
    }
}
