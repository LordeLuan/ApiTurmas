package br.com.projetosaula.springprojetoturmas.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String periodo;
	private int maxAlunos;
	
//	CARDINALIDADE: Uma turma para muitos alunos
	@OneToMany(mappedBy = "turma")
	private List<Aluno> alunos;
	
//	Um professor por turma
	@OneToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	public Turma() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getMaxAlunos() {
		return maxAlunos;
	}

	public void setMaxAlunos(int maxAlunos) {
		this.maxAlunos = maxAlunos;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
//		Verifica se a quantidade de aluno no arraylist é menor que o máximo permitido
		if(alunos.size() <= getMaxAlunos()) {
			this.alunos = alunos;
		}
	}
	
//	Passando um único aluno da classe Aluno por parametro
	public void addAluno(Aluno aluno) {
//		Se a quantidade de aluno atual for menor que o máximo que retornar no metodo get, insere um aluno no atributo arraylist
		if(getAlunos().size() <= getMaxAlunos()) {
//			insere um aluno no atributo arraylist alunos
//			this.alunos.add(aluno);
//			Forma encapsulada, o get retorna a lista de arrayAluno e então adiciona o Aluno passado como parametro no atributo retornado.
			getAlunos().add(aluno);
		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	

}
