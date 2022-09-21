package gestionVuelos;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private int id;
    private String modelo;
    private int pesoMaximo;
    private List<Asiento> asientos = new ArrayList<>();
    private int valor;

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
}
