CREATE TABLE hogar (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(128) NOT NULL
);

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL,
    apellido VARCHAR(32) NOT NULL,
    pass VARCHAR(32) NOT NULL,
    hogar_id BIGINT,
    CONSTRAINT fk_usuario_hogar FOREIGN KEY (hogar_id) REFERENCES hogar(id)
);

CREATE TABLE producto (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    presentacion VARCHAR(64) NOT NULL
);

CREATE TABLE stock (
    id BIGSERIAL PRIMARY KEY,
    hogar_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT fk_stock_hogar FOREIGN KEY (hogar_id) REFERENCES hogar(id),
    CONSTRAINT fk_stock_producto FOREIGN KEY (producto_id) REFERENCES producto(id)
);