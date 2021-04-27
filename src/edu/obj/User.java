package edu.obj;

/**
 * Implements an user, this is the basic type of person that interacts with the program, this is the
 * abstract type of that one, but this don't have any abstract method, so it's an normal class.
 */
public class User {
    private final String nombre;
    private String contraseña = "";

    /**
     * Creates an user with his name and a password, this is the basic things of an user in this system.
     * @param nombre is the name of the user, complete name.
     * @param contraseña is the password that will be forever associated with the user. Inmutable.
     */
    public User(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    /**
     * Creates an user with only his name, this doesn't delete the inmutability of the password, the password
     * is setteable only for the first time.
     * @param nombre is the complete name of the user.
     */
    public User(String nombre) {
        this.nombre = nombre;
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
}
