package br.com.consignado.api.exception;

public class AffiliationInvalidException extends Exception{
    public AffiliationInvalidException() {
    }

    public AffiliationInvalidException(String message) {
        super(message);
    }
}
