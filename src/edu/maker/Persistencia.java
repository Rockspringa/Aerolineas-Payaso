package edu.maker;

import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class Persistencia {
    private static File folder;
    public static File objFol;
    private final static JFileChooser fileC = new JFileChooser();

    static {
        fileC.setAcceptAllFileFilterUsed(false);
    }

    public static String getFileName() {
        return fileC.getSelectedFile().getName();
    }

    public static File getFolder(JFrame frame, String title) {
        fileC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileC.setDialogTitle(title);
        fileC.setFileFilter(null);

        int aprovedInt = fileC.showOpenDialog(frame);
        if (aprovedInt == JFileChooser.APPROVE_OPTION) {
            folder = fileC.getSelectedFile();
            return folder;
        } return null;
    }
    
    public static File getFile(JFrame frame, String title, String desc, String ext) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(desc, ext);
        fileC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileC.setDialogTitle(title);
        fileC.setFileFilter(filter);

        int aprovedInt = fileC.showOpenDialog(frame);
        if (aprovedInt == JFileChooser.APPROVE_OPTION) {
            folder = fileC.getSelectedFile();
            return folder;
        } return null;
    }
    
    public static File getTxtFile(JFrame frame, String title) {
        return getFile(frame, title, "Text Files", "txt");
    }
}
