
INSERT INTO `reclamosbd`.`perfil`(`idPerfil`, `perfil`,`estado`) VALUES 
 (1,'Administrador',1),
 (2,'Reclamos',1),
 (3,'Consulta',1),
 (4,'Jefe Supervisor',1);
 
INSERT INTO `reclamosbd`.`permisos`(`idPermiso`,`permiso`,`detalle`,`estado`) VALUES
 (1,'ROLE_USER','Usuario generico del sistema',1),
 (2,'ROLE_ADMIN','Administrador del sistema',1),
 (3,'ROLE_RECLAMO','Ver modulo de reclamo',1),
 (4,'ROLE_RECLAMO_R','Registrar Reclamos',1),
 (5,'ROLE_FIDELIZA','Ver modulo de fidelizar',1),
 (6,'ROLE_FIDELIZA_R','Registro de Fidelizar',1),
 (7,'ROLE_CAPACITA','Modulo de capacitaciones',1),
 (8,'ROLE_PRODUCTO','Modulo de cambio y devolucion de productos',1),
 (9,'ROLE_SEGUIMIENTO','Modulo de seguimiento dereclamos',1),
 (10,'ROLE_CLIENTES','Modulo de Clientes, solo consulta',1),
 (11,'ROLE_CLIENTES_R','Modulo de Clientes, opcion para registrar',1);

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

INSERT INTO `reclamosbd`.`persona`(`idPersona`,`nombres`,`ape_paterno`,`numero_documento`,`direccion`,`ubigeo`,`telefono1`,`telefono2`,`email`,`estado`,`created_at`,`updated_at`)VALUES 
(1,'Carlos','Vera','40004001','Av. Principal 100','020109','4170202',NULL,'dverat@gmail.com',1, NOW(),NULL),
(2,'Elvis','Campos','40004002','Av. Principal 200','020109','4170202',NULL,'lekvispk@gmail.com',1, NOW(),NULL),
(3,'pedro','aquijano','40004003','Av. Principal 300','020109','4170202',NULL,'email3@gmail.com',1, NOW(),NULL),
(4,'marta','quino','40004004','Av. Principal 400','020109','4170202',NULL,'email4@gmail.com',1, NOW(),NULL),
(5,'lourdes','malpartida','40004005','Av. Principal 500','020109','4170202',NULL,'email5@gmail.com',1, NOW(),NULL),
(6,'sofia','salgado','40004006','Av. Principal 600','020109','4170202',NULL,'email6@gmail.com',1, NOW(),NULL),
(7,'lucia','tarazona','40004007','Av. Principal 700','020109','4170202',NULL,'email7@gmail.com',1, NOW(),NULL),
(8,'maritza','mori','40004008','Av. Principal 800','020109','4170202',NULL,'email8@gmail.com',1, NOW(),NULL),
(9,'clara','almeyda','40004009','Av. Principal 900','020109','4170202',NULL,'email9@gmail.com',1, NOW(),NULL),
(10,'karen','risco','40004010','Av. Principal 1100','020109','4170202',NULL,'email10@gmail.com',1, NOW(),NULL);

INSERT INTO `reclamosbd`.`usuario`(`idUsuario`,`idPerfil`,`idPersona`,`username`,`password`,`password_caduca`,`email`,`estado`,`created_at`,`updated_at`,`deleted_at`) VALUES 
(1,1,1,'dverat','e10adc3949ba59abbe56e057f20f883e',NULL,'dverat@gmail.com',1,NOW(),NULL,NULL),
(2,1,1,'lekvispk','e10adc3949ba59abbe56e057f20f883e',NULL,'lekvispk@gmail.com',1,NOW(),NULL,NULL);

INSERT INTO `reclamosbd`.`authorities`(`id`,`username`,`authority`) VALUES 
(1,'dverat','ROLE_USER'),
(2,'dverat','ROLE_ADMIN'),
(3,'dverat','ROLE_RECLAMO'),
(4,'dverat','ROLE_FIDELIZA'),
(5,'lekvispk','ROLE_USER'),
(6,'lekvispk','ROLE_ADMIN'),
(7,'lekvispk','ROLE_RECLAMO'),
(8,'lekvispk','ROLE_FIDELIZA');

