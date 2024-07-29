CREATE DATABASE Contoso;

USE Contoso;

CREATE TABLE insumos_medicos (
    PK_insumo INT PRIMARY KEY AUTO_INCREMENT,
    nombre_insumo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

-- Insercion de datos de prueba
INSERT INTO insumos_medicos (nombre_insumo, descripcion, precio)
VALUES 
('Guantes Quirúrgicos', 'Guantes desechables de látex', 12.50),
('Mascarilla N95', 'Mascarilla de alta eficiencia contra partículas', 5.00),
('Jeringa 5ml', 'Jeringa desechable de 5ml', 1.20),
('Termómetro Digital', 'Termómetro digital para medir temperatura corporal', 25.75),
('Bata Médica', 'Bata médica desechable', 15.00),
('Desinfectante de Manos', 'Gel desinfectante de manos con alcohol al 70%', 3.99),
('Oxímetro de Pulso', 'Dispositivo para medir la saturación de oxígeno en sangre', 45.00),
('Tijeras Quirúrgicas', 'Tijeras de acero inoxidable para uso quirúrgico', 20.50),
('Curitas', 'Curitas adhesivas para pequeñas heridas', 2.50),
('Venda Elástica', 'Venda elástica para soporte y compresión', 7.99);

-- INSERT INTO insumos_medicos (nombre_insumo, descripcion, precio)
-- VALUES ('Nombre del Insumo', 'Descripción del Insumo', 99.99);

-- SELECT * FROM insumos_medicos;

-- SELECT * FROM insumos_medicos WHERE PK_insumo = 1;

-- UPDATE insumos_medicos
-- SET nombre_insumo = 'Nuevo Nombre', descripcion = 'Nueva Descripción', precio = 89.99
-- WHERE PK_insumo = 1;

 -- DELETE FROM insumos_medicos WHERE PK_insumo = 1;
