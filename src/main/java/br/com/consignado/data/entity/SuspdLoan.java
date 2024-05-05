package br.com.consignado.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "loan")
public class SuspdLoan {
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

    @Column(name = "valor-solicitado", length = 255, nullable = false)
    private double currentLoanValue;

    @Column(name = "taxa-aplicada", length = 255, nullable = false)
    private double feeValue;

    @Column(name = "quantidade-parcelas", length = 255, nullable = false)
    private int totalInstallments;

    @Column(name = "valor-total", length = 255, nullable = false)
    private double amount;

    @Column(name = "valor-parcela", length = 255, nullable = false)
    private double installmentValue;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "suspdLoan", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private SuspdContract suspdContracts;

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

    public SuspdContract getSuspdContracts() {
        return suspdContracts;
    }

    public void setSuspdContracts(SuspdContract suspdContracts) {
        this.suspdContracts = suspdContracts;
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
        SuspdLoan that = (SuspdLoan) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
