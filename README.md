Test project for ООО "Райдер Софт"
MySQL Workbench script for table:

CREATE DATABASE  IF NOT EXISTS `weather_directory`;
USE `weather_directory`;

DROP TABLE IF EXISTS `weather_history`;

CREATE TABLE `weather_history` (
  `weather_date` date NOT NULL,
  `weather_value` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`weather_date`)
) 

