package edu.obj.items;

import java.util.ArrayList;

import edu.obj.Creable;

public class Reservacion implements Creable {
    private static final ArrayList<Integer> numPas = new ArrayList<>();
    private static final ArrayList<Integer> numVue = new ArrayList<>();
    private final int pasaporte;
    private final int vuelo;
    private final int tarjeta;
    private final int numAsiento;
    
    public Reservacion(int pasaporte, int vuelo, int tarjeta, int numAsiento) {
        this.pasaporte = pasaporte;
        this.vuelo = vuelo;
        this.tarjeta = tarjeta;
        this.numAsiento = numAsiento;
        numPas.add(Integer.valueOf(pasaporte));
        numVue.add(Integer.valueOf(vuelo));
    }

    public static boolean exists(Integer pasaporte, Integer vuelo) {
        try {
            return numPas.contains(pasaporte) && numVue.get(numPas.indexOf(pasaporte)).equals(vuelo);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean exists(int pasaporte, int vuelo) {
        return exists(Integer.valueOf(pasaporte), Integer.valueOf(vuelo));
    }
    
    public int getPasaporte() {
        return this.pasaporte;
    }

    public int getVuelo() {
        return this.vuelo;
    }

    public int getTarjeta() {
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
