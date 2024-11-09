USE clinica_dental;
SET FOREIGN_KEY_CHECKS = 0;

-- Datos para la tabla `Paciente`
INSERT IGNORE INTO `clinica_dental`.`Paciente` (`nombre`, `apellido`, `email`, `telefono`, `direccion`, `fechaNacimiento`, `genero`, `origenCliente`)
VALUES
  ('Juan', 'Pérez', 'juan.perez@example.com', '123456789', 'Calle Falsa 123', '1985-02-15', 'M', 'redes'),
  ('María', 'García', 'maria.garcia@example.com', '987654321', 'Avenida Siempreviva 742', '1990-06-21', 'F', 'colegios'),
  ('Carlos', 'López', 'carlos.lopez@example.com', '555444333', 'Paseo de la Reforma 120', '1978-12-05', 'M', 'BNI'),
  ('Ana', 'Martínez', 'ana.martinez@example.com', '333222111', 'Calle Palma Real 50', '1995-08-19', 'F', 'grupon'),
  ('Lucía', 'Ramírez', 'lucia.ramirez@example.com', '111222333', 'Avenida de la Paz 320', '2000-04-11', 'Otro', 'otros');

-- Datos para la tabla `Financiacion`
INSERT INTO `clinica_dental`.`Financiacion` (`montoTotal`, `cuotas`, `montoCuota`, `fechaInicio`, `fechaFin`, `metodoFinanciacion`, `interes`, `idPaciente`)
VALUES
    (1500.00, 12, 125.00, '2023-01-01', '2023-12-01', 'banco', 5.0, 1),
    (2000.00, 10, 200.00, '2023-02-01', '2023-11-01', 'clinica', 3.0, 2),
    (1200.00, 8, 150.00, '2023-03-01', '2023-10-01', 'banco', 4.5, 3),
    (1800.00, 15, 120.00, '2023-04-01', '2024-07-01', 'clinica', 2.5, 4),
    (2200.00, 12, 183.33, '2023-05-01', '2024-04-01', 'banco', 6.0, 5)
    ON DUPLICATE KEY UPDATE `montoTotal` = VALUES(`montoTotal`);

-- Datos para la tabla `Caja`
INSERT IGNORE INTO `clinica_dental`.`Caja` (`fechaPago`, `monto`, `metodoPago`, `financiador`, `idPaciente`, `idFinanciacion`)
VALUES
  ('2023-06-01', 125.00, 'tarjeta', NULL, 1, 1),
  ('2023-07-01', 200.00, 'efectivo', NULL, 2, NULL),
  ('2023-08-01', 150.00, 'financiado', 'Banco XYZ', 3, 3),
  ('2023-09-01', 120.00, 'efectivo', NULL, 4, NULL),
  ('2023-10-01', 183.33, 'financiado', 'Banco ABC', 5, 5);

-- Datos para la tabla `Tratamiento`
INSERT IGNORE INTO `clinica_dental`.`Tratamiento` (`descripcion`, `fechaInicio`, `fechaFin`, `idPaciente`, `nombre`, `Presupuesto`, `aprobado`)
VALUES
  ('Ortodoncia', '2023-01-10', '2023-07-10', 1, 'Ortodoncia Adulto', 2500.00, 1),
  ('Blanqueamiento Dental', '2023-02-15', '2023-03-15', 2, 'Blanqueamiento', 500.00, 1),
  ('Implante', '2023-03-20', '2023-08-20', 3, 'Implante Dental', 1500.00, 0),
  ('Endodoncia', '2023-04-25', '2023-05-25', 4, 'Tratamiento de Endodoncia', 800.00, 1),
  ('Prótesis', '2023-06-30', '2023-12-30', 5, 'Prótesis Dental', 3000.00, 0);

-- Datos para la tabla `Documento`
INSERT IGNORE INTO `clinica_dental`.`Documento` (`nombreDocumento`, `fechaFirma`, `idPaciente`, `firmado`, `idTratamiento`)
VALUES
  ('Consentimiento Ortodoncia', '2023-01-09', 1, 1, 1),
  ('Consentimiento Blanqueamiento', '2023-02-14', 2, 1, 2),
  ('Consentimiento Implante', NULL, 3, 0, 3),
  ('Consentimiento Endodoncia', '2023-04-24', 4, 1, 4),
  ('Consentimiento Prótesis', NULL, 5, 0, 5);

-- Datos para la tabla `Presupuesto`
INSERT IGNORE INTO `clinica_dental`.`Presupuesto` (`descripcion`, `monto`, `fechaCreacion`, `fechaAceptacion`, `idPaciente`, `estado`)
VALUES
  ('Presupuesto Ortodoncia', 2500.00, '2023-01-01', '2023-01-09', 1, 'Aceptado'),
  ('Presupuesto Blanqueamiento', 500.00, '2023-02-01', '2023-02-14', 2, 'Aceptado'),
  ('Presupuesto Implante', 1500.00, '2023-03-01', NULL, 3, 'Pendiente'),
  ('Presupuesto Endodoncia', 800.00, '2023-04-01', '2023-04-24', 4, 'Aceptado'),
  ('Presupuesto Prótesis', 3000.00, '2023-05-01', NULL, 5, 'Pendiente');

-- Datos para la tabla `Rol`
INSERT IGNORE INTO `clinica_dental`.`Rol` (`nombreRol`)
VALUES
  ('Administrador'),
  ('GestorInventario'),
  ('UsuarioRegular');

-- Datos para la tabla `Usuario`
INSERT IGNORE INTO `clinica_dental`.`Usuario` (`nombreUsuario`, `email`, `password`, `idRol`)
VALUES
  ('admin', 'admin@example.com', 'admin_pass', 1),
  ('gestor', 'gestor@example.com', 'gestor_pass', 2),
  ('usuario', 'usuario@example.com', 'usuario_pass', 3),
  ('ana.martinez', 'ana.martinez@example.com', 'ana_pass', 3),
  ('lucia.ramirez', 'lucia.ramirez@example.com', 'lucia_pass', 3);

SET FOREIGN_KEY_CHECKS = 1;
