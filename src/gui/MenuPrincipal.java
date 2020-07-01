package gui;

import diez.Diezmil;
import gene.Generala;
import clases.Jugador;
import org.json.JSONArray;
import persistencia.Persistencia;
import persistencia.To;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MenuPrincipal extends JFrame {

    private MenuGenerala menuGenerala;
    private ArrayList<Jugador> jugadores;
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


    /**
     * frame inicial del juego
     * @param titulo titulo que tendra la ventana
     */
    public MenuPrincipal(String titulo) {
        super(titulo);
        iniciarIcono();
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        iniciarBarraMenus();
        jugadores = new ArrayList<>();
        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cargarListaDeJugadores())
                {
                    Generala generala = new Generala(jugadores);
                    menuGenerala = new MenuGenerala("Generala", generala);
                    jugadores=new ArrayList<>();
                }
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
        diezMilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cargarListaDeJugadores())
                {
                    Diezmil game = new Diezmil(jugadores);
                    MenuDiez menu10 = new MenuDiez("Diezmil",game);
                    jugadores=new ArrayList<>();
                }
            }
        });
    }


    /**
     * *llena el arraylist de jugadores con los textfield de cada jugador
     */
    private boolean cargarListaDeJugadores()
    {
        boolean f=true;
        if (comprobarFieldsVacios())
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
        else
        {
            JOptionPane.showMessageDialog(null,"Completa todos los nombres");
            f=false;
        }
        return f;
    }

    private boolean comprobarFieldsVacios()
    {
        boolean f=true;
        if(fieldJugador1.getText().trim().equals(""))
        {
            f=false;
            fieldJugador1.setBorder(new LineBorder(Color.RED));
        }
        if(fieldJugador2.getText().trim().equals(""))
        {
            f=false;
            fieldJugador2.setBorder(new LineBorder(Color.RED));
        }
        if (fieldJugador3.isEnabled() && fieldJugador3.getText().trim().equals(""))
        {
            f=false;
            fieldJugador3.setBorder(new LineBorder(Color.RED));
        }
        if (fieldJugador4.isEnabled() && fieldJugador4.getText().trim().equals(""))
        {
            f=false;
            fieldJugador4.setBorder(new LineBorder(Color.RED));
        }
        return f;
    }

    /**
     * crea menu de carga de partida de ambos juegos
     */
    private void iniciarBarraMenus()
    {
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Cargar partida");
        JMenuItem itemDiezMil=new JMenuItem("Diez mil");
        JMenuItem itemGenerala=new JMenuItem("Generala");
        /**
         * botones que inician el dialogo con el arreglo de partidas sacado del archivo correspondiente
         */
        itemGenerala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Generala> saves=cargarPartidaGenerala();
                DialogSaves<Generala> dialogo=new DialogSaves(saves);
                dialogo.setVisible(true);
                ArrayList<Generala> resultado=dialogo.getResultado();
                JSONArray arrayModificado;
                if (!resultado.isEmpty())         //recupera las partidas desde el dialogo(por si hubo eliminados) y lo vuelve a escribir
                {
                    arrayModificado= To.generalaArrayListToJSON(resultado);
                    Persistencia.escribirArray(arrayModificado,1);
                }
                else
                {
                    arrayModificado=new JSONArray();
                    Persistencia.escribirArray(arrayModificado,1);
                }
            }
        });
        itemDiezMil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Diezmil> saves=cargarPartidaDiezMil();
                DialogSaves<Diezmil> dialogo=new DialogSaves(saves);
                dialogo.setVisible(true);
                ArrayList<Diezmil> resultado=dialogo.getResultado();
                JSONArray arrayModificado;
                if (!resultado.isEmpty())
                {
                    arrayModificado= To.diezMilArrayListToJSON(resultado);
                    Persistencia.escribirArray(arrayModificado,0);
                }
                else {
                    arrayModificado = new JSONArray();
                    Persistencia.escribirArray(arrayModificado, 0);
                }
            }
        });
        this.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(itemDiezMil);
        menu.add(itemGenerala);
        menuBar.setVisible(true);
    }

    /**
     * lee del archivo y genera un arraylist de generalas
     * @return ArrayList de las partidas leidas
     */
    private ArrayList<Generala> cargarPartidaGenerala()
    {
        ArrayList<Generala> savesGenerala;
        JSONArray jsonArray=Persistencia.levantarArchivo(1);
        savesGenerala=To.toArrayListG(jsonArray);
        return savesGenerala;
    }

    /**
     * lee archivo y genera arraylist de diezMil
     * @return ArrayList de las partidas leidas
     */
    private ArrayList<Diezmil> cargarPartidaDiezMil()
    {
        ArrayList<Diezmil> saves;
        JSONArray jsonArray=Persistencia.levantarArchivo(0);
        saves=To.toArrayListDiezMil(jsonArray);
        return saves;
    }

    /**
     * agrega el icono para barra de tareas
     */
    private void iniciarIcono()
    {
        try
        {
            setIconImage(ImageIO.read(new File("Imagenes/IconoCompleto.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
