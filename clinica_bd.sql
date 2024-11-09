-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema clinica_dental
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clinica_dental
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinica_dental` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `clinica_dental` ;

-- -----------------------------------------------------
-- Table `clinica_dental`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`paciente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `telefono` VARCHAR(20) NULL DEFAULT NULL,
  `direccion` VARCHAR(255) NULL DEFAULT NULL,
  `fechaNacimiento` DATE NULL DEFAULT NULL,
  `genero` ENUM('M', 'F', 'Otro') NULL DEFAULT NULL,
  `origenCliente` ENUM('convenios', 'redes', 'grupon', 'BNI', 'otros', 'colegios', 'centro_de_mayores') NOT NULL,
  `fechaCreacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `documentoIdentidad` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  INDEX `idx_nombre` (`nombre` ASC) VISIBLE,
  INDEX `idx_apellido` (`apellido` ASC) VISIBLE,
  INDEX `idx_telefono` (`telefono` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`financiacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`financiacion` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `montoTotal` DECIMAL(10,2) NOT NULL,
  `cuotas` INT NOT NULL,
  `montoCuota` DECIMAL(10,2) NOT NULL,
  `fechaInicio` DATE NULL DEFAULT NULL,
  `fechaFin` DATE NULL DEFAULT NULL,
  `metodoFinanciacion` ENUM('banco', 'clinica') NULL DEFAULT NULL,
  `interes` DECIMAL(5,2) NULL DEFAULT NULL,
  `idPaciente` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `financiacion_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `clinica_dental`.`paciente` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`caja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`caja` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fechaPago` DATE NOT NULL,
  `monto` DECIMAL(10,2) NOT NULL,
  `metodoPago` ENUM('efectivo', 'tarjeta', 'financiado') NULL DEFAULT NULL,
  `financiador` VARCHAR(255) NULL DEFAULT NULL,
  `idPaciente` BIGINT NULL DEFAULT NULL,
  `idFinanciacion` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente` (`idPaciente` ASC) VISIBLE,
  INDEX `idFinanciacion` (`idFinanciacion` ASC) VISIBLE,
  CONSTRAINT `caja_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `clinica_dental`.`paciente` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `caja_ibfk_2`
    FOREIGN KEY (`idFinanciacion`)
    REFERENCES `clinica_dental`.`financiacion` (`id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`tratamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`tratamiento` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` TEXT NOT NULL,
  `fechaInicio` DATE NULL DEFAULT NULL,
  `fechaFin` DATE NULL DEFAULT NULL,
  `idPaciente` BIGINT NULL DEFAULT NULL,
  `nombre` VARCHAR(150) NOT NULL,
  `presupuesto` DECIMAL(10,2) NOT NULL,
  `aprobado` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `tratamiento_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `clinica_dental`.`paciente` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`documento` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombreDocumento` VARCHAR(255) NOT NULL,
  `fechaFirma` DATE NULL DEFAULT NULL,
  `idPaciente` BIGINT NULL DEFAULT NULL,
  `firmado` BIT(1) NULL DEFAULT NULL,
  `idTratamiento` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente` (`idPaciente` ASC) VISIBLE,
  INDEX `idTratamiento` (`idTratamiento` ASC) VISIBLE,
  CONSTRAINT `documento_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `clinica_dental`.`paciente` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `documento_ibfk_2`
    FOREIGN KEY (`idTratamiento`)
    REFERENCES `clinica_dental`.`tratamiento` (`id`)
    ON DELETE SET NULL)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`presupuesto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`presupuesto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `descripcion` TEXT NOT NULL,
  `monto` DECIMAL(10,2) NOT NULL,
  `fechaCreacion` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `fechaAceptacion` DATE NULL DEFAULT NULL,
  `idPaciente` BIGINT NULL DEFAULT NULL,
  `estado` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idPaciente` (`idPaciente` ASC) VISIBLE,
  CONSTRAINT `presupuesto_ibfk_1`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `clinica_dental`.`paciente` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`rol` (
  `idRol` BIGINT NOT NULL AUTO_INCREMENT,
  `nombreRol` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `clinica_dental`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_dental`.`usuario` (
  `idUsuario` BIGINT NOT NULL AUTO_INCREMENT,
  `nombreUsuario` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `idRol` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  INDEX `idRol` (`idRol` ASC) VISIBLE,
  CONSTRAINT `usuario_ibfk_1`
    FOREIGN KEY (`idRol`)
    REFERENCES `clinica_dental`.`rol` (`idRol`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
