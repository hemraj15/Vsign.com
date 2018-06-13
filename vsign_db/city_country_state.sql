-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.51 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for printkaari_db
CREATE DATABASE IF NOT EXISTS `printkaari_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `printkaari_db`;


-- Dumping structure for table printkaari_db.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) DEFAULT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6p2u50v8fg2y0js6djc6xanit` (`state_id`),
  CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table printkaari_db.city: ~2 rows (approximately)
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id`, `city_name`, `state_id`) VALUES
	(1, 'Bhopal', 1),
	(2, 'Indore', 1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


-- Dumping structure for table printkaari_db.country
CREATE TABLE IF NOT EXISTS `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table printkaari_db.country: ~1 rows (approximately)
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`id`, `country_name`) VALUES
	(1, 'INDIA');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


-- Dumping structure for table printkaari_db.state
CREATE TABLE IF NOT EXISTS `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKghic7mqjt6qb9vq7up7awu0er` (`country_id`),
  CONSTRAINT `FKghic7mqjt6qb9vq7up7awu0er` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table printkaari_db.state: ~1 rows (approximately)
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (`id`, `state_name`, `country_id`) VALUES
	(1, 'Madhya Pradesh', 1);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
