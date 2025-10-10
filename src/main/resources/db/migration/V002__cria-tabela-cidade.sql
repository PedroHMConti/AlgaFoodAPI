create table cidade(
id bigint not null auto_increment,
nome_cidade varchar(50) not null,
nome_estado varchar(50) not null,

primary key(id)
) engine = InnoDB default charset = utf8mb4;