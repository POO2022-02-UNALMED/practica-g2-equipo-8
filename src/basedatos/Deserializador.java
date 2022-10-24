package basedatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import gestionVuelos.Aeropuerto;

public class Deserializador {
	public static <E> void deserializar(Aeropuerto aeropuerto) {
		FileInputStream fin;

		try {
			String ruta = System.getProperty("user.dir") + "\\src\\basedatos\\temp\\aeropuerto.txt";
			fin = new FileInputStream(ruta);
			ObjectInputStream oos = new ObjectInputStream(fin);
			Aeropuerto e = (Aeropuerto) oos.readObject();

			aeropuerto.setAviones(e.getAviones());
			aeropuerto.setDinero(e.getDinero());
			aeropuerto.setEmpleados(e.getEmpleados());
			aeropuerto.setNombre(e.getNombre());
			aeropuerto.setPasajeros(e.getPasajeros());
			//aeropuerto.setSalas(e.getSalas());
			aeropuerto.setVuelos(e.getVuelos());

			oos.close();
			fin.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
