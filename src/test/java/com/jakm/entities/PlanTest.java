package com.jakm.entities;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PlanTest {

    private Plan classToTest;
    private int planSize;

    @BeforeEach
    void setUp() {

        StacksIF testStacks = new Stacks(Set.of("originStack", "firstStack", "secondStack"));

        List<String> originStack = Arrays.asList("A", "B", "C");
        List<String> firstStack = Arrays.asList();
        List<String> secondStack = Arrays.asList();

        testStacks.seedStack("originStack", originStack);
        testStacks.seedStack("firstStack", firstStack);
        testStacks.seedStack("secondStack", secondStack);

        classToTest = new Plan(10, testStacks);


    }

    @Test
    void generatePlan_shouldGenerateARandomPlan() {

        classToTest.generatePlan();

        Assert.assertEquals(10, classToTest.getSteps().size());
    }

    @Test
    void getNonEmptyFromStack_shouldGiveMeAStackToMoveFrom() {

        String fromStackSuggestion = classToTest.getNonEmptyFromStack();

        Assert.assertEquals("originStack", fromStackSuggestion);

    }

    @Test
    void getAnyToBlockOtherThan_shouldGiveMeAStackToMoveTo() {

        String toStackSuggestion = classToTest.getAnyToBlockOtherThan("originStack");

        //this should give randomly firstStack or secondStack
        Assert.assertTrue("firstStack".equals(toStackSuggestion) || "secondStack".equals(toStackSuggestion));

    }

}
