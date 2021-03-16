package com.beccon.conta.data.vo;

import java.io.Serializable;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.beccon.conta.entity.Conta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({"id", "agencia", "tipo"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ContaVo extends RepresentationModel<ContaVo> implements Serializable{

	private static final long serialVersionUID = -5835376252546973285L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("agencia")
	private Long agencia;

	@JsonProperty("tipo")
	private String tipo;
	
	@JsonProperty("pessoa")
	private PessoaVo pessoa;  
	
	
	public static ContaVo create(Conta conta) {
		return new ModelMapper().map(conta, ContaVo.class);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getAgencia() {
		return agencia;
	}


	public void setAgencia(Long agencia) {
		this.agencia = agencia;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public PessoaVo getPessoa() {
		return pessoa;
	}


	public void setPessoa(PessoaVo pessoa) {
		this.pessoa = pessoa;
	}

	
}
