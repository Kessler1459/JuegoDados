package Excepciones;

/**
 * se lanzara cuando se cumpla la correspondiente condicion de victoria de cada juego
 */
public class ExcepcionFinalDePartida extends RuntimeException { //runtime porque sino no podia hacer el throws en Generala.pasarTurno() por su herencia D:
    public  ExcepcionFinalDePartida() {
        super();
    }

}
