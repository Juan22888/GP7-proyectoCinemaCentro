-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2025 a las 23:24:39
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
  `password` varchar(20) NOT NULL,
  `metodoPago` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comprador`
--

INSERT INTO `comprador` (`codComprador`, `dni`, `nombre`, `fechaNac`, `password`, `metodoPago`) VALUES
(1, 14085034, 'Franco', '2004-06-07', 'Chamo123', 1),
(2, 33848315, 'Fernando', '1994-09-17', 'Fercho123', 1),
(3, 44838273, 'Juan', '2003-11-04', 'Juan123', 1),
(4, 33981232, 'Federico', '1979-12-09', 'Fede123', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleticket`
--

CREATE TABLE `detalleticket` (
  `codDetalle` int(11) NOT NULL,
  `codLugar` int(11) NOT NULL,
  `codTicket` int(11) NOT NULL,
  `codFuncion` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleticket`
--

INSERT INTO `detalleticket` (`codDetalle`, `codLugar`, `codTicket`, `codFuncion`, `cantidad`, `estado`) VALUES
(2, 10, 2, 1, 1, 1);

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
(1, 1, 'Ingles', 1, 1, '2025-10-21 01:00:25', '2025-10-21 01:00:25', 1, 3000);

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
(5, 'a', 5, 1, 1),
(7, 'a', 6, 1, 1),
(10, 'b', 5, 1, 1);

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
(1, 'Harry Potter', 'Juan Barzola', 'Tom Hanks, Tom Cruise, Tom Holland, Jennifer Aniston', 'Estados Unidos', 'Ciencia Ficción', '2025-10-21', 1);

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
(1, 3, 1, 170, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticketcompra`
--

CREATE TABLE `ticketcompra` (
  `codTicket` int(11) NOT NULL,
  `fechaCompra` date NOT NULL,
  `fechaFuncion` date NOT NULL,
  `monto` double NOT NULL,
  `codComprador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ticketcompra`
--

INSERT INTO `ticketcompra` (`codTicket`, `fechaCompra`, `fechaFuncion`, `monto`, `codComprador`) VALUES
(1, '2025-10-15', '2025-10-21', 5000, 1),
(2, '2025-10-07', '2025-10-14', 2500, 3);

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
  ADD KEY `codTicket` (`codTicket`),
  ADD KEY `codLugar` (`codLugar`),
  ADD KEY `codFuncion` (`codFuncion`);

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
  ADD KEY `codComprador` (`codComprador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comprador`
--
ALTER TABLE `comprador`
  MODIFY `codComprador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalleticket`
--
ALTER TABLE `detalleticket`
  MODIFY `codDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `funcion`
--
ALTER TABLE `funcion`
  MODIFY `codFuncion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `lugar`
--
ALTER TABLE `lugar`
  MODIFY `codLugar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `pelicula`
--
ALTER TABLE `pelicula`
  MODIFY `codPelicula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `sala`
--
ALTER TABLE `sala`
  MODIFY `codSala` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  ADD CONSTRAINT `detalleticket_ibfk_1` FOREIGN KEY (`codTicket`) REFERENCES `ticketcompra` (`codTicket`),
  ADD CONSTRAINT `detalleticket_ibfk_2` FOREIGN KEY (`codLugar`) REFERENCES `lugar` (`codLugar`),
  ADD CONSTRAINT `detalleticket_ibfk_3` FOREIGN KEY (`codFuncion`) REFERENCES `funcion` (`codFuncion`);

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
  ADD CONSTRAINT `ticketcompra_ibfk_1` FOREIGN KEY (`codComprador`) REFERENCES `comprador` (`codComprador`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
