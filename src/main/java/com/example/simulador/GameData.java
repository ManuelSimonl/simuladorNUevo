package com.example.simulador;
//Serializar el juego

//saveGame: Serializa un objeto GameState y lo escribe en un archivo.
//loadGame: Lee un objeto GameState desde un archivo y lo deserializa
//

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class GameData {
    public static void saveGame(EstadoJuego gameState, String filename) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) { // flujo que puede escribir objetos en un OutputStream. Aquí, está conectado a fileOut.
            out.writeObject(gameState);
        }}

    public static EstadoJuego loadGame(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {  //FileOutputStream es un flujo de salida de bytes que se conecta a un archivo. Aquí se abre (o crea) el archivo especificado por filename.
            return (EstadoJuego) in.readObject();
        }
    }
}