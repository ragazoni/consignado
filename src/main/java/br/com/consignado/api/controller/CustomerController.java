package br.com.consignado.api.controller;

import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.entity.SuspdLoan;
import br.com.consignado.domain.service.CustomerService;
import br.com.consignado.domain.service.Simulate;
import br.com.consignado.domain.utils.CpfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consignado")
public class CustomerController {
    private final CustomerService customerService;
    private final Simulate simulateService;

    public CustomerController(CustomerService customerService, Simulate simulateService) {
        this.customerService = customerService;
        this.simulateService = simulateService;
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

    @PostMapping(value = "/simulate")
    public ResponseEntity<SuspdLoan>createSimulation(@RequestBody SuspdLoan suspdLoan) throws InstallmenstNotFoundException {
        SuspdLoan saveSuspdLoan = simulateService.saveSimulate(suspdLoan);
        System.out.println("simulacao salva com suceeso " + suspdLoan);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveSuspdLoan);

    }

    @GetMapping(value = "/list-customer")
    public List<Customer> listCustomer(){
        return customerService.listCustomer();
    }


//    @PostMapping("/simulate")
//    public ResponseEntity<SimulateResponse> simulateLoan(@RequestBody SimulateRequest request) {
//        try {
//            // Chame o método simulate e obtenha a resposta
//            SimulateResponse simulateResponse = simulateService.simulate(request);
//
//            // Verifique se a resposta não é nula
//            if (simulateResponse != null) {
//                // Retorne a resposta com o status 200 OK
//                return ResponseEntity.ok(simulateResponse);
//            } else {
//                // Se a resposta for nula, retorne um status 500 Internal Server Error
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        } catch (IllegalArgumentException e) {
//            // Se ocorrer uma exceção IllegalArgumentException, retorne um status 400 Bad Request
//            return ResponseEntity.badRequest().build();
//        } catch (Exception e) {
//            // Se ocorrer qualquer outra exceção, retorne um status 500 Internal Server Error
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}


