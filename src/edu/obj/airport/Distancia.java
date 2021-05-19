package edu.obj.airport;

import java.util.ArrayList;

import edu.obj.*;

public class Distancia implements Creable {
    public static final ArrayList<String> origenes = new  ArrayList<>();
    public static final ArrayList<String> destinos = new  ArrayList<>();
    private final String origen;
    private final String destino;
    private final int millas;

    public Distancia(String origen, String destino, int millas) {
        this.origen = origen;
        this.destino = destino;
        this.millas = millas;
        origenes.add(origen);
        destinos.add(destino);
    }

    public static boolean exists(String origen, String destino) {
        try {
            return origenes.contains(origen) && destinos.get(origenes.indexOf(origen)).equals(destino);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getMillas() {
        return this.millas;
    }

    @Override
    public String toString() {
        return String.valueOf(this.millas);
    }

    @Override
    public String getFilename() {
        return "Distancia_" + this.origen + "_" + this.destino;
    }
}
