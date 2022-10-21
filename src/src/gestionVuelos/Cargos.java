package gestionVuelos;

import java.util.Scanner;

public enum Cargos {
	AZAFATA("Azafata", 1000), CONTROL_DE_PISTA("Control de pista", 1500), PILOTO("Piloto", 2000),
	COPILOTO("Copiloto", 1800);

	private String cargo;
	private int sueldoBase;
	private final int id;

	private static final class StaticFields {
		private static int nextId = 0;

	}

	Cargos(String cargo, int sueldoBase) {
		StaticFields.nextId++;
		this.id = StaticFields.nextId;
		this.cargo = cargo;
		this.sueldoBase = sueldoBase;
	}

	public String getCargo() {
		return cargo;
	}

	public int getSueldoBase() {
		return sueldoBase;
	}

	public int getId() {
		return id;
	public static Cargos elegirCargo() {
		for (int i = 0; i < Cargos.values().length; i++) {
			System.out.println((i+1) + " " + Cargos.values()[i].cargo);
		}
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese el indice del cargo a elegir: ");
		int indice = entrada.nextInt();
		return Cargos.values()[indice - 1];
	}
}
