package gestorAplicacion.gestionHumana;

import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.gestionVuelos.Asiento;
import gestorAplicacion.gestionVuelos.Equipaje;
import gestorAplicacion.gestionVuelos.Vuelo;

public class Pasajero extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asiento asiento;
	private Vuelo vuelo;
	private List<Equipaje> equipajes = new ArrayList<>();
	private int inversion;

	public Pasajero(String nombre, int cedula, int edad, String sexo, int inversion) {
		super(nombre, cedula, edad, sexo);
		this.inversion = inversion;
		aeropuerto.getPasajeros().add(this);
	}

	public void setInversion(int inversion) {
		this.inversion = inversion;
	}

	public int getInversion() {
		return inversion;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public List<Equipaje> getEquipajes() {
		return equipajes;
	}

	public void setEquipajes(List<Equipaje> equipajes) {
		this.equipajes = equipajes;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " Edad: " + edad + " Asiento: " + this.asiento.toString();
	}

	public static Pasajero buscarPasajero(int cedula) {
		for (Pasajero pasajero : aeropuerto.getPasajeros()) {
			if (pasajero.getCedula() == cedula) {
				return pasajero;
			}
		}
		return null;
	}
}