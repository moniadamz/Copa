-- This will create some database objects as well some data on server init,
-- Springboot will execute all DML/DDL commands it gets on data.sql
-- more: https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html (78.3 Initialize a database)

--Clean all objects
DROP ALL OBJECTS;

CREATE TABLE selecao (
  cnpj integer(10) PRIMARY KEY,
  nome_selecao VARCHAR(20),
  nome_estadio VARCHAR(50)

);

CREATE TABLE jogador (
 cpf integer(20) PRIMARY KEY,
 nome VARCHAR(30),
 posicao VARCHAR(50),
  camisa VARCHAR(3),
selecao_cnpj integer(10),

);
 --alter table jogador add constraint time_jogador foreign key selecao_cnpj references selecao;

INSERT INTO selecao(cnpj, nome_selecao, nome_estadio) VALUES (984890480,'Brasil', 'Maracana');

INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (506465010, 'Marcelo', 'lateral direito', '05', 984890480);
INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (60080648, 'Juninho', 'lateral esquerdo', '10', 984890480);
INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (980794520, 'Alisson', 'goleiro', '12', 984890480);
INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (548703014, 'Paulo', 'zagueiro', '04', 984890480);
INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (504890897, 'Henrique', 'meio campo', '22', 984890480);
INSERT INTO jogador(cpf, nome, posicao, camisa, selecao_cnpj) VALUES (871083500, 'Neymar', 'atacante', '11', 984890480);


