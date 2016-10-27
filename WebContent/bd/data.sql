INSERT INTO `reclamosbd`.`permisos`(`idPermiso`,`permiso`,`detalle`,`estado`) VALUES
 (1,'ROLE_USER','Usuario generico del sistema',1),
 (2,'ROLE_ADMIN','Administrador del sistema',1),
 (3,'ROLE_RECLAMO','Ver modulode reclamo',1),
 (4,'ROLE_RECLAMO_R','Registrar Reclamos',1),
 (5,'ROLE_FIDELIZA','Ver modulo de fidelizar',1),
 (6,'ROLE_FIDELIZA_R','Registro de Fidelizar',1);

INSERT INTO `reclamosbd`.`perfil`(`idPerfil`, `perfil`,`estado`) VALUES 
 (1,'Administrador',1),
 (2,'Reclamos',1),
 (3,'Consulta',1);
 
INSERT INTO `reclamosbd`.`permisos_perfil`(`idPermiso`,`idPerfil`,`estado`)VALUES 
 (1,1,1),
 (2,1,1),
 (3,1,1),
 (4,1,1),
 (5,1,1),
 (6,1,1),
 (1,2,1),
 (3,2,1),
 (4,2,1),
 (5,2,1), 
 (6,2,1), 
 (1,3,1),
 (3,3,1),
 (5,3,1);

INSERT INTO `reclamosbd`.`persona`(`idPersona`,`numero_documento`,`direccion`,`ubigeo`,`telefono1`,`telefono2`,`email`,`estado`,`created_at`,`updated_at`)VALUES 
(1,'40004001','Av. Principal 100','020109','4170202',NULL,'email1@gmail.com',1, NOW(),NULL),
(2,'40004002','Av. Principal 200','020109','4170202',NULL,'email2@gmail.com',1, NOW(),NULL),
(3,'40004003','Av. Principal 300','020109','4170202',NULL,'email3@gmail.com',1, NOW(),NULL),
(4,'40004004','Av. Principal 400','020109','4170202',NULL,'email4@gmail.com',1, NOW(),NULL),
(5,'40004005','Av. Principal 500','020109','4170202',NULL,'email5@gmail.com',1, NOW(),NULL),
(6,'40004006','Av. Principal 600','020109','4170202',NULL,'email6@gmail.com',1, NOW(),NULL),
(7,'40004007','Av. Principal 700','020109','4170202',NULL,'email7@gmail.com',1, NOW(),NULL),
(8,'40004008','Av. Principal 800','020109','4170202',NULL,'email8@gmail.com',1, NOW(),NULL),
(9,'40004009','Av. Principal 900','020109','4170202',NULL,'email9@gmail.com',1, NOW(),NULL),
(10,'40004010','Av. Principal 1100','020109','4170202',NULL,'email10@gmail.com',1, NOW(),NULL);

INSERT INTO `reclamosbd`.`promocion`(`idPromocion`,`descripcion`,`estado`,`created_at`,`updated_at`) VALUES 
(1,'Capacitacion',1,NOW(),NULL),
(2,'Descuento del 50%',1,NOW(),NULL);

INSERT INTO `reclamosbd`.`proveedor`(`idProveedor`,`razonSocial`,`numero_documento`,`representante`,`direccion`,`telefono1`,`email`,`ubigeo`,`tipoCertiProveedor`,`estado`,`created_at`,`updated_at`) VALUES 
(1,'Empresa 1 SAC','20450044401','Juan Perez','Calle Lima # 100','3966698','empresa1@gmail.com','020504',1,1,NOW(),NULL),
(2,'Empresa 2 SAC','20450044402','Juan Perez','Calle Lima # 100','3966698','empresa2@gmail.com','020504',1,1,NOW(),NULL),
(3,'Empresa 3 SAC','20450044403','Juan Perez','Calle Lima # 100','3966698','empresa3@gmail.com','020504',1,1,NOW(),NULL),
(4,'Empresa 4 SAC','20450044404','Juan Perez','Calle Lima # 100','3966698','empresa4@gmail.com','020504',1,1,NOW(),NULL),
(5,'Empresa 5 SAC','20450044405','Juan Perez','Calle Lima # 100','3966698','empresa5@gmail.com','020504',1,1,NOW(),NULL),
(6,'Empresa 6 SAC','20450044406','Juan Perez','Calle Lima # 100','3966698','empresa6@gmail.com','020504',1,1,NOW(),NULL),
(7,'Empresa 7 SAC','20450044407','Juan Perez','Calle Lima # 100','3966698','empresa7@gmail.com','020504',1,1,NOW(),NULL),
(8,'Empresa 8 SAC','20450044408','Juan Perez','Calle Lima # 100','3966698','empresa8@gmail.com','020504',1,1,NOW(),NULL),
(9,'Empresa 9 SAC','20450044409','Juan Perez','Calle Lima # 100','3966698','empresa9@gmail.com','020504',1,1,NOW(),NULL),
(10,'Empresa 10 SAC','20450044410','Juan Perez','Calle Lima # 100','3966698','empresa10@gmail.com','020504',1,1,NOW(),NULL);

