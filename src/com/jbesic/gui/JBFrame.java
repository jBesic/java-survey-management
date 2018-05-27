package com.jbesic.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class JBFrame extends JFrame {

    private JBFrame() {
        super();

        setTitle("Survey");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        pack();
        setVisible(true);
    }

    @Override
    public Component add(Component comp) {
        Container contentPane = getContentPane();
        contentPane.removeAll();
        contentPane.add(comp);
        contentPane.revalidate();
        contentPane.repaint();
        return super.add(comp);
    }

    static JBFrame frame;

    public static JFrame getFrame() {

        if (!(JBFrame.frame instanceof JFrame)) {
            JBFrame.frame = new JBFrame();
        }

        return JBFrame.frame;
    }
}
