package gui;

import clases.Dado;
import clases.Generala;
import clases.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGenerala extends JFrame {

    private Generala generala;
    private JPanel panelPrincipal;
    private JButton btnTirar;
    private JLabelDado[] labelDado;
    private JPanel panelDados;
    private JPanel panelPuntos;
    private JTable lTable;

    public MenuGenerala(String titulo, Generala gene) {
        super(titulo);
        this.generala = gene;
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        iniciarLabelsDado();
        iniciarBotonTirarDados();
        iniciarTable(generala.getJugadores());


    }

    private void iniciarLabelsDado()                //inicializa tod0 el arreglo, setea la imagen inicial y lo agrega al panel de dados
    {
        labelDado = new JLabelDado[5];
        ImageIcon imagen = new ImageIcon("Imagenes\\6.png");
        for (int i = 0; i < 5; i++) {
            labelDado[i] = new JLabelDado();
            labelDado[i].setIcon(imagen);
            panelDados.add(labelDado[i]);
        }
    }

    private void iniciarBotonTirarDados()        //inicializa, setea y agrega al panel de dados
    {
        btnTirar = new JButton("Tirar Dados");
        btnTirar.setMinimumSize(new Dimension(200, 50));
        panelDados.add(btnTirar);
        btnTirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generala.getTurno() == 1) {
                    generala.tirarDados();
                }
                //else aca
                else {

                }

                actualizarImagenDados();
            }
        });
    }

    private void actualizarImagenDados() {
        ArrayList<Dado> dados = generala.getDados();
        for (int i = 0; i < 5; i++) {
            labelDado[i].setIcon(dados.get(i).getImagen());
        }
    }

    public void animacionDados() {
        //poner un gif unos segundos al tirar, en lo posible tambien sonido
    }

    private void iniciarTable(ArrayList<Jugador> jugadores) {
        DefaultTableModel tablemodel = new DefaultTableModel(13, jugadores.size() + 1);
        String[] categorias = {"Generala doble", "Generala", "Poker", "Full", "Escalera", "0", "1", "2", "3", "4", "5", "6"};
        int i = 1;
        for (Jugador ju : jugadores) {
            tablemodel.setValueAt(ju.getNombre(), 0, i);
            i++;
        }
        for (i = 1; i < 13; i++) {
            tablemodel.setValueAt(categorias[i - 1], i, 0);
        }
        lTable.setModel(tablemodel);
    }
}
