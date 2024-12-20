create database bdclientes;


CREATE TABLE usuario(
id integer primary key auto_increment,
usuario char(100),
senha char(200)
);


select * from usuario;
select * from cliente;

create table cliente(
id integer primary key auto_increment,
nome char(100),
cpfcnpj char(100),
email char(100),
telefone char(100),
endereco char(100)
);