package sergiotahoces.modelo;

public class Elemento {

	private int id;
	private String nombre;
	private String descripcion;
	private String caracteristica;
	
	public Elemento(int id, String nombre, String descripcion, String caracteristica) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.caracteristica = caracteristica;
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCaracteristica() {
		return caracteristica;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
}
