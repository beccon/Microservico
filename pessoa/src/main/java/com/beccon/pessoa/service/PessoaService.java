package com.beccon.pessoa.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beccon.pessoa.data.vo.PessoaVo;
import com.beccon.pessoa.entity.Pessoa;
import com.beccon.pessoa.exception.ResouceNotFoundException;
import com.beccon.pessoa.message.PessoaSendMessage;
import com.beccon.pessoa.repository.PessoaRepository;

@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final PessoaSendMessage pessoaSendMessage;

	@Autowired
	public PessoaService(PessoaRepository pessoaRepository, PessoaSendMessage pessoaSendMessage) {
		this.pessoaRepository = pessoaRepository;
		this.pessoaSendMessage = pessoaSendMessage;
	}
	
	public PessoaVo create(PessoaVo pessoaVo){
		PessoaVo pessoaVoRetorno = PessoaVo.create(pessoaRepository.save(Pessoa.create(pessoaVo)));
		pessoaSendMessage.sendMessage(pessoaVoRetorno);
		return pessoaVoRetorno;
	}
	
	public PessoaVo update(PessoaVo pessoaVo) {
		final Optional<Pessoa> optionalPessoa = pessoaRepository.findById(pessoaVo.getId());
			
		if(!optionalPessoa.isPresent()) {
			new ResouceNotFoundException("Pessoa não encontrada!");
		}
		
		return PessoaVo.create(pessoaRepository.save(Pessoa.create(pessoaVo)));
	}
	
	public PessoaVo findById(Long id) {
		var entity = pessoaRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Pessoa não encontrada!"));
		return PessoaVo.create(entity);
	}
	
	public Page<PessoaVo> findAll(Pageable pageable){
		
		var page = pessoaRepository.findAll(pageable);
		return page.map(this::convertToPessoaVo);
	}
		
	private PessoaVo convertToPessoaVo(Pessoa pessoa) {
		return PessoaVo.create(pessoa);
	}
	
	public void delete(Long id) {
		var entity = pessoaRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException ("Pessoa não encontrada!"));
		pessoaRepository.delete(entity);
	}
		
}
