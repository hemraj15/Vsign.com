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


-- Dumping structure for table printkaari_db.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table printkaari_db.role: ~3 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `description`, `created_by`, `date_created`, `date_updated`, `last_modified_by`, `status`) VALUES
	(1, 'ROLE_ADMIN', 'ADMIN', 'Hemraj', '2016-11-02 16:52:34', '2017-02-17 03:27:22', 'Hemraj', 'ACTIVE'),
	(2, 'ROLE_EMPLOYEE', 'EMPLOYEE', 'Hemraj', '2016-11-02 16:52:34', '2016-11-02 16:52:36', 'Hemraj', 'ACTIVE'),
	(3, 'ROLE_CUSTOMER', 'CUSTOMER', 'Hemraj', '2016-11-02 16:52:34', '2017-02-23 04:45:13', 'Hemraj', 'ACTIVE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
