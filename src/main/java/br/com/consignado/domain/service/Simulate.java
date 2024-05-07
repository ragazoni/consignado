package br.com.consignado.domain.service;

import br.com.consignado.api.exception.AffiliationInvalidException;
import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.api.exception.LoanAmountIsZeroException;
import br.com.consignado.data.entity.Loan;

public interface Simulate {

    Loan saveSimulate(Loan loan) throws InstallmenstNotFoundException, AffiliationInvalidException, LoanAmountIsZeroException;
}
