package gestionVuelos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface zonasEmbarque{

    String sala1 = "A1";
    String sala2 = "A2";
    String sala3 = "A3";
    String sala4 = "B1";
    String sala5 = "B2";
    String sala6 = "B3";
    String sala7 = "C1";
    String sala8 = "C2";
    String sala9 = "C3";
    String sala10 = "D1";
    String sala11 = "D2";
    String sala12 = "D3";

    List<String> conjunto = new ArrayList<>(Arrays.asList(sala1, sala2, sala3, sala4, sala5, sala6, sala7, sala8, sala9, sala10, sala11, sala12));

    static void mostrarZonas(){
        System.out.println("\n\tZona A");
        for (int i=0; i < 3; i++) {
            System.out.print(" - "+conjunto.get(i)+" - ");
        }
        System.out.println("\n\tZona B");
        for (int i=3; i < 6; i++) {
            System.out.print(" - "+conjunto.get(i)+" - ");
        }
        System.out.println("\n\tZona C");
        for (int i=6; i < 9; i++) {
            System.out.print(" - "+conjunto.get(i)+" - ");
        }
        System.out.println("\n\tZona D");
        for (int i=9; i < 12; i++) {
            System.out.print(" - "+conjunto.get(i)+" - ");
        }
        System.out.print("\n");
    }
}
