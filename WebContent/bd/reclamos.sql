DROP DATABASE IF EXISTS reclamosBD;

CREATE DATABASE IF NOT EXISTS reclamosBD;

USE reclamosBD;

CREATE TABLE producto (
  idProducto INTEGER NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(500) NULL,
  fecProducto DATETIME NULL,
  direProducto VARCHAR(500) NULL,
  skuProducto VARCHAR(20) NULL,
  pesoProducto DECIMAL NULL,
  tipoProducto INTEGER NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idProducto)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se registran las personas que se encargan de hacer los despachos de los productos
-- ------------------------------------------------------------

CREATE TABLE despachador (
  idDespachador INTEGER NOT NULL AUTO_INCREMENT,
  codigo VARCHAR(15) NULL,
  nombres VARCHAR(50) NULL,
  apellidos VARCHAR(50) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idDespachador)
)
ENGINE=INNODB;

CREATE TABLE reclamo (
  idReclamo INTEGER NOT NULL AUTO_INCREMENT,
  descripcion TEXT NULL,
  fecReclamo DATETIME NULL,
  tipoReclamo VARCHAR(20) NULL,
  asunto VARCHAR(500) NULL,
  mensaje TEXT NULL,
  solucion TEXT NULL,
  respuesta VARCHAR(15) NULL,
  prioridad INTEGER NULL,
  vencimiento DATETIME NULL,
  indemnizar VARCHAR(2) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idReclamo)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla en donde seregistran las promociones disponibles
-- ------------------------------------------------------------

CREATE TABLE promocion (
  idPromocion INTEGER NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(500) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idPromocion)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se registran los proveedores
-- ------------------------------------------------------------

CREATE TABLE proveedor (
  idProveedor INTEGER NOT NULL AUTO_INCREMENT,
  razonSocial VARCHAR(500) NULL,
  numero_documento VARCHAR(12) NULL,
  representante VARCHAR(250) NULL,
  direccion VARCHAR(500) NULL,
  telefono1 VARCHAR(25) NULL,
  email VARCHAR(250) NULL,
  departamento VARCHAR(10) NULL,
  provincia VARCHAR(10) NULL,
  distrito VARCHAR(10) NULL,
  tipoCertiProveedor INTEGER NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idProveedor)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla con todos los permisos que los usuarios podran tener
-- ------------------------------------------------------------

CREATE TABLE permisos (
  idPermiso INTEGER NOT NULL AUTO_INCREMENT,
  permiso VARCHAR(25) NULL,
  estado INTEGER NULL DEFAULT 1,
  PRIMARY KEY(idPermiso)
)
ENGINE=INNODB;

CREATE TABLE persona (
  idPersona INTEGER NOT NULL AUTO_INCREMENT,
  numero_documento VARCHAR(12) NULL,
  direccion VARCHAR(500) NULL,
  departamento VARCHAR(10) NULL,
  provincia VARCHAR(10) NULL,
  distrito VARCHAR(10) NULL,
  telefono1 VARCHAR(25) NULL,
  telefono2 VARCHAR(25) NULL,
  email VARCHAR(250) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idPersona)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla  con los perfiles que podra tener el sistema
-- ------------------------------------------------------------

CREATE TABLE perfil (
  idPerfil INTEGER NOT NULL AUTO_INCREMENT,
  perfil VARCHAR(25) NULL,
  estado INTEGER NULL DEFAULT 1,
  PRIMARY KEY(idPerfil)
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla generica para guardar datos para listados como:
-- ubigeo
-- tipos
-- estados
-- ------------------------------------------------------------

CREATE TABLE parametros (
  idParametro INTEGER NOT NULL AUTO_INCREMENT,
  codGrupo VARCHAR(6) NULL,
  codigo VARCHAR(10) NULL,
  valor VARCHAR(50) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idParametro)
)
ENGINE=INNODB;

CREATE TABLE capacitador (
  idCapacitador INTEGER NOT NULL AUTO_INCREMENT,
  codigo VARCHAR(15) NULL,
  nombre VARCHAR(50) NULL,
  apellidos VARCHAR(50) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idCapacitador)
)
ENGINE=INNODB;

