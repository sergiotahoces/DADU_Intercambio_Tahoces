<?php

require 'bbdd.php';
require 'jsonEsperado.php';

$arrMensaje = array();

$parameters = file_get_contents("php://input");
echo $parameters;
if(isset($parameters)){

	$mensajeRecibido = json_decode($parameters, true);
	$id= $mensajeRecibido["id"];	
	$query  = "DELETE from elementos WHERE id=$id ";
	$result = $conn->query ( $query );
}
else{	// No nos han enviado el json correctamente
	
	$arrMensaje["estado"] = "error";
	$arrMensaje["mensaje"] = "EL JSON NO SE HA ENVIADO CORRECTAMENTE";
	
}

$mensajeJSON = json_encode($arrMensaje,JSON_PRETTY_PRINT);

//echo "<pre>";  // Descomentar si se quiere ver resultado "bonito" en navegador. Solo para pruebas
echo $mensajeJSON;
//echo "</pre>"; // Descomentar si se quiere ver resultado "bonito" en navegador

$conn->close ();

die();

?>