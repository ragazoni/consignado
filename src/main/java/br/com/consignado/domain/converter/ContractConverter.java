package br.com.consignado.domain.converter;

import br.com.consignado.data.entity.SuspdContract;
import br.com.consignado.domain.customer.ContractResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

public class ContractConverter implements Converter<ContractResponse, SuspdContract> {


    @Override
    public SuspdContract convert(ContractResponse contractResponse) {
        final SuspdContract suspdContract = new SuspdContract();

        if (Objects.nonNull(contractResponse.getIdContract())) {
            suspdContract.setId(contractResponse.getIdContract());
        }
        if (Objects.nonNull(contractResponse.getDateContract())) {
            suspdContract.setDateCreatedContract(contractResponse.getDateContract());
        }

        return suspdContract;
    }
}
