package gene;

import clases.Juego;
import clases.Jugador;
import clases.Dado;

import java.util.ArrayList;


public class Generala extends Juego {
    /**
     * contiene el numero de las tiradas dentro del turno del mismo jugador (3 tiradas por turno maximo)
     */
    private int tiradas;

    /**
     * numero de turnos totales que lleva la partida
     */
    private int duracionDePartida;

    public Generala() {
        super();
        tiradas=0;
        duracionDePartida=0;
    }
    public Generala(ArrayList<Jugador> jugadores) {
        super(jugadores,5);
        duracionDePartida=0;
        tiradas=0;
    }

    public Generala(int duracionDePartida, int tiradas, ArrayList<Jugador> jugadores, ArrayList<Dado> dados, int turno ) {
    	super(jugadores, dados, turno);
    	this.duracionDePartida = duracionDePartida;
    	this.tiradas = tiradas;
    }


    /**
     * aumenta el numero de tiradas
     */
    public void aumentarTirada()
    {
        tiradas++;
    }

    /**
     * al pasar de turno reinicia el numero de tiradas tambien
     */
    @Override
    public void pasarTurno() {
        super.pasarTurno();
        duracionDePartida++;
        tiradas=0;
    }

    /**
     * comprueba si la partida llego a su maximo de turnos jugados (11 * cantidad de jugadores)
     */
    public boolean comprobarFinalDePartida()
    {
        return duracionDePartida == getJugadores().size() * 11;
    }

    public int getTiradas() {
        return tiradas;
    }

    public int getDuracionDePartida() {
        return duracionDePartida;
    }

    @Override
    public String toString() {
        return  super.toString()+" Turno= " + duracionDePartida +
                "  tiradas= " + tiradas ;
    }
}
