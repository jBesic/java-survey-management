/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbesic.gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 *
 * @author jasmin
 */
public class JBPanel extends JPanel {

    private final Border border = BorderFactory.createEmptyBorder(5, 5, 30, 5);
    private final Color backgroundColor = new Color(255, 255, 255);
    private final Dimension size = new Dimension(640, 480);
    public GridBagLayout gridBagLayout = new GridBagLayout();
    public GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public JBPanel() {
        super();

        setBorder(border);
        setBackground(backgroundColor);
//        setMaximumSize(size);
//        setPreferredSize(size);
        setLayout(gridBagLayout);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
    }
}
