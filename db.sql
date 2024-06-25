-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurant_business_system
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `id_account` varchar(10) NOT NULL,
  `id_restaurant` varchar(10) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(10) NOT NULL,
  `status` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthdate` varchar(255) DEFAULT NULL,
  `avt` text,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_account`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('3HQ5954YN0','0UDUVYE7SM','abcde','$2a$12$hdXs14noGSxW3e3TNWtUEOjqUsIdMBBQNu.qG9Wz69.7.cWxd9qYa','ma','active','0981793201','A',NULL,NULL,'2024-06-10 17:26:29','2024-06-12 13:43:28'),('P3B2H66I1M','ZDZ749Q9G4','abc','$2a$12$hdXs14noGSxW3e3TNWtUEOjqUsIdMBBQNu.qG9Wz69.7.cWxd9qYa','manager',NULL,NULL,NULL,NULL,'https://res.cloudinary.com/dcweof28t/image/upload/v1717145213/oprivsmvzyzhhieekktr.png','2024-04-28 13:58:09','2024-06-12 13:43:28'),('VQ6WABOR10','','abcd','123','user',NULL,NULL,NULL,NULL,NULL,'2024-04-29 14:08:13','2024-04-29 14:08:13');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bills` (
  `id_bill` varchar(10) NOT NULL,
  `id_table` varchar(10) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_bill`),
  KEY `id_table` (`id_table`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`id_table`) REFERENCES `tables` (`id_table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` VALUES ('FOEGKYGU7J','98AFF73GPT','Close','2024-04-30 12:27:39','2024-06-12 16:28:05'),('JMDLSN48CU','98AFF73GPT','Close','2024-04-30 12:27:23','2024-04-30 12:27:36'),('MPFUWUYBGP','98AFF73GPT','Close','2024-04-30 12:25:57','2024-04-30 12:26:30'),('S11LTDD10L','98AFF73GPT','Open','2024-06-12 16:28:12','2024-06-12 16:28:12');
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foods`
--

DROP TABLE IF EXISTS `foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foods` (
  `id_food` varchar(10) NOT NULL,
  `id_menu` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `image` text NOT NULL,
  `price` float NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_food`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `foods_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `menus` (`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foods`
--

LOCK TABLES `foods` WRITE;
/*!40000 ALTER TABLE `foods` DISABLE KEYS */;
INSERT INTO `foods` VALUES ('OIV0TS6836','T2CCWHNW87','Gà rán','http://res.cloudinary.com/dcweof28t/image/upload/v1717045594/zp7klmys9dityebo10n1.png',160000,'2024-04-29 15:28:21','2024-05-30 12:17:03'),('UPQD629O5T','EQ06P6RO30','a','https://res.cloudinary.com/dcweof28t/image/upload/v1717146254/lrt9b5xioe1wskbizypm.png',0,'2024-06-10 18:04:50','2024-06-10 18:04:50');
/*!40000 ALTER TABLE `foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menus` (
  `id_menu` varchar(10) NOT NULL,
  `id_restaurant` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(45) DEFAULT 'Active',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_menu`),
  KEY `id_restaurant` (`id_restaurant`),
  CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES ('5S21ONQ2BC','0UDUVYE7SM','A','inactive','2024-06-01 09:16:57','2024-06-01 09:17:10'),('91BFAKN5VM','0UDUVYE7SM','VIP','Active','2024-06-01 08:19:43','2024-06-01 09:37:16'),('E0RIIPLOHU','0UDUVYE7SM','A','inactive','2024-06-01 09:38:08','2024-06-01 09:40:03'),('EQ06P6RO30','ZDZ749Q9G4','VIP','active','2024-06-10 18:00:24','2024-06-10 18:00:24'),('T2CCWHNW87','0UDUVYE7SM','D','active','2024-04-29 14:31:49','2024-06-09 09:31:30'),('TT6YMX6DMF','0UDUVYE7SM','VIP 2','active','2024-06-02 13:55:07','2024-06-02 13:55:07');
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id_order` varchar(10) NOT NULL,
  `id_bill` varchar(10) NOT NULL,
  `id_food` varchar(10) NOT NULL,
  `quantity` int NOT NULL,
  `status` varchar(255) NOT NULL,
  `payment` varchar(255) DEFAULT 'Not Paid',
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_order`),
  KEY `id_bill` (`id_bill`),
  KEY `id_food` (`id_food`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_bill`) REFERENCES `bills` (`id_bill`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`id_food`) REFERENCES `foods` (`id_food`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('UEOJZNYGSX','S11LTDD10L','OIV0TS6836',3,'Processing','Not Paid',NULL,'2024-06-13 15:58:48','2024-06-13 16:02:43');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id_payment` varchar(10) NOT NULL,
  `id_restaurant` varchar(10) NOT NULL,
  `partner_code` text,
  `access_key` text,
  `secret_key` text,
  PRIMARY KEY (`id_payment`),
  KEY `id_restaurant` (`id_restaurant`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES ('0UDUVYE7SM','0UDUVYE7SM','MOMO','F8BBA842ECF85','K951B6PE1waDMi640xX08PD3vg6EkVlz');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurants` (
  `id_restaurant` varchar(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES ('0UDUVYE7SM','a','active','2024-04-29 10:46:37','2024-04-29 10:46:37'),('WSJ370SFR9','b','active','2024-04-29 10:48:28','2024-04-29 10:48:28'),('ZDZ749Q9G4','A','active','2024-06-10 17:38:49','2024-06-10 17:38:49');
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tables` (
  `id_table` varchar(10) NOT NULL,
  `id_restaurant` varchar(10) NOT NULL,
  `name_table` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_table`),
  KEY `id_restaurant` (`id_restaurant`),
  CONSTRAINT `tables_ibfk_1` FOREIGN KEY (`id_restaurant`) REFERENCES `restaurants` (`id_restaurant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES ('3UOXSITD8M','ZDZ749Q9G4','Bàn 1','Available','2024-06-10 17:39:37','2024-06-10 17:39:37',NULL),('98AFF73GPT','0UDUVYE7SM','Mơ ước','Available','2024-04-30 09:40:10','2024-06-13 16:03:32','3TQIX3RZSG'),('M6BAP3T3ZT','0UDUVYE7SM','Bàn 1','Available','2024-05-28 09:08:11','2024-05-28 09:08:11',NULL);
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-13 20:08:40
