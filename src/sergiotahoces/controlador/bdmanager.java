package sergiotahoces.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import sergiotahoces.modelo.Elemento;
import sergiotahoces.modelo.conexion;

public class bdmanager {
	private conexion conexion1;
	private Connection cn;
	private PreparedStatement ps;

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

	public void Modificar() throws SQLException {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();

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

		String sql = "update elementos set nombre = ?, descripcion = ?, caracteristica = ? where id = ?";

		ps = cn.prepareStatement(sql);

		ps.setString(1, nombre);
		ps.setString(2, descripcion);
		ps.setString(3, caracteristica);
		ps.setInt(4, id);

		ps.executeUpdate();

	}

	public void eliminarElemento(int id) {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		String sql = "delete from elementos where id = ?";
		try {
			ps = cn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void eliminarTodo() {
		String sql = "truncate table elementos";
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		try {
			ps = cn.prepareStatement(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void buscarUno(int id) {
		conexion conexion1 = new conexion();
		Connection cn = conexion1.TestConexion();
		String sql = "select * from elementos where id = ?";
		try {
			PreparedStatement insertPstms = cn.prepareStatement(sql);
			insertPstms.setInt(1, id);
			ResultSet rs = insertPstms.executeQuery();
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				String caracteristicas = rs.getString("caracteristica");
				Elemento e = new Elemento(id, nombre, descripcion, caracteristicas);
				System.out.println(e.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void moverDatos(HashMap<Integer, Elemento> e) {
		Iterator<Elemento> iterador = e.values().iterator();
		while (iterador.hasNext()) {
			try {
				escribirElemento(iterador.next());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
