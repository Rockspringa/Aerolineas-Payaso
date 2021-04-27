package edu.obj.airport;

import edu.obj.Aeropuerto;

public class Avion {
    private final Aerolinea aerolinea;
    private final double gasPerMilla;
    private final int maxGasolina;
    private final int codigo;
    private Aeropuerto aeropuertoActual;
    private int maxPasajeros;
    private int gasolinaActual;
    
    public Avion(Aerolinea aerolinea, Aeropuerto aeropuertoActual, int codigo, int maxPasajeros, int maxGasolina,
            int gasolinaActual, double gasPerMilla) {
        this.aerolinea = aerolinea;
        this.aeropuertoActual = aeropuertoActual;
        this.codigo = codigo;
        this.maxPasajeros = maxPasajeros;
        this.maxGasolina = maxGasolina;
        this.gasolinaActual = gasolinaActual;
        this.gasPerMilla = gasPerMilla;
    }

    public String getAerolinea() {
        return this.aerolinea.getNombre();
    }

    public String getAeropuertoActual() {
        return this.aeropuertoActual.getNombre();
    }

    public void setAeropuertoActual(Aeropuerto aeropuertoActual) {
        this.aeropuertoActual = aeropuertoActual;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getMaxPasajeros() {
        return this.maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
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
}
