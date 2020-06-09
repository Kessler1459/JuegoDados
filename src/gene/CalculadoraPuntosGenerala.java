package gene;

import clases.Dado;

import java.util.ArrayList;
import java.util.Collections;

/**
 * tiene los metodos estaticos para calcular los puntos de cada categoria
 */
public class CalculadoraPuntosGenerala {

    /**
     * calcula  los puntos que vale la categoria pasada por String
     * @param generala para tener los dados y el numero de tiradas
     * @param categoria String con el nombre exacto de la categoria
     * @return los puntos correspondientes o un 0 de no ocurrir
     */
    public static int calcularCategoria(Generala generala,String categoria)
    {
        int a=0;
        int b=0;
        ArrayList<Dado> dados=generala.getDados();
        ArrayList<Integer> frecuencias=calcularFrecuencia(dados);
        switch (categoria)
        {
            case "Generala doble":
                a=calcularGeneralaDoble(frecuencias,generala.getJugadores().get(generala.getTurno()).getPuntosGen());
                break;
            case "Generala":
                a=calcularGenerala(frecuencias);
                break;
            case "Poker":
                a=calcularPoker(frecuencias);
                break;
            case "Full":
                a=calcularFull(frecuencias);
                break;
            case "Escalera":
                a=calcularEscalera(dados);
                break;
            case "1":
                b=calcularNumero(dados,1);
                break;
            case "2":
                b=calcularNumero(dados,2);
                break;
            case "3":
                b=calcularNumero(dados,3);
                break;
            case "4":
                b=calcularNumero(dados,4);
                break;
            case "5":
                b=calcularNumero(dados,5);
                break;
            case "6":
                b=calcularNumero(dados,6);
                break;
        }
        if (a>0 && generala.getTiradas()==0)          //si sale servida se suman 5 puntos mas
        {
            a+=5;
        }
        return a+b;
    }

    private static int calcularGeneralaDoble(ArrayList<Integer> frec,PuntajeGenerala puntos)
    {
        int a=0;
        if (calcularGenerala(frec)>0 && puntos.getGenerala()!=null)
        {
            a=100;
        }
        return a;
    }

    private static int calcularGenerala(ArrayList<Integer> frec)
    {
        int a=0;
        if (Collections.max(frec)==5)
        {
            a=60;
        }
        return a;
    }

    private static int calcularPoker(ArrayList<Integer> frec)
    {
        int a=0;
        if (Collections.max(frec)==4)
        {
            a=40;
        }
        return a;
    }

    private static int calcularFull(ArrayList<Integer> frec)
    {
        int a=0;
        Collections.sort(frec);
        if (frec.get(5)==3 && frec.get(4)==2)
        {
            a=30;
        }
        return a;
    }

    /**
     * ordena los dados de menor a mayor y los compara con las dos posibilidades de escalera que tiene el juego
     */
    private static int calcularEscalera(ArrayList<Dado> dados)
    {
        int a=0;
        ArrayList<Dado> aux=new ArrayList<>(dados);
        ArrayList<Dado> escalera=new ArrayList<>();
        ArrayList<Dado> escalera2=new ArrayList<>();
        Collections.sort(aux);
        for (int i=1;i<6;i++)
        {
            escalera.add(new Dado(i));
            escalera2.add(new Dado(i+1));
        }
        if (escalera.equals(aux) || escalera2.equals(aux))
        {
            a=20;
        }
        return a;
    }

    /**
     * llena un arraylist de enteros con las frecuencias de los 6 numeros posibles de tod0 el arreglo de dados
     * @param dados el arreglo de dados utilizado
     * @return el arreglo cargado, indice [0] la cantidad de dados 1, indice [1] la cantidad de dados 2, etc.
     */
    private static ArrayList<Integer> calcularFrecuencia(ArrayList<Dado> dados)
    {
        ArrayList<Dado> aux=dados;
        ArrayList<Integer> num=new ArrayList<Integer>();
        for (int i=1;i<7;i++)
        {
            num.add(Collections.frequency(aux,new Dado(i)));
        }
        return num;
    }

    /**
     * multiplica el valor pasado por parametro por la cantidad de veces que sea encontrardo el mismo dentro de los dados
     * @param n el numero que buscamos
     */
    private static int calcularNumero(ArrayList<Dado> dados,int n)
    {
        int a=0;
        for (int i=0;i<5;i++)
        {
            if (dados.get(i).getNumero()==n)
            {
                a+=n;
            }
        }
        return a;
    }
}
