CREATE DATABASE IF NOT EXISTS foodstore;
USE foodstore;

CREATE TABLE IF NOT EXISTS user (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL DEFAULT 'Customer',
    address VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS restaurant (
    restaurantId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL,
    cuisineType VARCHAR(120) NOT NULL,
    deliveryTime VARCHAR(50) NOT NULL,
    address VARCHAR(500) NOT NULL,
    adminUserId INT NOT NULL DEFAULT 1,
    rating VARCHAR(20) NOT NULL DEFAULT '4.5',
    isActive BOOLEAN NOT NULL DEFAULT TRUE,
    imagePath VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS menu (
    menuId INT PRIMARY KEY AUTO_INCREMENT,
    restaurantId INT NOT NULL,
    itemName VARCHAR(120) NOT NULL,
    description VARCHAR(500) NOT NULL,
    price INT NOT NULL,
    isAvailable BOOLEAN NOT NULL DEFAULT TRUE,
    imagePath VARCHAR(255) NOT NULL,
    CONSTRAINT fk_menu_restaurant FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

CREATE TABLE IF NOT EXISTS `order` (
    orderId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    restaurantId INT NOT NULL,
    orderDate TIMESTAMP NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    paymentMode VARCHAR(50) NOT NULL,
    address VARCHAR(500) NOT NULL,
    CONSTRAINT fk_order_user FOREIGN KEY (userId) REFERENCES user(userId),
    CONSTRAINT fk_order_restaurant FOREIGN KEY (restaurantId) REFERENCES restaurant(restaurantId)
);

ALTER TABLE `order` ADD COLUMN IF NOT EXISTS address VARCHAR(500) NOT NULL DEFAULT '';

CREATE TABLE IF NOT EXISTS orderitem (
    orderItemId INT PRIMARY KEY AUTO_INCREMENT,
    orderId INT NOT NULL,
    menuId INT NOT NULL,
    totalAmount DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_orderitem_order FOREIGN KEY (orderId) REFERENCES `order`(orderId),
    CONSTRAINT fk_orderitem_menu FOREIGN KEY (menuId) REFERENCES menu(menuId)
);

INSERT INTO restaurant (restaurantId, name, cuisineType, deliveryTime, address, adminUserId, rating, isActive, imagePath)
VALUES
(1, 'Spice Garden', 'Indian', '30', 'MG Road, Bengaluru', 1, '4.7', TRUE, 'Images/spiceGarden.jpg'),
(2, 'Pizza Corner', 'Italian', '25', 'Church Street, Bengaluru', 1, '4.5', TRUE, 'Images/pizza.jpg'),
(3, 'Burger Hub', 'Fast Food', '20', 'Indiranagar, Bengaluru', 1, '4.4', TRUE, 'Images/burger.jpg'),
(4, 'Sushi World', 'Japanese', '35', 'Koramangala, Bengaluru', 1, '4.8', TRUE, 'Images/sushi.jpg')
ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO menu (menuId, restaurantId, itemName, description, price, isAvailable, imagePath)
VALUES
(1, 1, 'Butter Chicken', 'Tender chicken cooked in a creamy tomato gravy.', 320, TRUE, 'Images/menuImages/butterChicken.jpg'),
(2, 1, 'Paneer Tikka', 'Spiced paneer cubes grilled with peppers and onions.', 240, TRUE, 'Images/menuImages/paneer.jpg'),
(3, 1, 'Butter Naan', 'Soft tandoor bread brushed with butter.', 55, TRUE, 'Images/menuImages/butterNaan.png'),
(4, 1, 'Veg Biryani', 'Fragrant rice cooked with vegetables and spices.', 210, TRUE, 'Images/menuImages/vegBiryani.jpg'),
(5, 2, 'Margherita Pizza', 'Classic pizza with tomato, basil, and mozzarella.', 260, TRUE, 'Images/menuImages/margheritapizza.jpg'),
(6, 2, 'Farmhouse Pizza', 'Loaded with capsicum, onion, tomato, and cheese.', 340, TRUE, 'Images/menuImages/farmhousepizza.jpg'),
(7, 3, 'Cheese Burger', 'Juicy patty, cheese, lettuce, and house sauce.', 180, TRUE, 'Images/menuImages/cheeseBurger.jpg'),
(8, 3, 'French Fries', 'Crispy salted fries served hot.', 110, TRUE, 'Images/menuImages/frenchFries.jpg'),
(9, 4, 'Sushi Platter', 'Assorted sushi rolls with soy and wasabi.', 420, TRUE, 'Images/menuImages/sushi.jpg'),
(10, 4, 'Ramen Bowl', 'Noodles in rich broth with vegetables and egg.', 360, TRUE, 'Images/menuImages/ramen.jpg')
ON DUPLICATE KEY UPDATE itemName = VALUES(itemName);
