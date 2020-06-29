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
	public static JSONObject diezMilToJSON(Juego juego) {
		JSONObject Jo = new JSONObject(juego);
		return Jo;
	}

	/**
	 * transforma generala a JSONObject
	 * @param ge generala a convertir
	 * @return objecto json de generala completa
	 */
	public static JSONObject generalaToJSON(Generala ge)
	{
		JSONObject generala = new JSONObject(ge);
		JSONArray jugadores=generala.getJSONArray("jugadores");
		JSONObject jugador;
		JSONObject puntos;
		for (int i=0;i<ge.getJugadores().size();i++)
		{
			jugador=jugadores.getJSONObject(i);
			puntos=new JSONObject(ge.getJugadores().get(i).getPuntosGen().listar());
			jugador.put("puntosGen",puntos);
			jugadores.put(i,jugador);
		}
		generala.put("jugadores",jugadores);
		return generala;
	}

	public  static  JSONArray arrayListToJSON(ArrayList <Generala> list)
	{
		JSONArray json=new JSONArray();
		for (Generala juego: list)
		{
			json.put(generalaToJSON(juego));
		}
		return json;
	}

	/**
	 * Transforma un JSONArray en un ArrayList de tipo generala
	 * @param leido array cargado
	 * @return ArrayList<Generala>
	 */
    public static ArrayList<Generala> toArrayListG(JSONArray leido){
    	ArrayList<Generala> arregloGen = new ArrayList<>();
    	int i = 0;

		while(i < leido.length()) {      //todo ver este try..
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
     * @param Oj JSONObject de generala
     * @return Generala
     * @throws JSONException 
     */
    public static Generala toGenerala(JSONObject Oj) {
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
    public static ArrayList<Jugador> toArrayListJg(JSONObject Oj) {
		ArrayList<Jugador> Aj = new ArrayList<Jugador>();
		for( int k=0; k < Oj.getJSONArray("jugadores").length(); k++){
			PuntajeGenerala puntos = new PuntajeGenerala(toHashMapPg(Oj, k));		
			Aj.add(new Jugador(Oj.getJSONArray("jugadores").getJSONObject(k).getString("nombre"), puntos));
		}
		return Aj;
    }
    
    /**
     * Devuelve un HashMap de los puntos de la generala desde JSON
     * @param Oj  JSONObject de PuntajeGenerala
     * @param pos
     * @return HashMap tabla
     * @throws JSONException
     */
    public static HashMap<String, String> toHashMapPg(JSONObject Oj, int pos){
		HashMap<String, String> tabla = new HashMap<String, String>();
		JSONObject OjTabla =  Oj.getJSONArray("jugadores").getJSONObject(pos).getJSONObject("puntosGen");
		tabla.put("Generala doble", OjTabla.getString("Generala doble"));
		tabla.put("Generala", OjTabla.getString("Generala"));
		tabla.put("Poker", OjTabla.getString("Poker"));
		tabla.put("Full", OjTabla.getString("Full"));
		tabla.put("Escalera", OjTabla.getString("Escalera"));
		tabla.put("1", OjTabla.getString("1"));
		tabla.put("2", OjTabla.getString("2"));
		tabla.put("3", OjTabla.getString("3"));
		tabla.put("4", OjTabla.getString("4"));
		tabla.put("5", OjTabla.getString("5"));
		tabla.put("6", OjTabla.getString("6"));
		return tabla;
    }

	/**
	 * Devuelve un ArrayList de dados desde JSON
	 * @param Oj
	 * @return ArrayList Dados
	 * @throws JSONException
	 */
	public static ArrayList<Dado> toArrayListD(JSONObject Oj){
		ArrayList<Dado> Ad = new ArrayList<Dado>();
		for(int j=0; j < Oj.getJSONArray("dados").length(); j++) {
			Ad.add(new Dado(Oj.getJSONArray("dados").getJSONObject(j).getInt("numero")));
		}
		return Ad;
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
*/


    

}
