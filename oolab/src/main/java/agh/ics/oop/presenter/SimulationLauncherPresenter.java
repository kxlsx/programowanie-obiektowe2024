package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationApp;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SimulationLauncherPresenter {
    private final List<Vector2d> ANIMAL_POSITIONS =
            List.of(new Vector2d(2,2), new Vector2d(3,4));

    private final SimulationEngine simEngine = new SimulationEngine();

    @FXML
    private TextField textField;

    public void onSimulationStartClicked() {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));

            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();
            SimulationApp.configureStage(stage, viewRoot);

            AbstractWorldMap map = new GrassField(10);
            presenter.setWorldMap(map);

            List<MoveDirection> moves = OptionsParser.parse(Arrays.stream(textField.getText().split(" ")).toList());

            simEngine.addSimulation(new Simulation(ANIMAL_POSITIONS, moves, map));
            simEngine.runAsync();
            stage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
