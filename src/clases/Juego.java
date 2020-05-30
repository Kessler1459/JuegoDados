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

    private void inicializarDados()
    {
        for (int i=0;i<5;i++)
        {
            dados.add(new Dado());
        }
    }

    public void tirarDados()                                //TIRA TODOS LOS DADOS DEL ARRAYLIST
    {
        for (Dado dado:dados)
        {
            dado.tirarDado();
        }
    }

    public void tirarDados(int a,int b, int c, int d, int e)           //TIRA INDIVIDUALMENTE SEGUN POSICION, SOLO SI SON DISTINTOS DE 0(llena con 0 los que no quieras tirar)
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

    public void pasarTurno()
    {
        if (turno!=jugadores.size()-1)
        {
            turno++;
        }
        else turno=0;
    }

    public int getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return "jugadores=" + jugadores + ",\ndados=" + dados;
    }
}
