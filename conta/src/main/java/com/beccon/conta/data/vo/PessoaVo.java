package com.beccon.conta.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.beccon.conta.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({"id", "nome", "documento", "score"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaVo extends RepresentationModel<PessoaVo> implements Serializable{

	private static final long serialVersionUID = 4842297939935010251L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;

	@JsonProperty("documento")
	private String documento;

	@JsonProperty("score")
	private Integer score;

	
	public static PessoaVo create(Pessoa pessoa) {
		return new ModelMapper().map(pessoa, PessoaVo.class);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}

	
}
