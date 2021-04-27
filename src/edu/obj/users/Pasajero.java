package edu.obj.users;

import javax.naming.InsufficientResourcesException;

import edu.obj.*;

public class Pasajero extends User {
    private Pasaporte pasaporte;
    private Tarjeta tarjeta;
    
    public Pasajero(Pasaporte pasaporte, Tarjeta tarjeta) {
        super(pasaporte.getNombreCompleto(), pasaporte.getContraseña());
        this.pasaporte = pasaporte;
        this.tarjeta = tarjeta;
    }

    public Pasaporte getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void pay(int dinero) throws InsufficientResourcesException {
        if (this.tarjeta.isEnoughMoney(dinero))
            this.tarjeta.setDinero(this.tarjeta.getDinero() - dinero);
        else
            throw new InsufficientResourcesException();
    }
}
