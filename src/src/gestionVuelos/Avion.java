package gestionVuelos;

import administrador.Administrador;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Avion {
	private String modelo;
	private int pesoMaximo;
	private List<Asiento> asientos = new ArrayList<>();
	private int valor;
	static int globalID = 1;
	private final int id;

	public Avion(String modelo, int pesoMaximo, int valor) {
		this.modelo = modelo;
		this.pesoMaximo = pesoMaximo;
		this.valor = valor;
		this.genAsientos(10, 15);
		Administrador.aeropuerto.agregarAvion(this);
		this.id = globalID;
		Avion.globalID ++;
	}

	public void genAsientos(int min, int max) {
		List<String> tipoAsiento = new ArrayList<>();
		tipoAsiento.add("Turista");
		tipoAsiento.add("Ejecutiva");
		tipoAsiento.add("Primera clase");

		int cant = (int) (min + Math.random() * (max - min));

		for (int i = 1; i <= cant; i++) {
			int ind = (int) (Math.random() * 3);
			asientos.add(new Asiento(i, tipoAsiento.get(ind)));
		}
	}

	public int getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(int pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public static Avion comprarAvion() {
		String modelot;
		int pesoMaximot;
		int valort;

		Scanner entrada = new Scanner(System.in);
		System.out.println("---COMPRAR NUEVO AVION---");
		System.out.print("Por favor inserte el modelo del Avion: ");
		modelot = entrada.nextLine();
		System.out.print("Por favor inserte el peso maximo del Avion: ");
		pesoMaximot = entrada.nextInt();
		System.out.print("Por favor inserte el valor a pagar por el Avion: ");
		valort = entrada.nextInt();

		Administrador.aeropuerto.transaccion("Compra de avion " + modelot, valort * (-1));
		//Aeropuerto.retirarDinero(valort);
		return new Avion(modelot, pesoMaximot, valort);
	}
}
