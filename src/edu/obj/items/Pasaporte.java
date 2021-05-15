package edu.obj.items;

import java.time.*;
import java.time.temporal.*;

import edu.obj.enums.Sexo;

public class Pasaporte {
    private final LocalDate nacimiento;
    private final LocalDate emision;
    private final LocalDate vencimiento;
    private final int numPasaporte;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private String estadoCivil;
    private String contraseña = "";
    private String paisActual;
    private double millasRecorridas;
    private Sexo sexo;

    public Pasaporte(String nombre, String apellido, Sexo sexo, LocalDate nacimiento, LocalDate emision,
            LocalDate vencimiento, String nacionalidad, int numPasaporte, String estadoCivil, String contraseña,
            String paisActual, double millasRecorridas) {
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
    }

    public Pasaporte(String nombre, String apellido, Sexo sexo, LocalDate nacimiento, LocalDate emision,
            LocalDate vencimiento, String nacionalidad, int numPasaporte, String estadoCivil,
            String paisActual, double millasRecorridas) {
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
    }

    public Pasaporte(Pasaporte oldPasaporte, LocalDate vencimiento) {
        this.nombre = oldPasaporte.nombre;
        this.apellido = oldPasaporte.apellido;
        this.sexo = oldPasaporte.sexo;
        this.nacimiento = oldPasaporte.nacimiento;
        this.emision = LocalDate.now();
        this.vencimiento = vencimiento;
        this.nacionalidad = oldPasaporte.nacionalidad;
        this.numPasaporte = oldPasaporte.numPasaporte;
        this.estadoCivil = oldPasaporte.estadoCivil;
        this.paisActual = oldPasaporte.paisActual;
        this.millasRecorridas = oldPasaporte.millasRecorridas;
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
        return vencimiento;
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

}
