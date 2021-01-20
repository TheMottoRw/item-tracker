-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2021 at 11:01 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.3.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `declareddocuments`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(70) NOT NULL,
  `category` varchar(70) NOT NULL,
  `phone` varchar (15) NOT NULL,
  `national_id` int(11) NOT NULL,
  `sector` varchar(70) NOT NULL,
  `password` varchar(255) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `done_by` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE IF NOT EXISTS `residents` (
                         `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         `name` varchar(70) NOT NULL,
                         `phone` varchar(70) NOT NULL,
                         `gender` enum ('Male','Female','Other') NOT NULL,
                         `sector` varchar(70) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
                         `registration_date` timestamp NOT NULL DEFAULT current_timestamp()
                         
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- --------------------------------------------------------

--
-- Table structure for table `document_type`
--

CREATE TABLE IF NOT EXISTS `document_types` (
                                 `doc_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                                 `document_name` varchar(70) NOT NULL,
                                 `added_by` int(11) NOT NULL,
                                 `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
                                 `registration_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `documents`
--

CREATE TABLE IF NOT EXISTS `declared_documents` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `resident` int(11) NOT NULL,
  `document_type` int(11) DEFAULT NULL,
  `document_id` varchar(50) DEFAULT NULL,
  `status` varchar(30) NOT NULL,
  `declared_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `added_by` varchar(70) NOT NULL,
  `given_by` varchar(50) NOT NULL,
  `given_date` timestamp NOT NULL DEFAULT current_timestamp(),
  FOREIGN KEY (resident) REFERENCES residents(id),
  FOREIGN KEY (document_type) REFERENCES document_types(doc_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- Table structure for table `submitted_documents`
--

CREATE TABLE IF NOT EXISTS `submitted_documents` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `document_type` int(11) NOT NULL,
  `document_id` int(11) NOT NULL,
  `document_picture` varchar(8000) NOT NULL,
  `status` varchar(20) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `added_by` varchar(70) NOT NULL,
  `given_by` varchar(50) NOT NULL,
  `given_date` timestamp NOT NULL DEFAULT current_timestamp(),
  FOREIGN KEY (document_type) REFERENCES document_types(doc_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
