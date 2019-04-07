package com.jakm.entities;

import com.jakm.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FitnessFunctionTest {

    private FitnessFunciton classToTest;

    @BeforeEach
    void setUp() {

        classToTest = new FitnessFunciton();
    }


    @Test
    void howFit_passNullThrowsException() {

        assertThrows(RuntimeException.class, () -> classToTest.howFit(null, null));

    }

    @Test
    void howFit_ShouldRetunr3() {

        List<Block> targetState = new Utils().blockSetCreator(Arrays.asList(new String[]{"A", "B", "C"}));
        List<Block> currentState = new Utils().blockSetCreator(Arrays.asList(new String[]{"A", "B", "C"}));

        assertEquals(classToTest.howFit(targetState, currentState), 3);


    }

    @Test
    void howFit_ShouldReturn1() {

        List<Block> targetState = new Utils().blockSetCreator(Arrays.asList(new String[]{"A", "B", "C"}));
        List<Block> currentState = new Utils().blockSetCreator(Arrays.asList(new String[]{"A", "C", "B"}));

        assertEquals(classToTest.howFit(targetState, currentState), 1);


    }

    @Test
    void howFit_ShouldReturn0() {

        List<Block> targetState = new Utils().blockSetCreator(Arrays.asList(new String[]{"A", "B", "C"}));
        List<Block> currentState = new Utils().blockSetCreator(Arrays.asList(new String[]{"C", "A", "B"}));

        assertEquals(classToTest.howFit(targetState, currentState), 0);


    }

}
