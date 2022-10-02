package administrador;

import java.util.Scanner;

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
		System.out.println("Bienvenido Administrador\n¿Qué desea hacer hoy? Ingrese el número de la opción a elegir:");
		System.out.println("1. Reserva de vuelo.\n" + "2. Ver información del vuelo.\n" + "3. Asignar empleados.\n"
				+ "4. Administrar finanzas.\n" + "5. Administrar vuelos y aviones.\n\n" + "6. Finalizar programa.\n");
		Scanner entrada = new Scanner(System.in);
		int option = entrada.nextInt();
		System.out.println(option);
	}

}
