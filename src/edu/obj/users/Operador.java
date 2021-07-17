package edu.obj.users;

import java.util.ArrayList;

public class Operador extends Trabajador {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();
    private final String aeropuerto;

    public Operador(String nombre, String contraseña, String aeropuerto) {
        super(nombre, contraseña);
        nombres.add(nombre);
        pass.add(contraseña);
        this.aeropuerto = aeropuerto;
    }

    public static boolean exists(String nombre, String contraseña) {
        try {
            return nombres.contains(nombre) && pass.get(nombres.indexOf(nombre)).equals(contraseña);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public String getAeropuerto() {
        return aeropuerto;
    }

    @Override
    public String getFilename() {
        return "Operador_de_" + aeropuerto;
    }
}
