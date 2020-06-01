package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Jlabel para las imagenes clickeables de los dados
 */
public class JLabelDado extends JLabel{

    private MouseListener listener;

    public JLabelDado() {
        super();
        this.setMinimumSize(new Dimension(105,105));
        instanciarListener();
        this.addMouseListener(listener);
    }

    /**
     * inicia el listener para hacer el Jlabel clickeable
     */
    private void instanciarListener()
    {
        listener=new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isEnabled())
                {
                    setEnabled(true);
                }
                else
                {
                    setEnabled(false);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }
}
