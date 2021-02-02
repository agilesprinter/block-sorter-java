package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlanTest {

    private Plan classToTest;
    private int planSize;

    @BeforeEach
    void setUp() {

        List<String> targetState = Arrays.asList("B", "A", "C");
        List<String> initialState = Arrays.asList("A", "B", "C");

        Stacks testStacks = new Stacks(initialState);

        classToTest = new Plan(10, initialState, targetState);

    }

    @Test
    void generatePlan_shouldGenerateARandomPlan() {

        //the plan moves around its blocks as it creates the plan
        classToTest.generateAndRunPlan();

        Assert.assertEquals(10, classToTest.getSteps().size());
    }

    @Test
    void getNonEmptyFromStack_shouldGiveMeAStackToMoveFrom() {

        StackNames fromStackSuggestion = classToTest.getNonEmptyFromStack();

        Assert.assertEquals(StackNames.ORIGINSTACK, fromStackSuggestion);

    }

    @Test
    void getAnyToBlockOtherThan_shouldGiveMeAStackToMoveTo() {

        StackNames toStackSuggestion = classToTest.getAnyToStackOtherThan(StackNames.ORIGINSTACK);

        //this should give randomly firstStack or secondStack
        Assert.assertTrue(StackNames.FIRSTSTACK.equals(toStackSuggestion) || StackNames.SECONDSTACK.equals(toStackSuggestion));

    }

    @Test
    void constructor_wrongSizedTargetShouldBeRejected() {

        List<String> targetState = Arrays.asList("B", "A", "C");
        List<String> initialState = Arrays.asList("A", "B");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Plan(10, initialState, targetState);
        });

        String expectedMessageNumberDifferent = "Initial state and target state must have the same number of blocks";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessageNumberDifferent));

    }

    @Test
    void constructor_inconsistentTargetShouldBeRejected() {

        List<String> targetState = Arrays.asList("B", "A", "C");
        List<String> initialState = Arrays.asList("A", "B", "D");


        Exception exception = assertThrows(RuntimeException.class, () -> {
            classToTest = new Plan(10, initialState, targetState);
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "Initial state and target state must have the same block names";
        assertTrue(actualMessage.contains(expectedMessage));

    }

}
