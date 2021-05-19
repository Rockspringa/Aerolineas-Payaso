package edu.obj.users;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.items.*;
import edu.obj.persis.Lista;
import edu.obj.*;

public class Pasajero extends User {
    private Pasaporte pasaporte;
    private static final ArrayList<String> pasas = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();
    public static final ArrayList<Integer> denegados = new ArrayList<>();
    public static final ArrayList<String> aerolinea = new ArrayList<>();

    private static final Lista civil = new Lista();
    private static final Lista edades = new Lista();
    private static final Lista nacios = new Lista();

    private static int M;
    private static int F;
    private static int S;
    
    public Pasajero(Pasaporte pasaporte) {
        super(pasaporte.getNumeroPasaporte() + "", pasaporte.getContraseña());
        this.pasaporte = pasaporte;
        pasas.add(String.valueOf(pasaporte.getNumeroPasaporte()));
        pass.add(pasaporte.getContraseña());

        civil.add(pasaporte.getEstadoCivil());
        
        int ed = LocalDate.now().getYear() - pasaporte.getNacimiento().getYear();
        edades.add(ed + "");

        nacios.add(pasaporte.getNacionalidad());

        switch (pasaporte.getSexo()) {
            case MASCULINO:
                M++; break;
            case FEMENINO:
                F++; break;
            case SIN_DEFINIR:
                S++; break;
            default:
                break;
        }
    }

    public static String getNacionalidad() {
        String nac = null;
        String maxNac = null;
        int nacN = 0;
        int maxN = 0;
        int n = 0;

        while (true) {
            try {
                String nacRi = nacios.get(n++);
                if (nacRi != null && nacRi.equals(nac)) {
                    nacN++;
                    if (nacN > maxN) {
                        maxN = nacN;
                        maxNac = nac;
                    }
                } else {
                    nac = nacRi;
                    nacN = 1;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        } return (maxNac != null) ? maxNac : "Indefinido";
    }

    public static String getEstadoCIvil() {
        String est = null;
        String maxEst = null;
        int estN = 0;
        int maxN = 0;
        int n = 0;

        while (true) {
            try {
                String estR = nacios.get(n++);
                if (estR.equals(est)) {
                    estN++;
                    if (estN > maxN) {
                        maxN = estN;
                        maxEst = est;
                    }
                } else {
                    est = estR;
                    estN = 1;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        } return (maxEst != null) ? maxEst : "Indefinido";
    }

    public static String getEdad() {
        String ed = null;
        String maxEd = null;
        int edN = 0;
        int maxN = 0;
        int n = 0;

        while (true) {
            try {
                String edR = nacios.get(n++);
                if (edR.equals(ed)) {
                    edN++;
                    if (edN > maxN) {
                        maxN = edN;
                        maxEd = ed;
                    }
                } else {
                    ed = edR;
                    edN = 1;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        } return (maxEd != null) ? maxEd : "Indefinido";
    }

    public static String getSexo() {
        if (M > F && M > S) {
            return "Masculino";
        } else if (F > M && F > S) {
            return "Femenino";
        } else {
            return "Sin definir";
        }
    } 

    public static boolean exists(String pasaporte, String contraseña) {
        try {
            return pasas.contains(pasaporte)
                    && pass.get(pasas.indexOf(pasaporte)).equals(contraseña);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean exists(String pasaporte) {
        return pasas.contains(pasaporte);
    }

    public Pasaporte getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Override
    public String getFilename() {return null;}
}
