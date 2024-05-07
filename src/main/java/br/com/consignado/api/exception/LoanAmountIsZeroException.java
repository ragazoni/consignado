package br.com.consignado.api.exception;

public class LoanAmountIsZeroException extends Exception{
    public LoanAmountIsZeroException() {
    }

    public LoanAmountIsZeroException(String message) {
        super(message);
    }
}
