package gui;

import clases.Dado;
import clases.Jugador;
import diez.CalculadoraPuntosDiez;
import diez.Diezmil;
import diez.PuntajeDiezmil;
import persistencia.Persistencia;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Frame principal del DiezMil
 */

public class MenuDiez extends JFrame{

    private Diezmil game;
    private JPanel PanelPrincipal;
    private JPanel panelDados;
    private JButton botonTirar;
    private JButton anotarPuntaje;
    private JPanel panelPuntos;
    private JTextPane editorPaneJUGADOR;
    private JTextPane editorPanePUNTAJE;
    private JTextPane []editor ;
    private JTextPane[]punt;
    private JTextPane puntajeEnJuego;
    private JLabel jlabelTurno;
    private JLabelDado[] labelDado;

    private ArrayList<Dado>nuevoTiro;

    public MenuDiez (String titulo,Diezmil game)
    {
        super(titulo);
        this.game = game;
        this.setContentPane(PanelPrincipal);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        iniciarBarraMenus();
        iniciarPuntajeEnJuego();
        iniciarLabelsDado();
        iniciarBotonTirarDados();
        iniciarBotonAnotarPuntaje();
        iniciarJLabelTurno();
        actualizarJLabelTurno();
        iniciarTablaPuntajes();
        actualizarPuntajes();
    }

