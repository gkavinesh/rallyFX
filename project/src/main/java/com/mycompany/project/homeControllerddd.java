package com.mycompany.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;


public class homeControllerddd {

    @FXML
    private TextField nameField;

    @FXML
    private Button deleteButton;
    
    @FXML
    private TextArea driverNamesTextArea;

    // Load the list of drivers from the file
    private List<Driver> drivers;


    public void initialize(){
        try{
            BufferedReader reader = new BufferedReader (new FileReader("drivers.txt"));
            String line = reader.readLine();
            while (line != null){
                String[] tokens = line.split(",");
                driverNamesTextArea.appendText(tokens[0] + "\n");
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        String name = nameField.getText();
        boolean found = false;
        for (Driver driver : drivers) {
            if (driver.getName().equals(name)) {
                drivers.remove(driver);
                found = true;
                break;
            }
        }
        if (!found) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Driver details");
            alert.setHeaderText(null);
            alert.setContentText("Driver name not found ! " );
            alert.showAndWait();
            return;
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Driver Details");
            alert.setHeaderText(null);
            alert.setContentText("Driver details successfully deleted");
            alert.showAndWait();
        }
        nameField.clear();
        // Write the updated list of drivers back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter("drivers.txt"));
        for (Driver driver : drivers) {
            writer.write(driver.getName() + "," + driver.getAge() + "," + driver.getTeam() + "," + driver.getCar() + "," + driver.getPoints());
            writer.newLine();
        }
        writer.close();
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();
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









