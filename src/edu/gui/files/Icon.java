package edu.gui.files;

import javax.swing.*;

import java.awt.*;

public enum Icon {
    PUBLIC("images/public.png", 150, 150),
    EMPLOYEES("images/employees.png", 150, 150),
    IMPORT("images/import.png", 150, 150);

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
