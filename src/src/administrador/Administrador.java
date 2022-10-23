package administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import basedatos.Serializador;
import gestionVuelos.Aeropuerto;
import gestionVuelos.Asiento;
import gestionVuelos.Avion;
import gestionVuelos.Cargos;
import gestionVuelos.Empleado;
import gestionVuelos.Equipaje;
import gestionVuelos.Pasajero;
import gestionVuelos.Persona;
import gestionVuelos.Vuelo;

public class Administrador {
	public static void main(String[] args) {

		Aeropuerto aeropuerto = new Aeropuerto();
		inicializarStatics(aeropuerto);
		// inicializadorObjetos(aeropuerto);

		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Vuelos --");

		opcionesPrincipales(aeropuerto);
	}

	public static void inicializadorObjetos(Aeropuerto aeropuerto) {
		Vuelo vuelo1 = new Vuelo(new Avion("X", 100, 1000), new Date(), "Bogota", 1000, "10A");
		Vuelo vuelo2 = new Vuelo(new Avion("A", 50, 3000), new Date(), "Miami", 1500, "1B");
		System.out.println(vuelo1);
		for (Asiento asiento : vuelo1.getAvion().getAsientos())
			System.out.println(asiento);
		List<Equipaje> equipaje = new ArrayList<>();
		Pasajero pasajero = new Pasajero("Pepito", 897915, 15, "M");

		equipaje.add(new Equipaje(12.4, pasajero));
		pasajero.setEquipajes(equipaje);
		vuelo1.agregarPasajero(pasajero, 10);
		System.out.println(equipaje);

		aeropuerto.setDinero((float) Math.pow(10, 7));
		Empleado e1 = new Empleado("Juan Carlos", 1200000, 10023031, Cargos.PILOTO, 45, "M");
		Empleado e2 = new Empleado("Felipe", 900000, 4553031, Cargos.COPILOTO, 37, "M");
		Empleado e3 = new Empleado("Andrea", 600000, 456174, Cargos.AZAFATA, 31, "F");
		Empleado e4 = new Empleado("Sara", 1, Cargos.COPILOTO, 38, "F");

		e4.setVuelo(vuelo1);

		Serializador.serializar(aeropuerto);
	}

	public static void inicializarStatics(Aeropuerto aeropuerto) {
		Avion.setAeropuerto(aeropuerto);
		Persona.setAeropuerto(aeropuerto);
		Vuelo.setAeropuerto(aeropuerto);
	}

	public static void opcionesPrincipales(Aeropuerto aeropuerto) {
		Scanner entrada = new Scanner(System.in);
		int option = 0;

		System.out.println("\nIngrese el numero de la opcion a elegir:");
		System.out.print("1. Reserva de vuelo.\n" + "2. Ver informacion del vuelo.\n" + "3. Gestionar empleados.\n"
				+ "4. Administrar finanzas.\n" + "5. Administrar vuelos y aviones.\n\n"
				+ "0. Pulse 0 en cualquier menu para finalizar el programa.\n");
		option = entrada.nextInt();
		switch (option) {
		case 1:
			reservaDeVuelo(aeropuerto);
			break;
		case 2:
			break;
		case 3:
			gestionarEmpleadosInterfaz(aeropuerto);
			break;
		case 4:
			interfazFinanzas(aeropuerto);
			break;
		case 5:
			Modificaciones(aeropuerto);
			break;
		case 0:
			salirDelSistema(aeropuerto);
			break;
		default:
			System.out.println("Opcion incorrecta, vuelva a intentarlo.");
			opcionesPrincipales(aeropuerto);
		}
	}

