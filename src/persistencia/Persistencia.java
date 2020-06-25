package persistencia;

import java.io.*;

import org.json.*;

import clases.Juego;

public class Persistencia {
	
	//Lo hacemos poli
	private static final String game = "Generala";
	
	 public static void guardarPartida(Juego j)
	    {
	        JSONArray partidas=levantarArchivo();          //lee el array ya guardado en archivo
	        partidas.put(To.ToJSON(j));                            //le agrega la partida nueva
	        escribirArray(partidas);                        //vuelve a escribir todo
	    }
	 
	    public static JSONArray levantarArchivo()
	    {
	    	JSONArray array =null;
	        try {
	            	
	        		DataInputStream data = new DataInputStream(new FileInputStream("archivo"+game+".dat"));

					try {
						array = new JSONArray(data.readUTF());
					} catch (JSONException e) {
						e.printStackTrace();
					}
					data.close();

	        }
	        catch (FileNotFoundException e)
	        {
	        	JSONArray nuevo=new JSONArray();                  //si el archivo no existe, se crea un array vacio, se lo escribe vacio, y se retorna
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
	        DataOutputStream data=null;
	    	   
	        try
	        {
	            data=new DataOutputStream(new FileOutputStream("archivoGenerala.dat"));
	            data.writeUTF(ar.toString());
	            data.close();
	        }  catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
