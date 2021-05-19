package edu.obj.users;

import java.util.ArrayList;

import edu.obj.User;

public class Gerente extends User {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();

    public Gerente(String nombre, String contraseña) {
        super(nombre, contraseña);
        nombres.add(nombre);
        pass.add(contraseña);
    }

    public static boolean exists(String nombre, String contraseña) {
        try {
            return nombres.contains(nombre) && pass.get(nombres.indexOf(nombre)).equals(contraseña);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String getFilename() {return null;}
}
