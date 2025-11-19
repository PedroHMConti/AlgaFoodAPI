CREATE TABLE restaurante_usuario (
    usuario_id BIGINT NOT NULL,
    restaurante_id BIGINT NOT NULL,

    PRIMARY KEY (usuario_id, restaurante_id),

    CONSTRAINT fk_restaurante_usuario_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8MB3;
