package gui;

import clases.Generala;
import clases.Jugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPrincipal extends JFrame {


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
    private ArrayList<Jugador> jugadores;

    public MenuPrincipal(String titulo) {
        super(titulo);
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        jugadores = new ArrayList<>();


        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                    //preguntar excepcion campos vacios
                cargarListaDeJugadores();
                Generala generala = new Generala(jugadores);
                MenuGenerala menuGenerala = new MenuGenerala(titulo, generala);
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

    public static void main(String[] args) {
        MenuPrincipal frame = new MenuPrincipal("titulo");
        frame.setVisible(true);
    }

    private void cargarListaDeJugadores()
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
}