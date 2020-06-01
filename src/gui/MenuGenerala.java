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

    /**
     * inicializa el arreglo de JlabelDado, les setea una imagen inicial y lo agrega al panel de dados
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
     * inicializa, setea y agrega el boton de tirar al panel de los dados
     */
    private void iniciarBotonTirarDados()
    {
        btnTirar = new JButton("Tirar Dados");

        btnTirar.setPreferredSize(new Dimension(250, 65));
        panelPrincipal.add(btnTirar);
        btnTirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generala.tirarDados();
                actualizarImagenDados();
            }
        });
    }

    /**
     * recorre el arreglo de JlabelDado actualizando su imagen con el dado correspondiente al mismo indice
     */
    private void actualizarImagenDados() {
        ArrayList<Dado> dados = generala.getDados();
        for (int i = 0; i < 5; i++) {
            labelDado[i].setIcon(dados.get(i).getImagen());
        }
    }

    public void animacionDados() {
        //todo poner un gif unos segundos al tirar, en lo posible tambien sonido
    }


    /**
     * inicializa la tabla con las categorias y jugadores
     * @param jugadores el arreglo de jugadores para la tabla
     */
    private void iniciarTable(ArrayList<Jugador> jugadores) {
        DefaultTableModel tablemodel = new DefaultTableModel(12, jugadores.size() + 1);
        String[] categorias = {"Generala doble", "Generala", "Poker", "Full", "Escalera", "1", "2", "3", "4", "5", "6"};
        int i = 1;
        for (Jugador ju : jugadores) {
            tablemodel.setValueAt(ju.getNombre(), 0, i);
            i++;
        }
        for (i = 1; i < 12; i++) {
            tablemodel.setValueAt(categorias[i - 1], i, 0);
        }
        lTable.setModel(tablemodel);
    }
}
