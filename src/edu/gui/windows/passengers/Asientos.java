package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.gui.components.DFrame;
import edu.obj.airport.Avion;

public class Asientos extends DFrame implements MouseInputListener {
    private JLabel txtLbl;
    private JPanel asientosPane;
    private JLabel[][] asientosLbls;
    private JScrollPane scroll;

    private Border waitBord;
    private Border pasilloBord;
    private Border overBord;

    private JPanel paneBtn;
    private JButton crearBtn;

    public Asientos(Avion avion) {
        super("Asientos disponibles", 501, 602);
        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new BorderLayout());

        int rows = avion.getRows();
        int cols = avion.getCols();
        int width = (cols < 10) ? 485 / cols : cols * 50;
        int height = (rows < 10) ? width * rows / cols : rows * 50;

        waitBord = BorderFactory.createLineBorder(DFrame.SHADOW_BLUE, 2);
        pasilloBord = BorderFactory.createLineBorder(DFrame.BACKG_COLOR, 2);
        overBord = BorderFactory.createLineBorder(DFrame.AQUA, 2);

        txtLbl = new JLabel("<html><br><div>Seleccione su asiento</div><br><html>");
        txtLbl.setHorizontalAlignment(JLabel.CENTER);
        win.add(txtLbl, BorderLayout.PAGE_START);

        asientosPane = new JPanel(new GridLayout(rows, cols, 0, 0));
        asientosPane.setPreferredSize(new Dimension(width, height));
        
        scroll = new JScrollPane(asientosPane);
        scroll.setBackground(DFrame.SHADOW_BLUE);
        win.add(scroll);

        new Thread(){
            private MouseInputListener frame;

            @Override
            public void run() {
                asientosLbls = new JLabel[rows][cols];
                for (int m = 0; m < rows; m++) {
                    for (int n = 0; n < cols; n++) {
                        asientosLbls[m][n] = new JLabel("<html><h6 style=\"text-align:center\">("
                                                        + m + ", " + n + ")</h6><html>");
                        asientosLbls[m][n].setBackground(Color.BLUE);
                        asientosLbls[m][n].setOpaque(true);
                        asientosLbls[m][n].addMouseListener(frame);
                        asientosLbls[m][n].setBorder(waitBord);
                        asientosPane.add(asientosLbls[m][n]);
                    }
                }
            }

            public void start(MouseInputListener frame) {
                this.frame = frame;
                this.start();
            }
        }.start(this);

        paneBtn = new JPanel();
        win.add(paneBtn, BorderLayout.PAGE_END);

        crearBtn = new JButton("Seleccionar asientos");
        crearBtn.addActionListener(this);
        paneBtn.add(crearBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(scroll.getWidth() + " " + scroll.getHeight());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        event.setBackground(DFrame.WHITE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel event = (JLabel) (e.getSource());
        if (event.getBorder() != waitBord)
            event.setBackground(Color.BLUE);
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
        if (event.getBackground() == DFrame.WHITE)
            event.setBackground(Color.BLUE);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
