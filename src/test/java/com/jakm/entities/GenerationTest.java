package com.jakm.entities;

import com.jakm.interfaces.StackNames;
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

    @Test
    void doesGenerationSelectRightNumberOfGoodPlans() {

        List<String> initialState = Arrays.asList("B", "A", "C");
        List<String> targetState = Arrays.asList("A", "B", "C");

        Plan firstPlan = new Plan(3, initialState, targetState);
        Plan secondPlan = new Plan(3, initialState, targetState);
        Plan thirdPlan = new Plan(3, initialState, targetState);
        Plan fourthPlan = new Plan(3, initialState, targetState);

        firstPlan.setPlanScore(10);
        secondPlan.setPlanScore(9);
        thirdPlan.setPlanScore(8);
        fourthPlan.setPlanScore(7);

        classToTest = new Generation(plans, initialState, targetState, 100, 10);

        classToTest.getPlans().add(firstPlan);
        classToTest.getPlans().add(secondPlan);
        classToTest.getPlans().add(thirdPlan);
        classToTest.getPlans().add(fourthPlan);

        List<Plan> bestPlans = classToTest.getBestPlans(50);

        assertEquals(2, bestPlans.size());
        assertTrue(bestPlans.contains(firstPlan));
        assertTrue(bestPlans.contains(secondPlan));

    }

    @Test
    void doesGenerationSelectRightNumberOfWorstPlans() {

        List<String> initialState = Arrays.asList("B", "A", "C");
        List<String> targetState = Arrays.asList("A", "B", "C");

        Plan firstPlan = new Plan(3, initialState, targetState);
        Plan secondPlan = new Plan(3, initialState, targetState);
        Plan thirdPlan = new Plan(3, initialState, targetState);
        Plan fourthPlan = new Plan(3, initialState, targetState);

        firstPlan.setPlanScore(10);
        secondPlan.setPlanScore(9);
        thirdPlan.setPlanScore(8);
        fourthPlan.setPlanScore(7);

        classToTest = new Generation(plans, initialState, targetState, 100, 10);

        classToTest.getPlans().add(firstPlan);
        classToTest.getPlans().add(secondPlan);
        classToTest.getPlans().add(thirdPlan);
        classToTest.getPlans().add(fourthPlan);

        List<Plan> worstPlans = classToTest.getWorstPlans(50);

        assertEquals(2, worstPlans.size());
        assertTrue(worstPlans.contains(thirdPlan));
        assertTrue(worstPlans.contains(fourthPlan));

    }

    @Test
    void doesGenerationSelectRightNumberOfWorstPlansWithTiedScores() {

        List<String> initialState = Arrays.asList("B", "A", "C");
        List<String> targetState = Arrays.asList("A", "B", "C");

        Plan firstPlan = new Plan(3, initialState, targetState);
        Plan secondPlan = new Plan(3, initialState, targetState);
        Plan thirdPlan = new Plan(3, initialState, targetState);
        Plan fourthPlan = new Plan(3, initialState, targetState);
        Plan fifthPlan = new Plan(3, initialState, targetState);
        Plan sixthPlan = new Plan(3, initialState, targetState);
        Plan seventhPlan = new Plan(3, initialState, targetState);
        Plan eithhPlan = new Plan(3, initialState, targetState);
        Plan ninthPlan = new Plan(3, initialState, targetState);
        Plan tenthPlan = new Plan(3, initialState, targetState);

        firstPlan.setPlanScore(10);
        secondPlan.setPlanScore(9);
        thirdPlan.setPlanScore(8);
        fourthPlan.setPlanScore(5);
        fifthPlan.setPlanScore(5);
        sixthPlan.setPlanScore(4);
        seventhPlan.setPlanScore(4);
        eithhPlan.setPlanScore(4);
        ninthPlan.setPlanScore(4);
        tenthPlan.setPlanScore(4);

        classToTest = new Generation(plans, initialState, targetState, 100, 10);

        classToTest.getPlans().add(firstPlan);
        classToTest.getPlans().add(secondPlan);
        classToTest.getPlans().add(thirdPlan);
        classToTest.getPlans().add(fourthPlan);
        classToTest.getPlans().add(fifthPlan);
        classToTest.getPlans().add(sixthPlan);
        classToTest.getPlans().add(seventhPlan);
        classToTest.getPlans().add(eithhPlan);
        classToTest.getPlans().add(ninthPlan);
        classToTest.getPlans().add(tenthPlan);

        List<Plan> worstPlans = classToTest.getWorstPlans(20);

        assertEquals(2, worstPlans.size());
        assertTrue(worstPlans.contains(tenthPlan));
        assertTrue(worstPlans.contains(ninthPlan));

    }

    @Test
    void mateTwoSimplePlans() {

        List<String> initialState = Arrays.asList("B", "A", "C");
        List<String> targetState = Arrays.asList("A", "B", "C");

        classToTest = new Generation(plans, initialState, targetState, 2, 4);

        Plan firstPlan = new Plan(4, initialState, targetState);
        Step step1 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step2 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step3 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        Step step4 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        List<Step> steps1 = new ArrayList<>();
        steps1.add(step1);
        steps1.add(step2);
        steps1.add(step3);
        steps1.add(step4);
        firstPlan.setSteps(steps1);

        Plan secondPlan = new Plan(4, initialState, targetState);
        Step step5 = new Step(StackNames.SECONDSTACK, StackNames.FIRSTSTACK);
        Step step6 = new Step(StackNames.SECONDSTACK, StackNames.FIRSTSTACK);
        Step step7 = new Step(StackNames.SECONDSTACK, StackNames.FIRSTSTACK);
        Step step8 = new Step(StackNames.SECONDSTACK, StackNames.FIRSTSTACK);
        List<Step> steps2 = new ArrayList<>();
        steps2.add(step5);
        steps2.add(step6);
        steps2.add(step7);
        steps2.add(step8);
        secondPlan.setSteps(steps2);

        List<Plan> parents = new ArrayList();
        parents.add(firstPlan);
        parents.add(secondPlan);

        List<Plan> matedPlans = classToTest.matePlans(parents);

        //assertEquals(matedPlans.size(), 2);

    }

}
