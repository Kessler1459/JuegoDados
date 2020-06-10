package persistencia;


import org.json.*;

import clases.Juego;

public class JHijo {

	
	private static JSONArray Aj= new JSONArray();
	private static int contador = 0;
	
	/**
	@return arregloJSON
	 */
	public static JSONArray getArray() {
		return Aj;
	}
	
	private static void setContador(int cont) {
		contador = cont;
	}
	
	/***
	 * crea un objeto JSON a partir de un juego
	 * @param juego a guardar
	 * @return Objeto JSON creado
	 */
	public static JSONObject crear_OJ(Juego game) {
		JSONObject Oj = new JSONObject();
		try {
			Oj.put("Index", contador);
			Oj.put("Jugadores", game.getJugadores());
			setContador(contador + 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Oj;
	}
	
	/**
	 * agrega un objeto al arreglo
	 * @param Objeto JSON
	 
	*/
	public static void agregar(JSONObject Oj) {
		Aj.put(Oj);
	}
}
