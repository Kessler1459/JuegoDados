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
     * aumenta el numero de tiradas, si fue la tercera se reinicia a 0 y se pasa de turno al siguiente jugador
     */
    public void aumentarTirada()
    {
        tiradas++;
        if (tiradas>=3)
        {
            tiradas=0;
            pasarTurno();
        }
    }


    public int getTiradas() {
        return tiradas;
    }
}
