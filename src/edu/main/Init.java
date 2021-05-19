package edu.main;

import javax.swing.*;

import edu.gui.components.DFrame;
import edu.gui.windows.*;

public class Init {
    public static DFrame winInit;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    winInit = new Initial();
                    winInit.open();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(winInit,
                                "Ocurrio un error que no se controlo", "Error",
                                JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
