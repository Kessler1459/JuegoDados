package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import gene.Generala;
import org.json.*;

import clases.Juego;

public class Persistencia {

	private static final String game = "Generala";
	
	 public static void guardarPartida(Juego j)
	    {
	        JSONArray partidas=levantarArchivo();          //lee el array ya guardado en archivo
			if (j instanceof Generala)						//le agrega la partida nueva
			{
				Generala ge=(Generala)j;
				partidas.put(To.generalaToJSON(ge));
			}
	        else {
	        	/*
				partidas.put(To.generalaToJSON((Generala) j));  aca lo mismo pero para diezmil

	        	 */
			}
	        escribirArray(partidas);                        //vuelve a escribir todo
	    }
	 
	    public static JSONArray levantarArchivo()
	    {
	    	JSONArray array =null;
	        try {
	        	array = new JSONArray(new String(Files.readAllBytes(Paths.get("archivo"+game+".json"))));
	        }
	        catch (EOFException e)
			{
				System.out.println("fin de archivo");
			}
	        catch (NoSuchFileException e) 							//si el archivo no existe, se crea un array vacio, se lo escribe vacio, y se retorna
			{
				JSONArray nuevo=new JSONArray();
				escribirArray(nuevo);
				return nuevo;
			}
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        return array;                                       //sino, devuelve el array leido del archivo
	    }
	 
	    public static void escribirArray(JSONArray ar)          
	    {
	        try
	        {
				FileWriter file = new FileWriter("archivo"+game+".json");
				file.write(ar.toString());
				file.flush();
				file.close();
	        }
	        catch (IOException e)
			{
	            e.printStackTrace();
	        }
	    }
}
