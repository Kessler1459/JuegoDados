package gui;

import Interfaces.MenuJuego;
import clases.Dado;
import Excepciones.ExcepcionFinalDePartida;
import gene.Generala;
import clases.Jugador;
import gene.PuntajeGenerala;
import persistencia.Persistencia;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * frame principal de la Generala
 */
public class MenuGenerala extends JFrame implements MenuJuego {

    private Generala generala;
    private DialogPuntos dialogo;
    private JLabel jlabelTurno;
    private JPanel panelPrincipal;
    private JButton btnTirar;
    private JLabelDado[] labelDado;
    private JPanel panelDados;
    private JPanel panelPuntos;
    private JTable lTable;

    public MenuGenerala(String titulo, Generala gene) {
        super(titulo);
        iniciarIcono();
        this.generala = gene;
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        iniciarBarraMenus();
        iniciarLabelsDado();
        iniciarBotones();
        iniciarJLabelTurno();
        actualizarJLabelTurno();
        iniciarPuntajes();
    }


    /**
     * inicializa el arreglo de JlabelDado, les setea una imagen inicial y lo agrega al panel de dados
     */
    @Override
    public void iniciarLabelsDado()
    {
        labelDado = new JLabelDado[5];
        ImageIcon imagen = new ImageIcon("Imagenes\\6.png");
        for (int i = 0; i < 5; i++) {
            labelDado[i] = new JLabelDado();
            labelDado[i].setIcon(imagen);
            panelDados.add(labelDado[i]);
        }
    }


