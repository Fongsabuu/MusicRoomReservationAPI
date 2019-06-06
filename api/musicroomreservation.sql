-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2019 at 09:22 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musicroomreservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `banner`
--

CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `img_name` varchar(1024) NOT NULL,
  `alt_name` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `banner`
--

INSERT INTO `banner` (`id`, `img_name`, `alt_name`) VALUES
(1, '3L.jpg', '3L.jpg'),
(2, '3M.jpg', '3M.jpg'),
(3, '2s.jpg', '2s.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `date` varchar(32) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reserve_id` int(11) NOT NULL,
  `payment_img` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `date`, `user_id`, `reserve_id`, `payment_img`) VALUES
(5, '04/06/2019', 2, 5, 'NewSimTruePack.png'),
(6, '04/06/2019', 2, 6, 'profile_default.jpg'),
(7, '04/06/2019', 11, 8, 'unnamed.jpg'),
(8, '04/06/2019', 4, 7, 'detective_pikachu_trailer_25_1920.0.jpg'),
(9, '05/06/2019', 3, 10, 'unnamed.jpg'),
(12, '05/06/2019', 11, 14, 'images.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date` varchar(32) NOT NULL,
  `room_id` int(11) NOT NULL,
  `time` varchar(32) NOT NULL,
  `hours` varchar(32) NOT NULL,
  `totalprice` varchar(32) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reserve_status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `date`, `room_id`, `time`, `hours`, `totalprice`, `user_id`, `reserve_status`) VALUES
