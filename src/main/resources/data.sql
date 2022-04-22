INSERT INTO pessoa (nome, idade, endereco, sexo) values ('Josemar', 48, 'Rua não é da sua conta', 'M');
INSERT INTO pessoa (nome, idade, endereco, sexo) values ('Josemeire', 55, 'Rua não sei', 'F');
INSERT INTO pessoa (nome, idade, endereco, sexo) values ('Joseiel', 34, 'Rua Mariupol', 'M');
INSERT INTO pessoa (nome, idade, endereco, sexo) values ('Josemarcial', 80, 'Rua Fernando', 'M');

INSERT INTO professor (pessoa_id) VALUES (4);


INSERT INTO aluno (ano , pessoa_id) VALUES (5, 1);
INSERT INTO aluno (ano , pessoa_id) VALUES (5, 2);
INSERT INTO aluno (ano , pessoa_id) VALUES (6, 3);

INSERT INTO turma (nome, periodo, max_alunos, professor_id) VALUES ('Turma 1', 'Noturno', 25, 1);
INSERT INTO turma (nome, periodo, max_alunos, professor_id) VALUES ('Turma 2', 'Matutino', 25, 1);
INSERT INTO turma (nome, periodo, max_alunos, professor_id) VALUES ('Turma 3', 'Noturno', 25, 1);