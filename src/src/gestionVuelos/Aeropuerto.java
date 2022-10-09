package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
	private static String nombre;
	private static List<Aeropuerto> aeropuertos = new ArrayList<>();
	private static int nextId = 0;
	private final int id;
	private static List<Vuelo> vuelos = new ArrayList<>();
	private static List<Empleado> empleados = new ArrayList<>();
	private static List<String> salas = new ArrayList<>();
	private static List<Avion> aviones = new ArrayList<>();

	//Comentario importante, me parece mejor tratar la administracion de UN solo aeropuerto, que sean muchos realmente solo nos agrega complejidad (lo que se traduce en mas trabajo)
	//mas no funcionalidad, por eso creo que es mejor concentrar los esfuerzos en que todas las demas funcionalidades queden muy bien.

	public Aeropuerto() {
		this(null);
	}

	public Aeropuerto(String nombre) {
		this.nombre = nombre;
		this.id = nextId;
		nextId++;
	}

	public static void agregarVuelo(Vuelo vuelo){
		Aeropuerto.vuelos.add(vuelo);
	}

	public static void agregarEmpleado(Empleado empleado){
		Aeropuerto.empleados.add(empleado);
	}

	public static void agregarSalas(String sala){
		Aeropuerto.salas.add(sala);
	}

	public static void agregarAvion(Avion avion){
		Aeropuerto.aviones.add(avion);
	}

	public static List<Aeropuerto> getAeropuertos() {
		return aeropuertos;
	}

	public static List<Vuelo> getVuelos() {
		return vuelos;
	}

	public static void setVuelos(List<Vuelo> vuelos) {
		Aeropuerto.vuelos = vuelos;
	}

	public static List<Empleado> getEmpleados() {
		return empleados;
	}

	public static void setEmpleados(List<Empleado> empleados) {
		Aeropuerto.empleados = empleados;
	}

	public static List<String> getSalas() {
		return salas;
	}

	public static void setSalas(List<String> salas) {
		Aeropuerto.salas = salas;
	}

	public static List<Avion> getAviones() {
		return aviones;
	}

	public static void setAviones(List<Avion> aviones) {
		Aeropuerto.aviones = aviones;
	}

	public int getId() {
		return id;
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Aeropuerto.nombre = nombre;
	}
}
