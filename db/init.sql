-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema airports
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `airports` ;

-- -----------------------------------------------------
-- Schema airports
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airports` DEFAULT CHARACTER SET utf8 ;
USE `airports` ;

-- -----------------------------------------------------
-- Table `airports`.`airport`
-- -----------------------------------------------------
CREATE TABLE `airports`.`airport` (
  `iata_code` varchar(3) NOT NULL, -- from CSV
  `country_id` varchar(2) NOT NULL, -- from CSV
  `region` varchar(10) DEFAULT NULL, -- from CSV
  `city` varchar(50) DEFAULT NULL, -- from CSV
  `icao_code` varchar(4) DEFAULT NULL, -- from CSV
  `latitude` decimal(9,6) DEFAULT NULL, -- from Lufthansa
  `longitude` decimal(9,6) DEFAULT NULL, -- from Lufthansa  
  `utc_offset` varchar(6) DEFAULT NULL, -- from Lufthansa
  `timezone` varchar(45) DEFAULT NULL, -- from Lufthansa
  PRIMARY KEY (`iata_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'airports'@'%' IDENTIFIED BY 'supersafe'; 
GRANT ALL ON airports.* TO 'airports'@'%';
FLUSH PRIVILEGES;