INSERT INTO `reclamosbd`.`cliente`(`idCliente`,`idPersona`,`nomCliente`,`fecCliente`,`rucCliente`,`estado`) VALUES 
(1,1,'Carlos Vera','2015-05-05','1020287020125',1),
(2,2,'Juan Vera','2015-05-05','1020287020136',1),
(3,3,'Pedro Vera','2015-05-05','1020287020127',1),
(4,4,'Samuel Vera','2015-05-05','1020287020128',1),
(5,5,'Matias Vera','2015-05-05','1020287020129',1),
(6,6,'Esteban Vera','2015-05-05','1020287020140',1),
(7,7,'Luis Vera','2015-05-05','1020287020141',1),
(8,8,'Manuel Vera','2015-05-05','1020287020142',1),
(9,9,'Antonio Vera','2015-05-05','1020287020143',1),
(10,10,'Carmen Vera','2015-05-05','1020287020144',1);

INSERT INTO `reclamosbd`.`factura`(`idFactura`,`idProveedor`,`idCliente`,`emision`,`numero`,`monto`,`estado`,`created_at`,`updated_at`) VALUES 
(1,NULL,1,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(2,NULL,1,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(3,NULL,6,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(4,NULL,2,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(5,NULL,2,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(6,NULL,7,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(7,NULL,3,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(8,NULL,3,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(9,NULL,3,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(10,NULL,3,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(11,NULL,3,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(12,NULL,8,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(13,NULL,4,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(14,NULL,4,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(15,NULL,4,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(16,NULL,4,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(17,NULL,9,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(18,NULL,5,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(19,NULL,5,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(20,NULL,5,'2016-10-01','001-1000','2500',1,NOW(),NULL);

INSERT INTO `reclamosbd`.`producto`(`idProducto`,`descripcion`,`fecProducto`,`direProducto`,`skuProducto`,`pesoProducto`,`tipoProducto`,`estado`,`created_at`,`updated_at`) VALUES 
(1,'Televisor 1','2016-10-01','--','10000001','1',1,1,NOW(),NULL),
(2,'Mouse 2','2016-10-01','--','10000002','1',1,1,NOW(),NULL),
(3,'Reloj 3','2016-10-01','--','10000003','1',1,1,NOW(),NULL),
(4,'Tablt AOC 4','2016-10-01','--','10000004','1',1,1,NOW(),NULL),
(5,'Celular5','2016-10-01','--','10000005','1',1,1,NOW(),NULL),
(6,'Licuadora 6','2016-10-01','--','10000006','1',1,1,NOW(),NULL),
(7,'Arroecera 7','2016-10-01','--','10000007','1',1,1,NOW(),NULL),
(8,'Hervidor 8','2016-10-01','--','10000008','1',1,1,NOW(),NULL),
(9,'Lustradora 9','2016-10-01','--','10000009','1',1,1,NOW(),NULL),
(10,'Laptop 10','2016-10-01','--','10000010','1',1,1,NOW(),NULL),
(11,'Telefono 11','2016-10-01','--','10000011','1',1,1,NOW(),NULL),
(12,'Monitor 12','2016-10-01','--','10000012','1',1,1,NOW(),NULL),
(13,'Teclado 13','2016-10-01','--','10000013','1',1,1,NOW(),NULL),
(14,'Ipad 14','2016-10-01','--','10000014','1',1,1,NOW(),NULL),
(15,'MP3 15','2016-10-01','--','10000015','1',1,1,NOW(),NULL);


INSERT INTO `reclamosbd`.`detallefactura`(`idDetalleFactura`,`idProducto`,`idFactura`,`cantidad`,`precio`,`estado`,`created_at`,`updated_at`) VALUES 
(1,1,1,'1','100',1,NOW(), NULL),
(2,1,1,'1','100',1,NOW(), NULL),
(3,1,2,'1','100',1,NOW(), NULL),
(4,1,2,'1','100',1,NOW(), NULL),
(5,1,3,'1','100',1,NOW(), NULL),
(6,1,3,'1','100',1,NOW(), NULL),
(7,1,4,'1','100',1,NOW(), NULL),
(8,1,4,'1','100',1,NOW(), NULL),
(9,1,5,'1','100',1,NOW(), NULL),
(10,1,5,'1','100',1,NOW(), NULL),
(11,1,6,'1','100',1,NOW(), NULL),
(12,1,6,'1','100',1,NOW(), NULL),
(13,1,7,'1','100',1,NOW(), NULL),
(14,1,7,'1','100',1,NOW(), NULL),
(15,1,8,'1','100',1,NOW(), NULL),
(16,1,8,'1','100',1,NOW(), NULL),
(17,1,9,'1','100',1,NOW(), NULL),
(18,1,9,'1','100',1,NOW(), NULL),
(19,1,10,'1','100',1,NOW(), NULL),
(20,1,10,'1','100',1,NOW(), NULL),
(21,1,11,'1','100',1,NOW(), NULL),
(22,1,11,'1','100',1,NOW(), NULL),
(23,1,12,'1','100',1,NOW(), NULL),
(24,1,12,'1','100',1,NOW(), NULL),
(25,1,13,'1','100',1,NOW(), NULL),
(26,1,13,'1','100',1,NOW(), NULL),
(27,1,14,'1','100',1,NOW(), NULL),
(28,1,14,'1','100',1,NOW(), NULL),
(29,1,15,'1','100',1,NOW(), NULL),
(30,1,15,'1','100',1,NOW(), NULL),
(31,1,16,'1','100',1,NOW(), NULL),
(32,1,17,'1','100',1,NOW(), NULL),
(33,1,17,'1','100',1,NOW(), NULL),
(34,1,18,'1','100',1,NOW(), NULL),
(35,1,19,'1','100',1,NOW(), NULL),
(36,1,20,'1','100',1,NOW(), NULL);