INSERT INTO `reclamosbd`.`capacitador` (`idCapacitador`, `codigo`, `nombre`, `apellidos`, `estado`, `created_at`, `updated_at`) VALUES 
 (1,'CAP001', 'Juan', 'Perez', 1, NOW(), NULL),
 (2,'CAP002', 'Luis', 'Rosas', 1, NOW(), NULL),
 (3,'CAP003', 'Alberto', 'Salgado', 1, NOW(), NULL),
 (4,'CAP004', 'Carlos', 'Sanchez', 1, NOW(), NULL),
 (5,'CAP005', 'Pedro', 'Salinas', 1, NOW(), NULL),
 (6,'CAP006', 'xavi', 'Weiland', 1, NOW(), NULL);
 
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

INSERT INTO `reclamosbd`.`cliente`(`idCliente`,`idPersona`,`nomCliente`,`representante`,`fecCliente`,`rucCliente`,`estado`) VALUES 
(1,1,'Carlos Vera','enrique cordero','2015-05-05','20287020125',1),
(2,2,'Juan Vera','Pedro Saul Aquijano','2015-05-05','20287020136',1),
(3,3,'Elvis Java','Luis Carlos Garcia','2015-05-05','20287020127',1),
(4,4,'Samuel Vera','Lucia Montes','2015-05-05','20287020128',1),
(5,5,'Matias Vera','Sara Cabrera','2015-05-05','10287020129',1),
(6,6,'Wilber Rojas','Melanie Muñoz','2015-05-05','10287020140',1),
(7,7,'Luis Vera','Carloz zambrano','2015-05-05','10287020141',1),
(8,8,'Xavi Zorrain','Pedro Quiroz','2015-05-05','10287020142',1),
(9,9,'Antonio Vera','Alicia Salgado','2015-05-05','10287020143',1),
(10,10,'Carmen Vera','Juan Perez','2015-05-05','1020287020144',1);

INSERT INTO `reclamosbd`.`factura`(`idFactura`,`idProveedor`,`idCliente`,`emision`,`numero`,`monto`,`estado`,`created_at`,`updated_at`) VALUES 
(1,NULL,1,'2016-10-01','001-1000','2500',1,NOW(),NULL),
(2,NULL,1,'2016-10-01','001-1001','2500',1,NOW(),NULL),
(3,NULL,6,'2016-10-01','001-1002','2500',1,NOW(),NULL),
(4,NULL,2,'2016-10-01','001-1003','2500',1,NOW(),NULL),
(5,NULL,2,'2016-10-01','001-1004','2500',1,NOW(),NULL),
(6,NULL,7,'2016-10-01','001-1005','2500',1,NOW(),NULL),
(7,NULL,3,'2016-10-01','001-1006','2500',1,NOW(),NULL),
(8,NULL,3,'2016-10-01','001-1007','2500',1,NOW(),NULL),
(9,NULL,3,'2016-10-01','001-1008','2500',1,NOW(),NULL),
(10,NULL,3,'2016-10-01','001-1009','2500',1,NOW(),NULL),
(11,NULL,3,'2016-10-01','001-1010','2500',1,NOW(),NULL),
(12,NULL,8,'2016-10-01','001-101','2500',1,NOW(),NULL),
(13,NULL,4,'2016-10-01','001-1012','2500',1,NOW(),NULL),
(14,NULL,4,'2016-10-01','001-1013','2500',1,NOW(),NULL),
(15,NULL,4,'2016-10-01','001-1014','2500',1,NOW(),NULL),
(16,NULL,4,'2016-10-01','001-1015','2500',1,NOW(),NULL),
(17,NULL,9,'2016-10-01','001-1016','2500',1,NOW(),NULL),
(18,NULL,5,'2016-10-01','001-1017','2500',1,NOW(),NULL),
(19,NULL,5,'2016-10-01','001-1018','2500',1,NOW(),NULL),
(20,NULL,5,'2016-10-01','001-1019','2500',1,NOW(),NULL),
(21,NULL,6,'2016-10-01','001-1011','2500',1,NOW(),NULL),
(22,NULL,6,'2016-10-01','001-1012','2500',1,NOW(),NULL);


