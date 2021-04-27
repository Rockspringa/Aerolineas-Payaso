package edu.obj;

/**
 * Is the class that saves the dates of an airport, like name of this, his city, and his country.
 */
public class Aeropuerto {
    private final String nombre;
    private final String ciudad;
    private final String pais;

    /**
     * Create an airport with all his attributes, this attributes are all inmutables.
     * @param nombre is the name given to the airport.
     * @param ciudad is the city where this was builted.
     * @param pais is the country where is the previous city.
     */
    public Aeropuerto(String nombre, String ciudad, String pais) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    /**
     * 
     * @return the name of the airport.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return the name of the city of the airport.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * 
     * @return the name of the country of the airport.
     */
    public String getPais() {
        return pais;
    }
}
