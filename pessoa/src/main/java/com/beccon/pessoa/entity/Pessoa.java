package com.beccon.pessoa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.beccon.pessoa.data.vo.PessoaVo;
import com.beccon.pessoa.data.vo.PessoaVo.TipoPessoa;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="tb_pessoa")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Pessoa implements Serializable{

	
	private static final long serialVersionUID = -2762050262423272183L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable = false, length = 255)
	private String nome;
	
	@Column(name="tipo", nullable = false, length = 8)
	private TipoPessoa tipo;
	
	@Column(name="score", nullable = false, length = 1)
	private Integer score;

	@Column(name="documento", nullable = false, length = 11)
	private String documento;

	public static Pessoa create(PessoaVo pessoaVo) {
		return new ModelMapper().map(pessoaVo, Pessoa.class);
	}

	public Pessoa() {
		this.setScore();
	}

	
	
	public Pessoa(Long id, String nome, TipoPessoa tipo, Integer score, String documento) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.setScore();
		this.documento = documento;
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

	public void setScore() {
		this.score = (int) ((Math.random() * (10)));
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}	
	
	
	
	
}
