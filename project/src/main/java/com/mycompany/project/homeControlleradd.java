package com.mycompany.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class homeControlleradd {

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField ageTextField;
    @FXML
    public TextField teamTextField;
    @FXML
    public TextField carTextField;
    @FXML
    public TextField pointsTextField;
    @FXML
    private Button saveButton;

    @FXML
    public void setDriver(Driver Driver) {
        if (nameTextField != null) {
            nameTextField.setText(Driver.getName());
        }
        if (ageTextField != null) {
            ageTextField.setText(String.valueOf(Driver.getAge()));
        }
        if (teamTextField != null) {
            teamTextField.setText(Driver.getTeam());
        }
        if (carTextField != null) {
            carTextField.setText(Driver.getCar());
        }
        if (pointsTextField != null) {
            pointsTextField.setText(String.valueOf(Driver.getPoints()));
        }
    }

    @FXML
    public void saveDriver() {
        if (nameTextField == null || ageTextField == null || teamTextField == null || carTextField == null || pointsTextField == null) {
            System.err.println("Error: text fields are null.");
            return;
        }

        String name = nameTextField.getText();
        String age = ageTextField.getText();
        String team = teamTextField.getText();
        String car = carTextField.getText();
        String points = pointsTextField.getText();

        if (name.isBlank() || age.isBlank() || team.isBlank() || car.isBlank() || points.isBlank()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incomplete details. Please fill in all fields.");
        alert.showAndWait();
        return;
    }


        // Save the data to a text file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("drivers.txt", true));
            writer.write(name + "," + age + "," + team + "," + car + "," + points);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Driver Details");
            alert.setHeaderText(null);
            alert.setContentText("Driver details successfully added");
            alert.showAndWait();

        // Clear the text fields after saving
        nameTextField.clear();
        ageTextField.clear();
        teamTextField.clear();
        carTextField.clear();
        carTextField.clear();
        pointsTextField.clear();
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
    
    
    






