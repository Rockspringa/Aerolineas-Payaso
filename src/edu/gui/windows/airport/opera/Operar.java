package edu.gui.windows.airport.opera;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.*;
import java.time.format.*;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.border.Border;

import edu.gui.components.DFrame;
import edu.maker.exp.ObjectExp;
import edu.maker.imp.ObjectImp;
import edu.obj.Aeropuerto;
import edu.obj.airport.*;
import edu.obj.users.*;

public class Operar extends DFrame {

    private static final Color GREEN  = new Color(45 , 175, 90);
    private static final Color RED    = new Color(180, 45 , 65);
    private static final Color YELLOW = new Color(225, 190, 70);
    private static final Color ORANGE = new Color(220, 120, 45);

    private static final Border BORD = BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(DFrame.AQUA, 2),
                            BorderFactory.createEmptyBorder(2, 2, 2, 2)
                            );

    private static final Border HEAD = BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(0, 0, 3, 1, DFrame.WHITE),
                            BorderFactory.createEmptyBorder(2, 2, 1, 2)
                            );

    private Vuelo[] vuelos;
    private int index;

    private JScrollPane pane;
    private JPanel gridPane;

    private JLabel[] codeLbls;
    private JLabel[] origenLbls;
    private JLabel[] destinoLbls;
    private JLabel[] stateLbls;
    private JLabel[] fechaLbls;

    private JMenuItem[] optionItems;
    private JPopupMenu menu;
    private JButton[] optionBtn;

    private String[] items = { "Iniciar", "Cancelar", "Posponer" };

    public Operar(DFrame frame) {
        super("Operando de vuelos", 1200, 600);
        this.setOpenFrame(frame);
        this.getContentPane().setLayout(new BorderLayout());

        Object[] aerVuelos = null;
        try {
            aerVuelos = ((Aeropuerto) ObjectImp.impObj(this, "Aeropuerto_"
                            + ((Trabajador) getUser()).getAeropuerto())).getVuelos();
        } catch (NameNotFoundException e1) {}

        // getting all the instances of 'vuelos' of the airport managed
        int size = aerVuelos.length + 1;
        setSize(900, size * 35 + 85);
        if (size == 0) {
            JOptionPane.showMessageDialog(this, "El aeropuerto escogido no contiene vuelos",
                                    "Info", JOptionPane.INFORMATION_MESSAGE); 
        }

        vuelos = new Vuelo[size - 1];
        for (int i = 0; i < size - 1; i++) {
            try {
                vuelos[i] = (Vuelo) (ObjectImp.impObj(this, "Vuelo_" + aerVuelos[i].toString()));
            } catch (NameNotFoundException e) {}
        }

        // Creating the JPanel that contains everything
        gridPane = new JPanel(new GridLayout(size, 6, 5, 5));
        gridPane.setPreferredSize(new Dimension(650, size * 35));

        pane = new JScrollPane(gridPane);
        this.getContentPane().add(pane, BorderLayout.CENTER);

        // creating all the components
        codeLbls = new JLabel[size];
        origenLbls = new JLabel[size];
        destinoLbls = new JLabel[size];
        stateLbls = new JLabel[size];
        fechaLbls = new JLabel[size];

        optionBtn = new JButton[size];
        optionItems = new JMenuItem[3];

        for (int i = 0; i < size; i++) {
            if (i != 0) createRowComponents(i - 1);
            else createHeader();
        }

        menu = new JPopupMenu();
        for (int i = 0; i < 3; i++) {
            optionItems[i] = new JMenuItem(items[i]);
            optionItems[i].addActionListener(this);
            menu.add(optionItems[i]);
        }
    }

    private void createHeader() {
        codeLbls[0] = new JLabel("Vuelo");
        codeLbls[0].setFont(DFrame.JBRAINS_BOLD);
        codeLbls[0].setAlignmentX(JLabel.CENTER_ALIGNMENT);
        codeLbls[0].setBorder(HEAD);
        gridPane.add(codeLbls[0]);

        origenLbls[0] = new JLabel("Origen");
        origenLbls[0].setFont(DFrame.JBRAINS_BOLD);
        origenLbls[0].setAlignmentX(JLabel.CENTER_ALIGNMENT);
        origenLbls[0].setBorder(HEAD);
        gridPane.add(origenLbls[0]);

        destinoLbls[0] = new JLabel("Destino");
        destinoLbls[0].setFont(DFrame.JBRAINS_BOLD);
        destinoLbls[0].setAlignmentX(JLabel.CENTER_ALIGNMENT);
        destinoLbls[0].setBorder(HEAD);
        gridPane.add(destinoLbls[0]);

        stateLbls[0] = new JLabel("Estado");
        stateLbls[0].setFont(DFrame.JBRAINS_BOLD);
        stateLbls[0].setAlignmentX(JLabel.CENTER_ALIGNMENT);
        stateLbls[0].setBorder(HEAD);
        gridPane.add(stateLbls[0]);

        fechaLbls[0] = new JLabel("Fecha Salida");
        fechaLbls[0].setFont(DFrame.JBRAINS_BOLD);
        fechaLbls[0].setAlignmentX(JLabel.CENTER_ALIGNMENT);
        fechaLbls[0].setBorder(HEAD);
        gridPane.add(fechaLbls[0]);

        optionBtn[0] = new JButton("  Opciones  ", 0);
        optionBtn[0].setFont(DFrame.JBRAINS_BOLD);
        optionBtn[0].setBorder(HEAD);
        optionBtn[0].setBackground(DFrame.BACKG_COLOR);
        optionBtn[0].setForeground(DFrame.WHITE);
        gridPane.add(optionBtn[0]);
    }

    private void createRowComponents(int i) {
        codeLbls[i] = new JLabel(vuelos[i].getCodigo());
        codeLbls[i].setBorder(BORD);
        gridPane.add(codeLbls[i]);

        origenLbls[i] = new JLabel(vuelos[i].getOrigen());
        origenLbls[i].setBorder(BORD);
        gridPane.add(origenLbls[i]);

        destinoLbls[i] = new JLabel(vuelos[i].getDestino());
        destinoLbls[i].setBorder(BORD);
        gridPane.add(destinoLbls[i]);

        stateLbls[i] = new JLabel("En espera");
        stateLbls[i].setBorder(BORD);
        stateLbls[i].setForeground(YELLOW);
        gridPane.add(stateLbls[i]);

        fechaLbls[i] = new JLabel(vuelos[i].getFechaVuelo().toString());
        fechaLbls[i].setBorder(BORD);
        gridPane.add(fechaLbls[i]);

        optionBtn[i] = new JButton("- Opciones -", i);
        optionBtn[i].setBorder(BORD);
        optionBtn[i].addActionListener(this);
        gridPane.add(optionBtn[i]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Aeropuerto aer = null;

        try {
            aer = (Aeropuerto) ObjectImp.impObj(this, "Aeropuerto_"
                                    + ((Trabajador) getUser()).getAeropuerto());
        } catch (NameNotFoundException e1) {}
        
        if (e.getSource() instanceof JButton) {
            JButton opt = (JButton) e.getSource();
            index = opt.getIndex();

            menu.show(this.getContentPane(), opt.getX() + 20, opt.getY() + 10);
        } else {
            JMenuItem opt = (JMenuItem) e.getSource();
            int i = index;
            try {
                Avion avion = (Avion) ObjectImp.impObj(this, "Avion_" + vuelos[i].getAvion());
                avion.setVuelo(null);
                aer.delVuelo(vuelos[i].getCodigo());
                ObjectExp.expObj(this, avion);

                boolean exito = ObjectImp.delObj(this, vuelos[i]);

                if (opt.getText().equals(items[0])) {
                    avion.setAeropuertoActual(vuelos[i].getDestino());
                    avion.resizeMatrix();

                    new Animacion(exito, vuelos[i].getOrigen(),
                                    vuelos[i].getDestino()).open();

                    if (exito) {
                        stateLbls[i].setText("Completado");
                        stateLbls[i].setForeground(GREEN);
                        optionBtn[i].setEnabled(false);
                    }
                } else if (opt.getText().equals(items[1])) {
                    avion.resizeMatrix();

                    if (exito) {
                        stateLbls[i].setText("Cancelado");
                        stateLbls[i].setForeground(RED);
                        fechaLbls[i].setText("Nunca");
                        optionBtn[i].setEnabled(false);
                    }
                } else if (opt.getText().equals(items[2])) {
                    LocalDate date = getDate(vuelos[i].getFechaVuelo());
                    if (date == null) throw new NameNotFoundException();

                    Vuelo vu = new Vuelo(vuelos[i], date);
                    avion.setVuelo(vu);
                    aer.addVuelo(vu.getCodigo());

                    ObjectExp.expObj(this, avion);
                    ObjectExp.expObj(this, vu);

                    if (exito) {
                        stateLbls[i].setText("Retrasado");
                        stateLbls[i].setForeground(ORANGE);
                        fechaLbls[i].setText(date.toString());
                    }
                }
            } catch (NameNotFoundException ex) {}
        }
        
        ObjectExp.expObj(this, aer);
    }

    private LocalDate getDate(LocalDate antDate) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate date = null;
        try {
        String fecha = JOptionPane.showInputDialog(this, "\tIngrese la nueva fecha para el\t\n"
                            + "\tvuelo (Posterior a " + antDate + ").\n"
                            + "En formato dd/mm/yy", "", JOptionPane.PLAIN_MESSAGE);

            date = LocalDate.parse(fecha, f);
            if (date.isBefore(antDate)) throw new NullPointerException();
        } catch (DateTimeException e) {
        } catch (NullPointerException e) {}
        return date;
    }
    
    private class JButton extends javax.swing.JButton {
        private final int index;

        public JButton(String s, int index) {
            super(s);
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }
}
