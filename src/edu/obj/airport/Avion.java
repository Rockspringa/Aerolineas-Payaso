package edu.obj.airport;

public class Avion {
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
}
