package administrador;

import java.util.Date;
import java.util.Scanner;

import gestionVuelos.*;

public class Administrador {
	private String nombre;
	private int cedula;
	private static int dinero;

	public static void ingresarDinero(int pago){
		Administrador.dinero += pago;
	}

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

	public static int getDinero() {
		return dinero;
	}

	public static void setDinero(int dinero) {
		Administrador.dinero = dinero;
	}

	public static void main(String[] args) {
		//Pruebas
		Vuelo vuelo = new Vuelo(new Avion("X",100,1000), new Date(),"Medellin","Bogota",1000,"10A");
		System.out.println(vuelo);
		for(Asiento asiento: vuelo.getAvion().getAsientos()) System.out.println(asiento);
		//Pruebas

		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Vuelos --\nAdministrador, Elija que aeropuerto desea administrar.");

		for (Aeropuerto aeropuerto : Aeropuerto.getAeropuertos()) {
			System.out.println(aeropuerto.getId() + " " + aeropuerto.getNombre());
		}
		int aeropuertoElegido = entrada.nextInt();
		System.out.println("Ingrese el numero de la opcion a elegir:");
		System.out.println("1. Reserva de vuelo.\n" + "2. Ver informacion del vuelo.\n" + "3. Asignar empleados.\n"
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
