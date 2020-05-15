package clases;

import java.util.HashMap;

public class PuntajeGenerala {
    private HashMap<String,String> tabla;

    public PuntajeGenerala() {
        tabla = new HashMap<String, String>();
    }

    public void asignarGeneralaDoble(String p)
    {
        tabla.put("generala_doble",p);
    }

    public void asignarGenerala(String p)
    {
        tabla.put("generala",p);
    }

    public void asignarPoker(String p)
    {
        tabla.put("poker",p);
    }

    public void asignarFull(String p)
    {
        tabla.put("full",p);
    }

    public void asignarEscalera(String p)
    {
        tabla.put("escalera",p);
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

    public String getGeneralaDoble()
    {
        return tabla.get("generala_doble");
    }

    public String getGenerala()
    {
        return tabla.get("generala");
    }

    public String getPoker()
    {
        return tabla.get("poker");
    }

    public String getFull()
    {
        return tabla.get("full");
    }

    public String getEscalera()
    {
        return tabla.get("escalera");
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
