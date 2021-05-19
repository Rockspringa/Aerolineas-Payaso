package edu.gui.windows.airport.opera;

import java.awt.*;
import java.awt.event.*;

import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.gui.components.DFrame;
import edu.gui.windows.passengers.Asientos;
import edu.maker.imp.ObjectImp;
import edu.obj.airport.Avion;

public class Aviones extends DFrame {
    private JLabel avionesLbl;
    private JButton lstBtn;
    private JComboBox<Object> avionesCom;

    public Aviones(DFrame f) {
        super("Seleccione el avion a visualizar", 450, 200);
        setOpenFrame(f);
        ((JPanel) (getContentPane())).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));;

        Dimension dim = new Dimension(200, 50);

        avionesLbl = new JLabel("Selecciones un avion");
        avionesLbl.setPreferredSize(dim);
        getContentPane().add(avionesLbl);

        avionesCom = new JComboBox<Object>(Avion.codigos.toArray());
        avionesCom.setPreferredSize(dim);
        getContentPane().add(avionesCom);

        getContentPane().add(createSeparatorPanel(440, 27));

        lstBtn = new JButton("Listo");
        lstBtn.addActionListener(this);
        avionesLbl.setPreferredSize(new Dimension(100, 50));
        getContentPane().add(lstBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!avionesCom.getSelectedItem().equals("")) {
            try {
                new Asientos((Avion) (ObjectImp.impObj(null, "Avion_"
                        + avionesCom.getSelectedItem().toString())), null, false).open();;
            } catch (NameNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "No se ha encontrado el archivo",
                                                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
