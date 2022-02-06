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
-- --SELLER
-- password 321
INSERT INTO users (name, document, username, password, enabled) VALUES ('Supermercado Guanabara', '132.2312/1212-00','guanabara', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 1
INSERT INTO users (name, document, username, password, enabled) VALUES ('DescontoAki Açougue', '241.6346/9382-01','descontoaki', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 2
INSERT INTO users (name, document, username, password, enabled) VALUES ('Carlito Frios', '978.0001/2430-45','carlitofrios', '$2a$10$FhB/9FUavDkVAkXx/i4f7e7oOUdQ0/.rMi2FmTmjA0b46xR3c7gbW', '1'); -- 3

--PRODUCT
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Frango Congelado', 'FROZEN', '2021-10-24', 2, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Pao de Queijo Congelado', 'FROZEN', '2022-01-11', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Manteiga', 'REFRIGERATED', '2022-01-10', 3, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Iogurte', 'REFRIGERATED', '2021-11-12', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('maça', 'FRESH', '2021-11-24', 1, 10.0);
INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('uva', 'FRESH', '2021-11-24', 1, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Frango Congelado', 2, '2021-10-24', 2, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Pao de Queijo Congelado', 2, '2022-01-11', 1, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Manteiga', 1, '2022-01-10', 3, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('Iogurte', 1, '2021-11-12', 1, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('maça', 0, '2021-11-24', 1, 10.0);
-- INSERT INTO product (name, section_type_refrigerated, date_valid, seller_id,price) VALUES ('uva', 0, '2021-11-24', 1, 10.0);
--
-- --REPRESENTATIVE
-- password 234
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('José Rodriguez', '123.123.123', 1,'joserodriguez', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 4
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Patrícia Santos', '234.234.234', 2,'patriciasantos', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 5
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Roberta Freitas', '345.345.345', 3,'robertafreitas', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 6
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Pablo Rogério', '456.456.456', 4,'pablorogerio', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 7
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Rodrigo Dutra', '567.567.567', 5,'rodrigodutra', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 8
INSERT INTO users (name, document, warehouse_id, username, password, enabled) VALUES ('Priscila Casagrande', '678.678.678', 6,'priscilacasagrande', '$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe', '1'); -- 9

--CLIENT
-- password 123
INSERT INTO users (document,name,address,telephone, username, password, enabled) VALUES ('12345678','Gabriela da Rocha', 'Palhoça - SC', '222222','gabriela', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 10
INSERT INTO users (document,name,address,telephone, username, password, enabled) VALUES ('31231323','Paula Rodrigues', 'Ermelino Matarazzo - SP', '000999','paula', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 11
INSERT INTO users (document,name,address,telephone, username, password, enabled) VALUES ('83283823','Marcelo Nader', 'Liberdade - SP', '888347','marcelo', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 12
INSERT INTO users (document,name,address,telephone, username, password, enabled) VALUES ('34234234','Rafael Oliveira', 'Barueri - SC', '949324','rafael', '$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe', '1'); -- 13


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
