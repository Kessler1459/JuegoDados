package clases;

public class Jugador {
    private String nombre;
    public PuntajeGenerala puntosGen;
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
}
