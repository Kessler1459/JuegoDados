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

    public MenuPrincipal(String titulo) {
        super(titulo);
        this.setContentPane(jPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setResizable(false);


        generalaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                    //preguntar excepcion campos vacios
                String jugador1 = fieldJugador1.getText().toString().trim();
                String jugador2 = fieldJugador2.getText().toString().trim();
                String jugador3 = fieldJugador3.getText().toString().trim();
                String jugador4 = fieldJugador4.getText().toString().trim();
                ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
                jugadores.add(new Jugador(jugador1));
                jugadores.add(new Jugador(jugador2));
                if (fieldJugador3.isEnabled())
                    jugadores.add(new Jugador(jugador3));
                if (fieldJugador4.isEnabled())
                    jugadores.add(new Jugador(jugador4));
                Generala generala = new Generala(jugadores);
                MenuGenerala menuGenerala = new MenuGenerala(titulo, generala);
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
    }

    public static void main(String[] args) {
        MenuPrincipal frame = new MenuPrincipal("titulo");
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());
        jPanel.setPreferredSize(new Dimension(800, 400));
        lJugador1 = new JLabel();
        Font lJugador1Font = this.$$$getFont$$$(null, -1, 16, lJugador1.getFont());
        if (lJugador1Font != null) lJugador1.setFont(lJugador1Font);
        lJugador1.setText("Jugador 1");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        jPanel.add(lJugador1, gbc);
        lJugador2 = new JLabel();
        Font lJugador2Font = this.$$$getFont$$$(null, -1, 16, lJugador2.getFont());
        if (lJugador2Font != null) lJugador2.setFont(lJugador2Font);
        lJugador2.setText("Jugador 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        jPanel.add(lJugador2, gbc);
        fieldJugador1 = new JTextField();
        fieldJugador1.setMargin(new Insets(2, 6, 2, 6));
        fieldJugador1.setMaximumSize(new Dimension(180, 32));
        fieldJugador1.setMinimumSize(new Dimension(180, 32));
        fieldJugador1.setPreferredSize(new Dimension(180, 32));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        gbc.insets = new Insets(0, 5, 0, 0);
        jPanel.add(fieldJugador1, gbc);
        fieldJugador3 = new JTextField();
        fieldJugador3.setEnabled(false);
        fieldJugador3.setMargin(new Insets(2, 6, 2, 6));
        fieldJugador3.setMaximumSize(new Dimension(180, 32));
        fieldJugador3.setMinimumSize(new Dimension(180, 32));
        fieldJugador3.setPreferredSize(new Dimension(180, 32));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        jPanel.add(fieldJugador3, gbc);
        fieldJugador4 = new JTextField();
        fieldJugador4.setEnabled(false);
        fieldJugador4.setMargin(new Insets(2, 6, 2, 6));
        fieldJugador4.setMaximumSize(new Dimension(180, 32));
        fieldJugador4.setMinimumSize(new Dimension(180, 32));
        fieldJugador4.setPreferredSize(new Dimension(180, 32));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        jPanel.add(fieldJugador4, gbc);
        generalaButton = new JButton();
        Font generalaButtonFont = this.$$$getFont$$$(null, -1, 18, generalaButton.getFont());
        if (generalaButtonFont != null) generalaButton.setFont(generalaButtonFont);
        generalaButton.setMaximumSize(new Dimension(170, 40));
        generalaButton.setMinimumSize(new Dimension(170, 40));
        generalaButton.setPreferredSize(new Dimension(170, 40));
        generalaButton.setText("Generala");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        jPanel.add(generalaButton, gbc);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText(" Titulo");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        jPanel.add(label1, gbc);
        fieldJugador2 = new JTextField();
        fieldJugador2.setMargin(new Insets(2, 6, 2, 6));
        fieldJugador2.setMaximumSize(new Dimension(180, 32));
        fieldJugador2.setMinimumSize(new Dimension(180, 32));
        fieldJugador2.setPreferredSize(new Dimension(180, 32));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        jPanel.add(fieldJugador2, gbc);
        diezMilButton = new JButton();
        Font diezMilButtonFont = this.$$$getFont$$$(null, -1, 18, diezMilButton.getFont());
        if (diezMilButtonFont != null) diezMilButton.setFont(diezMilButtonFont);
        diezMilButton.setMaximumSize(new Dimension(170, 40));
        diezMilButton.setMinimumSize(new Dimension(170, 40));
        diezMilButton.setPreferredSize(new Dimension(170, 40));
        diezMilButton.setText("Diez Mil");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        jPanel.add(diezMilButton, gbc);
        lJugador4 = new JLabel();
        Font lJugador4Font = this.$$$getFont$$$(null, -1, 16, lJugador4.getFont());
        if (lJugador4Font != null) lJugador4.setFont(lJugador4Font);
        lJugador4.setText("Jugador 4");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        jPanel.add(lJugador4, gbc);
        lJugador3 = new JLabel();
        lJugador3.setAlignmentY(0.5f);
        Font lJugador3Font = this.$$$getFont$$$(null, -1, 16, lJugador3.getFont());
        if (lJugador3Font != null) lJugador3.setFont(lJugador3Font);
        lJugador3.setText("Jugador 3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        jPanel.add(lJugador3, gbc);
        checkJugador3 = new JCheckBox();
        checkJugador3.setBorderPainted(false);
        checkJugador3.setHorizontalAlignment(0);
        checkJugador3.setMargin(new Insets(20, 2, 0, 2));
        checkJugador3.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        jPanel.add(checkJugador3, gbc);
        checkJugador4 = new JCheckBox();
        checkJugador4.setMargin(new Insets(20, 0, 0, 0));
        checkJugador4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        jPanel.add(checkJugador4, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jPanel;
    }
}
