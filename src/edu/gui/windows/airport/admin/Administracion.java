package edu.gui.windows.airport.admin;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.gui.components.DFrame;
import edu.gui.files.Icon;
import edu.gui.windows.airport.Select;

public class Administracion extends DFrame implements MouseInputListener {
    private Border generalBorder;
    private Border paneBorder;
    private Border selectBorder;

    private JPanel importPane;
    private JPanel airlinePane;
    private JPanel userPane;

    private JLabel importLbl;
    private JLabel airlineLbl;
    private JLabel userLbl;

    private JLabel importImage;
    private JLabel airlineImage;
    private JLabel userImage;

    public Administracion(DFrame frame) {
        super("Que desea hacer", 500, 260);
        setOpenFrame(frame);

        JPanel win = (JPanel) (this.getContentPane());
        win.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        win.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
        
        Dimension dimPane = new Dimension(152, 192);
        Dimension dimImage = new Dimension(150, 150);

        generalBorder = BorderFactory.createLineBorder(DFrame.SHADOW_BLUE, 3, true);
        paneBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        selectBorder = BorderFactory.createLineBorder(DFrame.AQUA, 1);

        /* JPanel that contains the labels, with text and with images. */

        importPane = new JPanel(new BorderLayout());
        importPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        importPane.setPreferredSize(dimPane);
        importPane.addMouseListener(this);
        importPane.setBorder(paneBorder);
        win.add(importPane);

        airlinePane = new JPanel(new BorderLayout());
        airlinePane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        airlinePane.setPreferredSize(dimPane);
        airlinePane.addMouseListener(this);
        airlinePane.setBorder(paneBorder);
        win.add(airlinePane);

        userPane = new JPanel(new BorderLayout());
        userPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        userPane.setPreferredSize(dimPane);
        userPane.addMouseListener(this);
        userPane.setBorder(paneBorder);
        win.add(userPane);
        
        /* The JLabel that contains the images with listener for mouse click. */

        importImage = new JLabel(Icon.IMPORT.getIcon());
        importImage.setPreferredSize(dimImage);
        importImage.setBorder(generalBorder);
        importImage.setOpaque(true);
        importPane.add(importImage, BorderLayout.CENTER);

        airlineImage = new JLabel(Icon.AVION.getIcon(150, 150));
        airlineImage.setPreferredSize(dimImage);
        airlineImage.setBorder(generalBorder);
        airlineImage.setOpaque(true);
        airlinePane.add(airlineImage, BorderLayout.CENTER);

        userImage = new JLabel(Icon.USUARIO.getIcon());
        userImage.setPreferredSize(dimImage);
        userImage.setBorder(generalBorder);
        userImage.setOpaque(true);
        userPane.add(userImage, BorderLayout.CENTER);

        /* The JLabels that just get text. */

        importLbl = new JLabel("Importar datos");
        importLbl.setHorizontalAlignment(JLabel.CENTER);
        importPane.add(importLbl, BorderLayout.SOUTH);

        airlineLbl = new JLabel("Aerolineas");
        airlineLbl.setHorizontalAlignment(JLabel.CENTER);
        airlinePane.add(airlineLbl, BorderLayout.SOUTH);

        userLbl = new JLabel("Crear empleado");
        userLbl.setVerticalAlignment(JLabel.CENTER);
        userPane.add(userLbl, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == importPane) {
            importImage.setBackground(DFrame.AQUA);
            importPane.setBorder(selectBorder);
        } if (e.getSource() == airlinePane) {
            airlineImage.setBackground(DFrame.AQUA);
            airlinePane.setBorder(selectBorder);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Color bg = null;
        DFrame frame = null;
        JPanel pane = (JPanel) (e.getSource());
        if (pane == userPane) {
            bg = userImage.getBackground();
            frame = new Select(this, 4);
        } else if (pane == importPane) {
            bg = importImage.getBackground();
            frame = new Import(this);
        } else if (pane == airlinePane) {
            bg = airlineImage.getBackground();
            try {
                String[] options = {"   Crear un avion  ",
                                    " Modificar un avion"};

                Object x = JOptionPane.showInputDialog(this, "Que desea realizar.\n",
                                    "Aciones de operador", JOptionPane.DEFAULT_OPTION,
                                    null, options, options[0]);
    
                if (x.toString().equals(options[0]))
                    frame = new CrearAvion(this);
                else if (x.toString().equals(options[1])) {
                    frame = new ModificarAvion(this, ModificarAvion.searchAvion(this));
                }
            } catch (NullPointerException ex) {}
        } if (bg != DFrame.BACKG_COLOR) {
            this.mouseExited(e);
            if (frame != null) {
                this.dispose(false);
                frame.open();
            }
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == userPane) {
            userImage.setBackground(DFrame.FOCUS_COLOR);
            userLbl.setForeground(DFrame.AQUA);
        } if (e.getSource() == importPane) {
            importImage.setBackground(DFrame.FOCUS_COLOR);
            importLbl.setForeground(DFrame.AQUA);
        } if (e.getSource() == airlinePane) {
            airlineImage.setBackground(DFrame.FOCUS_COLOR);
            airlineLbl.setForeground(DFrame.AQUA);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel pane = (JPanel) (e.getSource());
        if (pane == userPane) {
            userImage.setBackground(DFrame.BACKG_COLOR);
            userLbl.setForeground(DFrame.WHITE);
        } if (pane == importPane) {
            importImage.setBackground(DFrame.BACKG_COLOR);
            importLbl.setForeground(DFrame.WHITE);
        } if (pane == airlinePane) {
            airlineImage.setBackground(DFrame.BACKG_COLOR);
            airlineLbl.setForeground(DFrame.WHITE);
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
