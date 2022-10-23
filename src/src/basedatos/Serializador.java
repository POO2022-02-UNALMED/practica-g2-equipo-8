package basedatos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import gestionVuelos.Aeropuerto;

public class Serializador {
	public static <E> void serializar(Aeropuerto aeropuerto, String archivo) {
		FileOutputStream fos;
		try {
			String ruta = System.getProperty("user.dir") + "\\src\\basedatos\\temp\\aeropuerto.txt";
			fos = new FileOutputStream(ruta);
			ObjectOutputStream outputStream = new ObjectOutputStream(fos);
			outputStream.writeObject(aeropuerto);
			System.out.println("Se ha guardado correctamente el objeto en el archivo: " + archivo);
			outputStream.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void serializar() {
		serializar(new Aeropuerto(), "aeropuertos");
	}

}
