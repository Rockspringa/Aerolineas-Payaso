package edu.obj.airport;

import java.util.ArrayList;

import edu.obj.Creable;

public class Avion implements Creable {
    private static final ArrayList<Integer> codigos = new ArrayList<>();
    private final String aerolinea;
    private final double gasPerMilla;
    private final int maxGasolina;
    private final int codigo;
    private String aeropuertoActual;
    private Vuelo vuelo;
    private int rows;
    private int cols;
    private int gasolinaActual;
    
    public Avion(String aerolinea, String aeropuertoActual, int codigo, int rows, int cols, int maxGasolina,
            int gasolinaActual, double gasPerMilla) {
        this.aerolinea = aerolinea;
        this.aeropuertoActual = aeropuertoActual;
        this.codigo = codigo;
        this.rows = rows;
        this.cols = cols;
        this.maxGasolina = maxGasolina;
        this.gasolinaActual = gasolinaActual;
        this.gasPerMilla = gasPerMilla;
        codigos.add(codigo);
    }

    public static boolean exists(Integer cod) {
        return codigos.contains(cod);
    }

    public static boolean exists(int cod) {
        return exists(Integer.valueOf(cod));
    }
    
    public String getAerolinea() {
        return this.aerolinea;
    }

    public String getAeropuertoActual() {
        return this.aeropuertoActual;
    }

    public void setAeropuertoActual(String aeropuertoActual) {
        this.aeropuertoActual = aeropuertoActual;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getMaxPasajeros() {
        return (this.cols - 1) * this.rows;
    }

    public int getMaxGasolina() {
        return this.maxGasolina;
    }

    public int getGasolinaActual() {
        return this.gasolinaActual;
    }

    public void setGasolinaActual(int gasolina) {
        if (maxGasolina >= gasolina)
            this.gasolinaActual = gasolina;
    }

    public double getGasPerMilla() {
        return this.gasPerMilla;
    }

    public Vuelo getVuelo() {
        return this.vuelo;
    }

    public void setVuelo(Vuelo nuevoVuelo) {
        this.vuelo = nuevoVuelo;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    @Override
    public String getFilename() {
        return "Avion_" + this.codigo;
    }
}
