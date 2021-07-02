package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.gui.components.DFrame;
import edu.obj.airport.*;
import edu.obj.items.*;
import edu.obj.persis.Matrix;
import edu.obj.users.Pasajero;
import edu.gui.files.Icon;
import edu.maker.exp.ObjectExp;
import edu.maker.imp.ObjectImp;

public class Asientos extends DFrame implements MouseInputListener {
    private static final Color BL = new Color(0, 0, 255, 40);
    private static final Color RD = new Color(255, 0, 0, 40);
    private static final Color WT = new Color(238, 238, 238, 40);
    private static final Color MG = new Color(200, 0, 200, 40);
    private static final Color SB = new Color(50, 50, 65, 40);

    private static int num = 1111;
    private int asiento = -1;
    private Vuelo vue;

    private JLabel txtLbl;
    private JPanel asientosPane;
    private JLabel[][] asientosLbls;
    private JScrollPane scroll;

    private Border waitBord;
    private Border pasilloBord;
    private Border overBord;

    private JPanel paneBtn;
    private JButton crearBtn;

    public Asientos(Avion avion, Vuelo vuelo, boolean listen) {
        super("Vista de asientos", 501, 602);
        setOpenFrame(null);
        JPanel win = (JPanel) (this.getContentPane());
        DFrame ven = this;
        win.setLayout(new BorderLayout());

        vue = vuelo;

        int rows = avion.getRows();
        int cols = avion.getCols();
        int width = (cols < 10) ? 485 / cols : cols * 50;
        int height = (rows < 10) ? width * rows / cols : rows * 50;

        waitBord = BorderFactory.createLineBorder(DFrame.SHADOW_BLUE, 2);
        pasilloBord = BorderFactory.createLineBorder(DFrame.BACKG_COLOR, 2);
        overBord = BorderFactory.createLineBorder(DFrame.AQUA, 2);

        if (listen) {
            txtLbl = new JLabel("<html><br><div>Seleccione su asiento</div><br><html>");
            txtLbl.setHorizontalAlignment(JLabel.CENTER);
            win.add(txtLbl, BorderLayout.PAGE_START);
        }

        asientosPane = new JPanel(new GridLayout(rows, cols, 0, 0)) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Icon.AVION.getIcon().getImage(), 0, 0, this);
            }
        };
        asientosPane.setPreferredSize(new Dimension(width, height));
        
        scroll = new JScrollPane(asientosPane);
        scroll.setBackground(DFrame.SHADOW_BLUE);
        win.add(scroll);

        new Thread(){
            private MouseInputListener frame;

            @Override
            public void run() {
                asientosLbls = new JLabel[rows][cols];
                int p = avion.getPasillo() - 1;
                Matrix<String> ax = Avion.asientos.get(avion.getCodigo());
                try {
                    for (int m = 0; m < rows; m++) {
                        for (int n = 0; n < cols; n++) {
                            String dato = ax.get(m, n);
                            asientosLbls[m][n] = new JLabel();
                            if (n == p) {
                                asientosLbls[m][n].setBackground(SB);
                                asientosLbls[m][n].setBorder(pasilloBord);
                            } else if (dato.equals("r")) {
                                asientosLbls[m][n].setBackground(RD);
                                asientosLbls[m][n].setBorder(waitBord);
                            } else if (dato.equals("b")) {
                                asientosLbls[m][n].setBackground(BL);
                                asientosLbls[m][n].setBorder(waitBord);
                            }  if (listen && n != p) {
                                asientosLbls[m][n].addMouseListener(frame);
                            }
                            asientosLbls[m][n].setOpaque(true);
                            asientosPane.add(asientosLbls[m][n]);
                        }
                    }
                } catch (NullPointerException exec) {
                    ven.dispose();
                    JOptionPane.showMessageDialog(ven, "Hubo un dato erroneo",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                }
                scroll.revalidate();
                scroll.repaint();
            }

            public void start(MouseInputListener frame) {
                this.frame = frame;
                this.start();
            }
        }.start(this);

        if (listen) {
            paneBtn = new JPanel();
            win.add(paneBtn, BorderLayout.PAGE_END);

            crearBtn = new JButton("Seleccionar asientos");
            crearBtn.addActionListener(this);
            paneBtn.add(crearBtn);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (asiento >= 0) {
            int pasN = 0;
            boolean valido = false;
            while (!valido) {
                String pas = JOptionPane.showInputDialog(this,
                        "Ingrese el numero de su pasaporte.", "", JOptionPane.PLAIN_MESSAGE);
                
                try {
                    pasN = Integer.valueOf(pas);
                    valido = Pasaporte.exists(pasN);
                } catch (NumberFormatException ex) {
                    valido = false;
                }
            }

            int tarN = 0;
            valido = false;
            while (!valido) {
                String tar = JOptionPane.showInputDialog(this,
                        "Ingrese el numero de su tarjeta.", "", JOptionPane.PLAIN_MESSAGE);
                
                try {
                    tarN = Integer.valueOf(tar);
                    valido = Tarjeta.exists(tarN);
                } catch (NumberFormatException ex) {
                    valido = false;
                }
            }
            ObjectExp.expObj(this, new Reservacion(pasN, vue.getCodigo(), tarN, asiento));
            if (!Pasajero.exists(pasN + "")) {
                try {
                    ((Pasaporte) (ObjectImp.impObj(this, "Pasaporte_" + pasN))).setContraseña("abcd" + num);
                } catch (NameNotFoundException ex) {}
                JOptionPane.showMessageDialog(this, "Su contraseña va a ser : 'abcd" + num + "'",
                                "", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        event.setBackground(WT);
        asiento = -1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        if (event.getBorder() != waitBord) {
            event.setBackground(BL);
        } else if (event.getBackground() == BL) {
            event.setBackground(MG);
            int rows = asientosLbls.length;
            int cols = asientosLbls[0].length;
            for (int m = 0; m < rows; m++) {
                for (int n = 0; n < cols; n++) {
                    if (asientosLbls[m][n] == event) {
                        asiento = m * cols + n;
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        event.setBorder(overBord);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        event.setBorder(waitBord);
        if (event.getBackground() == WT)
            event.setBackground(BL);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
