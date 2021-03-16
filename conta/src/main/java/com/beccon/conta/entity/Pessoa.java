package com.beccon.conta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.beccon.conta.data.vo.PessoaVo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tb_pessoa")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pessoa {

	@Id
	private Long id;
	
	@Column(name="nome", nullable = false, length = 10)
	private String nome;
	
	@Column(name="tipo", nullable = false, length = 10)
	private String tipo;

	@Column(name="documento", nullable = false, length = 10)
	private String documento;

	@Column(name="score", nullable = false, length = 10)
	private String score;

	public static Pessoa create(PessoaVo pessoaVo) {
		return new ModelMapper().map(pessoaVo, Pessoa.class);
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	
	
}
