package Excepciones;

/**
 * si no se encuentra las imagenes de dados en la ruta especificada
 */
public class ExcepcionImagenNoEncontrada extends Exception{

    public ExcepcionImagenNoEncontrada()
    {
        super("No se encontro la imagen en \"Imagenes/NUMERO.png\"");
    }

}
