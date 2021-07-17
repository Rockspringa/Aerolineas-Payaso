package edu.gui.windows.airport;

import java.awt.event.*;
import java.awt.*;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;

import edu.gui.windows.airport.opera.*;
import edu.obj.Aeropuerto;
import edu.obj.users.*;
import edu.maker.imp.*;
import edu.gui.windows.airport.admin.*;
import edu.gui.components.DFrame;
import edu.gui.files.Icon;

public class Select extends DFrame implements MouseInputListener {
    public static final int ADMIN = 0;
    public static final int GEREN = 1;
    public static final int OPERA = 2;

    private boolean crear = false;

    private Border generalBorder;
    private Border paneBorder;
    private Border selectBorder;

    private JPanel adminPane;
    private JPanel gerenPane;
    private JPanel operaPane;

    private JLabel adminLbl;
    private JLabel gerenLbl;
    private JLabel operaLbl;

    private JLabel adminImage;
    private JLabel gerenImage;
    private JLabel operaImage;

    public Select(DFrame frame, int lvl) {
        super((lvl != 4) ? "Seleccione su departamento" : "Crear usuario...", 500, 260);
        setOpenFrame(frame);

        crear = (lvl == 4) ? true : false;

        JPanel win = (JPanel) (this.getContentPane());
        win.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        win.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
        
        Dimension dimPane = new Dimension(152, 192);
        Dimension dimImage = new Dimension(150, 150);

        generalBorder = BorderFactory.createLineBorder(DFrame.SHADOW_BLUE, 3, true);
        paneBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
        selectBorder = BorderFactory.createLineBorder(DFrame.AQUA, 1);

        /* JPanel that contains the labels, with text and with images. */

        adminPane = new JPanel(new BorderLayout());
        adminPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        adminPane.setPreferredSize(dimPane);
        if (lvl == 0 || lvl == 4) adminPane.addMouseListener(this);
        adminPane.setBorder(paneBorder);
        win.add(adminPane);
        
        gerenPane = new JPanel(new BorderLayout());
        gerenPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        gerenPane.setPreferredSize(dimPane);
        if (lvl != 2) gerenPane.addMouseListener(this);
        gerenPane.setBorder(paneBorder);
        win.add(gerenPane);

        operaPane = new JPanel(new BorderLayout());
        operaPane.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        operaPane.setPreferredSize(dimPane);
        operaPane.addMouseListener(this);
        operaPane.setBorder(paneBorder);
        win.add(operaPane);

        /* The JLabel that contains the images with listener for mouse click. */

        adminImage = new JLabel(Icon.ADMIN.getIcon());
        adminImage.setPreferredSize(dimImage);
        adminImage.setBorder(generalBorder);
        adminImage.setOpaque(true);
        if (lvl != 0 && lvl != 4) adminImage.setBackground(Color.DARK_GRAY);
        adminPane.add(adminImage, BorderLayout.CENTER);

        gerenImage = new JLabel(Icon.GEREN.getIcon());
        gerenImage.setPreferredSize(dimImage);
        gerenImage.setBorder(generalBorder);
        gerenImage.setOpaque(true);
        if (lvl == 2) gerenImage.setBackground(Color.DARK_GRAY);
        gerenPane.add(gerenImage, BorderLayout.CENTER);

        operaImage = new JLabel(Icon.OPERA.getIcon());
        operaImage.setPreferredSize(dimImage);
        operaImage.setBorder(generalBorder);
        operaImage.setOpaque(true);
        operaPane.add(operaImage, BorderLayout.CENTER);

        /* The JLabels that just get text. */

        adminLbl = new JLabel("Administracion");
        adminLbl.setHorizontalAlignment(JLabel.CENTER);
        if (lvl != 0 && lvl != 4) adminLbl.setForeground(Color.DARK_GRAY);
        adminPane.add(adminLbl, BorderLayout.SOUTH);

        gerenLbl = new JLabel("Gerencia");
        gerenLbl.setHorizontalAlignment(JLabel.CENTER);
        if (lvl == 2) gerenLbl.setForeground(Color.DARK_GRAY);
        gerenPane.add(gerenLbl, BorderLayout.SOUTH);

        operaLbl = new JLabel("Operador de vuelo");
        operaLbl.setHorizontalAlignment(JLabel.CENTER);
        operaPane.add(operaLbl, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == adminPane) {
            adminImage.setBackground(DFrame.AQUA);
            adminPane.setBorder(selectBorder);
        } if (e.getSource() == gerenPane) {
            gerenImage.setBackground(DFrame.AQUA);
            gerenPane.setBorder(selectBorder);
        } if (e.getSource() == operaPane) {
            operaImage.setBackground(DFrame.AQUA);
            operaPane.setBorder(selectBorder);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Color bg = null;
        DFrame frame = null;
        JPanel pane = (JPanel) (e.getSource());
        String emp = null;

        if (pane == adminPane) {
            emp = "admin";
            bg = adminImage.getBackground();
            frame = new Administracion(this);
        } else if (pane == gerenPane) {
            emp = "geren";
            bg = gerenImage.getBackground();
        } else if (pane == operaPane) {
            emp = "opera";
            bg = operaImage.getBackground();
            String[] options = {"Visualizar un avion",
                                " Operar Aeropuerto "};

            if (!crear)
                try {
                int x = JOptionPane.showOptionDialog(this, "Que desea realizar.",
                            "Aciones de operador", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, Icon.OPERA.getIcon(),
                            options, options[0]);

                    if (x == 0)
                        frame = new Aviones(this);
                    else if (x == 1 && !(getUser() instanceof Operador)) {
                        Aeropuerto aer = null;
                        try {
                            aer = (Aeropuerto) ObjectImp.impObj(frame, "Aeropuerto_"
                                        + JOptionPane.showInputDialog(frame,
                                            "\tEscoja el aeropuerto que desea\t\n\tadministrar\t\n",
                                            "Busqueda de avion", JOptionPane.DEFAULT_OPTION, null,
                                            Aeropuerto.nombres.toArray(),
                                            Aeropuerto.nombres.toArray()[0]).toString()
                                    );
                            if (getUser() instanceof Admin)
                                ((Admin) getUser()).setAeropuerto(aer.getNombre());
                            else if (getUser() instanceof Gerente)
                                ((Gerente) getUser()).setAeropuerto(aer.getNombre());
                            frame = new Operar(this);
                        } catch (NameNotFoundException ex) {}
                    } else if (x == 1) {
                        frame = new Operar(this);
                    }
                } catch (Exception ex) {}

        } if (crear && emp != null) {
            frame = new CrearEmp(this, emp);
        }
        
        if (bg != DFrame.BACKG_COLOR) {
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
        if (e.getSource() == adminPane) {
            adminImage.setBackground(DFrame.FOCUS_COLOR);
            adminLbl.setForeground(DFrame.AQUA);
        } if (e.getSource() == gerenPane) {
            gerenImage.setBackground(DFrame.FOCUS_COLOR);
            gerenLbl.setForeground(DFrame.AQUA);
        } if (e.getSource() == operaPane) {
            operaImage.setBackground(DFrame.FOCUS_COLOR);
            operaLbl.setForeground(DFrame.AQUA);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel pane = (JPanel) (e.getSource());
        if (pane == adminPane) {
            adminImage.setBackground(DFrame.BACKG_COLOR);
            adminLbl.setForeground(DFrame.WHITE);
        } if (pane == gerenPane) {
            gerenImage.setBackground(DFrame.BACKG_COLOR);
            gerenLbl.setForeground(DFrame.WHITE);
        } if (pane == operaPane) {
            operaImage.setBackground(DFrame.BACKG_COLOR);
            operaLbl.setForeground(DFrame.WHITE);
        }
        pane.setBorder(paneBorder);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
