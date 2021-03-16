package com.beccon.pessoa.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.beccon.pessoa.entity.Pessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({"id", "nome", "tipo", "score"})
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PessoaVo extends RepresentationModel<PessoaVo> implements Serializable {
	
	@JsonProperty("id")	
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("tipo")
	private TipoPessoa tipo;
	
	@JsonProperty("score")
	private Integer score;
	
	@JsonProperty("documento")
	private String documento;
	
	public static PessoaVo create(Pessoa produto){
		return new ModelMapper().map(produto, PessoaVo.class);
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

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	 public enum TipoPessoa {
	        PF, PJ
	 }		
	
}
