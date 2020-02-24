-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.22 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 achievement 的数据库结构
DROP DATABASE IF EXISTS `achievement`;
CREATE DATABASE IF NOT EXISTS `achievement` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `achievement`;

-- 导出  表 achievement.activity 结构
DROP TABLE IF EXISTS `activity`;
CREATE TABLE IF NOT EXISTS `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- 正在导出表  achievement.activity 的数据：0 rows
DELETE FROM `activity`;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` (`id`, `content`, `title`) VALUES
	(1, 'hahahahhaahahaahah  one', 'one hahhahahahahahahahah'),
	(2, 'xixixiixixixixixixixixixixix  one', 'one xixixixiixixixixixixixixixixix');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;

-- 导出  表 achievement.punch 结构
DROP TABLE IF EXISTS `punch`;
CREATE TABLE IF NOT EXISTS `punch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcn2vnybcj5jcpyw52361wjvg3` (`activity_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  achievement.punch 的数据：0 rows
DELETE FROM `punch`;
/*!40000 ALTER TABLE `punch` DISABLE KEYS */;
/*!40000 ALTER TABLE `punch` ENABLE KEYS */;

-- 导出  表 achievement.sign 结构
DROP TABLE IF EXISTS `sign`;
CREATE TABLE IF NOT EXISTS `sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrc2eaaplhbqgh1yhj6npk1d0q` (`activity_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  achievement.sign 的数据：0 rows
DELETE FROM `sign`;
/*!40000 ALTER TABLE `sign` DISABLE KEYS */;
/*!40000 ALTER TABLE `sign` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
