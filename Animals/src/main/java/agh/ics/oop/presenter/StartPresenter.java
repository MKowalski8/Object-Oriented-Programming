package agh.ics.oop.presenter;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StartPresenter {
    @FXML
    private TextField textField;

    @FXML
    private Label infoLabel;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @FXML
    public void onSimulationStartClicked() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/simulation.fxml"));

            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();

            GrassField grassField = new GrassField(10);
            presenter.setWorldMap(grassField);
            simulationStart(textField.getText().split(" "), grassField);
            Stage stage = new Stage();
            configureStage(stage, viewRoot);
            stage.show();
    }
    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation screen");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    public void simulationStart(String[] args, WorldMap map) {
        List<MoveDirection> directions = OptionsParser.getDirection(args);
        List<Vector2d> positions = List.of(new Vector2d(-3, -3), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions, map);
        executorService.submit(simulation);
    }
}
