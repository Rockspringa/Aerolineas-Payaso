package edu.gui.windows.airport.admin;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.enums.Obj;
import edu.gui.components.DFrame;
import edu.gui.files.Icon;
import edu.gui.windows.airport.Select;

public class Administracion extends DFrame implements MouseInputListener {
    private Border generalBorder;
    private Border paneBorder;
    private Border selectBorder;

    private JPanel importPane;
    private JPanel reportPane;
    private JPanel userPane;

    private JLabel importLbl;
    private JLabel reportLbl;
    private JLabel userLbl;

    private JLabel importImage;
    private JLabel reportImage;
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

        reportPane = new JPanel(new BorderLayout());
        reportPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        reportPane.setPreferredSize(dimPane);
        reportPane.addMouseListener(this);
        reportPane.setBorder(paneBorder);
        win.add(reportPane);

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

        reportImage = new JLabel(Icon.REPORTE.getIcon());
        reportImage.setPreferredSize(dimImage);
        reportImage.setBorder(generalBorder);
        reportImage.setOpaque(true);
        reportPane.add(reportImage, BorderLayout.CENTER);

        userImage = new JLabel(Icon.USUARIO.getIcon());
        userImage.setPreferredSize(dimImage);
        userImage.setBorder(generalBorder);
        userImage.setOpaque(true);
        userPane.add(userImage, BorderLayout.CENTER);

        /* The JLabels that just get text. */

        importLbl = new JLabel("Importar datos");
        importLbl.setHorizontalAlignment(JLabel.CENTER);
        importPane.add(importLbl, BorderLayout.SOUTH);

        reportLbl = new JLabel("Ver reportes");
        reportLbl.setHorizontalAlignment(JLabel.CENTER);
        reportPane.add(reportLbl, BorderLayout.SOUTH);

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
        } if (e.getSource() == reportPane) {
            reportImage.setBackground(DFrame.AQUA);
            reportPane.setBorder(selectBorder);
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
        } else if (pane == reportPane) {
            bg = reportImage.getBackground();
            frame = new Reporte(this, Obj.ADMINISTRADOR);
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
        } if (e.getSource() == reportPane) {
            reportImage.setBackground(DFrame.FOCUS_COLOR);
            reportLbl.setForeground(DFrame.AQUA);
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
        } if (pane == reportPane) {
            reportImage.setBackground(DFrame.BACKG_COLOR);
            reportLbl.setForeground(DFrame.WHITE);
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
