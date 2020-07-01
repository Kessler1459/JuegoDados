package diez;

import clases.Dado;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Contiene los metodos estaticos para calcular los puntajes
 */

public class CalculadoraPuntosDiez {

    /**
     * Calcula si los 5 dados son iguales (diezmil) y setea el puntaje
     *
     * @param dados el arreglo con el que obtiene el puntaje
     * @return true si obtuvo diezmil, false si no lo obtuvo
     */
    public static boolean Diez_mil(ArrayList<Dado> dados) {
        boolean rta = false;

        ArrayList<Integer> frecuencia = new ArrayList<>();
        frecuencia = calcularFrecuencia(dados);
        Collections.sort(frecuencia, Collections.reverseOrder());
        if (frecuencia.get(1) == 5) {
            rta = true;
        }
        return rta;
    }

    /**
     * calcula con que frecuencia aparece cada numero de los 6 posibles
     *
     * @param dados con el que calcula las frecuencias
     * @return frecuencia : arraylist de enteros donde cada posicion es el nro de dado, y
     * el dato es la cantidad de veces que aparece
     */
    public static ArrayList<Integer> calcularFrecuencia(ArrayList<Dado> dados) /// devuelve la frecuencia de cada dado en un nuevo arreglo
    {
        ArrayList<Integer> frecuencia = new ArrayList<Integer>();
        for (int i = 1; i < 7; i++) {
            frecuencia.add(Collections.frequency(dados, new Dado(i)));
        }
        return frecuencia;
    }

    /**
     * Cuenta cuantos 5 salieron en el tiro
     *
     * @param dados con el tiro obtenido
     * @return cantidad de veces que salio el 5
     */

    public static int ContarCincos(ArrayList<Dado> dados) {
        ArrayList<Integer> d = copiarDados(dados);
        int cant = Collections.frequency(d, 5);
        return cant;
    }

    /**
     * Suma los puntos segun la cantidad de 5,50 puntos por cada 5
     * 500 puntos si hay tres 5, 550 si  hay cuatro 5.
     *
     * @param dados con el que suma los puntos
     * @return puntos totales
     */

    public static int SumarPtosCinco(ArrayList<Dado> dados) {
        int cant = ContarCincos(dados);
        int ptos = 0;
        if (cant == 3) {
            ptos += 500;
        } else {
            if (cant > 3) {
                ptos += 500 + (cant - 3) * 50;
            }
            {
                ptos += cant * 50;
            }
        }
        return ptos;
    }

    /**
     * Cuenta cuantos 1 salieron en el tiro
     *
     * @param dados con el tiro obtenido
     * @return cantidad de veces que salio el 1
     */

    public static int ContarUnos(ArrayList<Dado> dados) {
        ArrayList<Integer> d = copiarDados(dados);
        int cant = Collections.frequency(d, 1);

        return cant;
    }

    /**
     * Suma los puntos segun la cantidad de 1,100 puntos por cada 1
     * 1000 puntos si hay tres 1, 1050 si  hay cuatro 1.
     *
     * @param dados con el que suma los puntos
     * @return puntos totales
     */
    public static int SumarPtosUnos(ArrayList<Dado> dados) {
        int puntos = 0;
        int cant = ContarUnos(dados);

        if (cant == 3) {
            puntos += 1000;
        } else {
            if (cant > 3) {
                puntos += 1000 + (cant - 3) * 100;
            } else {
                puntos += cant * 100;
            }
        }
        return puntos;
    }

    /**
     * Calcula el puntaje total del tiro
     *
     * @param dados para calcular el puntaje
     * @return puntos obtenidos
     */
    public static int calcularPuntajeTotal(ArrayList<Dado> dados) {

        int puntaje = 0;

        ArrayList<Integer> copia = copiarDados(dados);

        int i = 2;

        if (Diez_mil(dados) == false && escalera(dados) == false) {

            puntaje += SumarPtosCinco(dados) + SumarPtosUnos(dados);

            for (i = 2; i < 7; i++) {
                if (Collections.frequency(copia, i) >= 3 && i != 5) {
                    puntaje += i * 100;
                }

            }
        } else {
            if (escalera(dados)) {
                puntaje += 500;
            } else {
                puntaje += 10000;
            }
        }

        return puntaje;
    }

    /**
     * copia los dados en un arraylist de enteros
     *
     * @param d arreglo de dados a copiar
     * @return arreglo de enteros con el numero de cada dado obtenido
     */
    public static ArrayList<Integer> copiarDados(ArrayList<Dado> d) {
        ArrayList<Integer> copia = new ArrayList<>();
        for (Dado da : d) {
            copia.add(da.getNumero());
        }

        return copia;
    }

    /**
     * Calcula si salio escalera
     *
     * @param dados arrelgo de dados para calcular
     * @return true or false, dependiendo si hay o no escalera
     */

    public static boolean escalera(ArrayList<Dado> dados) {
        boolean escalera = false;
        ArrayList<Integer> esca1 = new ArrayList<>();//1,2,3,4,5
        ArrayList<Integer> esca2 = new ArrayList<>();//2,3,4,5,6
        for (int i = 1; i < 6; i++) {
            esca1.add(i);
            esca2.add(i + 1);
        }
        ArrayList<Integer> tirada = copiarDados(dados);
        Collections.sort(tirada);

        if (tirada.equals(esca1) || tirada.equals(esca2)) {
            escalera = true;

        }

        return escalera;
    }
}