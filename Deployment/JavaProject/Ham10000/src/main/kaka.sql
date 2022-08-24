/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.35-log : Database - kaka
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kaka` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kaka`;

/*Table structure for table `knowledge` */

DROP TABLE IF EXISTS `knowledge`;

CREATE TABLE `knowledge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `is_archived` int(11) DEFAULT NULL,
  `num_reviewed` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

/*Data for the table `knowledge` */

insert  into `knowledge`(`id`,`title`,`detail`,`description`,`url`,`image`,`video`,`date_created`,`is_archived`,`num_reviewed`,`menu_id`) values 
(5,'Trúc Chi Bolero - Tựa Cánh Bèo Trôi | Bolero Tình Yêu Hay Nhất','','         ','https://www.youtube.com/watch?v=o0xtwvM84WQ&list=RDo0xtwvM84WQ&start_radio=1&ab_channel=Tr%C3%BAcChiBolero','5-2021_11_07_13_18_25_0-1035.jpg',NULL,'2021-11-07 13:18:25',0,0,32),
(6,'Tình Tuổi Ô Mai (#TTOM) - Trúc Chi Bolero ','Người tình ơi anh vì sao ...','','https://www.youtube.com/watch?v=JVzJ7vfJBTU&list=RDo0xtwvM84WQ&index=15&ab_channel=Tr%C3%BAcChiBolero','6-2021_11_07_13_19_00_0-4925.PNG',NULL,'2021-11-07 13:19:00',0,0,32),
(7,'ANAK Remix 2','','','https://www.youtube.com/watch?v=yiicHpBHoKY&ab_channel=QuangMusic',NULL,NULL,'2021-11-07 13:25:59',0,0,35),
(21,'Backup','Backup web server chạy kaka','','',NULL,NULL,'2021-11-07 21:02:11',0,0,38),
(22,'Backup','Backup mysql chạy kaka','','',NULL,NULL,'2021-11-07 21:02:31',0,0,38),
(23,'Intellij Pojo generator không hỗ trợ MariaDB','Intellij Pojo generator không hỗ trợ MariaDB','','',NULL,NULL,'2021-11-07 21:08:53',0,0,39),
(24,'Nên là War vì còn upload, tải file','Nên là War vì còn upload, tải file','','',NULL,NULL,'2021-11-07 21:09:26',0,0,39),
(30,'Trai hư gái ngoan Phim thái lan','Trai hư gái ngoan Phim thái lan','<pre><code class=\"language-java\">\r\n\r\n</code></pre>\r\n                                                ','https://www.youtube.com/watch?v=vZ0cizsJhd0&list=PLmJb6HzOmUOBIKWkXb5sNTmBkrH6IhWok&index=10&ab_channel=PhimTV','34-2021_11_07_22_29_15_846-1963.jpg','30-2021_11_07_22_29_16_0-2690.05','2021-11-07 22:29:16',0,0,34),
(31,'adsf','asdf','asdf','sadf','40-2021_11_08_08_36_52_870-4156.jpg','31-2021_11_08_08_36_53_0-4834.06','2021-11-08 08:36:53',0,0,40),
(33,'@Value spring boot','@Value(\"${cdn_location}\")     String cdn_location;','<pre><code class=\"language-java\">\r\n\r\n</code></pre>\r\n                                                ','','33-2021_11_08_08_38_33_0-2880.jpg','33-2021_11_08_08_38_33_0-2701.06','2021-11-08 08:38:33',0,0,40),
(35,'asdf','asdf','asdf','asdf','40-2021_11_08_09_08_09_946-4207.jpg',NULL,'2021-11-08 09:08:10',0,0,40),
(37,'Bài 1','','','','43-2021_11_08_09_09_58_550-1155.jpg',NULL,'2021-11-08 09:09:59',0,0,43),
(38,'Bài 2','','','','43-2021_11_08_09_10_06_568-34.jpg',NULL,'2021-11-08 09:10:07',0,0,43),
(39,'Bài 3','','','','43-2021_11_08_09_10_14_533-4304.jpg',NULL,'2021-11-08 09:10:15',0,0,43),
(40,'Bài 4','','','','43-2021_11_08_09_10_36_507-3093.jpg',NULL,'2021-11-08 09:10:37',0,0,43),
(41,'Bài 5','','','','43-2021_11_08_09_10_42_552-817.jpg',NULL,'2021-11-08 09:10:43',0,0,43),
(42,'Bài 6','','','','43-2021_11_08_09_10_51_453-1384.jpg',NULL,'2021-11-08 09:10:51',1,0,43),
(51,'spring bôt adđ value','','<pre><code class=\"language-java\">\r\n    @Value(\"${file_upload_location}\")\r\n    String file_upload_location;\r\n</code></pre>\r\n                                                ','','51-2021_11_08_11_19_30_0-3519.jpg','51-2021_11_08_11_19_30_0-3882.06','2021-11-08 11:19:30',0,0,40),
(53,'tét properties ','','<pre><code class=\"language-properties\">\r\nspring.datasource.url=jdbc:mysql://localhost:3306/kaka\r\nspring.datasource.username=kaka\r\nspring.datasource.password=tunguyenaz1\r\nspring.jpa.hibernate.ddl-auto=update\r\nspring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver\r\n</code></pre>\r\n                                                ','','53-2021_11_08_11_26_29_0-4050.png',NULL,'2021-11-08 11:26:29',0,0,40),
(54,'test python','','<pre><code class=\"language-java\">\r\ndef fetch_housing_data(housing_url=HOUSING_URL, housing_path=HOUSING_PATH):\r\n    os.makedirs(housing_path, exist_ok=True)\r\n    tar_file = HOUSING_PATH + \"/housing.tgz\"\r\n    urllib.request.urlretrieve(HOUSING_URL, tar_file)\r\n    housing_tgz = tarfile.open(tar_file)\r\n    housing_tgz.extractall(path=housing_path)\r\n    housing_tgz.close()\r\n</code></pre>\r\n                                                ','','54-2021_11_08_11_29_18_0-2737.jpg',NULL,'2021-11-08 11:29:18',0,0,40),
(55,'ádadsf','ádfdsf','<pre><code class=\"language-html\">\r\n<ul style=\"list-style-type: square;\">\r\n<li>Tạo <span style=\"background-color: #ff0000; color: #ffffff;\">check list</span></li>\r\n<li>Che <strong>giấu</strong> code</li>\r\n<li>Che giấ<span style=\"color: #0000ff;\">u <span style=\"color: #ffffff; background-color: #0000ff;\">t&acirc;m l&yacute;</span></span></li>\r\n</ul>\r\n</code></pre>\r\n                                                ','','55-2021_11_08_12_06_59_0-262.jpg',NULL,'2021-11-08 12:06:59',0,0,40),
(57,'sed linux','cat file from line 33 to line 67','<pre><code class=\"language-bash\">\r\nsed -n 33,67p somefile.txt\r\n</code></pre>\r\n                                                ','',NULL,NULL,'2021-11-08 14:03:44',0,0,25),
(63,'test video','ádfsdfa','<pre><code class=\"language-python\"> \r\ndef fetch_housing_data(housing_url=HOUSING_URL, housing_path=HOUSING_PATH):\r\n    os.makedirs(housing_path, exist_ok=True)\r\n    tar_file = HOUSING_PATH + \"/housing.tgz\"\r\n    urllib.request.urlretrieve(HOUSING_URL, tar_file)\r\n    housing_tgz = tarfile.open(tar_file)\r\n    housing_tgz.extractall(path=housing_path)\r\n    housing_tgz.close()\r\n</code></pre>\r\n                                                ','https://www.youtube.com/watch?v=o0xtwvM84WQ&list=RD3nnYnxaRN3E&index=8&ab_channel=Tr%C3%BAcChiBolero','40-2021_11_08_15_18_46_975-4860.jpg','40-2021_11_08_15_18_46_980-1334.05','2021-11-08 15:18:47',0,0,40),
(69,'config kaka google instance','','<pre><code class=\"language-java\">\r\n\r\n</code></pre>\r\n                                                ','',NULL,NULL,'2021-11-09 08:24:12',0,0,45),
(70,'tạo web mới tải file NEXT ','','<pre><code class=\"language-java\">\r\nvà excute insert, update, lis mysql table\r\n</code></pre>\r\n                                                ','',NULL,NULL,'2021-11-09 08:24:28',0,0,45);

/*Table structure for table `menu_item` */

DROP TABLE IF EXISTS `menu_item`;

CREATE TABLE `menu_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `menu_item` */

