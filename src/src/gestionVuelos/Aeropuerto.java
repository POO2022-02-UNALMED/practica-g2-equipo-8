package gestionVuelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Aeropuerto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nombre;
	private static List<Vuelo> vuelos = new ArrayList<>();
	private static List<Empleado> empleados = new ArrayList<>();
	private static List<Pasajero> pasajeros = new ArrayList<>();
	private static List<String> salas = new ArrayList<>();
	private static List<Avion> aviones = new ArrayList<>();
	private static float dinero;

	public Aeropuerto() {
		// Deserializador.deserializarAeropuerto(this);
	}

	public static void ingresarDinero(int pago) {
		Aeropuerto.dinero += pago;
	}

	public static float getDinero() {
		return dinero;
	}

	public static void setDinero(float dinero) {
		Aeropuerto.dinero = dinero;
	}

	public Aeropuerto(String nombre) {
		this.nombre = nombre;
	}

	public static void agregarVuelo(Vuelo vuelo) {
		Aeropuerto.vuelos.add(vuelo);
	}

	public static void agregarEmpleado(Empleado empleado) {
		Aeropuerto.empleados.add(empleado);
	}

	public static void agregarSalas(String sala) {
		Aeropuerto.salas.add(sala);
	}

	public static void agregarAvion(Avion avion) {
		Aeropuerto.aviones.add(avion);
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

	public static List<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public static void setPasajeros(List<Pasajero> pasajeros) {
		Aeropuerto.pasajeros = pasajeros;
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Aeropuerto.nombre = nombre;
	}

	public static void despedirEmpleado(Empleado empleado) {
		empleado.getVuelo().getEmpleados().remove(empleado);
		empleados.remove(empleados.indexOf(empleado));
	}
}
