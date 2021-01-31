

package com.jakm.workflow;

import com.jakm.entities.FitnessFunciton;
import com.jakm.entities.PlanManager;
import com.jakm.entities.Stacks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GeneticAlgorithmTest {

    private GeneticAlgorithmIF classToTest;

    @BeforeEach
    void setUp() {
        List<String> targetState = Arrays.asList("B", "A", "C");
        List<String> initialState = Arrays.asList("A", "B", "C");


        classToTest = new GeneticAlgorithm(new Stacks(
                initialState), new FitnessFunciton(), new PlanManager());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSimpleSortWithStacksNotSetUpThrowsException() {


        List<String> target = new ArrayList<>();

        target.add("A");
        target.add("B");
        target.add("C");

        List<String> current = new ArrayList<>();

        classToTest.setUpProblem(current, target);
        //this is the wrong place for this test but basically we haven't set up the stacks properly
        //the current state must have some blocks in it or there is nothing to do
        //assertThrows(RuntimeException.class, () -> classToTest.runAlgorithm());

        //TODO: No assertions here neither

    }


    @Test
    void testSimpleSortWithStacksSetUp() {

        List<String> target = new ArrayList<>();

        target.add("A");
        target.add("B");
        target.add("C");

        List<String> current = new ArrayList<>();
        current.add("C");
        current.add("B");
        current.add("A");

        classToTest.setUpProblem(current, target);

        classToTest.runAlgorithm();

        //TODO: no assertions here, this is more of a debug test- need to fix this
    }


}
