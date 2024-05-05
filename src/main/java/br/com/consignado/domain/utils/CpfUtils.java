package br.com.consignado.domain.utils;

import java.io.Serializable;

public class CpfUtils implements Serializable {

    public static String validateFormateCpf(final String cpfDigits){
        if(!cpfDigits.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            throw new IllegalArgumentException("format cpf invalid " + cpfDigits);
        }
        return cpfDigits;
    }
}
