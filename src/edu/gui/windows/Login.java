package edu.gui.windows;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.gui.components.DFrame;

public class Login extends DFrame {
    private JButton logearBtn;

    private JPanel userPane;
    private JLabel userLbl;
    private JTextField userTxt;

    private JPanel passPane;
    private JLabel passLbl;
    private JTextField passTxt;

    public Login() {
        super("Login", 400, 210);
        FlowLayout frameLyt = (FlowLayout) (this.getContentPane().getLayout());
        frameLyt.setVgap(20);

        this.userPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.userPane.setPreferredSize(new Dimension(400, 30));
        this.getContentPane().add(this.userPane);

        this.userLbl = new JLabel("Username");
        this.userLbl.setPreferredSize(new Dimension(80, 30));
        this.userPane.add(userLbl);

        this.userTxt = new JTextField();
        this.userTxt.setPreferredSize(new Dimension(235, 30));
        this.userPane.add(this.userTxt);

        this.passPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.passPane.setPreferredSize(new Dimension(400, 30));
        this.getContentPane().add(this.passPane);

        this.passLbl = new JLabel("Password");
        this.passLbl.setPreferredSize(new Dimension(80, 30));
        this.passPane.add(passLbl);

        this.passTxt = new JTextField();
        this.passTxt.setPreferredSize(new Dimension(235, 30));
        this.passPane.add(this.passTxt);

        this.logearBtn = new JButton("Log In");
        this.logearBtn.addActionListener(this);
        this.logearBtn.setPreferredSize(new Dimension(95, 35));
        this.getContentPane().add(this.logearBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
