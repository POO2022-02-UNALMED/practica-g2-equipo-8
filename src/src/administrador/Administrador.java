package administrador;

import java.util.Scanner;

import gestionVuelos.Aeropuerto;

public class Administrador {
	private String nombre;
	private int cedula;
	private int dinero;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("-- Bienvenido al sistema de administracion de Vuelos --\n\nAdministrador ¿Qué aeropuerto desea administrar?");

		for (Aeropuerto aeropuerto : Aeropuerto.getAeropuertos()) {
			System.out.println(aeropuerto.getId() + " " + aeropuerto.getNombre());
		}
		int aeropuertoElegido = entrada.nextInt();
		System.out.println("¿Qué desea hacer hoy? Ingrese el número de la opción a elegir:");
		System.out.println("1. Reserva de vuelo.\n" + "2. Ver información del vuelo.\n" + "3. Asignar empleados.\n"
				+ "4. Administrar finanzas.\n" + "5. Administrar vuelos y aviones.\n" + "6. Finalizar programa.\n");

		int option = entrada.nextInt();
		switch (option) {
		case 1: {
			break;
		}
		case 2: {
			break;
		}
		case 3: {
			break;
		}
		case 4: {
			break;
		}
		case 5: {
			break;
		}
		case 6: {
			break;
		}
		}
	}

	private void ReservaDeVuelo() {

	}
}
