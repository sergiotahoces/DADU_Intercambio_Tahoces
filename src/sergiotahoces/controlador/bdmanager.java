package sergiotahoces.controlador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import sergiotahoces.modelo.Elemento;
import sergiotahoces.modelo.conexion;

public class bdmanager {

	public HashMap<Integer, Elemento> leerEnBase() throws SQLException {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		Statement stm = cn.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * FROM elementos");
		HashMap<Integer, Elemento> datos1 = new HashMap<Integer, Elemento>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String nombre = rs.getString("nombre");
			String descripcion = rs.getString("descripcion");
			String caracteristica = rs.getString("caracteristica");
			datos1.put(id, new Elemento(id, nombre, descripcion, caracteristica));
		}
		return datos1;
	}

	public void escribir(HashMap<Integer, Elemento> datos) throws SQLException {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		Statement stm = cn.createStatement();
		for (Integer i : datos.keySet()) {
			Elemento a = datos.get(i);
			stm.executeUpdate("Insert into elementos (nombre,descripcion,caracteristica) values ('" + a.getNombre()
					+ "','" + a.getDescripcion() + "','" + a.getCaracteristica() + "')");
		}
	}

	public void escribirElemento(Elemento e) throws SQLException {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		Statement stm = cn.createStatement();

		stm.executeUpdate("Insert into elementos (nombre,descripcion,caracteristica) values ('" + e.getNombre() + "','"
				+ e.getDescripcion() + "','" + e.getCaracteristica() + "')");

	}

	public void imprimir(HashMap<Integer, Elemento> datos) {
		for (Integer i : datos.keySet()) {
			Elemento a = datos.get(i);
			System.out.println(
					a.getId() + " / " + a.getNombre() + " / " + a.getDescripcion() + " / " + a.getCaracteristica());
		}
	}

	public Elemento insertar() {
		bdmanager bd1 = new bdmanager();

		Scanner sc = new Scanner(System.in);
		int id;
		String nombre;
		String descripcion;
		String caracteristica;

		System.out.println("Introduce el id: ");
		id = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce el nombre: ");
		nombre = sc.nextLine();
		System.out.println("Introduce la descripcion: ");
		descripcion = sc.nextLine();
		System.out.println("Introduce las caracteristicas: ");
		caracteristica = sc.nextLine();
		Elemento a = new Elemento(id, nombre, descripcion, caracteristica);
		return a;
	}

}
