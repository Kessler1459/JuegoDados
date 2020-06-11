package gui;

import gene.Generala;
import clases.Jugador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPrincipal extends JFrame {

    private MenuGenerala menuGenerala;
    private ArrayList<Jugador> jugadores;

    private JPanel jPanel;
    private JCheckBox checkJugador4;
    private JCheckBox checkJugador3;
    private JTextField fieldJugador1;
    private JTextField fieldJugador2;
    private JTextField fieldJugador3;
    private JTextField fieldJugador4;
    private JButton diezMilButton;
    private JButton generalaButton;
    private JLabel lJugador1;
    private JLabel lJugador2;
    private JLabel lJugador3;
    private JLabel lJugador4;


    /**
     * frame inicial del juego
     * @param titulo titulo que tendra la ventana
     */
    public MenuPrincipal(String titulo) {
        super(titulo);
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        iniciarBarraMenus();
        jugadores = new ArrayList<>();
        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cargarListaDeJugadores())
                {
                    Generala generala = new Generala(jugadores);
                    menuGenerala = new MenuGenerala("Generala", generala);
                }
            }
        });
        checkJugador3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJugador3.isSelected()) {
                    fieldJugador3.setEnabled(true);
                } else fieldJugador3.setEnabled(false);
            }
        });
        checkJugador4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJugador4.isSelected()) {
                    fieldJugador4.setEnabled(true);
                } else fieldJugador4.setEnabled(false);
            }
        });
    }


    /**
     * *llena el arraylist de jugadores con los textfield de cada jugador
     */
    private boolean cargarListaDeJugadores()
    {
        boolean f=true;
        if (comprobarFieldsVacios())
        {
            String jugador1 = fieldJugador1.getText().trim();
            String jugador2 = fieldJugador2.getText().trim();
            String jugador3 = fieldJugador3.getText().trim();
            String jugador4 = fieldJugador4.getText().trim();
            jugadores.add(new Jugador(jugador1));
            jugadores.add(new Jugador(jugador2));
            if (fieldJugador3.isEnabled())
                jugadores.add(new Jugador(jugador3));
            if (fieldJugador4.isEnabled())
                jugadores.add(new Jugador(jugador4));
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Completa todos los nombres");
            f=false;
        }
        return f;
    }

    private boolean comprobarFieldsVacios()
    {
        boolean f=true;
        if(fieldJugador1.getText().trim().equals(""))
        {
            f=false;
            fieldJugador1.setBorder(new LineBorder(Color.RED));
        }
        if(fieldJugador2.getText().trim().equals(""))
        {
            f=false;
            fieldJugador2.setBorder(new LineBorder(Color.RED));
        }
        if (fieldJugador3.isEnabled() && fieldJugador3.getText().trim().equals(""))
        {
            f=false;
            fieldJugador3.setBorder(new LineBorder(Color.RED));
        }
        if (fieldJugador4.isEnabled() && fieldJugador4.getText().trim().equals(""))
        {
            f=false;
            fieldJugador4.setBorder(new LineBorder(Color.RED));
        }
        return f;
    }

    /**
     * crea menu de carga de partida de ambos juegos
     */
    private void iniciarBarraMenus()
    {
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Cargar partida");
        JMenuItem itemDiezMil=new JMenuItem("Diez mil");
        JMenuItem itemGenerala=new JMenuItem("Generala");
        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(itemDiezMil);
        menu.add(itemGenerala);
        menuBar.setVisible(true);
    }

    /**
     * reanuda una partida
     * @param generala partida para cargar
     */
    private void cargarPartidaGenerala(Generala generala)
    {
        menuGenerala = new MenuGenerala("Generala", generala);
    }
}