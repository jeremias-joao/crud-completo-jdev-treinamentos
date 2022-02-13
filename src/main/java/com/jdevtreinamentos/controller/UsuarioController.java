package com.jdevtreinamentos.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jdevtreinamentos.dto.UsuarioDTO;
import com.jdevtreinamentos.model.UsuarioEntity;
import com.jdevtreinamentos.repository.UsuarioRepository;
import com.jdevtreinamentos.service.UsuarioService;

import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/user-api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioService usuarioService;

//	@RequestMapping(value = "/mostrarnome/{name}", method =  RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String greetingText(@PathVariable String nome) {
//		return "Curso de Spring Boot API";
//	}
//	
//	@RequestMapping(value = "/olamundo/{nome}", method =  RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String retornaOlaMundo(@PathVariable String nome) {
//		return "Ola Mundo" + nome;
//		
//	}

	@GetMapping(value = "listartodos")
	@ResponseBody
	public ResponseEntity<Page<UsuarioEntity>> getAllUser(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll(pageable));
	}

	@GetMapping(value = "/{iduser}")
	public ResponseEntity<Object> getOneUserById(@PathVariable(value = "iduser") UUID id) {
		Optional<UsuarioEntity> usuarioOptional = usuarioService.findById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao Encontrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}

	// Em postman tem que passar o body ->form date
	// key name e value o nome a ser pesquisado no banco
	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<UsuarioEntity>> findUserByName(@RequestParam(name = "name") String name) {

		List<UsuarioEntity> usuario = usuarioService.findByName(name.trim().toUpperCase());

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@PostMapping(value = "salvar")
	@ResponseBody
	public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioDTO usuarioDTO) {

		var usuarioEntity = new UsuarioEntity();
		BeanUtils.copyProperties(usuarioDTO, usuarioEntity);
		usuarioEntity.setDataCadastro(LocalDate.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioEntity));
	}

//	@PostMapping(value = "salvar") 
//	@ResponseBody
//	public ResponseEntity<UsuarioEntity>salvar(@RequestBody UsuarioEntity usuario) {
//		
//		UsuarioEntity user = usuarioRepository.save(usuario);
//		
//		return new ResponseEntity<UsuarioEntity>(user, HttpStatus.CREATED);
//	}

	// Em postman tem que passar o body (form date ou x-www-form-urlencode
	// key iduser e numero de id para deletar e bucaruserid
	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam UUID iduser) {
		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<>("User deletado com sucesso", HttpStatus.OK);
	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody UsuarioEntity usuario) {

		if (usuario.getId() == null) {
			return new ResponseEntity<>("Id nao foi informado para atualização.", HttpStatus.OK);
		}
		UsuarioEntity user = usuarioRepository.saveAndFlush(usuario);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
