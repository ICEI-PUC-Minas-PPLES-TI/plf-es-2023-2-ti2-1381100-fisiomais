
---

## Script de Criação do Banco de Dados

```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fisiomais_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fisiomais_db` DEFAULT CHARACTER SET utf8 ;
USE `fisiomais_db` ;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`paciente` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `genero` ENUM('Homem', 'Mulher', 'Outro') NOT NULL,
  `endereco` VARCHAR(200) NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`fisioterapeuta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`fisioterapeuta` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `endereco` VARCHAR(200) NULL,
  PRIMARY KEY (`_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`agenda` (
  `_id` INT NOT NULL AUTO_INCREMENT,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `disponivel` TINYINT NOT NULL,
  `dia` TINYINT NOT NULL DEFAULT 0,
  `horario_inicio` TIME NULL,
  `horario_fim` TIME NULL,
  `fisioterapeuta__id` INT NOT NULL,
  PRIMARY KEY (`_id`, `fisioterapeuta__id`),
  INDEX `fk_agenda_fisioterapeuta1_idx` (`fisioterapeuta__id` ASC) VISIBLE,
  CONSTRAINT `fk_agenda_fisioterapeuta1`
    FOREIGN KEY (`fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`fisioterapeuta` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`consulta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`consulta` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `paciente__id` INT NOT NULL,
  `fisioterapeuta__id` INT NOT NULL,
  `data_e_hora` DATETIME(1) NOT NULL,
  `observacoes` TEXT(500) NULL,
  `confirmacao` TINYINT NOT NULL,
  `owner_id` INT NOT NULL,
  `link` LONGTEXT NULL,
  PRIMARY KEY (`_id`, `paciente__id`, `fisioterapeuta__id`),
  INDEX `fk_consulta_paciente_idx` (`paciente__id` ASC) VISIBLE,
  INDEX `fk_consulta_fisioterapeuta1_idx` (`fisioterapeuta__id` ASC) VISIBLE,
  CONSTRAINT `fk_consulta_paciente`
    FOREIGN KEY (`paciente__id`)
    REFERENCES `fisiomais_db`.`paciente` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_consulta_fisioterapeuta1`
    FOREIGN KEY (`fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`fisioterapeuta` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`midia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`midia` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `fisioterapeuta__id` INT NOT NULL,
  `type` ENUM(Video, 'Imagem', 'GIF') NOT NULL,
  `arquivo` LONGBLOB NULL,
  `link_arquivo` LONGTEXT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` TEXT(1000) NOT NULL,
  PRIMARY KEY (`_id`, `fisioterapeuta__id`),
  INDEX `fk_midia_fisioterapeuta1_idx` (`fisioterapeuta__id` ASC) VISIBLE,
  CONSTRAINT `fk_midia_fisioterapeuta1`
    FOREIGN KEY (`fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`fisioterapeuta` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`tratamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`tratamento` (
  `create_time` TIMESTAMP NULL,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `fisioterapeuta__id` INT NOT NULL,
  `paciente__id` INT NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `observacoes` TEXT NULL,
  `end_date` DATETIME NULL,
  `feedback` LONGTEXT NULL,
  PRIMARY KEY (`_id`),
  INDEX `fk_tratamento_fisioterapeuta1_idx` (`fisioterapeuta__id` ASC) VISIBLE,
  INDEX `fk_tratamento_paciente1_idx` (`paciente__id` ASC) VISIBLE,
  CONSTRAINT `fk_tratamento_fisioterapeuta1`
    FOREIGN KEY (`fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`fisioterapeuta` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tratamento_paciente1`
    FOREIGN KEY (`paciente__id`)
    REFERENCES `fisiomais_db`.`paciente` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`exercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`exercicio` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `descricao` TEXT(1000) NOT NULL,
  `fisioterapeuta__id` INT NOT NULL,
  PRIMARY KEY (`_id`, `fisioterapeuta__id`),
  INDEX `fk_exercicio_fisioterapeuta1_idx` (`fisioterapeuta__id` ASC) VISIBLE,
  CONSTRAINT `fk_exercicio_fisioterapeuta1`
    FOREIGN KEY (`fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`fisioterapeuta` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`exercicio_has_midias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`exercicio_has_midias` (
  `midia__id` INT NOT NULL,
  `midia_fisioterapeuta__id` INT NOT NULL,
  `exercicio__id` INT NOT NULL,
  PRIMARY KEY (`midia__id`, `midia_fisioterapeuta__id`, `exercicio__id`),
  INDEX `fk_exercicio_videos_midia1_idx` (`midia__id` ASC, `midia_fisioterapeuta__id` ASC) VISIBLE,
  INDEX `fk_exercicio_has_midias_exercicio1_idx` (`exercicio__id` ASC) VISIBLE,
  CONSTRAINT `fk_exercicio_videos_midia1`
    FOREIGN KEY (`midia__id` , `midia_fisioterapeuta__id`)
    REFERENCES `fisiomais_db`.`midia` (`_id` , `fisioterapeuta__id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_exercicio_has_midias_exercicio1`
    FOREIGN KEY (`exercicio__id`)
    REFERENCES `fisiomais_db`.`exercicio` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `fisiomais_db`.`tratamento_has_exercicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fisiomais_db`.`tratamento_has_exercicios` (
  `tratamento__id` INT NOT NULL,
  `exercicio__id` INT NOT NULL,
  PRIMARY KEY (`tratamento__id`, `exercicio__id`),
  INDEX `fk_tratamento_has_exercicios_exercicio1_idx` (`exercicio__id` ASC) VISIBLE,
  CONSTRAINT `fk_tratamento_has_exercicios_tratamento1`
    FOREIGN KEY (`tratamento__id`)
    REFERENCES `fisiomais_db`.`tratamento` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tratamento_has_exercicios_exercicio1`
    FOREIGN KEY (`exercicio__id`)
    REFERENCES `fisiomais_db`.`exercicio` (`_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```

---
