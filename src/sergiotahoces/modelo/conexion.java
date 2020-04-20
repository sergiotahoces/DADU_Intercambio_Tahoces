package sergiotahoces.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conexion {

	private static String bd = "adat_intercambio";
	private static String login = "root";
	private static String pwd = "root";
	private static String url = "jdbc:mysql://localhost:8888/" + bd;
	private static Connection conexion;
	private Statement st;
	private ResultSet rs;
	
	public static Connection TestConexion() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, login, pwd);
			System.out.println(" - Conexión con MySQL establecida -");

		} catch (Exception e) {
			System.out.println(" – Error de Conexión con MySQL -");
			e.printStackTrace();
		}
		return conexion;
	}

}
