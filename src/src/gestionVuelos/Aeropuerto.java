package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
	private String nombre;
	private static List<Aeropuerto> aeropuertos = new ArrayList<>();
	private static int nextId = 0;
	private final int id;
	private List<Vuelo> vuelos = new ArrayList<>();
	private List<Empleado> empleados = new ArrayList<>();
	private List<String> salas = new ArrayList<>();
	private List<Avion> aviones = new ArrayList<>();

	public Aeropuerto() {
		this(null);
	}

	public Aeropuerto(String nombre) {
		this.nombre = nombre;
		this.id = nextId;
		nextId++;
	}

	public void copiarAeropuerto(Aeropuerto aeropuerto) {
		this.nombre = aeropuerto.nombre;
		this.vuelos = aeropuerto.vuelos;
		this.empleados = aeropuerto.empleados;
		this.salas = aeropuerto.salas;
		this.aviones = aeropuerto.aviones;
	}

	public static List<Aeropuerto> getAeropuertos() {
		return aeropuertos;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<String> getSalas() {
		return salas;
	}

	public void setSalas(List<String> salas) {
		this.salas = salas;
	}

	public List<Avion> getAviones() {
		return aviones;
	}

	public void setAviones(List<Avion> aviones) {
		this.aviones = aviones;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
