package clases;

import java.util.ArrayList;

public abstract class Juego {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Dado> dados;
    private int turno;


    public Juego() {
        dados=new ArrayList<Dado>();
        jugadores=new ArrayList<>();
        inicializarDados();
        turno=1;
    }

    public Juego(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        dados=new ArrayList<Dado>();
        inicializarDados();
        turno=1;
    }


    /**
     * instancia el arreglo de 5 dados que inician en 0
     */
    private void inicializarDados()
    {
        for (int i=0;i<5;i++)
        {
            dados.add(new Dado());
        }
    }

    /**
     * tira todos los dados del arraylist
     */
    public void tirarDados()
    {
        for (Dado dado:dados)
        {
            dado.tirarDado();
        }
    }


    /**
     * tira dados individualmente, segun su posicion, usando ceros y unos
     */
    public void tirarDados(int a,int b, int c, int d, int e)
    {
        int arr[]=new int[]{a,b,c,d,e};
        for (int i=0;i<6;i++)
        {
            if (arr[i]!=0)
            {
                dados.set(i,dados.get(i).tirarDado());
            }
        }
    }


    /**
     * aumenta el valor del turno hacia el siguiente jugador, de ser el ultimo se vuelve a 0
     */
    public void pasarTurno()
    {
        if (turno!=jugadores.size()-1)
        {
            turno++;
        }
        else turno=0;
    }


    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Dado> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Dado> dados) {
        dados = dados;
    }


    public int getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return "jugadores=" + jugadores + ",\ndados=" + dados;
    }
}
