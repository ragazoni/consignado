package br.com.consignado.domain.service.impl;

import br.com.consignado.api.exception.CustomerNotFoundException;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.repository.CustomerRepository;
import br.com.consignado.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer seachrCustomerByCpf(String document) {
        return null;
    }

    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findByCpf(customer.getCpf());
        if(existingCustomer != null){
            existingCustomer.setName(customer.getName());
            existingCustomer.setCpf(customer.getCpf());
            existingCustomer.setAffiliation(customer.getAffiliation());
            existingCustomer.setSegment(customer.getSegment());
            existingCustomer.setAccountType(customer.getAccountType());
            customerRepository.save(existingCustomer);
        } else {
            customerRepository.save(customer);
        }
        return existingCustomer;
    }


    @Override
    public Customer validCustomer(String document) {
        Customer customer = customerRepository.findByCpf(document);
        if (customer == null) {
            throw new CustomerNotFoundException("cliente não encontrado");
        }
        boolean isCorrentista = customer.getAccountType().equals("S");
        if (isCorrentista) {
            customer.getSegment();
            customer.getAffiliation();
        } else {
            customer.getSegment();
            customer.getAffiliation();
        }
        return customer;
    }



}
