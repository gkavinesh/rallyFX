package com.mycompany.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class homeControllervrl {
    
    
    @FXML
    private DatePicker datePicker;

    @FXML
    TextArea detailsTextArea;

    @FXML
    private AnchorPane root;

    @FXML
    private Button showDetailsBtn;
    
    @FXML
    Text locationText;
    

public void showRaceDetails(ActionEvent event){
    
    LocalDate selectedDate = datePicker.getValue();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    RaceDetails(selectedDate);
}



public void RaceDetails(LocalDate selectedDate) {
    try {
        File file = new File("racedetails.txt");
        Scanner scanner = new Scanner(file);

        String location = "";
        ArrayList<String> raceDetails = new ArrayList<>();
        ArrayList<RaceDetail> results = new ArrayList<>();

        boolean foundDate = false;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("Date: ")) {
                // If we found a new date, check if it matches the selected date
                LocalDate date = LocalDate.parse(line.substring("Date: ".length()), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (date.equals(selectedDate)) {
                    foundDate = true;
                    raceDetails.add(line);
                    location = scanner.nextLine().substring("Track Location: ".length());
                    raceDetails.add("Track Location: " + location);
                } else {
                    foundDate = false;
                    // If the date doesn't match, skip to the next date
                    try (BufferedReader reader = new BufferedReader(new FileReader("raceDetails.txt"))) {
    String nextLine;
    while ((nextLine = reader.readLine()) != null) {
        if (nextLine.startsWith("Date: ")) {
            if (date.equals(selectedDate)) {
                System.out.println("\n" + nextLine);
                System.out.println(reader.readLine());
                System.out.println(reader.readLine());
                System.out.println(reader.readLine());
                System.out.println(reader.readLine());
                reader.readLine();
                break;
            } else {
                reader.readLine();
                reader.readLine();
                reader.readLine();
            }
        }
    }
} catch (IOException e) {
    System.out.println("Error reading file: " + e.getMessage());
}
                }
                    
                    
            } else if (foundDate && (line.startsWith("Max Verstappen") || line.startsWith("Lewis Hamilton")
                    || line.startsWith("Fernando Alonso") || line.startsWith("Sergio Perez")
                    || line.startsWith("Lando Norris")||line.startsWith("Pierre Gasly")||line.startsWith("Carlos Sainz"))) {
                String[] parts = line.split(" - Position: ");
                String driverName = parts[0];
                String[] resultParts = parts[1].split(", Points: ");
                int position = Integer.parseInt(resultParts[0]);
                int points = Integer.parseInt(resultParts[1]);
                results.add(new RaceDetail(position, driverName, points));
            }
        }

        scanner.close();

        if (results.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Race Simulator");
            alert.setHeaderText(null);
            alert.setContentText("No race details found for selected date: " + selectedDate);
            alert.showAndWait();
            return;
        }

        locationText.setText(location);

        // Format the results as a table string
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append(String.format("%-20s %-10s %-10s\n", "Driver Name", "Position", "Points"));
        for (RaceDetail result : results) {
            tableBuilder.append(String.format("%-20s %-10d %-10d\n", result.getDriverName(), result.getPosition(),
                    result.getPoints()));
        }

        // Set the text area's font to monospaced
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 12);
        detailsTextArea.setFont(font);

        // Set the text area's content to the formatted table string
        detailsTextArea.setText(tableBuilder.toString());

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}



private static class RaceDetail {
    private int position;
    private String driverName;
    private int points;

    public RaceDetail(int position, String driverName, int points) {
        this.position = position;
        this.driverName = driverName;
        this.points = points;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public String getDriverName() {
        return this.driverName;
    }
    
    public int getPoints() {
        return this.points;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "RaceDetail{" +
                "position=" + position +
                ", driverName='" + driverName + '\'' +
                ", points=" + points +
                '}';
    }
}





}










