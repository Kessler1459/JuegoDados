package gui;


import javax.swing.*;



public class app {
    private JPanel panel1;
    private JButton BtnDiezMil;
    private JButton BtnGenerala;
    private JLabel LTitulo;
    private JLabel LJugadores;
    private JLabel LJugador1;
    private JLabel LJugador2;
    private JTextField TxtFJugador2;
    private JTextField TxtFJugador1;
    private JLabel LJugador4;
    private JLabel LJugador3;
    private JTextField TxtFJugador3;
    private JTextField TxtFJugador4;


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setContentPane(new app().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