INSERT INTO `reclamosbd`.`producto`(`idProducto`,`descripcion`,`fecProducto`,`direProducto`,`skuProducto`,`precio`,`pesoProducto`,`tipoProducto`,`estado`,`created_at`,`updated_at`) VALUES 
(1,'Televisor 1','2016-10-01','--','10000001','20','1',1,1,NOW(),NULL),
(2,'Mouse 2','2016-10-01','--','10000002','20','1',1,1,NOW(),NULL),
(3,'Reloj 3','2016-10-01','--','10000003','20','1',1,1,NOW(),NULL),
(4,'Tablt AOC 4','2016-10-01','--','10000004','20','1',1,1,NOW(),NULL),
(5,'Celular5','2016-10-01','--','10000005','20','1',1,1,NOW(),NULL),
(6,'Licuadora 6','2016-10-01','--','10000006','20','1',1,1,NOW(),NULL),
(7,'Arroecera 7','2016-10-01','--','10000007','20','1',1,1,NOW(),NULL),
(8,'Hervidor 8','2016-10-01','--','10000008','20','1',1,1,NOW(),NULL),
(9,'Lustradora 9','2016-10-01','--','10000009','20','1',1,1,NOW(),NULL),
(10,'Laptop 10','2016-10-01','--','10000010','20','1',1,1,NOW(),NULL),
(11,'Telefono 11','2016-10-01','--','10000011','20','1',1,1,NOW(),NULL),
(12,'Monitor 12','2016-10-01','--','10000012','20','1',1,1,NOW(),NULL),
(13,'Teclado 13','2016-10-01','--','10000013','20','1',1,1,NOW(),NULL),
(14,'Ipad 14','2016-10-01','--','10000014','20','1',1,1,NOW(),NULL),
(15,'MP3 15','2016-10-01','--','10000015','20','1',1,1,NOW(),NULL);


INSERT INTO `reclamosbd`.`detallefactura`(`idDetalleFactura`,`idProducto`,`idFactura`,`cantidad`,`precio`,`estado`,`created_at`,`updated_at`) VALUES 
(1,1,1,'1','100',1,NOW(), NULL),
(2,2,1,'1','100',1,NOW(), NULL),
(3,3,2,'1','100',1,NOW(), NULL),
(4,4,2,'1','100',1,NOW(), NULL),
(5,5,3,'1','100',1,NOW(), NULL),
(6,6,3,'1','100',1,NOW(), NULL),
(7,7,4,'1','100',1,NOW(), NULL),
(8,8,4,'1','100',1,NOW(), NULL),
(9,9,5,'1','100',1,NOW(), NULL),
(10,10,5,'1','100',1,NOW(), NULL),
(11,11,6,'1','100',1,NOW(), NULL),
(12,12,6,'1','100',1,NOW(), NULL),
(13,13,7,'1','100',1,NOW(), NULL),
(14,14,7,'1','100',1,NOW(), NULL),
(15,15,8,'1','100',1,NOW(), NULL),
(16,1,8,'1','100',1,NOW(), NULL),
(17,2,9,'1','100',1,NOW(), NULL),
(18,3,9,'1','100',1,NOW(), NULL),
(19,4,10,'1','100',1,NOW(), NULL),
(20,5,10,'1','100',1,NOW(), NULL),
(21,6,11,'1','100',1,NOW(), NULL),
(22,7,11,'1','100',1,NOW(), NULL),
(23,8,12,'1','100',1,NOW(), NULL),
(24,9,12,'1','100',1,NOW(), NULL),
(25,10,13,'1','100',1,NOW(), NULL),
(26,11,13,'1','100',1,NOW(), NULL),
(27,12,14,'1','100',1,NOW(), NULL),
(28,13,14,'1','100',1,NOW(), NULL),
(29,14,15,'1','100',1,NOW(), NULL),
(30,15,15,'1','100',1,NOW(), NULL),
(31,1,16,'1','100',1,NOW(), NULL),
(32,2,17,'1','100',1,NOW(), NULL),
(33,3,17,'1','100',1,NOW(), NULL),
(34,4,18,'1','100',1,NOW(), NULL),
(35,5,19,'1','100',1,NOW(), NULL),
(36,6,20,'1','100',1,NOW(), NULL);


-- demo de insert detalle Factura

INSERT INTO `reclamosbd`.`detallefactura`(`idDetalleFactura`,`idProducto`,`idFactura`,`cantidad`,`precio`,`estado`,`created_at`,`updated_at`) VALUES 
(37,6,20,'3','180',1,NOW(), NULL),
(38,6,21,'4','200',1,NOW(), NULL),
(39,5,20,'4','200',1,NOW(), NULL),
(40,7,20,'3','150',1,NOW(), NULL);


