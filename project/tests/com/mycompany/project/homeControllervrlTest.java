package com.mycompany.project;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class homeControllervrlTest {

    @Test
    void testRaceDetails() {
        homeControllervrl controller = new homeControllervrl();

        // Test with a date that has race details
        LocalDate selectedDate = LocalDate.of(2022, 5, 15);
        controller.RaceDetails(selectedDate);
        assertEquals("Barcelona", controller.locationText.getText());

        // Test with a date that has no race details
        selectedDate = LocalDate.of(2022, 5, 16);
        controller.RaceDetails(selectedDate);
        assertEquals("No race details found for selected date: 2022-05-16", controller.detailsTextArea.getText().trim());
    }
}