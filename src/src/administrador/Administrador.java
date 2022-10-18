package administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import gestionVuelos.Aeropuerto;
import gestionVuelos.Asiento;
import gestionVuelos.Avion;
import gestionVuelos.Cargos;
import gestionVuelos.Empleado;
import gestionVuelos.Equipaje;
import gestionVuelos.Pasajero;
import gestionVuelos.Vuelo;

public class Administrador {
	public static void main(String[] args) {
		// Pruebas

		Aeropuerto aeropuerto = new Aeropuerto();
		Vuelo vuelo = new Vuelo(new Avion("X", 100, 1000), new Date(), "Bogota", 1000, "10A");
		System.out.println(vuelo);
		for (Asiento asiento : vuelo.getAvion().getAsientos())
			System.out.println(asiento);
		Pasajero pasajero = new Pasajero(15, 10001, "pepito");
		List<Equipaje> equipaje = new ArrayList<>();
		equipaje.add(new Equipaje(12.4, pasajero));
		pasajero.setEquipajes(equipaje);
		vuelo.agregarPasajero(pasajero, 10);
		System.out.println(equipaje);

		Aeropuerto.setDinero((float) Math.pow(10, 7));
		Empleado e1 = new Empleado("Juan Carlos", 1200000, 10023031, Cargos.PILOTO, 45, "M");
		Empleado e2 = new Empleado("Felipe", 900000, 4553031, Cargos.COPILOTO, 37, "M");
		Empleado e3 = new Empleado("Andrea", 600000, 456174, Cargos.AZAFATA, 31, "F");
		Empleado e4 = new Empleado("Sara", 1, Cargos.COPILOTO, 38, "F");

		e4.setVuelo(vuelo);

		// Pruebas

		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Vuelos --");

		opcionesPrincipales(entrada);
	}

	public static void opcionesPrincipales(Scanner entrada) {
		int option = 0;

		System.out.println("\nIngrese el numero de la opcion a elegir:");
		System.out.print("1. Reserva de vuelo.\n" + "2. Ver informacion del vuelo.\n" + "3. Gestionar empleados.\n"
				+ "4. Administrar finanzas.\n" + "5. Administrar vuelos y aviones.\n\n"
				+ "0. Pulse 0 en cualquier men� para finalizar el programa.\n");
		option = entrada.nextInt();
		switch (option) {
		case 1:
			reservaDeVuelo();
			break;
		case 2:
			break;
		case 3:
			gestionarEmpleadosInterfaz();
			break;
		case 4:
			interfazFinanzas();
			break;
		case 5:
			break;
		case 0:
			salirDelSistema();
			break;
		default:
			System.out.println("Opci�n incorrecta, vuelva a intentarlo.");
			opcionesPrincipales(entrada);
		}
	}

	public static void gestionarEmpleadosInterfaz() {
		mostrarEmpleados();
		System.out.println("Introduzca la cedula para ver m�s opciones:");
		Scanner entrada = new Scanner(System.in);
		int cedula = entrada.nextInt();
		if (cedula == 0) {
			salirDelSistema();
		}
		while (Empleado.buscarEmpleado(cedula) == null) {
			cedula = entrada.nextInt();
			if (cedula == 0) {
				salirDelSistema();
				break;
			}
			System.out.println("Esta cedula no est� asignada a ning�n empleado, vuelva a intentarlo.");
		}
		Empleado empleadoActual = Empleado.buscarEmpleado(cedula);
		System.out.println(empleadoActual);
		System.out.println("Seleccione la acci�n que quiere realizar:\n1. Cambiar cargo.");
	}

	public static void cambiarCargo() {

	}

	public static void mostrarEmpleados() {
		System.out.println("Estos son los empleados del aeropuerto:\n");
		System.out.println("Cedula         Nombre");
		for (Empleado empleado : Aeropuerto.getEmpleados()) {
			System.out.println(empleado.getCedula() + " ".repeat(15 - Integer.toString(empleado.getCedula()).length())
					+ empleado.getNombre());
		}
	}

	public static void salirDelSistema() {
		System.out.println("Vuelva pronto");
		// Serializador.serializarAeropuertos(aeropuerto);
		System.exit(0);
	}

	public static void reservaDeVuelo() {
		Scanner entradas = new Scanner(System.in);
		System.out.print("Por favor inserte la ciudad de destino: ");
		String entradaDestino = entradas.nextLine();

		List<Vuelo> vuelosDisp = new ArrayList<>();
		for (Vuelo vuelo : Aeropuerto.getVuelos()) {
			if (vuelo.getDestino().equals(entradaDestino) && !vuelo.isEnVuelo()) {
				vuelosDisp.add(vuelo);
			}
		}

		if (!vuelosDisp.isEmpty())
			for (Vuelo vuelo : vuelosDisp)
				System.out.println(vuelo);
		else {
			System.out.println("Lo sentimos, no hay vuelos disponibles desde ese origen para el destino indicado");
			return;
		}
		System.out.print("Inserte el ID del vuelo de su preferencia: ");
		int IDvuelo = entradas.nextInt();

		Vuelo vueloElegido = null;
		for (Vuelo vuelo : vuelosDisp)
			if (vuelo.getId() == IDvuelo)
				vueloElegido = vuelo;

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
			if (!asiento.isOcupado())
				System.out.println(asiento);
		}

		System.out.print("\nIngrese el numero de asiento de su preferencia: ");
		int nroAsiento = entradas.nextInt();
		vueloElegido.agregarPasajero(nuevoPasajero, nroAsiento);
		System.out.println(vueloElegido.tiquete(nuevoPasajero.getNombre(),nroAsiento));
	}

	public static void interfazFinanzas() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Finanzas --");
		System.out.println("Saldo actual = " + Aeropuerto.getDinero());

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
			case 1:
				pagarNominaInterfaz();
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
		} while (option != 5);
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
				Empleado.pagarNomina(Aeropuerto.getEmpleados());
			} else if (option == 2) {
				System.out.println("\nListado de empleados");
				for (int i = 0; i < lempleados.size(); i++) {
					System.out.println((i + 1) + ". " + lempleados.get(i).getCargo() + ": "
							+ lempleados.get(i).getNombre() + ", sueldo = " + lempleados.get(i).getSueldo());
				}
				System.out.println("Selecciona el numero del empleado a pagar:");
				option2 = entrada.nextInt();

				if (option2 < 1 || option2 > lempleados.size() + 1) {
					System.out.println("Error: numero incorrecto");
				} else {
					lempleados.get(option2 - 1).pagarNomina();
				}
			}
		} while (option != 3);
	}
}