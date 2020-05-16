package sergiotahoces.modelo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import sergiotahoces.controlador.ApiRequest;

public class JsonClass {

	ApiRequest peticiones;
	private String SERVER_PATH, GET_DATA, SET_DATA, UPDATE_DATA, DELETE_DATA, DELETE_ONE_DATA;

	public JsonClass() {
		peticiones = new ApiRequest();
		SERVER_PATH = "http://localhost:8888/PracticaUD03/";
		GET_DATA = "leeDatoss.php";
		SET_DATA = "escribirDatos.php";
		UPDATE_DATA = "UpdateDatos.php";
		DELETE_DATA = "DeleteDatos.php";
		DELETE_ONE_DATA = "deleteUnDato.php";
	}

	public HashMap<Integer, Elemento> leer() throws SQLException {
		try {
			HashMap<Integer, Elemento> data = new HashMap<Integer, Elemento>();
			String url = SERVER_PATH + GET_DATA;
			String response = peticiones.getRequest(url);
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
			if (respuesta == null) {
				System.out.println("Error con JSON");
			} else {
				String estado = (String) respuesta.get("estado");
				if (estado.equals("ok")) {
					JSONArray array = (JSONArray) respuesta.get("datos");
					if (array.size() > 0) {
						for (int i = 0; i < array.size(); i++) {
							JSONObject row = (JSONObject) array.get(i);
							Elemento dato = new Elemento(Integer.parseInt(row.get("id").toString()),
									row.get("nombre").toString(), row.get("descripcion").toString(),
									row.get("caracteristica").toString());
							data.put(dato.getId(), dato);
						}
						System.out.println("HashMap rellenado con exito.");
					} else {
						System.out.println("Array de datos vacio.");
					}
				} else {
					System.out.println("Estado distinto de 'ok'.");
				}
				return data;
			}
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}

	public void escribir(HashMap<Integer, Elemento> data) throws SQLException {

		String url = SERVER_PATH + SET_DATA;
        for (Integer a : data.keySet()) {
            JSONObject objPeticion = new JSONObject();
            Elemento elem = data.get(a);
            objPeticion.put("id", elem.getId());
            objPeticion.put("nombre", elem.getNombre());
            objPeticion.put("descripcion", elem.getDescripcion());
            objPeticion.put("caracteristica", elem.getCaracteristica());
            String json = objPeticion.toJSONString();
            try {
            String response = peticiones.postRequest(url, json);
            JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
            } catch (IOException e) {
            // TODO Bloque catch generado automáticamente
            e.printStackTrace();
            }
        }

	}

	public void imprimir(HashMap<Integer, Elemento> data) {
		for (Integer a : data.keySet()) {
			Elemento jugador = data.get(a);
			System.out.println(jugador.getId() + " // " + jugador.getNombre() + " // " + jugador.getDescripcion()
					+ " // " + jugador.getCaracteristica());
		}
		System.out.println();
	}

	public void escribirUno() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca id: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce descripcion: ");
		String descripcion = sc.nextLine();
		System.out.println("Introduce caracteristica: ");
		String caracteristica = sc.nextLine();

		JSONObject objPeticion = new JSONObject();
		objPeticion.put("id", id);
		objPeticion.put("nombre", nombre);
		objPeticion.put("descripcion", descripcion);
		objPeticion.put("caracteristica", caracteristica);

		String json = objPeticion.toJSONString();
		System.out.println(json);
		String url = SERVER_PATH + SET_DATA;
		try {
			String response = peticiones.postRequest(url, json);
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public void modificar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca id: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Introduce nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce descripcion: ");
		String descripcion = sc.nextLine();
		System.out.println("Introduce caracteristica: ");
		String caracteristica = sc.nextLine();

		JSONObject objPeticion = new JSONObject();
		objPeticion.put("id", id);
		objPeticion.put("nombre", nombre);
		objPeticion.put("descripcion", descripcion);
		objPeticion.put("caracteristica", caracteristica);

		String json = objPeticion.toJSONString();
		System.out.println(json);
		String url = SERVER_PATH + UPDATE_DATA;
		try {
			String response = peticiones.putRequest(url, json);
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public void eliminar() {
		String url = SERVER_PATH + DELETE_DATA;
		try {
			String response = peticiones.deleteRequest(url);
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}

	public void eliminaUno() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca id: ");
		int id = Integer.parseInt(sc.nextLine());
		JSONObject objPeticion = new JSONObject();
		objPeticion.put("id", id);
		String json = objPeticion.toJSONString();
		String url = SERVER_PATH + DELETE_ONE_DATA;
		try {
			String response = peticiones.deleteUnoRequest(url, json);
			JSONObject respuesta = (JSONObject) JSONValue.parse(response.toString());
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void buscarElemento() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca id para buscar elemento: ");
		int id = Integer.parseInt(sc.nextLine());
		String url = SERVER_PATH + GET_DATA + "?id" + id;
		try {
			String response = peticiones.getRequest(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
