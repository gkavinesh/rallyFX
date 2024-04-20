package com.mycompany.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        primaryStage.setTitle("  RallyFX");
        primaryStage.setScene(new Scene(root, 640, 420));
        primaryStage.show();
    }
    public static void main(String[] args) {

        launch();
    }

}