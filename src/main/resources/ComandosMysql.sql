//CREACION DE TABLAS

USE homie;

CREATE TABLE hogar (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(128) NOT NULL
);

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(32) NOT NULL,
    apellido VARCHAR(32) NOT NULL,
    pass VARCHAR(32) NOT NULL,
    hogar_id BIGINT,
    CONSTRAINT fk_usuario_hogar FOREIGN KEY (hogar_id) REFERENCES hogar(id)
);

CREATE TABLE producto (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(64) NOT NULL,
    presentacion VARCHAR(64) NOT NULL
);

CREATE TABLE stock (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hogar_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL,
    CONSTRAINT fk_stock_hogar FOREIGN KEY (hogar_id) REFERENCES hogar(id),
    CONSTRAINT fk_stock_producto FOREIGN KEY (producto_id) REFERENCES producto(id)
);

//DATOS

INSERT INTO hogar (id, nombre)
VALUES (100, 'Casa Franc MySql');
INSERT INTO hogar (id, nombre)
VALUES (200, 'Casa Ardak MySql');
INSERT INTO hogar (id, nombre)
VALUES (300, 'Casa Iroma MySql');

INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (100, 'Juan', 'Gómez MySql', '111', 100);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (200, 'Marisa', 'López MySql', '222', 100);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (300, 'Ana', 'Pérez MySql', '333', 200);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (400, 'Luz', 'García MySql', '444', 200);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (500, 'Jose', 'RuizMySql', '555', 300);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id)
VALUES (600, 'Tucho', 'PruebaMySql', '666', NULL);

INSERT INTO producto (id, nombre, presentacion)
VALUES (100, 'ArrozMySql', '1kg');
INSERT INTO producto (id, nombre, presentacion)
VALUES (200, 'FideosMySql', '500g');
INSERT INTO producto (id, nombre, presentacion)
VALUES (300, 'AzúcarMySql', '1kg');
