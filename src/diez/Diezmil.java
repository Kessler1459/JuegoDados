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

    /**
     * constructor vacio
     */
    public Diezmil ()
    {
        super();
        continuar = false;
        tiradas=0;
    }

    /**
     * constructor
     * @param jugadores con la lista de jugadores
     */
    public Diezmil (ArrayList<Jugador>jugadores)
    {
        super(jugadores,5);
        continuar = false;
        tiradas=0;
    }

    /**
     * constructor con todos los parametros del juego
     * @param continuar
     * @param tiradas
     * @param jugadores
     * @param dados
     * @param turno
     */
    public Diezmil(boolean continuar, int tiradas, ArrayList<Jugador> jugadores,ArrayList<Dado> dados, int turno)
    {
        super(jugadores,dados,turno);
        this.continuar=continuar;
        this.tiradas=tiradas;
    }
    /**
     * Reinicia los tiros a 0
     */
    public void reiniciarTiradas()
    {
        tiradas = 0;
    }
    /**
     * Devuelve el numero de tiros del turno
     * @return tiradas
     */
    public int getTiradas()
    {
        return tiradas;
    }
    /**
     * Aumenta el numero de tiradas
     */
    public void aumentarTiradas()
    {
        tiradas++;
    }

    /**
     * setea el continuar como v o f segun salga o no juego
     */
    public void setContinuar()
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
    /**
     * aumenta el nro de turno y reinicia los tiros del turno a 0
     */

    @Override
    public void pasarTurno() {

        super.pasarTurno();
        reiniciarTiradas();


    }
    /**
     * Tira los dados que no obtuvieron puntos
     * @return un nuevo arreglo de dados solo con los dados del nuevo tiro
     */

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
    /**
     * Indica si en el tiro inicial todos los dados suman puntos
     * @return 1 si todos sirven o 0 si no sirven todos
     */
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
    /**
     * Retorna el valor de la variable continuar
     * @return true o false
     */
    public boolean getContinuar ()
    {
        return continuar;
    }
    /**
     * Indica si sale o no escalera cuando se tiran los 5 dados
     * @return boolean true si hay escalera, false si no hay
     */

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
    /**
     * Indica si al lanzar los 5 dados todos son iguales (diezmil)
     * @return boolean true si hay diez mil,false si no hay
     */
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
    /**
     * Copia los nros de cada dado y los devuelve en un arreglo de enteros
     * @param dados el arreglo a copiar
     * @return ArrayList<Integer>copia donde se copia el arreglo de dados
     */

    public ArrayList<Integer> copiarDados(ArrayList<Dado>dados) // copia y devuelve los dados en un arreglo de enteros
    {
        ArrayList<Integer>copia = new ArrayList<>();
        for (Dado d: dados)
        {
            copia.add(d.getNumero());
        }
        return copia;
    }

    /**
     * Devuelve la frecuencia de cada dado en un arreglo de enteros,donde la posicion es el numero del dado
     * @param dados El arreglo de dados sobre el que se obtienen las frecuencias
     * @return ArrayList<Integer>frecuencia con las frecuencias de cada numero
     */
    public ArrayList<Integer> calcularFrecuencia(ArrayList<Dado> dados)
    {
        ArrayList<Dado> aux=dados;
        ArrayList<Integer> frecuencia=new ArrayList<Integer>();
        for (int i=1;i<7;i++)
        {
            frecuencia.add(Collections.frequency(aux,new Dado(i)));
        }
        return frecuencia;
    }


    /**
     * Devuelve un array de enteros indicando 1 para el dado que suma puntos y 0 para el que no
     * El numero del dado es la posicion del array
     * @return ArrayList<Integer>sinPuntos con los dados que no suman
     */
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


    /**
     * Busca y retorna la posicion en el arreglo del ultimo dado que
     * se repite mas de 3 veces y q no es ni 1 ni 5
     * @return pos la posicion del dado que se puede volver a tirar
     */

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

    /**
     * sobreescritura del toString
     * @return String con la representacion heredada mas la particular
     */
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
