DROP DATABASE IF EXISTS reclamosBD;

CREATE DATABASE IF NOT EXISTS reclamosBD;

USE reclamosBD;

CREATE TABLE tb_proveedor (
  idProveedor INTEGER NOT NULL AUTO_INCREMENT,
  razonSocial VARCHAR(500) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idProveedor)
)
ENGINE=INNODB;

CREATE TABLE tb_cliente (
  idCliente INTEGER NOT NULL AUTO_INCREMENT,
  nomCliente VARCHAR(500) NULL,
  direCliente VARCHAR(500) NULL,
  email VARCHAR(500) NULL,
  fecCliente DATETIME NULL,
  rucCliente VARCHAR(500) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idCliente)
)
ENGINE=INNODB;

CREATE TABLE tb_solicitud (
  idSolicitud INTEGER NOT NULL AUTO_INCREMENT,
  idCliente INTEGER NOT NULL,
  descripcion VARCHAR(2000) NULL,
  fecSolicitud DATETIME NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idSolicitud),
  FOREIGN KEY(idCliente)
    REFERENCES tb_cliente(idCliente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE tb_factura (
  idFactura INTEGER NOT NULL AUTO_INCREMENT,
  idProveedor INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  descripcion VARCHAR(2000) NULL,
  fecFactura DATETIME NULL,
  estado INTEGER NULL,
  numero VARCHAR(20) NULL,
  fecRegistro DATETIME NULL,
  monto DOUBLE NULL,
  PRIMARY KEY(idFactura),
  FOREIGN KEY(idCliente)
    REFERENCES tb_cliente(idCliente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idProveedor)
    REFERENCES tb_proveedor(idProveedor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE tb_reclamos (
  idReclamo INTEGER NOT NULL AUTO_INCREMENT,
  idFactura INTEGER NOT NULL,
  descripcion VARCHAR(2000) NULL,
  fecReclamo DATETIME NULL,
  fecRegistro DATETIME NULL,
  fecVencimiento DATETIME NULL,
  tipoReclamo VARCHAR(20) NULL,
  asunto VARCHAR(500) NULL,
  mensaje VARCHAR(2500) NULL,
  solucion VARCHAR(2500) NULL,
  prioridad INTEGER NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idReclamo),
  FOREIGN KEY(idFactura)
    REFERENCES tb_factura(idFactura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;



	insert into `reclamosbd`.`tb_cliente` 
	(idCliente, nomCliente, direCliente, email, fecCliente, rucCliente, estado)
	values (1, 'Carlos Vera', 'Av. Industrial 236', 'dverat@gmail.com','2015-05-05', '1020287020125', 1),
	(2, 'Juan Panta', 'Av. El Sol 236', 'user2@gmail.com','2015-05-05', '1020209820205', 1),
	(3, 'Luis Garcia', 'Av. Las Cumbres 236', 'user3@gmail.com','2015-05-05', '1020256020365', 1),
	(4, 'Alex Pérez', 'Av. Los Nogales 236', 'user4@gmail.com','2015-05-05', '1020302020445', 1);

	insert into `reclamosbd`.`tb_proveedor` (idProveedor, razonSocial, estado)
	values 	(1, 'dvera sac', 1);

	insert into `reclamosbd`.`tb_factura` 
	(idFactura, idProveedor, idCliente, descripcion, fecFactura, estado, numero,monto)
	values 	(1, 1, 1, 'compra uno', '2015-05-05', 1, '00001',2000),
	(2, 1, 1, 'compra dos', '2015-07-05', 1, '00003',4000),
	(3, 1, 1, 'compra tres', '2015-07-05', 1, '00004',5000),
	(4, 1, 1, 'compra cuatro', '2015-07-05', 1, '00005',6000),
	(5, 1, 1, 'compra cinco', '2015-07-05', 1, '00006',7000),
	(6, 1, 2, 'compra seis', '2015-07-05', 1, '00007',8000),
	(7, 1, 2, 'compra siete', '2015-07-05', 1, '00008',4000),
	(8, 1, 3, 'compra ocho', '2015-07-05', 1, '00009',5000),
	(9, 1, 3, 'compra nueve', '2015-07-05', 1, '00010',6000),
	(10, 1, 4, 'compra diez', '2015-07-05', 1, '00011',7000),
	(11, 1, 4, 'compra once', '2015-07-05', 1, '00012',8000),
	(12, 1, 1, 'compra doce', '2015-07-05', 1, '00013',3000);
	
	