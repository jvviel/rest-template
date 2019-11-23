package br.edu.fema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.model.Usuario;

public interface IUsuarioJPARepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByLoginAndSenha(String login, String senha);
}