	public static void gestionarEmpleadosInterfaz(Aeropuerto aeropuerto) {
		Empleado.mostrarEmpleados();
		System.out.println("Introduzca la cedula para ver mas opciones:");
		Scanner entrada = new Scanner(System.in);
		int cedula = entrada.nextInt();
		if (cedula == 0) {
			salirDelSistema(aeropuerto);
		}
		while (Empleado.buscarEmpleado(cedula) == null) {
			cedula = entrada.nextInt();
			if (cedula == 0) {
				salirDelSistema(aeropuerto);
				break;
			}
			System.out.println("Esta cedula no esta asignada a ningun empleado, vuelva a intentarlo.");
		}
		Empleado empleadoActual = Empleado.buscarEmpleado(cedula);
		System.out.println(empleadoActual);
		opcionesEmpleado(empleadoActual, aeropuerto);
	}

	private static void opcionesEmpleado(Empleado empleadoActual, Aeropuerto aeropuerto) {
		Scanner entrada = new Scanner(System.in);

		System.out.println(
				"Seleccione la accion que quiere realizar:\n1. Cambiar cargo.\n2. Cambiar sueldo.\n3. Asignar vuelo.\n4. Despedir.");
		int option = entrada.nextInt();
		switch (option) {
		case 1:
			cambiarCargo(empleadoActual, aeropuerto);
			break;
		case 2:
			cambiarSueldo(empleadoActual);
			break;
		case 3:
			asignarVuelo(empleadoActual, aeropuerto);
			break;
		case 4:
			aeropuerto.despedirEmpleado(empleadoActual);
			System.out.println(empleadoActual);
			break;
		default:
			System.out.println("Opcion erronea, vuelva a intentarlo.");
			opcionesEmpleado(empleadoActual, aeropuerto);
		}
	}

	public static void cambiarSueldo(Empleado empleado) {
		System.out.println("El sueldo actual de " + empleado.getNombre() + " es " + empleado.getSueldo());
		System.out.println(
				"Ingrese el valor a aumentar o disminuir, en caso de disminuir coloca que un - antes del valor: ");
		Scanner entrada = new Scanner(System.in);
		int valor = entrada.nextInt();
		empleado.setSueldo(empleado.getSueldo() + valor);
		System.out.println("El nuevo saldo de " + empleado.getNombre() + " es " + empleado.getSueldo());
	}

	public static void asignarVuelo(Empleado empleado, Aeropuerto aeropuerto) {

		if (empleado.getVuelo() == null) {
			System.out.println("Este empleado no tiene vuelo asignado");
		} else {
			System.out.println("El vuelo de este empleado es:\n" + empleado.getVuelo());
		}
		System.out.println("Seleccione el ID del vuelo al que quiere asignar al empleado: ");

		for (Vuelo vuelo : aeropuerto.getVuelos()) {
			if (vuelo != empleado.getVuelo()) {
				System.out.println(vuelo);
			}
		}
		Scanner entrada = new Scanner(System.in);
		int idVuelo = entrada.nextInt();
		if (Vuelo.encontrarVuelo(idVuelo) == null) {
			System.out.println("ID invalido, vuelva a intentarlo.\n");
			asignarVuelo(empleado, aeropuerto);
		} else {
			empleado.setVuelo(Vuelo.encontrarVuelo(idVuelo));
			System.out.println("Ahora el vuelo del empleado es:\n" + empleado.getVuelo());
		}
	}

	public static void cambiarCargo(Empleado empleadoActual, Aeropuerto aeropuerto) {
		System.out.println("El cargo actual de" + empleadoActual.getNombre() + " es " + empleadoActual.getCargo());
		System.out.println("�A que cargo quieres asignarle? Los cargos disponibles son: ");

		Cargos c = Cargos.elegirCargo();
		if (c.getCargo().equals(empleadoActual.getCargo())) {
			System.out.println(empleadoActual.getNombre() + " ya era " + empleadoActual.getCargo());
		} else {
			empleadoActual.setCargo(c);
			System.out.println("Ahora el cargo de " + empleadoActual.getNombre() + " es " + empleadoActual.getCargo());
		}
		opcionesPrincipales(aeropuerto);
	}

