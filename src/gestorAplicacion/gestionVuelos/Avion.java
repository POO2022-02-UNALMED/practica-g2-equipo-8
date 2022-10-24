// Autores: 
/*
 * 
 */
package gestorAplicacion.gestionVuelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Avion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private static int globalID = 1;
	private String modelo;
	private int pesoMaximo;
	private List<Asiento> asientos = new ArrayList<>();
	private int valor;
	private static Aeropuerto aeropuerto;

	public Avion(String modelo, int pesoMaximo, int valor) {
		this.modelo = modelo;
		this.pesoMaximo = pesoMaximo;
		this.valor = valor;
		this.id = globalID;
		globalID++;
		this.genAsientos(10, 15);
		aeropuerto.agregarAvion(this);
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

	public void setId(int id) {
		this.id = id;
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

	/*
	 * public static Avion comprarAvion() { String modelot; int pesoMaximot; int
	 * valort;
	 * 
	 * Scanner entrada = new Scanner(System.in);
	 * System.out.println("---COMPRAR NUEVO AVION---");
	 * System.out.print("Por favor inserte el modelo del Avion: "); modelot =
	 * entrada.nextLine();
	 * System.out.print("Por favor inserte el peso maximo del Avion: "); pesoMaximot
	 * = entrada.nextInt();
	 * System.out.print("Por favor inserte el valor a pagar por el Avion: "); valort
	 * = entrada.nextInt();
	 * 
	 * aeropuerto.transaccion("Compra de avion " + modelot, valort * (-1)); //
	 * Aeropuerto.retirarDinero(valort); return new Avion(modelot, pesoMaximot,
	 * valort); }
	 */

	public static void setAeropuerto(Aeropuerto aeropuerto) {
		Avion.aeropuerto = aeropuerto;
	}

	@Override
	public String toString() {
		return "ID avion: " + id + " - Modelo: " + modelo + " - Cantidad de asientos: " + asientos.size()
				+ " - Peso maximo: " + pesoMaximo;
	}
}
