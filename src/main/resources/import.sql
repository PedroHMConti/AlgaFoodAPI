insert into permissao(nome, descricao) values ('CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao(nome, descricao) values ('EDITAR_USUARIOS', 'Permite editar dados de usuários');
insert into permissao(nome, descricao) values ('GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');

INSERT INTO cozinha (nome) VALUES ('Brasileira');
INSERT INTO cozinha (nome) VALUES ('Italiana');
INSERT INTO cozinha (nome) VALUES ('Baiana');
INSERT INTO cozinha (nome) VALUES ('Japonesa');
INSERT INTO cozinha (nome) VALUES ('Americana');


INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Restaurante Sabor do Campo', 12.50, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Cantina Italiana', 8.00, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Tempero Baiano', 10.00, 3);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Sushi Zen', 15.00, 4);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Burguer Brasil', 5.00, 5);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Churrascaria Gaúcha', 20.00, 1);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Pizzaria Napoli', 9.50, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Acarajé da Bahia', 7.00, 3);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Sakura Sushi', 13.00, 4);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id) VALUES ('Texas Burgers', 6.00, 5);


INSERT INTO estado (id, nome) VALUES (1, 'Acre');
INSERT INTO estado (id, nome) VALUES (2, 'Alagoas');
INSERT INTO estado (id, nome) VALUES (3, 'Amazonas');
INSERT INTO estado (id, nome) VALUES (4, 'Bahia');
INSERT INTO estado (id, nome) VALUES (5, 'Ceará');
INSERT INTO estado (id, nome) VALUES (6, 'Distrito Federal');
INSERT INTO estado (id, nome) VALUES (7, 'Espírito Santo');
INSERT INTO estado (id, nome) VALUES (8, 'Goiás');
INSERT INTO estado (id, nome) VALUES (9, 'Maranhão');
INSERT INTO estado (id, nome) VALUES (10, 'Mato Grosso');


INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Rio Branco', 1);          -- Acre
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Cruzeiro do Sul', 1);

INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'Maceió', 2);              -- Alagoas
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Arapiraca', 2);

INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Manaus', 3);              -- Amazonas
INSERT INTO cidade (id, nome, estado_id) VALUES (6, 'Parintins', 3);

INSERT INTO cidade (id, nome, estado_id) VALUES (7, 'Salvador', 4);            -- Bahia
INSERT INTO cidade (id, nome, estado_id) VALUES (8, 'Feira de Santana', 4);

INSERT INTO cidade (id, nome, estado_id) VALUES (9, 'Fortaleza', 5);           -- Ceará
INSERT INTO cidade (id, nome, estado_id) VALUES (10, 'Juazeiro do Norte', 5);

INSERT INTO cidade (id, nome, estado_id) VALUES (11, 'Brasília', 6);           -- Distrito Federal
INSERT INTO cidade (id, nome, estado_id) VALUES (12, 'Samambaia', 6);

INSERT INTO cidade (id, nome, estado_id) VALUES (13, 'Vitória', 7);            -- Espírito Santo
INSERT INTO cidade (id, nome, estado_id) VALUES (14, 'Serra', 7);

INSERT INTO cidade (id, nome, estado_id) VALUES (15, 'Goiânia', 8);            -- Goiás
INSERT INTO cidade (id, nome, estado_id) VALUES (16, 'Anápolis', 8);

INSERT INTO cidade (id, nome, estado_id) VALUES (17, 'São Luís', 9);           -- Maranhão
INSERT INTO cidade (id, nome, estado_id) VALUES (18, 'Imperatriz', 9);

INSERT INTO cidade (id, nome, estado_id) VALUES (19, 'Cuiabá', 10);            -- Mato Grosso
INSERT INTO cidade (id, nome, estado_id) VALUES (20, 'Rondonópolis', 10);

insert into forma_de_pagamento (descricao) values ('Cartão de Crédito');
insert into forma_de_pagamento (descricao) values ('Cartão de Débito');
insert into forma_de_pagamento (descricao) values ('Dinheiro');
insert into forma_de_pagamento (descricao) values ('Pix');
insert into forma_de_pagamento (descricao) values ('Vale Refeição');


-- Restaurante 1: Restaurante Sabor do Campo
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 4);

-- Restaurante 2: Cantina Italiana
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 5);

-- Restaurante 3: Tempero Baiano
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 4);

-- Restaurante 4: Sushi Zen
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (4, 4);

-- Restaurante 5: Burguer Brasil
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (5, 3);

-- Restaurante 6: Churrascaria Gaúcha
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (6, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (6, 4);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (6, 5);

-- Restaurante 7: Pizzaria Napoli
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (7, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (7, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (7, 5);

-- Restaurante 8: Acarajé da Bahia
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (8, 3);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (8, 4);

-- Restaurante 9: Sakura Sushi
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (9, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (9, 4);

-- Restaurante 10: Texas Burgers
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (10, 1);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (10, 2);
INSERT INTO restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (10, 3);
