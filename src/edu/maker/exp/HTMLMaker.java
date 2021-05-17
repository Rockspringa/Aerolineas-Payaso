package edu.maker.exp;

import edu.maker.Persistencia;
import edu.obj.Matrix;
import javax.swing.*;
import java.io.*;

public class HTMLMaker extends Persistencia {
    private static final StringBuilder SB_DUMMY = new StringBuilder();

    private static final int HEAD = 0;
    private static final int TITLE = 1;
    private static final int BODY = 2;
    private static final int TABLE = 3;
    private static final int HEADER = 4;
    private static final int TH = 5;
    private static final int TR = 6;
    private static final int TD = 7;
    
    private static void guardarArchivo(File file, String cont) {
        try (BufferedWriter bfWriter = new BufferedWriter(new FileWriter(file));) {
            bfWriter.write(cont);
            bfWriter.flush();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "La exportacion del archivo ha fallado o interrumpido.",
                                                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {}
    }

    public static StringBuilder getWeb(StringBuilder body) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n<html>\n");
        addTag(HEAD, addTag(TITLE, "Estadisticas y Reportes"), sb);
        sb.append(body);
        sb.append("</html>");

        return sb;
    }

    public static void exportarWeb(JFrame frame, StringBuilder web) {
        File folder = getFolder(frame, "Seleccione la carpeta de destino.");
        File file = new File(folder.getPath() + "\\" + "Estadisticas_Reportes.html");
        guardarArchivo(file, web.toString());
    }

    public static StringBuilder createTable(String header, Matrix<Object> datos) {
        StringBuilder sb = new StringBuilder();
        addTag(BODY, addTag(HEADER, header) + addTag(TABLE, addTable(datos)), sb);
        return sb;
    }

    private static String addTable(Matrix<Object> datos) {
        int rows = datos.length()[0];
        int cols = datos.length()[1];
        StringBuilder sbTr = new StringBuilder();
        StringBuilder sbTd = new StringBuilder();
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                if (n == 0) {
                    addTag(TH, datos.get(m, n).toString(), sbTd);
                } else {
                    addTag(TD, datos.get(m, n).toString(), sbTd);
                }
            }
            addTag(TR, sbTd.toString(), sbTr);
            sbTd.delete(0, sbTd.length());
        }
        return sbTr.toString();
    }

    private static void addTag(int codTag, String cont, StringBuilder sb) {
        String start = "";
        String end = "";

        if (codTag == TH) {
            start = "\t\t\t\t<th>\n";
            cont = "\t\t\t\t\t" + cont;
            end = "\n\t\t\t\t</th>\n";
        } else if (codTag == TR) {
            start = "\t\t\t<tr>\n";
            end = "\n\t\t\t</tr>\n";
        } else if (codTag == TD) {
            start = "\t\t\t\t<td>\n";
            end = "\n\t\t\t\t</td>\n";
        } else if (codTag == HEADER) {
            start = "\t\t\t\t<header align=\"center\">\n";
            cont = "\t\t\t\t\t<h1>" + cont + "</h1>";
            end = "\n\t\t\t\t</header>\n";
        } else if (codTag == HEAD) {
            start = "\t<head>\n"
                    + "\t\t<style>\n"
                    + "\t\t\tbody {\n"
                    + "\t\t\t\tcolor: #EEEEEE;\n"
                    + "\t\t\t\tbackground-color: #303045;\n"
                    + "\t\t\t}\n"
                    + "\t\t\theader {\n"
                    + "\t\t\t\tborder-bottom: 15px solid #303045;\n"
                    + "\t\t\t\tborder-top: 15px solid #303045;\n"
                    + "\t\t\t}\n"
                    + "\t\t\tdiv {\n"
                    + "\t\t\t\theight: 20px;\n"
                    + "\t\t\t}\n"
                    + "\t\t\th1 {\n"
                    + "\t\t\t\tmargin: 0px auto;\n"
                    + "\t\t\t\twidth: 80%;\n"
                    + "\t\t\t\theight: 120%;\n"
                    + "\t\t\t\tbackground-color: #1A1A36;\n"
                    + "\t\t\t\tborder: 3px solid #1A1A36;\n"
                    + "\t\t\t\tborder-radius: 20px;\n"
                    + "\t\t\t\tfont-size: 2.8em;\n"
                    + "\t\t\t}\n"
                    + "\t\t\ttable {\n"
                    + "\t\t\t\twidth: 100%;\n"
                    + "\t\t\t\tborder-collapse: collapse;\n"
                    + "\t\t\t\tborder: 3px solid #303045;\n"
                    + "\t\t\t}\n"
                    + "\t\t\theader:hover + table {\n"
                    + "\t\t\t\tborder-color: #1A4D48;\n"
                    + "\t\t\t}\n"
                    + "\t\t\tth {\n"
                    + "\t\t\t\tfont-weight: bold;\n"
                    + "\t\t\t\tfont-size: 24px;\n"
                    + "\t\t\t\tcolor: #1A1A36;\n"
                    + "\t\t\t\tbackground-color: #14EBAF;\n"
                    + "\t\t\t\tborder: 2px solid #303045;\n"
                    + "\t\t\t}\n"
                    + "\t\t\ttr {\n"
                    + "\t\t\t\tfont-weight: bold;\n"
                    + "\t\t\t\ttext-align: center;\n"
                    + "\t\t\t\tfont-size: 20px;\n"
                    + "\t\t\t\theight: 30px;\n"
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
                    + "\t\t\t\tbackground-color: #14EBAF50;\n"
                    + "\t\t\t}\n"
                    + "\t\t\ttd:nth-child(n):hover {\n"
                    + "\t\t\t\tbackground-color: #14EBAF60;\n"
                    + "\t\t\t}\n"
                    + "\t\t</style>\n";
            end = "\n\t</head>\n";
        } else if (codTag == TITLE) {
            start = "\t\t<title>\n";
            cont = "\t\t\t" + cont;
            end = "\n\t\t</title>\n";
        } else if (codTag == BODY) {
            start = "\t<body>\n";
            end = "\n\t</body>\n";
        } else if (codTag == TABLE) {
            start = "\t\t<table>\n";
            end = "\n\t\t</table>\n\t\t<div></div>\n";
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

    public static String fontTag(String text, String color, boolean breakline) {
        String tag = "<font color=\"" + color + "\">" + text + "</font>";
        tag += (breakline) ? "<br>" : "";
        return tag;
    }
}
