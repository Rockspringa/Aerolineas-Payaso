package edu.gui.windows.airport.opera;

import java.awt.*;
import java.awt.event.*;

import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.gui.components.DFrame;
import edu.gui.windows.passengers.Asientos;
import edu.maker.exp.ObjectExp;
import edu.maker.imp.ObjectImp;
import edu.obj.airport.*;

public class ModificarAvion extends DFrame {
    private static final Dimension dimPane = new Dimension(420, 25);
    private static final Dimension dimLbl = new Dimension(220, 25);
    private static final Dimension dimBtn = new Dimension(160, 30);
    private static final Dimension dimSp = new Dimension(70, 25);

    private Avion avion;

    private JPanel prinPanel;

    private JPanel colPanel;
    private JLabel colLbl;
    private JSpinner colSpin;

    private JPanel rowPanel;
    private JLabel rowLbl;
    private JSpinner rowSpin;

    private JPanel pasPanel;
    private JLabel pasLbl;
    private JSpinner pasSpin;

    private JPanel btnPanel;
    private JButton preBtn;
    private JButton creBtn;

    public ModificarAvion(DFrame frame, Avion av) {
        super("Modificar avion", 450, 300);
        setOpenFrame(frame);
        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new BorderLayout());
        win.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        avion = av;

        prinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.getContentPane().add(prinPanel, BorderLayout.CENTER);
        
        colPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        pasPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));

        create(colPanel);
        create(rowPanel);
        create(pasPanel);
        
        colLbl = new JLabel();
        rowLbl = new JLabel();
        pasLbl = new JLabel();

        create(colLbl, colPanel, "Asientos por Fila:     ");
        create(rowLbl, rowPanel, "Cantidad de Filas:     ");
        create(pasLbl, pasPanel, "Columna de pasillo:    ");
        
        colSpin = new JSpinner(new SpinnerNumberModel(avion.getCols() - 1, 1, 20000, 1));
        rowSpin = new JSpinner(new SpinnerNumberModel(avion.getRows(), 1, 20000, 1));
        pasSpin = new JSpinner(new SpinnerNumberModel(avion.getPasillo() + 1, 1, 20000, 1));

        create(colSpin, colPanel);
        create(rowSpin, rowPanel);
        create(pasSpin, pasPanel);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        btnPanel.setBorder(null);
        btnPanel.setPreferredSize(new Dimension(445, 40));
        this.getContentPane().add(btnPanel, BorderLayout.PAGE_END);

        preBtn = new JButton();
        creBtn = new JButton();
        create(preBtn, " Previsualizar ");
        create(creBtn, "Modificar Avion");
                    
        JOptionPane.showMessageDialog(this, "El avion " + avion
                        + " tiene " + (avion.getCols() - 1) + " columnas, "
                        + (avion.getRows()) + " filas y el pasillo esta en la "
                        + " columna numero " + (avion.getPasillo() + 1) + ".",
                    "Avion actualmente", JOptionPane.INFORMATION_MESSAGE);
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

    private void create(JButton btn, String txt) {
        btn.setText(txt);
        btn.setPreferredSize(dimBtn);
        btn.addActionListener(this);
        btnPanel.add(btn);
    }

    public static Avion searchAvion(DFrame frame) {
        
        Aerolinea aer = null;
        while (true) {
            try {
                aer = (Aerolinea) ObjectImp.impObj(frame, "Aerolinea_"
                            + JOptionPane.showInputDialog(frame,
                                "Escoja la aerolinea a la que\npertenece el avion a modificar\n",
                                "Busqueda de avion", JOptionPane.DEFAULT_OPTION, null,
                                Aerolinea.nombres.toArray(),
                                Aerolinea.nombres.toArray()[0]).toString()
                        );

                if (aer != null)
                    break;
            } catch (NameNotFoundException e) {}
        }

        Avion avion = null;
        while (true) {
            try {
                avion = (Avion) ObjectImp.impObj(frame, "Avion_"
                            + JOptionPane.showInputDialog(frame,
                                "Escoja el avion a modificar\n",
                                "Busqueda de avion", JOptionPane.DEFAULT_OPTION, null,
                                aer.getAviones(),
                                aer.getAviones()[0]).toString()
                        );

                if (avion != null)
                    break;
            } catch (NameNotFoundException e) {}
        }
        return avion;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((Integer) (pasSpin.getValue()) <= (Integer) (colSpin.getValue())) {
            try {
                if (preBtn == e.getSource()) {
                    int cod = Avion.codigos.size() + 1;
                    String codigo = (cod < 10) ? "AV000" + cod
                                            : (cod < 100) ? "AV00" + cod
                                                : (cod < 1000) ? "AV0" + cod : "AV" + cod;

                    int cols = Integer.parseInt(colSpin.getValue().toString()) + 1;
                    int rows = Integer.parseInt(rowSpin.getValue().toString());
                    int pasillo = Integer.parseInt(pasSpin.getValue().toString()) - 1;

                    new Asientos(new Avion("", "", codigo, rows, cols,
                                        pasillo, 0, 0), null, false).open();
                    Avion.codigos.remove(codigo);
                    
                } if (creBtn == e.getSource()) {

                    int cols = Integer.parseInt(colSpin.getValue().toString()) + 1;
                    int rows = Integer.parseInt(rowSpin.getValue().toString());
                    int pasillo = Integer.parseInt(pasSpin.getValue().toString()) - 1;

                    avion.setCols(cols);
                    avion.setRows(rows);
                    avion.setPasillo(pasillo);
                    avion.resizeMatrix();

                    ObjectExp.expObj(this, avion);
                    
                    JOptionPane.showMessageDialog(this, "Modificacion de avion terminada, ahora el "
                                        + "avion tiene " + (cols * rows) + " asientos.",
                                    "Avion modificado", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            } catch (IndexOutOfBoundsException exe) {
                JOptionPane.showMessageDialog(this, "Hubo un dato erroneo, verifique los datos.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception exe) {
                
                JOptionPane.showMessageDialog(this, "Hubo un error inesperado, "
                                        + "verifique sus entradas y vuelva a intentarlo.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
