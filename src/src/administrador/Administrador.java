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

		Aeropuerto.setDinero((float) Math.pow(10,7));
		Empleado e1 = new Empleado("Juan Carlos" ,1200000, 10023031, "Piloto");
		Empleado e2 = new Empleado("Felipe" ,900000, 4553031, "control de pista");
		Empleado e3 = new Empleado("Andrea" ,600000, 67489, "azafata");
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
				case 4: interfazFinanzas();
				case 5: break;
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

	public static void interfazFinanzas() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Finanzas --");

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("""
					1. Pagar Nomina de empleados.
					2. ver historial de transacciones.
					3. Otorgar un aumento o disminución de sueldo a un empleado.
					4. Modificar el presupuesto.
					5. Volver.
					""");

			option = entrada.nextInt();
			switch (option) {
				case 1: pagarNominaInterfaz();
				case 2: break;
				case 3: break;
				case 4: break;
			}
		} while (option != 5);
	}
	private static void pagarNomina(Empleado empleado) {
		float dineroapagar = empleado.getSueldo();
		float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

		if (nuevosaldo < 0) {
			System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
		} else {
			Aeropuerto.setDinero(nuevosaldo);
			System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
		}
	}
	private static void pagarNomina(List<Empleado> empleados){
		float dineroapagar = 0;
		for (Empleado empleado : empleados) {
			dineroapagar += empleado.getSueldo();
		}
		float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

		if (nuevosaldo < 0) {
			System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
		} else {
			Aeropuerto.setDinero(nuevosaldo);
			System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
		}
	}
	public static void pagarNominaInterfaz() {
		int dineroapagar = 0;
		List<Empleado> lempleados = Aeropuerto.getEmpleados();
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de pago de nomina --");

		int option;
		int option2;
		do {
			System.out.println("\n¿Desea pagarle a todos los empleados?");
			System.out.print("""
					1. Pagar a todos los empleados
					2. Elegir empleados
					3. Finalizar y Volver.
					""");

			option = entrada.nextInt();

			if (option == 1) {
				pagarNomina(Aeropuerto.getEmpleados());
			} else if (option == 2) {
				System.out.println("\nListado de empleados");
				for (int i = 0; i < lempleados.size(); i++) {
					System.out.println((i+1) + ". " + lempleados.get(i).getCargo() + ": " + lempleados.get(i).getNombre() + ", sueldo = " + lempleados.get(i).getSueldo());
				}
				System.out.println("Selecciona el numero del empleado a pagar:");
				option2 = entrada.nextInt();

				if (option2 < 1 || option2 > lempleados.size() + 1) {
					System.out.println("Error: numero incorrecto");
				} else {
					pagarNomina(lempleados.get(option2-1));
				}
			}
		} while (option != 3);
	}
}