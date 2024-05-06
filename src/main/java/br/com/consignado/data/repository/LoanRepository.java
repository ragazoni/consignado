package br.com.consignado.data.repository;

import br.com.consignado.data.entity.SuspdLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<SuspdLoan, Long> {

}
