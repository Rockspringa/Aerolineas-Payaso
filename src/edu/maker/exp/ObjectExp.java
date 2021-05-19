package edu.maker.exp;

import java.io.*;

import javax.swing.*;

import edu.maker.Persistencia;
import edu.obj.Creable;

public class ObjectExp extends Persistencia {
    public static void expObj(JFrame frame, Creable cb) {
        if (objFol == null) {
            JOptionPane.showMessageDialog(frame, "Escoja la carpeta en donde se guardaran los binarios",
                    "Informacion que cura", JOptionPane.INFORMATION_MESSAGE);
            objFol = getFolder(frame, "Seleccione la carpeta de destino.");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getPathObj(cb)));) {
            out.writeObject(cb);
            out.flush();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "No se ha encontrado el archivo",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "La exportacion del archivo ha fallado o interrumpido.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {}
    }

    private static String getPathObj(Creable cb) {
        return objFol.getPath() + "\\" + cb.getFilename() + ".delta";
    }
}
