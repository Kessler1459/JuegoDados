package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JLabelDado extends JLabel{

    private MouseListener listener;

    public JLabelDado() {
        super();
        this.setMinimumSize(new Dimension(105,105));
        instanciarListener();
        this.addMouseListener(listener);
    }

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
