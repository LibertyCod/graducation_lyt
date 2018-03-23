/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.11-log : Database - graduation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`graduation` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `graduation`;

/*Table structure for table `room_order` */

DROP TABLE IF EXISTS `room_order`;

CREATE TABLE `room_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(60) NOT NULL,
  `room_name` varchar(100) DEFAULT '',
  `room_num` tinyint(5) DEFAULT NULL,
  `room_price` double DEFAULT NULL,
  `room_total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `room_order` */

LOCK TABLES `room_order` WRITE;

insert  into `room_order`(`id`,`uuid`,`room_name`,`room_num`,`room_price`,`room_total_price`) values (1,'4EE9E7F47DA141BA8E4B0CA099BDAB2C','da',1,1,1),(2,'0D24ED945D484E49A08B0F50F0E69360','da',1,1.2,1.2);

UNLOCK TABLES;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(60) DEFAULT NULL COMMENT '关联外键',
  `email` varchar(100) NOT NULL COMMENT '邮箱，登陆使用',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `cellphone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `identity_card` varchar(50) NOT NULL DEFAULT '' COMMENT '身份证',
  `address` varchar(300) NOT NULL DEFAULT '' COMMENT '住址',
  `create_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_datetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`uuid`,`email`,`password`,`cellphone`,`identity_card`,`address`,`create_datetime`,`update_datetime`) values (1,'45678912','490543154@qq.com','1234','','','','2018-03-23 14:28:18','0000-00-00 00:00:00');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
