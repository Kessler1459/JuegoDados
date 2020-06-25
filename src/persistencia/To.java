package persistencia;

import java.util.*;

import org.json.*;

import clases.*;
import gene.*;

public class To {
	
	/**
	 * Transforma un juego a un JSONObject
	 * @param juego (Puede ser tanto Generala como Diezmil)
	 * @return JSONObject
	 */
	public static JSONObject ToJSON(Juego j) {
		JSONObject Jo = new JSONObject(j);
		System.out.println(Jo.toString());
		return Jo;
	}

	/**
	 * Transforma un JSONArray en un ArrayList de tipo generala
	 * @param JSONArray
	 * @return ArrayList<Generala>
	 */
	
    public static ArrayList<Generala> toArrayListG(JSONArray leido){
    	ArrayList<Generala> arregloGen = new ArrayList<Generala>();
    	int i = 0;
		
		while(i < leido.length()) {
				try {
					JSONObject Oj = leido.getJSONObject(i);
					Generala juego = toGenerala(Oj);
					arregloGen.add(juego);
					i++;
				} catch (JSONException e) {
					e.printStackTrace();
				}

		}
		return arregloGen;
    }
    
    /**
     * Transforma un JSONObject a un Objeto Generala
     * @param JSONObject
     * @return Generala
     * @throws JSONException 
     */
    public static Generala toGenerala(JSONObject Oj) throws JSONException {
    	int duracionDePartida = Oj.getInt("duracionDePartida");
		int tiradas = Oj.getInt("tiradas");
		ArrayList<Jugador> Aj = toArrayListJg(Oj);
		ArrayList<Dado> Ad = toArrayListD(Oj);
		int turno = Oj.getInt("turno");
		Generala gen = new Generala(duracionDePartida, tiradas, Aj, Ad, turno);
		return gen;
    }
    
    /**
     * Devuelve el ArrayList de jugadores desde el JSON
     * @param Oj
     * @return ArrayList Jugadores
     * @throws JSONException
     */
    public static ArrayList<Jugador> toArrayListJg(JSONObject Oj) throws JSONException{
		ArrayList<Jugador> Aj = new ArrayList<Jugador>();
		for( int k=0; k < Oj.getJSONArray("jugadores").length(); k++){
			PuntajeGenerala puntos = new PuntajeGenerala(toHashMapPg(Oj, k));		
			Aj.add(new Jugador(Oj.getJSONArray("jugadores").getJSONObject(k).getString("nombre"), puntos));
		}
		return Aj;
    }
    
    /**
     * Devuelve un HashMap de los puntos de la generala desde JSON
     * @param Oj
     * @param posicion
     * @return HashMap tabla
     * @throws JSONException
     */
    public static HashMap<String, String> toHashMapPg(JSONObject Oj, int pos) throws JSONException{
		HashMap<String, String> tabla = new HashMap<String, String>();
		
		JSONObject OjTabla =  Oj.getJSONArray("jugadores").getJSONObject(pos).getJSONObject("puntosGen");
		
		tabla.put("GeneralaDoble", OjTabla.getString("generalaDoble"));
		tabla.put("Generala", OjTabla.getString("generala"));
		tabla.put("Poker", OjTabla.getString("poker"));
		tabla.put("Full", OjTabla.getString("full"));
		tabla.put("Escalera", OjTabla.getString("escalera"));
		tabla.put("Uno", OjTabla.getString("uno"));
		tabla.put("Dos", OjTabla.getString("dos"));
		tabla.put("Tres", OjTabla.getString("tres"));
		tabla.put("Cuatro", OjTabla.getString("cuatro"));
		tabla.put("Cinco", OjTabla.getString("cinco"));
		tabla.put("Seis", OjTabla.getString("seis"));

		return tabla;
    }   
    /* 
	/**
	 * Transforma un JSONArray en un ArrayList de tipo diezmil
	 * @param JSONArray
	 * @return ArrayList<diezmil>
	 
	
    public static ArrayList<Diezmil> toArrayListD(JSONArray leido){
    	ArrayList<Diezmil> arregloDiez = new ArrayList<Diezmil>();
    	int i = 0;
		
		while(i < leido.length()) {
				try {
					JSONObject Oj = leido.getJSONObject(i);
					Diezmil juego = toGenerala(Oj);
					arregloDiez.add(juego);
					i++;
				} catch (JSONException e) {
					e.printStackTrace();
				}

		}
		return arregloDiez;
    }
    
    /**
     * Transforma un JSONObject a un Objeto Diezmil
     * @param JSONObject
     * @return Diezmil
     * @throws JSONException 
     
    public static Diezmil toDiezmil(JSONObject Oj) throws JSONException {
    	int duracionDePartida = Oj.getInt("duracionDePartida");
		int tiradas = Oj.getInt("tiradas");
		ArrayList<Jugador> Aj = toArrayListJd(Oj);
		ArrayList<Dado> Ad = toArrayListD(Oj);
		int turno = Oj.getInt("turno");
		Diezmil diez = new Diezmil(duracionDePartida, tiradas, Aj, Ad, turno);
		return diez;
    }
    
    public static ArrayList<Jugador> toArrayListJd(JSONObject Oj) throws JSONException{
		ArrayList<Jugador> Aj = new ArrayList<Jugador>();
		for( int k=0; k < Oj.getJSONArray("jugadores").length(); k++){
			PuntajeGenerala puntos = new PuntajeDiezmil(toHashMapPg(Oj, k));		
			Aj.add(new Jugador(Oj.getJSONArray("jugadores").getJSONObject(k).getString("nombre"), puntos));
		}
		return Aj;
    }

    public static HashMap<String, String> toHashMapPd(JSONObject Oj, int pos) throws JSONException{
		HashMap<String, String> tabla = new HashMap<String, String>();
		
		JSONObject OjTabla =  Oj.getJSONArray("jugadores").getJSONObject(pos).getJSONObject("puntosDiez");
		
		tabla.put("GeneralaDoble", OjTabla.getString("generalaDoble"));
		tabla.put("Generala", OjTabla.getString("generala"));
		tabla.put("Poker", OjTabla.getString("poker"));
		tabla.put("Full", OjTabla.getString("full"));
		tabla.put("Escalera", OjTabla.getString("escalera"));
		tabla.put("Uno", OjTabla.getString("uno"));
		tabla.put("Dos", OjTabla.getString("dos"));
		tabla.put("Tres", OjTabla.getString("tres"));
		tabla.put("Cuatro", OjTabla.getString("cuatro"));
		tabla.put("Cinco", OjTabla.getString("cinco"));
		tabla.put("Seis", OjTabla.getString("seis"));

		return tabla;
    }
    */
    /**
     * Devuelve un ArrayList de dados desde JSON
     * @param Oj
     * @return ArrayList Dados
     * @throws JSONException
     */
    public static ArrayList<Dado> toArrayListD(JSONObject Oj) throws JSONException{
		ArrayList<Dado> Ad = new ArrayList<Dado>();
    	for(int j=0; j < Oj.getJSONArray("dados").length(); j++) {
			Ad.add(new Dado(Oj.getJSONArray("dados").getJSONObject(j).getInt("numero")));
		}
		return Ad;
    }
    

}
