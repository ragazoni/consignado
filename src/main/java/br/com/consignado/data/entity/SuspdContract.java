package br.com.consignado.data.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "contract")
public class SuspdContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "data_contrato", nullable = false)
    private LocalDateTime dateCreatedContract;
    @Column(name = "loan_id", nullable = false)
    private Long simulateLoanId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreatedContract() {
        return dateCreatedContract;
    }

    public void setDateCreatedContract(LocalDateTime dateCreatedContract) {
        this.dateCreatedContract = dateCreatedContract;
    }

    public Long getSimulateLoanId() {
        return simulateLoanId;
    }

    public void setSimulateLoanId(Long simulateLoanId) {
        this.simulateLoanId = simulateLoanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuspdContract suspdContract = (SuspdContract) o;
        return Objects.equals(id, suspdContract.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SuspdContract{" +
                "id=" + id +
                ", dateCreatedContract=" + dateCreatedContract +
                ", simulateLoanId=" + simulateLoanId +
                '}';
    }
}
