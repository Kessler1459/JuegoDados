package gene;

import clases.Juego;
import clases.Jugador;

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
    
    public Jugador(String nombre, PuntajeGenerala puntosGen) {
        this.nombre = nombre;
        this.puntosGen=puntosGen;
        //inicializar puntajeDiezmil aca
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

}
