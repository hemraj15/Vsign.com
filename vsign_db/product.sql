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


-- Dumping structure for table printkaari_db.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `product_catagory` bigint(20) DEFAULT NULL,
  `sampleFileId_id` bigint(20) DEFAULT NULL,
  `productCode` varchar(255) DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'ACTIVE',
  `favourite` bit(1) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKajjvty7byyaa847ntsseet3u9` (`product_catagory`),
  KEY `FKjkejyovn5iqfr5m4g8x3p10k8` (`sampleFileId_id`),
  CONSTRAINT `FKajjvty7byyaa847ntsseet3u9` FOREIGN KEY (`product_catagory`) REFERENCES `product_catagory` (`id`),
  CONSTRAINT `FKjkejyovn5iqfr5m4g8x3p10k8` FOREIGN KEY (`sampleFileId_id`) REFERENCES `product_sample_file` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Dumping data for table printkaari_db.products: ~27 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`, `name`, `product_catagory`, `sampleFileId_id`, `productCode`, `status`, `favourite`, `created_by`, `date_created`, `date_updated`, `last_modified_by`, `description`) VALUES
	(1, 'Minor/Major Project', 1, NULL, 'COLLEGE_PROJECTS', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(2, 'Priscription Pads', 2, NULL, 'HS_PRISCRIPTION_PAD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(3, 'Business Card', 2, NULL, 'HS_BUSINESS_CARD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(4, 'Folder Files', 2, NULL, 'HS_FOLDER_FILE', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(5, 'Pamphlets', 2, NULL, 'HS_PAMPHLET', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(6, 'Referral Pads', 2, NULL, 'HS_REFERRAL_PAD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(7, 'Envelop', 2, NULL, 'HS_ENVELOP', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(8, 'Broucher', 2, NULL, 'HS_BROUCHER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(9, 'OPD Register', 2, NULL, 'HS_OPD_REGISTER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(10, 'Tea-Shirt', 3, NULL, 'CUSTOM_T_SHIRT', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(11, 'Bags', 3, NULL, 'CUSTOM_BAG', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(12, 'Cups', 3, NULL, 'CUSTOM_CUP', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(13, 'Posters', 4, NULL, 'MKTNG_POSTER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(14, 'Multi Color Pamphlets', 4, NULL, 'MKTNG_MULYI_CLR_PAMPHLET', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(15, 'Leaf lets', 4, NULL, 'MKTNG_LEAF_LET', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(16, 'Broucher', 4, NULL, 'MKTNG_BROUCHER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(17, 'Glow Shine Boards', 4, NULL, 'MKTNG_GLOW_SHINE_BOARD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(18, 'Letter Heads', 5, 4, 'OFFICE_LETTER_HEAD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(19, 'Business Card', 5, NULL, 'OFFICE_BUSINESS_CARD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(20, 'Brouchers', 5, NULL, 'OFFICE_BROUCHER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(21, 'Envelopes', 5, NULL, 'OFFICE_ENVELOP', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(22, 'Receipt Books', 5, NULL, 'OFFICE_RECEIPT_BOOK', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(23, 'Multi Color Files', 5, NULL, 'OFFICE_MULTI_COLOR_FILE', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(24, 'Folders', 5, NULL, 'OFFICE_FOLDER', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(25, 'I-Cards', 5, NULL, 'OFFICE_I_CARD', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(26, 'Hard Binding', 1, NULL, 'HARD_BINDING', 'ACTIVE', b'0', 'Hemraj', '2017-01-11 20:06:29', '2017-01-11 20:06:28', 'Hemraj', NULL),
	(27, 'Spiral Binding', 1, NULL, 'SPIRAL_BINDING', 'ACTIVE', b'0', 'Hemraj', '2017-03-11 21:21:05', '2017-03-11 21:21:09', NULL, NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
