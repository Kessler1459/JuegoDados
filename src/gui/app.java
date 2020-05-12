package gui;

import clases.Dado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class app {
    private JPanel panel1;
    private Dado dad;


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setContentPane(new app().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
