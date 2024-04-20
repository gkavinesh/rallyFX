package com.mycompany.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class homeControlleraddTest {

    private homeControlleradd.Driver driver;

    @BeforeEach
    void setUp() {
        driver = new homeControlleradd.Driver("John Doe", 30, "Team A", "Car A", 100);

    }

    @Test
    void testGetName() {
        assertEquals("John Doe", driver.getName());
    }

    @Test
    void testGetAge() {
        assertEquals(30, driver.getAge());
    }

    @Test
    void testGetTeam() {
        assertEquals("Team A", driver.getTeam());
    }

    @Test
    void testGetCar() {
        assertEquals("Car A", driver.getCar());
    }

    @Test
    void testGetPoints() {
        assertEquals(100, driver.getPoints());
    }

}