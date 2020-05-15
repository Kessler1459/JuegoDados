package clases;

import java.util.ArrayList;

public abstract class Juego {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Dado> dados;


    public Juego() {
        dados=new ArrayList<Dado>();
        jugadores=new ArrayList<Jugador>();
        inicializarDados();
    }

    public Juego(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        dados=new ArrayList<Dado>();
        inicializarDados();
    }

    private void inicializarDados()
    {
        for (int i=0;i<6;i++)
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

    public void tirarDados(int a,int b, int c, int d, int e, int f)           //TIRA INDIVIDUALMENTE SEGUN POSICION, SOLO SI SON DISTINTOS DE 0(llena con 0 los que no quieras tirar)
    {
        int arr[]=new int[]{a,b,c,d,e,f};
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

    @Override
    public String toString() {
        return "jugadores=" + jugadores + ",\ndados=" + dados;
    }
}
