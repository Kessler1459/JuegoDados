package persistencia;

import java.io.*;

public class Persistencia {

	private static String archi = "Partidas.dat";
	/*
	public static void guardar_partida(ArrayList<Juego> aGuardar) {
		ObjectOutputStream save = null;
		try {
			save = new ObjectOutputStream(new FileOutputStream(archi));
			save.writeObject(aGuardar);
			save.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static ArrayList<Juego> cargar_partida() {
		ArrayList<Juego> aCargar = new ArrayList<Juego>();
		ObjectInputStream log = null;
		try {
			log = new ObjectInputStream(new FileInputStream(archi));
			aCargar = (ArrayList<Juego>)log.readObject();
			log.close();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(EOFException e){
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return aCargar;
	}*/
}
