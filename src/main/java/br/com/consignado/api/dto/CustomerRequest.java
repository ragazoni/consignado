package br.com.consignado.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerRequest {
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

    public CustomerRequest(String name, String cpf, String accountType, String segment, String affiliation) {
        this.name = name;
        this.cpf = cpf;
        this.accountType = accountType;
        this.segment = segment;
        this.affiliation = affiliation;
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
}



