package com.jdevtreinamentos.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private int idade;
	
	@NotBlank
	private LocalDate dataCadastro;
	
	
	
	

}
