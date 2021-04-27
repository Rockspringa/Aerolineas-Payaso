package edu.main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import edu.gui.components.DFrame;
import edu.gui.windows.*;

public class Init {
    public static DFrame winInit;
    public static final ArrayList<String> ciudades = new ArrayList<String>();

    public static void main(String[] args) {
        ciudades.add("Guatemala");
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                winInit = new Initial();
                winInit.open();
            }
        });
    }
    
}
