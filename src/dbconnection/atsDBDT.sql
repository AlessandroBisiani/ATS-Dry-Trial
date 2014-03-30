USE abpn980;

SET storage_engine=INNODB;

DROP TABLE IF EXISTS Sale_Report;
DROP TABLE IF EXISTS Report;
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
    howAwarded VARCHAR(50),
    payLaterOption INTEGER(1),
    PRIMARY KEY (discountPlanID)
);

CREATE TABLE Customer(
    customerID INTEGER(10) AUTO_INCREMENT,
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    title VARCHAR(10),
    `type` VARCHAR(10),
    addressID INTEGER(10),
    discountPlanID INTEGER(10),
    PRIMARY KEY (customerID),
    FOREIGN KEY (addressID) REFERENCES Address(addressID),
    FOREIGN KEY (discountPlanID) REFERENCES DiscountPlan(discountPlanID)
);

CREATE TABLE `User`(
    username VARCHAR(20),
    password VARCHAR(20),
    firstName VARCHAR(20),
    surname VARCHAR(20),
    `role` VARCHAR(20),
    PRIMARY KEY (username)
);
	
CREATE TABLE Commission(
    commissionID INTEGER(10) AUTO_INCREMENT,
    percentage DECIMAL(10, 2),
    commissionType VARCHAR(10),
    PRIMARY KEY (commissionID)
);
	
CREATE TABLE BlankType(
    blankTypeID VARCHAR(3),
    blankType VARCHAR(10),
    numberOfCoupons INTEGER(10),
    PRIMARY KEY (blankTypeID)
);
	
CREATE TABLE Blank(
    blankID VARCHAR(11),
    blankStatus VARCHAR(20),
    blankTypeID VARCHAR(3),
    username VARCHAR(20),
    PRIMARY KEY (blankID),
    FOREIGN KEY (blankTypeID) REFERENCES BlankType(blankTypeID),
    FOREIGN KEY (username) REFERENCES `User`(username)
);

CREATE TABLE Sale(
    saleID INTEGER(10) AUTO_INCREMENT,
    price DECIMAL(10, 2),
    currency VARCHAR(20),
    valueInUSD DECIMAL(10, 2),
    `date` DATE,
    `time` TIME,
    paymentType VARCHAR(10),
    tax DECIMAL (10, 2),
    customerID INTEGER(10),
    username VARCHAR(20),
    blankID VARCHAR(11),
    commissionID INTEGER(10),
    refunded BIT,
    PRIMARY KEY (saleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (username) REFERENCES `User`(username),
    FOREIGN KEY (blankID) REFERENCES Blank(blankID),
    FOREIGN KEY (commissionID) REFERENCES Commission(commissionID)
);
	
CREATE TABLE Report(
    reportID INTEGER(10) AUTO_INCREMENT,
    salesOfficePlace VARCHAR(50),
    periodStartDate DATE,
    periodEndDate DATE,
    `type` VARCHAR(50),
    PRIMARY KEY (reportID)
);

CREATE TABLE Sale_Report(
    sale_reportID INTEGER(10) AUTO_INCREMENT,
    saleID INTEGER(10),
    reportID INTEGER(10),
    PRIMARY KEY (sale_reportID),
    FOREIGN KEY (saleID) REFERENCES Sale(saleID),
    FOREIGN KEY (reportID) REFERENCES Report(reportID)
);

-- INSERT queries
INSERT INTO Address(addressLine1, addressLine2, county, city, country, postCode) VALUES
    ("St Mary's Street 24", "Flat 79", "Middlesex", "London", "GB", "E34 2FG"),
    ("High Street", "BAS", "East Sussex", "Battle", "GB", "TN33 0AD"),
    ("Oxford Street", "Flat 61", "Westminster", "London", "GB", "W1W 8FJ"),
    ("Doctor Street", "Flat 1", "Greater London", "London", "GB", "E14 8FF"),
    ("Kingsland Road 149", "Flat 142", "Middlesex", "London", "GB", "E1 9FG");

INSERT INTO DiscountPlan (percentage, discountType, howAwarded, payLaterOption) VALUES
    (10, "Fixed", "Pay at end of month", 1),
    (15, "Flexible", "Deduct from future sales", 1),
    (0, null, null, 1),
    (0, null, null, 0);

INSERT INTO Customer VALUES
    (null, "Oregua", "Rakina", "Miss", "Valued", 1, 1),
    (null, "Jack", "Minchel", "Mr", "Regular", 2, 4),
    (null, "Michael", "Mandon", "Mr", "Regular", 3, 3);

INSERT INTO `User`(username, password, firstName, surname, `role`) VALUES
    ("addmein", MD5("Greedy"), "Glara", "McJanes", "Admin"),
    ("Jennifer", MD5("WestChance"), "Jennifer", "Alba", "Office Manager"),
    ("Sandy", MD5("PassMyWord"), "Sandy", "Alba", "Advisor");

INSERT INTO Commission(percentage, commissionType) VALUES
    (9, "Assessable"),
    (5, "Assessable");

INSERT INTO BlankType VALUES
    (201, "Domestic", 1);

INSERT INTO Blank VALUES
    ("20104277209", "Valid", 201, "Jennifer"),
    ("20158426790", "Valid", 201, "Jennifer"),
    ("20157257225", "Valid", 201, "Jennifer");

INSERT INTO Sale(price, currency, valueInUSD, `date`, `time`, paymentType, tax, customerID, username, blankID, commissionID, refunded) VALUES
    (30000, "BGL", 20, "2008-02-01", "18:32:02", "Cash", 5000, 1, "addmein", "20104277209", 1, 0),
    (40000, "BGL", 30, "2008-02-16", "11:32:49", "Credit Card", 5000, 2, "Jennifer", "20157257225", 1, 0),
    (50000, "BGL", 35, "2008-01-30", "14:41:25", "Cash", 5000, 1, "Sandy", "20158426790", 1, 1);

INSERT INTO Report(salesOfficePlace, periodStartDate, periodEndDate, `type`) VALUES
    ("Giday's Office", "2008-01-28", "2008-02-27", "Domestic Sales Report");
