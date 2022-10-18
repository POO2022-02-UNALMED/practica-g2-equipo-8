package gestionVuelos;

public abstract class Persona {
	protected String nombre;
	protected final int cedula;
	protected int edad;
	protected String sexo;

	public Persona(String nombre, int cedula, int edad, String sexo) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.edad = edad;
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getCedula() {
		return cedula;
	}

	public abstract String toString();
}
