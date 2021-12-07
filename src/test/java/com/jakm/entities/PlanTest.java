package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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
        classToTest.generateAndRunNewPlan();

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

    @Test
    void basicCombinationOfPlans() {
        List<String> initialState1 = Arrays.asList("A", "B", "C", "D");
        List<String> targetState1 = Arrays.asList("A", "B", "C", "D");

        List<String> initialState2 = Arrays.asList("E", "F", "G", "H");
        List<String> targetState2 = Arrays.asList("E", "F", "G", "H");

        classToTest = new Plan(10, initialState1, targetState1);
        Step step1 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step2 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step3 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step4 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);

        List<Step> steps1 = new ArrayList<Step>();
        steps1.add(step1);
        steps1.add(step2);
        steps1.add(step3);
        steps1.add(step4);

        classToTest.setSteps(steps1);

        Plan otherPlan = new Plan(10, initialState2, targetState2);

        Step step5 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        Step step6 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        Step step7 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        Step step8 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);

        List<Step> steps2 = new ArrayList<Step>();
        steps2.add(step5);
        steps2.add(step6);
        steps2.add(step7);
        steps2.add(step8);

        otherPlan.setSteps(steps2);

        classToTest.combinePlans(otherPlan);

        assertEquals(4, classToTest.getSteps().size());
        assertEquals(step1, classToTest.getSteps().get(0));
        assertEquals(step2, classToTest.getSteps().get(1));
        assertEquals(step7, classToTest.getSteps().get(2));
        assertEquals(step8, classToTest.getSteps().get(3));

    }

    @Test
    void mutateOneStepInPlan() {

        List<String> initialState1 = Arrays.asList("A", "B", "C", "D");
        List<String> targetState1 = Arrays.asList("A", "B", "C", "D");

        classToTest = new Plan(10, initialState1, targetState1);
        Step step1 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step2 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step3 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step4 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);

        List<Step> steps1 = new ArrayList<Step>();
        steps1.add(step1);
        steps1.add(step2);
        steps1.add(step3);
        steps1.add(step4);

        classToTest.setSteps(steps1);

        classToTest.mutate();

        //now we expect that one and only one of the steps in classToTest has FirstStack as its 'from' member
        List<StackNames> fromPositions = new ArrayList<>();

        //collect all of the 'from' values from each of our steps
        IntStream.range(0, steps1.size()).forEach(i -> fromPositions.add(steps1.get(i).getFrom()));

        //we set them up with ORIGINSTACK in the from value, so with a mutation, ONE should be FIRSTSTACK
        assertTrue(fromPositions.contains(StackNames.FIRSTSTACK));

        //and of course this should only have happened one time
        int countOfOriginFromPositions = Collections.frequency(fromPositions, StackNames.ORIGINSTACK);
        assertEquals(3, countOfOriginFromPositions);

    }

    @Test
    void mutatorShouldThrowException() {

        List<String> initialState1 = Arrays.asList("A", "B", "C", "D");
        List<String> targetState1 = Arrays.asList("A", "B", "C", "D");

        classToTest = new Plan(10, initialState1, targetState1);

        classToTest.setMutators(null);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            classToTest.mutate();
        });

    }

}
