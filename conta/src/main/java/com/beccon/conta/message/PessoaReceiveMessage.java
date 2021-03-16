package com.beccon.conta.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.beccon.conta.data.vo.PessoaVo;
import com.beccon.conta.entity.Pessoa;
import com.beccon.conta.repository.PessoaRepository;

@Component
public class PessoaReceiveMessage {

	private final PessoaRepository pessoaRepository;

	@Autowired
	public PessoaReceiveMessage(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload PessoaVo pessoaVo) {		
		pessoaRepository.save(Pessoa.create(pessoaVo));
	}

	
}
