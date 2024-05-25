package com.example.simulador;

import java.io.Serializable;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int PosicionX;
    private static int PosicionY;
    private static int score;

    // Constructor, getters y setters
    public EstadoJuego(int PosicionX, int PosicionY, int score){
        this.PosicionX = PosicionX;
        this.PosicionY = PosicionY;
        this.score = score;}

    public static int getPosicionX() {
        return PosicionX;}

    public static int getPosicionY() {
        return PosicionY;}

    public static int getScore() {
        return score;}

    public void setPosicionX(int PosicionX) {
        this.PosicionX = PosicionX;}

    public void setPosicionY(int PosicionY) {
        this.PosicionY = PosicionY;
    }

    public void setScore(int score) {
        this.score = score;
    }
}