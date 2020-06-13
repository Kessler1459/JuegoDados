package clases;

import diez.PuntajeDiezmil;
import gene.PuntajeGenerala;

public class Jugador  {
    private String nombre;
    private PuntajeGenerala puntosGen;
    private PuntajeDiezmil puntosDiez;

    public Jugador() {
        this.nombre = "";
        puntosGen=new PuntajeGenerala();
        //inicializar puntajeDiezmil aca
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        puntosGen=new PuntajeGenerala();
        //inicializar puntajeDiezmil aca
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PuntajeGenerala getPuntosGen() {
        return puntosGen;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    //todo getter JSONObject
}
