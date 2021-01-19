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

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `category` varchar(70) NOT NULL,
  `phone` int(11) NOT NULL,
  `national_id` int(11) NOT NULL,
  `sector` varchar(70) NOT NULL,
  `password` int(11) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `done by` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `id` int(11) NOT NULL,
  `docType` varchar(70) DEFAULT NULL,
  `document_id` varchar(50) DEFAULT NULL,
  `name` varchar(70) NOT NULL,
  `phone` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `status` varchar(30) NOT NULL,
  `declared_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `documents`
--

INSERT INTO `documents` (`id`, `docType`, `document_id`, `name`, `phone`, `gender`, `status`, `declared_at`) VALUES
(1, NULL, '', '0', 783061718, 'male', 'available', '2021-01-17 14:36:50'),
(2, 'passport', '1234', '0', 788536278, 'male', 'deleted', '2021-01-17 14:58:44'),
(3, 'passort', '', '0', 783061718, 'male', 'available', '2021-01-17 15:01:57'),
(4, 'national Id', '', '0', 783061718, 'male', 'available', '2021-01-17 16:39:55'),
(5, 'national Id', NULL, '0', 783061718, 'male', 'available', '2021-01-17 17:57:46'),
(6, 'paspport', NULL, '0', 783621452, 'male', 'available', '2021-01-17 18:01:54');

-- --------------------------------------------------------

--
-- Table structure for table `document_type`
--

CREATE TABLE `document_type` (
  `doc_id` int(11) NOT NULL,
  `document_name` varchar(70) NOT NULL,
  `regi_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `submitted_documents`
--

CREATE TABLE `submitted_documents` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `document_type` varchar(50) NOT NULL,
  `document_id` int(11) NOT NULL,
  `document_picture` varchar(8000) NOT NULL,
  `status` varchar(20) NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `added_by` varchar(70) NOT NULL,
  `given_by` varchar(50) NOT NULL,
  `given_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(70) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `phone` int(11) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `gender`, `phone`, `password`) VALUES
(1, 'igor', '', 783061718, ''),
(2, 'ivan', '', 78552365, 'hacker'),
(3, 'jean', '', 78552365, 'rambo'),
(4, 'emmy', '', 78552365, 'rambo'),
(5, 'noel', '', 785632145, 'umukunzi'),
(6, 'kajyibwami', '', 789654123, 'kazitunga');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`category`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `document_type`
--
ALTER TABLE `document_type`
  ADD PRIMARY KEY (`doc_id`);

--
-- Indexes for table `submitted_documents`
--
ALTER TABLE `submitted_documents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `document_id` (`document_id`),
  ADD KEY `given_by` (`added_by`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `documents`
--
ALTER TABLE `documents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `document_type`
--
ALTER TABLE `document_type`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `submitted_documents`
--
ALTER TABLE `submitted_documents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `submitted_documents`
--
ALTER TABLE `submitted_documents`
  ADD CONSTRAINT `submitted_documents_ibfk_1` FOREIGN KEY (`added_by`) REFERENCES `admin` (`category`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id`) REFERENCES `documents` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
