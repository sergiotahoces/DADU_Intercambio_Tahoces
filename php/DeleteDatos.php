<?php

require 'bbdd.php'; // Incluimos fichero en la que está la coenxión con la BBDD
require 'jsonEsperado.php';

/*
 * Se mostrará siempre la información en formato json para que se pueda leer desde un html (via js)
 * o una aplicación móvil o de escritorio realizada en java o en otro lenguajes
 */
	
$query  = "Delete from elementos";	
$result = $conn->query ( $query );

$conn->close ();

die();

?>