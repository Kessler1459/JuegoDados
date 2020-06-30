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
        puntosDiez = new PuntajeDiezmil();
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        puntosGen=new PuntajeGenerala();
        puntosDiez = new PuntajeDiezmil();
    }

    public void setPuntosDiez(PuntajeDiezmil puntos) {

        puntosDiez.setPuntajeTotal(puntos.getPuntajeTotal());
        puntosDiez.setPuntajeTiro(puntos.getPuntajeTiro());
    }

    public PuntajeDiezmil getPuntosDiez()
    {
        return puntosDiez;
    }

    public Jugador(String nombre, PuntajeGenerala puntosGen) {
        this.nombre = nombre;
        this.puntosGen=puntosGen;
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
        return nombre;
    }

}
