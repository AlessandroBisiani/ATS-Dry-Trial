USE abpn980;

SET storage_engine=INNODB;

DROP TABLE IF EXISTS Report;
DROP TABLE IF EXISTS Refund;
DROP TABLE IF EXISTS Sale;
DROP TABLE IF EXISTS Blank;
DROP TABLE IF EXISTS BlankType;
DROP TABLE IF EXISTS Commission;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS DiscountPlan;
DROP TABLE IF EXISTS Address;

CREATE TABLE Address(
    addressID INTEGER(10) AUTO_INCREMENT, 
    addressLine1 VARCHAR(50), 
    addressLine2 VARCHAR(50), 
    county VARCHAR(50), 
    city VARCHAR(50), 
    country VARCHAR(50), 
    postCode VARCHAR(8),
    PRIMARY KEY (addressID)
);


CREATE TABLE DiscountPlan(
    discountPlanID INTEGER(10) AUTO_INCREMENT,
    percentage DECIMAL(10, 2),
    discountType VARCHAR(10),
    payLaterOption BIT,
    PRIMARY KEY (discountPlanID)
);

CREATE TABLE Customer(
    customerID INTEGER(10) AUTO_INCREMENT,
    firstName VARCHAR(20),
    surname VARCHAR(20),
    gender VARCHAR(10),
    `type` VARCHAR(10),
    addressID INTEGER(10),
    discountPlanID INTEGER(10),
    PRIMARY KEY (customerID),
    FOREIGN KEY (addressID) REFERENCES Address(addressID),
    FOREIGN KEY (discountPlanID) REFERENCES DiscountPlan(discountPlanID)
);

CREATE TABLE `User`(
    userID INTEGER(10) AUTO_INCREMENT,
    username VARCHAR(20),
    password VARCHAR(20),
    firstName VARCHAR(20),
    surname VARCHAR(20),
    `role` VARCHAR(20),
    PRIMARY KEY (userID)
);
	
CREATE TABLE Commission(
    commissionID INTEGER(10) AUTO_INCREMENT,
    amount DECIMAL(10, 2),
    commissionType VARCHAR(10),
    userID INTEGER(10),
    PRIMARY KEY (commissionID),
    FOREIGN KEY (userID) REFERENCES `User`(userID)
);
	
CREATE TABLE BlankType(
    blankTypeID INTEGER(3),
    numberOfCoupons INTEGER(10),
    PRIMARY KEY (blankTypeID)
);
	
CREATE TABLE Blank(
    blankID INT(11),
    blankStatus VARCHAR(20),
    blankTypeID INTEGER(3),
    PRIMARY KEY (blankID),
    FOREIGN KEY (blankTypeID) REFERENCES BlankType(blankTypeID)
);

CREATE TABLE Sale(
    saleID INTEGER(10) AUTO_INCREMENT,
    price DECIMAL(10, 2),
    currency VARCHAR(20),
    valueInUSD DECIMAL(10, 2),
    `date` DATE,
    `time` TIME,
    paymentType VARCHAR(10),
    customerID INTEGER(10),
    userID INTEGER(10),
    blankID INTEGER(11),
    commissionID INTEGER(10),
    PRIMARY KEY (saleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (userID) REFERENCES `User`(userID),
    FOREIGN KEY (blankID) REFERENCES Blank(blankID),
    FOREIGN KEY (commissionID) REFERENCES Commission(commissionID)
);
	
CREATE TABLE Refund(
    refundID INTEGER(10) AUTO_INCREMENT,
    refundAmount DECIMAL(10, 2),
    currency VARCHAR(20),
    valueInUSD DECIMAL(10, 2),
    saleID INTEGER(10),
    customerID INTEGER(10),
    PRIMARY KEY (refundID),
    FOREIGN KEY (saleID) REFERENCES Sale(saleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);
	
CREATE TABLE Report(
    reportID INTEGER(10) AUTO_INCREMENT,
    salesOfficePlace VARCHAR(50),
    periodStartDate DATE,
    periodEndDate DATE,
    `type` VARCHAR(50),
    userID INTEGER(10),
    PRIMARY KEY (reportID),
    FOREIGN KEY (userID) REFERENCES `User`(userID)
);

INSERT INTO Address(addressLine1, addressLine2, county, city, country, postCode) VALUES
    ("St Mary's Street 24", "Flat 79", "Greater London", "London", "GB", "E34 2FG"),
    ("High Street", "BAS", "South-East", "Battle", "GB", "TN33 0AD"),
    ("Oxford Street", "Flat 61", "Westminster", "London", "GB", "W1W 8FJ"),
    ("Doctor Street", "Flat 1", "Greater London", "London", "GB", "E14 8FF"),
    ("Kingsland Road 149", "Flat 142", "Greater London", "London", "GB", "E1 9FG");

INSERT INTO `User`(username, password, firstName, surname, `role`) VALUES
    ("addmein", MD5("Greedy"), "Glara", "McJanes", "Admin"),
    ("Jennifer", MD5("g3rfjlgk3"), "Jennifer", "Alba", "Sales Manager"),
    ("Sandy", MD5("h7ni72"), "Sandy", "Alba", "Advisor");

DELETE FROM `User` WHERE `role` = "Sales Manager";

DELETE FROM Address WHERE county = "Westminster" OR city = "Battle";

SELECT * FROM Address;

SELECT firstName, surname, `role` FROM `User`;

INSERT INTO BlankType(blankTypeID, numberOfCoupons) VALUES (444, 4), (440, 4), (420, 2), (201, 2), (101, 1), (451, NULL), (452, NULL);