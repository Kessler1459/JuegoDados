package diez;


public class PuntajeDiezmil {

    private  int puntajeTotal=0;
    private  int puntajeTiro=0;



    public  void setPuntajeTotal (int ptos)
    {

        puntajeTotal+=ptos;
    }
    public void setPuntajeTiro(int ptos)
    {
        puntajeTiro+=ptos;

    }
    public void restarPuntaje ()
    {
        int ptos =getPuntajeTiro();
        puntajeTotal = puntajeTotal-ptos;
    }

    public  void reiniciarPuntaje()
    {
        puntajeTiro = 0;
    }

    public  int getPuntajeTotal()
    {
        return puntajeTotal;
    }
    public  int getPuntajeTiro()
    {
        return puntajeTiro;
    }
}