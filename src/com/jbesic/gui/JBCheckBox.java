/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbesic.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.border.Border;

/**
 *
 * @author jasmin
 */
public class JBCheckBox extends JCheckBox {

    private final Border border = BorderFactory.createLineBorder(new Color(250, 250, 250), 15);
    private final Font font = new Font("SanSerif", Font.PLAIN, 16);
    private final Color textColor = new Color(0, 0, 0);
    private final Color backgroundColor = new Color(250, 250, 250);

    public JBCheckBox(String text) {
        super(text);

        setFont(font);
        setBorder(border);
        setBackground(backgroundColor);
        setForeground(textColor);
    }
}
