package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class SimulationPresenter implements MapChangeListener {

    private static final double CELL_WIDTH = 30;
    private static final double CELL_HEIGHT = 30;
    @FXML
    private TextField textField;
    WorldMap map;

    @FXML
    GridPane mapGrid = new GridPane();

    @FXML
    private Label infoLabel;

    @FXML
    private Label moveLabel;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap(WorldMap worldMap) {
        clearGrid();

        Boundary bounds = worldMap.getCurrentBounds();

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX()+1; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        for (int i = bounds.lowerLeft().getY(); i <= bounds.upperRight().getY()+1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        int toAddX = -bounds.lowerLeft().getX() + 1;
        int toAddY = -bounds.lowerLeft().getY() + 1;

        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX(); i++){
            label = new Label(String.format("%d", i));
            mapGrid.add(label, i+toAddX, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = bounds.lowerLeft().getY(); i <= bounds.upperRight().getY(); i++){
            label = new Label(String.format("%d", i));
            mapGrid.add(label, 0, i+toAddY);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX(); i++){
            for (int j = bounds.lowerLeft().getY(); j <= bounds.upperRight().getY(); j++) {
                label = new Label(" ");

                if (map.isOccupied(new Vector2d(i,j))) {
                    WorldElement element = map.objectAt(new Vector2d(i,j));
                    if (element != null) {
                        label.setText(element.toString());
                    }
                }
                mapGrid.add(label, i+toAddX, j+toAddY);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(worldMap);
            moveLabel.setText(message);
        });
    }

    @FXML
    public void onSimulationStartClicked() {
        List<MoveDirection> directions = OptionsParser.getDirection(textField.getText().split(" "));
        List<Vector2d> positions = List.of(new Vector2d(-5, -5), new Vector2d(3, 4));
        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        try {
            simulationEngine.runAsyncInThreadPool();
//            simulationEngine.awaitSimulationEnd();
        } catch (IllegalArgumentException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

}
