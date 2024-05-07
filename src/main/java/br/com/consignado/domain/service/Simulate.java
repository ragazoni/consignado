package br.com.consignado.domain.service;

import br.com.consignado.api.exception.AffiliationInvalidException;
import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.api.exception.LoanAmountIsZeroException;
import br.com.consignado.data.entity.Contract;
import br.com.consignado.data.entity.Customer;
import br.com.consignado.data.entity.Loan;

import java.util.List;

public interface Simulate {

    List<Contract> listContract();
    Loan saveSimulate(Loan loan) throws InstallmenstNotFoundException, AffiliationInvalidException, LoanAmountIsZeroException;
}
