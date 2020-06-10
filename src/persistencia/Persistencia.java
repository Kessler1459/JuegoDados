package persistencia;

import java.io.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;


public class Persistencia {

	private static String archi = "Partidas.dat";
	
	public static void guardar_partida(JSONArray aGuardar) {
		
		DataOutputStream save = null;
		try {
			save = new DataOutputStream(new FileOutputStream(archi));
			for(int i = 0; i < aGuardar.length(); i++) {
				try {
					save.writeUTF(aGuardar.getJSONObject(i).toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				save.flush();
			}
			save.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static ArrayList<String> cargar_partida() {
		String aux;
		ArrayList<String> datos = new ArrayList<String>();
		DataInputStream log = null;
		try {
			log = new DataInputStream(new FileInputStream(archi));
			while((aux = (String)log.readUTF()) != null) {
				datos.add(aux);
			}
		} catch(EOFException e){
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datos;
	}
}
