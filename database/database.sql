-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema todolist
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema todolist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todolist` DEFAULT CHARACTER SET utf8 ;
USE `todolist` ;

-- -----------------------------------------------------
-- Table `todolist`.`tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`tb_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `passw` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `todolist`.`tb_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`tb_category` (
  `cat_id` INT NOT NULL AUTO_INCREMENT,
  `cat_name` VARCHAR(100) NOT NULL,
  `fk_id_user` INT NOT NULL,
  PRIMARY KEY (`cat_id`, `fk_id_user`),
  INDEX `fk_tb_category_tb_user_idx` (`fk_id_user` ASC) VISIBLE,
  CONSTRAINT `fk_tb_category_tb_user`
    FOREIGN KEY (`fk_id_user`)
    REFERENCES `todolist`.`tb_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `todolist`.`tb_task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`tb_task` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `descr` VARCHAR(100) NOT NULL,
  `dueDate` VARCHAR(50) NULL DEFAULT NULL,
  `priority` VARCHAR(35) NULL,
  `done` BIT(1) NULL DEFAULT NULL,
  `fk_id_category` INT NOT NULL,
  PRIMARY KEY (`task_id`, `fk_id_category`),
  INDEX `fk_tb_task_tb_category1_idx` (`fk_id_category` ASC) VISIBLE,
  CONSTRAINT `fk_tb_task_tb_category1`
    FOREIGN KEY (`fk_id_category`)
    REFERENCES `todolist`.`tb_category` (`cat_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
