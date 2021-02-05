-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: item_tracker
-- ------------------------------------------------------
-- Server version	5.7.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT '',
  `category` varchar(70) DEFAULT 'Admin',
  `phone` varchar(70) DEFAULT '',
  `national_id` varchar(70) DEFAULT '',
  `sector` varchar(70) DEFAULT '',
  `password` text,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `done_by` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Superadmin','Superadmin','0726183049','0','','MTIzNDU=','2021-01-31 07:51:28','2021-01-31 07:51:28',0),(2,'Murenzi Jameson','Admin','0789456123','1121212110','Kabarondongore','cGFzc3dvcmQ=','2021-01-31 08:06:54','2021-01-31 08:05:43',1),(4,'Murenzi','Admin','0789456123','1121212112','Kabarondo','MTIzNDU=','2021-01-31 09:37:10','2021-01-31 09:37:10',1),(5,'Murenzi','Admin','0789456123','1121212112','Kabarondo','MTIzNDU=','2021-01-31 13:00:44','2021-01-31 13:00:44',1),(6,'Mannini',NULL,'1234567890',NULL,'Karara','MTIzNDU=','2021-01-31 13:15:49','2021-01-31 13:15:49',1),(7,'Murindwa Augustin',NULL,'0789526431',NULL,'Rukinda','MTIzNDU=','2021-01-31 13:17:38','2021-01-31 13:17:38',1),(8,'Didier',NULL,'0788940718',NULL,'Musanze','MTIzNDU=','2021-01-31 13:19:13','2021-01-31 13:19:13',1),(9,'','Catgrgor','',NULL,'','','2021-01-31 13:21:24','2021-01-31 13:21:24',1),(10,'Manzi James Roger','Admin','07345626362',NULL,'Rukoma','MTIzNDU=','2021-01-31 13:22:01','2021-01-31 13:22:01',1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `declared_documents`
--

DROP TABLE IF EXISTS `declared_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `declared_documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resident` int(11) NOT NULL,
  `document_type` int(11) DEFAULT NULL,
  `document_id` varchar(50) DEFAULT NULL,
  `status` varchar(70) DEFAULT 'pending',
  `declared_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `added_by` int(11) DEFAULT '0',
  `given_by` int(11) DEFAULT '0',
  `given_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `resident` (`resident`),
  KEY `document_type` (`document_type`),
  CONSTRAINT `declared_documents_ibfk_1` FOREIGN KEY (`resident`) REFERENCES `residents` (`id`),
  CONSTRAINT `declared_documents_ibfk_2` FOREIGN KEY (`document_type`) REFERENCES `document_types` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `declared_documents`
--

LOCK TABLES `declared_documents` WRITE;
/*!40000 ALTER TABLE `declared_documents` DISABLE KEYS */;
INSERT INTO `declared_documents` VALUES (1,1,1,'12321','pending','2021-01-31 08:41:49',0,0,'2021-01-31 08:41:49'),(2,1,1,'12321','deleted','2021-01-31 08:42:10',0,0,'2021-01-31 08:42:10'),(3,5,1,'16362627736372','pending','2021-01-31 14:11:49',0,0,'2021-01-31 14:11:49');
/*!40000 ALTER TABLE `declared_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_types`
--

DROP TABLE IF EXISTS `document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_types` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_name` varchar(70) NOT NULL,
  `added_by` int(11) DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_types`
--

LOCK TABLES `document_types` WRITE;
/*!40000 ALTER TABLE `document_types` DISABLE KEYS */;
INSERT INTO `document_types` VALUES (1,'National identity card',0,'2021-01-31 08:19:53','2021-01-31 08:16:01'),(2,'Passport',0,'2021-01-31 08:20:52','2021-01-31 08:20:52'),(4,'Passport',0,'2021-01-31 12:26:32','2021-01-31 12:26:32');
/*!40000 ALTER TABLE `document_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residents`
--

DROP TABLE IF EXISTS `residents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `residents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) DEFAULT '',
  `phone` varchar(70) DEFAULT '',
  `gender` varchar(70) DEFAULT 'Male',
  `sector` varchar(70) DEFAULT '',
  `password` varchar(70) DEFAULT '',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residents`
--

LOCK TABLES `residents` WRITE;
/*!40000 ALTER TABLE `residents` DISABLE KEYS */;
INSERT INTO `residents` VALUES (1,'Allen','078039891','Male','Kabarondo','cGFzc3dvcmQ=','2021-01-31 08:34:24','2021-01-31 08:30:52'),(3,'Manzi Ksrenzi','0789456253','Male','Kabarore','MTIzNDU=','2021-01-31 12:20:36','2021-01-31 12:20:36'),(4,'Munezero Jean','0789635246','Male','Lavanyinya','MTIzNDU=','2021-01-31 12:22:27','2021-01-31 12:22:27'),(5,'Kabatiza Janes','0789465325','Male','kabanaa','MTIzNDU=','2021-01-31 13:58:31','2021-01-31 13:58:31');
/*!40000 ALTER TABLE `residents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submitted_documents`
--

DROP TABLE IF EXISTS `submitted_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submitted_documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `document_type` int(11) NOT NULL,
  `document_id` varchar(140) DEFAULT '',
  `document_picture` text,
  `other_description` text,
  `status` varchar(20) DEFAULT 'pending',
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `added_by` int(11) DEFAULT '0',
  `given_by` int(11) DEFAULT '0',
  `given_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `document_type` (`document_type`),
  CONSTRAINT `submitted_documents_ibfk_1` FOREIGN KEY (`document_type`) REFERENCES `document_types` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submitted_documents`
--

LOCK TABLES `submitted_documents` WRITE;
/*!40000 ALTER TABLE `submitted_documents` DISABLE KEYS */;
INSERT INTO `submitted_documents` VALUES (1,'Allen Mugisha',1,'12320','',NULL,'pending','2021-01-31 09:04:59',0,0,NULL),(2,'Kabatiza James',1,'16362627736372','',NULL,'pending','2021-01-31 14:17:27',NULL,0,NULL),(3,'Kabatiza James',1,'16362627736372','',NULL,'pending','2021-01-31 14:18:00',NULL,0,NULL),(4,'Tuyeisenge Gerard',1,'12345','',NULL,'pending','2021-02-05 10:11:12',10,0,NULL);
/*!40000 ALTER TABLE `submitted_documents` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-05 14:50:27
