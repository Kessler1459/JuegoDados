package diez;


import Excepciones.ExcepcionFinalDePartida;

public class PuntajeDiezmil {

    private  int puntajeTotal=0;
    private  int puntajeTiro=0;


    /**
     * Setea los puntos del jugador cuando este decide anotarse
     * @param ptos los puntos obtenidos en la jugada/s
     * @throws ExcepcionFinalDePartida si el jugador supera lo 10mil puntos
     */
    public  void setPuntajeTotal (int ptos) throws ExcepcionFinalDePartida {
        puntajeTotal+=ptos;
        if (puntajeTotal>=10000)
        {
            throw new ExcepcionFinalDePartida();
        }
    }

    /**
     * Setea el puntaje del tiro al jugador
     * @param ptos los puntos a setear (van a ser calculados con la calculadora)
     */
    public void setPuntajeTiro(int ptos)
    {
        puntajeTiro+=ptos;

    }

    /**
     * reinicia el puntaje del tiro a 0
     * si pierde los puntos o si se anota y pasa el turno
     */
    public  void reiniciarPuntaje()
    {
        puntajeTiro = 0;
    }

    /**
     * retorna los puntos que el jugador tiene anotados
     * @return puntajeTotal , la suma de puntos anotados
     */
    public  int getPuntajeTotal()
    {
        return puntajeTotal;
    }

    /**
     * retorn los puntos acumulados en los tiros del turno
     * @return  puntos obtenidos en el turno
     */
    public  int getPuntajeTiro()
    {
        return puntajeTiro;
    }
}