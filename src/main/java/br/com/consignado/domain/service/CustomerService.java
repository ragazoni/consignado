package br.com.consignado.domain.service;

import br.com.consignado.data.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    Customer seachrCustomerByCpf(String document);

    List<Customer> listCustomer();

    Customer saveOrUpdateCustomer(Customer customer);

   Customer validCustomer(String document);
}
