package com.beccon.pessoa.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.beccon.pessoa.data.vo.PessoaVo;

@Component
public class PessoaSendMessage {
	
	@Value("${pessoa.rabbitmq.exchange}")
	String exchange;
	
	@Value("${pessoa.rabbitmq.routingKey}")
	String routingKey;
	
	public final RabbitTemplate rabbitTemplate;

	@Autowired
	public PessoaSendMessage(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(PessoaVo pessoaVo) {
		rabbitTemplate.convertAndSend(exchange, routingKey, pessoaVo);
	}
		
}


