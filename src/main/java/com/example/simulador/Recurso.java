package com.example.simulador;

public class Recurso {

    private int fila;
    private int columna;
    private String nombre;
    private int efectoTurnosDeVida;
    private double efectoProbabilidadReproduccion;
    private double efectoProbabilidadClonacion;
    private int tiempoAparicion;

    // Constructor
    public Recurso(String nombre, int efectoTurnosDeVida, double efectoProbabilidadReproduccion,
                   double efectoProbabilidadClonacion, int tiempoAparicion, int fila, int columna) {
        this.nombre = nombre;
        this.efectoTurnosDeVida = efectoTurnosDeVida;
        this.efectoProbabilidadReproduccion = efectoProbabilidadReproduccion;
        this.efectoProbabilidadClonacion = efectoProbabilidadClonacion;
        this.tiempoAparicion = tiempoAparicion;
        this.fila = fila;
        this.columna = columna;
    }

    // Getters y setters para fila y columna
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    // Getters y setters para los demás atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEfectoTurnosDeVida() {
        return efectoTurnosDeVida;
    }

    public void setEfectoTurnosDeVida(int efectoTurnosDeVida) {
        this.efectoTurnosDeVida = efectoTurnosDeVida;
    }

    public double getEfectoProbabilidadReproduccion() {
        return efectoProbabilidadReproduccion;
    }

    public void setEfectoProbabilidadReproduccion(double efectoProbabilidadReproduccion) {
        this.efectoProbabilidadReproduccion = efectoProbabilidadReproduccion;
    }

    public double getEfectoProbabilidadClonacion() {
        return efectoProbabilidadClonacion;
    }

    public void setEfectoProbabilidadClonacion(double efectoProbabilidadClonacion) {
        this.efectoProbabilidadClonacion = efectoProbabilidadClonacion;
    }

    public int getTiempoAparicion() {
        return tiempoAparicion;
    }

    public void setTiempoAparicion(int tiempoAparicion) {
        this.tiempoAparicion = tiempoAparicion;
    }
}