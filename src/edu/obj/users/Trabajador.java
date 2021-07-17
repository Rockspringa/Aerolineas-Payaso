package edu.obj.users;

import edu.obj.User;

public abstract class Trabajador extends User {

    public Trabajador(String nombre, String pass) {
        super(nombre, pass);
    }

    public abstract String getAeropuerto();
}
