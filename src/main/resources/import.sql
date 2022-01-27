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

INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, quantity_stock) VALUES ('Frango Congelado', 2, null, 2, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, quantity_stock) VALUES ('Pao de Queijo Congelado', 2, null, 1, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, quantity_stock) VALUES ('Manteiga', 1, null, 3, null);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id, quantity_stock) VALUES ('Iogurte', 1, null, 1, null);