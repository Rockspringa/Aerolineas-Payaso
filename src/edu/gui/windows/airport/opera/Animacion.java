package edu.gui.windows.airport.opera;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.gui.components.DFrame;
import edu.gui.files.Icon;

public class Animacion extends DFrame {

    private final boolean exitoso;

    private JPanel pane;
    private JLabel[] avion;
    private JLabel ciudad2Image;

    private static final Dimension IMG_DIM = new Dimension(100, 100);
    private static final Dimension TXT_DIM = new Dimension(100, 35 );

    public Animacion(boolean exitoso, String ciudad1, String ciudad2) {
        super((exitoso) ? "Vuelo completado" : "Vuelo fallido", 380, 330);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setOpenFrame(null);

        this.exitoso = exitoso;

        pane = new JPanel(new GridLayout(2, 3, 0, 0));
        pane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.getContentPane().add(pane);

        avion = new JLabel[3];
        this.createAvionLabels();
        this.createCityPanel(ciudad1);
        pane.add(this.createEmptyLabel());
        ciudad2Image = createCityPanel(ciudad2);
    }

    private JLabel createCityPanel(String s) {
        JPanel pane = new JPanel(new BorderLayout(0, 0));
        this.pane.add(pane);

        JLabel img = new JLabel(Icon.CIUDAD_1.getIcon());
        img.setPreferredSize(IMG_DIM);
        pane.add(img, BorderLayout.CENTER);

        JLabel txt = new JLabel(s);
        txt.setPreferredSize(TXT_DIM);
        pane.add(txt, BorderLayout.PAGE_END);

        return img;
    }

    private void createAvionLabels() {
        for (int i = 0; i < 3; i++) {
            avion[i] = this.createEmptyLabel();
            this.pane.add(avion[i]);
        }
    }

    private JLabel createEmptyLabel() {
        JLabel lbl = new JLabel(Icon.VACIO.getIcon(50, 50));
        lbl.setBorder(BorderFactory.createEmptyBorder(85, 25, 0, 25));
        lbl.setPreferredSize(IMG_DIM);
        return lbl;
    }

    private void animar() {
        try {
            this.setCharging(true);
            for (int i = 0; i < 3; i++) cambiarAvion(i);
            this.setCharging(false);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(this, "Hubo un error animando, pero el"
                                + " vuelo fue " + ((exitoso) ? "exitoso." : "fallido."),
                                "Error Desconocido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cambiarAvion(int i) throws InterruptedException {
        ImageIcon icon = null;
        if (i == 0) icon = Icon.AVION_1.getIcon();
        else if (i == 1) icon = Icon.AVION_2.getIcon();
        else icon = Icon.AVION_3.getIcon();

        avion[i].setIcon(icon);
        Thread.sleep(1000);
        avion[i].setIcon(Icon.VACIO.getIcon());

        if (i == 3 && !exitoso) ciudad2Image.setIcon(Icon.CIUDAD_2.getIcon());
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void dispose() {
        this.setCharging(false);
        super.dispose();
    }

    @Override
    public void open() {
        super.open();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {}
                animar();
            };
        }.start();
    }
}
