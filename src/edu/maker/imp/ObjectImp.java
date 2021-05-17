package edu.maker.imp;

import java.io.*;

import javax.swing.*;

import edu.maker.Persistencia;
import edu.obj.Creable;

public class ObjectImp extends Persistencia {
    public static Creable impObj(JFrame frame, String filename) {
        Creable obj = null;
        if (objFol == null)
            objFol = getFolder(frame, "Seleccione la carpeta de destino.");

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(getPathObj(filename)));) {
            obj = (Creable) (in.readObject());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "No se ha encontrado el archivo",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "La importacion del archivo ha fallado o interrumpido.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "No se encontro la representacion del archivo.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {}
        return obj;
    }

    public static String getPathObj(String filename) {
        return objFol.getPath() + "\\" + filename + ".delta";
    }
}
