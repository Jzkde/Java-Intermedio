-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-11-2023 a las 04:11:26
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `persistencia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `desc_categoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `desc_categoria`) VALUES
(1, 'Reparacion'),
(2, 'Mantenimiento'),
(3, 'Reposicion');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `dni` int NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`dni`, `direccion`, `mail`, `nombre`) VALUES
(123, 'Palpala - Jujuy', 'd@g.com', 'Diego Gallardo'),
(456, 'Salta - Salta', 'e@s.c', 'Ernesto Sabato'),
(789, 'Posadas - Misiones', 'f@k.c', 'Frida Kahlo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencia`
--

DROP TABLE IF EXISTS `incidencia`;
CREATE TABLE IF NOT EXISTS `incidencia` (
  `id_incidencia` int NOT NULL AUTO_INCREMENT,
  `costo` float NOT NULL,
  `desc_incidencia` varchar(255) DEFAULT NULL,
  `estado` bit(1) NOT NULL,
  `fecha_incidencia` date DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  `cliente_dni` int DEFAULT NULL,
  `tecnico_id` int DEFAULT NULL,
  PRIMARY KEY (`id_incidencia`),
  KEY `FK5aqucghhxndcoa96rwxtn7vit` (`categoria_id`),
  KEY `FK5krwmcc88c97sj3q83wuibg16` (`cliente_dni`),
  KEY `FKjyr4nsy6y57iov096wcwkeorl` (`tecnico_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `incidencia`
--

INSERT INTO `incidencia` (`id_incidencia`, `costo`, `desc_incidencia`, `estado`, `fecha_incidencia`, `categoria_id`, `cliente_dni`, `tecnico_id`) VALUES
(1, 147, 'Pantalla dañada', b'1', '2023-11-01', 1, 123, 1),
(2, 258, 'Equipo responde lento', b'1', '2023-11-03', 2, 456, 2),
(3, 369, 'Boton de inicio nunca funciono', b'1', '2023-11-07', 3, 789, 3),
(4, 741, 'Equipo callo al agua', b'1', '2023-11-15', 1, 789, 1),
(5, 852, 'Equipo recalienta', b'1', '2023-11-20', 2, 456, 2),
(6, 0, 'Falla de Fabrica - Garantia', b'1', '2023-11-27', 3, 123, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
CREATE TABLE IF NOT EXISTS `tecnico` (
  `id_tecnico` int NOT NULL AUTO_INCREMENT,
  `nombre_tecnico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tecnico`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tecnico`
--

INSERT INTO `tecnico` (`id_tecnico`, `nombre_tecnico`) VALUES
(1, 'Lucia'),
(2, 'Ernesto'),
(3, 'Jose');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `incidencia`
--
ALTER TABLE `incidencia`
  ADD CONSTRAINT `FK5aqucghhxndcoa96rwxtn7vit` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id_categoria`),
  ADD CONSTRAINT `FK5krwmcc88c97sj3q83wuibg16` FOREIGN KEY (`cliente_dni`) REFERENCES `cliente` (`dni`),
  ADD CONSTRAINT `FKjyr4nsy6y57iov096wcwkeorl` FOREIGN KEY (`tecnico_id`) REFERENCES `tecnico` (`id_tecnico`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
