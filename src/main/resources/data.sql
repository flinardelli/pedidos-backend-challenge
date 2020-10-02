INSERT INTO PRODUCTOS(id, nombre, descripcion_corta, descripcion_larga, precio_unitario)
VALUES ('89efb206-2aa6-4e21-8a23-5765e3de1f31', 'Jamón crudo y rúcula', 'Pizza de jamón crudo y rúcula', 'Mozzarella, jamón crudo y rúcula', 560.00);

INSERT INTO PRODUCTOS(id, nombre, descripcion_corta, descripcion_larga, precio_unitario)
VALUES ('e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1', 'Palmitos', 'Pizza de palmitos', 'Mozzarella, jamón cocido y palmitos', 560.00);

INSERT INTO PRODUCTOS(id, nombre, descripcion_corta, descripcion_larga, precio_unitario)
VALUES ('89efb206-2aa6-4e21-8a23-5765e3de1f30', 'Mozzarela', 'Pizza de mozzarella y salsa', 'Mozzarella, salsa y aceitunas', 400.00);

INSERT INTO PEDIDOS_CABECERA(id, direccion, email, telefono, horario, fecha_alta, monto_total)
VALUES ('89efb206-2aa6-4e21-8a23-5765e3de1f345', 'San Martín 885', 'tsayb@opera.com','(0351) 48158101', '12:00', '2020-10-01', 500.30);

INSERT INTO PEDIDOS_DETALLE(id, id_pedido_cabecera, id_producto, cantidad, precio_unitario)
VALUES ('89efb206-2aa6-4e21-8a23-5765e3de1f685', '89efb206-2aa6-4e21-8a23-5765e3de1f345','89efb206-2aa6-4e21-8a23-5765e3de1f30', 2, 200.60);
