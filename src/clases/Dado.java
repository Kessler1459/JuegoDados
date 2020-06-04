package clases;


import javax.swing.*;
import java.util.Random;


public class Dado implements Comparable<Dado>{
    private int numero;

    /**
     * imagen para setear al JlabelDado
     */
    private ImageIcon imagen;

    /**
     * inicializa el dado
     * @param num numero con el que iniciara el dado
     */
    public Dado(int num) {
        setNumero(num);
    }

    /**
     * inicializa el dado en 0
     */
    public Dado() {
        numero=0;
    }



    /**
     * cambia el numero del dado y le cambia a su imagen correspondiente
     * @param num entero para asignar
     */
    private void setNumero(int num)
    {
        numero=num;
        asignarImagen();
    }


    /**
     * asigna la imagen del dado correspondiente
     */
    private void asignarImagen()
    {
        imagen=new ImageIcon("Imagenes/"+getNumero()+".png");
    }


    /**
     * tira el dado
     * @return devuelve el Dado
     */
    public Dado tirarDado()
    {
        Random ran=new Random();
        setNumero(1+ran.nextInt(6));
        return this;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Dado{" +
                "numero=" + numero +
                '}';
    }

    @Override
    public int compareTo(Dado o) {
        if (this.getNumero()<o.getNumero())
        {
            return -1;
        }
        else
        {
            if (this.getNumero()==o.getNumero())
            {
                return 0;
            }
            else return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        Dado dado = (Dado) o;
        return numero == dado.numero;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
