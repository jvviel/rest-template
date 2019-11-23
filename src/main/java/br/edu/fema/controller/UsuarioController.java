package br.edu.fema.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.model.Usuario;
import br.edu.fema.repository.IUsuarioJPARepository;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/usuarios")
@Api(description = "usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioJPARepository repository;
	
	@GetMapping
	public ResponseEntity<?> recuperarTodos() {
		List<Usuario> usuarios = this.repository.findAll();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{login}/{senha}")
	public ResponseEntity<?> validarLogin(@PathVariable String login, @PathVariable String senha) {
		Usuario usuario = this.repository.findByLoginAndSenha(login, senha);
		return usuario != null ? new ResponseEntity<>(usuario, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/busca/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = this.repository.findById(id);
		return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
		this.repository.save(usuario);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		usuario.setId(id);
		this.repository.save(usuario);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
