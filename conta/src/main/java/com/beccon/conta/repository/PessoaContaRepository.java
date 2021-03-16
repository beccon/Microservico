package com.beccon.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beccon.conta.entity.PessoaConta;


@Repository
public interface PessoaContaRepository extends JpaRepository<PessoaConta, Long>{

}
