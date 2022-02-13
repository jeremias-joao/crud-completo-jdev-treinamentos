package com.jdevtreinamentos.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@EqualsAndHashCode

public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "idade")
	private int idade;

	@Column(name = "dataCadastro")
	private LocalDate dataCadastro = LocalDate.now();

	public UsuarioEntity() {
		
	}
	
	

	

}
