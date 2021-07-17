package edu.maker;

import java.io.FileNotFoundException;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.management.AttributeNotFoundException;
import javax.swing.*;

import edu.enums.*;
import edu.maker.exp.ObjectExp;
import edu.maker.imp.ObjectImp;
import edu.obj.*;
import edu.obj.airport.*;
import edu.obj.items.*;
import edu.obj.persis.Matrix;
import edu.obj.users.Pasajero;

public class ObjectMaker extends Persistencia {
    public static Creable createObj(JFrame frame, Obj tipo, String[] att) throws FileNotFoundException, RuntimeException, Exception {
        Creable obj = null;
        String msg = "No se han encontrado los atributos necesarios";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("d/MM/yyyy");

        if (objFol == null) {
            JOptionPane.showMessageDialog(frame, "Escoja la carpeta en donde se guardaran los binarios",
                    "Informacion que cura", JOptionPane.INFORMATION_MESSAGE);
            objFol = getFolder(frame, "Seleccione la carpeta de destino.");
        }
        
        try {
            switch (tipo) {
                case AEROLINEA:
                    if (att.length != 2)
                        throw new AttributeNotFoundException(msg);
                    obj = crearAerolinea(frame, att[0], att[1]);
                    break;
                case AEROPUERTO:
                    if (att.length != 3)
                        throw new AttributeNotFoundException(msg);
                    obj = crearAeropuerto(frame, att[0], att[1], att[2]);
                    break;
                case AVION:
                    if (att.length == 6)
                        obj = crearAvion(frame, att[0], att[1], att[2],
                                    Integer.parseInt(att[3]), Integer.parseInt(att[4]),
                                    Integer.parseInt(att[5]));
                                
                    else if (att.length == 8)
                        obj = crearAvion(frame, att[0], att[1], att[2],
                                    Integer.parseInt(att[3]), Integer.parseInt(att[4]),
                                    Integer.parseInt(att[5]), Integer.parseInt(att[6]),
                                    Integer.parseInt(att[7]));
                    
                    else
                        throw new AttributeNotFoundException(msg);
                    
                    break;
                case DISTANCIA:
                        if (att.length != 3)
                            throw new AttributeNotFoundException(msg);
                        obj = crearDistancia(frame, att[0], att[1], Integer.parseInt(att[2]));
                    break;
                case PASAPORTE:
                    if (att.length == 12)
                        obj = crearPasaporte(frame, Integer.parseInt(att[0]), att[1], LocalDate.parse(att[2], f),
                                    att[3], att[4], att[5], att[6], Sexo.parse(att[7]), LocalDate.parse(att[8], f),
                                    LocalDate.parse(att[9], f), att[10], Double.parseDouble(att[11]));
                    
                    else if (att.length == 11)
                        obj = crearPasaporte(frame, Integer.parseInt(att[0]), LocalDate.parse(att[1], f), att[2],
                                    att[3], att[4], att[5], Sexo.parse(att[6]), LocalDate.parse(att[7], f),
                                    LocalDate.parse(att[8], f), att[9], Double.parseDouble(att[10]));

                    else
                        throw new AttributeNotFoundException(msg);

                    break;
                case RESERVACION:
                        if (att.length != 4)
                            throw new AttributeNotFoundException(msg);
                        obj = crearReservacion(frame, Integer.parseInt(att[0]), att[1],
                                    Long.parseLong(att[2]), Integer.parseInt(att[3]));
                    break;
                case TARJETA:
                    if (att.length != 4)
                        throw new AttributeNotFoundException(msg);
                    obj = crearTarjeta(frame, Long.parseLong(att[0]), Integer.parseInt(att[1]),
                                Integer.parseInt(att[2]), Integer.parseInt(att[3]));
                    break;
                case VUELO:
                    if (att.length != 6)
                        throw new AttributeNotFoundException(msg);
                    obj = crearVuelo(frame, att[0], att[1],
                                att[2], att[3], Integer.parseInt(att[4]), LocalDate.parse(att[5], f));
                    break;
                default:
                    break;
            }
        } catch (AttributeNotFoundException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } ObjectExp.expObj(frame, obj);
        return obj;
    }

