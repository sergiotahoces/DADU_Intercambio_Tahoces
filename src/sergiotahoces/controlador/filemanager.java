package sergiotahoces.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import sergiotahoces.modelo.Elemento;

public class filemanager {

	public void escrbirEnFichero(HashMap<Integer, Elemento> datos) throws SQLException {

		File f = new File("AccesoDatos");
		try {
			FileWriter writer = new FileWriter(f);
			BufferedWriter bwriter = new BufferedWriter(writer);

			for (Integer i : datos.keySet()) {
				Elemento a = datos.get(i);
				bwriter.write(a.getId() + " / " + a.getNombre() + " / " + a.getDescripcion() + " / "
						+ a.getCaracteristica() + "\n");
			}
			bwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void escrbirEnFicheroElemento(Elemento a) throws SQLException {

		File f = new File("AccesoDatos");
		try {
			FileWriter writer = new FileWriter(f);
			BufferedWriter bwriter = new BufferedWriter(writer);

			bwriter.write(
					a.getId() + " / " + a.getNombre() + " / " + a.getDescripcion() + " / " + a.getCaracteristica());

			bwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
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

	public HashMap<Integer, Elemento> leerFichero() throws SQLException {
		HashMap<Integer, Elemento> datosMap = new HashMap<Integer, Elemento>();
		File file = new File("AccesoDatos");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String datos;
			while ((datos = br.readLine()) != null) {
				String[] data = datos.split(" / ");
				int id = Integer.parseInt(data[0]);
				String nombre = data[1];
				String descripcion = data[2];
				String caracteristica = data[3];

				datosMap.put(id, new Elemento(id, nombre, descripcion, caracteristica));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datosMap;
	}

	public void modificarElemento(Elemento e) {
		File f = new File("AccesoDatos");
		try {
			File tempFile = new File("Files/temp.txt");
			BufferedReader br = new BufferedReader(new FileReader(f));
			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
			String data = null;
			while ((data = br.readLine()) != null) {
				if ((Character.getNumericValue(data.charAt(0))!= e.getId())) {
					pw.println(data);
					pw.flush();
				} else {
					pw.println(e.toString());
				}
			}
			pw.close();
			br.close();

		} catch (IOException o) {
			o.printStackTrace();
		}
	}

	public void buscarUno(int id) {
		HashMap<Integer, Elemento> datosmap = new HashMap<Integer, Elemento>();
		try {
			datosmap = leerFichero();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Iterator<Elemento> valor = datosmap.values().iterator();
		while (valor.hasNext()) {
			Elemento e = valor.next();
			if (e.getId() == id) {
				System.out.println(e.toString());
			}
		}
	} 
	public void eliminarTodo() {
        PrintWriter pw;
        File f = new File("AccesoDatos");
        try {
            pw = new PrintWriter(f);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	public void moverDatos(HashMap<Integer, Elemento> e) {
        FileWriter writer;
        File f = new File("AccesoDatos");
        
        try {
            writer = new FileWriter(f);
            BufferedWriter bwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(bwriter);
            Iterator<Elemento> itr = e.values().iterator();
            while (itr.hasNext()) {
                pwriter.write(itr.next().toString());
                pwriter.write("\n");
            }
            pwriter.close();
            bwriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
