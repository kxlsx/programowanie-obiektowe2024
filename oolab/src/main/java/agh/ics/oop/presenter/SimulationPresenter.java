package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.World;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Arrays;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private final static int CELL_WIDTH = 64;
    private final static int CELL_HEIGHT = 64;

    @FXML
    private GridPane mapGrid;
    @FXML
    private Label moveLabel;

    private AbstractWorldMap worldMap;

    public void setWorldMap(AbstractWorldMap map) {
        this.worldMap = map;
        worldMap.addObserver(this);
    }

    public void drawMap() {
       clearGrid();
       drawGrid();
    }

    private void drawGrid() {
        Vector2d lowerLeft = worldMap.getCurrentBounds().lowerLeft();
        Vector2d upperRight = worldMap.getCurrentBounds().upperRight();
        int width = upperRight.getX() - lowerLeft.getX();
        int height = upperRight.getY() - lowerLeft.getY();

        setGridConstraints(width, height);

        mapGrid.add(createGridCell("y/x"), 0, 0);
        for(int j = 1; j < width; j++) {
            mapGrid.add(createGridCell(Integer.toString(lowerLeft.getX() + j - 1)), j, 0);
        }
        for(int i = 1; i < height; i++) {
            mapGrid.add(createGridCell(Integer.toString(upperRight.getY() - i - 1)), 0, i);
            for(int j = 1; j < width; j++){
                WorldElement elem = worldMap.objectAt(new Vector2d(lowerLeft.getX() + j - 1, upperRight.getY() - i + 1));
                mapGrid.add(createGridCell(elem == null ? " " : elem.toString()), j, i);
            }
        }
    }

    private Label createGridCell(String text) {
        Label tmp = new Label(text);
        GridPane.setHalignment(tmp, HPos.CENTER);
        return tmp;
    }

    private void setGridConstraints(int width, int height) {
        for(int i = 0; i < height; i++ ) {
            mapGrid.getColumnConstraints().add(0, new ColumnConstraints(CELL_WIDTH));
            mapGrid.getRowConstraints().add(0, new RowConstraints(CELL_HEIGHT));
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveLabel.setText(message);
        });
    }
}
