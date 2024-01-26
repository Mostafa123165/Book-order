--
-- Create database 
--

DROP DATABASE IF EXISTS `Book_Order`;
CREATE DATABASE IF NOT EXISTS `Book_Order`;
USE `Book_Order`;

--
-- User Table 
-- 
DROP TABLE IF EXISTS user ;

CREATE TABLE IF NOT EXISTS `user` (
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `name` 		 VARCHAR(110) NOT NULL ,
    `first_name` VARCHAR(55) NOT NULL ,
    `last_name`  VARCHAR(55) NOT NULL ,
    `email`      VARCHAR(55) NOT NULL ,
	`password`   VARCHAR(70) NOT NULL ,
    `phone`      varchar(13) NOT NULL ,
    `gendr`      VARCHAR(6)  NOT NULL ,
    PRIMARY KEY(`id`)
); 
	
-- 
-- Add some demo user , default password = 123
--

INSERT INTO `user` VALUES 
(1,'Mostafa Tarek' , 'Mostafa','Tarek'   ,'mostafa@admin.com','$2a$10$MhOBZVfvTRh2Dyczs99sauMBmNg6D6O0ouIp8CC1wS.gag.zYashO','+201061082296','MALE'  ),
(2,'Chad Darby', 'Chad','Darby'   ,'chad@luv2code.com','$2a$10$WR/AkDie.Wbd/FqzUZFO/.9BU0CTtyGyAe3Wyg0oRB0OJZJlL.aii','+201278577187','MALE'  ),
(3,'Reham Mohammed','Reham','Mohammed','reham@gmail.com'  ,'$2a$10$x7co9jEhpDr92mm45LHkYex.MByMRnwvhLddcHhFqx2miSOgkhbei','+201015456465','FEMALE'),
(4,'Maryam Naser','Maryam' ,'Naser'   ,'maryam@gmail.com' ,'$2a$10$x7co9jEhpDr92mm45LHkYex.MByMRnwvhLddcHhFqx2miSOgkhbei','+201511954571','FEMALE'),
(5,'Anwar Ali','Anwar' ,'Ali' ,'anwar@test.com'   ,'$2a$10$fUWxeb.rG/rJi8Zmz4UP5.uVPoOtt5.plCtyd96Q6fagq.BIqsMc6','+201115445127','MALE'  );
--
-- Role Table
--

DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role`(
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `role_name` VARCHAR(20) NOT NULL DEFAULT 'ROLE_CUSTOMER',
    UNIQUE KEY(`role_name`),
    PRIMARY KEY (`id`)
);

--
-- Add some demo role	
--

INSERT INTO `role` VALUES 
(2,'ROLE_ADMIN'),
(1,'ROLE_CUSTOMER');

--
-- Create roles as Join Table  between User and Role tables
--

DROP TABLE IF EXISTS `roles` ;

CREATE TABLE IF NOT EXISTS `roles`(
	`user_id` INT NOT NULL ,
    `role_id` INT NOT NULL ,
    PRIMARY KEY (`role_id` , `user_id`) ,
	CONSTRAINT `FK_Role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ,
    CONSTRAINT `FK_User` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

--
-- Add some demo roles to some users
--

INSERT INTO `roles` VALUES 
('1','1'),('1','2'),
('2','1'),('2','2'),
('3','1'),
('4','1'),
('5','1');

--
-- Book Table  
--

DROP TABLE IF EXISTS `book`;

