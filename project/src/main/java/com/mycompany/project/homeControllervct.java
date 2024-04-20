package com.mycompany.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class homeControllervct implements Initializable {

    @FXML
    private TableView<Driver> driverTableView;
    @FXML
    private TableColumn<Driver, String> namecolumn;
    @FXML
    private TableColumn<Driver, Integer> agecolumn;
    @FXML
    private TableColumn<Driver, String> teamcolumn;
    @FXML
    private TableColumn<Driver, String> carcolumn;
    @FXML
    private TableColumn<Driver, Integer> pointscolumn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Read data from the text file
            List<Driver> drivers = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 5){
                Driver driver = new Driver(fields[0], Integer.parseInt(fields[1]), fields[2], fields[3], Integer.parseInt(fields[4]));
                drivers.add(driver);
                }else{
                    System.out.println("Invalid Data: "+ line);
                }
            }
            reader.close();
            
            Collections.sort(drivers,Comparator.comparingInt(Driver::getPoints).reversed());

            // Populate the table with the data
            ObservableList<Driver> driverList = FXCollections.observableArrayList(drivers);
            driverTableView.setItems(driverList);

            namecolumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            agecolumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            teamcolumn.setCellValueFactory(new PropertyValueFactory<>("team"));
            carcolumn.setCellValueFactory(new PropertyValueFactory<>("car"));
            pointscolumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    public static class Driver {
        private String name;
        private int age;
        private String team;
        private String car;
        private int points;

        public Driver(String name, int age, String team, String car, int points) {
            this.name = name;
            this.age = age;
            this.team = team;
            this.car = car;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getTeam() {
            return team;
        }

        public String getCar() {
            return car;
        }

        public int getPoints() {
            return points;
        }
    }
}
    
    









