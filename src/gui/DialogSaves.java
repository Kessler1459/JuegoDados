package gui;


import clases.Juego;
import clases.Jugador;
import gene.Generala;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogSaves <T extends Juego> extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelLista;
    private JButton borrarButton;
    private JScrollPane jScroll;
    private JList<T> list1=new JList<T>();
    private DefaultListModel<T> modelo=new DefaultListModel<T>();
    private ArrayList<T> resultado;

    public DialogSaves(ArrayList<T> juegos) {          //todo talvez agregar fechas
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setLocationRelativeTo(null);
        iniciarLista(juegos);
        resultado=juegos;
        list1.setModel(modelo);
        panelLista.add(list1);
        iniciarBotones(juegos);
        this.pack();
    }

    private void iniciarLista(ArrayList<T> juegos)
    {
        for (T ju: juegos)
        {
            modelo.addElement(ju);
        }
    }

    private void iniciarBotones(ArrayList<T> juegos)
    {
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   //todo comprobar si selecciono algo
                Juego ju=list1.getSelectedValue();
                if (ju==null)
                {
                    JOptionPane.showMessageDialog(null,"Seleccione una partida");
                }
                else
                {
                    if (ju instanceof Generala)
                    {
                        Generala ge=(Generala) ju;
                        MenuGenerala menuGen=new MenuGenerala("Generala",ge);
                        menuGen.actualizarImagenDados();
                        menuGen.cargarTabla();
                    }
                    else
                    {
                        //todo aca lo mismo pero diezmil
                    }
                    dispose();
                }
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int seleccionado=list1.getSelectedIndex();
                if (seleccionado!= -1)
                {
                    modelo.remove(seleccionado);
                    resultado.remove(seleccionado);

                }
            }
        });
    }

    public ArrayList<T> getResultado() {
        return resultado;
    }
}
