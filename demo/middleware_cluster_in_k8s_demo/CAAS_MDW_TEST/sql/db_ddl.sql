DROP TABLE IF EXISTS `demo.user`;
CREATE TABLE `demo.user`(
  `id` VARCHAR (45) NOT NULL ,
  `user_name` VARCHAR (100),
  `password` CHAR(32),
  `email` VARCHAR (50),
  `phone` VARCHAR (50),
  `sex` ENUM('S_MALE','S_FEMALE','S_BM'),
  `comment` VARCHAR (200),
  `create_time` TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;