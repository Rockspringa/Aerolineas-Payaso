package edu.gui.windows.passengers;

import java.awt.*;
import java.awt.event.*;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import edu.enums.Sexo;
import edu.gui.components.DFrame;
import edu.maker.exp.HTMLMaker;
import edu.maker.imp.ObjectImp;
import edu.obj.items.Pasaporte;
import edu.obj.persis.Matrix;

public class Datos extends DFrame {
    private JEditorPane webPane;
    private JScrollPane scrollPanel;

    public Datos(String ps) {
        super("Datos del pasajero", 535, 590);
        setResizable(true);

        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new BorderLayout());
        win.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));

        webPane = new JEditorPane();
        webPane.setContentType("text/html");
        webPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        webPane.setEditable(false);

        scrollPanel = new JScrollPane(webPane);
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        scrollPanel.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        win.add(scrollPanel, BorderLayout.CENTER);

        new Thread() {
            private DFrame frame;

            @Override
            public void run() {
                StringBuilder web = new StringBuilder();
                Pasaporte pas = null;
                try {
                    pas = (Pasaporte) (ObjectImp.impObj(frame, "Pasaporte_" + ps));
                } catch (NameNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, "No se ha encontrado el archivo",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                }

                Matrix<Object> inf = new Matrix<>(6, 2);
                inf.add("Nombres");
                inf.add(pas.getNombre());
                inf.add("Apellidos");
                inf.add(pas.getApellido());
                inf.add("Fecha de Nacimiento");
                inf.add(pas.getNacimiento().toString());
                inf.add("Nacionalidad");
                inf.add(pas.getNacionalidad());
                inf.add("Estado Civil");
                inf.add(pas.getEstadoCivil());
                inf.add("Sexo");
                inf.add((pas.getSexo() == Sexo.MASCULINO) ? "Masculino" : "Femenino");

                web.append(HTMLMaker.createTable("Datos Personales", inf));

                Matrix<Object> datos = new Matrix<>(3, 2);

                datos.add("Boletos comprados");
                datos.add(String.valueOf(pas.getReservaciones().size()));
                datos.add("Gastos por Tarjeta");
                datos.add(String.valueOf(pas.getTarjeta().getGastos()));
                datos.add("Millas recoridas");
                datos.add(String.valueOf(pas.getMillasRecorridas()));

                web.append(HTMLMaker.createTable("Informacion", datos));

                web = HTMLMaker.getWeb("Datos_Pasajero_" + pas.getNombre(), web);
                webPane.setText(web.toString());
                HTMLMaker.exportarWeb(frame, "Reporte_para_Admin", web);
            }

            public synchronized void start(DFrame frame) {
                this.frame = frame;
                start();
            }

        }.start(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