(1, '03/06/2019', 1, '12.30-13.30 น.', '1 ชม.', '250', 3, '0'),
(2, '03/06/2019', 1, '14.30-16.30 น.', '2 ชม.', '500', 3, '0'),
(3, '03/06/2019', 1, '17.30-19.30 น.', '2 ชม.', '500', 1, '0'),
(4, '03/06/2019', 1, '20.30-21.30 น.', '1 ชม.', '250', 2, '0'),
(5, '04/06/2019', 1, '12.30-14.30 น.', '2 ชม.', '500', 2, '1'),
(6, '04/06/2019', 1, '15.30-16.30 น.', '1 ชม.', '250', 2, '2'),
(7, '04/06/2019', 2, '17.30-21.30 น.', '4 ชม.', '800', 4, '1'),
(8, '04/06/2019', 2, '12.30-14.30 น.', '2 ชม.', '400', 11, '2'),
(9, '04/06/2019', 5, '12.30-16.30 น.', '4 ชม.', '400', 3, '0'),
(10, '06/06/2019', 1, '14.30-16.30 น.', '2 ชม.', '500', 3, '2'),
(11, '05/06/2019', 1, '12.30-14.30 น.', '2 ชม.', '500', 3, '0'),
(14, '28/06/2019', 1, '12.30-16.30 น.', '4 ชม.', '1000', 11, '2');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `type` char(1) NOT NULL,
  `price` varchar(64) NOT NULL,
  `mus_instrument` varchar(1024) NOT NULL,
  `detail` varchar(1024) NOT NULL,
  `room_status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `name`, `type`, `price`, `mus_instrument`, `detail`, `room_status`) VALUES
(1, 'Room 399', 'L', '250 บาท/ชม.', 'กีตาร์ 2 ตัว, เบส 1 ตัว, กลองชุด , ไมค์ 2 ตัว', 'ขนาดห้อง 4x6 เมตร', '1'),
(2, 'Secret Room', 'M', '200 บาท/ชม.', 'กีตาร์ 1 ตัว, เบส 1 ตัว, กลองชุด , ไมค์ 1 ตัว', 'ขนาดห้อง 3.50x6 เมตร', '1'),
(3, 'Small Room', 'S', '170 บาท/ชม.', 'กีตาร์ 1 ตัว, เบส 1 ตัว, กลองชุด , ไมค์ 1 ตัว', 'ขนาดห้อง 3x5 เมตร', '1'),
(5, 'testcreate Room', 'M', '1000 บาท/ชม.', 'กีตาร์ 2 ตัว, เบส 1 ตัว, กลองชุด , ไมค์ 2 ตัว', 'ขนาดห้อง 4x6 เมตร + ผู้เชี่ยวชาญด้านดนตนรี 2 คน', '1');

-- --------------------------------------------------------

--
-- Table structure for table `room_img`
--

CREATE TABLE `room_img` (
  `id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `type_img` char(1) CHARACTER SET utf8mb4 NOT NULL,
  `name_img` varchar(1024) NOT NULL,
  `alt_img` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room_img`
--

INSERT INTO `room_img` (`id`, `room_id`, `type_img`, `name_img`, `alt_img`) VALUES
(1, 1, 'B', '1L.jpg', '1L.jpg'),
(2, 2, 'B', '4M.jpg', '4M.jpg'),
(3, 3, 'B', '1s.jpg', '1s.jpg'),
(5, 1, 'N', '12931077_1126677410695963_6757896456152794292_n.jpg', '12931077_1126677410695963_6757896456152794292_n.jpg'),
(6, 1, 'N', '12938294_1126677547362616_8642285096154961307_n.jpg', '12938294_1126677547362616_8642285096154961307_n.jpg'),
(7, 1, 'N', '12961711_1126677400695964_6045826908984083210_n.jpg', '12961711_1126677400695964_6045826908984083210_n.jpg'),
(8, 1, 'N', '12998185_1126676820696022_7585695262075974821_o.jpg', '12998185_1126676820696022_7585695262075974821_o.jpg'),
(9, 1, 'N', '12983908_1126676357362735_8175335415466493814_o.jpg', '12983908_1126676357362735_8175335415466493814_o.jpg'),
(10, 2, 'N', '10M.jpg', '10M.jpg'),
(11, 2, 'N', '9M.jpg', '9M.jpg'),
(12, 2, 'N', '7M.jpg', '7M.jpg'),
(13, 2, 'N', '12M.jpg', '12M.jpg'),
(14, 3, 'N', '5S.jpg', '5S.jpg'),
(15, 3, 'N', '6S.jpg', '6S.jpg'),
(16, 3, 'N', '12967288_1125566054140432_3562326539030863891_o.jpg', '12967288_1125566054140432_3562326539030863891_o.jpg'),
(17, 1, 'N', 'unnamed.jpg', 'unnamed.jpg'),
(18, 1, 'N', 'detective_pikachu_trailer_25_1920.0.jpg', 'detective_pikachu_trailer_25_1920.0.jpg'),
(21, 5, 'B', 'images.jpg', 'images.jpg'),
(22, 5, 'N', 'detective_pikachu_trailer_25_1920.0.jpg', 'detective_pikachu_trailer_25_1920.0.jpg'),
(23, 5, 'B', 'images.jpg', 'images.jpg'),
(24, 5, 'N', 'unnamed.jpg', 'unnamed.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `role` char(1) NOT NULL,
  `email` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `gender` varchar(16) NOT NULL,
  `birthday` varchar(32) NOT NULL,
  `address` varchar(1024) NOT NULL,
  `tel` varchar(32) NOT NULL,
  `img_user` varchar(1024) NOT NULL,
  `user_status` char(1) NOT NULL COMMENT 'สถานะการใช้งาน '
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `role`, `email`, `password`, `firstname`, `lastname`, `gender`, `birthday`, `address`, `tel`, `img_user`, `user_status`) VALUES
(1, 'm', 'cakey@gmail.com', 'cake1234', 'cake', 'sotoy', 'm', '01/06/1997', 'ถนน องค์การบริหารส่วนจังหวัด หมู่ 2 ตำบล กำแพงแสน อำเภอ กำแพงแสน นครปฐม 73140', '0838128484', 'default.jpg', '1'),
(2, 'm', 'cakeky2@gmail.com', 'cake1234', 'nongcake', 'sotoy', 'm', '01/06/1997', 'ถนน องค์การบริหารส่วนจังหวัด หมู่ 2 ตำบล กำแพงแสน อำเภอ กำแพงแสน นครปฐม 73140', '0812345678', 'default.jpg', '1'),
(3, 'm', 'test@gmail.com', '1234', 'test567', 'ichigooo1', 'female', '19/02/1996', 'ดาวดวงใหญ่ที่มีไดโนเสาร์', '0987654321', '5.jpeg', '1'),
(4, 'm', 'test2@gmail.com', '1234', 'test', 'testernaja', 'female', '19/02/1996', 'ดาวนาเเม็ก', '0987654321', 'default.jpg', '1'),
(9, 'm', 'kuroky@gmail.com', '1232456', 'Tee', 'Fongsabuu', 'Male', '01/06/2019', '70/939 เเฟลต สวนกวนตุ้ง ถ. พระราม 4 เเขวง ป้อมปราบ', '0826584786', 'default.jpg', '1'),
(10, 'a', 'admin@gmail.com', 'ad1234', 'Admin', 'Super', 'male', '12/12/1988', 'ดาวไซย่า', '66236987169', 'default.jpg', '1'),
(11, 'm', 'acer@gmail.com', '123456', 'duck', 'dududu', 'Male', '04/06/2019', '456 หมู่ 4, Boutique Apartment ห้อง 324 ตำบล กำเเพงเเสน', '0826584786', 'default.jpg', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `room_img`
--
ALTER TABLE `room_img`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`,`tel`),
  ADD UNIQUE KEY `email_2` (`email`,`tel`),
  ADD UNIQUE KEY `email_3` (`email`,`tel`),
  ADD UNIQUE KEY `email_5` (`email`,`tel`),
  ADD UNIQUE KEY `email_6` (`email`),
  ADD UNIQUE KEY `email_7` (`email`),
  ADD UNIQUE KEY `email_8` (`email`,`tel`);
ALTER TABLE `user` ADD FULLTEXT KEY `email_4` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banner`
--
ALTER TABLE `banner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `room_img`
--
ALTER TABLE `room_img`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
