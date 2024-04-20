package com.mycompany.project;

import com.mycompany.project.homeControlleradd;
import com.mycompany.project.homeControlleradd.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;



public class homeControllerudd {
    
    @FXML
    private TextField DriverName;

    @FXML
    private TextArea driverNamesTextArea;
    
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
        void updateDriver(ActionEvent event) {
    String driverName = DriverName.getText();
    Driver driver = loadDriverFromFiles(driverName);
    if (driver != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Driver");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to update the details of " + driverName + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // remove the existing driver record from the file
            List<String> lines = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5 && parts[0].equals(driverName)) {
                        // skip the existing driver record
                        continue;
                    }
                    lines.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // write the updated driver record to the file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("drivers.txt"));
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // prompt the user to enter the new driver details
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Update Driver");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter the new details for " + driverName + ":\n\n" +
                    "Name: ");
            Optional<String> nameResult = dialog.showAndWait();
            if (nameResult.isPresent()) {
                String name = nameResult.get();
                dialog.setContentText("Age: ");
                Optional<String> ageResult = dialog.showAndWait();
                if (ageResult.isPresent()) {
                    int age = Integer.parseInt(ageResult.get());
                    dialog.setContentText("Team: ");
                    Optional<String> teamResult = dialog.showAndWait();
                    if (teamResult.isPresent()) {
                        String team = teamResult.get();
                        dialog.setContentText("Car: ");
                        Optional<String> carResult = dialog.showAndWait();
                        if (carResult.isPresent()) {
                            String car = carResult.get();
                            dialog.setContentText("Points: ");
                            Optional<String> pointsResult = dialog.showAndWait();
                            if (pointsResult.isPresent()) {
                                int points = Integer.parseInt(pointsResult.get());
                                // append the new driver record to the file
                                String record = name + "," + age + "," + team + "," + car + "," + points;
                                try {
                                    BufferedWriter writer = new BufferedWriter(new FileWriter("drivers.txt", true));
                                    writer.write(record);
                                    writer.newLine();
                                    writer.flush();
                                    writer.close();
                                    Alert success = new Alert(Alert.AlertType.INFORMATION);
                                    success.setTitle("Update Driver");
                                    success.setHeaderText(null);
                                    success.setContentText("Driver record updated successfully.");
                                    success.showAndWait();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Driver not found!");
        alert.showAndWait();
    }
    DriverName.clear();
}

private Driver loadDriverFromFiles(String driverName) {
    // Read the driver details from the file
    try {
        BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5 && parts[0].equals(driverName)) {
                String age = parts[1];
                String team = parts[2];
                String car = parts[3];
                String points = parts[4];
                int ageInt = Integer.parseInt(age);
                int pointsInt = Integer.parseInt(points);
                return new Driver(driverName, ageInt , team, car, pointsInt);
            }
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null; // driver not found
}

}









