package gestionVuelos;

import administrador.Administrador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vuelo {
    private Avion avion;
    private List<Empleado> empleados = new ArrayList<>();
    private List<Pasajero> pasajeros = new ArrayList<>();
    private Date fecha;
    private String origen;
    private String destino;
    private boolean enVuelo;
    private int costo;
    private String salaEmbarque;
    private double pesoActual;

    public Vuelo(Avion avion, Date fecha, String origen, String destino, int costo, String salaEmbarque) {
        this.avion = avion;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
        this.salaEmbarque = salaEmbarque;
        Aeropuerto.agregarVuelo(this);
    }

    public void agregarPasajero(Pasajero pasajero, int nroAsiento) {

        double pesoEquipajePasajero = 0;
        for(Equipaje equipaje: pasajero.getEquipajes()) pesoEquipajePasajero += equipaje.getPeso();

        if (pesoActual+pesoEquipajePasajero < avion.getPesoMaximo() && pasajeros.size() < avion.getAsientos().size() && !avion.getAsientos().get(nroAsiento).isOcupado()){
            pesoActual += pesoEquipajePasajero;
            pasajeros.add(pasajero);
            pasajero.setAsiento(avion.getAsientos().get(nroAsiento));
            pasajero.setVuelo(this);
            avion.getAsientos().get(nroAsiento).setOcupado(true);
            Administrador.ingresarDinero(costo);
        } else {
            System.out.println("No queda espacio suficiente en este vuelo, lo sentimos mucho.");
        }
    }

    //Este metodo servira para verificar los vuelos disponibles en el main
    public boolean vueloLleno(){
        return pesoActual < avion.getPesoMaximo() && pasajeros.size() < avion.getAsientos().size();
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

    public List<Pasajero> getPasajeros() {
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

    public void setOrigen(String origen) {
        this.origen = origen;
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
}
