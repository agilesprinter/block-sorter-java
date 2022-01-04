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

        List<String> targetState = new Utils().blockSetCreator(Arrays.asList("A", "B", "C"));
        List<String> currentState = new Utils().blockSetCreator(Arrays.asList("A", "B", "C"));

        assertEquals(100, classToTest.howFit(targetState, currentState));


    }

    @Test
    void howFit_ShouldReturn1() {

        List<String> targetState = new Utils().blockSetCreator(Arrays.asList("A", "B", "C"));
        List<String> currentState = new Utils().blockSetCreator(Arrays.asList("A", "C", "B"));

        assertEquals(35, classToTest.howFit(targetState, currentState));

    }

    @Test
    void howFit_ShouldReturn0() {

        List<String> targetState = new Utils().blockSetCreator(Arrays.asList("A", "B", "C"));
        List<String> currentState = new Utils().blockSetCreator(Arrays.asList("C", "A", "B"));

        assertEquals(2, classToTest.howFit(targetState, currentState));


    }

    @Test
    void percentageFit_should_return_100() {

        int targetStateSize = 5, rawScore = 5;

        assertEquals(100, classToTest.percentageFit(targetStateSize, rawScore));

    }

    @Test
    void percentageFit_should_return_50() {

        int targetStateSize = 6, rawScore = 3;

        assertEquals(50, classToTest.percentageFit(targetStateSize, rawScore));

    }

    @Test
    void percentageFit_should_return_0_for_target_0() {

        int targetStateSize = 0, rawScore = 3;

        assertEquals(0, classToTest.percentageFit(targetStateSize, rawScore));

    }

    @Test
    void percentageFit_should_return_0_for_rawScore_0() {

        int targetStateSize = 6, rawScore = 0;

        assertEquals(0, classToTest.percentageFit(targetStateSize, rawScore));

    }

    @Test
    void percentageFit_should_give_points_for_correct_length() {

        List<String> targetState = new Utils().blockSetCreator(Arrays.asList("A", "B", "C", "D"));
        List<String> currentState = new Utils().blockSetCreator(Arrays.asList("C", "A", "B", "D"));

        int howFit = classToTest.howFit(targetState, currentState);

        //we have one block in the right place so we should get 25 points for that
        //we have the correct length of plan so we should get an extra 10 points for that

        assertEquals(27, classToTest.howFit(targetState, currentState));


    }

}
