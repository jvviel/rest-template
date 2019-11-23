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

import br.edu.fema.model.Grupo;
import br.edu.fema.repository.IGrupoJPARepository;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private IGrupoJPARepository repository;
	
	@GetMapping
	public ResponseEntity<?> recuperarTodosGrupos() {
		List<Grupo> grupos = this.repository.findAll();
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}
	
	@GetMapping("/busca/{id}") 
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Optional<Grupo> grupo = this.repository.findById(id);
		return new ResponseEntity<>(grupo.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> salvarGrupo(@RequestBody Grupo grupo) {
		this.repository.save(grupo);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
		grupo.setId(id);
		this.repository.save(grupo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerGrupo(@PathVariable Long id) {
		this.repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
