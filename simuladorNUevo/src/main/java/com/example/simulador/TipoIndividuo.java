package com.example.simulador;

public class TipoIndividuo {
    private String nombre;
    private double probabilidadReproduccion;
    private double probabilidadClonacion;
    private double probabilidadMuerte;

    public TipoIndividuo(String nombre, double probabilidadReproduccion, double probabilidadClonacion, double probabilidadMuerte) {
        this.nombre = nombre;
        this.probabilidadReproduccion = probabilidadReproduccion;
        this.probabilidadClonacion = probabilidadClonacion;
        this.probabilidadMuerte = probabilidadMuerte;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    // Métodos de acceso para las probabilidades
    public double getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public void setProbabilidadReproduccion(double probabilidadReproduccion) {
        // Validar que la probabilidad esté en el rango [0, 1]
        if (probabilidadReproduccion >= 0 && probabilidadReproduccion <= 1) {
            this.probabilidadReproduccion = probabilidadReproduccion;
        } else {
            throw new IllegalArgumentException("La probabilidad de reproducción debe estar entre 0 y 1.");
        }
    }

    public double getProbabilidadClonacion() {
        return probabilidadClonacion;
    }

    public void setProbabilidadClonacion(double probabilidadClonacion) {
        // Validar que la probabilidad esté en el rango [0, 1]
        if (probabilidadClonacion >= 0 && probabilidadClonacion <= 1) {
            this.probabilidadClonacion = probabilidadClonacion;
        } else {
            throw new IllegalArgumentException("La probabilidad de clonación debe estar entre 0 y 1.");
        }
    }
    public double getProbabilidadMuerte() {
        return probabilidadMuerte;
    }

    public void setProbabilidadMuerte(double probabilidadMuerte) {
        // Validar que la probabilidad esté en el rango [0, 1]
        if (probabilidadMuerte >= 0 && probabilidadMuerte <= 1) {
            this.probabilidadMuerte = probabilidadMuerte;
        } else {
            throw new IllegalArgumentException("La probabilidad de muerte debe estar entre 0 y 1.");
        }
    }
    // Otros métodos y getters/setters...
}