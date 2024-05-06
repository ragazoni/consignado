package br.com.consignado.domain.converter;

import br.com.consignado.data.entity.SuspdLoan;
import br.com.consignado.domain.customer.LoanResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

public class SuspdLoanConverter implements Converter<LoanResponse, SuspdLoan> {
    @Override
    public SuspdLoan convert(LoanResponse loanResponse) {
        final SuspdLoan suspdLoan = new SuspdLoan();

        if (Objects.nonNull(loanResponse.getId())) {
            suspdLoan.setId(loanResponse.getId());
        }
        if (StringUtils.isNotBlank(loanResponse.getCpf())) {
            suspdLoan.setUserDocument(loanResponse.getCpf());
        }
        if (Objects.nonNull(loanResponse.getAffiliation())) {
            suspdLoan.setAffiliation(loanResponse.getAffiliation());
        }
        suspdLoan.setCurrentLoanValue(loanResponse.getCurrentLoanValue());
        suspdLoan.setAmount(loanResponse.getAmount());
        suspdLoan.setFeeValue(loanResponse.getFeeValue());
        suspdLoan.setCurrentLoanValue(loanResponse.getCurrentLoanValue());
        if (Objects.nonNull(loanResponse.getSimulationDate())) {
            suspdLoan.setSimulationDate(loanResponse.getSimulationDate());
        }
        suspdLoan.setTotalInstallments(loanResponse.getTotalInstallments());

        return suspdLoan;
    }
}
