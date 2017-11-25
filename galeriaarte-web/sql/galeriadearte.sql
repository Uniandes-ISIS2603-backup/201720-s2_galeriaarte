    
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

insert into ArtistaEntity ( name, imagen) values ( 'Pedro', 'https://upload.wikimedia.org/wikipedia/commons/d/d0/Pedro_rodriguez.JPG');
insert into ArtistaEntity ( name, imagen) values ( 'Pablo','https://images.pagina12.com.ar/styles/width700/public/2016-11/Jos%C3%A9-Pablo-Feinmann.png?itok=GN_Vn-5I');
insert into ArtistaEntity ( name, imagen) values ( 'Pepe', 'http://images.coveralia.com/autores/fotos/pablo-lopez77104.jpg');
insert into ArtistaEntity ( name, imagen) values ( 'Juan', 'https://news.efinancialcareers.com/wp-content/uploads/2014/02/Sartre.jpg');
insert into ArtistaEntity ( name, imagen) values ( 'Carlos', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Pablo_Larra%C3%ADn_%2830116382680%29_%28cropped%29.jpg/220px-Pablo_Larra%C3%ADn_%2830116382680%29_%28cropped%29.jpg');

insert into GaleriaEntity ( nombre, direccion, telefono) values ( 'GaleriaKADDA','Cra. 1 #18a-12, BogotÃ¡',3394949);

insert into CatalogoEntity (id,categoria) values (5, 'contemporÃ¡neo');
insert into CatalogoEntity (id,categoria) values (6, 'greco-romano');
insert into CatalogoEntity (id,categoria) values (7, 'vanguardia');

insert into ObraEntity ( cantidad, name, tipo, valor, artista_id, imagen) values ( 5, 'Las meninas', 'Pintura', 20000,1,'https://content3.cdnprado.net/imagenes/Documentos/imgsem/68/6871/68718fb0-d062-4db4-bf25-7af5824eebac/d44c40de-9d5b-4280-a096-9f63b116dcec.jpg');
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id, imagen) values ( 4, 'La últimma', 'Pintura', 30000,2,'https://cloud10.todocoleccion.online/arte-religioso/tc/2015/05/14/19/49342258.jpg');
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id, imagen) values ( 3, 'La noche estrellada', 'Pintura', 40000,3,'http://listas.eleconomista.es/system/lists/000/010/640/medium/listas-economista-van-portada.jpg?1467835341');
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id, imagen) values ( 2, 'El nacimiento de Venus', 'Pintura', 50000,4,'https://i0.wp.com/www.marisolroman.com/wp-content/uploads/2012/02/the-birth-of-venus-14851large-1.jpg?fit=750%2C500');
insert into ObraEntity ( cantidad, name, tipo, valor, artista_id, imagen) values ( 1, 'La creación de Adán', 'Pintura', 60000,5,'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Creation_of_Adam.jpg/1200px-Creation_of_Adam.jpg');

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





