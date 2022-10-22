package gestionVuelos;

import java.util.List;
import java.util.Scanner;

public class Empleado extends Persona {

	private int sueldo;
	private String cargo;
	private Vuelo vuelo;

	public Empleado(String nombre, int sueldo, int cedula, Cargos cargo, int edad, String sexo) {
		super(nombre, cedula, edad, sexo);
		this.sueldo = sueldo;
		this.cargo = cargo.getCargo();
		Aeropuerto.agregarEmpleado(this);
	}

	public Empleado(String nombre, int cedula, Cargos cargo, int edad, String sexo) {
		this(nombre, cargo.getSueldoBase(), cedula, cargo, edad, sexo);
	}

	@Override
	public String toString() {
		if (this.vuelo == null) {
			return "Nombre: " + nombre + ".\nCedula: " + cedula + "\nEdad: " + edad + "\nSexo: " + sexo + ".\nCargo: "
					+ cargo + ".\nSueldo: " + sueldo + "$.\nVuelo: Este empleado aun no tiene un vuelo asignado.";
		} else {
			return "Nombre: " + nombre + ".\nCedula: " + cedula + "\nEdad: " + edad + "\nSexo: " + sexo + ".\nCargo: "
					+ cargo + ".\nSueldo: " + sueldo + "$.\nVuelo: " + vuelo.toString();
		}
	}

	public static void mostrarEmpleados() {
		System.out.println("Estos son los empleados del aeropuerto:\n");
		System.out.println("Cedula         Nombre");
		for (Empleado empleado : Aeropuerto.getEmpleados()) {
			System.out.println(empleado.getCedula() + " ".repeat(15 - Integer.toString(empleado.getCedula()).length())
					+ empleado.getNombre());
		}
	}

	public static Empleado buscarEmpleado(int cedula) {
		for (Empleado empleado : Aeropuerto.getEmpleados()) {
			if (empleado.getCedula() == cedula) {
				return empleado;
			}
		}
		return null;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo.getCargo();
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
		this.vuelo.getEmpleados().add(this);
	}

	public void pagarNomina() {
		int dineroapagar = this.getSueldo();
		float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

		if (nuevosaldo < 0) {
			System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
		} else {
			//Aeropuerto.setDinero(nuevosaldo);
			Aeropuerto.transaccion("Nomina " + this.getNombre(), dineroapagar * (-1));
			System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
		}
	}

	public static void pagarNomina(List<Empleado> empleados) {
		int dineroapagar = 0;
		for (Empleado empleado : empleados) {
			dineroapagar += empleado.getSueldo();
		}
		float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

		if (nuevosaldo < 0) {
			System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
		} else {
			Aeropuerto.transaccion("Nomina General", dineroapagar * (-1));
			//Aeropuerto.setDinero(nuevosaldo);
			System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
		}
	}

	public static Empleado nuevoEmpleado() {
		String nombret;
		int cedulat;
		Cargos cargot;
		int edadt;
		String sexot;
		int sueldot;

		Scanner entrada = new Scanner(System.in);
		System.out.println("---NUEVO EMPLEADO---");
		System.out.print("Por favor inserte el nombre del empleado: ");
		nombret = entrada.nextLine();
		System.out.print("Por favor elija el cargo del empleado:\n");
		cargot = Cargos.elegirCargo();
		System.out.print("Por favor inserte el sexo del empleado, (M) para hombres y (F) para mujeres: ");
		sexot = entrada.nextLine();
		System.out.print("Por favor inserte la cedula del empleado: ");
		cedulat = entrada.nextInt();
		System.out.print("Por favor inserte la edad del empleado: ");
		edadt = entrada.nextInt();
		System.out.print("Por favor inserte el sueldo del empleado: \n(Inserte 0 si desea asignarle el precio base): ");
		sueldot = entrada.nextInt();

		if (sueldot == 0) {
			sueldot = cargot.getSueldoBase();
		}
		System.out.println("Se ha agragado al empleado " + nombret);
		return new Empleado(nombret, sueldot, cedulat, cargot, edadt, sexot);
	}

	public static void cambiarSueldo(){
		Scanner entrada = new Scanner(System.in);
		List<Empleado> lempleados = Aeropuerto.getEmpleados();

		System.out.println("\nListado de empleados");
		for (int i = 0; i < lempleados.size(); i++) {
			System.out.println((i + 1) + ". " + lempleados.get(i).getCargo() + ": "
					+ lempleados.get(i).getNombre() + ", sueldo = " + lempleados.get(i).getSueldo());
		}
		System.out.println("Selecciona el numero del empleado a cambiar sueldo:");
		int option = entrada.nextInt();

		System.out.println("Empleado seleccionado: ");
		System.out.println(lempleados.get(option - 1).getCargo() + ": "
				+ lempleados.get(option - 1).getNombre() + ", sueldo = " + lempleados.get(option - 1).getSueldo());

		System.out.println("\nIngresa el nuevo sueldo: ");
		int option2 = entrada.nextInt();

		lempleados.get(option-1).setSueldo(option2);
		System.out.println("Nuevo sueldo de " + lempleados.get(option - 1).getNombre() + " es de: " + lempleados.get(option - 1).getSueldo());
	}
}