INSERT INTO `reclamosbd`.`despachador` (`idDespachador`, `codigo`, `nombres`, `apellidos`, `estado`, `created_at` ) VALUES 
(1,'D001', 'Juan', 'Perez', 1, NOW()),
(2,'D002', 'Luis', 'Salgado', 1, NOW()),
(3,'D003', 'Enrique', 'Escobar', 1, NOW()),
(4,'D004', 'Raquel', 'Tarazona', 1, NOW()),
(5,'D005', 'Javier', 'Vilca', 1, NOW()),
(6,'D006', 'Pedro', 'Chavez', 1, NOW()),
(7,'D007', 'Antonio', 'Solis', 1, NOW());



INSERT INTO `reclamosbd`.`capacitacion` (`idCapacitacion`,`idCapacitador`, `idFactura`, `fecha_capacitacion`, `hora_capacitacion`, `motivo_pospuesto`, `estado`, `created_at`, `updated_at`) VALUES 
 (2, 1, 1, '2017-06-01', '09:00', NULL, 1, NOW(), NULL), 
 (3, 1, 1, '2017-06-02', '09:00', NULL, 1, NOW(), NULL),
 (4, 2, 2, '2017-06-03', '09:10', NULL, 1, NOW(), NULL),
 (5, 2, 2, '2017-06-04', '09:10', NULL, 1, NOW(), NULL),
 (6, 3, 3, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (7, 3, 3, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (8, 4, 4, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (9, 4, 4, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (10, 5, 5, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (11, 5, 5, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (12, 1, 6, '2017-06-05', '09:20', NULL, 1, NOW(), NULL),
 (13, 1, 6, '2017-06-05', '09:20', NULL, 1, NOW(), NULL);


 insert into `reclamosbd`.`items_reclamo` (`idItemReclamo`,`idDetalleFactura`,`idReclamo`,`Estado`) values
 (1,1,2,1),
 (2,7,3,1),
 (3,9,4,1),
 (4,5,5,1),
 (5,11,6,1),
 (6,6,6,1),
 (7,7,7,1),
 (8,21,8,1),
 (9,3,2,1), 
 (10,1,1,1),
 (11,38,8,1),
 (12,2,2,1), 
 (14,4,3,1),
 
 
-- FORMATO DE FECHA PARA INSERT MySQL : yyyy-mm-dd
-- Validar inserts

INSERT INTO `reclamosbd`.`reclamo` (`idReclamo`,`idFactura`,`idCliente`,`descripcion`,`fecReclamo`,`tipoReclamo`,`asunto`,`mensaje`, `solucion`, `respuesta`, `fecRespuesta`, `prioridad`, `vencimiento`, `indemnizar`, `fidelizado`, `estado`, `created_at`, `updated_at`) VALUES  
(9, 1, 2, NULL, '2017-05-01', NULL, 'linterna fallo', 'linternas no funciona', NULL, NULL, '2017-15-01', 2, '2017-25-01', NULL, NULL, 1, NOW(), NULL),
(10, 1, 2, NULL, '2017-06-01', NULL, 'señales seguridad', 'señales no funciona pegapega', NULL, NULL, '2017-16-01', 2, '2017-26-01', NULL, NULL, 1, NOW(), NULL),
(11, 2, 3, NULL, '2017-07-01', NULL, 'termos cambiados', 'no son termos metalicos, son plastico', NULL, NULL, '2017-17-01', 2, '2017-27-01', NULL, NULL, 1, NOW(), NULL),
(12, 2, 3, NULL, '2017-08-01', NULL, 'botas extranas', 'se pidio 1lote llego incompleto', NULL, NULL, '2017-18-01', 2, '2017-28-01', NULL, NULL, 1, NOW(), NULL),
(13, 1, 2, NULL, '2017-09-01', NULL, 'auriculares error', 'se pidio auriculares antiruido para obras', NULL, NULL, '2017-19-01', 2, '2017-29-01', NULL, NULL, 1, NOW(), NULL),
(14, 1, 2, NULL, '2017-10-01', NULL, 'mouse inalambrico', 'no funciona sobre superficies', NULL, NULL, '2017-15-01', 2, '2017-25-01', NULL, NULL, 1, NOW(), NULL),
(15, 2, 3, NULL, '2017-11-01', NULL, 'herbidor error', 'herbido se quemo el motor', NULL, NULL, '2017-16-01', 2, '2017-26-01', NULL, NULL, 1, NOW(), NULL),

-- reclamos mes Febrero 2017
(16, 2, 3, NULL, '2017-01-02', NULL, 'cargador movil', 'cargador es otro tipo input al pedido', NULL, NULL, '2017-10-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(17, 2, 3, NULL, '2017-02-02', NULL, 'porta mapas', 'no llego la cantidad requerida', NULL, NULL, '2017-10-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(18, 1, 2, NULL, '2017-03-02', NULL, 'bateria externa ', 'la bateria esta drenando agua', NULL, NULL, '2017-11-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(19, 2, 3, NULL, '2017-04-02', NULL, 'canaletas irq', 'algunas canaletas llegaron quebradas', NULL, NULL, '2017-12-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(20, 2, 3, NULL, '2017-05-02', NULL, 'brochas', 'llegaron brochas pequeñas se pidio medianas', NULL, NULL, '2017-13-02', 2, '2017-27-02', NULL, NULL, 1, NOW(), NULL),
(21, 2, 3, NULL, '2017-06-02', NULL, 'calaminas', 'faltan unidades de calaminas', NULL, NULL, '2017-14-01', 2, '2017-27-01', NULL, NULL, 1, NOW(), NULL),
(22, 2, 3, NULL, '2017-07-02', NULL, 'drywall', 'el material drywall es muy flexible', NULL, NULL, '2017-15-02', 2, '2017-27-02', NULL, NULL, 1, NOW(), NULL),
(23, 2, 3, NULL, '2017-08-02', NULL, 'bancos plasticos', 'faltan 5 unidades de bancos', NULL, NULL, '2017-16-02', 2, '2017-28-02', NULL, NULL, 1, NOW(), NULL),
(24, 2, 3, NULL, '2017-09-02', NULL, 'desarmadores', 'tiene otro tipo de punta qno se pidio', NULL, NULL, '2017-17-02', 2, '2017-28-02', NULL, NULL, 1, NOW(), NULL),
(25, 2, 3, NULL, '2017-10-02', NULL, 'guantes', 'se probo con corriente y pasa poco electricidad', NULL, NULL, '2017-18-02', 2, '2017-28-02', NULL, NULL, 1, NOW(), NULL),

(26, 3, 4, NULL, '2017-10-02', NULL, 'bandas seguridad', 'bandas rasgadas en el envio', NULL, NULL, '2017-14-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(27, 3, 4, NULL, '2017-11-02', NULL, 'gafas proteccion', 'lunas de gafas estan borrosas', NULL, NULL, '2017-14-02', 2, '2017-26-02', NULL, NULL, 1, NOW(), NULL),
(28, 3, 4, NULL, '2017-12-02', NULL, 'salvavidas', 'los items estan usados', NULL, NULL, '2017-15-01', 2, '2017-27-02', NULL, NULL, 1, NOW(), NULL),
(29, 3, 4, NULL, '2017-13-02', NULL, 'equipo telecom radio', 'se requirio marca motorola llegaron dolphin', NULL, NULL, '2017-15-02', 2, '2017-27-02', NULL, NULL, 1, NOW(), NULL),
(30, 3, 4, NULL, '2017-14-02', NULL, 'guantes', 'se probo con corriente y pasa poco electricidad', NULL, NULL, '2017-16-02', 2, '2017-28-02', NULL, NULL, 1, NOW(), NULL),

-- reclamos mes Marzo 2017
(31, 1, 1, NULL, '2017-01-03', NULL, 'mascarillas', 'se recibio menos unidades de las pedidas', NULL, NULL, '2017-10-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(32, 1, 1, NULL, '2017-02-03', NULL, 'impermeables', 'llegaron elementos destenidos sin logo empresa', NULL, NULL, '2017-10-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(33, 1, 1, NULL, '2017-03-03', NULL, 'extinguidor', 'los balones extinguidor pesan menos del peso ideal', NULL, NULL, '2017-10-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(34, 2, 1, NULL, '2017-04-03', NULL, 'sacos de laboratorio', 'mayoria de sacos blancos llegaron destenidos', NULL, NULL, '2017-11-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(35, 2, 1, NULL, '2017-05-03', NULL, 'mascaras antigas', 'se recibio mascaras con pequenos agujeros', NULL, NULL, '2017-13-03', 2, '2017-27-03', NULL, NULL, 1, NOW(), NULL),
(36, 2, 1, NULL, '2017-06-03', NULL, 'cascos', 'llego cascos de un solo color azul se pidio 3 colores', NULL, NULL, '2017-14-03', 2, '2017-27-03', NULL, NULL, 1, NOW(), NULL),
(37, 2, 2, NULL, '2017-07-03', NULL, 'gafas oscuras', 'se hizo pedido de 50UNI vino menos', NULL, NULL, '2017-15-03', 2, '2017-27-03', NULL, NULL, 1, NOW(), NULL),
(38, 2, 2, NULL, '2017-08-03', NULL, 'palanas para obra', 'aun no llegan el pedido demora delivery', NULL, NULL, '2017-16-03', 2, '2017-28-03', NULL, NULL, 1, NOW(), NULL),
(39, 2, 2, NULL, '2017-09-03', NULL, 'Chalecos', 'LLego el pedido mas estan con otro logo de empresa',  NULL, NULL, '2017-17-03', 2, '2017-28-03', NULL, NULL, 1, NOW(), NULL),
(40, 2, 2, NULL, '2017-10-03', NULL, 'manual de usuario', 'no se recepciono los manuales del equipo adquirido   y pasa poco electricidad', NULL, NULL, '2017-18-03', 2, '2017-28-03', NULL, NULL, 1, NOW(), NULL),
(41, 3, 4, NULL, '2017-10-03', NULL, 'bandas seguridad', 'bandas incompletas en el envio', NULL, NULL, '2017-14-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(42, 3, 4, NULL, '2017-11-03', NULL, 'balones de oxigeno', 'los items no pesan de acuerdo las especificaciones', NULL, NULL, '2017-14-03', 2, '2017-26-03', NULL, NULL, 1, NOW(), NULL),
(43, 3, 4, NULL, '2017-12-03', NULL, 'rodilleras', 'demora en el delivery del producto', NULL, NULL, '2017-15-03', 2, '2017-27-03', NULL, NULL, 1, NOW(), NULL),
(44, 3, 4, NULL, '2017-13-03', NULL, 'mascara antipolvo', 'llegaron menos unidades de las solicitadas', NULL, NULL, '2017-15-03', 2, '2017-27-03', NULL, NULL, 1, NOW(), NULL),
(45, 3, 4, NULL, '2017-14-03', NULL, 'candados', 'no llegaron las respectivas llaves de estos items', NULL, NULL, '2017-16-03', 2, '2017-28-03', NULL, NULL, 1, NOW(), NULL),

-- reclamos mes Abril 2017
(46, 4, 1, NULL, '2017-01-04', NULL, 'arneses', 'se recibio menos cantidad de arneses', NULL, NULL, '2017-14-04', 2, '2017-27-04', NULL, NULL, 1, NOW(), NULL),
(47, 4, 1, NULL, '2017-02-04', NULL, 'visor nocturno', 'los visores no son la marca q se pidio', NULL, NULL, '2017-15-04', 2, '2017-27-04', NULL, NULL, 1, NOW(), NULL),
(48, 4, 1, NULL, '2017-03-04', NULL, 'cinturones', 'se recibio menos antidad de cinturones requeridos', NULL, NULL, '2017-16-04', 2, '2017-28-04', NULL, NULL, 1, NOW(), NULL),
(49, 4, 2, NULL, '2017-04-04', NULL, 'bengalas', 'se recepciono una cantidad menor de bengalas',  NULL, NULL, '2017-17-04', 2, '2017-28-04', NULL, NULL, 1, NOW(), NULL),
(50, 4, 2, NULL, '2017-05-04', NULL, 'uniformes', 'No coinciden con las tallas solicitadas para el pesonal', NULL, NULL, '2017-18-04', 2, '2017-28-04', NULL, NULL, 1, NOW(), NULL),
(51, 4, 3, NULL, '2017-06-04', NULL, 'linternas acuaticas', 'Algunas linternas no funcionan a cierta profundidad', NULL, NULL, '2017-14-04', 2, '2017-26-04', NULL, NULL, 1, NOW(), NULL),
(52, 5, 3, NULL, '2017-07-04', NULL, 'extinguidores', 'la mayoria de equipos cuenta con fecha vencida', NULL, NULL, '2017-14-04', 2, '2017-26-04', NULL, NULL, 1, NOW(), NULL),
(53, 5, 5, NULL, '2017-08-04', NULL, 'cuerdas', 'las cuerdas son de menor grosor al solicitado', NULL, NULL, '2017-15-04', 2, '2017-27-04', NULL, NULL, 1, NOW(), NULL),
(54, 5, 5, NULL, '2017-09-04', NULL, 'brujulas', 'se solicito esto hace 4 semanas', NULL, NULL, '2017-15-04', 2, '2017-27-04', NULL, NULL, 1, NOW(), NULL),
(55, 5, 5, NULL, '2017-10-04', NULL, 'guantes electricos', 'demora en delivery del producto', NULL, NULL, '2017-16-04', 2, '2017-28-04', NULL, NULL, 1, NOW(), NULL),
(56, 5, 4, NULL, '2017-11-04', NULL, 'mascaras antigas', 'LLego menos cantidad de mascarillas', NULL, NULL, '2017-11-04', 2, '2017-23-04', NULL, NULL, 1, NOW(), NULL),
(57, 5, 4, NULL, '2017-12-04', NULL, 'handsfree NFC', 'se pidio handsfree aun no llegan', NULL, NULL, '2017-11-04', 2, '2017-23-04', NULL, NULL, 1, NOW(), NULL),
(58, 5, 5, NULL, '2017-13-04', NULL, 'cronometros', 'no funcionan gran parte del pedido', NULL, NULL, '2017-12-04', 2, '2017-24-04', NULL, NULL, 1, NOW(), NULL),
(59, 5, 5, NULL, '2017-14-04', NULL, 'barretas', 'se requirio cierta cantidad barretas llego incompleto', NULL, NULL, '2017-12-04', 2, '2017-24-04', NULL, NULL, 1, NOW(), NULL),
(60, 5, 5, NULL, '2017-15-04', NULL, 'chalecos de trafico', 'algunos chalecos no tienen los colores debidos', NULL, NULL, '2017-13-04', 2, '2017-25-04', NULL, NULL, 1, NOW(), NULL),

-- -- reclamos mes Mayo 2017
(61, 4, 3, NULL, '2017-01-05', NULL, 'casco con linterna ', 'la mayoria de linternas integradas no prenden', NULL, NULL, '2017-14-05', 2, '2017-25-05', NULL, NULL, 1, NOW(), NULL),
(62, 6, 3, NULL, '2017-02-05', NULL, 'cilindros oxigeno', 'llego menor cantidad de cilindros de lo pedido', NULL, NULL, '2017-14-05', 2, '2017-25-05', NULL, NULL, 1, NOW(), NULL),
(63, 6, 5, NULL, '2017-03-05', NULL, 'impermeables', 'durante la lluvia traspasa el uniforme impermeable', NULL, NULL, '2017-15-05', 2, '2017-25-05', NULL, NULL, 1, NOW(), NULL),
(64, 6, 1, NULL, '2017-04-05', NULL, 'arneses nuevos', 'llegaron menos arneses de lo solicitado', NULL, NULL, '2017-15-05', 2, '2017-26-05', NULL, NULL, 1, NOW(), NULL),
(65, 6, 1, NULL, '2017-05-05', NULL, 'botas', 'se recibio menor cantidad de lo acordado', NULL, NULL, '2017-16-05', 2, '2017-26-05', NULL, NULL, 1, NOW(), NULL),
(66, 6, 6, NULL, '2017-06-05', NULL, 'tapon auditivo', 'se filtra el ruido al estar en calle', NULL, NULL, '2017-11-05', 2, '2017-26-05', NULL, NULL, 1, NOW(), NULL),
(67, 6, 6, NULL, '2017-07-05', NULL, 'banderolas trafico', 'demora en recepcion dee este item', NULL, NULL, '2017-11-05', 2, '2017-27-05', NULL, NULL, 1, NOW(), NULL),
(68, 6, 6, NULL, '2017-08-05', NULL, 'conos naranjas', 'no funcionan gran parte del pedido', NULL, NULL, '2017-12-05', 2, '2017-27-05', NULL, NULL, 1, NOW(), NULL),
(69, 6, 6, NULL, '2017-09-05', NULL, 'bandas', 'no se nota la palabra peligro en las bandas', NULL, NULL, '2017-12-05', 2, '2017-27-05', NULL, NULL, 1, NOW(), NULL),
(70, 6, 6, NULL, '2017-10-05', NULL, 'candados', 'se recepciono solo candandos no hubo llaves', NULL, NULL, '2017-13-05', 2, '2017-27-05', NULL, NULL, 1, NOW(), NULL),

(71, 4, 6, NULL, '2017-11-05', NULL, 'ropa protectora', 'llego el paquete ropa una sola talla', NULL, NULL, '2017-17-05', 2, '2017-28-05', NULL, NULL, 1, NOW(), NULL),
(72, 4, 6, NULL, '2017-12-05', NULL, 'zapatos antishock', 'se recepciono menos cantidad', NULL, NULL, '2017-18-05', 2, '2017-29-05', NULL, NULL, 1, NOW(), NULL),
(73, 5, 6, NULL, '2017-13-05', NULL, 'lineas de vida', 'no se nota el color de las lineas', NULL, NULL, '2017-18-05', 2, '2017-29-05', NULL, NULL, 1, NOW(), NULL),
(74, 5, 6, NULL, '2017-14-05', NULL, 'fajas', 'se recibio menos cantidad de este producto', NULL, NULL, '2017-19-05', 2, '2017-30-05', NULL, NULL, 1, NOW(), NULL),
(75, 5, 6, NULL, '2017-15-05', NULL, 'respirador ', 'los respiradores se filtra el aire', NULL, NULL, '2017-20-05', 2, '2017-30-05', NULL, NULL, 1, NOW(), NULL),

-- -- reclamos mes Junio 2017
(76, 7, 6, NULL, '2017-01-06', NULL, 'lentes seguridad', 'La visibilidad es borrosa', NULL, NULL, '2017-11-06', 2, '2017-26-06', NULL, NULL, 1, NOW(), NULL),
(77, 7, 6, NULL, '2017-02-06', NULL, 'botines', 'LLego menor cantidad q la solicitada', NULL, NULL, '2017-11-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(78, 7, 6, NULL, '2017-03-06', NULL, 'Arnes', 'se hallo q los Arneses son muy flojos', NULL, NULL, '2017-12-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(79, 7, 6, NULL, '2017-04-06', NULL, 'balon oxigeno', 'no concuerda con la descripcion de peso', NULL, NULL, '2017-12-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(80, 7, 6, NULL, '2017-05-06', NULL, 'mascara facil', 'las mascarillas llegaron manchadas', NULL, NULL, '2017-13-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL)

(81, 7, 6, NULL, '2017-06-06', NULL, 'overoles', 'se demora en llegar el pedido', NULL, NULL, '2017-16-06', 2, '2017-26-06', NULL, NULL, 1, NOW(), NULL),
(82, 7, 6, NULL, '2017-07-06', NULL, 'respiradores', 'llego menos unidades de lo pedido', NULL, NULL, '2017-17-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(83, 7, 6, NULL, '2017-08-06', NULL, 'Tapones auditivos ', 'se recibio el producto pero presentan fallas', NULL, NULL, '2017-18-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(84, 7, 6, NULL, '2017-09-06', NULL, 'guantes aislantes', 'se recepciono menor pedido', NULL, NULL, '2017-19-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),
(85, 7, 6, NULL, '2017-10-06', NULL, 'chalecos', 'los chalecos presentan orificios', NULL, NULL, '2017-20-06', 2, '2017-27-06', NULL, NULL, 1, NOW(), NULL),

(86, 7, 6, NULL, '2017-07-06', NULL, 'respiradores', 'llego menos unidades de lo pedido', NULL, NULL, '2017-17-06', 2, '2017-28-06', NULL, NULL, 1, NOW(), NULL),
(87, 7, 6, NULL, '2017-08-06', NULL, 'cinturones', 'los cinturones no ajustan lo debido', NULL, NULL, '2017-18-06', 2, '2017-28-06', NULL, NULL, 1, NOW(), NULL),
(88, 7, 6, NULL, '2017-09-06', NULL, 'anteojos para quimicos', 'LLego menos recepciono menor pedido', NULL, NULL, '2017-19-06', 2, '2017-28-06', NULL, NULL, 1, NOW(), NULL),
(89, 7, 6, NULL, '2017-10-06', NULL, 'caretas soldador', 'se recibio menos cantidad q la solicitada', NULL, NULL, '2017-20-06', 2, '2017-29-06', NULL, NULL, 1, NOW(), NULL)





