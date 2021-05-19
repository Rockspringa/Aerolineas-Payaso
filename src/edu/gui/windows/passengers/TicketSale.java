package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.gui.components.DFrame;
import edu.maker.imp.ObjectImp;
import edu.obj.Aeropuerto;
import edu.obj.airport.*;

public class TicketSale extends DFrame implements ItemListener {
    private JLabel origenLbl;
    private JComboBox<Object> ciudades1Box;
    
    private JLabel destinoLbl;
    private JComboBox<Object> ciudades2Box;

    private JLabel pasajerosLlb;
    private JPanel pasajerosPane;
    private JSpinner pasajerosSpin;
    
    private LocalDate today;
    private JLabel fechaLbl;
    private JPanel fechaPane;
    private JSpinner diasSpin;
    private JSpinner mesesSpin;
    private JSpinner añosSpin;

    private JLabel aerolineaLbl;
    private JComboBox<Object> aerolineasBox;

    private JLabel vueloLbl;
    private JComboBox<Object> vuelosBox;

    private JPanel botonPanel;
    private JButton comprarBtn;

    public TicketSale() {
        super("Tickets Sale", 485, 520);
        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        win.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

        Dimension dimComboBox = new Dimension(230, 25);
        Dimension dimLabel = new Dimension(220, 25);
        
        /* Request of field 'Hometown' */

        origenLbl = new JLabel("Ciudad de origen:");
        origenLbl.setPreferredSize(dimLabel);
        win.add(origenLbl);

        ciudades1Box = new JComboBox<Object>(Aeropuerto.ciudades.values().toArray());
        ciudades1Box.setPreferredSize(dimComboBox);
        ciudades1Box.addItemListener(this);
        win.add(ciudades1Box);

        win.add(this.createSeparatorPanel(455, 27));
        
        /* Request of the field 'Destination city' */

        destinoLbl = new JLabel("Ciudad de destino:");
        destinoLbl.setPreferredSize(dimLabel);
        win.add(destinoLbl);

        ciudades2Box = new JComboBox<Object>(Aeropuerto.ciudades.values().toArray());
        ciudades2Box.setPreferredSize(dimComboBox);
        ciudades2Box.addItemListener(this);
        win.add(ciudades2Box);

        win.add(this.createSeparatorPanel(455, 27));

        /* Request for known the number of passengers */

        pasajerosLlb = new JLabel("Cantidad de Pasajeros:");
        pasajerosLlb.setPreferredSize(dimLabel);
        win.add(pasajerosLlb);

        pasajerosPane = new JPanel();
        pasajerosPane.setPreferredSize(dimComboBox);
        pasajerosPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pasajerosPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        pasajerosPane.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(DFrame.SHADOW_BLUE), 
                                BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(new Color(122, 138, 153)),
                                    BorderFactory.createLineBorder(new Color(184, 207, 229))
                                )
                            ));
        win.add(pasajerosPane);

        pasajerosSpin = new JSpinner(new SpinnerNumberModel(1, 1, 1, 1));
        pasajerosPane.add(pasajerosSpin);

        win.add(this.createSeparatorPanel(455, 27));
        
        /* Request of the wanted 'Flight date' */

        fechaLbl = new JLabel("Fecha de vuelo:");
        fechaLbl.setPreferredSize(dimLabel);
        win.add(fechaLbl);

        fechaPane = new JPanel();
        fechaPane.setAlignmentY(JPanel.TOP_ALIGNMENT);
        fechaPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        fechaPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        fechaPane.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(DFrame.SHADOW_BLUE), 
                                BorderFactory.createCompoundBorder(
                                    BorderFactory.createLineBorder(new Color(122, 138, 153)),
                                    BorderFactory.createLineBorder(new Color(184, 207, 229))
                                )
                            ));
        fechaPane.setPreferredSize(dimComboBox);
        win.add(fechaPane);

        today = LocalDate.now();

        diasSpin = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        fechaPane.add(diasSpin);
        fechaPane.add(new JLabel(" / "));

        mesesSpin = new JSpinner(new SpinnerNumberModel(today.getMonthValue(), 1, 12, 1));
        fechaPane.add(mesesSpin);
        fechaPane.add(new JLabel(" / "));

        añosSpin = new JSpinner(new SpinnerNumberModel(today.getYear(), today.getYear(), 99_999, 1));
        fechaPane.add(añosSpin);
        fechaPane.add(Box.createHorizontalStrut(10));

        win.add(this.createSeparatorPanel(455, 27));

        /* Field section to put his preferred airline. */

        aerolineaLbl = new JLabel("Aerolinea:");
        aerolineaLbl.setPreferredSize(dimLabel);
        win.add(aerolineaLbl);

        aerolineasBox = new JComboBox<Object>();
        aerolineasBox.setPreferredSize(dimComboBox);
        aerolineasBox.addItemListener(this);
        win.add(aerolineasBox);

        win.add(this.createSeparatorPanel(455, 27));


        /* Field section to select his flight. */

        vueloLbl = new JLabel("Vuelos disponibles:");
        vueloLbl.setPreferredSize(dimLabel);
        win.add(vueloLbl);

        vuelosBox = new JComboBox<Object>();
        vuelosBox.setPreferredSize(dimComboBox);
        win.add(vuelosBox);

        win.add(this.createSeparatorPanel(455, 27));

        /* The end of the page, here contains a panel with a button, aesthetics purpousses, for create a ticket. */

        botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        botonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        botonPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        botonPanel.setPreferredSize(new Dimension(455, 40));
        win.add(botonPanel);

        comprarBtn = new JButton("Comprar Boleto");
        comprarBtn.addActionListener(this);
        botonPanel.add(comprarBtn);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (true) {
            Vuelo vu = null;
            Avion av = null;
            Aerolinea aer = null;
            try {
                aer = (Aerolinea) (ObjectImp.impObj(this, "Aerolinea_" +
                        aerolineasBox.getSelectedItem() + "_" + ciudades1Box.getSelectedItem()));
                vu = (Vuelo) (ObjectImp.impObj(this, "Vuelo_" +
                        aer.getCodigos().get(aer.getVuelos().indexOf((LocalDate) (vuelosBox.getSelectedItem())))
                        ));
                av = (Avion) (ObjectImp.impObj(this, "Avion_" + vu.getAvion()));
            } catch (NameNotFoundException ex) {}
            this.dispose(false);
            new Asientos(av, vu, true).open();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == ciudades1Box) {
            Aeropuerto aer = null;
            try {aer = (Aeropuerto) (ObjectImp.impObj(this, "Aeropuerto_" +
                    Aeropuerto.ciuInv.get(ciudades1Box.getSelectedItem().toString())));
            } catch (NameNotFoundException ex) {}
            if (aer != null) {
                aerolineasBox.removeAllItems();
                for (int x = 0; x < aer.getAerolineas().size(); x++)
                    aerolineasBox.addItem(aer.getAerolineas().get(x));
            }
        } if (e.getSource() == aerolineasBox) {
            Aerolinea aer = null;
            try {aer = (Aerolinea) (ObjectImp.impObj(this, "Aerolinea_"
                    + aerolineasBox.getSelectedItem() + "_"
                    + Aeropuerto.ciuInv.get(ciudades1Box.getSelectedItem().toString())));
            } catch (NameNotFoundException ex) {}
            if (aer != null) {
                vuelosBox.removeAllItems();
                for (int x = 0; x < aer.getVuelos().size(); x++) {
                    if (aer.getVuelos().get(x).isAfter(LocalDate.now())) {
                        vuelosBox.addItem(aer.getVuelos().get(x));
                    }
                }
            }
        }
    }
}
