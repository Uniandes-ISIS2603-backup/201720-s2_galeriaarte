
delete from ClienteEntity;
ALTER TABLE ClienteEntity ALTER COLUMN id RESTART WITH 1;
delete from CompraEntity;
ALTER TABLE CompraEntity ALTER COLUMN id RESTART WITH 1;
delete from PagoEntity;
ALTER TABLE PagoEntity ALTER COLUMN id RESTART WITH 1;
delete from ArtistaEntity;
ALTER TABLE ArtistaEntity ALTER COLUMN id RESTART WITH 1;

delete from ObraEntity;
ALTER TABLE ObraEntity ALTER COLUMN id RESTART WITH 1;
delete from HojaVidaEntity;
ALTER TABLE HojaVidaEntity ALTER COLUMN id RESTART WITH 1;
delete from MarcoEntity;
ALTER TABLE MarcoEntity ALTER COLUMN id RESTART WITH 1;
delete from BlogEntity;
ALTER TABLE BlogEntity ALTER COLUMN id RESTART WITH 1;
delete from ComentarioEntity;
ALTER TABLE ComentarioEntity ALTER COLUMN id RESTART WITH 1;

insert into CompraEntity (id, valor, fecha) values (1, 20, '01/02/2001');
insert into CompraEntity (id, valor, fecha) values (2, 30, '08/06/2009');
insert into CompraEntity (id, valor, fecha) values (3, 40, '07/09/2007');
 
insert into PagoEntity (id, total, impuesto) values (25, 20, 6);
insert into PagoEntity (id, total, impuesto) values (26, 30, 7);
insert into PagoEntity (id, total, impuesto) values (27, 40, 8);

insert into ArtistaEntity (id, name) values (1, 'Pedro');
insert into ArtistaEntity (id, name) values (2, 'Pablo');
insert into ArtistaEntity (id, name) values (3, 'Pepe');
insert into ArtistaEntity (id, name) values (4, 'Juan');
insert into ArtistaEntity (id, name) values (5, 'Carlos');

insert into ObraEntity (id, cantidad, name, tipo, valor, artista_id) values (1, 5, 'Las meninas', 'Pintura', 20000, 1);
insert into ObraEntity (id, cantidad, name, tipo, valor, artista_id) values (2, 4, 'La últimma', 'Pintura', 30000,2);
insert into ObraEntity (id, cantidad, name, tipo, valor, artista_id) values (3, 3, 'La noche estrellada', 'Pintura', 40000,3);
insert into ObraEntity (id, cantidad, name, tipo, valor, artista_id) values (4, 2, 'El nacimiento de Venus', 'Pintura', 50000,4);
insert into ObraEntity (id, cantidad, name, tipo, valor, artista_id) values (5, 1, 'La creación de Adán', 'Pintura', 60000,5);

insert into HojaVidaEntity (id, almamater, nacionalidad, name, trayectoria) values (1, 'Universidad de los Andes', 'Colombiano', 'Kelvin', 'Recibí el premio Mejor Artista 2010');
insert into HojaVidaEntity (id, almamater, nacionalidad, name, trayectoria) values (2, 'Universidad del Rosario', 'Venezolano', 'Juan Daniel', 'Recibí el premio Mejor Artista 2011');
insert into HojaVidaEntity (id, almamater, nacionalidad, name, trayectoria) values (3, 'Universidad Nacional', 'Argentino', 'Daniel', 'Recibí el premio Mejor Artista 2012');
insert into HojaVidaEntity (id, almamater, nacionalidad, name, trayectoria) values (4, 'Universidad de los Andes', 'Colombiana', 'Maria Alejandra', 'Recibí el premio Mejor Artista 2013');
insert into HojaVidaEntity (id, almamater, nacionalidad, name, trayectoria) values (5, 'Universidad Distrital', 'Chileno', 'Andrés', 'Recibí el premio Mejor Artista 2014');

insert into MarcoEntity (id, alto, ancho, material, name, valor) values (1, 10, 5, 'Madera','Marco Chileno', 50000);
insert into MarcoEntity (id, alto, ancho, material, name, valor) values (2, 15, 10, 'Metal','Marco Italiano', 55000);
insert into MarcoEntity (id, alto, ancho, material, name, valor) values (3, 20, 15, 'Madera','Marco Francés', 75000);
insert into MarcoEntity (id, alto, ancho, material, name, valor) values (4, 25, 20, 'Metal','Marco Chileno', 95000);
insert into MarcoEntity (id, alto, ancho, material, name, valor ) values (5, 30, 25, 'Madera','Marco Italiano', 500000);

insert into BlogEntity (id, contenido, name, artista_id ) values (1,'Contenido1', 'Como entender el arte abstracta',1);
insert into BlogEntity (id, contenido, name, artista_id ) values (2,'Contenido2', 'Como aprender a pintar',2);
insert into BlogEntity (id, contenido, name, artista_id ) values (3,'Contenido3', 'Aprendiendo analizar el arte',3);
insert into BlogEntity (id, contenido, name, artista_id ) values (4,'Contenido4', 'El arte de las esculturas',4);
insert into BlogEntity (id, contenido, name, artista_id ) values (5,'Contenido5', 'Las esculturas vs las obras de arte',5);


insert into ClienteEntity (id, name, numTarjeta, tipoTarjeta) values (1, 'Kelvin', 10, 'Visa');
insert into ClienteEntity (id, name, numTarjeta, tipoTarjeta) values (2, 'Andrés', 11, 'Master Card');
insert into ClienteEntity (id, name, numTarjeta, tipoTarjeta) values (3, 'Juan', 12, 'Visa');
insert into ClienteEntity (id, name, numTarjeta, tipoTarjeta) values (4, 'Daniel', 13, 'Master Card');
insert into ClienteEntity (id, name, numTarjeta, tipoTarjeta) values (5, 'Maria', 14, 'Visa');

insert into ComentarioEntity (id, name) values (1, 'Comentario1');
insert into ComentarioEntity (id, name, obra_id) values (2, 'Comentario2',2);
insert into ComentarioEntity (id, name, obra_id) values (3, 'Comentario3',3);
insert into ComentarioEntity (id, name, obra_id) values (4, 'Comentario4',4);
insert into ComentarioEntity (id, name, obra_id) values (5, 'Comentario5',5);



