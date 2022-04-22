package br.com.projetosaula.springprojetoturmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetosaula.springprojetoturmas.entity.Pessoa;
import br.com.projetosaula.springprojetoturmas.entity.Professor;
import br.com.projetosaula.springprojetoturmas.repository.PessoaRepository;
import br.com.projetosaula.springprojetoturmas.repository.ProfessorRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Professor> listAll(){
		return professorRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor) throws Exception{
//		Se o Id da pessoa não existir/for igual a nulo
		if( professor.getPessoa().getId() == null ){
			
//		Pega o objeto (atributo da classe Professor) pessoa passado no corpo da requisição dentro do objeto Professor, e insere na tabela pessoa
			Pessoa pessoaProfessor = pessoaRepository.save(professor.getPessoa());
			
//		Seta a pessoa criada anteriomente como a pessoa do atributo pessoa da classe aluno
			professor.setPessoa(pessoaProfessor);
		} else {
//			Pesquisa pelo id da pessoa para verificar se já existe no banco, caso não exista irá ser criada uma exeção
			Pessoa pessoaVerificacao = pessoaRepository.findById(professor.getPessoa().getId()).orElseThrow(
							() -> new Exception("Registro não encontrado"));
//			seta a pessoa do atributo pessoa do professor passado na requisição como a retornada pela busca anterior
			professor.setPessoa(pessoaVerificacao);
		}

//		Cria o aluno com o parametro passado no corpo da requisição
		Professor professorResponse = professorRepository.save(professor);
		
		return	new ResponseEntity<Professor>(professorResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteAluno(@PathVariable Integer id) throws Exception{
		
		pessoaRepository.findById(id).orElseThrow(
				() -> new Exception("Registro não encontrado"));
		
		pessoaRepository.deleteById(id);
		
		return true;
	}
}
