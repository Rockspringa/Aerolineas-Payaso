package edu.gui.windows.airport.admin;

import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import edu.enums.Obj;
import edu.gui.components.DFrame;
import edu.maker.exp.HTMLMaker;
import edu.maker.imp.ObjectImp;
import edu.obj.Aeropuerto;
import edu.obj.airport.Avion;
import edu.obj.persis.Matrix;
import edu.obj.users.Pasajero;

public class Reporte extends DFrame {
    private JEditorPane webPane;
    private JScrollPane scrollPanel;
    private LocalDate date1;
    private LocalDate date2;

    public Reporte(DFrame frame, Obj o) {
        super("Reportes", 535, 590);
        setOpenFrame(frame);
        setResizable(true);

        JPanel win = (JPanel) (this.getContentPane());
        win.setLayout(new BorderLayout());
        win.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));

        webPane = new JEditorPane();
		webPane.setContentType("text/html");
        webPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        webPane.setEditable(false);
        
        scrollPanel = new JScrollPane(webPane);
        scrollPanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
        scrollPanel.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
        win.add(scrollPanel, BorderLayout.CENTER);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("d/MM/yyyy");
        date1 = null;
        while (true) {
            String fecha = JOptionPane.showInputDialog(this, "Ingrese la fecha desde la cual empezara el reporte."
                                        + "En formato dd/mm/yy", "", JOptionPane.PLAIN_MESSAGE);

            try {
                date1 = LocalDate.parse(fecha, f);
                break;
            } catch (DateTimeException e) {}
        }

        date2 = null;
        while (true) {
            String fecha = JOptionPane.showInputDialog(this, "Ingrese la fecha hasta la cual terminara el reporte."
                                        + "En formato dd/mm/yy", "", JOptionPane.PLAIN_MESSAGE);

            try {
                date2 = LocalDate.parse(fecha, f);
                if (date2.isBefore(date1) || date2.isEqual(date1)) {
                    throw new DateTimeException("Mensaje");
                }
                break;
            } catch (DateTimeException e) {}
        }

        new Thread() {
            private DFrame frame;

            @Override
            public void run() {
                if (o == Obj.ADMINISTRADOR) {
                    StringBuilder web = new StringBuilder();
                    Matrix<Object> datosPas = new Matrix<>(4, 2);

                    datosPas.add("Estado civil que viaja mas");
                    datosPas.add(Pasajero.getEstadoCIvil());
                    datosPas.add("Edad que viaja mas");
                    datosPas.add(Pasajero.getEdad());
                    datosPas.add("Nacionalidad que viaja mas");
                    datosPas.add(Pasajero.getNacionalidad());
                    datosPas.add("Sexo que viaja mas");
                    datosPas.add(Pasajero.getSexo());

                    web.append(HTMLMaker.createTable("Reporte por pasajeros", datosPas));
                    
                    Matrix<Object> datosAer = new Matrix<>(0, 0);

                    web.append(HTMLMaker.createTable("Reporte por Aerolinea", datosAer));
                    
                    Matrix<Object> datosVue = new Matrix<>(0, 0);

                    web.append(HTMLMaker.createTable("Reporte por Vuelo", datosVue));
                    
                    ArrayList<String> conAvion = new ArrayList<>();

                    ArrayList<Avion> avs = new ArrayList<>();
                    for (int x = 0; x < Avion.codigos.size(); x++) {
                        try {
                            avs.add((Avion) (ObjectImp.impObj(frame, "Avion_" + Avion.codigos.get(x))));
                        } catch (NameNotFoundException e) {}
                    }

                    for (int x = 0; x < avs.size(); x++) {
                        ArrayList<Integer> con = avs.get(x).getConsumo();
                        ArrayList<LocalDate> das = avs.get(x).getFechasConsumo();
                        int consumo = 0;

                        for (int y = 0; y < con.size(); y++) {
                            if (das.get(y).isAfter(date1) && das.get(y).isBefore(date2)) {
                                consumo += con.get(y);
                            }
                        }
                        conAvion.add("Avion " + avs.get(x).getCodigo());
                        conAvion.add(consumo + " de gasolina consumida");
                    }
                    Matrix<Object> datosAvion = new Matrix<>(conAvion.size() / 2, 2);
                    
                    for (int x = 0; x < conAvion.size(); x++) {
                        datosAvion.add(conAvion.get(x));
                    }

                    web.append(HTMLMaker.createTable("Reporte por Aviones", datosAvion));
                    
                    ArrayList<String> conAer = new ArrayList<>();

                    ArrayList<Aeropuerto> aer = new ArrayList<>();
                    for (int x = 0; x < Avion.codigos.size(); x++) {
                        try {
                            aer.add((Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + Aeropuerto.nombres.get(x))));
                        } catch (NameNotFoundException e) {}
                    }

                    for (int x = 0; x < aer.size(); x++) {
                        ArrayList<Integer> con = aer.get(x).getGanancias();
                        ArrayList<LocalDate> das = aer.get(x).getFechas();
                        int gan = 0;

                        for (int y = 0; y < con.size(); y++) {
                            if (das.get(y).isAfter(date1) && das.get(y).isBefore(date2)) {
                                gan += con.get(y);
                            }
                        }
                        conAer.add("Aeropuerto " + aer.get(x).getNombre());
                        conAer.add("$" + gan);
                    }
                    Matrix<Object> datosAerop = new Matrix<>(conAer.size() / 2, 2);

                    for (int x = 0; x < conAer.size(); x++) {
                        datosAerop.add(conAer.get(x));
                    }

                    web.append(HTMLMaker.createTable("Reporte por Aeropuerto", datosAerop));

                    web = HTMLMaker.getWeb("Reporte_para_Admin", web);
                    webPane.setText(web.toString());
                    HTMLMaker.exportarWeb(frame, "Reporte_para_Admin", web);
                }
            }

            public synchronized void start(DFrame frame) {
                this.frame = frame;
                start();
            }
        }.start(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
