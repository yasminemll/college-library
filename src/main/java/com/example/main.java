package com.example;

import gui.MainInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage stage) {
        MainInterface ui = new MainInterface();
        ui.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}