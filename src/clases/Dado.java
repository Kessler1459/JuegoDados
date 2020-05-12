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
        switch (numero)
        {
            case 1:
                imagen=new ImageIcon("Imagenes/1.jpg");
                break;
            case 2:
                imagen=new ImageIcon("Imagenes/2.jpg");
                break;
            case 3:
                imagen=new ImageIcon("Imagenes/3.jpg");
                break;
            case 4:
                imagen=new ImageIcon("Imagenes/4.jpg");
                break;
            case 5:
                imagen=new ImageIcon("Imagenes/5.jpg");
                break;
            case 6:
                imagen=new ImageIcon("Imagenes/6.jpg");
                break;
        }
    }

    public int tirarDado()
    {
        Random ran=new Random();
        setNumero(1+ran.nextInt(6));
        return numero;
    }
}