    private static Aeropuerto crearAeropuerto(JFrame frame, String nombre, String ciudad, String pais) throws Exception {
        Aeropuerto aer = null;
        if (!Aeropuerto.exists(nombre)) {
            aer = new Aeropuerto(nombre, ciudad, pais);
        } else {
            throw new Exception("e Aeropuerto");
        } return aer;
    }

    private static Aerolinea crearAerolinea(JFrame frame, String aeropuerto, String nombre) throws Exception {
        Aerolinea aer = null;
        Aeropuerto ae = ((Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + aeropuerto)));
        if (!Aerolinea.exists(nombre)) {
            aer = new Aerolinea(aeropuerto, nombre);
            ae.getAerolineas().add(nombre);
            ObjectExp.expObj(frame, ae);
        } else {
            aer = (Aerolinea) ObjectImp.impObj(frame, "Aerolinea_" + nombre);
            if (aer.existsAeropuerto(aeropuerto)) {
                throw new Exception("a Aerolinea");
            } else {
                aer.addAeropuerto(aeropuerto);
                ae.getAerolineas().add(nombre);
                ObjectExp.expObj(frame, ae);
            }
        } return aer;
    }

    private static Avion crearAvion(JFrame frame, String aerolinea, String aeropuertoActual, String codigo,
                int capacidad, int maxGasolina, int gasPerMilla) throws Exception {
        Avion avi = null;
        if (!Avion.exists(codigo)) {
            Aerolinea lin = ((Aerolinea) (ObjectImp.impObj(frame, "Aerolinea_" + aerolinea)));
            Aeropuerto aer = ((Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + aeropuertoActual)));
            if (aer.getAerolineas().contains(aerolinea)) {
                avi = new Avion(aerolinea, aeropuertoActual, codigo, capacidad, maxGasolina, gasPerMilla);
                aer.getAviones().add(codigo);
                lin.addAvion(codigo);
                ObjectExp.expObj(frame, aer);
                ObjectExp.expObj(frame, lin);
            }
        } else {
            throw new Exception("e Avion");
        } return avi;
    }

    private static Avion crearAvion(JFrame frame, String aerolinea, String aeropuertoActual, String codigo,
                int r, int c, int pas, int maxGasolina, int gasPerMilla) throws Exception {
        Avion avi = null;
        if (!Avion.exists(codigo)) {
            Aerolinea lin = ((Aerolinea) (ObjectImp.impObj(frame, "Aerolinea_" + aeropuertoActual)));
            Aeropuerto aer = (Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + aeropuertoActual));
            if (aer.getAerolineas().contains(aerolinea)) {
                avi = new Avion(aerolinea, aeropuertoActual, codigo, r, c, pas, maxGasolina, gasPerMilla);
                aer.getAviones().add(codigo);
                lin.addAvion(codigo);
                ObjectExp.expObj(frame, aer);
                ObjectExp.expObj(frame, lin);
            }
        } else {
            throw new Exception("e Avion");
        } return avi;
    }

    private static Distancia crearDistancia(JFrame frame, String origen, String destino, int millas) throws Exception {
        Distancia dis = null;
        if (!Distancia.exists(origen, destino)) {
            Aeropuerto aer = ((Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + origen)));
            dis = new Distancia(origen, destino, millas);
            aer.getDistancias().add(dis);
            ObjectExp.expObj(frame, aer);
        } else {
            throw new Exception("a Distancia");
        } return dis;
    }

    private static Vuelo crearVuelo(JFrame frame, String codigo, String avion, String origen, String destino,
                int precioBoleto, LocalDate fechaVuelo) throws Exception {
        Vuelo vue = null;
        if (!Vuelo.exists(codigo)) {
            Avion av = (Avion) (ObjectImp.impObj(frame, "Avion_" + avion));
            Distancia dis = (Distancia) (ObjectImp.impObj(frame, "Distancia_" + origen + "_" + destino));
            Aerolinea aer = (Aerolinea) (ObjectImp.impObj(frame, "Aerolinea_" + av.getAerolinea()));
            Aeropuerto ap = (Aeropuerto) (ObjectImp.impObj(frame, "Aeropuerto_" + origen));

            vue = new Vuelo(codigo, avion, origen, destino, precioBoleto, fechaVuelo);
            av.setVuelo(vue);
            av.addConsumo(av.getGasPerMilla() * dis.getMillas(), fechaVuelo);
            aer.addVuelo(codigo, fechaVuelo);
            ap.addGanancias(precioBoleto, fechaVuelo);
            ap.addVuelo(codigo);
            ObjectExp.expObj(frame, av);
            ObjectExp.expObj(frame, aer);
            ObjectExp.expObj(frame, ap);
        } else {
            throw new Exception("e Vuelo");
        } return vue;
    }

    private static Pasaporte crearPasaporte(JFrame frame, int numPasaporte, String contraseña, LocalDate nacimiento,
                String nacionalidad, String estadoCivil, String nombre, String apellido, Sexo sexo, LocalDate vencimiento,
                LocalDate emision, String paisActual, double millasRecorridas) throws Exception {
        Pasaporte pas = null;
        if (!Pasaporte.exists(numPasaporte)) {
            pas = new Pasaporte(numPasaporte, contraseña, nacimiento, nacionalidad, estadoCivil,
                        nombre, apellido, sexo, vencimiento, emision, paisActual, millasRecorridas);
            new Pasajero(pas);
        } else {
            throw new Exception("e Pasaporte");
        } return pas;
    }

    private static Pasaporte crearPasaporte(JFrame frame, int numPasaporte, LocalDate nacimiento, String nacionalidad,
                String estadoCivil, String nombre, String apellido, Sexo sexo, LocalDate vencimiento,
                LocalDate emision, String paisActual, double millasRecorridas) throws Exception {
        Pasaporte pas = null;
        if (!Pasaporte.exists(numPasaporte)) {
            pas = new Pasaporte(numPasaporte, nacimiento, nacionalidad, estadoCivil,
                        nombre, apellido, sexo, vencimiento, emision, paisActual, millasRecorridas);
        } else {
            throw new Exception("e Pasaporte");
        } return pas;
    }

    private static Reservacion crearReservacion(JFrame frame, int pasaporte, String vuelo, long tarjeta, int numAsiento) throws Exception {
        Reservacion res = null;
        if (!Reservacion.exists(pasaporte, vuelo)) {
            Pasaporte ps = (Pasaporte) (ObjectImp.impObj(frame, "Pasaporte_" + pasaporte));
            Vuelo vu = (Vuelo) (ObjectImp.impObj(frame, "Vuelo_" + vuelo));
            Avion av = (Avion) (ObjectImp.impObj(frame, "Avion_" + vu.getAvion()));
            Matrix<String> ax = Avion.asientos.get(av.getCodigo());
            int r = (int) ((numAsiento - 1) / (av.getCols() - 1));
            int c = ((numAsiento - 1) % av.getCols() >= 2) ? (numAsiento - 1) % av.getCols() + 1
                                                            : (numAsiento - 1) % av.getCols();

            if (ps.getVencimiento().isBefore(LocalDate.now())) {
                Pasajero.denegados.add(Integer.valueOf(pasaporte));
                Pasajero.aerolinea.add(av.getAerolinea());
            } else if (ps != null && ax.get(r, c).equals("b")) {
                res = new Reservacion(pasaporte, vuelo, tarjeta, numAsiento);
                ax.set("r", r, c);
                av.addPasajero();
                vu.addNombre(ps.getNombre(), ps.getApellido());
                ps.getReservaciones().add(res);
                ObjectExp.expObj(frame, ps);
                ObjectExp.expObj(frame, av);
                ObjectExp.expObj(frame, vu);
            }
            System.out.print("");
        } else {
            throw new Exception("a Reservacion");
        } return res;
    }

    private static Tarjeta crearTarjeta(JFrame frame, long numTarjeta, int pasaporte, int dinero, int cvc) throws Exception {
        Tarjeta tar = null;
        if (!Tarjeta.exists(numTarjeta)) {
            Pasaporte ps = (Pasaporte) (ObjectImp.impObj(frame, "Pasaporte_" + pasaporte));
            tar = new Tarjeta(numTarjeta, pasaporte, dinero, cvc);
            ps.setTarjeta(numTarjeta);
            ObjectExp.expObj(frame, ps);
        } else {
            throw new Exception("a Tarjeta");
        } return tar;
    }
}
