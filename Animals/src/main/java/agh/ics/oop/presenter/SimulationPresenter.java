package agh.ics.oop.presenter;

import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class SimulationPresenter implements MapChangeListener {

    private static final double CELL_WIDTH = 35;
    private static final double CELL_HEIGHT = 35;

    WorldMap map;

    @FXML
    GridPane mapGrid = new GridPane();

    @FXML
    private Label moveLabel;

    public void setWorldMap(WorldMap map) {
        this.map = map;
    }

    public void drawMap(WorldMap worldMap) {
        clearGrid();

        Boundary bounds = worldMap.getCurrentBounds();

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX() + 1; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }

        for (int i = bounds.lowerLeft().getY(); i <= bounds.upperRight().getY() + 1; i++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        int toAddX = -bounds.lowerLeft().getX() + 1;
        int toAddY = -bounds.lowerLeft().getY() + 1;

        Label label = new Label("y/x");
        mapGrid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX(); i++) {
            label = new Label(String.format("%d", i));
            mapGrid.add(label, i + toAddX, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = bounds.lowerLeft().getY(); i <= bounds.upperRight().getY(); i++) {
            label = new Label(String.format("%d", i));
            mapGrid.add(label, 0, i + toAddY);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int i = bounds.lowerLeft().getX(); i <= bounds.upperRight().getX(); i++) {
            for (int j = bounds.lowerLeft().getY(); j <= bounds.upperRight().getY(); j++) {
                label = new Label(" ");

                if (map.isOccupied(new Vector2d(i, j))) {
                    WorldElement element = map.objectAt(new Vector2d(i, j));
                    if (element != null) {
                        label.setText(element.toString());
                    }
                }
                mapGrid.add(label, i + toAddX, j + toAddY);
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

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

}
