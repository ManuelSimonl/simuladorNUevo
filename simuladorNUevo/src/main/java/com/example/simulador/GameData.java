package com.example.simulador;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameData {

    public static void saveGame(EstadoJuego gameState, String filename) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(gameState);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        }
    }

    public static EstadoJuego loadGame(String filename) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, EstadoJuego.class);
        }
    }
}
//Serializar el juego
//saveGame: Serializa un objeto GameState y lo escribe en un archivo.
//loadGame: Lee un objeto GameState desde un archivo y lo deserializa
//