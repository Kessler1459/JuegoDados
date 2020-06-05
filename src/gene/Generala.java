package gene;

import clases.Juego;
import clases.Jugador;

import java.util.ArrayList;


public class Generala extends Juego {
    /**
     * contiene el numero de las tiradas dentro del turno del mismo jugador (3 tiradas por turno maximo)
     */
    private int tiradas;


    public Generala() {
        super();
        tiradas=0;
    }
    public Generala(ArrayList<Jugador> jugadores) {
        super(jugadores,5);
        tiradas=0;
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
        tiradas=0;
    }

    public int getTiradas() {
        return tiradas;
    }
}
