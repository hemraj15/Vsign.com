-- --------------------------------------------------------
-- Host:                         162.220.61.86
-- Server version:               5.6.35 - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for printkaari_db
CREATE DATABASE IF NOT EXISTS `printkaari_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `printkaari_db`;


-- Dumping structure for table printkaari_db.product_catagory
CREATE TABLE IF NOT EXISTS `product_catagory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'ACTIVE',
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table printkaari_db.product_catagory: ~5 rows (approximately)
/*!40000 ALTER TABLE `product_catagory` DISABLE KEYS */;
INSERT INTO `product_catagory` (`id`, `name`, `status`, `created_by`, `date_created`, `date_updated`, `last_modified_by`, `description`) VALUES
	(1, 'College Projects', 'ACTIVE', 'Hemraj', '2017-01-11 20:05:50', '2017-01-11 20:05:51', 'Hemraj', NULL),
	(2, 'Hospital/Doctors Essentials', 'ACTIVE', 'Hemraj', '2017-01-11 20:05:50', '2017-01-11 20:05:51', 'Hemraj', NULL),
	(3, 'Customized Product', 'ACTIVE', 'Hemraj', '2017-01-11 20:05:50', '2017-01-11 20:05:51', 'Hemraj', NULL),
	(4, 'Marketing Essentials', 'ACTIVE', 'Hemraj', '2017-01-11 20:05:50', '2017-01-11 20:05:51', 'Hemraj', NULL),
	(5, 'Office Essentials', 'ACTIVE', 'Hemraj', '2017-01-11 20:05:50', '2017-01-11 20:05:51', 'Hemraj', NULL);
/*!40000 ALTER TABLE `product_catagory` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
