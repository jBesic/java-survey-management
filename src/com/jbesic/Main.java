package com.jbesic;

import com.jbesic.gui.JBFrame;
import com.jbesic.gui.JBLanding;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JBApplication application = new JBApplication();
        JFrame frame = JBFrame.getFrame();
        frame.add(new JBLanding()).revalidate();
    }
}
