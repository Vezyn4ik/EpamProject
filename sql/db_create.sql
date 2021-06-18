drop schema `epam`;
CREATE SCHEMA IF NOT EXISTS `epam` DEFAULT CHARACTER SET utf8 ;
USE `epam` ;

CREATE TABLE IF NOT EXISTS `epam`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birth` DATE NOT NULL,
  `login` VARCHAR(15) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `role` ENUM('ADMIN', 'USER') NOT NULL,
  `create_time` DATETIME NOT NULL,
  `state` ENUM('LOCKED', 'UNLOCKED') NOT NULL DEFAULT 'UNLOCKED',
  `telephone` VARCHAR(12) NOT NULL,
  `email` VARCHAR(35) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `epam`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_user` INT NOT NULL,
  `balance` DOUBLE NOT NULL DEFAULT '0',
  `create_time` DATETIME NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `fk_requisite` INT NOT NULL,
  `limit` DOUBLE NOT NULL,
  `currency` VARCHAR(5) NOT NULL,
  `state` ENUM('LOCKED', 'UNLOCKED', 'WAITING') NOT NULL DEFAULT 'UNLOCKED',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE,
  INDEX `fk_Account_User1_idx` (`fk_user` ASC) VISIBLE,
  CONSTRAINT `fk_Account_User1`
    FOREIGN KEY (`fk_user`)
    REFERENCES `epam`.`user` (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `epam`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `epam`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fk_account` INT NOT NULL,
  `status` ENUM('PREPARED', 'SENT') NOT NULL,
  `date` DATETIME NOT NULL,
  `purpose` VARCHAR(45) NOT NULL,
  `fk_category` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `commission` DOUBLE NOT NULL,
  `recipient_account` VARCHAR(20) NOT NULL,
  `recipient_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Payment_Account1_idx` (`fk_account` ASC) VISIBLE,
  INDEX `fk_Payment_Type1_idx` (`fk_category` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_Account1`
    FOREIGN KEY (`fk_account`)
    REFERENCES `epam`.`account` (`id`),
  CONSTRAINT `fk_Payment_Type1`
    FOREIGN KEY (`fk_category`)
    REFERENCES `epam`.`category` (`id`))
ENGINE = InnoDB;