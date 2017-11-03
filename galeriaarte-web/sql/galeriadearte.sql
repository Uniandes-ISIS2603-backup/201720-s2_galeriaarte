
delete from ClienteEntity;
ALTER TABLE ClienteEntity ALTER COLUMN id RESTART WITH 1;
delete from CompraEntity;
ALTER TABLE CompraEntity ALTER COLUMN id RESTART WITH 1;
delete from PagoEntity;
ALTER TABLE PagoEntity ALTER COLUMN id RESTART WITH 1;
delete from ArtistaEntity;
ALTER TABLE ArtistaEntity ALTER COLUMN id RESTART WITH 1;
delete from CatalogoEntity;
ALTER TABLE CatalogoEntity ALTER COLUMN id RESTART WITH 1;
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
delete from GaleriaEntity;
ALTER TABLE GaleriaEntity ALTER COLUMN id RESTART WITH 1;

insert into CompraEntity (valor, fecha) values ( 20, '01/02/2001');
insert into CompraEntity (valor, fecha) values ( 30, '08/06/2009');
insert into CompraEntity (valor, fecha) values ( 40, '07/09/2007');
 
insert into PagoEntity ( total, impuesto) values ( 20, 6);
insert into PagoEntity ( total, impuesto) values ( 30, 7);
insert into PagoEntity ( total, impuesto) values ( 40, 8);

insert into ArtistaEntity ( name) values ( 'Pedro');
insert into ArtistaEntity ( name) values ( 'Pablo');
insert into ArtistaEntity ( name) values ( 'Pepe');
insert into ArtistaEntity ( name) values ( 'Juan');
insert into ArtistaEntity ( name) values ( 'Carlos');

insert into GaleriaEntity ( nombre, direccion, telefono) values ( 'GaleriaKADDA','Cra. 1 #18a-12, BogotÃ¡',3394949);

insert into CatalogoEntity (id,categoria) values (5, 'contemporÃ¡neo');
insert into CatalogoEntity (id,categoria) values (6, 'greco-romano');
insert into CatalogoEntity (id,categoria) values (7, 'vanguardia');

insert into ObraEntity ( cantidad, name, tipo, valor, artista_id) values ( 5, 'Las meninas', 'Pintura', 20000,1);
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id) values ( 4, 'La últimma', 'Pintura', 30000,2);
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id) values ( 3, 'La noche estrellada', 'Pintura', 40000,3);
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id) values ( 2, 'El nacimiento de Venus', 'Pintura', 50000,4);
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id) values ( 1, 'La creación de Adán', 'Pintura', 60000,5);

insert into HojaVidaEntity ( almamater, nacionalidad, name, trayectoria, artista_id) values ( 'Universidad de los Andes', 'Colombiano', 'Kelvin', 'Recibí el premio Mejor Artista 2010',2);
insert into HojaVidaEntity ( almamater, nacionalidad, name, trayectoria, artista_id) values ( 'Universidad del Rosario', 'Venezolano', 'Juan Daniel', 'Recibí el premio Mejor Artista 2011',3);
insert into HojaVidaEntity ( almamater, nacionalidad, name, trayectoria, artista_id) values ( 'Universidad Nacional', 'Argentino', 'Daniel', 'Recibí el premio Mejor Artista 2012',4);
insert into HojaVidaEntity ( almamater, nacionalidad, name, trayectoria, artista_id) values ( 'Universidad de los Andes', 'Colombiana', 'Maria Alejandra', 'Recibí el premio Mejor Artista 2013',5);
insert into HojaVidaEntity ( almamater, nacionalidad, name, trayectoria, artista_id) values ( 'Universidad Distrital', 'Chileno', 'Andrés', 'Recibí el premio Mejor Artista 2014',1);


insert into MarcoEntity ( alto, ancho, material, name, valor,image) values ( 1, 1.5, 'Madera','Marco de madera', 80000,'http://img.aws.ehowcdn.com/intl-620/ds-photo/110/42/fotolia_973974_XS.jpg');
insert into MarcoEntity ( alto, ancho, material, name, valor,image) values ( 0.5, 0.6, 'Oro','Marco de oro', 150000,'https://static.vix.com/es/sites/default/files/styles/large/public/imj/hogartotal/M/Marcos-para-cuadros-3.jpg?itok=OPXFS6kq');
insert into MarcoEntity ( alto, ancho, material, name, valor,image) values ( 2, 2.5, 'Plastico','Marco de colores', 35000,'https://www.moldurashergon.es/img/blog/molduras-coloridas-520-748.jpg');
insert into MarcoEntity ( alto, ancho, material, name, valor,image) values ( 1.5, 1.5, 'Yeso','Marco grabado', 95000,'https://www.moldurashergon.es/img/blog/molduras-grabadas-635-U76.jpg');
insert into MarcoEntity ( alto, ancho, material, name, valor,image) values ( 3, 2.5, 'Metal','Marco de metal', 95000,'https://www.moldurashergon.es/img/blog/molduras-plata-025-900.jpg');

insert into BlogEntity ( contenido, name, artista_id ) values ('Contenido1', 'Como entender el arte abstracta',1);
insert into BlogEntity ( contenido, name, artista_id ) values ('Contenido2', 'Como aprender a pintar',2);
insert into BlogEntity ( contenido, name, artista_id ) values ('Contenido3', 'Aprendiendo analizar el arte',3);
insert into BlogEntity ( contenido, name, artista_id ) values ('Contenido4', 'El arte de las esculturas',4);
insert into BlogEntity ( contenido, name, artista_id ) values ('Contenido5', 'Las esculturas vs las obras de arte',5);


insert into ClienteEntity ( name, numTarjeta, tipoTarjeta) values ( 'Kelvin', 10, 'Visa');
insert into ClienteEntity ( name, numTarjeta, tipoTarjeta) values ( 'Andrés', 11, 'Master Card');
insert into ClienteEntity ( name, numTarjeta, tipoTarjeta) values ( 'Juan', 12, 'Visa');
insert into ClienteEntity ( name, numTarjeta, tipoTarjeta) values ( 'Daniel', 13, 'Master Card');
insert into ClienteEntity ( name, numTarjeta, tipoTarjeta) values ( 'Maria', 14, 'Visa');

insert into ComentarioEntity ( name) values ( 'Comentario1');
insert into ComentarioEntity ( name, obra_id) values ( 'Comentario2',2);
insert into ComentarioEntity ( name, obra_id) values ( 'Comentario3',3);
insert into ComentarioEntity ( name, obra_id) values ( 'Comentario4',4);
insert into ComentarioEntity ( name, obra_id) values ( 'Comentario5',5);





