package gui;

import clases.Generala;
import clases.Jugador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class MenuGenerala extends JFrame {


    private JPanel panelPrincipal;
    private JButton btnTirar;
    private JLabel lDado1;
    private JLabel lDado2;
    private JLabel lDado3;
    private JLabel lDado4;
    private JLabel lDado5;
    private JLabel lDado6;
    private JPanel panelDados;
    private JPanel panelPuntos;
    private JTable lTable;

    public MenuGenerala(String titulo, Generala generala) {
        super(titulo);
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        iniciarIconos();
        iniciarTable(generala.getJugadores());
    }

    public void iniciarIconos()
    {
        ImageIcon imagen=new ImageIcon("Imagenes\\6.png");
        lDado1.setIcon(imagen);
        lDado2.setIcon(imagen);
        lDado3.setIcon(imagen);
        lDado4.setIcon(imagen);
        lDado5.setIcon(imagen);
        lDado6.setIcon(imagen);
    }

    public void iniciarTable(ArrayList<Jugador> jugadores)
    {
        DefaultTableModel tablemodel=new DefaultTableModel(13,jugadores.size()+1);
        String [] categorias={"Generala doble","Generala","Poker","Full","Escalera","0","1","2","3","4","5","6"};
        int  i=1;
        for (Jugador ju: jugadores)
        {
            tablemodel.setValueAt(ju.getNombre(),0,i);
            i++;
        }
        for (i=1;i<13;i++)
        {
            tablemodel.setValueAt(categorias[i-1],i,0);
        }
        lTable.setModel(tablemodel);
    }

    public void animacionDados()
    {
        //poner un gif unos segundos al tirar, en lo posible tambien sonido
    }
}
