package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Persona {
    private Asiento asiento;
    private Vuelo vuelo;
    private List<Equipaje> equipajes;

    public Pasajero(String nombre, int cedula,int edad, String sexo) {
        super(nombre,cedula,edad,sexo);
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
}
