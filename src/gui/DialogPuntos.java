package gui;

import clases.Jugador;
import gene.CalculadoraPuntosGenerala;
import gene.Generala;
import gene.PuntajeGenerala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * dialogo para elegir de que manera anotar los puntos de una tirada o seguir intentando
 */
public class DialogPuntos extends JDialog {
    private JPanel contentPane;
    private ArrayList<JButton> botonesParaPuntuar;
    private ArrayList<JButton> botonesParaTachar;
    private String categoriaSeleccionada;

    /**constructor
     * @param generala para generar los botones correspondientes
     */
    public DialogPuntos(Generala generala) {
        botonesParaPuntuar =new ArrayList<JButton>();
        botonesParaTachar = new ArrayList<JButton>();
        categoriaSeleccionada="";
        this.setUndecorated(true);
        this.setResizable(false);
        this.setContentPane(contentPane);
        contentPane.setBackground(new Color(0x094100));
        this.setMinimumSize(new Dimension(250,617));
        this.setMaximumSize(new Dimension(250,617)); //todo probar localizacion en pantalla mas chica y sacar la X
        this.setModal(true);
        this.pack();
        this.setLocation(1550,216);
        generarOpciones(generala);
        agregarBotones();
        agregarBotonSeguirTirando(generala);
        this.setVisible(true);
    }


    /**
     * genera todos los botones segun las elecciones posibles para anotar recorriendo tod0s los nombres de categorias y calculando los resultados de las mismas
     * @param generala para obtener los dados y numero de tiradas
     */
    private void generarOpciones(Generala generala)
    {
        Jugador jugador=generala.getJugadores().get(generala.getTurno()); //busco el jugador que esta jugando el turno
        PuntajeGenerala puntos=jugador.getPuntosGen();                    //guardo la lista de puntos
        String [] categorias={"Generala doble","Generala", "Poker", "Full", "Escalera", "1", "2", "3", "4", "5", "6"};
        int resultado;
        for (int i=0;i<11;i++)
        {
            resultado= CalculadoraPuntosGenerala.calcularCategoria(generala,categorias[i]);  //calcula cuantos puntos ganaria en cada categoria
            if (puntos.getCategoria(categorias[i]).equals(""))  //si la categoria buscada no se encuentra ya anotada
            {
                botonesParaTachar.add(crearBotonDeTachar(categorias[i],puntos));      //agrega los botones de tachar al arreglo
                if (resultado>0)  //y si el resultado es mayor a 0
                {
                    botonesParaPuntuar.add(crearBotonDeCategoria(categorias[i],resultado,puntos));     //agrega los botones de categorias disponibles
                }
            }
        }
    }

    /**
     * crea un boton que asignara los puntos calculados a la categoria correspondiente y cerrara el dialogo
     * @param categoria para asignar el puntaje
     * @param resultado el puntaje a asignar
     * @param puntos   la lista de puntos
     */
    private JButton crearBotonDeCategoria(String categoria,int resultado,PuntajeGenerala puntos)
    {
        JButton btn= new JButton();
        btn.setText(resultado+" en "+categoria);
        btn.setMinimumSize(new Dimension(250,30));
        btn.setMaximumSize(new Dimension(250,30));
        btn.setPreferredSize(new Dimension(250,30));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.asignarCategoria(categoria,String.valueOf(resultado));
                categoriaSeleccionada=categoria;
                dispose();
            }
        });
        return btn;
    }

    /**
     * crea un boton que tachara la categoria correspondiente y cerrara el dialogo
     * @param categoria la categoria que tachara
     * @param puntos la puntuacion donde tachara
     */
    private JButton crearBotonDeTachar(String categoria,PuntajeGenerala puntos)
    {
        JButton btn= new JButton();
        btn.setMinimumSize(new Dimension(250,30));
        btn.setMaximumSize(new Dimension(250,30));
        btn.setPreferredSize(new Dimension(250,30));
        btn.setForeground(new Color(145,0,0));
        btn.setText("Tachar: "+categoria);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                puntos.asignarCategoria(categoria,"X");
                categoriaSeleccionada=categoria;
                dispose();
            }
        });
        return btn;
    }

    /**
     * agrega al panel todos los botones guardados en el arreglo
     */
    private void agregarBotones()
    {
        for (JButton bt: botonesParaPuntuar)
        {
            contentPane.add(bt);
        }
        for (JButton btParaTachar: botonesParaTachar)
        {
            contentPane.add(btParaTachar);
        }
    }

    /**
     * aumenta el numero de tiradas y permite volver a tirar hasta 2 veces mas
     */
    private void agregarBotonSeguirTirando(Generala gen)
    {
        if (gen.getTiradas()!=2)
        {
            JButton btn=new JButton("Seguir tirando");
            btn.setMinimumSize(new Dimension(250,30));
            btn.setMaximumSize(new Dimension(250,30));
            btn.setPreferredSize(new Dimension(250,30));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();                      //de no querer anotar, cerramos el dialogo para que siga tirando
                }
            });
            contentPane.add(btn);
        }
    }

    public String getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }
}
