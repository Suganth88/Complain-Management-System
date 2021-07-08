-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2021 at 12:41 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `complain_mangement_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_database`
--

CREATE TABLE `admin_database` (
  `Name` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin_database`
--

INSERT INTO `admin_database` (`Name`, `Email`, `Password`) VALUES
('Suganth', '18tucs234@skct.edu.in', '#1Megamind');

-- --------------------------------------------------------

--
-- Table structure for table `complaint_college`
--

CREATE TABLE `complaint_college` (
  `Name` varchar(100) NOT NULL,
  `Rollno` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Block` varchar(20) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Complain` varchar(100) NOT NULL,
  `Complaint_Description` varchar(500) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(20) NOT NULL,
  `UID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint_college`
--

INSERT INTO `complaint_college` (`Name`, `Rollno`, `Email`, `Block`, `Class`, `Complain`, `Complaint_Description`, `Date`, `Status`, `UID`) VALUES
('Suganth', '18tucs234', '18tucs234@skct.edu.in', 'Library', 'CR02', 'Pen', 'Pen Lost in class', '2021-07-06', 'Resolved', '1234'),
('Suganth', '18tucs234', '18tucs234@skct.edu.in', 'Library', 'CR03', 'Pen Lost', 'Losssss', '2021-07-07', 'Resolved', '8520'),
('Suganth', '18tucs234', '18tucs234@skct.edu.in', 'CSE Block', 'CR03', 'Fan not working', 'Fan not working in college', '2021-07-07', 'Resolved', '789');

-- --------------------------------------------------------

--
-- Table structure for table `complaint_hostel`
--

CREATE TABLE `complaint_hostel` (
  `Name` varchar(100) NOT NULL,
  `Rollno` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Block` varchar(20) NOT NULL,
  `Room_No` varchar(20) NOT NULL,
  `Complain` varchar(100) NOT NULL,
  `Complaint_Description` varchar(500) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(100) NOT NULL,
  `UID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint_hostel`
--

INSERT INTO `complaint_hostel` (`Name`, `Rollno`, `Email`, `Block`, `Room_No`, `Complain`, `Complaint_Description`, `Date`, `Status`, `UID`) VALUES
('Suganth', '18tucs234', '18tucs234@skct.edu.in', 'A block', '102', 'Speaker Lost', 'Speaker Lost in room', '2021-07-06', 'Resolved', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `missing_object`
--

CREATE TABLE `missing_object` (
  `Name` varchar(100) NOT NULL,
  `Roll_Number` varchar(100) NOT NULL,
  `Phone_Number` varchar(100) NOT NULL,
  `Email_ID` varchar(100) NOT NULL,
  `Missing_Object` varchar(200) NOT NULL,
  `Description` varchar(500) NOT NULL,
  `Location` varchar(100) NOT NULL,
  `Date` date NOT NULL,
  `Status` varchar(100) NOT NULL,
  `collected_by` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `missing_object`
--

INSERT INTO `missing_object` (`Name`, `Roll_Number`, `Phone_Number`, `Email_ID`, `Missing_Object`, `Description`, `Location`, `Date`, `Status`, `collected_by`) VALUES
('Suganth', '18tucs234', '9976535210', '18tucs234@skct.edu.in', 'NIl', 'Lost', 'Aroma', '2021-07-06', 'Found and Notified', 'Suganth'),
('Suganth', '18tucs234', '9976535210', '18tucs234@skct.edu.in', 'NIl', 'Lost', 'Aroma', '2021-07-06', 'Found and Notified', 'Not Specified'),
('Suganth', '18tucs234', '9976535210', '18tucs234@skct.edu.in', 'ID Card', '654546', 'dsfsdfs', '2021-07-06', 'Not Found', 'Not Specified'),
('Suganth', '18tucs234', '9976535210', '18tucs234@skct.edu.in', 'ID Card', 'Lost in aroma', 'Aroma', '2021-07-08', 'Not Found', 'Not Specified'),
('Suganth', '18tucs234', '9976535210', '18tucs234@skct.edu.in', 'Bag', 'Lost in aroma', 'Aroma', '2021-07-08', 'Not Found', 'Not Specified');

-- --------------------------------------------------------

--
-- Table structure for table `student_database`
--

CREATE TABLE `student_database` (
  `Name` varchar(100) NOT NULL,
  `Rollno` varchar(10) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_database`
--

INSERT INTO `student_database` (`Name`, `Rollno`, `Email`, `Gender`, `Password`) VALUES
('Suganth', '18tucs234', '18tucs234@skct.edu.in', 'male', '#1Megamind');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
