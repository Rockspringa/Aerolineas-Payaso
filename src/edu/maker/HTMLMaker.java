package edu.maker;

import edu.gui.components.DFrame;
import edu.main.Init;
import edu.obj.Matrix;
import javax.swing.*;
import java.io.*;

public class HTMLMaker {
    private static final StringBuilder SB_DUMMY = new StringBuilder();

    private static final int HEAD = 0;
    private static final int TITLE = 1;
    private static final int BODY = 2;
    private static final int TABLE = 3;
    private static final int THEAD = 4;
    private static final int TH = 5;
    private static final int TR = 6;
    private static final int TD = 7;

    public static void createWeb(DFrame frame, String header, Matrix<Object> datos) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n<html>\n");

        addTag(HEAD, addTag(TITLE, header), sb);

        addTag(BODY, addTag(TABLE, addTable(datos)), sb);

        sb.append("</html>");

        JFileChooser fileC = new JFileChooser();
        fileC.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileC.setDialogTitle("Seleccione donde guardar el archivo.");
        fileC.setAcceptAllFileFilterUsed(false);
        int aprovedInt = fileC.showOpenDialog(frame);

        if (aprovedInt == JFileChooser.APPROVE_OPTION) {
            File dir = new File(fileC.getSelectedFile().getPath() + "\\" + "Registros_" + header + ".html");
            Init.guardarArchivo(dir, sb.toString());
        }
    }

    private static String addTable(Matrix<Object> datos) {
        int rows = datos.length()[0];
        int cols = datos.length()[1];
        StringBuilder sbTr = new StringBuilder();
        StringBuilder sbTd = new StringBuilder();
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                if (m == 0) {
                    addTag(TH, datos.get(m, n).toString(), sbTd);
                } else {
                    addTag(TD, datos.get(m, n).toString(), sbTd);
                }
            }
            if (m == 0) {
                addTag(THEAD, sbTd.toString(), sbTr);
            } else {
                addTag(TR, sbTd.toString(), sbTr);
            } sbTd.delete(0, sbTd.length());
        }
        return sbTr.toString();
    }

    private static void addTag(int codTag, String cont, StringBuilder sb) {
        String start = "";
        String end = "";

        if (codTag == THEAD) {
            start = "\t\t\t<thead>\n"
                        + "\t\t<style>\n"
                        + "\t\t\tbody {\n"
                        + "\t\t\t\tcolor: #EEEEEE;\n"
                        + "\t\t\t\tbackground-color: #303045;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttable {\n"
                        + "\t\t\t\twidth: 100%;\n"
                        + "\t\t\t\tborder-collapse: collapse;\n"
                        + "\t\t\t\tborder: 2px solid #303045;\n"
                        + "\t\t\t}\n"
                        + "\t\t\tth {\n"
                        + "\t\t\t\tfont-weight: bold;\n"
                        + "\t\t\t\tfont-size: 24px;\n"
                        + "\t\t\t\theight: 32px;\n"
                        + "\t\t\t\tcolor: #1A1A36;\n"
                        + "\t\t\t\tbackground-color: #14EBAF;\n"
                        + "\t\t\t\tborder: 4px solid #303045;\n"
                        + "\t\t\t\tborder-bottom-width: 8px;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttr {\n"
                        + "\t\t\t\tfont-size: 15px;\n"
                        + "\t\t\t\theight: 28px;\n"
                        + "\t\t\t\tbackground-color: #141428;\n"
                        + "\t\t\t\tborder: 2px solid #303045;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttr:nth-child(odd) {\n"
                        + "\t\t\t\tbackground-color: #1A1A36;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttd {\n"
                        + "\t\t\t\tpadding: 10px;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttr:hover {\n"
                        + "\t\t\t\tfont-size: 16px;\n"
                        + "\t\t\t\tbackground-color: #14EBAF50;\n"
                        + "\t\t\t}\n"
                        + "\t\t\ttd:nth-child(n):hover {\n"
                        + "\t\t\t\tfont-size: 16px;\n"
                        + "\t\t\t\tbackground-color: #14EBAF60;\n"
                        + "\t\t\t}\n"
                        + "\t\t</style>\n";
            end = "\n\t\t\t</thead>\n";
        } else if (codTag == TH) {
            start = "\t\t\t\t<th>\n";
            cont = "\t\t\t\t\t" + cont;
            end = "\n\t\t\t\t</th>\n";
        } else if (codTag == TR) {
            start = "\t\t\t<tr>\n";
            end = "\n\t\t\t</tr>\n";
        } else if (codTag == TD) {
            start = "\t\t\t\t<td>\n";
            cont = "\t\t\t\t\t" + cont;
            end = "\n\t\t\t\t</td>\n";
        } else if (codTag == HEAD) {
            start = "\t<head>\n";
            end = "\n\t</head>\n";
        } else if (codTag == TITLE) {
            start = "\t\t<title>\n";
            cont = "\t\t\t" + cont;
            end = "\n\t\t</title>\n";
        } else if (codTag == BODY) {
            start = "\t<body>\n";
            end = "\n\t</body>\n";
        }  else if (codTag == TABLE) {
            start = "\t\t<TABLE>\n";
            end = "\n\t\t</TABLE>\n";
        }

        sb.append(start);
        sb.append(cont);
        sb.append(end);
    }

    private static String addTag(int codTag, String cont) {
        SB_DUMMY.delete(0, SB_DUMMY.length());
        addTag(codTag, cont, SB_DUMMY);
        return SB_DUMMY.toString();
    }
}
