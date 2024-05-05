package br.com.consignado.api.controller;

import br.com.consignado.data.entity.Customer;
import br.com.consignado.domain.service.CustomerService;
import br.com.consignado.domain.utils.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consignado")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saveCustomer = customerService.saveOrUpdateCustomer(customer);
        System.out.println("Cliente salvo com sucesso " + customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCustomer);
    }

    @PostMapping(value = "/valid-customer")
    public ResponseEntity<Customer> checkCustomer(@RequestBody Customer customer) {
        String document = customer.getCpf();
        String isValidDocument = CpfUtils.validateFormateCpf(document);
        try {
            Customer validateCustomer = customerService.validCustomer(isValidDocument);
            System.out.println("Cliente validado com sucesso " + customer.getCpf());
            return ResponseEntity.ok(validateCustomer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/list-customer")
    public List<Customer> listCustomer(){
        return customerService.listCustomer();
    }

}
