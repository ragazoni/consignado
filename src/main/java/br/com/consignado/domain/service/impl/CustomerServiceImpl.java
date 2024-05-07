package br.com.consignado.domain.service.impl;

import br.com.consignado.api.exception.CustomerNotFoundException;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.repository.CustomerRepository;
import br.com.consignado.domain.constants.ConsignadoConstants;
import br.com.consignado.domain.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


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
        if (existingCustomer != null) {
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
            throw new CustomerNotFoundException("customer not found");
        }
        String accountType = customer.getAccountType();
        String segment = customer.getSegment();
        String affiliation = customer.getAffiliation();
        if (accountType.equals(ConsignadoConstants.CORRENTISTA)) {
            logger.info(String.format("Customer is account holder: segment=%s - affiliation=%s", segment, affiliation));
        } else {
            logger.info("Customer is not account holder");
        }
        logger.info(String.format("Affiliation customer: %s", affiliation));
        return customer;
    }


}
