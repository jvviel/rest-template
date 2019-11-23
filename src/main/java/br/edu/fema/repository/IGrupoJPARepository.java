package br.edu.fema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fema.model.Grupo;

public interface IGrupoJPARepository extends JpaRepository<Grupo, Long>{

}
