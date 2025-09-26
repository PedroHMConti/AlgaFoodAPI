CREATE TABLE cozinha(
id int not null auto_increment,
nome varchar(255) not null,
primary key (id));



create table cidade(
id bigint not null auto_increment,
nome_cidade varchar(255),
nome_estado varchar(255) not null,

primary key (id)
)engine = InnoDB default charset=utf8mb4;
