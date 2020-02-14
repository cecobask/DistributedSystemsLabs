-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 14, 2020 at 09:47 AM
-- Server version: 5.7.29-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `ssn` varchar(10) NOT NULL,
  `dob` date NOT NULL,
  `name` varchar(20) NOT NULL,
  `address` varchar(50) DEFAULT 'NOT_PROVIDED',
  `salary` int(7) NOT NULL DEFAULT '0',
  `gender` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`ssn`, `dob`, `name`, `address`, `salary`, `gender`) VALUES
('01928374BA', '1960-01-29', 'Philomena Johnson', '39 Bellview, Dungarvan, Co. Waterford', 22000, 'F'),
('09876543AB', '1976-10-26', 'Sarah Conor', '10 Upper Street, Dublin', 45000, 'F'),
('10293847WB', '2001-05-09', 'Ryan Rea', '79 Main Street, Wexford', 23000, 'M'),
('12345678BW', '1996-07-14', 'Tsvetoslav Dimov', '3 The Estuary, Tramore, Co. Waterford', 30000, 'M'),
('33344466BD', '1983-03-30', 'Peter Phillips', '9 Queen Road, Waterford', 96000, 'M');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`ssn`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
