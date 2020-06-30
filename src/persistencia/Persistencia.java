package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import diez.Diezmil;
import gene.Generala;
import org.json.*;

import clases.Juego;

/**
 * metodos de lectura y escritura de archivo de tipo JSON
 */
public class Persistencia {

	/**
	 * nombres que podra tomar el archivo
	 */
	private static final String[] game = {"DiezMil","Generala"};

    /**
     * guarda una partida del juego en un archivo con un JSONArray
     * @param j el juego a guardar
     * @return boolean si tuvo exito o no
     */
	 public static boolean guardarPartida(Juego j)
	    {
	        boolean resultado;
	        JSONArray partidas;                          //lee el array ya guardado en archivo
			if (j instanceof Generala)						//le agrega la partida nueva
			{
			    partidas=levantarArchivo(1);
				Generala ge=(Generala)j;
				partidas.put(To.generalaToJSON(ge));
                resultado=escribirArray(partidas,1);             //vuelve a escribir tod0
			}
	        else {
	            Diezmil diez=(Diezmil)j;
                partidas=levantarArchivo(0);
				partidas.put(new JSONObject(diez));          //aca lo mismo pero para diezmil
                resultado=escribirArray(partidas,0);
			}
	        return resultado;
	    }

    /**
     * lee el archivo y crea un JSONArray
     * @param juego 0 para leer el de DiezMil, 1 para Generala
     * @return devuelve el JSONArray cargado o vacio si el archivo no existia aun
     */
	    public static JSONArray levantarArchivo(int juego)
	    {
	    	JSONArray array =null;
	        try {
	        	array = new JSONArray(new String(Files.readAllBytes(Paths.get("archivo"+game[juego]+".json"))));
	        }
	        catch (EOFException e)
			{
				System.out.println("fin de archivo");
			}
	        catch (NoSuchFileException e) 							//si el archivo no existe, se crea un array vacio, se lo escribe vacio, y se retorna
			{
				JSONArray nuevo=new JSONArray();
				escribirArray(nuevo,juego);
				return nuevo;
			}
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        return array;                                       //sino, devuelve el array leido del archivo
	    }

    /**
     * escribe un JSONArray en un archivo de tipo json
     * @param ar el JSONArray a escribir
     * @param juego 0 para escribir el de DiezMil, 1 para Generala
     * @return boolean si tuvo exito o no
     */
	    public static boolean escribirArray(JSONArray ar,int juego)
	    {
	        try
	        {
				FileWriter file = new FileWriter("archivo"+game[juego]+".json");
				file.write(ar.toString());
				file.flush();
				file.close();
				return true;
	        }
	        catch (IOException e)
			{
	            e.printStackTrace();
	            return false;
	        }
	    }
}
