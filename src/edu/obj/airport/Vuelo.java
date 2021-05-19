package edu.obj.airport;

import java.time.LocalDate;
import java.util.ArrayList;

import edu.obj.*;

public class Vuelo implements Creable {
    private static final ArrayList<String> codigos = new ArrayList<>();
    private final int precioBoleto;
    private final String avion;
    private final String codigo;
    private final String origen;
    private final String destino;
    private final LocalDate fechaVuelo;

    
    public Vuelo(String codigo, String avion, String origen, String destino,
                    int precioBoleto, LocalDate fechaVuelo) {
        this.avion = avion;
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.fechaVuelo = fechaVuelo;
        this.precioBoleto = precioBoleto;
        Vuelo.codigos.add(codigo);
    }
    
    public static boolean exists(String cod) {
        return codigos.contains(cod);
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getAvion() {
        return this.avion;
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getDestino() {
        return this.destino;
    }

    public int getPrecioBoleto() {
        return this.precioBoleto;
    }

    public LocalDate getFechaVuelo() {
        return this.fechaVuelo;
    }

    @Override
    public String getFilename() {
        return "Vuelo_" + this.codigo;
    }
}
