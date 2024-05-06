package br.com.consignado.domain.service;

import br.com.consignado.api.exception.InstallmenstNotFoundException;
import br.com.consignado.data.entity.SuspdLoan;

public interface Simulate {

    SuspdLoan saveSimulate(SuspdLoan suspdLoan) throws InstallmenstNotFoundException;
}
