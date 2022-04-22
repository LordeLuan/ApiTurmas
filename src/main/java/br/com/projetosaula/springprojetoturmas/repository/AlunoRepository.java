package br.com.projetosaula.springprojetoturmas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetosaula.springprojetoturmas.entity.Aluno;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

	
}
