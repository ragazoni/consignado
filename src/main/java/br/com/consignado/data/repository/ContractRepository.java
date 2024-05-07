package br.com.consignado.data.repository;

import br.com.consignado.data.entity.SuspdContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<SuspdContract, Long> {
}
