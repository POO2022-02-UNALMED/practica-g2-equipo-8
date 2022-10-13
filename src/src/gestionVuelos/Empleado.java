package gestionVuelos;

import java.util.List;

public class Empleado {
    private String nombre;
    private int sueldo;
    private int cedula;
    private String cargo;

    public Empleado(String nombre, int sueldo, int cedula, String cargo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.cedula = cedula;
        this.cargo = cargo;
        Aeropuerto.agregarEmpleado(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void pagarNomina() {
        float dineroapagar = this.getSueldo();
        float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

        if (nuevosaldo < 0) {
            System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
        } else {
            Aeropuerto.setDinero(nuevosaldo);
            System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
        }
    }
    public static void pagarNomina(List<Empleado> empleados){
        float dineroapagar = 0;
        for (Empleado empleado : empleados) {
            dineroapagar += empleado.getSueldo();
        }
        float nuevosaldo = Aeropuerto.getDinero() - dineroapagar;

        if (nuevosaldo < 0) {
            System.out.println("No se ha podido realizar la transaccion: no tienes suficiente dinero");
        } else {
            Aeropuerto.setDinero(nuevosaldo);
            System.out.println("Transaccion realizada, nuevo saldo = " + Aeropuerto.getDinero());
        }
    }
}
