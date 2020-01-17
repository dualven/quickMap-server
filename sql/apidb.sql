-- MySQL dump 10.13  Distrib 5.7.27, for linux-glibc2.12 (x86_64)
--
-- Host: 10.60.2.175    Database: apidb
-- ------------------------------------------------------
-- Server version	5.7.27-log

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
-- Table structure for table `BeautyFile`
--

DROP TABLE IF EXISTS `BeautyFile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BeautyFile` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `filename` varchar(255) DEFAULT NULL,
  `group` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `image_src` varchar(128) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `author` int(255) DEFAULT NULL,
  `isdel` tinyint(2) unsigned NOT NULL DEFAULT '1',
  `thumbImagePath` varchar(255) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FILENAME` (`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapters` (
  `novel_ID` int(10) DEFAULT NULL,
  `chapter_ID` int(10) DEFAULT NULL,
  `chapter_Url` varchar(255) DEFAULT NULL,
  `chapter_Content` text,
  `chapter_Title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file_info`
--

DROP TABLE IF EXISTS `file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file_info` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `filename` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `author` int(255) DEFAULT NULL,
  `isdel` tinyint(2) unsigned NOT NULL DEFAULT '1',
  `thumbImagePath` varchar(255) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FILENAME` (`filename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `novels`
--

DROP TABLE IF EXISTS `novels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `novels` (
  `novel_ID` bigint(20) DEFAULT NULL,
  `novel_Url` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `novel_Author` varchar(100) DEFAULT NULL,
  `novel_Name` varchar(100) DEFAULT NULL,
  `novel_CoverURL` varchar(255) DEFAULT NULL,
  `novel_Intro` text,
  `novel_Isfinished` varchar(255) DEFAULT NULL,
  `novel_Wordscount` int(10) DEFAULT NULL,
  `novel_LatestUpdateTime` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `novel_Type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` varchar(36) NOT NULL,
  `login_name` varchar(32) NOT NULL,
  `password` varchar(45) NOT NULL,
  `salt` varchar(36) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `is_del` tinyint(4) DEFAULT '1',
  `reset_password_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_LOGIN_NAME` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-17 17:11:02
