drop database if exists `design_pattern`;
create database `design_pattern` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `payment_channel`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `channelName` VARCHAR(100) NOT NULL,
   `channelId` VARCHAR(40) NOT NULL,
   `strategyBeanId` VARCHAR(40) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;