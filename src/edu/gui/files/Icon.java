package edu.gui.files;

import javax.swing.*;

import java.awt.*;

public enum Icon {
    PUBLIC("images/public.png", 150, 150),
    EMPLOYEES("images/employees.png", 150, 150),
    IMPORT("images/import.png", 150, 150),
    ADMIN("images/admin.png", 150, 150),
    GEREN("images/geren.png", 150, 150),
    OPERA("images/opera.png", 150, 150),
    REPORTE("images/reporte.png", 150, 150),
    USUARIO("images/usuario.png", 150, 150),
    AVION("images/avion.png", 500, 500);

    private ImageIcon icon;

    private Icon(String path, int width, int height) {
        icon = new ImageIcon(
                    new ImageIcon(
                        getClass().getResource(path)
                    ).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
                );
    }
    
    public ImageIcon getIcon() {
        return icon;
    }
    
    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