	public static void salirDelSistema(Aeropuerto aeropuerto) {
		System.out.println("Vuelva pronto");
		Serializador.serializar(aeropuerto);
		System.exit(0);
	}

	public static void reservaDeVuelo(Aeropuerto aeropuerto) {
		Scanner entradas = new Scanner(System.in);
		System.out.print("Por favor inserte la ciudad de destino: ");
		String entradaDestino = entradas.nextLine();

		List<Vuelo> vuelosDisp = new ArrayList<>();
		for (Vuelo vuelo : aeropuerto.getVuelos()) {
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

		System.out.print("Inserte su nombre: \n");
		entradas.nextLine();
		String nombre = entradas.nextLine();
		System.out.print("Inserte su documento de identidad: ");
		int documento = entradas.nextInt();
		System.out.print("Inserte su edad: ");
		int edad = entradas.nextInt();
		System.out.println("Inserte su genero: ");
		entradas.nextLine();
		String sexo = entradas.nextLine();
		System.out.print("Inserte la cantidad de equipajes que transporta: ");
		int nroEquipajes = entradas.nextInt();
		List<Equipaje> equipajes = new ArrayList<>();
		Pasajero nuevoPasajero = new Pasajero(nombre, documento, edad, sexo);

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
		// vueloElegido.agregarPasajero(nuevoPasajero, nroAsiento);
		if (vueloElegido.agregarPasajero(nuevoPasajero, nroAsiento) == true) {
			System.out.println(vueloElegido.tiquete(nuevoPasajero));
		} else {
			vueloElegido.getPasajeros().remove(nuevoPasajero);
			aeropuerto.getPasajeros().remove(nuevoPasajero);
		}

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("""
					1. Volver.
					2. Finalizar.
					""");

			option = entradas.nextInt();
			switch (option) {
			case 1:
				opcionesPrincipales(aeropuerto);
			case 2:
				salirDelSistema(aeropuerto);
				break;
			}
		} while (option != 3);
	}

	public static void interfazFinanzas(Aeropuerto aeropuerto) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de Finanzas --");
		System.out.println("Saldo actual = " + aeropuerto.getDinero());

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("""
					1. Pagar Nomina de empleados.
					2. ver historial de transacciones.
					3. Otorgar un aumento o disminucion de sueldo a un empleado.
					4. Contratar nuevo empleado.
					5. Volver.
					""");

