--WAREHOUSES

INSERT INTO warehouse (geographic_area) VALUES ('São Paulo');
INSERT INTO warehouse (geographic_area) VALUES ('Santa Catarina');
INSERT INTO warehouse (geographic_area) VALUES ('Bahia');
INSERT INTO warehouse (geographic_area) VALUES ('Minas Gerais');
INSERT INTO warehouse (geographic_area) VALUES ('Paraíba');
INSERT INTO warehouse (geographic_area) VALUES ('Paraná');

--SECTIONS

INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 500, 500, 1);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10, 'REFRIGERATED', 650, 650, 1);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 400, 400, 2);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10.0, 'REFRIGERATED', 400, 400, 2);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 350, 350, 3);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10.0, 'REFRIGERATED', 600, 600, 3);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 450, 450, 4);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10.0, 'REFRIGERATED', 500, 500, 4);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 200, 200, 5);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10.0, 'REFRIGERATED', 300, 300, 5);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (-5.0, 'FROZEN', 500, 500, 6);
INSERT INTO section (temperature, storage_type, max_capacity, available_capacity, warehouse_id) VALUES (10.0, 'REFRIGERATED', 450, 450, 6);

--SELLER

INSERT INTO seller (name, document) VALUES ('Supermercado Guanabara', '132.2312/1212-00');
INSERT INTO seller (name, document) VALUES ('DescontoAki Açougue', '241.6346/9382-01');
INSERT INTO seller (name, document) VALUES ('Carlito Frios', '978.0001/2430-45');

--PRODUCT

INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('Frango Congelado', 2, '2021-11-24', 2, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('Pao de Queijo Congelado', 2, '2021-11-24', 1, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('Manteiga', 1, '2021-11-24', 3, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('Iogurte', 1, '2021-11-24', 1, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('maça', 0, '2021-11-24', 1, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, stock_id) VALUES ('uva', 0, '2021-11-24', 1, null);

--REPRESENTATIVE

INSERT INTO representative (name, document, warehouse_id) VALUES ('José Rodriguez', '123.123.123', 1);
INSERT INTO representative (name, document, warehouse_id) VALUES ('Patrícia Santos', '234.234.234', 2);
INSERT INTO representative (name, document, warehouse_id) VALUES ('Roberta Freitas', '345.345.345', 3);
INSERT INTO representative (name, document, warehouse_id) VALUES ('Pablo Rogério', '456.456.456', 4);
INSERT INTO representative (name, document, warehouse_id) VALUES ('Rodrigo Dutra', '567.567.567', 5);
INSERT INTO representative (name, document, warehouse_id) VALUES ('Priscila Casagrande', '678.678.678', 6);
