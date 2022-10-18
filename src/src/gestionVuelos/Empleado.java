package gestionVuelos;

import java.util.List;

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
			return "Nombre: " + nombre + ".\nCedula: " + cedula + "\nEdad: " + edad
					+ "\nSexo: " + sexo + ".\nCargo: " + cargo + ".\nSueldo: " + sueldo
					+ "$.\nVuelo: Este empleado aún no tiene un vuelo asignado.";
		} else {
			return "Nombre: " + nombre + ".\nCedula: " + cedula + "\nEdad: " + edad
					+ "\nSexo: " + sexo + ".\nCargo: " + cargo + ".\nSueldo: " + sueldo
					+ "$.\nVuelo: " + vuelo.toString();
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
		float dineroapagar = this.getSueldo();
		float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

		if (nuevosaldo < 0) {
			System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
		} else {
			Aeropuerto.setDinero(nuevosaldo);
			System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
		}
	}

	public static void pagarNomina(List<Empleado> empleados) {
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
}
