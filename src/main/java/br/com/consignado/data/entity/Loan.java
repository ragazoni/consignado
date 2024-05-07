package br.com.consignado.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_simulacao", length = 255, nullable = false)
    private LocalDateTime simulationDate;

    @Column(name = "documento", length = 255, nullable = false)
    private String userDocument;

    @Column(name = "convenio", length = 255, nullable = false)
    private String affiliation;

    @Column(name = "valor_solicitado", length = 255, nullable = false)
    private double currentLoanValue;

    @Column(name = "taxa_aplicada", length = 255, nullable = false)
    private double feeValue;

    @Column(name = "quantidade_parcelas", length = 255, nullable = false)
    private int totalInstallments;

    @Column(name = "valor_total", length = 255, nullable = false)
    private double amount;

    @Column(name = "valor_parcela", length = 255, nullable = false)
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
        if (Double.isInfinite(currentLoanValue) || Double.isNaN(currentLoanValue)) {
            this.currentLoanValue = 0.0;
            throw new IllegalArgumentException("Invalid value for currentLoanValue: " + currentLoanValue);
        } else {
            this.currentLoanValue = currentLoanValue;
        }
    }

    public double getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(double feeValue) {
        if (Double.isInfinite(feeValue) || Double.isNaN(feeValue)) {
            this.feeValue = 0.0;
            throw new IllegalArgumentException("Invalid value for feeValue: " + feeValue);
        } else {
            this.feeValue = feeValue;
        }

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
        if (Double.isInfinite(amount) || Double.isNaN(amount)) {
            this.amount = 0.0;
            throw new IllegalArgumentException("Invalid value for amount: " + amount);
        } else {
            this.amount = amount;
        }
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
        Loan that = (Loan) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SuspdLoan{" +
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
