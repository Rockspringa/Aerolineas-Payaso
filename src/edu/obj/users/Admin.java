package edu.obj.users;

import java.util.ArrayList;

public class Admin extends Trabajador {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();
    private static int cod = 0;
    private String aerolinea;
    private String aeropuerto;

    public Admin(String nombre, String contraseña) {
        super(nombre, contraseña);
        nombres.add(nombre);
        pass.add(contraseña);
        cod++;
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
        return "Admin_00" + cod;
    }
}
