package br.com.consignado.domain.converter;

import br.com.consignado.data.entity.Customer;
import br.com.consignado.domain.customer.CustomerResponse;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

public class CustomerConverter implements Converter<CustomerResponse, Customer> {
    @Override
    public Customer convert(CustomerResponse customerResponse) {

        final Customer customer = new Customer();

        if(Objects.nonNull(customerResponse.getId())){
            customer.setId(customerResponse.getId());
        }
        if(Objects.nonNull(customerResponse.getName())){
            customer.setName(customerResponse.getName());
        }
        if (Objects.nonNull(customerResponse.getCpf())){
            customer.setCpf(customerResponse.getCpf());
        }
        if(Objects.nonNull(customerResponse.getSegment())){
            customer.setSegment(customerResponse.getSegment());
        }
        if(Objects.nonNull(customerResponse.getAffiliation())){
            customer.setAffiliation(customerResponse.getAffiliation());
        }
        if(Objects.nonNull(customerResponse.getAccountType())){
            customer.setAccountType(customerResponse.getAccountType());
        }

        return customer;
    }
}
