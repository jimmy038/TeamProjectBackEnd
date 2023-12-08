CREATE TABLE  IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` int NOT NULL,
  `inventory` int NOT NULL,
  `sale_count` int NOT NULL DEFAULT '0',
  `shelves` tinyint NOT NULL DEFAULT '0',
  `photo` mediumblob,
  `user_id` int NOT NULL,
  PRIMARY KEY (`product_id`)
)

CREATE TABLE IF NOT EXISTS `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_date` datetime NOT NULL,
  `amount` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`order_id`)
)

