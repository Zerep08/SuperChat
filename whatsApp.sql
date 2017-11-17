-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema whatsApp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema whatsApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `whatsApp` DEFAULT CHARACTER SET utf8 ;
USE `whatsApp` ;

-- -----------------------------------------------------
-- Table `whatsApp`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whatsApp`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `conectado` TINYINT NOT NULL,
  `fotografia` TEXT NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `eliminado` TINYINT NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `whatsApp`.`contacto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whatsApp`.`contacto` (
  `idContacto` INT NOT NULL AUTO_INCREMENT,
  `idUsuarioA` INT NOT NULL,
  `idUsuarioB` INT NOT NULL,
  PRIMARY KEY (`idContacto`),
  INDEX `fk_idUsuarioA_idx` (`idUsuarioA` ASC),
  INDEX `fk_idUsuarioB_idx` (`idUsuarioB` ASC),
  CONSTRAINT `fk_idUsuarioA`
    FOREIGN KEY (`idUsuarioA`)
    REFERENCES `whatsApp`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUsuarioB`
    FOREIGN KEY (`idUsuarioB`)
    REFERENCES `whatsApp`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `whatsApp`.`contenido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whatsApp`.`contenido` (
  `idContenido` INT NOT NULL AUTO_INCREMENT,
  `texto` TEXT NOT NULL,
  `emoji` TEXT NOT NULL,
  PRIMARY KEY (`idContenido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `whatsApp`.`mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whatsApp`.`mensaje` (
  `idMensaje` INT NOT NULL AUTO_INCREMENT,
  `leido` TINYINT NOT NULL,
  `enviado` TINYINT NOT NULL,
  `recibido` TINYINT NOT NULL,
  `dt_leido` DATETIME NOT NULL,
  `dt_enviado` DATETIME NOT NULL,
  `dt_recibido` DATETIME NOT NULL,
  `idContenido` INT NOT NULL,
  `idUsuario` INT NOT NULL,
  `idContacto` INT NOT NULL,
  PRIMARY KEY (`idMensaje`),
  INDEX `fk_contenido_idx` (`idContenido` ASC),
  INDEX `fk_idContacto_idx` (`idContacto` ASC),
  INDEX `fk_idUsuario_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_contenido`
    FOREIGN KEY (`idContenido`)
    REFERENCES `whatsApp`.`contenido` (`idContenido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idContacto`
    FOREIGN KEY (`idContacto`)
    REFERENCES `whatsApp`.`contacto` (`idContacto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUsuario`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `whatsApp`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
