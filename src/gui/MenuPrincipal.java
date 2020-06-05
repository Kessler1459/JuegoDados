package gui;

import gene.Generala;
import clases.Jugador;

import javax.swing.*;
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
    private JTextField fieldJugador3; //todo talvez hacer un arreglo 
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
    public MenuPrincipal(String titulo) {             //todo si sobra tiempo, guardado y cargado de partida de ambos juegos en json
        super(titulo);
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jugadores = new ArrayList<>();


        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarListaDeJugadores();
                Generala generala = new Generala(jugadores);
                menuGenerala = new MenuGenerala(titulo, generala);
                dispose();
            }
        });
        checkJugador3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJugador3.isSelected()) {
                    fieldJugador3.setEnabled(true); //todo modularizar esto, talvez otra clase check
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