package clases;

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
        super(jugadores);
        tiradas=0;
    }


    /**
     * aumenta el numero de tiradas, si fue la tercera se reinicia a 0 y se pasa de turno al siguiente jugador
     */
    private void aumentarTirada()
    {
        tiradas++;
        if (tiradas>=3)
        {
            tiradas=0;
            pasarTurno();
        }
    }


    /**
     * {@inheritDoc}
     * y aumenta tiradas
     */
    @Override
    public void tirarDados() {
        super.tirarDados();
        aumentarTirada();
    }


    /**
     * {@inheritDoc}
     * y aumenta tiradas
     */
    @Override
    public void tirarDados(int a, int b, int c, int d, int e) {
        super.tirarDados(a, b, c, d, e);
        aumentarTirada();
    }

    public int getTiradas() {
        return tiradas;
    }
}
