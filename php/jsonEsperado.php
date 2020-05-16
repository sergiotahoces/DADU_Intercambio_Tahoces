<?php

/*  Formato JSON esperado */
/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	$auxCorrecto = false;
	if(isset($recibido["id"]) && isset($recibido["nombre"]) && isset($recibido["descripcion"]) && isset($recibido["caracteristica"])){
		$auxCorrecto = true;
	}
	return $auxCorrecto;
	
}
