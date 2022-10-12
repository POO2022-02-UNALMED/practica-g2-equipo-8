package gestionVuelos;

public class Asiento {
    private char fila;
    private int numero;
    private boolean ocupado;
    private String clase;

    public Asiento(int numero, String clase) {
        this.numero = numero;
        this.clase = clase;
    }

    @Override
    public String toString(){
        return "Fila: "+fila+" - numero: "+numero+" - clase: "+clase;
    }

    public char getFila() {
        return fila;
    }

    public void setFila(char fila) {
        this.fila = fila;
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
