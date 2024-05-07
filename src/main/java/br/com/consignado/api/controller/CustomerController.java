package br.com.consignado.api.controller;

import br.com.consignado.api.dto.LoanDTO;
import br.com.consignado.api.exception.CustomerNotFoundException;
import br.com.consignado.api.exception.SimulateExceptionNotFound;
import br.com.consignado.data.entity.Contract;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.entity.Loan;
import br.com.consignado.domain.service.CustomerService;
import br.com.consignado.domain.service.Simulate;
import br.com.consignado.domain.utils.CpfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consignado") //controle versao API
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final Simulate simulateService;

    public CustomerController(CustomerService customerService, Simulate simulateService) {
        this.customerService = customerService;
        this.simulateService = simulateService;
    }

    @PostMapping(value = "/create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer saveCustomer = customerService.saveOrUpdateCustomer(customer);
        logger.warn(String.format("Cliente salvo com sucesso customer=%s", customer));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCustomer);
    }

    @PostMapping(value = "/check-document")
    public ResponseEntity<Customer> checkCustomer(@RequestBody Customer customer) {
        String document = customer.getCpf();
        String isValidDocument = CpfUtils.validateFormateCpf(document);
        try {
            Customer validateCustomer = customerService.validCustomer(isValidDocument);
            logger.warn(String.format("Cliente validado com sucesso document=%s", customer.getCpf()));
            return ResponseEntity.ok(validateCustomer);
        } catch (CustomerNotFoundException e) {
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/simulate")
    public ResponseEntity<Loan> createSimulation(@RequestBody LoanDTO dto)
            throws SimulateExceptionNotFound {
        try {
            Loan loan = new Loan();
            loan.setUserDocument(dto.getUserDocument());
            loan.setCurrentLoanValue(dto.getCurrentLoanValue());
            loan.setAffiliation(dto.getAffiliation());
            loan.setTotalInstallments(dto.getTotalInstallments());
            Loan saveLoan = simulateService.saveSimulate(loan);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveLoan);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SimulateExceptionNotFound("error simulate loan");
        }

    }

    @GetMapping(value = "/list-customer")
    public List<Customer> listCustomer() {
        return customerService.listCustomer();
    }

    @GetMapping(value = "/list-contract")
    public List<Contract> listContract() {
        return simulateService.listContract();
    }

}


