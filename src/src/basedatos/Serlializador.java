package basedatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import gestionVuelos.Aeropuerto;

public class Serlializador {
	private static File rutaTempFile = new File("src//basedatos//temp");

	public static void serializarAeropuertos(Aeropuerto aeropuerto) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		File[] docs = rutaTempFile.listFiles();
		PrintWriter pw;

		for (File file : docs) {
			try {
				pw = new PrintWriter(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (File file : docs) {
			if (file.getAbsolutePath().contains("aeropuertos")) {
				try {
					fos = new FileOutputStream(file);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(aeropuerto);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
