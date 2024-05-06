package br.com.consignado.domain.converter;

import br.com.consignado.data.entity.Customer;
import br.com.consignado.domain.customer.ContractResponse;
import br.com.consignado.domain.customer.CustomerResponse;
import br.com.consignado.domain.customer.LoanResponse;
import br.com.consignado.domain.customer.SimulateResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Objects;

public class SimulateResponseConverter extends SimulateResponse implements Converter<CustomerResponse, Customer> {
    public SimulateResponseConverter(List<LoanResponse> loans, List<ContractResponse> contractResponses) {
        super(loans, contractResponses);
    }

    @Override
    public Customer convert(CustomerResponse response) {
        final Customer customer = new Customer();
        if(Objects.nonNull(response.getId())){
            customer.setId(response.getId());
        }
        if(Objects.nonNull(response.getName())){
            customer.setName(response.getName());
        }
        if(Objects.nonNull(response.getCpf())){
            customer.setCpf(response.getCpf());
        }
        if(Objects.nonNull(response.getAccountType())){
            customer.setAccountType(response.getAccountType());
        }
        if(Objects.nonNull(response.getAffiliation())){
            customer.setAffiliation(response.getAffiliation());
        }
        if (Objects.nonNull(response.getSegment())){
            customer.setSegment(response.getSegment());
        }
        return customer;
    }

    @Override
    public <U> Converter<CustomerResponse, U> andThen(Converter<? super Customer, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
