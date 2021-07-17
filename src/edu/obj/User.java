package edu.obj;

import java.util.ArrayList;
import java.util.HashMap;

import edu.enums.Obj;
import edu.obj.users.Admin;
import edu.obj.users.Gerente;
import edu.obj.users.Operador;
import edu.obj.users.Pasajero;

/**
 * Implements an user, this is the basic type of person that interacts with the program, this is the
 * abstract type of that one, but this don't have any abstract method, so it's an normal class.
 */
public abstract class User implements Creable {
    private static final HashMap<String, User> instances = new HashMap<>();
    private static final ArrayList<String> users = new ArrayList<>();
    private static final ArrayList<String> pass = new ArrayList<>();
    private final String nombre;
    private String contraseña = "";

    static {
        new Admin("temp", "temp");
    }

    /**
     * Creates an user with his name and a password, this is the basic things of an user in this system.
     * @param nombre is the name of the user, complete name.
     * @param contraseña is the password that will be forever associated with the user. Inmutable.
     */
    public User(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        users.add(nombre);
        pass.add(contraseña);

        instances.put(nombre + contraseña, this);
    }

    /**
     * Creates an user with only his name, this doesn't delete the inmutability of the password, the password
     * is setteable only for the first time.
     * @param nombre is the complete name of the user.
     */
    public User(String nombre) {
        this.nombre = nombre;
    }

    public static boolean exists(String nombre, String contraseña) {
        try {
            return users.contains(nombre) && pass.get(users.indexOf(nombre)).equals(contraseña);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static Obj getUser(String nombre, String contraseña) {
        if (exists(nombre, contraseña)) {
            if (Pasajero.exists(nombre, contraseña)) {
                return Obj.PASAJERO;
            } else if (Admin.exists(nombre, contraseña)) {
                return Obj.ADMINISTRADOR;
            } else if (Gerente.exists(nombre, contraseña)) {
                return Obj.GERENTE;
            } else if (Operador.exists(nombre, contraseña)) {
                return Obj.OPERADOR;
            }
        } return null;
    }

    /**
     * 
     * @return a <code>String</code> with the name of the user.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * This set the value for the password of the user, is possible to change only when it is equal to "".
     * @param contraseña is the new password.
     */
    public void setContraseña(String contraseña) {
        if (this.contraseña.equals("")) {
            this.contraseña = contraseña;
            users.add(nombre);
            pass.add(nombre);
        }
    }

    /**
     * 
     * @return a String with the password, it is without any codification.
     */
    public String getContraseña() {
        return this.contraseña;
    }

    /**
     * This method is for verify if the password entered is equal to the password of the user.
     * @param contraseña is the password entered.
     * @return <code>true</code> if the password entered is equal to the password of the user, <code>false</code>
     * otherwise.
     */
    public boolean equalsContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }

    public static User getInstance(String nombre, String pass) {
        return instances.get(nombre + pass);
    }
}
