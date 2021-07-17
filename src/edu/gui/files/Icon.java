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
    AVION_1("images/avion_1.png", 50, 50),
    AVION_2("images/avion_2.png", 50, 50),
    AVION_3("images/avion_3.png", 50, 50),
    CIUDAD_1("images/ciudad_1.png", 100, 100),
    CIUDAD_2("images/ciudad_2.png", 100, 100),
    AVION("images/avion.png", 500, 500),
    ATRAS("images/atras.png", 25, 25),
    VACIO("images/vacio.png", 25, 25),
    CARG("images/cargando.gif", 25, 25),
    PASILLO("images/pasillo.png", 50, 50),
    SILLON_O("images/sillon_o.png", 50, 50),
    SILLON_X("images/sillon_x.png", 50, 50),
    ATRAS_P("images/atras_press.png", 25, 25);

    private ImageIcon icon;

    private Icon(String path, int width, int height) {
        icon = new ImageIcon( 
                    new ImageIcon(
                        getClass().getResource(path)
                    ).getImage().getScaledInstance(width, height, (path.contains("gif"))
                                            ? Image.SCALE_DEFAULT : Image.SCALE_SMOOTH)
                );
    }
    
    public ImageIcon getIcon() {
        return icon;
    }
    
    public ImageIcon getIcon(int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
