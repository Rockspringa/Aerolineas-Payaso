package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import edu.gui.components.DFrame;
import edu.main.Init;

public class TicketSale extends DFrame {
    private JLabel origenLbl;
    private JComboBox<Object> ciudades1Spin;
    
    private JLabel destinoLbl;
    private JComboBox<Object> ciudades2Spin;
    
    private JLabel fechaLbl;
    private JPanel fechaPane;
    private JSpinner diasSpin;
    private JLabel diaLbl;
    private JSpinner mesesSpin;
    private JLabel mesLbl;
    private JSpinner añosSpin;
    private JLabel añoLbl;

    public TicketSale(DFrame Frame) {
        super("Tickets Sale", 480, 400);
        JPanel win = (JPanel) (this.getContentPane());
        win.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        this.setOpenFrame(Frame);
        Dimension dimComboBox = new Dimension(230, 25);
        Dimension dimLabel = new Dimension(190, 25);
        
        origenLbl = new JLabel("Ciudad de origen:");
        origenLbl.setPreferredSize(dimLabel);
        win.add(origenLbl);

        ciudades1Spin = new JComboBox<Object>(Init.ciudades.toArray());
        ciudades1Spin.setPreferredSize(dimComboBox);
        win.add(ciudades1Spin);
        
        destinoLbl = new JLabel("Ciudad de destino:");
        destinoLbl.setPreferredSize(dimLabel);
        win.add(destinoLbl);

        ciudades2Spin = new JComboBox<Object>(Init.ciudades.toArray());
        ciudades2Spin.setPreferredSize(dimComboBox);
        win.add(ciudades2Spin);
        
        fechaLbl = new JLabel("Fecha de vuelo:");
        fechaLbl.setPreferredSize(dimLabel);
        win.add(fechaLbl);

        fechaPane = new JPanel();
        fechaPane.setPreferredSize(dimComboBox);
        win.add(fechaPane);

        diasSpin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
