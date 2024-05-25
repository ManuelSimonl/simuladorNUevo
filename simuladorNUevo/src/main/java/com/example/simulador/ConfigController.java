package com.example.simulador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ConfigController {

    @FXML
    private Slider rowsSlider;
    @FXML
    private Slider colsSlider;
    @FXML
    private Label rowsLabel;
    @FXML
    private Label colsLabel;
    @FXML
    private Slider aguaSlider;
    @FXML
    private Label aguaLabel;
    @FXML
    private Slider pozoSlider;
    @FXML
    private Label pozoLabel;
    @FXML
    private Slider bibliotecaSlider;
    @FXML
    private Label bibliotecaLabel;
    @FXML
    private Slider tesoroSlider;
    @FXML
    private Label tesoroLabel;
    @FXML
    private Slider montañaSlider;
    @FXML
    private Label montañaLabel;
    @FXML
    private Slider comidaSlider;
    @FXML
    private Label comidaLabel;

    @FXML
    private void confirmConfiguration() {
        try {
            int rows = (int) rowsSlider.getValue();
            int cols = (int) colsSlider.getValue();

            // Guardar las filas y columnas en la clase Configuration
            Configuration.setRows(rows);
            Configuration.setCols(cols);

            // Crear una instancia de Configuration
            Configuration configuration = new Configuration();

            // Guardar la configuración de recursos en la instancia de Configuration
            configuration.setAgua((int) aguaSlider.getValue());
            configuration.setPozo((int) pozoSlider.getValue());
            configuration.setBiblioteca((int) bibliotecaSlider.getValue());
            configuration.setTesoro((int) tesoroSlider.getValue());
            configuration.setMontaña((int) montañaSlider.getValue());
            configuration.setComida((int) comidaSlider.getValue());


        } catch (NumberFormatException e) {
            // Manejar error si los valores ingresados no son números válidos
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarVentanaConfiguracion() {
        // Obteniendo la escena actual y cerrando la ventana
        rowsSlider.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() {
        // Configura los valores iniciales y los límites de los sliders
        rowsSlider.setMin(1);
        rowsSlider.setMax(100);
        colsSlider.setMin(1);
        colsSlider.setMax(100);
        rowsSlider.setPrefWidth(200);
        colsSlider.setPrefWidth(200);
        aguaSlider.setMin(1);
        aguaSlider.setMax(100);
        aguaSlider.setPrefWidth(200);
        pozoSlider.setMin(1);
        pozoSlider.setMax(100);
        pozoSlider.setPrefWidth(200);
        bibliotecaSlider.setMin(1);
        bibliotecaSlider.setMax(100);
        bibliotecaSlider.setPrefWidth(200);
        tesoroSlider.setMin(1);
        tesoroSlider.setMax(100);
        tesoroSlider.setPrefWidth(200);
        montañaSlider.setMin(1);
        montañaSlider.setMax(100);
        montañaSlider.setPrefWidth(200);
        comidaSlider.setMin(1);
        comidaSlider.setMax(100);
        comidaSlider.setPrefWidth(200);


        // Establecer valores por defecto para filas y columnas
        rowsSlider.setValue(Configuration.getRows());
        colsSlider.setValue(Configuration.getCols());


        // Configura los listeners de cambio de valor para los sliders
        comidaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            comidaLabel.setText(String.valueOf(newValue.intValue()));
        });

        montañaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            montañaLabel.setText(String.valueOf(newValue.intValue()));
        });

        tesoroSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            tesoroLabel.setText(String.valueOf(newValue.intValue()));
        });

        bibliotecaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            bibliotecaLabel.setText(String.valueOf(newValue.intValue()));
        });

        pozoSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            pozoLabel.setText(String.valueOf(newValue.intValue()));
        });

        aguaSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            aguaLabel.setText(String.valueOf(newValue.intValue()));
        });

        rowsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            rowsLabel.setText(String.valueOf(newValue.intValue()));
        });

        colsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Actualiza el valor del label correspondiente
            colsLabel.setText(String.valueOf(newValue.intValue()));
        });
    }
}
