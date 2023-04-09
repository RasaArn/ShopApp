CREATE DATABASE IF NOT EXISTS  supermarketdb;

USE supermarketdb;

CREATE TABLE IF NOT EXISTS product_data (
  productId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  productName VARCHAR(255) NOT NULL,
  weight DOUBLE NOT NULL,
  quantityInStock INT NOT NULL,
  costPrice DOUBLE NOT NULL,
  sellingPrice DOUBLE NOT NULL,
  productType ENUM ('FRUIT', 'VEGETABLES', 'NUTS' ) NOT NULL
);

CREATE TABLE IF NOT EXISTS  user_data (
  userId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  userNameSurname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  addressStreet VARCHAR(255) NOT NULL,
  addressBuildingNumber VARCHAR(255) NOT NULL,
  apartmentNumber VARCHAR(255),
  city VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  zipCode VARCHAR(10) NOT NULL,
  balance DOUBLE NOT NULL,
  userRole ENUM ('ADMIN', 'CUSTOMER' ) NOT NULL
);

CREATE TABLE IF NOT EXISTS sales_data (
  saleId INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  saleDate DATE NOT NULL,
  product_id INT NOT NULL,
  user_id INT NOT NULL,
  purchasedQuantity INT NOT NULL,
  totalPricePerSale DOUBLE NOT NULL,
  costPrice DOUBLE NOT NULL,
  productName VARCHAR(255) NOT NULL,
  FOREIGN KEY (product_id) REFERENCES product_data (productId),
  FOREIGN KEY (user_id) REFERENCES user_data (userId)
);

DROP TABLE  user_data;
DROP TABLE  sales_data;
DROP TABLE  product_data;


SELECT * FROM sales_data;
SELECT * FROM product_data;
SELECT * FROM user_data;


-- SALES REPORT QUERY
SELECT 
    DATE_FORMAT(saleDate, '%Y-%m') AS month,
    p.productName,
    SUM(s.costPrice * s.purchasedQuantity) AS costOfGoodsSold, 
    SUM(s.totalPricePerSale) AS revenue,
    SUM(s.totalPricePerSale) - SUM(s.costPrice * s.purchasedQuantity) AS profit
FROM 
    sales_data s
    INNER JOIN product_data p ON s.product_id = p.productId
GROUP BY 
    DATE_FORMAT(s.saleDate, '%Y-%m'),
    p.productName;



/*
AS is a keyword used in SQL to give an alias or alternative name to a column or table. 

INNER JOIN is a type of join used in SQL that returns only the rows that have matching values in both tables being joined. 
It is used to combine rows from two or more tables into a single result set based on a related column between them. 
*/

SELECT @@version;


