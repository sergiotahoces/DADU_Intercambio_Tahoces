package sergiotahoces.vista;
import java.util.HashMap;
import java.util.Scanner;

import sergiotahoces.controlador.bdmanager;
import sergiotahoces.controlador.filemanager;
import sergiotahoces.controlador.hibernatemanager;
import sergiotahoces.modelo.Elemento;
import sergiotahoces.modelo.JsonClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Main {
	static filemanager f1 = new filemanager();
	static bdmanager bd1 = new bdmanager();
	static HashMap<Integer, Elemento> datos1 = new HashMap<Integer, Elemento>();
	static hibernatemanager h1 = new hibernatemanager();
	static JsonClass jc = new JsonClass();
	
	public static void main(String[] args) throws SQLException {
		int id;
		Elemento id2;
		Scanner sc = new Scanner(System.in);
		System.out.println("*--------------------------------------------------------------------------*");
		System.out.println("");
		System.out.println("* OPCIONES");
		System.out.println("* Pulsa 1 si: Mostrar por pantalla la información de la base de datos");
		System.out.println("* Pulsa 2 si: Añadir 1 a BD");
		System.out.println("* Pulsa 3 si: Mostrar datos de Fichero");
		System.out.println("* Pulsa 4 si: Añadir 1 a Fichero");
		System.out.println("* Pulsa 5 si: Pasar los datos que haya en la BD al fichero");
		System.out.println("* Pulsa 6 si: Pasar los datos que haya en el Fichero a la BD");
		System.out.println("* Pulsa 7 si: Modifica los datos de la BD");
		System.out.println("* Pulsa 8 si: Mostrar datos del fichero");
		System.out.println("* Pulsa 9 si: Modifica un perfil del fichero");
		System.out.println("* Pulsa 10 si: Borrar dato de base de datos");
		System.out.println("* Pulsa 11 si: Borrar dato de fichero");
		System.out.println("* Pulsa 12 si: Borrar todos los datos de fichero");
		System.out.println("* Pulsa 13 si: Borrar todos los datos base de datos");
		System.out.println("* Pulsa 14 si: Buscar un elemento en base de datos");
		System.out.println("* Pulsa 15 si: Buscar un elemento en fichero");
		System.out.println("* Pulsa 16 si: Modifica los datos de la BD");
		System.out.println("* Pulsa 17 si: Pasar datos de fichero a base");
		System.out.println("* Pulsa 18 si: Pasar fichero a base");
		System.out.println("*--------------------------------------------------------------------------*");
		System.out.println("");
		System.out.println("* Metodos utilizando JSON *");
		System.out.println("");
		System.out.println("*--------------------------------------------------------------------------*");
		System.out.println("Pulsa 19 para mostrar todos los datos");
		System.out.println("Pulsa 20 para insertar un nuevo elemento");
		System.out.println("Pulsa 21 para modificar un elemento existente");
		System.out.println("Pulsa 22 para borrar un elemento");
		System.out.println("Pulsa 23 para borrar todos los elementos");
		System.out.println("Pulsa 24 para buscar un elemento. PROXIMAMENTE");
		System.out.println("Pulsa 25 para pasar los datos de A a B");
		System.out.println("*--------------------------------------------------------------------------*");
		System.out.println("");
		int option;
		option = sc.nextInt();
		switch (option) {
		case 1:
			System.out.println("OPCION 1 Elegida, Mirar la consola para comprobar los datos.");
			System.out.println("*-----------------------------------------------------------*");
			bd1.imprimir(bd1.leerEnBase());
			break;
		case 2: 
			System.out.println("OPCION 2 Elegida, has añadido 1 a la base de datos.");
			System.out.println("*-----------------------------------------------------------*");
			Elemento el = bd1.insertar();
			bd1.escribirElemento(el);
			break;
		case 3:
			System.out.println("OPCION 3 Elegida, mostrando datos en el ficheros.");
			System.out.println("*------------------------------------------------------------------*");
			datos1 = f1.leerFichero();
			for (Integer i : datos1.keySet()) {
				Elemento a = datos1.get(i);
				System.out.println(a.getId()+ " / " + a.getNombre()+ " / " + a.getCaracteristica() + " / " + a.getDescripcion());
			}
			break;
		case 4:
			System.out.println("OPCION 4 Elegida, Añadir 1 dato a fichero");
			System.out.println("*------------------------------------------------------------------*");
			Elemento elF = f1.insertar();
			f1.escrbirEnFicheroElemento(elF);
			break;
		case 5:
			System.out.println("OPCION 5 Elegida, Pasar los datos que haya en la BD al fichero");
			System.out.println("*------------------------------------------------------------------*");
			datos1 = bd1.leerEnBase();
			f1.escrbirEnFichero(datos1);
			break;
		case 6: 
			System.out.println("OPCION 6 Elegida, Pasar los datos que haya en el Fichero a la BD");
			System.out.println("*------------------------------------------------------------------*");
			datos1 = f1.leerFichero();
			bd1.escribir(datos1);
			break;
		case 7: 
			System.out.println("OPCION 7 Elegida, Modifica los datos de la BD");
			System.out.println("*------------------------------------------------------------------*");
			bd1.Modificar();
			break;
		case 8:
			System.out.println("OPCION 8 Elegida, Mostrar datos del fichero");
			System.out.println("*------------------------------------------------------------------*");
			f1.leerFichero();
			break;
		case 9:
			System.out.println("OPCION 9 Elegida, Modifica un dato del fichero");
			System.out.println("*------------------------------------------------------------------*");
			System.out.println("Introduce el id: ");
			//id=(int)sc.next();
			//f1.modificarElemento(id);
			
			break;
		case 10:
			System.out.println("OPCION 10 Elegida, Borrar dato de base de datos");
			System.out.println("*------------------------------------------------------------------*");
			System.out.println("Introduce el id: ");
			id=sc.nextInt();
			bd1.eliminarElemento(id);
			break;
		case 11:
			System.out.println("OPCION 11 Elegida, Borrar dato de fichero");
			System.out.println("*------------------------------------------------------------------*");
			
			break;
		case 12:
			System.out.println("OPCION 12 Elegida, Borrar todos los datos de fichero");
			System.out.println("*------------------------------------------------------------------*");
			f1.eliminarTodo();
			break;
		case 13:
			System.out.println("OPCION 13 Elegida, Borrar todos los datos base de datos");
			System.out.println("*------------------------------------------------------------------*");
			bd1.eliminarTodo();
			break;
		case 14:
			System.out.println("OPCION 14 Elegida, Buscar un elemento en base de datos");
			System.out.println("*------------------------------------------------------------------*");
			//bd1.buscarUno(id);
			break;
		case 15:
			System.out.println("OPCION 15 Elegida, Buscar un elemento en fichero");
			System.out.println("*------------------------------------------------------------------*");
			//f1.buscarUno(id);
			break;
		case 16:
			System.out.println("OPCION 16 Elegida, Modifica los datos de la BD");
			System.out.println("*------------------------------------------------------------------*");
			bd1.Modificar();
			break;
		case 17:
			System.out.println("OPCION 17 Elegida, Pasar datos de fichero a base");
			System.out.println("*------------------------------------------------------------------*");
			bd1.moverDatos(f1.leerFichero());
			break;
		case 18:
			System.out.println("OPCION 18 Elegida, Pasar fichero a base");
			System.out.println("*------------------------------------------------------------------*");
			f1.moverDatos(bd1.leerEnBase());
			break;
		case 19:
			System.out.println("OPCION 19 Elegida, mostrar todos los datos");
			System.out.println("*------------------------------------------------------------------*");
			jc.imprimir(jc.leer());
			break;
		case 20:
			System.out.println("OPCION 20 Elegida, insertar un nuevo elemento");
			System.out.println("*------------------------------------------------------------------*");
			jc.escribirUno();
			break;
		case 21:
			System.out.println("OPCION 21 Elegida, modificar un elemento existente");
			System.out.println("*------------------------------------------------------------------*");
			jc.modificar();
			break;
		case 22:
			System.out.println("OPCION 22 Elegida, borrar un elemento");
			System.out.println("*------------------------------------------------------------------*");
			jc.eliminaUno();
			break;
		case 23:
			System.out.println("OPCION 23 Elegida, borrar todos los elementos");
			System.out.println("*------------------------------------------------------------------*");
			jc.eliminar();
			break;
		case 24:
			System.out.println("OPCION 24 Elegida, buscar un elemento.");
			System.out.println("*------------------------------------------------------------------*");
			break;
		case 25:
			System.out.println("OPCION 25 Elegida, pasar los datos de A a B");
			System.out.println("*------------------------------------------------------------------*");
			System.out.println("¿Quieres pasar o recibir datos? ");
			System.out.println("1. Recibir");
			System.out.println("2. Pasar");
			int opc = Integer.parseInt(sc.nextLine());
			if(opc==1) {
				System.out.println("¿De donde quieres recibir los datos?");
				System.out.println("1. JDBC");
				System.out.println("2. Fichero");
				int opc2 = Integer.parseInt(sc.nextLine());
				if(opc2==1) {
					jc.escribir(bd1.leerEnBase());
				}
				if(opc2==2) {
					jc.escribir(f1.leerFichero());
				}
			}
			if(opc==2) {
				System.out.println("¿De donde quieres pasar los datos?");
				System.out.println("1. JDBC");
				System.out.println("2. Fichero");
				int opc2 = Integer.parseInt(sc.nextLine());
				if(opc2==1) {
					bd1.escribir(jc.leer());
				}
				if(opc2==2) {
					f1.escrbirEnFichero(jc.leer());
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}
	}

}
