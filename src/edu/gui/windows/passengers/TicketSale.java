package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import javax.swing.*;
import edu.gui.components.DFrame;
import edu.obj.airport.Avion;

public class TicketSale extends DFrame {
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

        ciudades1Box = new JComboBox<Object>(aeropuertos.toArray());
        ciudades1Box.setPreferredSize(dimComboBox);
        win.add(ciudades1Box);

        win.add(this.createSeparatorPanel(455, 27));
        
        /* Request of the field 'Destination city' */

        destinoLbl = new JLabel("Ciudad de destino:");
        destinoLbl.setPreferredSize(dimLabel);
        win.add(destinoLbl);

        ciudades2Box = new JComboBox<Object>(aeropuertos.toArray());
        ciudades2Box.setPreferredSize(dimComboBox);
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

        pasajerosSpin = new JSpinner(new SpinnerNumberModel(1, 1, 10_000, 1));
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

        aerolineasBox = new JComboBox<Object>(/***/);
        aerolineasBox.setPreferredSize(dimComboBox);
        win.add(aerolineasBox);

        win.add(this.createSeparatorPanel(455, 27));


        /* Field section to select his flight. */

        vueloLbl = new JLabel("Vuelos disponibles:");
        vueloLbl.setPreferredSize(dimLabel);
        win.add(vueloLbl);

        vuelosBox = new JComboBox<Object>(/***/);
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
            this.dispose(false);
            new Asientos(new Avion(null, null, 123, 25, 15, 100, 100, 1)).open();
        }
    }
}
