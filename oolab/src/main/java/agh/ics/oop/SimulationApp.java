package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.presenter.SimulationLauncherPresenter;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws IOException {
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulationLauncher.fxml"));
        BorderPane viewRoot = loader.load();
        configureStage(primaryStage, viewRoot);
    }

    public static void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
