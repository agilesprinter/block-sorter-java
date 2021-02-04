package com.jakm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerationTest {

    private Generation classToTest;
    List<Plan> plans;
    List<String> initialState;
    List<String> targetState;

    @BeforeEach
    void setUp() {

        plans = new ArrayList<Plan>();
        initialState = Arrays.asList("A", "B", "C");
        targetState = Arrays.asList("C", "B", "A");

    }


    @Test
    void createPlan_creates100Plans() {

        classToTest = new Generation(plans, initialState, targetState, 100, 10);

        classToTest.createPlans();

        assertEquals(100, classToTest.getPlans().size());

    }

    @Test
    void create_generation_throws_exception() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Generation(plans, initialState, targetState, 0, 10);
        });

        String expectedMessageNumberDifferent = "Your problem configuration is incorrect. Program cannot continue";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageNumberDifferent));

        exception = assertThrows(RuntimeException.class, () -> {
            new Generation(plans, null, targetState, 100, 10);
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageNumberDifferent));

        exception = assertThrows(RuntimeException.class, () -> {
            new Generation(plans, initialState, null, 100, 10);
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageNumberDifferent));

        exception = assertThrows(RuntimeException.class, () -> {
            new Generation(plans, initialState, null, 100, 0);
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageNumberDifferent));

    }

}
