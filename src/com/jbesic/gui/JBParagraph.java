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
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author jasmin
 */
public class JBParagraph extends JTextArea {

    private final Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    private final Font font = new Font("SanSerif", Font.PLAIN, 16);
    private final Color textColor = new Color(0, 0, 0);
    private final Color backgroundColor = new Color(255, 255, 255);
    private final Dimension size = new Dimension(150, 50);

    public JBParagraph(String text) {
        super(text);

        setFont(font);
        setBorder(border);
        setBackground(backgroundColor);
        setForeground(textColor);
//        setMinimumSize(size);
//        setPreferredSize(size);
        setEditable(false);
        setFocusable(false);
        setLineWrap(true);
        setWrapStyleWord(true);
    }
}