CREATE TABLE IF NOT EXISTS `book` (
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `title` VARCHAR(255) NOT NULL ,
    `description` VARCHAR(255) DEFAULT NULL ,
    `price` DOUBLE NOT NULL, 
    `publisher_id` INT NOT NULL ,
    `publisher_date` VARCHAR(10) NOT NULL ,
    `author_name` VARCHAR(40) DEFAULT NULL ,
   	CONSTRAINT `FK_book_publisher_id` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`) ,
    PRIMARY KEY(`id`)
);

--
-- Add some demo book 
--

INSERT INTO `book` VALUES
    (1,'The Great Gatsby','This classic novel paints a vivid picture of the Jazz Age, describing opulent parties, complex characters, and the decadence of the era with lush and evocative language.', 15.99, 1, '2022-01-15', 'F. Scott Fitzgerald'),
    (2,'The Night Circus','This fantastical story about a magical competition between two young illusionists features mesmerizing descriptions of the enchanting circus', 12.50, 1, '2021-11-28', 'Harper Lee'),
    (3,'Where the Crawdads Sing', 'Set in the marshes of North Carolina, this novel beautifully captures the natural world, describing the landscape, flora, and fauna in a way that almost makes it a character in the story.',10.75, 2, '2023-05-10', 'George Orwell'),
    (4,'The Shadow of the Wind',' This atmospheric book brings 1940s Barcelona to life, with its intricate descriptions of the city\'s streets, secret libraries, and mysterious characters.', 14.25, 3, '2020-09-03', 'Jane Austen');

--
-- Reviews Table  
--

DROP TABLE IF EXISTS `reviews`;

CREATE TABLE IF NOT EXISTS `reviews` (
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `review` VARCHAR(255) NOT NULL  ,
    `user_id` INT NOT NULL ,
    `book_id` INT NOT NULL ,
    CONSTRAINT `FK_review_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ,
	CONSTRAINT `FK_review_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ,
    PRIMARY KEY(`id`)
);

--
-- Add some demo reviews to demo book
--

INSERT INTO `reviews` VALUES 
    (1,'Fantastic book, highly recommended!', 1, 1),
    (2,'Couldn\'t put it down!', 1, 1),
    (3,'A must-read for everyone!', 1, 2),
    (4,'Enjoyed every page of this book.', 2, 1),
	(5,'This book changed my perspective.', 2, 2),
    (6,'A classic that everyone should read.', 2, 2);

--
-- Rates Table  
--

CREATE TABLE IF NOT EXISTS `rates` (
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `rate` INT NOT NULL  ,
    `user_id` INT NOT NULL ,
    `book_id` INT NOT NULL ,
    UNIQUE KEY(`user_id`,`book_id`),
    CONSTRAINT `FK_rate_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ,
	CONSTRAINT `FK_rate_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ,
    PRIMARY KEY(`id`)
);

--
-- Add some demo rate to demo book
--

INSERT INTO `rates` VALUES
    (1,8 , 1, 1),
    (2,10, 1, 2),
    (3, 5, 3, 1),
    (4, 6, 4, 2),
    (5, 6, 4, 1),
    (6, 2, 2, 1);
    
--
-- Order Table
--    

DROP TABLE IF EXISTS `orders`;

CREATE TABLE IF NOT EXISTS `orders` (
	`id` INT NOT NULL AUTO_INCREMENT  ,
    `user_id`   INT NOT NULL ,
    `state`     VARCHAR(20) NOT NULL  ,
    `create_at` VARCHAR(10) NOT NULL ,
    `phone`     VARCHAR(13) NOT NULL  ,
    `country`   VARCHAR(25) NOT NULL  ,
	`city` 	    VARCHAR(30) NOT NULL  ,
    `street`    VARCHAR(60) NOT NULL  ,
    CONSTRAINT `FK_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ,
    PRIMARY KEY(`id`)
);

--
-- Order details Table
--

DROP TABLE IF EXISTS `orderDetails`;

CREATE TABLE IF NOT EXISTS `orderDetails` (
	`id`         INT NOT NULL AUTO_INCREMENT ,
    `order_id`   INT DEFAULT NULL ,
    `book_id`    INT NOT NULL ,
    `book_title` VARCHAR(255) NOT NULL ,
    `quantity`   INT NOT NULL ,
    `price` 	 DOUBLE NOT NULL ,
    
	CONSTRAINT `FK_orderDetails_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ,
    CONSTRAINT `FK_orderDetails_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ,
    PRIMARY KEY(`id`)
) AUTO_INCREMENT = 10000;

--
-- Add some demo orders and details
--

INSERT INTO `orders`VALUES
    (1,1, 'Pending', '2023-12-23', '+1234567890', 'United States', 'New York', '123 Main St'),
    (2,2, 'Shipped', '2023-12-21', '+1987654321', 'Canada', 'Toronto', '456 Elm St'),
    (3,3, 'Delivered', '2023-12-20', '+4455667788', 'United Kingdom', 'London', '789 Oak Ave'),
    (4,1, 'Canceled', '2023-12-19', '+1122334455', 'Australia', 'Sydney', '321 Pine St'),
    (5,4, 'Processing', '2023-12-18', '+6677889900', 'Germany', 'Berlin', '555 Birch St');

INSERT INTO `orderDetails` VALUES
    (10000,1, 1, 'The Great Gatsby', 3, 47.97),
    (10001,2, 2, 'The Night Circus', 2, 25),
    (10002,3, 3, 'Where the Crawdads Sing', 4, 10.75),
    (10003,4, 1, 'The Great Gatsby', 1, 15.99),
    (10004,5, 4, 'The Shadow of the Wind', 2, 28.5),
	(10005,1, 4, 'The Shadow of the Wind', 2, 28.5);
--
-- Cart Table
-- 

DROP TABLE IF EXISTS `cart`; 

CREATE TABLE IF NOT EXISTS `cart`(
	`id`    INT NOT NULL AUTO_INCREMENT  ,
	`user_id`    INT NOT NULL ,
	`book_id`    INT NOT NULL ,
    `book-title` VARCHAR(255) NOT NULL ,
    `price`      DOUBLE NOT NULL ,
    `quantity`   INT NOT NULL ,
    CONSTRAINT `FK_cart_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ,
    CONSTRAINT `FK_cart_userr_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ,
    PRIMARY KEY(`id`)
);



