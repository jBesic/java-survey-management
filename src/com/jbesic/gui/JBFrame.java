package com.jbesic.gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jbesic.Main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class JBFrame extends JFrame {

    JBFrame(String windowTitle) {
        super(windowTitle);
        
        InputStream windowIconStream = Main.class.getResourceAsStream("images/survey-icon.png");
        try {
			setIconImage(ImageIO.read(windowIconStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        setSize(600, 400);
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public Component add(Component arg0) {
    	this.removeAll();
    	this.revalidate();
    	
    	super.add(arg0);
    	
    	this.setVisible(true);
    	return arg0;
    }
}
