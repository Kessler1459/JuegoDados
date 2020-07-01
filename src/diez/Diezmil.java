package diez;


import clases.Dado;
import clases.Juego;
import clases.Jugador;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Diezmil  extends Juego {
    private boolean continuar;
    private int tiradas;

    public Diezmil ()
    {
        super();
        continuar = false;
        tiradas=0;
    }
    public Diezmil (ArrayList<Jugador>jugadores)
    {
        super(jugadores,5);
        continuar = false;
        tiradas=0;
    }

    public Diezmil(boolean continuar, int tiradas, ArrayList<Jugador> jugadores,ArrayList<Dado> dados, int turno)
    {
        super(jugadores,dados,turno);
        this.continuar=continuar;
        this.tiradas=tiradas;
    }

    public void reiniciarTiradas()
    {
        tiradas = 0;
    }

    public int getTiradas()
    {
        return tiradas;
    }

    public void aumentarTiradas()
    {
        tiradas++;
    }


    public void setContinuar() // define si el jugador puede continuar jugando ya sea porque salio 1 o 5 o cualquier otro numero pero por triplicado
    {
        continuar = false;
        ArrayList<Dado>dados=this.getDados();
        ArrayList<Integer>copia = new ArrayList<>();
        ArrayList<Integer>frecuencia = new ArrayList<>();

        copia = copiarDados (dados);
        if (Collections.frequency(copia,1)>0 || Collections.frequency(copia, 5)>0)
        {
            continuar = true;
        }
        else
        {
            frecuencia = calcularFrecuencia (dados);
            Collections.sort(frecuencia,Collections.reverseOrder());
            if (frecuencia.get(0)>=3)
            {
                continuar = true;
            }
            else
            {
                if(escalera() == true)
                {
                    continuar = true;
                }
            }
        }
    }

    @Override
    public void tirarDados()
    {
        super.tirarDados();
        setContinuar();
        if(getContinuar()==true)
            aumentarTiradas();

    }


    @Override
    public void pasarTurno() {

        super.pasarTurno();
        reiniciarTiradas();


    }

    public ArrayList<Dado> tirarDadosSinPuntos()
    {
        ArrayList<Dado>nuevoTiro=new ArrayList<>();
        ArrayList<Dado>dados=getDados();
        ArrayList<Integer>SinPuntos = new ArrayList<>();

        SinPuntos =CalcularDadosSinPuntos();
        if(SinPuntos.size()>0 && SirvenTodos()!=1)
        {
            for (int i=0;i<5;i++)
            {
                if (SinPuntos.get(i)==0)
                {
                    dados.set(i,dados.get(i).tirarDado());
                    nuevoTiro.add(dados.get(i)); /// crea un nuevo arreglo de Dados con los dados del 2do tiro.
                }
            }

        }


        return nuevoTiro;
    }
    public void setContinuar (ArrayList<Dado>d)
    {
        int i=0;
        continuar=false;
        ArrayList<Integer>copia = copiarDados(d);
        for (i=0;i<copia.size();i++)
        {
            if(Collections.frequency(copia,copia.get(i))==3)
            {
                continuar=true;
            }
            else
            {
                if (Collections.frequency(copia,1)>0 || Collections.frequency(copia,5)>0 )
                {
                    continuar=true;
                }
            }

        }
    }
    public int SirvenTodos()
    {
        int rta=1;
        ArrayList<Integer>sirvenTodos= CalcularDadosSinPuntos();
        int i=0;
        if (sirvenTodos.size()>0)
        {
            while (i<sirvenTodos.size()-1 && sirvenTodos.get(i)!=0)
            {
                i++;
            }
            if(sirvenTodos.get(i)==0)
            {
                rta=0;
            }
        }

        return rta;
    }

    public boolean getContinuar ()
    {
        return continuar;
    }

    public boolean escalera () //otro juego para setear el continuar
    {
        boolean escalera = false;
        ArrayList<Integer>esca1 = new ArrayList<>();//1,2,3,4,5
        ArrayList<Integer>esca2 = new ArrayList<>();//2,3,4,5,6
        for (int i = 1;i<6;i++ )
        {
            esca1.add(i);
            esca2.add(i+1);
        }
        ArrayList<Integer>tirada=copiarDados(getDados());
        Collections.sort(tirada);

        if (tirada.equals(esca1) || tirada.equals(esca2))
        {
            escalera = true;

        }

        return escalera;
    }

    public boolean Diez_mil ()
    {
        boolean rta= false;
        ArrayList<Dado>dados=getDados();
        ArrayList<Integer>frecuencia = new ArrayList<>();
        frecuencia=calcularFrecuencia(dados);
        Collections.sort(frecuencia, Collections.reverseOrder());
        if (frecuencia.get(1) ==5)
        {
            rta = true;
        }
        return rta;
    }

    public ArrayList<Integer> copiarDados(ArrayList<Dado>dados) // copia y devuelve los dados en un arreglo de enteros
    {
        ArrayList<Integer>copia = new ArrayList<>();
        for (Dado d: dados)
        {
            copia.add(d.getNumero());
        }
        return copia;
    }


    public ArrayList<Integer> calcularFrecuencia(ArrayList<Dado> dados) /// devuelve la frecuencia de cada dado en un nuevo arreglo
    {
        ArrayList<Dado> aux=dados;
        ArrayList<Integer> frecuencia=new ArrayList<Integer>();
        for (int i=1;i<7;i++)
        {
            frecuencia.add(Collections.frequency(aux,new Dado(i)));
        }
        return frecuencia;
    }

    //* DEVUELVE UN ARRAY CON LAS POSICIONES DE LOS DADOS QUE NO SUMAN PUNTOS
    ///INDICANDO 1 SI SUMA O 0 SI NO SUMA///

    public ArrayList <Integer>CalcularDadosSinPuntos ()
    {
        ArrayList<Integer> sinPuntos = new ArrayList<>();
        ArrayList<Dado>dados=getDados();
        ArrayList<Integer>copia =copiarDados(dados);
        int i = 0;
        if (escalera()!=true && Diez_mil()!=true)
        {
            for (i=0;i<copia.size();i++)
            {
                if (copia.get(i)!=5 && copia.get(i)!= 1 && Collections.frequency(copia,copia.get(i))<3)
                {
                    sinPuntos.add(0);
                }
                else
                {
                    sinPuntos.add(1);
                }
            }
            int pos = devolverUltimaPosicionRepetido();

            if (pos!= -1)
            {
                sinPuntos.set(pos,0);
            }
        }

        return sinPuntos;
    }

    /// FUNCION PARA SETEAR LOS DADOS QUE SE PUEDEN VOLVER A TIRAR
    /// CUANDO SALE MAS DE 3 VECES UN NUMERO QUE NO SEA 1 0 5 SE DEVUELVE LA
    /// POSICION DE SU ULTIMA APARICION QUE ES EL DADO QUE NO SIRVE
    public int devolverUltimaPosicionRepetido()
    {
        int pos=-1;
        int i =0;
        ArrayList<Dado>d = getDados();
        ArrayList<Integer>copia = copiarDados(d);
        for(i=0;i<copia.size();i++)
        {
            if(Collections.frequency(copia,copia.get(i))>3 && copia.get(i)!=1 && copia.get(i)!=5)
            {
                pos =i;

            }
        }
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for (Jugador ju: getJugadores())
        {
            builder.append(ju.getNombre()+": "+ju.getPuntosDiez().getPuntajeTotal()+", ");
        }
        builder.append(" tiradas=" + tiradas);
        return builder.toString();
    }
}