insert  into `menu_item`(`id`,`title`,`parent_id`,`date_created`,`url`) values 
(1,'JAVA',NULL,'2021-11-06 21:25:29','java'),
(24,'Python',NULL,'2021-11-07 05:37:41','null'),
(25,'Linux',NULL,'2021-11-07 05:37:46','null'),
(26,'Machine learning',24,'2021-11-07 05:41:03','null'),
(27,'Django',24,'2021-11-07 05:41:08','null'),
(31,'Giải trí',NULL,'2021-11-07 05:42:29','null'),
(32,'Music',31,'2021-11-07 05:42:38','null'),
(33,'Game Show',31,'2021-11-07 05:42:45','null'),
(34,'Phim',31,'2021-11-07 05:43:06','null'),
(35,'Remix',32,'2021-11-07 13:25:33','null'),
(37,'Check List',NULL,'2021-11-07 21:01:19','null'),
(38,'Cài win',37,'2021-11-07 21:01:32','null'),
(39,'Java Spring Boot Web',37,'2021-11-07 21:08:20','null'),
(40,'Testing',NULL,'2021-11-08 08:36:43','null'),
(41,'Khóa học',NULL,'2021-11-08 09:08:52','null'),
(42,'Trí tuệ nhân tạo',41,'2021-11-08 09:09:05','null'),
(43,'VTC-Python for DS',42,'2021-11-08 09:09:18','null'),
(44,'Karaoke',31,'2021-11-08 09:11:32','null'),
(45,'Đang làm',NULL,'2021-11-08 14:34:40','null');

