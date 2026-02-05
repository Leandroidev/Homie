INSERT INTO hogar (id, nombre) VALUES (100, 'Casa Franc');
INSERT INTO hogar (id, nombre) VALUES (200, 'Casa Ardak');
INSERT INTO hogar (id, nombre) VALUES (300, 'Casa Iroma');

INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (100, 'Juan', 'Gómez', '111', 100);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (200, 'Marisa', 'López', '222', 100);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (300, 'Ana', 'Pérez', '333', 200);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (400, 'Luz', 'García', '444', 200);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (500, 'Jose', 'Ruiz', '555', 300);
INSERT INTO usuario (id, nombre, apellido, pass, hogar_id) VALUES (600, 'Tucho', 'Prueba', '666', NULL);

INSERT INTO producto (id, nombre, presentacion) VALUES (100, 'Arroz', '1kg');
INSERT INTO producto (id, nombre, presentacion) VALUES (200, 'Fideos', '500g');
INSERT INTO producto (id, nombre, presentacion) VALUES (300, 'Azúcar', '1kg');

INSERT INTO stock (hogar_id, producto_id, cantidad) VALUES (100, 100, 2);
INSERT INTO stock (hogar_id, producto_id, cantidad) VALUES (100, 200, 1);
INSERT INTO stock (hogar_id, producto_id, cantidad) VALUES (200, 100, 5);
INSERT INTO stock (hogar_id, producto_id, cantidad) VALUES (300, 300, 3);
