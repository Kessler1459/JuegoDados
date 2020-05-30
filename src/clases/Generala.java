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

    @Override
    public void tirarDados() {
        super.tirarDados();
        tiradas++;
    }

    @Override
    public void tirarDados(int a, int b, int c, int d, int e) {
        super.tirarDados(a, b, c, d, e);
        tiradas++;
    }
}
