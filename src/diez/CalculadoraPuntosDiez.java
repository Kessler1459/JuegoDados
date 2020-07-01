package diez;

import clases.Dado;

import java.util.ArrayList;
import java.util.Collections;

public class CalculadoraPuntosDiez {


    public static boolean Diez_mil (ArrayList<Dado>dados)
    {
        boolean rta= false;

        ArrayList<Integer>frecuencia = new ArrayList<>();
        frecuencia=calcularFrecuencia(dados);
        Collections.sort(frecuencia,Collections.reverseOrder());
        if (frecuencia.get(1) ==5)
        {
            rta = true;
        }
        return rta;
    }
    public static ArrayList<Integer> calcularFrecuencia(ArrayList<Dado> dados) /// devuelve la frecuencia de cada dado en un nuevo arreglo
    {
        ArrayList<Integer> frecuencia=new ArrayList<Integer>();
        for (int i=1;i<7;i++)
        {
            frecuencia.add(Collections.frequency(dados,new Dado(i)));
        }
        return frecuencia;
    }



    public static int ContarCincos(ArrayList<Dado>dados)
    {

        ArrayList<Integer>d =copiarDados(dados);
        int cant = Collections.frequency(d, 5);
        return cant;
    }

    public static int SumarPtosCinco(ArrayList<Dado>dados)
    {
        int cant = ContarCincos(dados);
        int ptos = 0;
        if(cant ==3)
        {
            ptos+=500;
        }
        else
        {
            if (cant>3)
            {
                ptos +=500+(cant-3)*50;
            }
            {
                ptos+=cant*50;
            }
        }
        return ptos;
    }


    public static int ContarUnos (ArrayList<Dado>dados)
    {
        ArrayList<Integer>d = copiarDados(dados);
        int cant = Collections.frequency(d, 1);

        return cant;
    }

    public static int SumarPtosUnos(ArrayList<Dado>dados)
    {
        int puntos = 0;
        int cant = ContarUnos(dados);

        if(cant==3)
        {
            puntos += 1000;
        }
        else
        {
            if(cant>3)
            {
                puntos += 1000 +(cant-3)*100;
            }
            else
            {
                puntos += cant*100;
            }
        }
        return puntos;
    }





    public static int calcularPuntajeTotal (ArrayList<Dado>dados)
    {

        int puntaje=0;


        ArrayList<Integer>copia = copiarDados(dados);
        ArrayList<Integer>frecuencia = new ArrayList<>();

        int i=2;



        if(Diez_mil(dados) == false && escalera(dados)==false)
        {

            puntaje += SumarPtosCinco(dados)+SumarPtosUnos(dados);

            for (i=2;i<7;i++)
            {
                if(Collections.frequency(copia,i)>=3 && i!=5)
                {
                    puntaje+=i*100;
                }

            }
        }
        else
        {
            if (escalera(dados))
            {
                puntaje+=500;
            }
            else
            {
                puntaje+=10000;
            }
        }
        // PuntajeDiezmil.setPuntajeTotal(puntaje);
        //PuntajeDiezmil.setPuntajeTiro(puntaje);
        return puntaje;
    }

    public static ArrayList<Integer>copiarDados (ArrayList<Dado>d)
    {
        ArrayList<Integer>copia = new ArrayList<>();
        for(Dado da: d)
        {
            copia.add(da.getNumero());
        }

        return copia;
    }



    public static boolean escalera (ArrayList<Dado>dados)
    {
        boolean escalera = false;
        ArrayList<Integer>esca1 = new ArrayList<>();//1,2,3,4,5
        ArrayList<Integer>esca2 = new ArrayList<>();//2,3,4,5,6
        for (int i = 1;i<6;i++ )
        {
            esca1.add(i);
            esca2.add(i+1);
        }
        ArrayList<Integer>tirada=copiarDados(dados);
        Collections.sort(tirada);

        if (tirada.equals(esca1) || tirada.equals(esca2))
        {
            escalera = true;

        }

        return escalera;
    }

    public static ArrayList<Dado>tirarDadosSinPuntos(ArrayList<Dado>dados)
    {
        ArrayList<Integer>sinpuntos = CalcularDadosSinPuntos(dados);
        ArrayList<Dado>nuevoTiro = new ArrayList<>();

        for (int i=0;i<sinpuntos.size();i++)
        {
            if(sinpuntos.get(i)== 0)
            {
                dados.set(i,dados.get(i).tirarDado());
                nuevoTiro.add(dados.get(i));
            }
        }
        return nuevoTiro;
    }

    public static ArrayList <Integer>CalcularDadosSinPuntos (ArrayList<Dado>dados)
    {
        ArrayList<Integer>sinPuntos = new ArrayList<>();


        ArrayList<Integer>copia =copiarDados(dados);
        int i = 0;
        if (escalera(dados)==true || Diez_mil(dados)==true)
        {
            for (i=0;i<copia.size();i++)
            {
                sinPuntos.add(1);

            }
        }

        else
        {
            for (i=0;i<copia.size();i++)
            {
                if (copia.get(i)!=5 && copia.get(i)!= 1 && Collections.frequency(copia,copia.get(i))<3)
                {
                    sinPuntos.add(0);
                }
                else
                {
                    sinPuntos.add(1);
                }
            }
            int pos = devolverUltimaPosicionRepetido(dados);

            if (pos!= -1)
            {
                sinPuntos.set(pos,0);
            }
        }

        return sinPuntos;
    }

    /// FUNCION PARA SETEAR LOS DADOS QUE SE PUEDEN VOLVER A TIRAR
    /// CUANDO SALE MAS DE 3 VECES UN NUMERO QUE NO SEA 1 0 5 SE DEVUELVE LA
    /// POSICION DE SU ULTIMA APARICION QUE ES EL DADO QUE NO SIRVE
    public static int devolverUltimaPosicionRepetido(ArrayList<Dado>d)
    {
        int pos=-1;
        int i =0;

        ArrayList<Integer>copia = copiarDados(d);
        for(i=0;i<copia.size();i++)
        {
            if(Collections.frequency(copia,copia.get(i))>3 && copia.get(i)!=1 && copia.get(i)!=5)
            {
                pos =i;

            }
        }
        return pos;
    }
}