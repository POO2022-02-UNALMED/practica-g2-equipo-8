package basedatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import gestionVuelos.Aeropuerto;

public class Deserializador {
	private static File rutaTempFile = new File("src//basedatos//temp");

	public static void deserializarAeropuerto(Aeropuerto aeropuerto) {
		File[] dosc = rutaTempFile.listFiles();
		FileInputStream fis;
		ObjectInputStream ois;

		for (File file : dosc) {
			if (file.getAbsolutePath().contains("aeropuertos")) {
				try {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);

					Aeropuerto aux = (Aeropuerto) ois.readObject();

					//aeropuerto.copiarAeropuerto(aux);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
