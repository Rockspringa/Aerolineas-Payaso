package edu.main;

import java.util.ArrayList;
import java.io.*;

import javax.swing.*;

import edu.gui.components.DFrame;
import edu.gui.windows.*;
import edu.maker.HTMLMaker;
import edu.obj.airport.*;
import edu.obj.*;

public class Init {
    public static DFrame winInit;

    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                winInit = new Initial();
                winInit.open();
            }
        });
*/
        Matrix<Object> mat = new Matrix<Object>(5, 4);
        mat.add("Nombre 1");
        mat.add("Nombre 2");
        mat.add("Apellido 1");
        mat.add("Apellido 2");
        for (int x = 0; x < 4; x++) {
            mat.add("Dylan");
            mat.add("Antonio");
            mat.add("Elias");
            mat.add("Vasquez");
        } HTMLMaker.createWeb(null, "Nombres", mat);
    }
    
    public static void guardarArchivo(File file, String cont) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));) {
            bfWriter.write(cont);
            bfWriter.flush();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
