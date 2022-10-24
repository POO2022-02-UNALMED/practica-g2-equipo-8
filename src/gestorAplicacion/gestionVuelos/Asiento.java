// Autores: 
/*
 * 
 */
package gestorAplicacion.gestionVuelos;

import java.io.Serializable;

public class Asiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private boolean ocupado;
	private String clase;

	public Asiento(int numero, String clase) {
		this.numero = numero;
		this.clase = clase;
	}

	@Override
	public String toString() {
		return "numero: " + numero + " - clase: " + clase;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
}
