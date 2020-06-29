package gene;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * setters y getters de todas las categorias de la generala
 */
public class PuntajeGenerala {                   //parece que no necesite tantos metodos D:
    private HashMap<String,String> tabla;
    private String [] categorias={"Generala doble","Generala", "Poker", "Full", "Escalera", "1", "2", "3", "4", "5", "6"};

    public PuntajeGenerala() {
        tabla = new HashMap<String, String>();
        //inicializa todos los campos en ""
        for (String s: categorias)
        {
            tabla.put(s,"");
        }
    }
    
    public PuntajeGenerala(HashMap<String, String> tabla) {
    	this.tabla = tabla;
    }

    public void asignarCategoria(String categoria, String resultado)
    {
        tabla.put(categoria,resultado);
    }

    public void asignarGeneralaDoble(String p)
    {
        tabla.put(categorias[0],p);
    }

    public void asignarGenerala(String p)
    {
        tabla.put(categorias[1],p);
    }

    public void asignarPoker(String p)
    {
        tabla.put(categorias[2],p);
    }

    public void asignarFull(String p)
    {
        tabla.put(categorias[3],p);
    }

    public void asignarEscalera(String p)
    {
        tabla.put(categorias[4],p);
    }

    public void asignarUno(String p)
    {
        tabla.put(categorias[5],p);
    }

    public void asignarDos(String p)
    {
        tabla.put(categorias[6],p);
    }

    public void asignarTres(String p)
    {
        tabla.put(categorias[7],p);
    }

    public void asignarCuatro(String p)
    {
        tabla.put(categorias[8],p);
    }

    public void asignarCinco(String p)
    {
        tabla.put(categorias[9],p);
    }

    public void asignarSeis(String p)
    {
        tabla.put(categorias[10],p);
    }

    public String getCategoria(String str)
    {
        return tabla.get(str);
    }

    public String getGeneralaDoble()
    {
        return tabla.get(categorias[0]);
    }

    public String getGenerala()
    {
        return tabla.get(categorias[1]);
    }

    public String getPoker()
    {
        return tabla.get(categorias[2]);
    }

    public String getFull()
    {
        return tabla.get(categorias[3]);
    }

    public String getEscalera()
    {
        return tabla.get(categorias[4]);
    }

    public String getUno()
    {
        return tabla.get(categorias[5]);
    }

    public String getDos()
    {
        return tabla.get(categorias[6]);
    }

    public String getTres()
    {
        return tabla.get(categorias[7]);
    }

    public String getCuatro()
    {
        return tabla.get(categorias[8]);
    }

    public String getCinco()
    {
        return tabla.get(categorias[9]);
    }

    public String getSeis()
    {
        return tabla.get(categorias[10]);
    }

    public int sumarPuntajeTotal()
    {
        int total=0;
        Iterator iterator=tabla.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry entrada= (Map.Entry) iterator.next();
            if (!entrada.getValue().toString().equals("X"))
            {
                total+=Integer.parseInt(entrada.getValue().toString());
            }
        }
        return total;
    }

    public String listar()
    {
        StringBuilder bui=new StringBuilder();
        bui.append("{");
        for (int i=0;i<categorias.length;i++)
        {
            if (i!=0)
            {
                bui.append(",");
            }
            bui.append("\""+categorias[i]+"\":"+"\""+getCategoria(categorias[i])+"\"");
        }
        bui.append("}");
        return bui.toString();
    }
}
