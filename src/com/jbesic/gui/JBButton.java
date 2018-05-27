/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbesic.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author jasmin
 */
public class JBButton extends JButton {

    private final Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private final Font font = new Font("SanSerif", Font.BOLD, 16);
    private final Color textColor = new Color(255, 255, 255);
    private final Color backgroundColor = new Color(255, 90, 95);
    private final Dimension size = new Dimension(150, 50);

    public JBButton(String buttonLabel) {
        super(buttonLabel);

        setFont(font);
        setBorder(border);
        setBackground(backgroundColor);
        setForeground(textColor);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }
}
