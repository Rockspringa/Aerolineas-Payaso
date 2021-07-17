package edu.gui.windows.airport.admin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.gui.components.DFrame;
import edu.obj.Aeropuerto;
import edu.obj.airport.Aerolinea;
import edu.obj.users.Admin;
import edu.obj.users.Gerente;
import edu.obj.users.Operador;

public class CrearEmp extends DFrame implements FocusListener {
    private String emp;

    private JButton logearBtn;

    private JPanel userPane;
    private JLabel userLbl;
    private JTextField userTxt;

    private JPanel passPane;
    private JLabel passLbl;
    private JTextField passTxt;

    private JPanel repPassPane;
    private JLabel repPassLbl;
    private JTextField repPassTxt;

    private JPanel aerPane;
    private JLabel aerLbl;
    private JComboBox<Object> aerBox;

    public CrearEmp(DFrame frame, String empleado) {
        super("Crear " + empleado, 500, (empleado == "admin") ? 260 : 330);
        setOpenFrame(frame);
        emp = empleado.toLowerCase();
        FlowLayout frameLyt = (FlowLayout) (this.getContentPane().getLayout());
        frameLyt.setVgap(20);

        Dimension dimPane = new Dimension(500, 30);
        Dimension dimLbl = new Dimension(190, 30);
        Dimension dimTxt = new Dimension(235, 30);

        this.userPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.userPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.userPane);

        this.userLbl = new JLabel("Nombre del Empleado");
        this.userLbl.setPreferredSize(dimLbl);
        this.userPane.add(userLbl);

        this.userTxt = new JTextField();
        this.userTxt.setPreferredSize(dimTxt);
        this.userTxt.addFocusListener(this);
        this.userPane.add(this.userTxt);

        this.passPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.passPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.passPane);

        this.passLbl = new JLabel("Crear Contraseña");
        this.passLbl.setPreferredSize(dimLbl);
        this.passPane.add(passLbl);

        this.passTxt = new JTextField();
        this.passTxt.setPreferredSize(dimTxt);
        this.passTxt.addFocusListener(this);
        this.passPane.add(this.passTxt);

        this.repPassPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.repPassPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.repPassPane);

        this.repPassLbl = new JLabel("Repita la Contraseña");
        this.repPassLbl.setPreferredSize(dimLbl);
        this.repPassPane.add(repPassLbl);

        this.repPassTxt = new JTextField();
        this.repPassTxt.setPreferredSize(dimTxt);
        this.repPassTxt.addFocusListener(this);
        this.repPassPane.add(this.repPassTxt);

        this.aerPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.aerPane.setPreferredSize(dimPane);
        this.getContentPane().add(this.aerPane);

        this.aerLbl = new JLabel("Escoja " + ((empleado == "geren") ? "la aerolinea" : "el aeropuerto"));
        this.aerLbl.setPreferredSize(dimLbl);
        this.aerPane.add(aerLbl);

        this.aerBox = new JComboBox<>((empleado == "geren")
                                ? Aerolinea.nombres.toArray()
                                : Aeropuerto.nombres.toArray());
        this.aerBox.setPreferredSize(dimTxt);
        this.aerBox.setSelectedItem("- Seleccione uno -");
        this.aerPane.add(this.aerBox);

        this.logearBtn = new JButton("Log In");
        this.logearBtn.addActionListener(this);
        this.logearBtn.setEnabled(false);
        this.logearBtn.setPreferredSize(new Dimension(95, 35));
        this.getContentPane().add(this.logearBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (userTxt.getText().equals("") || passTxt.getText().equals("") || repPassTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Llene los campos, por favor.", "", JOptionPane.WARNING_MESSAGE);
        } else if (!passTxt.getText().equals(repPassTxt.getText())) {
            JOptionPane.showMessageDialog(this, "Las dos contraseñas no coinciden.", "", JOptionPane.WARNING_MESSAGE);
        } else {
            switch (emp) {
                case "admin":
                    new Admin(userTxt.getText(), repPassTxt.getText());
                    break;
                case "geren":
                    new Gerente(userTxt.getText(), repPassTxt.getText(), aerBox.getSelectedItem().toString());
                    break;
                case "opera":
                    new Operador(userTxt.getText(), repPassTxt.getText(), aerBox.getSelectedItem().toString());
                    break;
                default:
                    break;
            } this.dispose();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof JTextField) {
            ((JTextField) (e.getSource())).setText("");
            logearBtn.setEnabled(true);
            if (userTxt.getText().equals("") || passTxt.getText().equals("") && repPassTxt.getText().equals("")) {
                logearBtn.setEnabled(false);
            } else {
                logearBtn.setEnabled(true);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (userTxt.getText().equals("") || passTxt.getText().equals("") || repPassTxt.getText().equals("")) {
            logearBtn.setEnabled(false);
        } else if (!passTxt.getText().equals(repPassTxt.getText())) {
            logearBtn.setEnabled(false);
        } else {
            logearBtn.setEnabled(true);
        }
    }


}