    /**
     * inicializa, setea y agrega el boton de tirar al panel de los dados
     */
    @Override
    public void iniciarBotones()
    {
        btnTirar = new JButton("Tirar Dados");
        btnTirar.setFont(new Font("Arial Black",Font.PLAIN,16));
        btnTirar.setPreferredSize(new Dimension(250, 65));
        panelPrincipal.add(btnTirar);
        btnTirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jugador jugadorDeTurno=generala.getJugadores().get(generala.getTurno());
                if (generala.getTiradas()==0)                                     //en la primer tirada se deben tirar todos los dados
                {
                    generala.tirarDados();
                }
                else
                {
                    tirarDadosSelectivo();                                                //en las otras dos ya se puede elegir
                }
                actualizarImagenDados();                                     //cambia la imagen a la de los dados actuales
                dialogo=new DialogPuntos(generala);
                String categoriaSeleccionada=dialogo.getCategoriaSeleccionada();                 //guarda la categoria del boton usado
                if (!categoriaSeleccionada.equals(""))                                      //si fue seleccionada una categoria, se anota y se pasa de turno
                {
                    anotarEnTabla(categoriaSeleccionada,jugadorDeTurno.getPuntosGen());
                    try
                    {
                        generala.pasarTurno();                                //si la partida llega a su ultimo turno se muestran los resultados finales
                    }
                    catch (ExcepcionFinalDePartida ex)
                    {
                        dialogoResultadosFinales();
                    }
                    actualizarJLabelTurno();
                }
                else                                                                        //si se decide seguir tirando se aumenta el numero de tirada
                {
                    generala.aumentarTirada();
                }
                reactivarLabelsDado();                                                      //resetea la seleccion de los dados
            }
        });
    }

    /**
     * label que indica el nombre del jugador que tiene el turno
     */
    @Override
    public void iniciarJLabelTurno()
    {
        jlabelTurno=new JLabel();
        jlabelTurno.setMinimumSize(new Dimension(200,40));
        jlabelTurno.setFont(new Font("Arial Black",Font.PLAIN,16));
        jlabelTurno.setForeground(Color.BLACK);
        panelPrincipal.add(jlabelTurno);
        jlabelTurno.setLabelFor(btnTirar);
    }

    /**
     * actualiza el JLabel con el nombre del jugador del turno actual
     */
    @Override
    public void actualizarJLabelTurno()
    {
        Jugador jugador=generala.getJugadores().get(generala.getTurno());
        jlabelTurno.setText("Turno de: "+jugador.getNombre());
    }


    /**
     * tira dados pero teniendo en cuenta que dados estan seleccionados y cuales no
     */
    private void tirarDadosSelectivo()
    {
        int[] dados={0,0,0,0,0};
        for (int i=0;i<5;i++)
        {
            dados[i]=verificarSeleccionDado(labelDado[i]);
        }
        generala.tirarDados(dados[0],dados[1],dados[2],dados[3],dados[4]);
    }

    /**
     * comprueba si el label esta seleccionado o no
     * @return 1 o 0 segun lo este
     */
    private int verificarSeleccionDado(JLabelDado labelDado)
    {
        int a=0;
        if (labelDado.isEnabled())
        {
            a=1;
        }
        return a;
    }

    /**
     * vuelve a activar tod0 el arreglo de labels
     */
    private void reactivarLabelsDado()
    {
        for (JLabelDado d: labelDado)
        {
            d.setEnabled(true);
        }
    }

    /**
     * recorre el arreglo de JlabelDado actualizando su imagen con el dado correspondiente al mismo indice
     */
    public void actualizarImagenDados() {
        ArrayList<Dado> dados = generala.getDados();
        for (int i = 0; i < 5; i++) {
            labelDado[i].setIcon(dados.get(i).getImagen());
        }
    }


    /**
     * suma todos los puntajes de los jugadores y muestra el ganador en un dialog
     */
    private void dialogoResultadosFinales()
    {
        JDialog dialogo=new JDialog();
        dialogo.setTitle("Resultados");
        JPanel panelDialogo=new JPanel(new GridLayout(2,0));
        JPanel panelLabels=new JPanel((new FlowLayout(FlowLayout.CENTER,40,40)));
        JPanel panelBoton=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelLabels.setBackground(new Color(9, 65,0));
        panelBoton.setBackground(new Color(9, 65,0));
        panelDialogo.add(panelLabels);
        panelDialogo.add(panelBoton);
        dialogo.setContentPane(panelDialogo);
        dialogo.setResizable(false);
        int i=0;
        for (Jugador ju: generala.getJugadores())
        {
            JLabel label=new JLabel(ju.getNombre()+": "+ju.getPuntosGen().sumarPuntajeTotal());
            label.setFont(new Font("Arial Black",Font.PLAIN,25));
            label.setForeground(Color.BLACK);
            label.setVisible(true);
            panelLabels.add(label);
            i++;
        }
        JButton btn=new JButton("Aceptar");
        btn.setFont(new Font("Arial Black",Font.PLAIN,25));
        btn.setPreferredSize(new Dimension(150,60));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                dialogo.dispose();
            }
        });
        panelBoton.add(btn);
        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }


    /**
     * inicializa la tabla con las categorias y jugadores
     */
    @Override
    public void iniciarPuntajes() {
        ArrayList<Jugador> jugadores=generala.getJugadores();
        DefaultTableModel tablemodel = new DefaultTableModel(12, jugadores.size() + 1);
        String[] categorias = {"","Generala doble", "Generala", "Poker", "Full", "Escalera", "1", "2", "3", "4", "5", "6"};
        int i = 1;
        for (Jugador ju : jugadores) {
            tablemodel.setValueAt(ju.getNombre(), 0, i);
            i++;
        }
        for (i = 0; i < 12; i++) {
            tablemodel.setValueAt(categorias[i], i, 0);
        }
        lTable.setModel(tablemodel);
        lTable.setDefaultEditor(Object.class,null);
        lTable.setFocusable(false);
    }

    /**
     * escribe en la tabla un dato que se hubiera agregado a las puntuaciones del jugador
     * @param categoriaSeleccionada categoria en la que se asignara
     * @param puntajeDelJugador para leer la nueva puntuacion a ser anotada
     */
    private void anotarEnTabla(String categoriaSeleccionada,PuntajeGenerala puntajeDelJugador)
    {
        int i=0;
        boolean flag=false;
        while ((i<12) && (!flag))  //mientras no llegue al final o no encuentre la categoria seguira recorriendo las filas
        {
            String celda=lTable.getValueAt(i,0).toString();
            if (celda.equals(categoriaSeleccionada))
            {
                lTable.setValueAt(puntajeDelJugador.getCategoria(categoriaSeleccionada),i,generala.getTurno()+1);
                flag=true;
            }
            i++;
        }
    }

    /**
     * escribe la tabla entera con los puntajes de los jugadores(se usa al reanudar partida)
     */
    @Override
    public void actualizarPuntajes()
    {
        ArrayList<Jugador> jugadores=generala.getJugadores();
        for (int i=0; i<jugadores.size(); i++)
        {
            for (int j=1;j<12;j++)
            {
                String categoria=lTable.getValueAt(j,0).toString();
                String punto=jugadores.get(i).getPuntosGen().getCategoria(categoria);
                lTable.setValueAt(punto,j,i+1);
            }
        }
    }

    /**
     * crea menu de guardado de partida
     */
    @Override
    public void iniciarBarraMenus()
    {
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Partida");
        JMenuItem item=new JMenuItem("Guardar partida");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Persistencia.guardarPartida(generala))
                {
                    JOptionPane.showMessageDialog(null,"Partida guardada con exito.");
                }
                else
                    JOptionPane.showMessageDialog(null,"Error al guardar la partida.");
            }
        });
        menuBar.add(menu);
        menu.add(item);
        this.setJMenuBar(menuBar);
        menuBar.setVisible(true);
    }

    /**
     * agrega el icono para barra de tareas
     */
    private void iniciarIcono()
    {
        try
        {
            setIconImage(ImageIO.read(new File("Imagenes/IconoCompleto.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
