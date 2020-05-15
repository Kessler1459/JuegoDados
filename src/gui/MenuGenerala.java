package gui;

import clases.Generala;

import javax.swing.*;

public class MenuGenerala extends JFrame {


    private JPanel panel;

    public MenuGenerala(String titulo, Generala generala) {
        super(titulo);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
