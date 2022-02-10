--WAREHOUSES

INSERT INTO warehouse (geographic_area) VALUES ('São Paulo');
INSERT INTO warehouse (geographic_area) VALUES ('Santa Catarina');
INSERT INTO warehouse (geographic_area) VALUES ('Bahia');
INSERT INTO warehouse (geographic_area) VALUES ('Minas Gerais');
INSERT INTO warehouse (geographic_area) VALUES ('Paraíba');
INSERT INTO warehouse (geographic_area) VALUES ('Paraná');

--PROFILES
insert into profile(name) values ('CLIENT'); -- 1
insert into profile(name) values ('REPRESENTATIVE'); -- 2
insert into profile(name) values ('SELLER'); -- 3

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
--
--SELLER
-- password 321
INSERT INTO users (name, document, username, password, enabled) VALUES ('Supermercado Guanabara', '132.2312/1212-00','guanabara', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 1
INSERT INTO users (name, document, username, password, enabled) VALUES ('DescontoAki Açougue', '241.6346/9382-01','descontoaki', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 2
INSERT INTO users (name, document, username, password, enabled) VALUES ('Carlito Frios', '978.0001/2430-45','carlitofrios', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 3

--PRODUCT
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Frango Congelado', 'FROZEN', '2022-10-24', 2, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Pao de Queijo Congelado', 'FROZEN', '2022-04-11', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Manteiga', 'REFRIGERATED', '2022-01-10', 3, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Iogurte', 'REFRIGERATED', '2021-11-12', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('maça', 'FRESH', '2021-11-24', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('uva', 'FRESH', '2021-11-24', 1, 10.0);

--REPRESENTATIVE
-- password 234
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('José Rodriguez', '123.123.123', 1,'joserodriguez', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 4
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Patrícia Santos', '234.234.234', 2,'patriciasantos', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 5
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Roberta Freitas', '345.345.345', 3,'robertafreitas', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 6
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Pablo Rogério', '456.456.456', 4,'pablorogerio', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 7
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Rodrigo Dutra', '567.567.567', 5,'rodrigodutra', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 8
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Priscila Casagrande', '678.678.678', 6,'priscilacasagrande', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 9

--CLIENT
-- password 123
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('12345678','Gabriela da Rocha', 'Palhoça - SC', '222222', 'Santa Catarina', 'gabriela', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 10
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('31231323','Paula Rodrigues', 'Ermelino Matarazzo - SP', '000999', 'São Paulo', 'paula', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 11
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('83283823','Marcelo Nader', 'Liberdade - SP', '888347','São Paulo', 'marcelo', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 12
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('34234234','Rafael Oliveira', 'Barueri - SP', '949324','São Paulo', 'rafael', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 13
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('12345678','Felipe Conceição', 'Saúde - SP', '222452', 'São Paulo', 'felipe', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 14
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('31231323','Patrícia Santos', 'Sumé - PB', '123323', 'Paraíba', 'patricia', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 15
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('83283823','Rogéria Gonçalves', 'Terra Boa - PR', '342355','Paraná', 'rogeria', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 16
INSERT INTO users (document,name,address,telephone, state, username, password, enabled) VALUES ('34234234','Peter Rodolfo', 'Capitólio - MG', '003932','Minas Gerais', 'peter', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 17

INSERT INTO user_profiles (user_id, profile_id) VALUES (1, 3);
INSERT INTO user_profiles (user_id, profile_id) VALUES (2, 3);
INSERT INTO user_profiles (user_id, profile_id) VALUES (3, 3);
INSERT INTO user_profiles (user_id, profile_id) VALUES (4, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (5, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (6, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (7, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (8, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (9, 2);
INSERT INTO user_profiles (user_id, profile_id) VALUES (10, 1);
INSERT INTO user_profiles (user_id, profile_id) VALUES (11, 1);
INSERT INTO user_profiles (user_id, profile_id) VALUES (12, 1);
INSERT INTO user_profiles (user_id, profile_id) VALUES (13, 1);

-- -- DELIVERY
-- -- Delivery para São Paulo
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 3, 7.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 24, 15.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 20, 15.0, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 12, 10.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 30, 20.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('São Paulo', 24, 15.00, 6);
-- -- Delivery para Santa Catarina
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 24, 15.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 3, 7.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 36, 25.00, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 12, 15.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 48, 30.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Santa Catarina', 9, 10.00, 6);
-- -- Delivery para Bahia
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 20, 15.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 36, 25.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 3, 7.00, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 12, 15.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 12, 15.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Bahia', 36, 25.00, 6);
-- -- Delivery para Minas Gerais
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 12, 10.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 24, 15.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 12, 10.00, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 3, 7.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 20, 25.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Minas Gerais', 24, 25.00, 6);
-- -- Delivery para Paraíba
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 30, 30.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 36, 35.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 12, 10.00, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 20, 15.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 3, 7.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraíba', 36, 35.00, 6);
-- -- Delivery para Paraná
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 12, 15.00, 1);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 9, 10.00, 2);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 36, 30.00, 3);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 24, 15.00, 4);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 36, 30.00, 5);
INSERT INTO delivery_times(state, hours, shipping_value, warehouse_id) VALUES ('Paraná', 3, 7.00, 6);
-- -- DELIVERY
-- -- Delivery para São Paulo
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 3, 7.00);       -- 1 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (1, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 24, 15.00);      -- 2 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (2, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 20, 15.00);      -- 3 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (3, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 12, 10.00);       -- 4 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (4, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 30, 20.00);      -- 5 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (5, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('São Paulo', 24, 15.00);      -- 6 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (6, 6);
-- -- Delivery para Santa Catarina
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 24, 15.00);       -- 7 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (7, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 3, 7.00);      -- 8 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (8, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 36, 25.00);      -- 9 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (9, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 12, 15.00);       -- 10 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (10, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 48, 30.00);      -- 11 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (11, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Santa Catarina', 9, 10.00);      -- 12 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (12, 6);
-- -- Delivery para Bahia
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 20, 15.00);       -- 13 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (13, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 36, 25.00);      -- 14 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (14, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 3, 7.00);      -- 15 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (15, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 12, 15.00);       -- 16 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (16, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 12, 15.00);      -- 17 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (17, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Bahia', 36, 25.00);      -- 18 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (18, 6);
-- -- Delivery para Minas Gerais
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 12, 10.00);       -- 19 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (19, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 24, 15.00);      -- 20 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (20, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 12, 10.00);      -- 21 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (21, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 3, 7.00);       -- 22 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (22, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 20, 25.00);      -- 23 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (23, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Minas Gerais', 24, 25.00);      -- 24 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (24, 6);
-- -- Delivery para Paraíba
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 30, 30.00);       -- 25 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (25, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 36, 35.00);      -- 26 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (26, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 12, 10.00);      -- 27 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (27, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 20, 15.00);       -- 28 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (28, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 3, 7.00);      -- 29 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (29, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraíba', 36, 35.00);      -- 30 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (30, 6);
-- -- Delivery para Paraná
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 12, 15.00);       -- 31 - Warehouse São Paulo
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (31, 1);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 9, 10.00);      -- 32 - Warehouse Santa Catarina
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (32, 2);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 36, 30.00);      -- 33 - Warehouse Bahia
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (33, 3);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 24, 15.00);       -- 34 - Warehouse Minas Gerais
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (34, 4);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 36, 30.00);      -- 35 - Warehouse Paraíba
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (35, 5);
-- INSERT INTO delivery_times (state, hours, shipping_value) VALUES ('Paraná', 3, 7.00);      -- 36 - Warehouse Paraná
--     INSERT INTO deliverytime_warehouse (deliverytime_id, warehouse_id) VALUES (36, 6);
--
--
-- -- SELECT * FROM DELIVERY_TIMES INNER JOIN DELIVERYTIME_WAREHOUSE ON DELIVERY_TIMES.ID = DELIVERYTIME_WAREHOUSE.DELIVERYTIME_ID INNER JOIN WAREHOUSE ON DELIVERYTIME_WAREHOUSE.WAREHOUSE_ID = WAREHOUSE.ID
