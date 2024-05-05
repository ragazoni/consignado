package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class LoanResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("dataSimulacao")
    private LocalDateTime simulationDate;

    @JsonProperty("cpfCliente")
    private String userDocument;

    @JsonProperty("convenio")
    private String affiliation;

    @JsonProperty("valorSolicitado")
    private double currentLoanValue;

    @JsonProperty("taxaAplicada")
    private double feeValue;

    @JsonProperty("quantidadeParcelas")
    private int totalInstallments;

    @JsonProperty("valorTotal")
    private double amount;

    @JsonProperty("valorParcela")
    private double installmentValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSimulationDate() {
        return simulationDate;
    }

    public void setSimulationDate(LocalDateTime simulationDate) {
        this.simulationDate = simulationDate;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public double getCurrentLoanValue() {
        return currentLoanValue;
    }

    public void setCurrentLoanValue(double currentLoanValue) {
        this.currentLoanValue = currentLoanValue;
    }

    public double getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(double feeValue) {
        this.feeValue = feeValue;
    }

    public int getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(int totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(double installmentValue) {
        this.installmentValue = installmentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse that = (LoanResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LoanResponse{" +
                "id=" + id +
                ", simulationDate=" + simulationDate +
                ", userDocument='" + userDocument + '\'' +
                ", affiliation='" + affiliation + '\'' +
                ", currentLoanValue=" + currentLoanValue +
                ", feeValue=" + feeValue +
                ", totalInstallments=" + totalInstallments +
                ", amount=" + amount +
                ", installmentValue=" + installmentValue +
                '}';
    }
}
