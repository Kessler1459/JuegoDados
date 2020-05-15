package gui;

import clases.Generala;
import clases.Jugador;
import com.sun.javafx.collections.ElementObservableListDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

    public MenuPrincipal(String titulo){
        super(titulo);
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);


        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jugador1=fieldJugador1.getText().toString().trim();
                String jugador2=fieldJugador2.getText().toString().trim();
                String jugador3=fieldJugador3.getText().toString().trim();
                String jugador4=fieldJugador4.getText().toString().trim();
                ArrayList<Jugador> jugadores= new ArrayList<Jugador>();
                jugadores.add(new Jugador(jugador1));
                jugadores.add(new Jugador(jugador2));
                if (fieldJugador3.isEnabled())
                    jugadores.add(new Jugador(jugador3));
                if (fieldJugador4.isEnabled())
                    jugadores.add(new Jugador(jugador4));
                Generala generala = new Generala(jugadores);
                MenuGenerala menuGenerala=new MenuGenerala(titulo,generala);
            }
        });
        checkJugador3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJugador3.isSelected())
                {
                    fieldJugador3.setEnabled(true);
                }
                else fieldJugador3.setEnabled(false);
            }
        });
        checkJugador4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkJugador4.isSelected())
                {
                    fieldJugador4.setEnabled(true);
                }
                else fieldJugador4.setEnabled(false);
            }
        });
    }

    public static void main(String[] args) {
        MenuPrincipal frame=new MenuPrincipal("titulo");
        frame.setVisible(true);
    }

}
