-- Create the database
CREATE DATABASE IF NOT EXISTS `store_inventory`;
USE `store_inventory`;

-- ----------------------------
-- Table structure for `branch`
-- ----------------------------
CREATE TABLE `branch` (
  `branch_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`branch_id`)
) ;

INSERT INTO `branch` (`branch_id`, `name`, `location`) VALUES
(1, 'store 1', 'kandy'),
(2, 'store 2', 'digana'),
(3, 'Store 3', 'Katugasthota');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ;

INSERT INTO `product` (`product_id`, `name`, `price`) VALUES
(1, 'xps 1', 20093.02),
(3, 'laptop', 12343.00);

-- ----------------------------
-- Table structure for `inventory`
-- ----------------------------
CREATE TABLE `inventory` (
  `inventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`inventory_id`),
  UNIQUE KEY `product_id` (`product_id`,`branch_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `inventory_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`)
) ;

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `contact_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ;

INSERT INTO `supplier` (`supplier_id`, `name`, `contact_info`) VALUES
(1, 'Dell', '0758021331');

-- ----------------------------
-- Table structure for `productsupplier`
-- ----------------------------
CREATE TABLE `productsupplier` (
  `product_supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_supplier_id`),
  KEY `product_id` (`product_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `productsupplier_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `productsupplier_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`)
) ;

INSERT INTO `productsupplier` (`product_supplier_id`, `product_id`, `supplier_id`) VALUES
(1, 1, 1),
(2, 1, 1),
(3, 1, 1),
(4, 1, 1),
(5, 1, 1),
(6, 1, 1);

-- ----------------------------
-- Table structure for `purchaseorder`
-- ----------------------------
CREATE TABLE `purchaseorder` (
  `po_id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_id` int(11) DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `status` enum('Pending','Received') DEFAULT NULL,
  PRIMARY KEY (`po_id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `purchaseorder_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`),
  CONSTRAINT `purchaseorder_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`)
) ;

-- ----------------------------
-- Table structure for `purchasedetails`
-- ----------------------------
CREATE TABLE `purchasedetails` (
  `po_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `po_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`po_detail_id`),
  KEY `po_id` (`po_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `purchasedetails_ibfk_1` FOREIGN KEY (`po_id`) REFERENCES `purchaseorder` (`po_id`),
  CONSTRAINT `purchasedetails_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ;

-- ----------------------------
-- Table structure for `sales`
-- ----------------------------
CREATE TABLE `sales` (
  `sale_id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_date` date DEFAULT NULL,
  `branch_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sale_id`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`)
) ;

-- ----------------------------
-- Table structure for `saledetails`
-- ----------------------------
CREATE TABLE `saledetails` (
  `sale_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`sale_detail_id`),
  KEY `sale_id` (`sale_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `saledetails_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`sale_id`),
  CONSTRAINT `saledetails_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `usertype` enum('ADMIN','MANAGER','STAFF') NOT NULL,
  `branch_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`branch_id`)
) ;

INSERT INTO `users` (`id`, `username`, `name`, `password`, `phone`, `usertype`, `branch_id`, `created_at`) VALUES
(1, 'Admin', 'admin admin', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '0750459230', 'ADMIN', 1, '2025-06-12 10:11:19'),
(4, 'Sachin', 'Sachin Mahesh', '123456', '1231231232', 'MANAGER', 2, '2025-06-14 17:16:15');

-- ----------------------------
-- Table structure for table `userlogs`
-- ----------------------------
CREATE TABLE `userlogs` (
  'id' INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `in_time` VARCHAR(45) NOT NULL,
  `out_time` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);
