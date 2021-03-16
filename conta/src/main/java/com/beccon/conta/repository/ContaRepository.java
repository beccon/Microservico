package com.beccon.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beccon.conta.entity.Conta;



@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
