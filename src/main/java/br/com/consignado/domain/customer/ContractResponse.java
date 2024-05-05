package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class ContractResponse {

    @JsonProperty("idContrato")
    private Long idContract;

    @JsonProperty("dataContrato")
    private LocalDateTime dateContract;

    @JsonProperty("loan")
    private LoanResponse loan;

    public Long getIdContract() {
        return idContract;
    }

    public void setIdContract(Long idContract) {
        this.idContract = idContract;
    }

    public LocalDateTime getDateContract() {
        return dateContract;
    }

    public void setDateContract(LocalDateTime dateContract) {
        this.dateContract = dateContract;
    }

    public LoanResponse getLoan() {
        return loan;
    }

    public void setLoan(LoanResponse loan) {
        this.loan = loan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractResponse contractResponse = (ContractResponse) o;
        return Objects.equals(idContract, contractResponse.idContract);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idContract);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "idContract='" + idContract + '\'' +
                ", dateContract=" + dateContract +
                ", idSimulate='" + loan + '\'' +
                '}';
    }
}
