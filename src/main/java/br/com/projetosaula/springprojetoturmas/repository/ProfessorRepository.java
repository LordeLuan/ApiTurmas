package br.com.projetosaula.springprojetoturmas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetosaula.springprojetoturmas.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