    /**
     * Inicializa el boton para anotar puntaje y lo agrega al panel principal
     *
     */
    private void iniciarBotonAnotarPuntaje()
    {
        anotarPuntaje= new JButton();
        anotarPuntaje.setPreferredSize(new Dimension(250,65));
        anotarPuntaje.setBounds(410,500,250,65);
        anotarPuntaje.setFont(new Font("Arial Black",Font.PLAIN,16));
        anotarPuntaje.setText("Anotar Puntaje");
        PanelPrincipal.add(anotarPuntaje);
        anotarPuntaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                PuntajeDiezmil puntosJugador =getJugadorDeTurno(game).getPuntosDiez();
                puntosJugador.setPuntajeTotal(puntosJugador.getPuntajeTiro());
                if (puntosJugador.getPuntajeTiro()==0)
                {
                    JOptionPane.showMessageDialog(null,"NO TIENE PUNTOS QUE ANOTAR");
                }
                else
                {
                    game.pasarTurno();
                    actualizarJLabelTurno();
                    puntosJugador.reiniciarPuntaje();
                    actualizarPuntajes();
                    actualizarPuntajeEnJuego();
                    actualizarBotonTirar();
                }
            }
        });
    }

    /**
     * inicia la tabla de puntuacion y carga los nombres y los puntajes de cada uno
     */
    private void iniciarTablaPuntajes()
    {
        ArrayList<Jugador>players  =game.getJugadores();
        editor = new JTextPane[players.size()];
        punt = new JTextPane[players.size()];
        editorPaneJUGADOR = new JTextPane();
        editorPanePUNTAJE = new JTextPane();


        editorPaneJUGADOR.setFont(new Font("Arial Black",Font.PLAIN,20));

        StyledDocument doc = editorPaneJUGADOR.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        editorPaneJUGADOR.setPreferredSize(new Dimension(250,65));
        editorPaneJUGADOR.setBackground(new Color(9,65,0));
        editorPaneJUGADOR.setForeground(Color.WHITE);
        editorPaneJUGADOR.setText("JUGADOR");
        editorPaneJUGADOR.setEditable(false);

        editorPanePUNTAJE.setFont(new Font("Arial Black",Font.PLAIN,20));

        doc = editorPanePUNTAJE.getStyledDocument();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        editorPanePUNTAJE.setPreferredSize(new Dimension(250,65));
        editorPanePUNTAJE.setBackground(new Color(9,65,0));
        editorPanePUNTAJE.setForeground(Color.WHITE);
        editorPanePUNTAJE.setText("PUNTAJE");
        editorPanePUNTAJE.setEditable(false);
        panelPuntos.add(editorPaneJUGADOR);
        panelPuntos.add(editorPanePUNTAJE);
        for (int i=0;i<players.size();i++)
        {
            editor[i] = new JTextPane();
            doc = editor[i].getStyledDocument();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            editor[i].setPreferredSize(new Dimension(250,65));
            editor[i].setFont(new Font ("Arial Black",Font.PLAIN,20));
            editor[i].setForeground(Color.WHITE);
            editor[i].setBackground(new Color(9,65,0));
            editor[i].setText(players.get(i).getNombre());
            editor[i].setEditable(false);
            panelPuntos.add(editor[i]);

            punt[i] = new JTextPane();
            doc = punt[i].getStyledDocument();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            punt[i].setFont(new Font ("Arial Black",Font.PLAIN,20));
            punt[i].setPreferredSize(new Dimension(250,65));
            punt[i].setBackground(new Color(9,65,0));
            punt[i].setForeground(Color.WHITE);
            punt[i].setEditable(false);
            panelPuntos.add(punt[i]);
        }
    }

    /**
     * actualiza los puntajes en la tabla de puntuacion recorriendo el arraylist de jugadores
     */
    public void actualizarPuntajes ()
    {
        ArrayList<Jugador>players = game.getJugadores();
        for (int i = 0;i<players.size();i++)
        {
            punt[i].setText(""+players.get(i).getPuntosDiez().getPuntajeTotal());
        }
    }

    /**
     * inicializa los dados en el panel principal y les asigna una imagen
     */
    private void iniciarLabelsDado()
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
     * inicializa el JTextPane del puntaje en juego
     */
    private void iniciarPuntajeEnJuego ()
    {
        Jugador j = game.getJugadores().get(game.getTurno());
        puntajeEnJuego = new JTextPane();

        StyledDocument doc = puntajeEnJuego.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        puntajeEnJuego.setPreferredSize(new Dimension(400,65));
        puntajeEnJuego.setBackground(new Color(9,65,0));
        puntajeEnJuego.setFont(new Font("Arial Black",Font.PLAIN,18));
        puntajeEnJuego.setForeground(Color.WHITE);
        puntajeEnJuego.setEditable(false);
        puntajeEnJuego.setText("Puntaje en juego: " +j.getPuntosDiez().getPuntajeTiro());
        panelDados.add(puntajeEnJuego);
    }

    /**
     * Actualiza el JTextPane del puntaje en juego
     */
    private void  actualizarPuntajeEnJuego ()
    {
        Jugador j = game.getJugadores().get(game.getTurno());
        puntajeEnJuego.setText("Puntaje en juego: " + j.getPuntosDiez().getPuntajeTiro());
    }

    /**
     * Busca un ganador si uno de los jugadores sumo 10 mil puntos o mas
     */
    private void buscarGanador()
    {
        ArrayList<Jugador>players=game.getJugadores();
        int i = 0;
        while(i <players.size()-1 && players.get(i).getPuntosDiez().getPuntajeTotal()<10000)
        {
            i++;
        }
        if (players.get(i).getPuntosDiez().getPuntajeTotal()>=10000)
        {
            JOptionPane.showMessageDialog(null,"FELICITACIONES "+players.get(i).getNombre()+" GANA EL JUEGO");
        }
    }

    /**
     * Actualiza la imagen de los dados luego de que fueron lanzados
     */
    public void actualizarImagenDados() {
        ArrayList<Dado> dados = game.getDados();
        for (int i = 0; i < 5; i++) {
            labelDado[i].setIcon(dados.get(i).getImagen());
        }
    }

    /**
     * Inicializa un JLabel y setea el texto con el nombre del jugador que le corresponde jugar
     */
    private void iniciarJLabelTurno()
    {
        jlabelTurno=new JLabel();

        jlabelTurno.setMinimumSize(new Dimension(200,65));
        jlabelTurno.setFont(new Font("Arial Black",Font.PLAIN,20));
        jlabelTurno.setForeground(Color.white);
        PanelPrincipal.add(jlabelTurno);
    }

    /**
     * Actualiza el texto del boton de tirar dados
     */
    private void actualizarBotonTirar()
    {
        if(game.getTiradas()==0)
        {
            botonTirar.setText("Tirar Dados");
        }
        else
        {
            botonTirar.setText("Seguir Tirando");
        }
    }

    /**
     * actualiza el texto del JLabelTurno
     */
    private void actualizarJLabelTurno()
    {
        String turno=game.getTurnoJugador();
        jlabelTurno.setText("Turno de: "+turno);
    }

    /**
     * Inicializa y setea el boton para tirar los dados y lo agrega al Panel Principal
     */

    private void iniciarBotonTirarDados() {

        botonTirar = new JButton("Tirar Dados");
        botonTirar.setFont(new Font("Arial Black", Font.PLAIN, 16));
        botonTirar.setPreferredSize(new Dimension(250, 65));
        PanelPrincipal.add(botonTirar);
        botonTirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jugador jugadorDeTurno =game.getJugadores().get(game.getTurno());  //guarda el jugador de turno en una variable
                PuntajeDiezmil puntosJugadorTurno = jugadorDeTurno.getPuntosDiez();  // guarda el puntaje en una variable

                if (game.getTiradas()==0 || game.SirvenTodos()==1)   //tira los 5 dados tanto si es el primer tiro del turno como si luego de tirar todos los dados tienen puntos
                {
                    game.tirarDados();
                    if (CalculadoraPuntosDiez.calcularPuntajeTotal(game.getDados())==0)    // si no se obtienen puntos en el tiro
                    {

                        puntosJugadorTurno.reiniciarPuntaje();                   // se reinicia el puntaje de los tiros a 0
                        game.pasarTurno();                                     // se pasa el turno al proximo jugador
                    }
                    else
                    {
                        puntosJugadorTurno.setPuntajeTiro(CalculadoraPuntosDiez.calcularPuntajeTotal(game.getDados()));    //actualiza el puntaje del tiro con los puntos obtenidos
                    }
                }
                else
                {
                    nuevoTiro=game.tirarDadosSinPuntos(); //tira los dados sin puntos y los guarda en un nuevo arreglo

                    if(CalculadoraPuntosDiez.calcularPuntajeTotal(nuevoTiro)==0)    //calcula los puntos del nuevo tiro
                    {
                        puntosJugadorTurno.reiniciarPuntaje();  //reinicia el puntaje del tiro a 0 si no se obtuvo puntaje
                        game.pasarTurno();
                    }
                    else
                    {
                        puntosJugadorTurno.setPuntajeTiro(CalculadoraPuntosDiez.calcularPuntajeTotal(nuevoTiro)); //agrega el puntaje obtenido al tiro
                    }
                }
                actualizarPuntajeEnJuego();
                actualizarImagenDados();
                actualizarBotonTirar();
                actualizarJLabelTurno();
                actualizarPuntajes();
                buscarGanador();

            }});
    }

    /**
     * Recorre los jugadores cargados y devuelve al que le toca jugar
     * @param game recibe el objeto Diezmil que contiene la funcion para buscar el jugador
     * @return Jugador al que le corresponde jugar
     */


    private Jugador getJugadorDeTurno (Diezmil game)
    {
        Jugador jugadorDeTurno =new Jugador();
        ArrayList<Jugador>jugadores =game.getJugadores();
        for (Jugador j : jugadores)
        {
            if(j.getNombre().equals(game.getTurnoJugador()))
            {
                jugadorDeTurno=j;
            }
        }
        return jugadorDeTurno;
    }
    /**
     * crea menu de guardado de partida
     */
    private void iniciarBarraMenus()
    {
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Partida");
        JMenuItem item=new JMenuItem("Guardar partida");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Persistencia.guardarPartida(game))
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
}
