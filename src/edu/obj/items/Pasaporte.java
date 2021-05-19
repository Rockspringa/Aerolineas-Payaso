package edu.obj.items;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;

import javax.naming.InsufficientResourcesException;
import javax.naming.NameNotFoundException;

import edu.enums.Sexo;
import edu.maker.exp.ObjectExp;
import edu.maker.imp.ObjectImp;
import edu.obj.Creable;

public class Pasaporte implements Creable {
    private static final ArrayList<Integer> nums = new ArrayList<>();
    private final ArrayList<Reservacion> reservaciones;
    private final LocalDate nacimiento;
    private final LocalDate emision;
    private final int numPasaporte;
    private LocalDate vencimiento;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String estadoCivil;
    private String contraseña = "555_1234";
    private String paisActual;
    private double millasRecorridas;
    private Sexo sexo;
    private long tarjeta;

    public Pasaporte(int numPasaporte, String contraseña, LocalDate nacimiento, String nacionalidad,
            String estadoCivil, String nombre, String apellido, Sexo sexo, LocalDate vencimiento,
            LocalDate emision, String paisActual, double millasRecorridas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.emision = emision;
        this.vencimiento = vencimiento;
        this.nacionalidad = nacionalidad;
        this.numPasaporte = numPasaporte;
        this.estadoCivil = estadoCivil;
        this.contraseña = contraseña;
        this.paisActual = paisActual;
        this.millasRecorridas = millasRecorridas;
        nums.add(Integer.valueOf(numPasaporte));
        this.reservaciones = new ArrayList<Reservacion>();
    }

    public Pasaporte(int numPasaporte, LocalDate nacimiento, String nacionalidad, String estadoCivil,
            String nombre, String apellido, Sexo sexo, LocalDate vencimiento,
            LocalDate emision, String paisActual, double millasRecorridas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
        this.emision = emision;
        this.vencimiento = vencimiento;
        this.nacionalidad = nacionalidad;
        this.numPasaporte = numPasaporte;
        this.estadoCivil = estadoCivil;
        this.paisActual = paisActual;
        this.millasRecorridas = millasRecorridas;
        nums.add(Integer.valueOf(numPasaporte));
        this.reservaciones = new ArrayList<Reservacion>();
    }

    public Tarjeta getTarjeta() {
        Tarjeta t = null;
        try {
            t = (Tarjeta) (ObjectImp.impObj(null, "Tarjeta_" + tarjeta));
        } catch (NameNotFoundException e) {}
        return t;
    }

    public void setTarjeta(long tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void pay(int dinero) throws InsufficientResourcesException {
        Tarjeta t = getTarjeta();
        if (t.isEnoughMoney(dinero)) {
            t.setDinero(t.getDinero() - dinero);
            t.setGastos(dinero);
            ObjectExp.expObj(null, t);
        }
        else
            throw new InsufficientResourcesException();
    }

    public ArrayList<Reservacion> getReservaciones() {
        return this.reservaciones;
    }

    public static boolean exists(Integer num) {
        return nums.contains(num);
    }

    public static boolean exists(int num) {
        return exists(Integer.valueOf(num));
    }
    
    public static boolean isValidNum(int num) {
        return num > 9_999_999 && num < 100_000_000;
    }

    public LocalDate getNacimiento() {
        return this.nacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public int getNumeroPasaporte() {
        return this.numPasaporte;
    }
    
    public String getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public String getContraseña() {
        return this.contraseña;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public void setContraseña(String contraseña) {
        if (this.contraseña.equals(""))
            this.contraseña = contraseña;
    }

    public String getNombreCompleto() {
        return nombre + apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getEmision() {
        return emision;
    }

    public LocalDate getVencimiento() {
        return this.vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public boolean isVencido() {
        return ChronoUnit.DAYS.between(vencimiento, LocalDate.now()) > 0;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPaisActual() {
        return paisActual;
    }

    public void setPaisActual(String paisActual) {
        this.paisActual = paisActual;
    }

    public double getMillasRecorridas() {
        return millasRecorridas;
    }

    public void setMillasRecorridas(double millasRecorridas) {
        this.millasRecorridas = millasRecorridas;
    }

    @Override
    public String getFilename() {
        return "Pasaporte_" + this.numPasaporte;
    }
}