CREATE TABLE indemnizacion (
  idIndemnizacion INTEGER NOT NULL AUTO_INCREMENT,
  idReclamo INTEGER NOT NULL,
  fecha_indemnizacion DATE NULL,
  total_indemnizacion DOUBLE NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idIndemnizacion),
  FOREIGN KEY(idReclamo)
    REFERENCES reclamo(idReclamo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- solucion que se lle hadado al reclamo
-- ------------------------------------------------------------

CREATE TABLE solucion (
  idSolucion INTEGER NOT NULL AUTO_INCREMENT,
  idReclamo INTEGER NOT NULL,
  descripcion TEXT NULL,
  fecSolucion DATETIME NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idSolucion),
  FOREIGN KEY(idReclamo)
    REFERENCES reclamo(idReclamo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE cliente (
  idCliente INTEGER NOT NULL AUTO_INCREMENT,
  idPersona INTEGER NOT NULL,
  nomCliente VARCHAR(500) NULL,
  fecCliente DATETIME NULL,
  rucCliente VARCHAR(500) NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idCliente),
  FOREIGN KEY(idPersona)
    REFERENCES persona(idPersona)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE devolucion (
  idDevolucion INTEGER NOT NULL AUTO_INCREMENT,
  idReclamo INTEGER NOT NULL,
  detalle TEXT NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idDevolucion),
  FOREIGN KEY(idReclamo)
    REFERENCES reclamo(idReclamo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se registran las solicitudes de reclamo. 
-- ------------------------------------------------------------

CREATE TABLE solicitud (
  idSolicitud INTEGER NOT NULL AUTO_INCREMENT,
  idCliente INTEGER NOT NULL,
  descripcion VARCHAR(2000) NULL,
  fecSolicitud DATETIME NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idSolicitud),
  FOREIGN KEY(idCliente)
    REFERENCES cliente(idCliente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se registran los usuarios que tendran acceso al sistema
-- ------------------------------------------------------------

CREATE TABLE usuario (
  idUsuario INTEGER NOT NULL AUTO_INCREMENT,
  idPerfil INTEGER NOT NULL,
  idPersona INTEGER NOT NULL,
  username VARCHAR(100) NULL,
  ´password´ VARCHAR(500) NULL,
  password_caduca DATETIME NULL,
  email VARCHAR(500) NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  deleted_at DATETIME NULL,
  PRIMARY KEY(idUsuario),
  INDEX usuario_username_uniq(deleted_at),
  FOREIGN KEY(idPersona)
    REFERENCES persona(idPersona)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idPerfil)
    REFERENCES perfil(idPerfil)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE permisos_usuario (
  idUsuario INTEGER NOT NULL,
  idPermiso INTEGER NOT NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idUsuario, idPermiso),
  FOREIGN KEY(idUsuario)
    REFERENCES usuario(idUsuario)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idPermiso)
    REFERENCES permisos(idPermiso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- registro de las facturas EMITIDAS por la empresa
-- ------------------------------------------------------------

CREATE TABLE factura (
  idFactura INTEGER NOT NULL AUTO_INCREMENT,
  idProveedor INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  emision DATETIME NULL,
  numero VARCHAR(20) NULL,
  monto DECIMAL NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idFactura),
  FOREIGN KEY(idCliente)
    REFERENCES cliente(idCliente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idProveedor)
    REFERENCES proveedor(idProveedor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- detalle con los permisos que tiene cada perfil
-- ------------------------------------------------------------

CREATE TABLE permisos_perfil (
  idPermiso INTEGER NOT NULL,
  idPerfil INTEGER NOT NULL,
  estado INTEGER NULL,
  PRIMARY KEY(idPermiso, idPerfil),
  FOREIGN KEY(idPermiso)
    REFERENCES permisos(idPermiso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idPerfil)
    REFERENCES perfil(idPerfil)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- tabla en donde se registran las promociones aplicadas a cada cliente como resultado de la fidelizacion
-- ------------------------------------------------------------

CREATE TABLE fideliza (
  idFideliza INTEGER NOT NULL AUTO_INCREMENT,
  idReclamo INTEGER NOT NULL,
  idPromocion INTEGER NOT NULL,
  descripcion VARCHAR(2000) NULL,
  fecFideliza DATETIME NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idFideliza),
  FOREIGN KEY(idPromocion)
    REFERENCES promocion(idPromocion)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idReclamo)
    REFERENCES reclamo(idReclamo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE detalle_devolucion (
  idDetalleDevolucion INTEGER NOT NULL AUTO_INCREMENT,
  idDevolucion INTEGER NOT NULL,
  idDespachador INTEGER NOT NULL,
  idProducto INTEGER NOT NULL,
  direccion_entrega VARCHAR(250) NULL,
  fecha_entrega DATE NULL,
  hora_entrega TIME NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idDetalleDevolucion),
  FOREIGN KEY(idDevolucion)
    REFERENCES devolucion(idDevolucion)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idProducto)
    REFERENCES producto(idProducto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idDespachador)
    REFERENCES despachador(idDespachador)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla pensada para que se registre la intencion de cada cliente de apelar en caso no sea satisfactorio el reclamo
-- ------------------------------------------------------------

CREATE TABLE apelacion (
  idApelacion INTEGER NOT NULL AUTO_INCREMENT,
  idCliente INTEGER NOT NULL,
  descripcion TEXT NULL,
  tipoApelacion INTEGER NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idApelacion),
  FOREIGN KEY(idCliente)
    REFERENCES cliente(idCliente)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

CREATE TABLE detalleFactura (
  idDetalleFactura INTEGER NOT NULL AUTO_INCREMENT,
  idProducto INTEGER NOT NULL,
  idFactura INTEGER NOT NULL,
  cantidad INTEGER NULL,
  precio DECIMAL NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idDetalleFactura),
  FOREIGN KEY(idFactura)
    REFERENCES factura(idFactura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idProducto)
    REFERENCES producto(idProducto)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- reciben capacitacion solo las facturas que cumplan:
-- 
-- que tengan un reclamo solucionado y hayn sido fidelizados con la opcion capacitacion
-- que no hayan sido capacitadas anteriormente
-- ------------------------------------------------------------

CREATE TABLE capacitacion (
  idCapacitacion INTEGER NOT NULL AUTO_INCREMENT,
  idCapacitador INTEGER NOT NULL,
  idFactura INTEGER NOT NULL,
  fecha_capacitacion DATE NULL,
  hora_capacitacion TIME NULL,
  estado INTEGER NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idCapacitacion),
  FOREIGN KEY(idCapacitador)
    REFERENCES capacitador(idCapacitador)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idFactura)
    REFERENCES factura(idFactura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- detalle de los items por lo que esta reclamando, la factura puede tener munos items pero puede reclamar por uno, alguno o todos los items.
-- ------------------------------------------------------------

CREATE TABLE items_reclamo (
  idItemReclamo INTEGER NOT NULL AUTO_INCREMENT,
  idDetalleFactura INTEGER NOT NULL,
  idReclamo INTEGER NOT NULL,
  estado INTEGER NULL DEFAULT 1,
  PRIMARY KEY(idItemReclamo),
  FOREIGN KEY(idReclamo)
    REFERENCES reclamo(idReclamo)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idDetalleFactura)
    REFERENCES detalleFactura(idDetalleFactura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;

-- ------------------------------------------------------------
-- Tabla en donde se registran los items  que fueron reclmados y por los cuales se van a capacitar
-- ------------------------------------------------------------

CREATE TABLE capacitacion_items (
  idCapacitacionItem INTEGER NOT NULL,
  idCapacitacion INTEGER NOT NULL,
  idDetalleFactura INTEGER NOT NULL,
  detalle VARCHAR(500) NULL,
  estado INTEGER NULL DEFAULT 1,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY(idCapacitacionItem),
  FOREIGN KEY(idDetalleFactura)
    REFERENCES detalleFactura(idDetalleFactura)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(idCapacitacion)
    REFERENCES capacitacion(idCapacitacion)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
ENGINE=INNODB;









/*

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
	*/
	