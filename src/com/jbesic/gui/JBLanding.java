package com.jbesic.gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class JBLanding extends JBPanel {

    public JBLanding() {
        super();

        JButton admin = new JBButton("Admin");
        JButton regular = new JBButton("Regular");

        admin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JBFrame.getFrame().add(new JBAdminLanding());
            }
        });

        regular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JBFrame.getFrame().add(new JBRegularLanding());
            }
        });

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        add(admin, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        add(regular, gridBagConstraints);
    }
}
