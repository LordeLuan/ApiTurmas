package br.com.projetosaula.springprojetoturmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetosaula.springprojetoturmas.entity.Pessoa;
import br.com.projetosaula.springprojetoturmas.repository.PessoaRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
	
//	Faz uma interface entre o back-end e o banco de dados.
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping
	public List<Pessoa> getAll(){
		return pessoaRepository.findAll();
	}
	
//	GetMapping é chamado quando é passado algum parametro na url, após a url raiz, no caso api/pessoa/ + parametro id
	@GetMapping("/{id}")
	public Pessoa getPessoaById(@PathVariable("id") Integer id) throws Exception {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new Exception("Pessoa não encontrado com o id= " + id));
	return pessoa;
	}
	
//	PERSISTIR É SALVAR / INSERIR INFORMAÇÃO NO BANCO
	
//	Annotations para inserir informações que vierem da raiz
	@PostMapping
										//Anotations @RequestBody sinalizando que é para passar a informação no corpo da requisição
	public ResponseEntity<Pessoa> addPessoa(@RequestBody Pessoa pessoa) throws Exception{
//		Metodo save insere a informação no banco
		Pessoa pessoaResponse = pessoaRepository.save(pessoa);
	
//		Devolve na resposta da requisição o status 200(metodo OK) no header e no corpo da resposta o próprio objeto que foi inserido
		return new ResponseEntity<Pessoa>(pessoaResponse,HttpStatus.CREATED);
	}
	
	
}
