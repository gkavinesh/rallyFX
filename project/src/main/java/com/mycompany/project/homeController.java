package com.mycompany.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.Alert;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class homeController {

    @FXML
    private Button openvctWindow;

    @FXML
    private BorderPane borderpane;

    @FXML
    private Button openaddwindow;

    @FXML
    private Button opendddWindow;

    @FXML
    private Button openuddwindow;
    
    @FXML
    private Button simulaterace;
    
    

    @FXML
    public void openaddWindow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(" Add Driver Details");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        stage.show();
    }
    
    
    

    @FXML
    public void opendddWindow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ddd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(" Delete Driver Details");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        stage.show();
    }
    
    @FXML
    public void openuddWindow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("udd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(" Update Driver Details");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        stage.show();
    }

    
    @FXML
    public void openvctWindow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("vct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
         stage.setTitle(" Driver Details");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        stage.show();
    }
    
    @FXML
    public void openvrlWindow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("vrl.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(" View race Details");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/favicon.jpg")));
        stage.show();
    }
    
    private static final int FIRST_PLACE_POINTS = 10;
    private static final int SECOND_PLACE_POINTS = 7;
    private static final int THIRD_PLACE_POINTS = 5;
    private static final String[] LOCATIONS = {"Nyirád", "Höljes", "Montalegre", "Barcelona", "Rīga", "Norway"};
    
    @FXML 
public void simulateRace(){
    HashMap<String, Integer> driverPoints = new HashMap<>();

    try {
        // Read driver details from text file
        File file = new File("drivers.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] driverDetails = scanner.nextLine().split(",");
            if (driverDetails.length > 1) {
                String driverName = driverDetails[0];
                int driverPosition = Integer.parseInt(driverDetails[1]);
                driverPoints.put(driverName, driverPosition);
            }
            
        }

        scanner.close();

        // Generate random race
        ArrayList<String> drivers = new ArrayList<>(driverPoints.keySet());
        Collections.shuffle(drivers);

        // Assign points to each driver based on finishing position
        for (int i = 0; i < drivers.size(); i++) {
            String driverName = drivers.get(i);
            int driverPosition = i + 1;

            int points = 0;
            if (driverPosition == 1) {
                points = FIRST_PLACE_POINTS;
            } else if (driverPosition == 2) {
                points = SECOND_PLACE_POINTS;
            } else if (driverPosition == 3) {
                points = THIRD_PLACE_POINTS;
            } else {
                // Assign 0 points to drivers who come in positions after 3
                points = 0;
            }

            driverPoints.put(driverName, points);
        }
        
        Random random = new Random();
        
        

        // Save race details to new text file
        String location = LOCATIONS[(int) (Math.random() * LOCATIONS.length)];
        LocalDate today = LocalDate.now();
        LocalDate tenDaysAgo = today.minusDays((long) (Math.random() * 11));
        LocalDate date = tenDaysAgo.plusDays((long) (Math.random() * 11));
        String fileName = "racedetails.txt";
        File raceFile = new File(fileName);
        FileWriter writer = new FileWriter(raceFile, true); // Pass true to append to the file

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        writer.write("Date: " + formatter.format(date) + "\n");
        writer.write("Track Location: " + location + "\n");

        for (String driverName : drivers) {
            int driverPosition = drivers.indexOf(driverName)+1;
            int driverPointsScored = driverPoints.get(driverName);
            if (driverPosition <= 3) {
                if (driverPosition == 1) {
                    driverPointsScored = FIRST_PLACE_POINTS;
                    writer.write(driverName + " - Position: " + driverPosition + ", Points: " + driverPointsScored + "\n");
                } else if (driverPosition == 2) {
                    driverPointsScored = SECOND_PLACE_POINTS;
                    writer.write(driverName + " - Position: " + driverPosition + ", Points: " + driverPointsScored + "\n");
                } else if (driverPosition == 3) {
                    driverPointsScored = THIRD_PLACE_POINTS;
                    writer.write(driverName + " - Position: " + driverPosition + ", Points: " + driverPointsScored + "\n");
                }
            } else {
                // Print drivers who came in positions after 3 with 0 points
                writer.write(driverName + " - Position: " + driverPosition + ", Points: 0\n");
            }

            
        }

        writer.close();


            System.out.println("Race details saved to file: " + fileName);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Race Simulator");
            alert.setHeaderText(null);
            alert.setContentText("Race succesfully simulated !\nRace details saved to file: " + fileName);
            alert.showAndWait();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
    
    
    
  





