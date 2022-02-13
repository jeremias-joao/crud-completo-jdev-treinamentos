package com.jdevtreinamentos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jdevtreinamentos.model.UsuarioEntity;
import com.jdevtreinamentos.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional
	public UsuarioEntity save(UsuarioEntity usuarioEntity) {
		return usuarioRepository.save(usuarioEntity);
	}

	public Page<UsuarioEntity> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	public Optional<UsuarioEntity> findById(UUID id) {
		return usuarioRepository.findById(id);
	}

	@Transactional
	public void delete(UsuarioEntity usuarioEntity) {
		usuarioRepository.delete(usuarioEntity);
	}
	
	public List<UsuarioEntity>findByName(String nome){
		return usuarioRepository.buscarPorNome(nome);
	}
	

}
