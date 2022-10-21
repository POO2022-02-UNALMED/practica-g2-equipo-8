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
		List<Equipaje> equipaje = new ArrayList<>();
		Pasajero pasajero = new Pasajero("Pepito",897915, 15,"M" );

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
				+ "0. Pulse 0 en cualquier menu para finalizar el programa.\n");
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
			Modificaciones();
			break;
		case 0:
			salirDelSistema();
			break;
		default:
			System.out.println("Opcion incorrecta, vuelva a intentarlo.");
			opcionesPrincipales(entrada);
		}
	}

	public static void gestionarEmpleadosInterfaz() {
		mostrarEmpleados();
		System.out.println("Introduzca la cedula para ver mas opciones:");
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
			System.out.println("Esta cedula no esta asignada a ningun empleado, vuelva a intentarlo.");
		}
		Empleado empleadoActual = Empleado.buscarEmpleado(cedula);
		System.out.println(empleadoActual);
		System.out.println("Seleccione la accion que quiere realizar:\n1. Cambiar cargo.");
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
		Pasajero nuevoPasajero = new Pasajero(nombre,documento,edad, sexo);

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
		System.out.println(vueloElegido.tiquete(nuevoPasajero));
		cambiarVuelo(vuelosDisp);

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
					opcionesPrincipales(entradas);
				case 2:
					salirDelSistema();
					break;
			}
		} while (option != 3);
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
					4. Contratar nuevo empleado.
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
				Empleado.nuevoEmpleado();
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

    public static void Modificaciones() {
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
					cambiarAsiento();
                case 2:
                    break;
                case 3:
                    break;
				case 4:
					opcionesPrincipales(entrada);
					break;
				case 5:
					salirDelSistema();
					break;
            }
        } while (option != 6);
    }


	private static void cambiarAsiento(){
		Scanner entradas = new Scanner(System.in);
		System.out.println("Ingrese su documento de identidad.");
		int documento = entradas.nextInt();
		System.out.println(documento);
		if(buscarPasajero(documento) == false){
			System.out.println(Vuelo.getPasajeros());
			System.out.println(buscarPasajero(documento));
			System.out.println(Vuelo.getPasajeros());
			System.out.println("El usuario no se encuentra registrado.");
		}
		else{
			reservaDeVuelo();
		}

		//System.out.println("Ingrese el ID del vuelo: ");
		//int id = entradas.nextInt();

		/*
		System.out.println("\nLos asientos disponibles en el vuelo son los siguientes: ");
		for(Asiento asiento : Avion.getAsientos()){
			if(!asiento.isOcupado()){
				System.out.println(asiento);
			}
		}

		System.out.print("\nIngrese el numero de asiento de su preferencia: ");
		int nroAsiento = entradas.nextInt();
		obtenerVuelo(documento, id);
		obtenerVuelo(documento, id).agregarPasajero(, nroAsiento);
		System.out.println(vueloElegido.tiquete(nuevoPasajero));

		 */

		}

	public static boolean buscarPasajero(int documento){
		boolean encontrado = false;
		for(int i = 0; i < Vuelo.getPasajeros().size(); i++){
			if(Vuelo.getPasajeros().get(i).getCedula() == documento){
				encontrado = true;
			}
			else {encontrado = false;}
			System.out.println("i " + documento + " " + Vuelo.getPasajeros().get(i).getCedula());
		}
		return encontrado;
	}

	public static void cambiarVuelo(List<Vuelo> vuelos){
		Vuelo vuelo = null;
		for(int i = 0; i < vuelos.size(); i++){
			vuelo = vuelos.get(i);
		}
	}

	public static Vuelo obtenerVuelo(int documento, int id){
		Vuelo encontrado = null;
		for(int i = 0; i < Vuelo.getPasajeros().size(); i++){
			if(Vuelo.getPasajeros().get(i).getVuelo().getId() == id) {
				encontrado = Vuelo.getPasajeros().get(i).getVuelo();
			}
		}
		return encontrado;
	}

}
