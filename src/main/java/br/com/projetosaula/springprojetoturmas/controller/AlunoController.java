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

import br.com.projetosaula.springprojetoturmas.entity.Aluno;
import br.com.projetosaula.springprojetoturmas.entity.Pessoa;
import br.com.projetosaula.springprojetoturmas.repository.AlunoRepository;
import br.com.projetosaula.springprojetoturmas.repository.PessoaRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Aluno> listAllAlunos(){
		return alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> findByID(@PathVariable Integer id) throws Exception {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(
				()-> new Exception("ID não encontrado"));
		
		 return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
	}
	
	@PostMapping
//	ResponseEntity é uma classe,retorna uma entidade como resposta do metodo(requisição)
	public ResponseEntity<Aluno> addAluno(@RequestBody Aluno aluno){
//		Cria uma pessoa, pois para setar um aluno precisa existir uma pessoa na tabela, passa como parametro do metodo save
		//pegando o id passado como parametro no corpo da requisição
		Pessoa pessoaAluno = pessoaRepository.save(aluno.getPessoa());
//		aluno.getPessoa().setId(pessoaAluno.getId());
//		Seta a pessoa criada anteriomente como a pessoa do atributo pessoa da classe aluno
		aluno.setPessoa(pessoaAluno);
//		Cria o aluno com o parametro passado no corpo da requisição
		Aluno alunoResponse = alunoRepository.save(aluno);
		
//		Instância a classe ResponseEntity passando no construtor o aluno criado no metodo anterior, e o status 201 = Created 
		return	new ResponseEntity<Aluno>(alunoResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteAluno(@PathVariable Integer id) throws Exception{
		
		Aluno aluno = alunoRepository.findById(id).orElseThrow(
				() -> new Exception("Registro não encontrado"));
		
		Pessoa pessoa = pessoaRepository.findById(aluno.getPessoa().getId())
				.orElseThrow(() -> new Exception ("Id Pessoa não encontrado"));
		
		alunoRepository.deleteById(id);
		pessoaRepository.deleteById(pessoa.getId());
		
		return true;
	}
	
//	Não está sendo utilizado na API
	@DeleteMapping
	public boolean delAluno(@RequestBody Integer id) throws Exception{
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new Exception("Id Aluno não encontrado"));
		
		Pessoa pessoa = pessoaRepository.findById(aluno.getPessoa().getId())
				.orElseThrow(() -> new Exception ("Id Pessoa não encontrado"));
		
		alunoRepository.delete(aluno);
		pessoaRepository.delete(pessoa);
		return true;
	}
	
}
