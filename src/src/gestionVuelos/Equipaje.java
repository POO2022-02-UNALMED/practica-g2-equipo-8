package gestionVuelos;

public class Equipaje {
    private int id;
    private double peso;
    private Pasajero propietario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Pasajero getPropietario() {
        return propietario;
    }

    public void setPropietario(Pasajero propietario) {
        this.propietario = propietario;
    }
}
