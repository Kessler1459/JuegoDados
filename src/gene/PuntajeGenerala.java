package gene;

import java.util.HashMap;

/**
 * setters y getters de todas las categorias de la generala
 */
public class PuntajeGenerala {                   //parece que no necesite tantos metodos D:
    private HashMap<String,String> tabla;

    public PuntajeGenerala() {
        tabla = new HashMap<String, String>();
    }

    public void asignarCategoria(String categoria, String resultado)
    {
        tabla.put(categoria,resultado);
    }

    public void asignarGeneralaDoble(String p)
    {
        tabla.put("Generala doble",p);
    }

    public void asignarGenerala(String p)
    {
        tabla.put("Generala",p);
    }

    public void asignarPoker(String p)
    {
        tabla.put("Poker",p);
    }

    public void asignarFull(String p)
    {
        tabla.put("Full",p);
    }

    public void asignarEscalera(String p)
    {
        tabla.put("Escalera",p);
    }

    public void asignarUno(String p)
    {
        tabla.put("1",p);
    }

    public void asignarDos(String p)
    {
        tabla.put("2",p);
    }

    public void asignarTres(String p)
    {
        tabla.put("3",p);
    }

    public void asignarCuatro(String p)
    {
        tabla.put("4",p);
    }

    public void asignarCinco(String p)
    {
        tabla.put("5",p);
    }

    public void asignarSeis(String p)
    {
        tabla.put("6",p);
    }

    public String getCategoria(String str)
    {
        return tabla.get(str);
    }

    public String getGeneralaDoble()
    {
        return tabla.get("Generala doble");
    }

    public String getGenerala()
    {
        return tabla.get("Generala");
    }

    public String getPoker()
    {
        return tabla.get("Poker");
    }

    public String getFull()
    {
        return tabla.get("Full");
    }

    public String getEscalera()
    {
        return tabla.get("Escalera");
    }

    public String getUno()
    {
        return tabla.get("1");
    }

    public String getDos()
    {
        return tabla.get("2");
    }

    public String getTres()
    {
        return tabla.get("3");
    }

    public String getCuatro()
    {
        return tabla.get("4");
    }

    public String getCinco()
    {
        return tabla.get("5");
    }

    public String getSeis()
    {
        return tabla.get("6");
    }
}
