package edu.obj.items;

import java.util.ArrayList;

import edu.obj.Creable;

public class Reservacion implements Creable {
    private static final ArrayList<Integer> numPas = new ArrayList<>();
    private static final ArrayList<String> numVue = new ArrayList<>();
    private final int pasaporte;
    private final String vuelo;
    private final long tarjeta;
    private final int numAsiento;
    
    public Reservacion(int pasaporte, String vuelo, long tarjeta, int numAsiento) {
        this.pasaporte = pasaporte;
        this.vuelo = vuelo;
        this.tarjeta = tarjeta;
        this.numAsiento = numAsiento;
        numPas.add(Integer.valueOf(pasaporte));
        numVue.add(vuelo);
    }

    public static boolean exists(Integer pasaporte, String vuelo) {
        try {
            return numPas.contains(pasaporte) && numVue.get(numPas.indexOf(pasaporte)).equals(vuelo);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean exists(int pasaporte, String vuelo) {
        return exists(Integer.valueOf(pasaporte), vuelo);
    }
    
    public int getPasaporte() {
        return this.pasaporte;
    }

    public String getVuelo() {
        return this.vuelo;
    }

    public long getTarjeta() {
        return this.tarjeta;
    }

    public int getNumAsiento() {
        return this.numAsiento;
    }

    @Override
    public String getFilename() {
        return "Reservacion_" + this.pasaporte + "_" + this.vuelo;
    }
}
