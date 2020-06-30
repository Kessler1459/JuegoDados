package persistencia;

import java.util.*;

import diez.Diezmil;
import diez.PuntajeDiezmil;
import org.json.*;
import clases.*;
import gene.*;

/**
 * metodos necesarios para todas las conversiones entre las clases DiezMil y Generala, y sus correspondientes objetos
 */
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

    /**
     * carga el JSONArray con un arraylist de partidas de generala
     * @param list lista de partidas para cargar al json
     * @return JSONArray cargado
     */
	public static JSONArray generalaArrayListToJSON(ArrayList <Generala> list)
	{
		JSONArray json=new JSONArray();
		for (Generala juego: list)
		{
			json.put(generalaToJSON(juego));
		}
		return json;
	}

    /**
     * carga el JSONArray con un arraylist de partidas de diezMil
     * @param list lista de partidas para cargar al json
     * @return JSONArray cargado
     */
    public static JSONArray diezMilArrayListToJSON(ArrayList <Diezmil> list)
    {
        JSONArray json=new JSONArray();
        for (Diezmil juego: list)
        {
            json.put(diezMilToJSON(juego));
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
		while(i < leido.length()) {
			JSONObject Oj = leido.getJSONObject(i);
			Generala juego = toGenerala(Oj);
			arregloGen.add(juego);
			i++;
		}
		return arregloGen;
    }
    
    /**
     * Transforma un JSONObject a un Objeto Generala
     * @param Oj JSONObject de generala
     * @return Generala
     */
    public static Generala toGenerala(JSONObject Oj) {
    	int duracionDePartida = Oj.getInt("duracionDePartida");
		int tiradas = Oj.getInt("tiradas");
		ArrayList<Jugador> Aj = toArrayListJGenerala(Oj);
		ArrayList<Dado> Ad = toArrayListD(Oj);
		int turno = Oj.getInt("turno");
		Generala gen = new Generala(duracionDePartida, tiradas, Aj, Ad, turno);
		return gen;
    }
    
    /**
     * Devuelve el ArrayList de jugadores desde el JSON
     * @param Oj
     * @return ArrayList Jugadores
     */
    public static ArrayList<Jugador> toArrayListJGenerala(JSONObject Oj) {
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
	 */
	public static ArrayList<Dado> toArrayListD(JSONObject Oj){
		ArrayList<Dado> Ad = new ArrayList<Dado>();
		for(int j=0; j < Oj.getJSONArray("dados").length(); j++) {
			Ad.add(new Dado(Oj.getJSONArray("dados").getJSONObject(j).getInt("numero")));
		}
		return Ad;
	}


	/**
	 * Transforma un JSONArray en un ArrayList de tipo diezmil
	 * @param leido JSONArray a convertir
	 * @return ArrayList<diezmil> cargado con tod0 el json
        */
	
    public static ArrayList<Diezmil> toArrayListDiezMil(JSONArray leido){
    	ArrayList<Diezmil> arregloDiez = new ArrayList<Diezmil>();
    	int i = 0;
		while(i < leido.length()) {
			JSONObject Oj = leido.getJSONObject(i);
			Diezmil juego = toDiezmil(Oj);
			arregloDiez.add(juego);
			i++;
		}
		return arregloDiez;
    }


    /**
     * Transforma un JSONObject a un Objeto Diezmil
     * @param Oj objeto para convertir
     * @return Diezmil objeto DiezMil cargado
     */
    public static Diezmil toDiezmil(JSONObject Oj) {
    	boolean continuar = Oj.getBoolean("continuar");
		int tiradas = Oj.getInt("tiradas");
		ArrayList<Jugador> Aj = toArrayListJugDiezMil(Oj);
		ArrayList<Dado> Ad = toArrayListD(Oj);
		int turno = Oj.getInt("turno");
		Diezmil diez = new Diezmil(continuar,tiradas,Aj,Ad,turno);
		return diez;
    }

	/**
	 * llena un ArrayList de tipo Jugador con todos los leidos en el JSONObject de una partida de DiezMil
	 * @param oj objecto a convertir
	 * @return ArrayList de jugadores cargado
	 */
	public static ArrayList<Jugador> toArrayListJugDiezMil(JSONObject oj){
		ArrayList<Jugador> arr = new ArrayList<Jugador>();
		JSONArray jugadores=oj.getJSONArray("jugadores");
		Jugador jugador;
		for( int k=0; k < jugadores.length(); k++)
		{
		    JSONObject ju=jugadores.getJSONObject(k);
		    jugador=new Jugador(ju.getString("nombre"));
			JSONObject punt=ju.getJSONObject("puntosDiez");
		    PuntajeDiezmil puntos = new PuntajeDiezmil();
			puntos.setPuntajeTiro((int)punt.get("puntajeTiro"));
            puntos.setPuntajeTotal((int) punt.get("puntajeTotal"));
			jugador.setPuntosDiez(puntos);
			arr.add(k,jugador);
		}
		return arr;
    }
}
