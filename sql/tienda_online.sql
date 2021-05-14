-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2021 a las 23:16:40
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda_online`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Ratones', 'Ratones de todos los tipos y modelos'),
(2, 'Ordenadores', 'Toda la gama de ordenadores desde los más bajo de precio hasta los más alto'),
(5, 'Teclados', 'Teclados inalambricos y con cable'),
(7, 'Monitores', 'Monitores para ordenadores de todos los tipos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `clave`, `valor`, `tipo`) VALUES
(1, 'nombre', 'AloShop', 'texto'),
(2, 'direccion', 'Calle Santa Clara 123, Zamora', 'texto'),
(3, 'cif', 'A12389123', 'texto'),
(4, 'numFactura', '8', 'entero'),
(5, 'telefono', '689960179', 'texto'),
(6, 'rutaArchivos', 'C:/Users/Formacion/Desktop/ficheros/', 'text'),
(7, 'ficheroExportar', 'listadoProductosExportar.xls', 'texto'),
(8, 'ficheroImportar', 'listadoProductosImportar.xls', 'texto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descuentos`
--

CREATE TABLE `descuentos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `fecha_inicio` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_fin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descuentos`
--

INSERT INTO `descuentos` (`id`, `codigo`, `descuento`, `fecha_inicio`, `fecha_fin`) VALUES
(3, 'DESC10', 10, '2021-05-23 10:01:22', '2021-05-16 10:01:22'),
(4, 'DESC20', 20, '2021-05-29 10:04:20', '2021-05-02 10:04:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pedido`
--

CREATE TABLE `detalles_pedido` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `precio_unidad` float DEFAULT NULL,
  `unidades` int(11) DEFAULT NULL,
  `impuesto` float DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalles_pedido`
--

INSERT INTO `detalles_pedido` (`id`, `id_pedido`, `id_producto`, `precio_unidad`, `unidades`, `impuesto`, `total`) VALUES
(56, 36, 54, 68, 1, 10, 68),
(57, 36, 57, 34, 2, 10, 68),
(58, 36, 56, 21, 1, 10, 21),
(60, 37, 60, 950, 2, 10, 1900),
(61, 37, 53, 127, 1, 10, 127),
(62, 38, 65, 90, 1, 10, 90),
(63, 39, 53, 127, 1, 10, 127),
(64, 39, 56, 21, 2, 10, 42),
(65, 40, 61, 45, 1, 10, 45),
(66, 41, 56, 21, 1, 10, 21),
(67, 41, 53, 127, 1, 10, 127),
(68, 42, 61, 45, 1, 10, 45),
(69, 42, 60, 950, 1, 10, 950),
(70, 43, 64, 76, 1, 10, 76),
(71, 43, 67, 125, 1, 10, 125),
(72, 43, 53, 127, 1, 10, 127);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuestos`
--

CREATE TABLE `impuestos` (
  `id` int(11) NOT NULL,
  `impuesto` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pago`
--

CREATE TABLE `metodos_pago` (
  `id` int(11) NOT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `metodos_pago`
--

INSERT INTO `metodos_pago` (`id`, `metodo_pago`) VALUES
(1, 'Tarjeta'),
(2, 'PayPal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones_menu`
--

CREATE TABLE `opciones_menu` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `nombre_opcion` varchar(255) DEFAULT NULL,
  `url_opcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `opciones_menu`
--

INSERT INTO `opciones_menu` (`id`, `id_rol`, `nombre_opcion`, `url_opcion`) VALUES
(1, 1, 'Productos', 'producto/listar'),
(2, 1, 'Usuarios', 'usuario/listar'),
(3, 1, 'Roles', 'rol/listar'),
(4, 1, 'Categorias', 'categoria/listar'),
(5, 1, 'Pedidos', 'pedido/listar'),
(6, 3, 'Pedidos', 'pedido/listar/client'),
(7, 2, 'Pedidos', 'pedido/listar'),
(8, 1, 'Configuracion', 'configuracion/listar'),
(9, 2, 'Usuarios', 'usuario/listar'),
(10, 2, 'Productos', 'producto/listar'),
(12, 2, 'Categorias', 'categoria/listar'),
(13, 1, 'Proveedores', 'proveedor/listar'),
(14, 2, 'Proveedores', 'proveedor/listar'),
(15, 1, 'Estadisticas', 'usuario/stats');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `metodo_pago` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `num_factura` varchar(255) DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id`, `id_usuario`, `fecha`, `metodo_pago`, `estado`, `num_factura`, `total`) VALUES
(36, 2, '2021-05-13 20:15:29', 'PayPal', 'Enviado', '2021-7', 110.7),
(37, 2, '2021-05-13 20:15:54', 'PayPal', 'Pendiente', '####', 2027),
(38, 2, '2021-05-13 20:16:20', 'PayPal', 'Pendiente Cancelar', '####', 81),
(39, 26, '2021-05-13 20:17:54', 'Tarjeta', 'Pendiente', '####', 133.2),
(40, 26, '2021-05-13 20:18:04', 'Tarjeta', 'Pendiente', '####', 36),
(41, 27, '2021-05-13 20:18:35', 'Tarjeta', 'Cancelado', '####', 133.2),
(42, 27, '2021-05-13 20:18:49', 'PayPal', 'Enviado', '2021-8', 796),
(43, 27, '2021-05-13 20:19:10', 'Tarjeta', 'Pendiente Cancelar', '####', 262.4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `fecha_alta` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_baja` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `impuesto` float DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `nombre`, `descripcion`, `precio`, `stock`, `fecha_alta`, `fecha_baja`, `impuesto`, `imagen`) VALUES
(53, 7, 'Monitor Asus', 'Monitor de 27\" Full HD 4k, con una tasa de refresco de 144hz', 127, 5, '2021-05-13 19:45:53', '2021-05-13 19:45:53', 10, 'monitor.jfif'),
(54, 7, 'Monitor Dell', 'Monitor de 24\" IPS con HDR10, colores RGB y tasa de refresco de 60hz', 68, 24, '2021-05-13 19:47:38', '2021-05-13 19:47:38', 10, 'monitor2.jpg'),
(55, 1, 'Logitech MX90', 'Raton logitech inalambrico hasta 3 dispositivos para conectar al mismo tiempo', 89, 4, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'raton4.jfif'),
(56, 1, 'Asus Strike 40', 'Raton asus el mejor para el confort y comodidad', 21, 5, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'raton2.jfif'),
(57, 1, 'Raton Inalambrico', 'Raton inalambrico compatible con movil y tablet', 34, 6, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'raton3.jfif'),
(58, 2, 'Asus Slim', 'Ordenador Asus super ligero de color plata I7-9500 12GB', 546, 2, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'ordenador.jpg'),
(59, 2, 'Asus Zenbook', 'Ordenador Asus tactil con 32 Gb de RAM y 128 Gb de almacenamiento ideal para cualquier estudiante', 600, 4, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'ordenador2.jfif'),
(60, 2, 'Dell SQ76T', 'Ordenador Dell con i9-900K y 32Gb de RAM', 950, 78, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'ordenador2.jfif'),
(61, 5, 'Logitec MX180', 'Teclado logitech inalambrico retroiluminado con tres modos de pulsacion', 45, 12, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'teclado.jfif'),
(63, 5, 'New Skill Gaming', 'Teclado para los m�s Gaming reptroiluminado con cambio de color', 67, 0, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'teclado3.jfif'),
(64, 5, 'New Skill YU76', 'Teclado 60% Gaming retroiluminado con 3 modos de pulsacion', 76, 43, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'teclado.jfif'),
(65, 7, 'Dell UY27', 'Monitor de 27\" FullHD con HDR10 y RGB', 90, 12, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'monitor.jfif'),
(66, 7, 'LG UIU98', 'Monitor LG con disintos modos de adaptacion para girar la pantalla', 120, 3, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'monitor3.jfif'),
(67, 7, 'Samsung 28P', 'Monitor Samsung de 28\" ideal para ver series o peliculas', 125, 23, '2021-05-13 20:11:59', '2021-05-13 20:11:59', 10, 'monitor2.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedores`
--

CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedores`
--

INSERT INTO `proveedores` (`id`, `nombre`, `direccion`, `localidad`, `provincia`, `telefono`, `cif`, `email`) VALUES
(3, 'PcComponentes', 'Calle Madrid', 'Madrid', 'Madrid', '980645321', '12345678A', 'pccomponentes@gmail.com'),
(4, 'Asus', 'Calle real', 'Barcelona', 'Barcelona', '980654325', '98765432W', 'asus@gmail.com'),
(5, 'Lenovo', 'Avenida Portugal', 'Zamora', 'Zamora', '980654543', '123123123P', 'lenovo@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`) VALUES
(1, 'Admin'),
(2, 'Empleado'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `id_rol` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `id_rol`, `email`, `clave`, `nombre`, `apellido1`, `apellido2`, `direccion`, `provincia`, `localidad`, `telefono`, `dni`) VALUES
(1, 1, 'admin@tiendaonline.es', 'MTIzNA==', 'Admin', NULL, NULL, NULL, 'Zamora', 'Zamora', NULL, NULL),
(2, 3, 'sergio@gmail.com', 'c2VyZ2lv', 'Sergio', 'Alonso', 'Caso', 'Federico Silva ', 'Zamora', 'Benavente', '675545431', '71041487F'),
(4, 2, 'gemma@gmail.com', 'Z2VtbWE=', 'Gemma', 'Saludes', 'Murciego', 'Calle carros', 'Madrid', 'Madrid', '654432123', '11234321Q'),
(11, 2, 'david@gmail.com', 'ZGF2aWQ=', 'David', 'Vicente', 'Gonzalez', 'Calle real', 'zamora', 'Zamora', '675564538', '71041462R'),
(12, 1, 'pepe@gmail.com', 'Y0dWd1pRPT0=', 'Pepe', 'Perez', 'Perico', 'Calle renueva', 'Zamora', 'Benavente', '654434231', '71253625T'),
(26, 3, 'manuel@gmail.com', 'bWFudWVs', 'Manuel', 'Alonso', 'Fernandez', 'Avenida Real', 'Zamora', 'Zamora', '654453432', '11987265F'),
(27, 3, 'merce@gmail.com', 'bWVyY2U=', 'Merce', 'De Caso', 'Torices', 'Calle Santa Clara', 'Zamora', 'Zamora', '654435241', '11231524Y'),
(28, 1, 'diego@gmail.com', 'ZGllZ28=', 'Diego', 'Garcia', 'Fernandez', 'Avenida Luis Moran', 'Valladolid', 'Valladolid', '654432123', '12213222P'),
(29, 2, 'juan@gmail.com', 'anVhbg==', 'Juan', 'Lopez', 'Garcia', 'Calle nueva', 'Zamora', 'Zamora', '654454345', '12312332T'),
(30, 3, 'mario@gmail.com', 'bWFyaW8=', 'Mario', 'Casas', 'Gonzalez', 'Avenida Federico Silva', 'Zamora', 'Zamora', '675564543', '11876656R'),
(34, 3, 'sergiolitos@gmail.com', 'cw==', 's', 's', 's', 's', 'Albacete', 's', '123456789', 's');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoraciones`
--

CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `valoracion` int(11) DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `valoraciones`
--

INSERT INTO `valoraciones` (`id`, `id_producto`, `id_usuario`, `valoracion`, `comentario`) VALUES
(6, 54, 2, 5, 'Muy buen monitor y no cansa para nada la vista'),
(7, 57, 2, 4, 'Raton excelente'),
(8, 56, 2, 5, 'Me encanta este teclado no lo cambiaria por nada'),
(9, 61, 27, 5, 'De los mejores teclados que he probado nunca'),
(10, 60, 27, 1, 'La peor compra que he hecho en mucho tiempo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `descuentos`
--
ALTER TABLE `descuentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `detalles_pedido`
--
ALTER TABLE `detalles_pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT de la tabla `impuestos`
--
ALTER TABLE `impuestos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pago`
--
ALTER TABLE `metodos_pago`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `opciones_menu`
--
ALTER TABLE `opciones_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT de la tabla `proveedores`
--
ALTER TABLE `proveedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `valoraciones`
--
ALTER TABLE `valoraciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
