package com.beccon.conta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.beccon.conta.data.vo.ContaVo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tb_conta")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Conta implements Serializable{

	private static final long serialVersionUID = 2619587989430310976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name="numero", nullable = false, length = 10)
	private String numero;

	@Column(name="agencia", nullable = false, length = 10)
	private String agencia;

	@Column(name="tipo", nullable = false, length = 10)
	private String tipo;

	@OneToOne(fetch= FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.REFRESH)
	private PessoaConta pessoaConta;  
		
	public static Conta create(ContaVo contaVo) {
		return new ModelMapper().map(contaVo, Conta.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public PessoaConta getPessoaConta() {
		return pessoaConta;
	}

	public void setPessoaConta(PessoaConta pessoaConta) {
		this.pessoaConta = pessoaConta;
	}


}
