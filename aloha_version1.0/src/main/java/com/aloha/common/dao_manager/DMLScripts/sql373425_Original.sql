-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: sql3.freemysqlhosting.net
-- Generation Time: Apr 15, 2015 at 01:25 AM
-- Server version: 5.5.40-0ubuntu0.12.04.1
-- PHP Version: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sql373425`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `chat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chatContent` varchar(250) NOT NULL,
  `timestamp` datetime NOT NULL,
  `user_id1` int(10) unsigned NOT NULL,
  `user_id2` int(10) unsigned NOT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `FK_chat_1` (`user_id1`),
  KEY `FK_chat_2` (`user_id2`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`chat_id`, `chatContent`, `timestamp`, `user_id1`, `user_id2`) VALUES
(1, 'Hello Vivek', '2015-04-13 00:00:00', 4, 5),
(2, 'Hello Renuka', '2015-04-13 00:00:00', 5, 4),
(3, 'Where are you?', '2015-04-13 00:00:00', 4, 5),
(4, 'Hello Milind', '2015-04-14 03:06:16', 5, 8);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `comment_content` varchar(500) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `post_id` int(10) unsigned NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `FK_comment_1` (`user_id`),
  KEY `FK_comment_2` (`post_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `comment_content`, `user_id`, `post_id`, `timestamp`) VALUES
(1, 'Comment 1 on Post 1', 1, 1, '2015-04-11 20:54:29'),
(2, 'This is update of comment', 1, 1, '2015-04-11 21:23:44'),
(4, 'Comment 1 on Post 4', 1, 4, '2015-04-11 20:54:34'),
(5, 'Comment 2 on Post 4', 1, 4, '2015-04-11 20:54:35');

-- --------------------------------------------------------

--
-- Table structure for table `friendship`
--

CREATE TABLE IF NOT EXISTS `friendship` (
  `friendship_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id1` int(10) unsigned NOT NULL,
  `user_id2` int(10) unsigned NOT NULL,
  `friend_status_id` int(10) DEFAULT NULL,
  `blocked_by` int(10) DEFAULT NULL,
  `req_sent_by` int(10) DEFAULT NULL,
  PRIMARY KEY (`friendship_id`) USING BTREE,
  KEY `FK_friendship_1` (`user_id1`),
  KEY `FK_friendship_2` (`user_id2`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `friendship`
--

INSERT INTO `friendship` (`friendship_id`, `user_id1`, `user_id2`, `friend_status_id`, `blocked_by`, `req_sent_by`) VALUES
(4, 4, 7, 1, -1, 4),
(5, 5, 8, 1, -1, 5),
(6, 4, 6, 1, -1, 4),
(7, 6, 5, 1, -1, 6),
(8, 6, 7, 1, -1, 6),
(9, 6, 8, 1, -1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `friend_status`
--

CREATE TABLE IF NOT EXISTS `friend_status` (
  `status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `likedislike`
--

CREATE TABLE IF NOT EXISTS `likedislike` (
  `like_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `like_type` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL,
  `post_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FK_likedislike_1` (`user_id`),
  KEY `FK_likedislike_2` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `post_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_content` varchar(500) NOT NULL,
  `hascomments` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK_post_1` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `timestamp`, `post_content`, `hascomments`, `user_id`) VALUES
(1, '2015-06-12 06:26:36', 'This is my first post', 0, 1),
(2, '2015-04-24 14:25:30', 'This is update post', 0, 1),
(4, '2015-04-11 20:26:14', 'this is trial post', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(16) CHARACTER SET utf8 NOT NULL,
  `bdate` date DEFAULT NULL,
  `isVerified` tinyint(1) NOT NULL DEFAULT '0',
  `isLocked` tinyint(1) NOT NULL DEFAULT '0',
  `lastactive` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `lname`, `contact_number`, `email`, `password`, `bdate`, `isVerified`, `isLocked`, `lastactive`) VALUES
(1, 'DefaultUser', 'DefaultUser', '123456789', 'default@default.com', 'default', '1999-12-31', 0, 0, '2015-04-12 00:00:00'),
(4, 'Renuka', 'Deshmukh', '1234567890', 'renudesh@umail.iu.edu', '123456', '2012-09-12', 0, 0, '2015-04-12 18:59:30'),
(5, 'Vivek', 'Supe', '9087654321', 'vsupe@umail.iu.edu', '1234567', '2000-05-05', 0, 0, '2015-04-12 18:59:30'),
(6, 'Mrunal', 'Pagnis', '3451236789', 'mmpagnis@umail.iu.edu', 'mrunalp', '2007-09-12', 0, 0, '2015-04-12 19:02:46'),
(7, 'Amy', 'Fowler', '9871236540', 'amy.fowler@gmail.com', 'fowlera', '1994-12-07', 0, 0, '2015-04-12 19:02:46'),
(8, 'Milind', 'Gokhale', '2468097531', 'mgokhale@umail.iu.edu', 'mmilind', '2005-10-02', 0, 0, '2015-04-12 19:07:35'),
(9, 'Mrunalabcd', 'Pagnis', '8123695371', 'mmpagnis@indiana.edu', 'Mrunal123', NULL, 0, 0, NULL),
(12, 'Prajakta', 'Ghatage', '9922927097', 'prajaktag@gmail.com', 'Prajakta123', NULL, 0, 0, NULL),
(13, 'Meghana', 'Shah', '9011044404', 'meghna@gmail.com', 'Meghana123', NULL, 0, 0, NULL),
(15, 'Priyanka', 'unune', '9890912216', 'pri_123@gmail.com', 'Priyanka123', NULL, 0, 0, NULL),
(17, 'newUser', 'newUserLast', '8123695371', 'abcd@somewhere.com', '[B@1984ccfc', NULL, 0, 0, NULL),
(18, 'someonenew', 'someoneold', '8123695371', 'abcd@somewhere.com', '[B@55f19257', NULL, 0, 0, NULL),
(19, 'newnewuser', 'oldolduser', '8123695371', 'newuser@gmail.com', '[B@18012261', NULL, 0, 0, NULL),
(20, 'newnewuser', 'oldolduser', '8123695371', 'newuser@gmail.com', '[B@de185d2', NULL, 0, 0, NULL),
(21, 'newnewuser', 'oldolduser', '8123695371', 'newuser1@gmail.com', '[B@27e0a97a', NULL, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users_education`
--

CREATE TABLE IF NOT EXISTS `users_education` (
  `user_id` int(11) NOT NULL,
  `school` varchar(50) NOT NULL,
  `area` varchar(50) DEFAULT NULL,
  KEY `user_id` (`user_id`,`school`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `FK_chat_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_chat_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_comment_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_comment_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `FK_friendship_2` FOREIGN KEY (`user_id2`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_friendship_1` FOREIGN KEY (`user_id1`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `likedislike`
--
ALTER TABLE `likedislike`
  ADD CONSTRAINT `FK_likedislike_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_likedislike_2` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_post_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
