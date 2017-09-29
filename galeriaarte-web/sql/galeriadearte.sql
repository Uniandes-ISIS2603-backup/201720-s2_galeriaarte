
delete from CompraEntity;
delete from PagoEntity;
delete from ArtistaEntity;

insert into CompraEntity (id, valor, fecha) values (1, 20, '01/02/2001');
insert into CompraEntity (id, valor, fecha) values (2, 30, '08/06/2009');
insert into CompraEntity (id, valor, fecha) values (3, 40, '07/09/2007');
 
insert into PagoEntity (id, total, impuesto) values (25, 20, 6);
insert into PagoEntity (id, total, impuesto) values (26, 30, 7);
insert into PagoEntity (id, total, impuesto) values (27, 40, 8);

insert into ArtistaEntity (id, name) values (1, 'Pedro');
insert into ArtistaEntity (id, name) values (2, 'Pablo');
insert into ArtistaEntity (id, name) values (3, 'pepe');