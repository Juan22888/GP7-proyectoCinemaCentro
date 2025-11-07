-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-11-2025 a las 16:04:19
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gp7_cinemacentro`
--
CREATE DATABASE IF NOT EXISTS `gp7_cinemacentro` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gp7_cinemacentro`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprador`
--

CREATE TABLE `comprador` (
  `codComprador` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `fechaNac` date NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comprador`
--

INSERT INTO `comprador` (`codComprador`, `dni`, `nombre`, `fechaNac`, `password`) VALUES
(5, 45801072, 'Franco', '2004-06-07', 'Chamo123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleticket`
--

CREATE TABLE `detalleticket` (
  `codDetalle` int(11) NOT NULL,
  `codLugar` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funcion`
--

CREATE TABLE `funcion` (
  `codFuncion` int(11) NOT NULL,
  `codPelicula` int(11) NOT NULL,
  `idioma` varchar(20) NOT NULL,
  `es3d` tinyint(1) NOT NULL,
  `subtitulada` tinyint(1) NOT NULL,
  `horaInicio` datetime NOT NULL,
  `horaFin` datetime NOT NULL,
  `codSala` int(11) NOT NULL,
  `precioLugar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `funcion`
--

INSERT INTO `funcion` (`codFuncion`, `codPelicula`, `idioma`, `es3d`, `subtitulada`, `horaInicio`, `horaFin`, `codSala`, `precioLugar`) VALUES
(5, 23, 'Ingles', 1, 1, '2025-11-19 20:20:55', '2025-11-19 21:30:55', 10, 2500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lugar`
--

CREATE TABLE `lugar` (
  `codLugar` int(11) NOT NULL,
  `fila` char(1) NOT NULL,
  `numero` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `codFuncion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `lugar`
--

INSERT INTO `lugar` (`codLugar`, `fila`, `numero`, `estado`, `codFuncion`) VALUES
(273, 'A', 1, 0, 5),
(274, 'A', 2, 0, 5),
(275, 'A', 3, 0, 5),
(276, 'A', 4, 0, 5),
(277, 'A', 5, 0, 5),
(278, 'A', 6, 0, 5),
(279, 'A', 7, 1, 5),
(280, 'A', 8, 0, 5),
(281, 'A', 9, 0, 5),
(282, 'A', 10, 0, 5),
(283, 'A', 11, 0, 5),
(284, 'A', 12, 0, 5),
(285, 'A', 13, 0, 5),
(286, 'A', 14, 0, 5),
(287, 'A', 15, 0, 5),
(288, 'A', 16, 0, 5),
(289, 'A', 17, 0, 5),
(290, 'A', 18, 0, 5),
(291, 'A', 19, 0, 5),
(292, 'A', 20, 0, 5),
(293, 'B', 1, 0, 5),
(294, 'B', 2, 0, 5),
(295, 'B', 3, 0, 5),
(296, 'B', 4, 0, 5),
(297, 'B', 5, 0, 5),
(298, 'B', 6, 0, 5),
(299, 'B', 7, 0, 5),
(300, 'B', 8, 0, 5),
(301, 'B', 9, 0, 5),
(302, 'B', 10, 0, 5),
(303, 'B', 11, 0, 5),
(304, 'B', 12, 0, 5),
(305, 'B', 13, 0, 5),
(306, 'B', 14, 0, 5),
(307, 'B', 15, 0, 5),
(308, 'B', 16, 0, 5),
(309, 'B', 17, 0, 5),
(310, 'B', 18, 0, 5),
(311, 'B', 19, 0, 5),
(312, 'B', 20, 0, 5),
(313, 'C', 1, 0, 5),
(314, 'C', 2, 0, 5),
(315, 'C', 3, 0, 5),
(316, 'C', 4, 0, 5),
(317, 'C', 5, 0, 5),
(318, 'C', 6, 0, 5),
(319, 'C', 7, 0, 5),
(320, 'C', 8, 0, 5),
(321, 'C', 9, 0, 5),
(322, 'C', 10, 0, 5),
(323, 'C', 11, 0, 5),
(324, 'C', 12, 0, 5),
(325, 'C', 13, 0, 5),
(326, 'C', 14, 0, 5),
(327, 'C', 15, 0, 5),
(328, 'C', 16, 0, 5),
(329, 'C', 17, 0, 5),
(330, 'C', 18, 0, 5),
(331, 'C', 19, 0, 5),
(332, 'C', 20, 0, 5),
(333, 'D', 1, 0, 5),
(334, 'D', 2, 0, 5),
(335, 'D', 3, 0, 5),
(336, 'D', 4, 0, 5),
(337, 'D', 5, 0, 5),
(338, 'D', 6, 0, 5),
(339, 'D', 7, 0, 5),
(340, 'D', 8, 0, 5),
(341, 'D', 9, 0, 5),
(342, 'D', 10, 0, 5),
(343, 'D', 11, 0, 5),
(344, 'D', 12, 0, 5),
(345, 'D', 13, 0, 5),
(346, 'D', 14, 0, 5),
(347, 'D', 15, 0, 5),
(348, 'D', 16, 0, 5),
(349, 'D', 17, 0, 5),
(350, 'D', 18, 0, 5),
(351, 'D', 19, 0, 5),
(352, 'D', 20, 0, 5),
(353, 'E', 1, 0, 5),
(354, 'E', 2, 0, 5),
(355, 'E', 3, 0, 5),
(356, 'E', 4, 0, 5),
(357, 'E', 5, 0, 5),
(358, 'E', 6, 0, 5),
(359, 'E', 7, 0, 5),
(360, 'E', 8, 0, 5),
(361, 'E', 9, 0, 5),
(362, 'E', 10, 0, 5),
(363, 'E', 11, 0, 5),
(364, 'E', 12, 0, 5),
(365, 'E', 13, 0, 5),
(366, 'E', 14, 0, 5),
(367, 'E', 15, 0, 5),
(368, 'E', 16, 0, 5),
(369, 'E', 17, 0, 5),
(370, 'E', 18, 0, 5),
(371, 'E', 19, 0, 5),
(372, 'E', 20, 0, 5),
(373, 'F', 1, 0, 5),
(374, 'F', 2, 0, 5),
(375, 'F', 3, 0, 5),
(376, 'F', 4, 0, 5),
(377, 'F', 5, 0, 5),
(378, 'F', 6, 0, 5),
(379, 'F', 7, 0, 5),
(380, 'F', 8, 0, 5),
(381, 'F', 9, 0, 5),
(382, 'F', 10, 0, 5),
(383, 'F', 11, 0, 5),
(384, 'F', 12, 0, 5),
(385, 'F', 13, 0, 5),
(386, 'F', 14, 0, 5),
(387, 'F', 15, 0, 5),
(388, 'F', 16, 0, 5),
(389, 'F', 17, 0, 5),
(390, 'F', 18, 0, 5),
(391, 'F', 19, 0, 5),
(392, 'F', 20, 0, 5),
(393, 'G', 1, 0, 5),
(394, 'G', 2, 0, 5),
(395, 'G', 3, 0, 5),
(396, 'G', 4, 0, 5),
(397, 'G', 5, 0, 5),
(398, 'G', 6, 0, 5),
(399, 'G', 7, 0, 5),
(400, 'G', 8, 0, 5),
(401, 'G', 9, 0, 5),
(402, 'G', 10, 0, 5),
(403, 'G', 11, 0, 5),
(404, 'G', 12, 0, 5),
(405, 'G', 13, 0, 5),
(406, 'G', 14, 0, 5),
(407, 'G', 15, 0, 5),
(408, 'G', 16, 0, 5),
(409, 'G', 17, 0, 5),
(410, 'G', 18, 0, 5),
(411, 'G', 19, 0, 5),
(412, 'G', 20, 0, 5),
(413, 'H', 1, 0, 5),
(414, 'H', 2, 0, 5),
(415, 'H', 3, 0, 5),
(416, 'H', 4, 0, 5),
(417, 'H', 5, 0, 5),
(418, 'H', 6, 0, 5),
(419, 'H', 7, 0, 5),
(420, 'H', 8, 0, 5),
(421, 'H', 9, 0, 5),
(422, 'H', 10, 0, 5),
(423, 'H', 11, 0, 5),
(424, 'H', 12, 0, 5),
(425, 'H', 13, 0, 5),
(426, 'H', 14, 0, 5),
(427, 'H', 15, 0, 5),
(428, 'H', 16, 0, 5),
(429, 'H', 17, 0, 5),
(430, 'H', 18, 0, 5),
(431, 'H', 19, 0, 5),
(432, 'H', 20, 0, 5),
(433, 'I', 1, 0, 5),
(434, 'I', 2, 0, 5),
(435, 'I', 3, 0, 5),
(436, 'I', 4, 0, 5),
(437, 'I', 5, 0, 5),
(438, 'I', 6, 0, 5),
(439, 'I', 7, 0, 5),
(440, 'I', 8, 0, 5),
(441, 'I', 9, 0, 5),
(442, 'I', 10, 0, 5),
(443, 'I', 11, 0, 5),
(444, 'I', 12, 0, 5),
(445, 'I', 13, 0, 5),
(446, 'I', 14, 0, 5),
(447, 'I', 15, 0, 5),
(448, 'I', 16, 0, 5),
(449, 'I', 17, 0, 5),
(450, 'I', 18, 0, 5),
(451, 'I', 19, 0, 5),
(452, 'I', 20, 0, 5),
(453, 'J', 1, 0, 5),
(454, 'J', 2, 0, 5),
(455, 'J', 3, 0, 5),
(456, 'J', 4, 0, 5),
(457, 'J', 5, 0, 5),
(458, 'J', 6, 0, 5),
(459, 'J', 7, 0, 5),
(460, 'J', 8, 0, 5),
(461, 'J', 9, 0, 5),
(462, 'J', 10, 0, 5),
(463, 'J', 11, 0, 5),
(464, 'J', 12, 0, 5),
(465, 'J', 13, 0, 5),
(466, 'J', 14, 0, 5),
(467, 'J', 15, 0, 5),
(468, 'J', 16, 0, 5),
(469, 'J', 17, 0, 5),
(470, 'J', 18, 0, 5),
(471, 'J', 19, 0, 5),
(472, 'J', 20, 0, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pelicula`
--

CREATE TABLE `pelicula` (
  `codPelicula` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `director` varchar(20) NOT NULL,
  `actores` varchar(100) NOT NULL,
  `origen` varchar(30) NOT NULL,
  `genero` varchar(25) NOT NULL,
  `estreno` date NOT NULL,
  `encartelera` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pelicula`
--

INSERT INTO `pelicula` (`codPelicula`, `titulo`, `director`, `actores`, `origen`, `genero`, `estreno`, `encartelera`) VALUES
(23, 'Harry Popotter', 'J.K Rowling', 'Ronie Wesley, Harry Potter, Hermione', 'Estados Unidos', 'Romantico', '2025-10-29', 1),
(24, 'Mulan', 'Richard Hard', 'Eddie Philman, Laurel Williams', 'Estados Unidos', 'Animada', '2025-11-12', 1),
(25, '28 Days Later', 'David Boyle', 'Frank Sinatra', 'Britanico', 'CIencia Ficcion', '2025-11-12', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `codSala` int(11) NOT NULL,
  `nroSala` int(11) NOT NULL,
  `apta3d` tinyint(1) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`codSala`, `nroSala`, `apta3d`, `capacidad`, `estado`) VALUES
(10, 1, 1, 200, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticketcompra`
--

CREATE TABLE `ticketcompra` (
  `codTicket` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaFuncion` date NOT NULL,
  `monto` double NOT NULL,
  `metodoPago` tinyint(1) NOT NULL,
  `codComprador` int(11) NOT NULL,
  `codDetalle` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comprador`
--
ALTER TABLE `comprador`
  ADD PRIMARY KEY (`codComprador`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD PRIMARY KEY (`codDetalle`),
  ADD UNIQUE KEY `codLugar_2` (`codLugar`),
  ADD KEY `codLugar` (`codLugar`);

--
-- Indices de la tabla `funcion`
--
ALTER TABLE `funcion`
  ADD PRIMARY KEY (`codFuncion`),
  ADD KEY `codSala` (`codSala`),
  ADD KEY `codPelicula` (`codPelicula`);

--
-- Indices de la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD PRIMARY KEY (`codLugar`),
  ADD UNIQUE KEY `llaveCompuesta` (`fila`,`numero`),
  ADD KEY `codFuncion` (`codFuncion`);

--
-- Indices de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  ADD PRIMARY KEY (`codPelicula`),
  ADD KEY `titulo` (`titulo`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`codSala`);

--
-- Indices de la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  ADD PRIMARY KEY (`codTicket`),
  ADD KEY `codComprador` (`codComprador`),
  ADD KEY `codDetalle` (`codDetalle`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comprador`
--
ALTER TABLE `comprador`
  MODIFY `codComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  MODIFY `codDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `funcion`
--
ALTER TABLE `funcion`
  MODIFY `codFuncion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `codLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=473;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `codPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `codSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  MODIFY `codTicket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  ADD CONSTRAINT `detalleticket_ibfk_2` FOREIGN KEY (`codLugar`) REFERENCES `lugar` (`codLugar`);

--
-- Filtros para la tabla `funcion`
--
ALTER TABLE `funcion`
  ADD CONSTRAINT `funcion_ibfk_2` FOREIGN KEY (`codSala`) REFERENCES `sala` (`codSala`),
  ADD CONSTRAINT `funcion_ibfk_3` FOREIGN KEY (`codPelicula`) REFERENCES `pelicula` (`codPelicula`);

--
-- Filtros para la tabla `lugar`
--
ALTER TABLE `lugar`
  ADD CONSTRAINT `lugar_ibfk_1` FOREIGN KEY (`codFuncion`) REFERENCES `funcion` (`codFuncion`);

--
-- Filtros para la tabla `ticketcompra`
--
ALTER TABLE `ticketcompra`
  ADD CONSTRAINT `ticketcompra_ibfk_1` FOREIGN KEY (`codComprador`) REFERENCES `comprador` (`codComprador`),
  ADD CONSTRAINT `ticketcompra_ibfk_2` FOREIGN KEY (`codDetalle`) REFERENCES `detalleticket` (`codDetalle`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