			option = entrada.nextInt();
			switch (option) {
			case 1:
				pagarNominaInterfaz(aeropuerto);
				break;
			case 2:
				aeropuerto.transacciones();
				break;
			case 3:
				Empleado.cambiarSueldo();
				break;
			case 4:
				Empleado.nuevoEmpleado();
			case 5:
				break;
			default:
				System.out.println("Opcion incorrecta, vuelva a intentarlo.");
			}
		} while (option != 5 && option != 0);
	}

	public static void pagarNominaInterfaz(Aeropuerto aeropuerto) {
		int dineroapagar = 0;
		List<Empleado> lempleados = aeropuerto.getEmpleados();
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de pago de nomina --");

		int option;
		int option2;
		do {
			System.out.println("\nDesea pagarle a todos los empleados?");
			System.out.print("""
					1. Pagar a todos los empleados
					2. Elegir empleados
					3. Finalizar y Volver.
					""");

			option = entrada.nextInt();

			if (option == 1) {
				Empleado.pagarNomina(aeropuerto.getEmpleados());
			} else if (option == 2) {
				System.out.println("\nListado de empleados");
				for (int i = 0; i < lempleados.size(); i++) {
					System.out.println((i + 1) + ". " + lempleados.get(i).getCargo() + ": "
							+ lempleados.get(i).getNombre() + ", sueldo = " + lempleados.get(i).getSueldo());
				}
				System.out.println("Selecciona el numero del empleado a pagar:");
				option2 = entrada.nextInt();

				if (option2 < 1 || option2 > lempleados.size()) {
					System.out.println("Error: numero incorrecto");
				} else {
					lempleados.get(option2 - 1).pagarNomina();
				}
			}
		} while (option != 3);
	}

	public static void mostrarPasajeros(Aeropuerto aeropuerto) {
		System.out.println("Estos son los pasajeros del aeropuerto:\n");
		System.out.println("Cedula         Nombre");
		for (Pasajero pasajero : aeropuerto.getPasajeros()) {
			System.out.println(pasajero.getCedula() + " ".repeat(15 - Integer.toString(pasajero.getCedula()).length())
					+ pasajero.getNombre());
		}
	}

	public static void Modificaciones(Aeropuerto aeropuerto) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("\n-- Bienvenido al sistema de administracion de vuelos y aviones --");

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("""
					1. Cambiar asiento.
					2. Cancelar vuelo.
					3. Eliminar avion.
					4. Volver.
					5. Finalizar.
					""");

			option = entrada.nextInt();
			switch (option) {
			case 1:
				cambiarAsiento(aeropuerto);
			case 2:
				break;
			case 3:
				break;
			case 4:
				opcionesPrincipales(aeropuerto);
				break;
			case 5:
				salirDelSistema(aeropuerto);
				break;
			}
		} while (option != 6);
	}

	private static void cambiarAsiento(Aeropuerto aeropuerto) {
		mostrarPasajeros(aeropuerto);
		Scanner entradas = new Scanner(System.in);
		System.out.println("Ingrese su documento de identidad.");
		int documento = entradas.nextInt();
		Pasajero pasajero = Pasajero.buscarPasajero(documento);
		if (documento == 0) {

		}
		if (pasajero == null) {
			System.out.println("El usuario no se encuentra registrado.\n");
			cambiarAsiento(aeropuerto);
		} else {
			pasajero.getAsiento().setOcupado(false); // Se habilita el asiento que tenía el pasajero
			reservaDeVuelo2(pasajero, aeropuerto);
		}
	}

	public static void reservaDeVuelo2(Pasajero pasajero, Aeropuerto aeropuerto) {
		System.out.println(pasajero.getAsiento());
		Scanner entradas = new Scanner(System.in);

		System.out.println("\nFormulario de datos personales");

		System.out.print("Inserte su documento de identidad: ");
		int documento = entradas.nextInt();
		if (documento == pasajero.getCedula()) {

		} else {
			System.out.println("Los datos no coinciden");
			reservaDeVuelo2(pasajero, aeropuerto);
		}

		Pasajero nuevoPasajero = new Pasajero(pasajero.getNombre(), documento, pasajero.getEdad(), pasajero.getSexo());

		System.out.println("\nLos asientos disponibles en el vuelo son los siguientes: ");
		for (Asiento asiento : pasajero.getVuelo().getAvion().getAsientos()) {
			if (!asiento.isOcupado())
				System.out.println(asiento);
		}

		System.out.print("\nIngrese el numero de asiento de su preferencia: ");
		int nroAsiento = entradas.nextInt();
		pasajero.getVuelo().agregarPasajero(nuevoPasajero, nroAsiento);
		System.out.println(pasajero.getVuelo().tiquete(nuevoPasajero));

		int option;
		do {
			System.out.println("\nIngrese el numero de la opcion a elegir:");
			System.out.print("""
					1. Volver.
					2. Finalizar.
					""");

			option = entradas.nextInt();
			switch (option) {
			case 1:
				opcionesPrincipales(aeropuerto);
			case 2:
				salirDelSistema(aeropuerto);
				break;
			}
		} while (option != 3);

	}

	public static void cambiarVuelo(List<Vuelo> vuelos) {
		Vuelo vuelo = null;
		for (int i = 0; i < vuelos.size(); i++) {
			vuelo = vuelos.get(i);
		}
	}

}
