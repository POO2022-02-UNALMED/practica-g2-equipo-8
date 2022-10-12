package administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import gestionVuelos.*;

public class Administrador {
	public static void main(String[] args) {
		//Pruebas
		Vuelo vuelo = new Vuelo(new Avion("X", 100, 1000), new Date(), "Medellin", "Bogota", 1000, "10A");
		System.out.println(vuelo);
		for (Asiento asiento : vuelo.getAvion().getAsientos()) System.out.println(asiento);
		Pasajero pasajero = new Pasajero(15, 10001, "pepito");
		List<Equipaje> equipaje = new ArrayList<>();
		equipaje.add(new Equipaje(12.4, pasajero));
		pasajero.setEquipajes(equipaje);
		vuelo.agregarPasajero(pasajero, 10);
		System.out.println(equipaje);
		//Pruebas

		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Vuelos --");

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("1. Reserva de vuelo.\n" + "2. Ver informacion del vuelo.\n" + "3. Asignar empleados.\n"
					+ "4. Administrar finanzas.\n" + "5. Administrar vuelos y aviones.\n" + "6. Finalizar programa.\n");
			option = entrada.nextInt();
			switch (option) {
				case 1: reservaDeVuelo(); break;
				case 2: break;
				case 3: break;
				case 4: break;
				case 5: break;
				case 6: break;
			}
		} while (option != 6);
	}

	public static void reservaDeVuelo() {
		Scanner entradas = new Scanner(System.in);
		System.out.print("Por favor inserte la ciudad de origen: ");
		String entradaOrigen = entradas.nextLine();
		System.out.print("Por favor inserte la ciudad de destino: ");
		String entradaDestino = entradas.nextLine();

		List<Vuelo> vuelosDisp = new ArrayList<>();
		for (Vuelo vuelo : Aeropuerto.getVuelos()) {
			if (vuelo.getOrigen().equals(entradaOrigen) && vuelo.getDestino().equals(entradaDestino)
					&& !vuelo.isEnVuelo()) {
				vuelosDisp.add(vuelo);
			}
		}

		if (!vuelosDisp.isEmpty()) for (Vuelo vuelo : vuelosDisp) System.out.println(vuelo);
		else {
			System.out.println("Lo sentimos, no hay vuelos disponibles desde ese origen para el destino indicado");
			return;
		}
		System.out.print("Inserte el ID del vuelo de su preferencia: ");
		int IDvuelo = entradas.nextInt();

		Vuelo vueloElegido = null;
		for (Vuelo vuelo : vuelosDisp) if (vuelo.getId() == IDvuelo) vueloElegido = vuelo;

		System.out.println("\nFormulario de datos personales");
		System.out.print("Inserte su nombre: ");
		entradas.next();
		String nombre = entradas.nextLine();
		System.out.print("Inserte su documento de identidad: ");
		int documento = entradas.nextInt();
		System.out.print("Inserte su edad: ");
		int edad = entradas.nextInt();
		Pasajero nuevoPasajero = new Pasajero(edad, documento, nombre);
		System.out.print("Inserte la cantidad de equipajes que transporta: ");
		int nroEquipajes = entradas.nextInt();
		List<Equipaje> equipajes = new ArrayList<>();
		for (int i = 1; i <= nroEquipajes; i++) {
			System.out.print("Inserte el peso del equipaje " + i + ": ");
			double peso = entradas.nextDouble();
			equipajes.add(new Equipaje(peso, nuevoPasajero));
		}

		nuevoPasajero.setEquipajes(equipajes);

		System.out.println("\nLos asientos disponibles en el vuelo son los siguientes: ");
		for (Asiento asiento : vueloElegido.getAvion().getAsientos()) {
			if (!asiento.isOcupado()) System.out.println(asiento);
		}

		System.out.print("\nIngrese el numero de asiento de su preferencia: ");
		int nroAsiento = entradas.nextInt();
		vueloElegido.agregarPasajero(nuevoPasajero, nroAsiento);
	}
}