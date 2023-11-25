create database ventas;
use ventas;

CREATE TABLE clientes(
    	id int primary key auto_increment,
    	nombre varchar(75),
   	    apellido varchar(75),
    	numero_documento varchar(11)
);

CREATE TABLE productos(
    	id int primary key auto_increment,
    	descripcion varchar(150),
    	codigo varchar(50),
    	stock int,
    	precio decimal(10,2)
);

CREATE TABLE factura(
	    id int primary key auto_increment,
    	clientes_id int,
    	fecha_creacion datetime,
    	total double,

   	    CONSTRAINT fk_clientes_id FOREIGN KEY(clientes_id) REFERENCES clientes(id)
);

CREATE TABLE detalles_factura(
	    id int primary key auto_increment,
	    factura_id int,
	    cantidad_productos int,
	    productos_id int,
    	importe double,

	    CONSTRAINT fk_factura_id FOREIGN KEY(factura_id) REFERENCES factura(id),
    	CONSTRAINT fk_productos_id FOREIGN KEY(productos_id) REFERENCES productos(id)
);