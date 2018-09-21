-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 20, 2018 at 12:29 PM
-- Server version: 5.7.23-0ubuntu0.16.04.1
-- PHP Version: 7.0.32-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SacredBazzar`
--

-- --------------------------------------------------------

--
-- Table structure for table `CustomerTable`
--

CREATE TABLE `CustomerTable` (
  `FirstName` varchar(100) NOT NULL,
  `LastName` varchar(100) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Address` text NOT NULL,
  `MobileNo` varchar(10) NOT NULL,
  `PinNo` int(10) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Products` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CustomerTable`
--

INSERT INTO `CustomerTable` (`FirstName`, `LastName`, `UserName`, `Password`, `Address`, `MobileNo`, `PinNo`, `Email`, `Products`) VALUES
('Gaurav', 'Katare', 'Gk', 'vivek', 'Mirzapur', '999999999', 452003, 'kataregaurav1501@gmail.com', 0),
('Raj', 'Garg', 'raj', 'raj', 'Dewas', '9898989898', 452001, 'rajgarg150873@gmail.com', 0),
('vrvrv', 'rv', 'rvv', 'rvrv', 'rvrv', '123', 13124, 'rvv', 0),
('Vivek', 'Rathi', 'vr', 'yo', '27/1,Race Course Road\nSilver Park Colony', '7748843519', 452003, 'vivekrathi53@gmail.com', 0),
('vrvrv', 'vrv', 'vrrv', 'vrvr', 'rvvrv', '9899', 123, 'rvv', 0),
('vrvv', 'rvr', 'vrv', 'vrv', 'davd', '123', 12, 'rrees', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ProductsTable`
--

CREATE TABLE `ProductsTable` (
  `ProductId` int(100) NOT NULL,
  `Retailer` int(100) NOT NULL,
  `Price` int(100) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Category` varchar(100) NOT NULL,
  `Description` text NOT NULL,
  `Discount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ProductsTable`
--

INSERT INTO `ProductsTable` (`ProductId`, `Retailer`, `Price`, `Quantity`, `Category`, `Description`, `Discount`) VALUES
(1, 1, 100, 10, 'Maggie', 'Maggie 6 pieces pack Ready for you in just Rs100 hurray!!', 20),
(2, 2, 200, 12, 'Maggie', '2 min Masala Maggie of 12 packs', 50),
(3, 1, 20000, 13, 'Maggie', 'noodles', 500);

-- --------------------------------------------------------

--
-- Table structure for table `RetailerTable`
--

CREATE TABLE `RetailerTable` (
  `FirstName` int(11) NOT NULL,
  `LastName` int(11) NOT NULL,
  `UserName` int(11) NOT NULL,
  `Password` int(11) NOT NULL,
  `Address` int(11) NOT NULL,
  `MobileNo` int(10) NOT NULL,
  `PinNo` int(10) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `ProductsList` int(11) NOT NULL,
  `TurnOver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TransactionTable`
--

CREATE TABLE `TransactionTable` (
  `TransactionId` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `CustomerUserName` varchar(11) NOT NULL,
  `Time` datetime NOT NULL,
  `Address` text NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TransactionTable`
--

INSERT INTO `TransactionTable` (`TransactionId`, `ProductId`, `CustomerUserName`, `Time`, `Address`, `Quantity`, `Status`) VALUES
(1, 1, 'vr', '2018-09-01 00:00:00', 'indore', 1, 1),
(2, 2, 'vr', '2018-09-01 00:00:00', 'indore', 1, 2),
(3, 3, 'vr', '2018-09-01 00:00:00', 'indore', 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CustomerTable`
--
ALTER TABLE `CustomerTable`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `ProductsTable`
--
ALTER TABLE `ProductsTable`
  ADD PRIMARY KEY (`ProductId`);

--
-- Indexes for table `RetailerTable`
--
ALTER TABLE `RetailerTable`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `TransactionTable`
--
ALTER TABLE `TransactionTable`
  ADD PRIMARY KEY (`TransactionId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
