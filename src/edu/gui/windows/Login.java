package edu.gui.windows;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.enums.Obj;
import edu.gui.components.DFrame;
import edu.gui.windows.airport.Select;
import edu.gui.windows.passengers.Datos;
import edu.obj.User;

public class Login extends DFrame implements FocusListener {
    private JButton logearBtn;

    private JPanel userPane;
    private JLabel userLbl;
    private JTextField userTxt;

    private JPanel passPane;
    private JLabel passLbl;
    private JTextField passTxt;

    public Login(DFrame frame) {
        super("Login", 400, 210);
        setOpenFrame(frame);
        FlowLayout frameLyt = (FlowLayout) (this.getContentPane().getLayout());
        frameLyt.setVgap(20);

        Dimension dimPane = new Dimension(400, 30);
        Dimension dimLbl = new Dimension(80, 30);
        Dimension dimTxt = new Dimension(235, 30);

        this.userPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.userPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.userPane);

        this.userLbl = new JLabel("Username");
        this.userLbl.setPreferredSize(dimLbl);
        this.userPane.add(userLbl);

        this.userTxt = new JTextField();
        this.userTxt.setPreferredSize(dimTxt);
        this.userTxt.addFocusListener(this);
        this.userTxt.addActionListener(this);
        this.userPane.add(this.userTxt);

        this.passPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.passPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.passPane);

        this.passLbl = new JLabel("Password");
        this.passLbl.setPreferredSize(dimLbl);
        this.passPane.add(passLbl);

        this.passTxt = new JTextField();
        this.passTxt.setPreferredSize(dimTxt);
        this.passTxt.addFocusListener(this);
        this.passTxt.addActionListener(this);
        this.passPane.add(this.passTxt);

        this.logearBtn = new JButton("Log In");
        this.logearBtn.addActionListener(this);
        this.logearBtn.setEnabled(false);
        this.logearBtn.setPreferredSize(new Dimension(95, 35));
        this.getContentPane().add(this.logearBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logearBtn) {
            Obj obj = User.getUser(userTxt.getText(), passTxt.getText());
            int opcion = -1;
            if (obj != null) {
                this.dispose(false);
                switch (obj) {
                    case PASAJERO:
                        new Datos(userTxt.getText()).open();;
                        break;
                    
                    case ADMINISTRADOR:
                        opcion = 0;
                        break;

                    case GERENTE:
                        opcion = 1;
                        break;

                    case OPERADOR:
                        opcion = 2;
                        break;
                    default:
                        break;
                } if (opcion != -1) new Select(getOpenFrame(), opcion).open();
            } else {
                JOptionPane.showMessageDialog(this, "El usuario o la contraseña son incorrectos, vuelva a intentarlo",
                                        "Identidad no encontrada", JOptionPane.ERROR_MESSAGE);
                logearBtn.setEnabled(false);
            }
        } else {
            logearBtn.setEnabled(true);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            ((JTextField) (e.getSource())).setText("");
            if (userTxt.getText().equals("") && passTxt.getText().equals("")) {
                logearBtn.setEnabled(false);
            } else {
                logearBtn.setEnabled(true);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (userTxt.getText().equals("") || passTxt.getText().equals("")) {
            logearBtn.setEnabled(false);
        } else {
            logearBtn.setEnabled(true);
        }
    }


}
