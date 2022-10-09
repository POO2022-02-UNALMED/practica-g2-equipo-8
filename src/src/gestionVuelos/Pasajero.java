package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Pasajero {
    private int edad;
    private int cedula;
    private Asiento asiento;
    private Vuelo vuelo;
    private String nombre;
    private List<Equipaje> equipajes;

    public Pasajero(int edad, int cedula, String nombre) {
        this.edad = edad;
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipaje> getEquipajes() {
        return equipajes;
    }

    public void setEquipajes(List<Equipaje> equipajes) {
        this.equipajes = equipajes;
    }
}
