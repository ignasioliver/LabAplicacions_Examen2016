insert into user(username, name, second_name, email, password)
values('ronaldo', 'Christiano', 'Ronaldo', 'ronaldo@gmail.com', '$2a$10$chkYYy3512kSzmgdKRkT6O8x7tV.DDn4E/fi8Vm1OOgHzxFbpOvRa');

insert into user(username, name, second_name, email, password)
values('messi', 'Leonel', 'Messi', 'messi@gmail.com', '$2a$10$ek7mLiU2ceVBFPOD7hmcVuVLwhJ8JODCbqpavROsZH/sQmd0xYNJm');

INSERT INTO user_roles (username, role)
VALUES ('ronaldo', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('ronaldo', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('messi', 'ROLE_USER');


insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Lleida-Pirineus');
insert into station(latitud, longitud, nom) values('41.654221', '0.685937', 'Alcoletge');
insert into station(latitud, longitud, nom) values('41.687383', '0.72789', 'Vilanova de la Barca');
insert into station(latitud, longitud, nom) values('41.716451', '0.76295', 'Térmens');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Vallfogona de Balaguer');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Balaguer');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Gerb');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Sant Llorenç de Montgai');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Vilanova de la Sal');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Santa Linya');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Àger');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Cellers-Llimiana');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Guàrdia de Tremp');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Palau de Noguera');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Tremp');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'Salàs de Pallars');
insert into station(latitud, longitud, nom) values('41.65434', '0.685766', 'La Pobla de Segur');

