-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pizzaria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pizzaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pizzaria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pizzaria` ;

-- -----------------------------------------------------
-- Table `pizzaria`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `nome_usuario` VARCHAR(45) NOT NULL,
  `pswd_usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `nome_usuario_UNIQUE` (`nome_usuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzaria`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NULL DEFAULT NULL,
  `cliente_email` VARCHAR(45) NOT NULL,
  `cliente_end` VARCHAR(255) NOT NULL,
  `cliente_nome` VARCHAR(45) NOT NULL,
  `cliente_tel` BIGINT NULL DEFAULT NULL,
  `cliente_cpf` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`id_cliente` ASC) VISIBLE,
  UNIQUE INDEX `cliente_cpf_UNIQUE` (`cliente_cpf` ASC) VISIBLE,
  INDEX `id_usuario_idx` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `id_usuario`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `pizzaria`.`usuario` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzaria`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`item` (
  `id_item` INT NOT NULL AUTO_INCREMENT,
  `item_nome` VARCHAR(45) NOT NULL,
  `item_desc` TINYTEXT NULL DEFAULT NULL,
  `item_price` FLOAT NOT NULL,
  `item_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_item`),
  UNIQUE INDEX `item_nome_UNIQUE` (`item_nome` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzaria`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `id_cliente` INT NOT NULL,
  `pedido_ts` DATETIME(6) NULL DEFAULT NULL,
  `pedido_valor` FLOAT NOT NULL,
  `pedido_pag` VARCHAR(45) NOT NULL,
  `pedido_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `id_cliente_idx` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `id_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `pizzaria`.`cliente` (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzaria`.`pedido_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`pedido_item` (
  `id_pi` INT NOT NULL AUTO_INCREMENT,
  `id_pedido` INT NOT NULL,
  `id_item` INT NOT NULL,
  `pi_valor` FLOAT NOT NULL,
  `pi_qtd` INT NOT NULL,
  PRIMARY KEY (`id_pi`),
  UNIQUE INDEX `id_pi_UNIQUE` (`id_pi` ASC) VISIBLE,
  INDEX `id_pedido_idx` (`id_pedido` ASC) VISIBLE,
  INDEX `id_item_idx` (`id_item` ASC) VISIBLE,
  CONSTRAINT `id_item`
    FOREIGN KEY (`id_item`)
    REFERENCES `pizzaria`.`item` (`id_item`),
  CONSTRAINT `id_pedido`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `pizzaria`.`pedido` (`id_pedido`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pizzaria`.`spring_session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`spring_session` (
  `PRIMARY_ID` CHAR(36) NOT NULL,
  `SESSION_ID` CHAR(36) NOT NULL,
  `CREATION_TIME` BIGINT NOT NULL,
  `LAST_ACCESS_TIME` BIGINT NOT NULL,
  `MAX_INACTIVE_INTERVAL` INT NOT NULL,
  `EXPIRY_TIME` BIGINT NOT NULL,
  `PRINCIPAL_NAME` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `pizzaria`.`spring_session_attributes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pizzaria`.`spring_session_attributes` (
  `SESSION_PRIMARY_ID` CHAR(36) NOT NULL,
  `ATTRIBUTE_NAME` VARCHAR(200) NOT NULL,
  `ATTRIBUTE_BYTES` BLOB NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK`
    FOREIGN KEY (`SESSION_PRIMARY_ID`)
    REFERENCES `pizzaria`.`spring_session` (`PRIMARY_ID`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
