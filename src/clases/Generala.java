package clases;

import java.util.ArrayList;

public class Generala extends Juego {
    private int tiradas;

    public Generala() {
        super();
        tiradas=0;
    }
    public Generala(ArrayList<Jugador> jugadores) {
        super(jugadores);
        tiradas=0;
    }
}
