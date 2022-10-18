package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Persona {

    private Asiento asiento;
    private Vuelo vuelo;
    private List<Equipaje> equipajes;

    public Pasajero(int edad, int cedula, String nombre, String sexo) {
        super(nombre, cedula, edad, sexo);
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
        return "Nombre: "+nombre+" Edad: " + edad + " Asiento: " + this.asiento.toString();
    }
}
