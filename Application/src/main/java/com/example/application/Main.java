package com.example.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lib/Overview.fxml"));
        primaryStage.setTitle("Můj Rozpočet");
        primaryStage.setScene(new Scene(loader.load()));

        OverviewController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setWidth(700);
        primaryStage.setHeight(500);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}