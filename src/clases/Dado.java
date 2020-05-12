package clases;

import javax.swing.*;
import java.util.Random;

public class Dado {
    private int numero;
    private ImageIcon imagen;

    public Dado(int num) {
        setNumero(num);
    }

    public Dado() {
        numero=0;
    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int num)
    {
        numero=num;
        asignarImagen();
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    private void asignarImagen()
    {
        imagen=new ImageIcon("Imagenes/"+getNumero()+".jpg");
    }

    public int tirarDado()
    {
        Random ran=new Random();
        setNumero(1+ran.nextInt(6));
        return numero;
    }
}
