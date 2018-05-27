/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbesic.gui;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.Dimension;

/**
 *
 * @author jasmin
 */
public class JBScrollPane extends JScrollPane {

    private final Border border = BorderFactory.createEmptyBorder(2, 2, 2, 2);
    private final Dimension size = new Dimension(300, 400);

    public JBScrollPane(Component component) {
        super(component);

        setBorder(border);
        setMinimumSize(size);
        setMaximumSize(size);
    }
}
