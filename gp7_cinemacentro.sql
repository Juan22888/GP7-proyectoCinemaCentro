-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2025 a las 16:15:18
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
  `estado` tinyint(1) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comprador`
--

INSERT INTO `comprador` (`codComprador`, `dni`, `nombre`, `fechaNac`, `estado`, `password`) VALUES
(7, 45801072, 'Franco Coria', '2004-06-07', 1, 'Kakito123'),
(9, 33848316, 'Aquiles Bailo', '2005-11-08', 0, 'Aquilesbailo123');

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
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `fechaFuncion` date NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `codSala` int(11) NOT NULL,
  `precioLugar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `funcion`
--

INSERT INTO `funcion` (`codFuncion`, `codPelicula`, `idioma`, `es3d`, `subtitulada`, `horaInicio`, `horaFin`, `fechaFuncion`, `estado`, `codSala`, `precioLugar`) VALUES
(12, 23, 'Ingles', 1, 1, '21:30:00', '23:50:00', '2025-11-14', 1, 11, 3000);

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
(23, 'Harry Potter', 'J.K Rowling', 'Ronie Wesley, Harry Potter, Hermione', 'Estados Unidos', 'Romantico', '2025-10-27', 1),
(25, '28 Days Later', 'David Boyle', 'Frank Sinatra', 'Britanico', 'CIencia Ficcion', '2025-11-12', 0),
(26, 'Ready Player One', 'Clark Host', 'Nick Boss, Clark Robbertson', 'Britanico', 'CIencia Ficcion', '2025-11-20', 1);

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
(11, 1, 1, 170, 1),
(12, 2, 1, 200, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticketcompra`
--

CREATE TABLE `ticketcompra` (
  `codTicket` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
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
  ADD PRIMARY KEY (`codSala`),
  ADD UNIQUE KEY `nroSala` (`nroSala`);

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
  MODIFY `codComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  MODIFY `codDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `funcion`
--
ALTER TABLE `funcion`
  MODIFY `codFuncion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `codLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=535;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `codPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `codSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

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
