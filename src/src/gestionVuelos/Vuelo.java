package gestionVuelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vuelo {
	private Avion avion;
	private List<Empleado> empleados = new ArrayList<>();
	private static List<Pasajero> pasajeros = new ArrayList<>();
	private Date fecha;
	private final static String origen = "Medellin";
	private String destino;
	private boolean enVuelo;
	private int costo;
	private String salaEmbarque;
	private double pesoActual;
	private static int globalID = 1;
	private final int id;

	public Vuelo(Avion avion, Date fecha, String destino, int costo, String salaEmbarque) {
		this.avion = avion;
		this.fecha = fecha;
		this.destino = destino;
		this.costo = costo;
		this.salaEmbarque = salaEmbarque;
		Aeropuerto.agregarVuelo(this);
		this.id = globalID;
		Vuelo.globalID++;
	}

	public void agregarPasajero(Pasajero pasajero, int nroAsiento) {

		double pesoEquipajePasajero = 0;
		for (Equipaje equipaje : pasajero.getEquipajes())
			pesoEquipajePasajero += equipaje.getPeso();
		Asiento asientoElegido = avion.getAsientos().get(nroAsiento - 1);

		if (pesoActual + pesoEquipajePasajero < avion.getPesoMaximo() && pasajeros.size() < avion.getAsientos().size()
				&& !asientoElegido.isOcupado()) {
			pesoActual += pesoEquipajePasajero;
			pasajeros.add(pasajero);
			pasajero.setAsiento(asientoElegido);
			pasajero.setVuelo(this);
			asientoElegido.setOcupado(true);
			if (asientoElegido.getClase().equals("primera clase"))
				Aeropuerto.ingresarDinero(3 * costo);
			else if (asientoElegido.getClase().equals("ejecutiva"))
				Aeropuerto.ingresarDinero(2 * costo);
			else
				Aeropuerto.ingresarDinero(costo);
			System.out.println("Ha sido registrado exitosamente.");
		} else if (pesoActual + pesoEquipajePasajero > avion.getPesoMaximo()) {
			System.out.println(
					"No queda espacio suficiente en este vuelo para su equipaje, por favor elija otro vuelo o reduzca el peso.");
		} else {
			System.out.println("Ha elegido un puesto no disponible, por favor elija otro.");
		}
	}

	// Este metodo servira para verificar los vuelos disponibles en el main
	public boolean vueloLleno() {
		return pesoActual < avion.getPesoMaximo() && pasajeros.size() < avion.getAsientos().size();
	}

	@Override
	public String toString() {
		String infoVuelo = "ID vuelo: " + id + " - Fecha del vuelo: " + fecha + " - Origen: " + origen + " - Destino: "
				+ destino + " - Precio: " + costo + "\n";
		return infoVuelo;
	}

	public String tiquete(Pasajero pasajero){
		String tique =
					"------------------------------------\n" +
					"      Tiquete No." + this.id + "\n" +
					"------------------------------------\n" +
					"Nombre Pasajero: " + pasajero.getNombre() + "\n" +
					"Fecha: " + fecha + "\n" +
					"Vuelo: " + getId() + "\n" +
					"Clase: " + pasajero.getAsiento().getClase() + "\n" +
					"Num Silla: " + pasajero.getAsiento().getNumero() + "\n" +
					"Origen: " + origen + "\n" +
					"Destino: " + getDestino() + "\n" +
					"Precio Total: " + getCosto() + "\n" +
					"------------------------------------\n";
		return tique;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public static List<Pasajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public boolean isEnVuelo() {
		return enVuelo;
	}

	public void setEnVuelo(boolean enVuelo) {
		this.enVuelo = enVuelo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getSalaEmbarque() {
		return salaEmbarque;
	}

	public void setSalaEmbarque(String salaEmbarque) {
		this.salaEmbarque = salaEmbarque;
	}

	public double getPesoActual() {
		return pesoActual;
	}

	public void setPesoActual(double pesoActual) {
		this.pesoActual = pesoActual;
	}

	public int getId() {
		return id;
	}
}
