CREATE DATABASE `testdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
DROP TABLE IF EXISTS `testdb`.`user`;

CREATE TABLE  `testdb`.`user` (
  `user_id` int(10) unsigned NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 NOT NULL,
  `bdate` date DEFAULT NULL,
  `isVerfied` tinyint(1) NOT NULL DEFAULT '0',
  `isLocked` tinyint(1) NOT NULL DEFAULT '0',
  `lastactive` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
DROP TABLE IF EXISTS `testdb`.`chat`;

CREATE TABLE  `testdb`.`chat` (
  `chat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chatContent` varchar(250) NOT NULL,
  `timestamp` datetime NOT NULL,
  `user_id1` int(10) unsigned NOT NULL,
  `user_id2` int(10) unsigned NOT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `FK_chat_1` (`user_id1`),
  KEY `FK_chat_2` (`user_id2`),
  CONSTRAINT `FK_chat_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_chat_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `testdb`.`friend_status`;
CREATE TABLE  `testdb`.`friend_status` (
  `status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `testdb`.`friendship`;
CREATE TABLE  `testdb`.`friendship` (
  `friendship_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id1` int(10) unsigned NOT NULL,
  `user_id2` int(10) unsigned NOT NULL,
  `friend_status_id` int(10) DEFAULT NULL,
  `blocked_by` int(10) DEFAULT NULL,
  `req_sent_by` int(10) DEFAULT NULL,
  PRIMARY KEY (`friendship_id`) USING BTREE,
  KEY `FK_friendship_1` (`user_id1`),
  KEY `FK_friendship_2` (`user_id2`),
  CONSTRAINT `FK_friendship_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_friendship_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `testdb`.`post`;
CREATE TABLE  `testdb`.`post` (
  `post_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `post_content` varchar(500) NOT NULL,
  `hascomments` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK_post_1` (`user_id`),
  CONSTRAINT `FK_post_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `testdb`.`likedislike`;
CREATE TABLE  `testdb`.`likedislike` (
  `like_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `like_type` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL,
  `post_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FK_likedislike_1` (`user_id`),
  KEY `FK_likedislike_2` (`post_id`),
  CONSTRAINT `FK_likedislike_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_likedislike_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `testdb`.`comment`;
CREATE TABLE  `testdb`.`comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(500) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `post_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_comment_1` (`user_id`),
  KEY `FK_comment_2` (`post_id`),
  CONSTRAINT `FK_comment_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_comment_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
