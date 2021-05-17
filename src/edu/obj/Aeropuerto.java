package edu.obj;

import java.util.ArrayList;

import edu.obj.airport.*;

/**
 * Is the class that saves the dates of an airport, like name of this, his city, and his country.
 */
public class Aeropuerto implements Creable {
    private static final ArrayList<String> nombres = new ArrayList<>();
    private final String nombre;
    private final String ciudad;
    private final String pais;
    private final ArrayList<Integer> codAviones;
    private final ArrayList<Distancia> distancias;

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
        this.codAviones = new ArrayList<Integer>();
        this.distancias = new ArrayList<Distancia>();
        nombres.add(nombre);
    }

    public static boolean exists(String obj) {
        return nombres.contains(obj);
    }

    /**
     * 
     * @return the name of the airport.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * 
     * @return the name of the city of the airport.
     */
    public String getCiudad() {
        return this.ciudad;
    }

    /**
     * 
     * @return the name of the country of the airport.
     */
    public String getPais() {
        return this.pais;
    }

    /**
     * 
     * @return an <code>ArrayList</code> that contais all the planes in the airport, it's not a copy, beaware.
     */
    public ArrayList<Integer> getAviones() {
        return this.codAviones;
    }
    
    /**
     * 
     * @return a object <code>Distancia</code>, only if the destination airport it's avaible, otherwise <code>null</code>.
     */
    public Distancia getDistancia(String destino) {
        for (Distancia dist : this.distancias) {
            if (dist.getOrigen().equals(destino)) {
                return dist;
            }
        } return null;
    }

    /**
     * 
     * @return a object <code>Distancia</code>, only if the destination airport it's avaible, otherwise <code>null</code>.
     */
    public Distancia getDistancia(Aeropuerto destino) {
        return this.getDistancia(destino.getNombre());
    }

    public ArrayList<Distancia> getDistancias() {
        return this.distancias;
    }

    @Override
    public String getFilename() {
        return "Aeropuerto_" + this.nombre;
    }
}
