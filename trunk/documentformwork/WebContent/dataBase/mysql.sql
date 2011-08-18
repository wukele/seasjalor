/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.1.39-community : Database - seasjalor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seasjalor` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `seasjalor`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `STREET_NUMBER` int(11) DEFAULT NULL,
  `STREET_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `address` */

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `DOCUMENT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '??。ID',
  `DOCUMENT_NAME` varchar(255) NOT NULL COMMENT '??。??О',
  `LINK` varchar(200) DEFAULT NULL COMMENT '??。?炬?',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '??。绫诲?',
  `SIZE` int(11) DEFAULT NULL COMMENT '??。澶у?',
  `TAGS` varchar(255) DEFAULT NULL COMMENT '??。???',
  `PARENT_ID` int(11) NOT NULL COMMENT '?惰??股D',
  `ISLEAF` char(1) NOT NULL COMMENT '???涓哄?瀛????0:?Μ1:?',
  `STATUS` char(1) NOT NULL DEFAULT '1' COMMENT '?舵?',
  `DELFLAG` char(1) NOT NULL DEFAULT '1' COMMENT '???Flag 1锛????? 2锛?凡???',
  `CREATE_USER` varchar(20) NOT NULL COMMENT '?诲??',
  `CREATE_TIME` datetime NOT NULL COMMENT '?诲??堕?',
  `UPDATE_USER` varchar(20) NOT NULL COMMENT '?存??',
  `UPDATE_TIME` datetime NOT NULL COMMENT '?存??堕?',
  PRIMARY KEY (`DOCUMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='??。绠＄?';

/*Data for the table `document` */

