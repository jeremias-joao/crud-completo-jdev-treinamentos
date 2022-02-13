package com.jdevtreinamentos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jdevtreinamentos.model.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
	
	@Query(value = "select u from UsuarioEntity u where upper(trim(u.nome)) like %?1%")
	List<UsuarioEntity>buscarPorNome(String nome);

}
