package com.beccon.conta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beccon.conta.data.vo.ContaVo;
import com.beccon.conta.entity.Conta;
import com.beccon.conta.entity.Pessoa;
import com.beccon.conta.entity.PessoaConta;
import com.beccon.conta.exception.ResouceNotFoundException;
import com.beccon.conta.repository.ContaRepository;
import com.beccon.conta.repository.PessoaContaRepository;


@Service
public class ContaService {
	
	private final ContaRepository contaRepository;
	
	@Autowired
	public ContaService(ContaRepository contaRepository, PessoaContaRepository pessoaContaRepository) {
		this.contaRepository = contaRepository;		
	}
	
	public ContaVo create(ContaVo contaVo){
		Conta conta = contaRepository.save(Conta.create(contaVo));
		Pessoa pessoaSalva = new Pessoa();
		PessoaConta pv = PessoaConta.create(contaVo.getPessoa());
		pv.setIdConta(conta);
		conta.setPessoaConta(pv);
		return ContaVo.create(conta);
	}
	
	public ContaVo update(ContaVo contaVo) {
		final Optional<Conta> optionalConta = contaRepository.findById(contaVo.getId());
			
		if(!optionalConta.isPresent()) {
			new ResouceNotFoundException("Conta não encontrada!");
		}
		
		return ContaVo.create(contaRepository.save(Conta.create(contaVo)));
	}
	
	public ContaVo findById(Long id) {
		var entity = contaRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Conta não encontrada!"));
		return ContaVo.create(entity);
	}
	
	public Page<ContaVo> findAll(Pageable pageable){
		
		var page = contaRepository.findAll(pageable);
		return page.map(this::convertToContaVo);
	}
		
	private ContaVo convertToContaVo(Conta conta) {
		return ContaVo.create(conta);
	}
	
	public void delete(Long id) {
		var entity = contaRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Pessoa não encontrado!"));
		contaRepository.delete(entity);
	}
	
}
