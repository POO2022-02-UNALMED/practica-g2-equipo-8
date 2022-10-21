package gestionVuelos;

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
	}
}
