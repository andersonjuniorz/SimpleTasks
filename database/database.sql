-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema todolist
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `todolist` DEFAULT CHARACTER SET utf8 ;
USE `todolist` ;

-- -----------------------------------------------------
-- Table `todolist`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`category` (
  `cat_id` INT NOT NULL AUTO_INCREMENT,
  `cat_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE INDEX `cat_name_UNIQUE` (`cat_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `todolist`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_remmemberPass` BIT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `todolist`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `todolist`.`tasks` (
  `task_id` INT NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(100) NOT NULL,
  `task_date` DATE NULL,
  `task_timeStart` TIME NULL,
  `task_timeEnd` TIME NULL,
  `fk_id_category` INT NOT NULL,
  `fk_id_users` INT NOT NULL,
  PRIMARY KEY (`task_id`, `fk_id_users`, `fk_id_category`),
  INDEX `fk_tasks_category_idx` (`fk_id_category` ASC) VISIBLE,
  INDEX `fk_tasks_users1_idx` (`fk_id_users` ASC) VISIBLE,
  CONSTRAINT `fk_tasks_category`
    FOREIGN KEY (`fk_id_category`)
    REFERENCES `todolist`.`category` (`cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_users1`
    FOREIGN KEY (`fk_id_users`)
    REFERENCES `todolist`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
