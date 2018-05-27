package com.jbesic.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JBAdminQuestion extends JPanel {

    public JBAdminQuestion() {
        super();

        setLayout(new GridLayout(2, 3));
        setPreferredSize(new Dimension(640, 480));

        add(new JButton("JBAdminQuestion"));
    }
}
