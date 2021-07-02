package edu.gui.windows.airport.opera;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.enums.Obj;
import edu.gui.components.DFrame;
import edu.gui.windows.passengers.Asientos;
import edu.maker.ObjectMaker;
import edu.maker.imp.ObjectImp;
import edu.obj.airport.*;

public class CrearAvion extends DFrame implements ItemListener {
    private static final Dimension dimPane = new Dimension(420, 25);
    private static final Dimension dimLbl = new Dimension(220, 25);
    private static final Dimension dimBox = new Dimension(130, 25);
    private static final Dimension dimBtn = new Dimension(160, 30);
    private static final Dimension dimSp = new Dimension(70, 25);

    private JPanel prinPanel;

    private JPanel linPanel;
    private JLabel linLbl;
    private JComboBox<Object> linBox;

    private JPanel aerPanel;
    private JLabel aerLbl;
    private JComboBox<Object> aerBox;

    private JPanel colPanel;
    private JLabel colLbl;
    private JSpinner colSpin;

    private JPanel rowPanel;
    private JLabel rowLbl;
    private JSpinner rowSpin;

    private JPanel pasPanel;
    private JLabel pasLbl;
    private JSpinner pasSpin;

    private JPanel gasPanel;
    private JLabel gasLbl;
    private JSpinner gasSpin;

    private JPanel milPanel;
    private JLabel milLbl;
    private JSpinner milSpin;

    private JPanel btnPanel;
    private JButton preBtn;
    private JButton creBtn;

    public CrearAvion(DFrame frame) {
        super("Crear avion", 450, 500);
        setOpenFrame(frame);
        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new BorderLayout());
        win.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        prinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.getContentPane().add(prinPanel, BorderLayout.CENTER);
        
        linPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        aerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        colPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        pasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        gasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        milPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));

        create(linPanel);
        create(aerPanel);
        create(colPanel);
        create(rowPanel);
        create(pasPanel);
        create(gasPanel);
        create(milPanel);
        
        linLbl = new JLabel();
        aerLbl = new JLabel();
        colLbl = new JLabel();
        rowLbl = new JLabel();
        pasLbl = new JLabel();
        gasLbl = new JLabel();
        milLbl = new JLabel();

        create(linLbl, linPanel, "Aerolinea del Avion:   ");
        create(aerLbl, aerPanel, "Aeropuerto actual:     ");
        create(colLbl, colPanel, "Asientos por Fila:     ");
        create(rowLbl, rowPanel, "Cantidad de Filas:     ");
        create(pasLbl, pasPanel, "Columna de pasillo:    ");
        create(gasLbl, gasPanel, "Capacidad de gasolina: ");
        create(milLbl, milPanel, "Gasolina por milla:    ");
        
        linBox = new JComboBox<>();
        aerBox = new JComboBox<>();
        colSpin = new JSpinner(new SpinnerNumberModel(1, 1, 20000, 1));
        rowSpin = new JSpinner(new SpinnerNumberModel(1, 1, 20000, 1));
        pasSpin = new JSpinner(new SpinnerNumberModel(1, 1, 20000, 1));
        gasSpin = new JSpinner(new SpinnerNumberModel(1, 1, 20000, 1));
        milSpin = new JSpinner(new SpinnerNumberModel(1, 1, 20000, 1));

        linBox.addItemListener(this);

        create(linBox, linPanel, Aerolinea.nombres);
        create(aerBox, aerPanel, null);
        create(colSpin, colPanel);
        create(rowSpin, rowPanel);
        create(pasSpin, pasPanel);
        create(gasSpin, gasPanel);
        create(milSpin, milPanel);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        btnPanel.setBorder(null);
        btnPanel.setPreferredSize(new Dimension(445, 40));
        this.getContentPane().add(btnPanel, BorderLayout.PAGE_END);

        preBtn = new JButton();
        creBtn = new JButton();
        create(preBtn, "Previsualizar");
        create(creBtn, " Crear Avion ");
    }

    private void create(JPanel pan) {
        pan.setPreferredSize(dimPane);
        prinPanel.add(pan);
        prinPanel.add(this.createSeparatorPanel(455, 27));
    }

    private void create(JLabel lbl, JPanel pan, String txt) {
        lbl.setText(txt);
        lbl.setHorizontalTextPosition(SwingConstants.CENTER);
        lbl.setPreferredSize(dimLbl);
        pan.add(lbl);
    }

    private void create(JSpinner sp, JPanel pan) {
        sp.setPreferredSize(dimSp);
        pan.add(sp);
    }

    private void create(JComboBox<Object> sp, JPanel pan, ArrayList<String> items) {
        if (items != null) {
            sp.setModel(new DefaultComboBoxModel<Object>(items.toArray()));
        }
        sp.setPreferredSize(dimBox);
        pan.add(sp);
    }

    private void create(JButton btn, String txt) {
        btn.setText(txt);
        btn.setPreferredSize(dimBtn);
        btn.addActionListener(this);
        btnPanel.add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((Integer) (pasSpin.getValue()) <= (Integer) (colSpin.getValue())) {
            try {
                if (preBtn == e.getSource()) {
                    String aerolinea = (String) (linBox.getSelectedItem());
                    String aeropuerto = (String) (aerBox.getSelectedItem());

                    int cod = Avion.codigos.size() + 1;
                    String codigo = (cod < 10) ? "AV000" + cod
                                            : (cod < 100) ? "AV00" + cod
                                                : (cod < 1000) ? "AV0" + cod : "AV" + cod;

                    int cols = Integer.parseInt(colSpin.getValue().toString()) + 1;
                    int rows = Integer.parseInt(rowSpin.getValue().toString());
                    int pasillo = Integer.parseInt(pasSpin.getValue().toString()) - 1;
                    int gasolina = Integer.parseInt(gasSpin.getValue().toString());
                    int millas = Integer.parseInt(milSpin.getValue().toString());

                    new Asientos(new Avion(aerolinea, aeropuerto, codigo, rows, cols,
                                        pasillo, gasolina, millas), null, false).open();
                    Avion.codigos.remove(codigo);
                    
                } if (creBtn == e.getSource()) {
                    String aerolinea = (String) (linBox.getSelectedItem());
                    String aeropuerto = (String) (aerBox.getSelectedItem());

                    int cod = Avion.codigos.size() + 1;
                    String codigo = null;
                    while (true) {
                        codigo = (cod < 10) ? "AV000" + cod
                                                : (cod < 100) ? "AV00" + cod
                                                    : (cod < 1000) ? "AV0" + cod : "AV" + cod;

                        if (Avion.codigos.contains(codigo)) {cod++;} else {break;}
                    }

                    int cols = Integer.parseInt(colSpin.getValue().toString()) + 1;
                    int rows = Integer.parseInt(rowSpin.getValue().toString());
                    int pasillo = Integer.parseInt(pasSpin.getValue().toString()) - 1;
                    int gasolina = Integer.parseInt(gasSpin.getValue().toString());
                    int millas = Integer.parseInt(milSpin.getValue().toString());
                    
                    new Avion(aerolinea, aeropuerto, codigo, rows, cols, pasillo, gasolina, millas);
                    Avion.codigos.remove(codigo);

                    String[] att = new String[] {
                                        aerolinea, aeropuerto, codigo,
                                        String.valueOf(rows), String.valueOf(cols),
                                        String.valueOf(pasillo), String.valueOf(gasolina),
                                        String.valueOf(millas)
                                    };

                    ObjectMaker.createObj(this, Obj.AVION, att);
                    
                    JOptionPane.showMessageDialog(this, "Creacion de avion terminada, el codigo del "
                                        + "avion es " + codigo + ".",
                                    "Avion creado", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (IndexOutOfBoundsException exe) {
                JOptionPane.showMessageDialog(this, "Hubo un dato erroneo, verifique los datos.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException exe) {
                JOptionPane.showMessageDialog(this, "No se encontro el archivo requerido.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exe) {
                
                JOptionPane.showMessageDialog(this, "Hubo un error inesperado, "
                                        + "verifique sus entradas y vuelva a intentarlo.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            String it = "Aerolinea_" + e.getItem().toString();
            Object[] aer = ((Aerolinea) (ObjectImp.impObj(this, it))).getAeropuertos();
            aerBox.setModel(new DefaultComboBoxModel<Object>(aer));
        } catch (NameNotFoundException exe) {
            JOptionPane.showMessageDialog(this, "No encontramos la aerolinea, revise que exista "
                                    + "el archivo que guarda los datos del mismo.",
                                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
