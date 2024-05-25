package com.example.simulador;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Individuo {
    private int posX;
    private int posY;
    private int id;
    private int generacion;
    private int turnosDeVida;
    private double probabilidadReproduccion;
    private double probabilidadClonacion;
    private double probabilidadMuerte;
    private TipoIndividuo tipo;

    public Individuo(int posX, int posY, String tipo, int id, int generacion, TipoIndividuo tipoIndividuo) {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
        this.generacion = generacion;
        this.tipo = tipoIndividuo;
        this.turnosDeVida = generarTurnosDeVida();
        this.probabilidadReproduccion = tipoIndividuo.getProbabilidadReproduccion();
        this.probabilidadClonacion = tipoIndividuo.getProbabilidadClonacion();
        this.probabilidadMuerte = 1 - probabilidadReproduccion;
    }

    // Generar aleatoriamente los turnos de vida
    private int generarTurnosDeVida() {
        Random random = new Random();
        return random.nextInt(10) + 1; // Turnos de vida entre 1 y 10
    }

    // Método para disminuir un turno de vida
    public void disminuirTurnoDeVida() {
        this.turnosDeVida--;
    }

    // Getter para obtener el tipo del individuo
    public TipoIndividuo getTipo() {
        return tipo;
    }

    // Resto de getters y setters según necesidad
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGeneracion() {
        return generacion;
    }

    public void setGeneracion(int generacion) {
        this.generacion = generacion;
    }

    public int getTurnosDeVida() {
        return turnosDeVida;
    }

    public void setTurnosDeVida(int turnosDeVida) {
        this.turnosDeVida = turnosDeVida;
    }

    public double getProbabilidadReproduccion() {
        return probabilidadReproduccion;
    }

    public void setProbabilidadReproduccion(double probabilidadReproduccion) {
        this.probabilidadReproduccion = probabilidadReproduccion;
    }

    public double getProbabilidadClonacion() {
        return probabilidadClonacion;
    }

    public void setProbabilidadClonacion(double probabilidadClonacion) {
        this.probabilidadClonacion = probabilidadClonacion;
    }

    public double getProbabilidadMuerte() {
        return probabilidadMuerte;
    }

    public void setProbabilidadMuerte(double probabilidadMuerte) {
        this.probabilidadMuerte = probabilidadMuerte;
    }

    // Método para mover al individuo
    public void mover(int nuevaPosX, int nuevaPosY) {
        this.posX = nuevaPosX;
        this.posY = nuevaPosY;
    }

    // Método para reproducir
    public Individuo reproducir() {
        if (Math.random() < probabilidadReproduccion) {
            // Crear un nuevo individuo con la misma generación y tipo
            return new Individuo(this.posX, this.posY, this.tipo.getNombre(), this.id, this.generacion, this.tipo);
        } else {
            return null; // No se pudo reproducir
        }
    }

    // Método para clonar
    public Individuo clonar() {
        if (Math.random() < probabilidadClonacion) {
            // Crear un nuevo individuo idéntico
            return new Individuo(this.posX, this.posY, this.tipo.getNombre(), this.id, this.generacion, this.tipo);
        } else {
            return null; // No se pudo clonar
        }
    }
    private Recurso EncontrarRecursoMasCercano(int x, int y, ListaSimple<Recurso> resources) {
        Recurso recursomascercano = null;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < resources.getNum_elementos(); i++) {
            ElementoLS elemento = resources.getElemento(i);
            Recurso recurso = (Recurso) elemento.getData();

            // Calcular la distancia euclidiana desde (x, y) hasta las coordenadas del recurso
            double distance = Math.sqrt(Math.pow(recurso.getX() - x, 2) + Math.pow(recurso.getY() - y, 2));

            // Si este recurso es más cercano que cualquiera anterior, actualizar el recurso más cercano
            if (distance < minDistance) {
                minDistance = distance;
                recursomascercano = recurso;
            }
        }

        return recursomascercano;
    }

}