package br.com.consignado.domain.customer;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerResponse {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "nome")
    private String name;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "correntista")
    private String accountType;
    @JsonProperty(value = "segmento")
    private String segment;
    @JsonProperty(value = "convenio")
    private String affiliation;

    public CustomerResponse(Long id, String name, String cpf, String accountType, String segment, String affiliation) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.accountType = accountType;
        this.segment = segment;
        this.affiliation = affiliation;
    }

    public CustomerResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", accountType='" + accountType + '\'' +
                ", segment='" + segment + '\'' +
                ", affiliation='" + affiliation + '\'' +
                '}';
    }
}



