package br.com.consignado.api.exception;

public class CustomerNotFoundException extends IllegalArgumentException{
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
