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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private SuspdLoan suspdLoan;

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

    public SuspdLoan getSuspdLoan() {
        return suspdLoan;
    }

    public void setSuspdLoan(SuspdLoan suspdLoan) {
        this.suspdLoan = suspdLoan;
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
}