insert  into `document`(`DOCUMENT_ID`,`DOCUMENT_NAME`,`LINK`,`TYPE`,`SIZE`,`TAGS`,`PARENT_ID`,`ISLEAF`,`STATUS`,`DELFLAG`,`CREATE_USER`,`CREATE_TIME`,`UPDATE_USER`,`UPDATE_TIME`) values (1,'文件',NULL,NULL,NULL,NULL,0,'0','1','1','admin','2011-08-05 13:22:13','admin','2011-08-05 13:22:13'),(2,'Anynote1','e7ea93cd-d848-4d3f-99af-f40cd9acb0bb.zip','zip',14697823,'',0,'1','1','1','admin','2011-08-05 13:22:34','admin','2011-08-05 13:22:34'),(3,'1D_dy','2bef15c9-6ba4-4b39-ba40-632192333f27.res','res',757973,'',1,'1','1','1','admin','2011-08-05 13:23:02','admin','2011-08-05 13:23:02'),(4,'2D_dense_dy','d5c7dda0-6026-4811-b0a4-0f2177ad06bf.res','res',13687059,'',1,'1','1','1','admin','2011-08-05 13:23:03','admin','2011-08-05 13:23:03'),(5,'file','08187394-a105-4e42-af37-107544dce8be.sub','sub',65536,'',1,'1','1','1','admin','2011-08-05 13:23:03','admin','2011-08-05 13:23:03'),(6,'HELLO','44ad8ba1-c85b-4129-bc2e-95ec0cc146b4.f90','f90',877,'',1,'1','1','1','admin','2011-08-05 13:23:04','admin','2011-08-05 13:23:04'),(7,'HELLO','aeffc09a-c182-4783-a19f-632d882ece24.bak','bak',877,'',1,'1','1','1','admin','2011-08-05 13:23:04','admin','2011-08-05 13:23:04'),(8,'ok','48c21998-a7e6-4054-a6bb-9e6427dbe61d.txt','txt',158,'',1,'1','1','1','admin','2011-08-05 13:23:04','admin','2011-08-05 13:23:04'),(9,'setup','64a2b774-9a73-4d44-adad-72ddd68f81f4.exe','exe',460800,'',1,'1','1','1','admin','2011-08-05 13:23:04','admin','2011-08-05 13:23:04'),(10,'Setup','1a94dcb5-db80-4675-a419-c3e8892ee40c.msi','msi',784384,'',1,'1','1','1','admin','2011-08-05 13:23:04','admin','2011-08-05 13:23:04'),(11,'small_2D_dy','0f3adb30-1e65-4023-ab74-f68509164b04.res','res',13687059,'',1,'1','1','1','admin','2011-08-05 13:23:10','admin','2011-08-05 13:23:10'),(12,'test','54519bca-866c-4deb-a1b7-325353f3b190.f90','f90',800,'',1,'1','1','1','admin','2011-08-05 13:23:10','admin','2011-08-05 13:23:10'),(13,'test','20586d04-d435-438e-b9dd-4f3903110864.bak','bak',800,'',1,'1','1','1','admin','2011-08-05 13:23:11','admin','2011-08-05 13:23:11'),(14,'1D_dy','94663a0a-b3fc-4175-8492-bc8437914ee0.res','res',757973,'',1,'1','1','1','admin','2011-08-05 13:23:56','admin','2011-08-05 13:23:56'),(15,'2D_dense_dy','ed593235-d020-4e39-8824-14dc9fe97731.res','res',13687059,'',1,'1','1','1','admin','2011-08-05 13:23:57','admin','2011-08-05 13:23:57'),(16,'file','614efcf3-75c7-4be3-bd9a-9d9f7cbc97d7.sub','sub',65536,'',1,'1','1','1','admin','2011-08-05 13:23:57','admin','2011-08-05 13:23:57'),(17,'HELLO','5825dffa-2383-4628-aeca-602e8c8a1187.f90','f90',877,'',1,'1','1','1','admin','2011-08-05 13:23:58','admin','2011-08-05 13:23:58'),(18,'HELLO','e3fb7027-3740-4ccb-978d-9e783284fc6f.bak','bak',877,'',1,'1','1','1','admin','2011-08-05 13:23:58','admin','2011-08-05 13:23:58'),(19,'ok','639b3f6c-e98d-455c-8000-468548988778.txt','txt',158,'',1,'1','1','1','admin','2011-08-05 13:23:58','admin','2011-08-05 13:23:58'),(20,'setup','7bf707c9-d7fe-487e-854c-cf634270d3ce.exe','exe',460800,'',1,'1','1','1','admin','2011-08-05 13:23:58','admin','2011-08-05 13:23:58'),(21,'Setup','6bcfa2af-7f70-4e89-a455-857039d5bd4a.msi','msi',784384,'',1,'1','1','1','admin','2011-08-05 13:23:58','admin','2011-08-05 13:23:58'),(22,'small_2D_dy','2f152703-2c47-4301-9384-998a21370e66.res','res',13687059,'',1,'1','1','1','admin','2011-08-05 13:23:59','admin','2011-08-05 13:23:59'),(23,'test','fadcab69-7af0-495b-9272-e5aecf93a5ab.f90','f90',800,'',1,'1','1','1','admin','2011-08-05 13:23:59','admin','2011-08-05 13:23:59'),(24,'test','5741df21-dc32-4c13-8693-3008ef6a89c2.bak','bak',800,'',1,'1','1','1','admin','2011-08-05 13:24:00','admin','2011-08-05 13:24:00'),(25,'汽车零件',NULL,NULL,NULL,NULL,0,'0','1','1','admin','2011-08-05 13:25:11','admin','2011-08-05 13:25:11'),(26,'8-6',NULL,NULL,NULL,NULL,25,'0','1','1','admin','2011-08-05 13:25:41','admin','2011-08-05 13:25:41'),(27,'1D_dy','5ff7d130-8a11-4dee-b27c-f90544adce11.res','res',757973,'',26,'1','1','1','admin','2011-08-05 13:26:05','admin','2011-08-05 13:26:05'),(28,'2D_dense_dy','261d9383-ed3e-49cc-a0d6-fb43049e883b.res','res',13687059,'',26,'1','1','1','admin','2011-08-05 13:26:06','admin','2011-08-05 13:26:06'),(29,'file','24e18c9f-e809-42d7-8c75-6a1f4e48ec1c.sub','sub',65536,'',26,'1','1','1','admin','2011-08-05 13:26:06','admin','2011-08-05 13:26:06'),(30,'HELLO','97741630-fa54-417f-acba-6737e685711f.f90','f90',877,'',26,'1','1','1','admin','2011-08-05 13:26:06','admin','2011-08-05 13:26:06'),(31,'HELLO','60760d3f-2275-41fb-9a6b-e9c627a34bc4.bak','bak',877,'',26,'1','1','1','admin','2011-08-05 13:26:07','admin','2011-08-05 13:26:07'),(32,'ok','ced7fe53-daa1-473f-9f61-972ed8460e71.txt','txt',158,'',26,'1','1','1','admin','2011-08-05 13:26:07','admin','2011-08-05 13:26:07'),(33,'setup','00c51561-adc8-45ab-a5d6-d1e59766a742.exe','exe',460800,'',26,'1','1','1','admin','2011-08-05 13:26:07','admin','2011-08-05 13:26:07'),(34,'Setup','360a4a28-7b7f-4f3b-a726-20b2315270ce.msi','msi',784384,'',26,'1','1','1','admin','2011-08-05 13:26:07','admin','2011-08-05 13:26:07'),(35,'small_2D_dy','a138ad6b-3eb4-44aa-b4a0-0ef7dfb17727.res','res',13687059,'',26,'1','1','1','admin','2011-08-05 13:26:08','admin','2011-08-05 13:26:08'),(36,'test','109eac44-263f-469c-99db-57de880385c2.f90','f90',800,'',26,'1','1','1','admin','2011-08-05 13:26:08','admin','2011-08-05 13:26:08'),(37,'test','3c19b743-ebc3-4c19-9b0c-3f04c1145fbe.bak','bak',800,'',26,'1','1','1','admin','2011-08-05 13:26:08','admin','2011-08-05 13:26:08'),(38,'home','a1ed76b4-59f2-4015-a78a-c65857fe8008.png','png',21919,'',0,'1','1','1','admin','2011-08-17 00:30:20','admin','2011-08-17 00:30:20'),(39,'logining','d6247fa5-a431-4c30-9786-3d2d130765d3.png','png',66284,'',0,'1','1','1','admin','2011-08-17 00:30:21','admin','2011-08-17 00:30:21');

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LAST_NAME` varchar(50) DEFAULT NULL,
  `FIRST_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `person` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gb2312;

/*Data for the table `t_user` */

insert  into `t_user`(`name`,`password`,`id`) values ('xiaohe','xiaohe',4),('xiaohe','xiaohe',5),('xiaohe','xiaohe',6),('xiaohe','xiaohe',7),('xiaohe','xiaohe',8),('xiaohe','xiaohe',9),('xiaohe','xiaohe',10),('xiaohe','xiaohe',11),('xiaohe','xiaohe',12);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
