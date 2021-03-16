package com.beccon.conta.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.beccon.conta.entity.PessoaConta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({"id", "idPessoa"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaContaVo extends RepresentationModel<PessoaContaVo> implements Serializable{


	private static final long serialVersionUID = 1999209294244304212L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("idPessoa")
	private Long idPessoa;
	
	
	public static PessoaContaVo create(PessoaConta pessoaConta) {
		return new ModelMapper().map(pessoaConta, PessoaContaVo.class);
	}

	
	
}
