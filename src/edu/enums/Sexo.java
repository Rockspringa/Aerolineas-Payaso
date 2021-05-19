package edu.enums;

public enum Sexo {
    MASCULINO, FEMENINO, SIN_DEFINIR;

    public static Sexo parse(String sexo) {
        if (sexo.toLowerCase().equals("masculino") || sexo.toUpperCase().equals("M")) {
            return MASCULINO;
        } else if (sexo.toLowerCase().equals("femenino") || sexo.toUpperCase().equals("F")) {
            return FEMENINO;
        } else {
            return SIN_DEFINIR;
        }
    }

    
}
