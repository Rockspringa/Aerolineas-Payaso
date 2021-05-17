package edu.maker.imp;

import java.io.*;
import java.util.regex.PatternSyntaxException;

import javax.management.AttributeNotFoundException;
import javax.naming.NameNotFoundException;
import javax.swing.*;

import edu.maker.*;
import edu.maker.exp.HTMLMaker;

public class ObjectMaker extends Persistencia {
    public static void cargarObjetos(JFrame frame, JEditorPane box) {
        try (FileInputStream fileIn = new FileInputStream(getTxtFile(frame, "Seleccione el archivo de datos"));) {
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
                    obj += getObject(line);
                    color = "#F7F7F7";
                } catch (NameNotFoundException e) {
                    obj += e.getMessage();
                    color = "#B9002D";
                } catch (AttributeNotFoundException e) {
                    obj += e.getMessage();
                    color = "#B9002D";
                } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException e) {
                    obj += "No se encontro la sintaxis requerida.";
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
    }

    private static String getObject(String line) throws NameNotFoundException, AttributeNotFoundException {
        String type = line.split("\\(")[0].toLowerCase();
        String att = line.split("\\(")[1].split("\\)")[0];
        String obj;

        switch (type) {
            case "aeropuerto":
                obj = getAeropuerto(att);
                break;
            case "aerolinea":
                obj = getAerolinea(att);
                break;
            case "avion":
                obj = getAvion(att);
                break;
            case "distancia":
                obj = getDistancia(att);
                break;
            case "vuelo":
                obj = getVuelo(att);
                break;
            case "pasaporte":
                obj = getPasaporte(att);
                break;
            case "tarjeta":
                obj = getTarjeta(att);
                break;
            case "renovacion_pasaporte":
                obj = getRenovacionPasaporte(att);
                break;
            case "reservacion":
                obj = getReservacion(att);
                break;
            default:
                throw new NameNotFoundException("No se encontro un objeto que concuerdo con: " + type);
        }
        return obj;
    }

    private static String getAeropuerto(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 3) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Aeropuerto");
        } else if (attr.length > 3) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Aeropuerto");
        }
        return "Se ha creado el aeropuerto \"" + attr[0] + "\".";
    }

    private static String getAerolinea(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 2) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Aerolinea");
        } else if (attr.length > 2) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Aerolinea");
        }
        return "Se ha creado la aerolinea \"" + attr[1] + "\".";
    }

    private static String getAvion(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 6) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Avion");
        } else if (attr.length > 6) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Avion");
        }
        return "Se ha creado el avion.";
    }

    private static String getDistancia(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 3) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Distancia");
        } else if (attr.length > 3) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Distancia");
        }
        return "Se ha creado la distanica de \"" + attr[0] + "\" a \"" + attr[1] + "\".";
    }

    private static String getVuelo(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 6) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Vuelo");
        } else if (attr.length > 6) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Vuelo");
        }
        return "Se ha creado el \"" + attr[0] + "\".";
    }

    private static String getPasaporte(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 12) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Pasaporte");
        } else if (attr.length > 12) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Pasaporte");
        }
        return "Se ha creado el pasaporte de no. \"" + attr[0] + "\".";
    }

    private static String getTarjeta(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 4) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar el objeto: Tarjeta");
        } else if (attr.length > 4) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar el objeto: Tarjeta");
        }
        return "Se ha creado la tarjeta de no. \"" + attr[0] + "\".";
    }

    private static String getRenovacionPasaporte(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 2) {
            throw new AttributeNotFoundException("Faltan argumentos para poder renovar el pasaporte.");
        } else if (attr.length > 2) {
            throw new AttributeNotFoundException("Sobran argumentos para poder renovar el pasaporte.");
        }
        return "Se ha renovado el pasaporte de no. \"" + attr[0] + "\".";
    }

    private static String getReservacion(String att) throws AttributeNotFoundException {
        String[] attr = att.split(",");
        if (attr.length < 4) {
            throw new AttributeNotFoundException("Faltan argumentos para cargar la reservacion.");
        } else if (attr.length > 4) {
            throw new AttributeNotFoundException("Sobran argumentos para cargar la reservacion.");
        }
        return "Se ha creado la reservacion del pasaporte de no. \"" + attr[0] + "\".";
    }
}
