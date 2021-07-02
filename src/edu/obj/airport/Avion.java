package edu.obj.airport;

import java.time.LocalDate;
import java.util.*;

import edu.obj.Creable;
import edu.obj.persis.Matrix;

public class Avion implements Creable {
    public static final ArrayList<String> codigos = new ArrayList<>();
    public static HashMap<String, Matrix<String>> asientos;
    private final String aerolinea;
    private final int gasPerMilla;
    private final int maxGasolina;
    private final String codigo;
    private ArrayList<LocalDate> fechaConsumo = new ArrayList<>();
    private ArrayList<Integer> consumida = new ArrayList<>();
    private String aeropuertoActual;
    private Vuelo vuelo;
    private int rows;
    private int cols;
    private int pas;
    private int cant = 0;

    static {
        asientos = new HashMap<>();
    }

    public Avion(String aerolinea, String aeropuertoActual, String codigo, int capacidad, int maxGasolina,
            int gasPerMilla) {
        this.aerolinea = aerolinea;
        this.aeropuertoActual = aeropuertoActual;
        this.codigo = codigo;
        if (capacidad != 200) {
            this.rows = (int) (Math.sqrt(capacidad));
            this.cols = (rows * rows >= capacidad) ? rows
                    : rows + (int) ((capacidad - rows * rows) / rows - 1.999999999999999999999);
        } else {
            this.rows = 50;
            this.cols = 6;
        }
        this.pas = 2;
        this.maxGasolina = maxGasolina;
        this.gasPerMilla = gasPerMilla;
        codigos.add(codigo);
        asientos.put(codigo, llenarAsientos(rows, cols));
    }

    public Avion(String aerolinea, String aeropuertoActual, String codigo, int r, int c, int pas, int maxGasolina,
            int gasPerMilla) {
        this.aerolinea = aerolinea;
        this.aeropuertoActual = aeropuertoActual;
        this.codigo = codigo;
        this.rows = r;
        this.cols = c;
        this.pas = pas;
        this.maxGasolina = maxGasolina;
        this.gasPerMilla = gasPerMilla;
        codigos.add(codigo);
        asientos.put(codigo, llenarAsientos(rows, cols, pas));
    }

    private static Matrix<String> llenarAsientos(int rows, int cols, int pas) {
        Matrix<String> as = new Matrix<>(rows, cols);
        int[] l = as.length();
        for (int m = 0; m < l[0]; m++) {
            for (int n = 0; n < l[1]; n++) {
                as.set((pas == n) ? "" : "b", m, n);
            }
        } return as;
    }

    private static Matrix<String> llenarAsientos(int rows, int cols) {
        return llenarAsientos(rows, cols, 2);
    }

    public void addPasajero() {
        this.cant++;
    }

    public int getCantPasajeros() {
        return this.cant;
    }

    public static boolean exists(String cod) {
        return codigos.contains(cod);
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

    public String getCodigo() {
        return this.codigo;
    }

    public int getMaxPasajeros() {
        return (this.cols - 1) * this.rows;
    }

    public int getMaxGasolina() {
        return this.maxGasolina;
    }

    public ArrayList<Integer> getConsumo() {
        return this.consumida;
    }

    public ArrayList<LocalDate> getFechasConsumo() {
        return this.fechaConsumo;
    }

    public void addConsumo(int gasolina, LocalDate fecha) {
        this.consumida.add(Integer.valueOf(gasolina));
        this.fechaConsumo.add(fecha);
    }

    public int getGasPerMilla() {
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

    public int getPasillo() {
        return this.pas;
    }

    public void setPasillo(int pas) {
        this.pas = pas - 1;
    }

    public void resizeMatrix() {
        asientos.put(codigo, llenarAsientos(rows, cols, pas));
    }

    @Override
    public String toString() {
        return this.codigo;
    }

    @Override
    public String getFilename() {
        return "Avion_" + this.codigo;
    }
}
