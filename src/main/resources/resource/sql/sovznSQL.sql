/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.32-log : Database - sovzn
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sovzn` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `sovzn`;

/*Table structure for table `car` */

DROP TABLE IF EXISTS `car`;

CREATE TABLE `car` (
  `cid` int(4) NOT NULL AUTO_INCREMENT COMMENT '车辆id',
  `cnumber` varchar(20) NOT NULL COMMENT '车牌号',
  `ctype` varchar(20) DEFAULT NULL COMMENT '车型',
  `coutNum` int(4) NOT NULL COMMENT '出车次数',
  `cage` int(2) DEFAULT NULL COMMENT '车龄',
  `seatnum` int(2) DEFAULT NULL COMMENT '座位数',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `cseries` varchar(20) DEFAULT NULL COMMENT '车系',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `car` */

insert  into `car`(`cid`,`cnumber`,`ctype`,`coutNum`,`cage`,`seatnum`,`state`,`cseries`) values (1,'津M043L','混动',52,5,40,1,'比亚迪'),(2,'津M998','电动',98,1,40,1,'比亚迪'),(3,'津A2578','电动',582,1,20,0,'宇通'),(4,'津C486','电动',49,1,23,0,'宇通'),(5,'津M043L','电动',255,1,6,0,'特斯拉'),(6,'津H043L','电动',100,1,22,0,'特斯拉'),(7,'津A0888','混动',422,2,22,0,'比亚迪'),(8,'津A9999','混动',133,2,33,0,'比亚迪'),(9,'津A6666','混动',15,1,25,0,'宇通');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `nid` int(4) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `createusername` varchar(20) NOT NULL COMMENT '创建者登录名',
  `createname` varchar(20) NOT NULL COMMENT '创建者姓名',
  `received` int(4) NOT NULL DEFAULT '0' COMMENT '被阅次数',
  `content` varchar(200) NOT NULL COMMENT '通知内容',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

insert  into `notice`(`nid`,`createusername`,`createname`,`received`,`content`) values (8,'shiyaochang','师耀昌',9,'全部比亚迪车型将于下周统一进行维修校验'),(9,'shiyaochang','师耀昌',7,'平台App版将于5月份正式上线'),(10,'shiyaochang','师耀昌',5,'公司比亚迪系车辆优惠活动下月结束'),(11,'shiyaochang','师耀昌',2,'由于公司最近租车人数众多，若有租车用户车意向，请提前预约\r\n'),(12,'shiyaochang','师耀昌',3,'系统将于6.4---6.6进行维护'),(13,'shiyaochang','师耀昌',2,'系统微信小程序版将于下月上线，可以给用户带来更加快捷方便的体验\r\n'),(15,'fuzhuguanliyuan','小辅',1,'这是一条辅助管理员发送的通知'),(16,'liuchengyu','刘承宇',4,'这是刘承宇发布的一条通知');

/*Table structure for table `perm` */

DROP TABLE IF EXISTS `perm`;

CREATE TABLE `perm` (
  `pid` int(4) NOT NULL COMMENT '权限id',
  `perms` varchar(20) NOT NULL COMMENT '具体权限',
  `rid` int(4) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `perm` */

insert  into `perm`(`pid`,`perms`,`rid`) values (1,'User:*',1),(2,'admin',1),(4,'User:listadduser',2),(5,'Car:sent',5),(6,'Notice:sent',2),(7,'Car:ginfo',5);

/*Table structure for table `pnotice` */

DROP TABLE IF EXISTS `pnotice`;

CREATE TABLE `pnotice` (
  `pid` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `id` int(4) DEFAULT NULL COMMENT '用户id',
  `nid` int(4) DEFAULT NULL COMMENT '通知id',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pnotice` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(4) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rname` varchar(20) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`rid`,`rname`) values (1,'管理员'),(2,'辅助管理员'),(3,'普通用户'),(4,'司机'),(5,'调度员');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '登录名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(12) DEFAULT NULL COMMENT '电话',
  `company` varchar(30) DEFAULT NULL COMMENT '单位信息',
  `rid` int(2) DEFAULT NULL COMMENT '角色id',
  `states` int(2) DEFAULT '1' COMMENT '用户状态，1表示正常，2表示被注销',
  `yewustates` int(2) DEFAULT '1' COMMENT '业务状态，1表示没有业务，2表示有正在进行的业务，3表示已完成所有业务',
  `strDate` varchar(10) DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`name`,`sex`,`phone`,`company`,`rid`,`states`,`yewustates`,`strDate`) values (1,'shiyaochang','a3f930b4ce4153fa32ac9a301f5fd4ba','师耀昌','男','18522375834','系统平台',1,1,1,'1998-01-16'),(2,'zhangfei','a3f930b4ce4153fa32ac9a301f5fd4ba','张飞','男','18522375834','天津市大学软件学院',5,1,1,'1991-09-02'),(3,'liuchengyu','a3f930b4ce4153fa32ac9a301f5fd4ba','刘承宇','女','17797516564','天津市大学软件学院',3,1,1,'1995-09-02'),(4,'jiangyang','a3f930b4ce4153fa32ac9a301f5fd4ba','江扬','男','18293362470','系统平台',5,1,1,'1997-03-12'),(5,'newnew','a3f930b4ce4153fa32ac9a301f5fd4ba','小欣','女','13993365848','天津市大学软件学院',4,1,1,'1997-03-12'),(6,'zhangsanfeng','a3f930b4ce4153fa32ac9a301f5fd4ba','张三丰','男','13993368035','中国人寿',3,1,1,'1997-03-11'),(7,'niudan','a3f930b4ce4153fa32ac9a301f5fd4ba','牛蛋','男','18522375834','系统平台',2,2,2,'1999-09-02'),(8,'putongyonghu','a3f930b4ce4153fa32ac9a301f5fd4ba','小普','男','18522375834','中国农行',3,1,2,'1998-09-02'),(9,'fuzhuguanliyuan','a3f930b4ce4153fa32ac9a301f5fd4ba','小辅','女','15524853647','系统平台',2,1,1,'1991-09-02'),(10,'siji','a3f930b4ce4153fa32ac9a301f5fd4ba','小司','男','17797516564','天津市大学软件学院',4,1,2,'1992-09-02'),(11,'diaoduyuan','a3f930b4ce4153fa32ac9a301f5fd4ba','小调','女','13993369025','系统平台',5,1,1,'1993-09-02'),(12,'liuxi','a3f930b4ce4153fa32ac9a301f5fd4ba','刘希','女','18533694785','中国建行',3,1,1,'1994-09-02'),(13,'xiaoming','a3f930b4ce4153fa32ac9a301f5fd4ba','小明','男','13997765804','中国建行',3,1,1,'1993-09-02'),(14,'xiaolan','a3f930b4ce4153fa32ac9a301f5fd4ba','小兰','女','15393573497','中国建行',3,1,1,'1987-09-02'),(15,'zhanglei','a3f930b4ce4153fa32ac9a301f5fd4ba','张磊','男','15539875468','中国农行',3,1,1,'1988-09-02'),(16,'xiaowang','a3f930b4ce4153fa32ac9a301f5fd4ba','小旺','男','18793811168','中国农行',3,1,1,'1987-09-02'),(17,'liuchen','a3f930b4ce4153fa32ac9a301f5fd4ba','刘晨','男','18793811168','中国农行',2,0,1,'1960-09-02'),(18,'wangzhi','a3f930b4ce4153fa32ac9a301f5fd4ba','王志','男','13993365848','中国农行',3,1,1,'1997-09-02'),(19,'wangzhi1','a3f930b4ce4153fa32ac9a301f5fd4ba','王志','男','13883365848','中国农行',4,2,2,'1977-09-02'),(20,'liusisi','a3f930b4ce4153fa32ac9a301f5fd4ba','刘思思','女','18522368945','天津市大学软件学院',3,1,1,'1999-12-24'),(21,'wanglei','a3f930b4ce4153fa32ac9a301f5fd4ba','王磊','男','18522368945','天津市大学软件学院',3,1,1,'1999-12-10'),(22,'siii','a3f930b4ce4153fa32ac9a301f5fd4ba','sss','男','18522368945','天津市大学软件学院',3,1,1,'1999-12-24');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
