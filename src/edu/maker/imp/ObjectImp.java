package edu.maker.imp;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.management.AttributeNotFoundException;
import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.maker.exp.HTMLMaker;
import edu.maker.exp.ObjectExp;
import edu.enums.Obj;
import edu.maker.ObjectMaker;
import edu.maker.Persistencia;
import edu.obj.Creable;
import edu.obj.items.Pasaporte;

public class ObjectImp extends Persistencia {
    public static Creable impObj(JFrame frame, String filename) throws NameNotFoundException {
        Creable obj = null;
        if (objFol == null) {
            JOptionPane.showMessageDialog(frame, "Escoja la carpeta en donde se cargaran los binarios",
                    "Informacion que cura", JOptionPane.INFORMATION_MESSAGE);
            objFol = getFolder(frame, "Seleccione la carpeta de destino.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(getPathObj(filename)));) {
            obj = (Creable) (in.readObject());
        } catch (FileNotFoundException e) {
            throw new NameNotFoundException("No se encontraron los archivos requeridos.");
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
    
    public static String impObj(JFrame frame, JEditorPane box) {
        File ret = getTxtFile(frame, "Seleccione el archivo de datos");
        try (FileInputStream fileIn = new FileInputStream(ret);) {
            InputStreamReader reader = new InputStreamReader(fileIn);
            BufferedReader br = new BufferedReader(reader);

            StringBuilder sb = new StringBuilder();
            String line;
            String obj;
            String color;
            int numLine = 0;

            box.setText("");
            while ((line = br.readLine()) != null) {
                ++numLine;
                obj = "Linea " + numLine + ": ";
                try {
                    obj += getObjText(frame, line);
                    color = "#F7F7F7";
                } catch (NameNotFoundException e) {
                    obj += e.getMessage();
                    color = "#B9002D";
                } catch (AttributeNotFoundException e) {
                    obj += e.getMessage();
                    color = "#B9002D";
                } catch (RuntimeException e) {
                    obj += "No se encontro la sintaxis requerida.";
                    color = "#B9002D";
                } catch (Exception e) {
                    obj += "Ya existe es " + e.getMessage();
                    color = "#B9002D";
                }
                
                sb.append(HTMLMaker.fontTag(obj, color, true));
                box.setText(sb.toString());
            }

        } catch (FileNotFoundException exec) {
            JOptionPane.showMessageDialog(frame, "Error 404, file not fount",
                                    "Error 404", JOptionPane.ERROR_MESSAGE);
        } catch (IOException exec) {
            JOptionPane.showMessageDialog(frame, "Error trying to close the file.",
                                    "File Error", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException exec) {}
        return ret.getName();
    }

    private static String getObjText(JFrame frame, String line) throws RuntimeException, Exception {
        String type = line.split("\\(")[0].toLowerCase();
        String[] att = line.split("\\(")[1].split("\\)")[0].split(",");
        String obj;

        switch (type) {
            case "aeropuerto":
                obj = getAeropuerto(att);
                ObjectMaker.createObj(frame, Obj.AEROPUERTO, att);
                break;
            case "aerolinea":
                obj = getAerolinea(att);
                ObjectMaker.createObj(frame, Obj.AEROLINEA, att);
                break;
            case "aerolínea":
                obj = getAerolinea(att);
                ObjectMaker.createObj(frame, Obj.AEROLINEA, att);
                break;
            case "avion":
                obj = getAvion(att);
                ObjectMaker.createObj(frame, Obj.AVION, att);
                break;
            case "distancia":
                obj = getDistancia(att);
                ObjectMaker.createObj(frame, Obj.DISTANCIA, att);
                break;
            case "vuelo":
                obj = getVuelo(att);
                ObjectMaker.createObj(frame, Obj.VUELO, att);
                break;
            case "pasaporte":
                obj = getPasaporte(att);
                ObjectMaker.createObj(frame, Obj.PASAPORTE, att);
                break;
            case "tarjeta":
                obj = getTarjeta(att);
                ObjectMaker.createObj(frame, Obj.TARJETA, att);
                break;
            case "renovacion_pasaporte":
                obj = getRenovacionPasaporte(att);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
                Pasaporte ps = (Pasaporte) (ObjectImp.impObj(frame, "Pasaporte_" + att[0]));
                if (ps.getVencimiento().isBefore(LocalDate.parse(att[1], format))) {
                    ps.setVencimiento(LocalDate.parse(att[1], format));
                    ObjectExp.expObj(frame, ps);
                }
                break;
            case "reservacion":
                obj = getReservacion(att);
                ObjectMaker.createObj(frame, Obj.RESERVACION, att);
                break;
            default:
                throw new NameNotFoundException("No se encontro un objeto que concuerdo con: " + type);
        }
        return obj;
    }

    private static String getAeropuerto(String[] att) throws AttributeNotFoundException {
        if (att.length < 3) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Aeropuerto");
        } else if (att.length > 3) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Aeropuerto");
        }
        return "Se ha creado el aeropuerto \"" + att[0] + "\".";
    }

    private static String getAerolinea(String[] att) throws AttributeNotFoundException {
        if (att.length < 2) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Aerolinea");
        } else if (att.length > 2) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Aerolinea");
        }
        return "Se ha creado la aerolinea \"" + att[1] + "\".";
    }

    private static String getAvion(String[] att) throws AttributeNotFoundException {
        if (att.length < 6) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Avion");
        } else if (att.length > 6) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Avion");
        }
        return "Se ha creado el avion \"" + att[2] + "\".";
    }

    private static String getDistancia(String[] att) throws AttributeNotFoundException {
        if (att.length < 3) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Distancia");
        } else if (att.length > 3) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Distancia");
        }
        return "Se ha creado la distanica de \"" + att[0] + "\" a \"" + att[1] + "\".";
    }

    private static String getVuelo(String[] att) throws AttributeNotFoundException {
        if (att.length < 6) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Vuelo");
        } else if (att.length > 6) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Vuelo");
        }
        return "Se ha creado el \"" + att[0] + "\".";
    }

    private static String getPasaporte(String[] att) throws AttributeNotFoundException {
        if (att.length < 12) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Pasaporte");
        } else if (att.length > 12) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Pasaporte");
        }
        return "Se ha creado el pasaporte de no. \"" + att[0] + "\".";
    }

    private static String getTarjeta(String[] att) throws AttributeNotFoundException {
        if (att.length < 4) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Tarjeta");
        } else if (att.length > 4) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Tarjeta");
        }
        return "Se ha creado la tarjeta de no. \"" + att[0] + "\".";
    }

    private static String getRenovacionPasaporte(String[] att) throws AttributeNotFoundException {
        if (att.length < 2) {
            throw new AttributeNotFoundException("Faltan argumentos para poder renovar el pasaporte.");
        } else if (att.length > 2) {
            throw new AttributeNotFoundException("Sobran argumentos para poder renovar el pasaporte.");
        }
        return "Se ha renovado el pasaporte de no. \"" + att[0] + "\".";
    }

    private static String getReservacion(String[] att) throws AttributeNotFoundException {
        if (att.length < 4) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar la reservacion.");
        } else if (att.length > 4) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar la reservacion.");
        }
        return "Se ha creado la reservacion del pasaporte de no. \"" + att[0] + "\".";
    }
}