/* Function  structure for function  `levenshtein` */

/*!50003 DROP FUNCTION IF EXISTS `levenshtein` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`kaka`@`%` FUNCTION `levenshtein`( s1 VARCHAR(255), s2 VARCHAR(255) ) RETURNS int(11)
    DETERMINISTIC
BEGIN
    DECLARE s1_len, s2_len, i, j, c, c_temp, cost INT;
    DECLARE s1_char CHAR;
    -- max strlen=255
    DECLARE cv0, cv1 VARBINARY(256);

    SET s1_len = CHAR_LENGTH(s1), s2_len = CHAR_LENGTH(s2), cv1 = 0x00, j = 1, i = 1, c = 0;


    if LOCATE(LOWER(s1), LOWER(s2)) > 0 then return 0;

    end if;

    IF s1 = s2 THEN
        RETURN 0;
    ELSEIF s1_len = 0 THEN
        RETURN s2_len;
    ELSEIF s2_len = 0 THEN
        RETURN s1_len;
    ELSE
        WHILE j <= s2_len DO
                SET cv1 = CONCAT(cv1, UNHEX(HEX(j))), j = j + 1;
            END WHILE;
        WHILE i <= s1_len DO
                SET s1_char = SUBSTRING(s1, i, 1), c = i, cv0 = UNHEX(HEX(i)), j = 1;
                WHILE j <= s2_len DO
                        SET c = c + 1;
                        IF s1_char = SUBSTRING(s2, j, 1) THEN
                            SET cost = 0; ELSE SET cost = 1;
                        END IF;
                        SET c_temp = CONV(HEX(SUBSTRING(cv1, j, 1)), 16, 10) + cost;
                        IF c > c_temp THEN SET c = c_temp; END IF;
                        SET c_temp = CONV(HEX(SUBSTRING(cv1, j+1, 1)), 16, 10) + 1;
                        IF c > c_temp THEN
                            SET c = c_temp;
                        END IF;
                        SET cv0 = CONCAT(cv0, UNHEX(HEX(c))), j = j + 1;
                    END WHILE;
                SET cv1 = cv0, i = i + 1;
            END WHILE;
    END IF;
    RETURN c;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
