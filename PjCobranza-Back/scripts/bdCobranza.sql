CREATE DATABASE IF NOT EXISTS pjCobranza;
USE pjCobranza;

-- Primero desactiva las restricciones de clave foránea
SET FOREIGN_KEY_CHECKS = 0;

-- Tabla usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(60) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    confirm_password VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- Tabla clientes
CREATE TABLE IF NOT EXISTS clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(15) NOT NULL UNIQUE,
    correo VARCHAR(100),
    telefono VARCHAR(20),
    direccion TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Tabla prestamos (sin FK inicialmente)
CREATE TABLE IF NOT EXISTS prestamos (
    prestamo_id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    monto DECIMAL(10, 2) NOT NULL,
    tiempo INT NOT NULL DEFAULT 24,
    tasa_interes DECIMAL(5, 2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    estado ENUM('pendiente', 'pagado', 'vencido') DEFAULT 'pendiente'
) ENGINE=InnoDB;

-- Tabla pagos (sin FK inicialmente)
CREATE TABLE IF NOT EXISTS pagos (
    pago_id INT AUTO_INCREMENT PRIMARY KEY,
    prestamo_id INT,
    monto_pago DECIMAL(10, 2) NOT NULL,
    fecha_pago DATE NOT NULL,
    metodo_pago ENUM('efectivo', 'transferencia', 'tarjeta') NOT NULL
) ENGINE=InnoDB;

-- Reactiva las restricciones
SET FOREIGN_KEY_CHECKS = 1;

-- Agrega las relaciones después de crear todas las tablas
ALTER TABLE prestamos ADD CONSTRAINT fk_prestamos_clientes 
FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id) ON DELETE CASCADE;

ALTER TABLE pagos ADD CONSTRAINT fk_pagos_prestamos 
FOREIGN KEY (prestamo_id) REFERENCES prestamos(prestamo_id) ON DELETE CASCADE;

-- Inserta datos
INSERT IGNORE INTO clientes (nombre, apellido, dni, correo, telefono, direccion) VALUES 
("enrike rod", "samaniego guzman", "75798638", "erksg.10.26@gmail.com", "916912549", "ATE"),
("pamela sofia", "rojas guerrero", "48652345", "pame_sofia@gmail.com", "999562348", "SURCO"),
("korina stefa", "aranda ortiz", "10098638", "korina@gmail.com", "900253247", "MIRAFLORES");

INSERT IGNORE INTO prestamos(cliente_id, monto, tiempo, tasa_interes, fecha_inicio, fecha_vencimiento) VALUES
(1, 150.00, 24, 15, CURDATE(), "2025-03-27"),
(2, 200.00, 24, 10, CURDATE(), "2025-03-27");

INSERT IGNORE INTO pagos (prestamo_id, monto_pago, fecha_pago, metodo_pago) VALUES
(1, 6.25, CURDATE(), "transferencia"),
(2, 9.16, CURDATE(), "transferencia");

INSERT IGNORE INTO usuarios (email,password, confirm_password,rol) VALUES
("luisfernandosalinashui1997@gmail.com","enrike123","enrike123","ADMINISTRADOR");


-- Elimina los procedimientos si existen
DROP PROCEDURE IF EXISTS listaGeneralPrestamos;
DROP PROCEDURE IF EXISTS listaPrestamosPorEstado;
DROP PROCEDURE IF EXISTS listPagosByIdPrestamo;

-- Crea procedimientos almacenados
CREATE PROCEDURE listaGeneralPrestamos()
BEGIN 
    SELECT p.prestamo_id, c.cliente_id, CONCAT(c.nombre,' ',c.apellido) AS clientes,
    p.monto, p.tiempo, p.tasa_interes, p.fecha_inicio, p.fecha_vencimiento, p.estado,
    p.monto + (p.monto*(p.tasa_interes/100)) AS 'importeTotal'
    FROM prestamos p JOIN clientes c ON p.cliente_id = c.cliente_id; 
END;

CREATE PROCEDURE listaPrestamosPorEstado(IN estado_param VARCHAR(50))
BEGIN 
    SELECT p.prestamo_id, c.cliente_id, CONCAT(c.nombre,' ',c.apellido) AS clientes,
    p.monto, p.tiempo, p.tasa_interes, p.fecha_inicio, p.fecha_vencimiento, p.estado,
    p.monto + (p.monto*(p.tasa_interes/100)) AS 'importeTotal'
    FROM prestamos p JOIN clientes c ON p.cliente_id = c.cliente_id
    WHERE p.estado = estado_param;
END;

CREATE PROCEDURE listPagosByIdPrestamo(IN idPrestamo INT)
BEGIN
    SELECT p.pago_id, pr.prestamo_id, CONCAT(c.nombre,' ',c.apellido) AS cliente,
    pr.monto, pr.tiempo, pr.tasa_interes, p.monto_pago, p.fecha_pago, p.metodo_pago
    FROM pagos p
    JOIN prestamos pr ON p.prestamo_id=pr.prestamo_id
    JOIN clientes c ON pr.cliente_id=c.cliente_id
    WHERE p.prestamo_id=idPrestamo; 
END;
