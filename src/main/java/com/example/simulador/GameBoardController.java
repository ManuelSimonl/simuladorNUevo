package com.example.simulador;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.scene.Node;
import javafx.util.Duration;

public class GameBoardController {

    @FXML
    private GridPane gameGridPane;
    private int rows;
    private int cols;


    // Variable para controlar el estado de pausa del juego
    private boolean gamePaused = false;

    // Lista de individuos para mostrar en el tablero
    private List<Individuo> individuos = new ArrayList<>();



    // Otras variables de instancia...
    private List<Recurso> recursosActivos = new ArrayList<>();

    // Método para inicializar el tablero de juego con el número de filas y columnas especificado
    public void initializeGameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initialize();
        generarIndividuos(); // Generar algunos individuos al inicio del juego
        mostrarIndividuos(); // Mostrar los individuos en el tablero
        generarRecursos(); // Generar algunos recursos al inicio del juego
        mostrarRecursos(); // Mostrar los recursos en el tablero
    }

    // Método para inicializar el tablero de juego
    public void initialize() {
        gameGridPane.getChildren().clear();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Label cellLabel = new Label("");
                cellLabel.setMinSize(60, 60);
                cellLabel.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                gameGridPane.add(cellLabel, j, i);

                // Agregar controlador de eventos a cada celda del GridPane
                cellLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        // Obtener la fila y columna de la celda seleccionada
                        int row = GridPane.getRowIndex(cellLabel);
                        int col = GridPane.getColumnIndex(cellLabel);

                        // Mostrar la información de la celda seleccionada
                        mostrarInformacionCelda(row, col);
                    }
                });
            }
        }
    }

    // Método para mostrar la información de la celda seleccionada
    private void mostrarInformacionCelda(int row, int col) {
        // Buscar individuos en la posición de la celda seleccionada
        for (Individuo individuo : individuos) {
            if (individuo.getPosX() == row && individuo.getPosY() == col) {
                // Mostrar información del individuo
                System.out.println("Individuo: Tipo=" + individuo.getTipo().getNombre() + ", Turnos de vida=" + individuo.getTurnosDeVida());
                return;
            }
        }

        // Buscar recursos en la posición de la celda seleccionada
        for (Recurso recurso : recursosActivos) {
            if (recurso.getFila() == row && recurso.getColumna() == col) {
                // Mostrar información del recurso
                System.out.println("Recurso: Nombre=" + recurso.getNombre() + ", Efecto en turnos de vida=" + recurso.getEfectoTurnosDeVida());
                return;
            }
        }

        // Si no hay individuo ni recurso en la celda seleccionada, imprimir un mensaje indicando que la celda está vacía
        System.out.println("Celda vacía en la posición: (" + row + ", " + col + ")");
    }


    // Método para generar algunos individuos al inicio del juego
    private void generarIndividuos() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int posX = random.nextInt(rows);
            int posY = random.nextInt(cols);
            String tipo = "Tipo" + (i % 3 + 1);
            TipoIndividuo tipoIndividuo = new TipoIndividuo("Tipo" + (i % 3 + 1), 0.5, 0.2, 0.3);
            Individuo individuo = new Individuo(posX, posY, tipo, i, i, tipoIndividuo);
            individuos.add(individuo);
        }
    }

    private Map<Individuo, Label> individuoLabels = new HashMap<>();

    private void mostrarIndividuos() {
        // Actualizar la posición de los individuos en el GridPane
        for (Individuo individuo : individuos) {
            Label cellLabel = individuoLabels.get(individuo);
            if (cellLabel == null) {
                cellLabel = new Label(individuo.getTipo().getNombre());
                cellLabel.setMinSize(60, 60);
                cellLabel.setStyle("-fx-border-color: black; -fx-text-alignment: center; -fx-background-color: blue; -fx-text-fill: white;");
                individuoLabels.put(individuo, cellLabel);
                gameGridPane.add(cellLabel, individuo.getPosY(), individuo.getPosX());
            } else {
                gameGridPane.getChildren().remove(cellLabel);
                gameGridPane.add(cellLabel, individuo.getPosY(), individuo.getPosX());
            }
            // Imprimir las coordenadas del individuo en el terminal
            System.out.println("Individuo: Tipo=" + individuo.getTipo().getNombre() + ", PosX=" + individuo.getPosX() + ", PosY=" + individuo.getPosY());
        }
    }

    // Método para generar nuevos recursos en el tablero
    private void generarRecursos() {
        Random random = new Random();
        // Lógica para generar recursos en posiciones aleatorias con cierta probabilidad
        // Puedes ajustar esta lógica según tus requerimientos específicos
        // Por ejemplo:
        for (int i = 0; i < 58; i++) { // Generar al menos 18 recursos
            if (random.nextDouble() < 0.5) {
                // Seleccionar un tipo de recurso aleatorio
                Recurso nuevoRecurso = seleccionarTipoRecursoAleatorio();
                // Añadir el recurso a una posición aleatoria en el tablero
                añadirRecursoAleatorio(nuevoRecurso);
                // Después de añadir el recurso, actualizar la vista del tablero
            }
        }
    }

    // Método para seleccionar aleatoriamente un tipo de recurso
    private Recurso seleccionarTipoRecursoAleatorio() {
        // Lista de tipos de recursos disponibles
        List<Recurso> tiposRecursosDisponibles = new ArrayList<>();

        // Añadir los tipos de recursos disponibles a la lista
        tiposRecursosDisponibles.add(new Recurso("Agua", 2, 0.0, 0.0, 3, 0, 0)); // Efecto en turnos de vida: +2
        tiposRecursosDisponibles.add(new Recurso("Comida", 10, 0.0, 0.0, 3, 0, 0)); // Efecto en turnos de vida: +10
        tiposRecursosDisponibles.add(new Recurso("Montaña", -2, 0.0, 0.0, 3, 0, 0)); // Efecto en turnos de vida: -2
        tiposRecursosDisponibles.add(new Recurso("Tesoro", 0, 0.1, 0.0, 3, 0, 0)); // Efecto en probabilidad de reproducción: +10%
        tiposRecursosDisponibles.add(new Recurso("Biblioteca", 0, 0.0, 0.1, 3, 0, 0)); // Efecto en probabilidad de clonación: +10%
        tiposRecursosDisponibles.add(new Recurso("Pozo", 0, 0.0, 0.0, 3, 0, 0)); // Efecto: Muerte instantánea

        // Seleccionar aleatoriamente un tipo de recurso de la lista
        Random random = new Random();
        int indiceTipoRecurso = random.nextInt(tiposRecursosDisponibles.size());
        return tiposRecursosDisponibles.get(indiceTipoRecurso);
    }

    private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    private void añadirRecursoAleatorio(Recurso recurso) {
        Random random = new Random();
        int filaAleatoria = random.nextInt(rows);
        int columnaAleatoria = random.nextInt(cols);

        // Obtener el nodo (Label) en la posición aleatoria del GridPane
        Node nodo = getNodeByRowColumnIndex(filaAleatoria, columnaAleatoria, gameGridPane);

        // Verificar si el nodo es un Label
        if (nodo instanceof Label) {
            Label label = (Label) nodo;
            // Comprobar si el Label ya tiene recursos asignados
            if (label.getText().isEmpty() || !tieneTipoRecurso(label, recurso.getNombre())) {
                // Comprobar si ya hay tres recursos en la celda
                if (contarRecursosEnCelda(filaAleatoria, columnaAleatoria) <= 5) {
                    String textoActual = label.getText();
                    // Agregar el nombre del nuevo recurso al texto del Label con un salto de línea si no se ha alcanzado el límite de tres recursos
                    String nuevoTexto = (textoActual.isEmpty() ? recurso.getNombre() : textoActual + "\n" + recurso.getNombre());
                    label.setText(nuevoTexto);
                    // Agregar el recurso a la lista de recursos activos solo si no se ha alcanzado el límite de tres recursos
                    if (contarRecursosEnCelda(filaAleatoria, columnaAleatoria) <= 5) {
                        recursosActivos.add(new Recurso(recurso.getNombre(), recurso.getEfectoTurnosDeVida(),
                                recurso.getEfectoProbabilidadReproduccion(),
                                recurso.getEfectoProbabilidadClonacion(),
                                recurso.getTiempoAparicion(),
                                filaAleatoria, columnaAleatoria));
                    }
                }
            }
        }
    }

    // Método para verificar si un tipo de recurso ya está presente en un Label
    private boolean tieneTipoRecurso(Label label, String tipoRecurso) {
        for (String linea : label.getText().split("\n")) {
            if (linea.equals(tipoRecurso)) {
                return true;
            }
        }
        return false;
    }

    // Método para eliminar un recurso de la lista de recursos
    private void eliminarRecurso(Recurso recurso) {
        recursosActivos.remove(recurso);
        // También puedes realizar otras acciones necesarias para eliminar el recurso, como actualizar la vista del tablero
    }

    // Método para controlar la duración de los recursos activos
    private void controlarDuracionRecursos() {
        // Lista para almacenar los recursos que deben eliminarse
        List<Recurso> recursosAEliminar = new ArrayList<>();

        // Iterar sobre la lista de recursos activos
        for (Recurso recurso : recursosActivos) {
            // Decrementar el tiempo de aparición del recurso
            recurso.setTiempoAparicion(recurso.getTiempoAparicion() - 1);

            // Verificar si el tiempo de aparición ha alcanzado su máximo
            if (recurso.getTiempoAparicion() <= 0) {
                // Si el tiempo de aparición ha alcanzado su máximo, añadir el recurso a la lista de recursos a eliminar
                recursosAEliminar.add(recurso);
            }
        }

        // Eliminar los recursos que han alcanzado su tiempo de aparición máximo
        for (Recurso recurso : recursosAEliminar) {
            eliminarRecurso(recurso);
        }
    }

    // Método para mostrar los recursos en el tablero
    private void mostrarRecursos() {
        for (Recurso recurso : recursosActivos) {
            // Obtener la posición del recurso
            int fila = recurso.getFila();
            int columna = recurso.getColumna();

            // Crear un Label para representar el recurso
            Label label = new Label(recurso.getNombre());
            label.setMinSize(60, 60); // Tamaño mínimo para visualización
            label.setStyle("-fx-border-color: black; -fx-text-alignment: center;");

            // Agregar el Label a la posición correspondiente en el GridPane
            gameGridPane.add(label, columna, fila);
        }
    }

    // Método para actualizar la vista del tablero después de que un individuo se haya movido
    private void actualizarVistaTablero(Individuo individuo) {
        // Obtener el nodo (Label) en la posición del individuo en el GridPane
        Label cellLabel = individuoLabels.get(individuo);
        if (cellLabel != null) {
            // Actualizar las coordenadas del nodo en el GridPane
            GridPane.setColumnIndex(cellLabel, individuo.getPosY());
            GridPane.setRowIndex(cellLabel, individuo.getPosX());
        }
    }

    private void moverIndividuoArriba(Individuo individuo) {
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Verificar si el individuo ya está en el borde superior del tablero
        if (posX > 0) {
            // Mover el individuo una casilla hacia arriba
            individuo.setPosX(posX - 1);
            // Actualizar la vista del tablero después del movimiento
            actualizarVistaTablero(individuo);
        }
    }

    private void moverIndividuoAbajo(Individuo individuo) {
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Verificar si el individuo ya está en el borde inferior del tablero
        if (posX < rows - 1) {
            // Mover el individuo una casilla hacia abajo
            individuo.setPosX(posX + 1);
            // Actualizar la vista del tablero después del movimiento
            actualizarVistaTablero(individuo);
        }
    }

    private void moverIndividuoIzquierda(Individuo individuo) {
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Verificar si el individuo ya está en el borde izquierdo del tablero
        if (posY > 0) {
            // Mover el individuo una casilla hacia la izquierda
            individuo.setPosY(posY - 1);
            // Actualizar la vista del tablero después del movimiento
            actualizarVistaTablero(individuo);
        }
    }

    private void moverIndividuoDerecha(Individuo individuo) {
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Verificar si el individuo ya está en el borde derecho del tablero
        if (posY < cols - 1) {
            // Mover el individuo una casilla hacia la derecha
            individuo.setPosY(posY + 1);
            // Actualizar la vista del tablero después del movimiento
            actualizarVistaTablero(individuo);
        }
    }

    // Método para contar la cantidad de recursos en una celda específica
    private int contarRecursosEnCelda(int fila, int columna) {
        int count = 0;
        for (Recurso recurso : recursosActivos) {
            if (recurso.getFila() == fila && recurso.getColumna() == columna) {
                count++;
            }
        }
        return count;
    }

    private void moverTipoBasico(Individuo individuo) {
        // Movimiento aleatorio
        Random random = new Random();
        int direccion = random.nextInt(4); // 0: arriba, 1: abajo, 2: izquierda, 3: derecha
        switch (direccion) {
            case 0:
                moverIndividuoArriba(individuo);
                break;
            case 1:
                moverIndividuoAbajo(individuo);
                break;
            case 2:
                moverIndividuoIzquierda(individuo);
                break;
            case 3:
                moverIndividuoDerecha(individuo);
                break;
        }
    }

    // Método para mover a los individuos de tipo normal
    private void moverTipoNormal(Individuo individuo) {
        // Obtener la posición actual del individuo
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Encontrar un recurso disponible en el entorno (seleccionado aleatoriamente)
        Recurso recursoDestino = encontrarRecursoAleatorio();

        // Si se encontró un recurso disponible, moverse hacia él
        if (recursoDestino != null) {
            // Calcular la dirección para moverse hacia el recurso
            int deltaX = Integer.compare(recursoDestino.getColumna() - posY, 0);
            int deltaY = Integer.compare(recursoDestino.getFila() - posX, 0);

            // Actualizar la posición del individuo
            individuo.setPosX(posX + deltaX);
            individuo.setPosY(posY + deltaY);
        }
    }

    // Método para mover a los individuos de tipo avanzado
    private void moverTipoAvanzado(Individuo individuo) {
        // Obtener la posición actual del individuo
        int posX = individuo.getPosX();
        int posY = individuo.getPosY();

        // Encontrar el recurso más cercano que no esté en la posición actual del individuo
        Recurso recursoDestino = encontrarRecursoMasCercano(individuo);

        // Si se encontró un recurso disponible, moverse hacia él
        if (recursoDestino != null) {
            // Calcular la dirección para moverse hacia el recurso
            int deltaX = Integer.compare(recursoDestino.getColumna() - posY, 0);
            int deltaY = Integer.compare(recursoDestino.getFila() - posX, 0);

            // Actualizar la posición del individuo
            individuo.setPosX(posX + deltaX);
            individuo.setPosY(posY + deltaY);
        }
    }

    // Método para encontrar un recurso disponible en el entorno (seleccionado aleatoriamente)
    private Recurso encontrarRecursoAleatorio() {
        // Seleccionar un recurso aleatorio de la lista de recursos activos
        if (recursosActivos.isEmpty()) {
            return null;
        } else {
            Random random = new Random();
            int index = random.nextInt(recursosActivos.size());
            return recursosActivos.get(index);
        }
    }

    // Método para encontrar el recurso más cercano que no esté en la posición actual del individuo
    private Recurso encontrarRecursoMasCercano(Individuo individuo) {
        // Calcular la distancia a todos los recursos activos y seleccionar el más cercano que no esté en la posición actual del individuo
        Recurso recursoMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;
        for (Recurso recurso : recursosActivos) {
            if (recurso.getFila() != individuo.getPosX() || recurso.getColumna() != individuo.getPosY()) {
                double distancia = Math.sqrt(Math.pow(recurso.getFila() - individuo.getPosX(), 2) + Math.pow(recurso.getColumna() - individuo.getPosY(), 2));
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    recursoMasCercano = recurso;
                }
            }
        }
        return recursoMasCercano;
    }

    // Método para actualizar el estado de los individuos en el tablero después de cada turno
    public void actualizarEstadoIndividuos() {
        // Iterar sobre la lista de individuos y actualizar su estado según su tipo
        for (Individuo individuo : individuos) {
            // Verificar el tipo del individuo y llamar al método correspondiente para moverlo
            switch (individuo.getTipo().getNombre()) {
                case "Tipo1":
                    // Tipo básico: se mueve aleatoriamente (implementación no proporcionada)
                    moverTipoBasico(individuo);
                    break;
                case "Tipo2":
                    // Tipo normal: se mueve en línea recta hacia un recurso del entorno (seleccionado aleatoriamente)
                    moverTipoNormal(individuo);
                    break;
                case "Tipo3":
                    // Tipo avanzado: es capaz de hacer rutas hacia la posición de los recursos y seleccionar el recurso al que quiere ir (el más cercano que no esté en la posición actual del individuo)
                    moverTipoAvanzado(individuo);
                    break;
                default:
                    // Tipo desconocido
                    System.out.println("Tipo de individuo desconocido.");
            }
        }
        // Después de actualizar el estado de los individuos, mostrar los cambios en el tablero
        mostrarIndividuos();
    }

    @FXML
    private void saveGameState() {
        // Implementa lógica para guardar el estado del juego en archivos
    }

    @FXML
    private void loadGameState() {
        // Implementa lógica para cargar el estado del juego desde archivos
    }



    // Método para eliminar un individuo de la lista de individuos
    private void eliminarIndividuo(Individuo individuo) {
        individuos.remove(individuo);
        // También puedes realizar otras acciones necesarias después de eliminar el individuo
    }
    private void moverIndividuosAutomaticamente() {
        // Crear una copia de la lista individuos
        List<Individuo> copiaIndividuos = new ArrayList<>(individuos);

        // Iterar sobre la copia
        for (Individuo individuo : copiaIndividuos) {
            // Reducir los turnos de vida restantes
            individuo.setTurnosDeVida(individuo.getTurnosDeVida() - 1);
            if (individuo.getTurnosDeVida() > 0) {
                switch (individuo.getTipo().getNombre()) {
                    case "Tipo1":
                        moverTipoBasico(individuo);
                        break;
                    case "Tipo2":
                        moverTipoNormal(individuo);
                        break;
                    case "Tipo3":
                        moverTipoAvanzado(individuo);
                        break;
                    default:
                        // No se debería llegar aquí, pero podrías manejar una lógica de fallback si es necesario
                }
            } else {
                // Eliminar individuo si ha alcanzado el fin de sus turnos de vida
                eliminarIndividuo(individuo);
            }
        }
    }


    // Métodos para controlar la ejecución del juego
    private int numeroTurno = 0;
    private boolean juegoPausado = false;
    // Variable para almacenar la línea de tiempo del juego
    private Timeline timeline;

    // Método para iniciar el bucle de juego
    private void iniciarBucleJuego() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            moverIndividuosAutomaticamente(); // Mover los individuos automáticamente cada segundo
            controlarDuracionRecursos(); // Controlar la duración de los recursos
            mostrarRecursos(); // Mostrar los recursos en el tablero

            // Incrementar el número de turno
            numeroTurno++;
            // Actualizar el texto de la etiqueta de turno
            turnoLabel.setText("Turno: " + numeroTurno);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Método para iniciar el juego
    @FXML
    private void playGame() {
        // Si el juego ya está en pausa, no se reinicia
        if (juegoPausado) {
            juegoPausado = false;
            // Reiniciar la visibilidad de los botones
            configuracionButton.setVisible(false);
            guardarJuegoButton.setVisible(false);
            // Reiniciar el estado del juego
            estadoJuegoLabel.setText("Estado: Jugando");
            // Reiniciar el bucle de juego
            iniciarBucleJuego();
            return;
        }

        // Lógica para iniciar la ejecución del juego
        // Cambiar la visibilidad de los botones para ocultarlos
        configuracionButton.setVisible(false);
        guardarJuegoButton.setVisible(false);

        // Iniciar el bucle de juego
        iniciarBucleJuego();
    }

    // Método para pausar el juego
    @FXML
    private void pauseGame() {
        // Detener el bucle de juego si está en ejecución
        if (timeline != null) {
            timeline.stop();
        }

        // Cambiar el estado del juego a pausado
        juegoPausado = true;

        // Cambiar la visibilidad de los botones según el estado del juego
        configuracionButton.setVisible(true);
        guardarJuegoButton.setVisible(true);

        // Actualizar el estado del juego
        estadoJuegoLabel.setText("Estado: Pausado");
    }

    @FXML
    private void stopGame() {
        // Lógica para detener la ejecución del juego
        // Obtener la ventana actual
        Stage stage = (Stage) configuracionButton.getScene().getWindow();

        // Cerrar la ventana actual
        stage.close();
    }

    @FXML
    private Button configuracionButton;

    @FXML
    private Button guardarJuegoButton() {
        Button saveButton = new Button("Guardar Partida");
            saveButton.setOnAction(e -> {
            try {
                GameData.saveGame(EstadoJuego, "EstadoJuego.ser");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Button loadButton = new Button("Cargar Partida");
            loadButton.setOnAction(e -> {
            try {
                EstadoJuego = GameData.loadGame("EstadoJuego.ser");
                // Actualiza el juego con el estado cargado
                updateGameWithLoadedState(EstadoJuego);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });


    private void updateGameWithLoadedState(GameState gameState) {
        // Implementa la lógica para actualizar el juego con el estado cargado
        System.out.println("Posición del jugador: (" + EstadoJuego.getPosicionX() + ", " + EstadoJuego.getPosicionY() + ")");
        System.out.println("Puntuación: " + EstadoJuego.getScore());}
    }

//"EstadoJuego.ser" es el nombre del archivo donde se guardará o desde donde se cargará el estado del juego
//.ser es una convención común para indicar que un archivo contiene datos serializados. No es obligatorio usar .ser, pero ayuda a identificar rápidamente el propósito del archivo.


    @FXML
    private void openConfigScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfigScreen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Configuración");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label turnoLabel;

    @FXML
    private Label estadoJuegoLabel;
}