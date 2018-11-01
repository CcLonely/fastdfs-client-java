/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.41 : Database - db_file
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_file` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_file`;

/*Table structure for table `tb_file` */

DROP TABLE IF EXISTS `tb_file`;

CREATE TABLE `tb_file` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_file` */

insert  into `tb_file`(`id`,`project_name`,`file_name`,`type`,`path`) values 
(1,'测试','namei.jpg','jpg','group1/M00/00/00/wKhAgFvaoYGAFqP3AAfqZVr-3og040.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
