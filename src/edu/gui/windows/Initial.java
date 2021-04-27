package edu.gui.windows;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.gui.components.DFrame;
import edu.gui.files.Icon;
import edu.gui.windows.passengers.PModul;

public class Initial extends DFrame implements MouseInputListener {
    private Border generalBorder;
    private Border paneBorder;
    private Border selectBorder;

    private JPanel publicPane;
    private JPanel employeesPane;
    private JPanel textPanel;

    private JLabel publicLbl;
    private JLabel employeesLbl;
    private JLabel textLbl;

    private JLabel publicImage;
    private JLabel employeesImage;

    public Initial() {
        super("Welcome", 530, 260);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel win = (JPanel) (this.getContentPane());
        win.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        win.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
        
        Dimension dimPane = new Dimension(152, 192);
        Dimension dimImage = new Dimension(150, 150);

        generalBorder = BorderFactory.createLineBorder(DFrame.FOCUS_COLOR, 3, true);
        paneBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        selectBorder = BorderFactory.createLineBorder(DFrame.AQUA, 1);

        /* JPanel that contains the labels, with text and with images. */

        textPanel = new JPanel(new BorderLayout());
        textPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        textPanel.setPreferredSize(new Dimension(185, 192));
        textPanel.setBorder(paneBorder);
        win.add(textPanel);
        
        publicPane = new JPanel(new BorderLayout());
        publicPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        publicPane.setPreferredSize(dimPane);
        publicPane.addMouseListener(this);
        publicPane.setBorder(paneBorder);
        win.add(publicPane);

        employeesPane = new JPanel(new BorderLayout());
        employeesPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        employeesPane.setPreferredSize(dimPane);
        employeesPane.addMouseListener(this);
        employeesPane.setBorder(paneBorder);
        win.add(employeesPane);

        /* The JLabel that contains the images with listener for mouse click. */

        publicImage = new JLabel(Icon.PUBLIC.getIcon());
        publicImage.setPreferredSize(dimImage);
        publicImage.setBorder(generalBorder);
        publicImage.setOpaque(true);
        publicPane.add(publicImage, BorderLayout.CENTER);

        employeesImage = new JLabel(Icon.EMPLOYEES.getIcon());
        employeesImage.setPreferredSize(dimImage);
        employeesImage.setBorder(generalBorder);
        employeesImage.setOpaque(true);
        employeesPane.add(employeesImage, BorderLayout.CENTER);

        /* The JLabels that just get text. */

        textLbl = new JLabel("<html><div></div><p style=\"width:152px\">Bienvenido, aqui podras"
                        + " seleccionar el modulo al que te dirijes, toma en cuenta que"
                        + " el modulo de manejo es solo para empleados.</p></html>");
        textLbl.setVerticalAlignment(JLabel.TOP);
        textLbl.setHorizontalAlignment(JLabel.LEFT);
        textPanel.add(textLbl, BorderLayout.CENTER);

        publicLbl = new JLabel("Pasajeros");
        publicLbl.setHorizontalAlignment(JLabel.CENTER);
        publicPane.add(publicLbl, BorderLayout.SOUTH);

        employeesLbl = new JLabel("Manejo");
        employeesLbl.setHorizontalAlignment(JLabel.CENTER);
        employeesPane.add(employeesLbl, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if (e.getSource() == publicPane) {
            publicImage.setBackground(DFrame.AQUA);
            publicPane.setBorder(selectBorder);
        } if (e.getSource() == employeesPane) {
            employeesImage.setBackground(DFrame.AQUA);
            employeesPane.setBorder(selectBorder);
        }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        Color bg = null;
        DFrame frame = null;
        JPanel pane = (JPanel) (e.getSource());
        if (pane == publicPane) {
            bg = publicImage.getBackground();
            frame = new PModul();
        } else if (pane == employeesPane) {
            bg = employeesImage.getBackground();
        } if (bg != DFrame.BACKG_COLOR) {
            this.mouseExited(e);
        } if (frame != null) {
            this.dispose();
            frame.open();
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        if (e.getSource() == publicPane) {
            publicImage.setBackground(DFrame.SHADOW_BLUE);
        } if (e.getSource() == employeesPane) {
            employeesImage.setBackground(DFrame.SHADOW_BLUE);
        }
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        JPanel pane = (JPanel) (e.getSource());
        if (pane == publicPane) {
            publicImage.setBackground(DFrame.BACKG_COLOR);
        } if (pane == employeesPane) {
            employeesImage.setBackground(DFrame.BACKG_COLOR);
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {}
}
