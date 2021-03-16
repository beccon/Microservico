package com.beccon.conta.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.beccon.conta.data.vo.PessoaVo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tb_pessoa_conta")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PessoaConta implements Serializable{

	private static final long serialVersionUID = 1266072429423607223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="id_conta", nullable = false, length = 10)
	private Long idConta;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	public static PessoaConta create(PessoaVo p) {
		return new ModelMapper().map(p, PessoaConta.class);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIdConta() {
		return idConta;
	}


	public void setIdConta(Conta conta) {
		this.idConta = conta.getId();
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


}
