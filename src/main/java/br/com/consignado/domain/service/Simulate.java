package br.com.consignado.domain.service;

import br.com.consignado.api.exception.AffiliationInvalidException;
import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.api.exception.LoanAmountIsZeroException;
import br.com.consignado.data.entity.SuspdLoan;

public interface Simulate {

    SuspdLoan saveSimulate(SuspdLoan suspdLoan) throws InstallmenstNotFoundException, AffiliationInvalidException, LoanAmountIsZeroException;
}
