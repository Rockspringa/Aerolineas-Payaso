package edu.obj.users;

import java.util.ArrayList;

public class Gerente extends Trabajador {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();
    private final String aerolinea;
    private String aeropuerto;

    public Gerente(String nombre, String contraseña, String aerolinea) {
        super(nombre, contraseña);
        nombres.add(nombre);
        pass.add(contraseña);
        this.aerolinea = aerolinea;
    }

    public static boolean exists(String nombre, String contraseña) {
        try {
            return nombres.contains(nombre) && pass.get(nombres.indexOf(nombre)).equals(contraseña);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public String getAerolinea() {
        return this.aerolinea;
    }

    public String getAeropuerto() {
        return this.aeropuerto;
    }

    public void setAeropuerto(String aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    @Override
    public String getFilename() {
        return "Gerente_de_" + aerolinea;
    }
}
