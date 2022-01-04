package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StacksTest {

    private Stacks classToTest;

    @BeforeEach
    void setUp() {

        List<String> initialState = Arrays.asList("A", "B", "C");

        classToTest = new Stacks(initialState);
    }

    @Test
    void doesApplyStepIgnoreStepsThatCannotWork() {

        assertEquals(3, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        Step step1 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        classToTest.applyStep(step1);

        assertEquals(3, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

    }

    @Test
    void doesApplyStepMoveBlocks() {

        assertEquals(3, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        Step step1 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        classToTest.applyStep(step1);

        assertEquals(2, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(1, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        Step step2 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        classToTest.applyStep(step2);

        assertEquals(2, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(1, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

    }

    @Test
    void doesApplyStepMoveBlocksFromTopOnly() {

        //The top of a stack is the end of the list. The bottom is the element at index 0

        assertEquals(3, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        Step step1 = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);
        classToTest.applyStep(step1);

        assertEquals(2, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(1, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        assertEquals("B", classToTest.getStacks().get(StackNames.ORIGINSTACK)
                .get(classToTest.getStacks().get(StackNames.ORIGINSTACK).size() - 1));

        assertEquals("C", classToTest.getStacks().get(StackNames.FIRSTSTACK)
                .get(classToTest.getStacks().get(StackNames.FIRSTSTACK).size() - 1));

        Step step2 = new Step(StackNames.FIRSTSTACK, StackNames.SECONDSTACK);
        classToTest.applyStep(step2);

        assertEquals(2, classToTest.getStacks().get(StackNames.ORIGINSTACK).size());
        assertEquals(0, classToTest.getStacks().get(StackNames.FIRSTSTACK).size());
        assertEquals(1, classToTest.getStacks().get(StackNames.SECONDSTACK).size());

        assertEquals("B", classToTest.getStacks().get(StackNames.ORIGINSTACK)
                .get(classToTest.getTopBlockIndex(StackNames.ORIGINSTACK)));

        assertEquals("C", classToTest.getStacks().get(StackNames.SECONDSTACK)
                .get(classToTest.getTopBlockIndex(StackNames.SECONDSTACK)));

    }


    @Test
    void doesExtractionWorkCorrectly() {

        assertTrue(classToTest.getStacks().keySet() != null);
        List<String> originStack = classToTest.getStacks().get(StackNames.ORIGINSTACK);
        assertTrue(originStack != null);
        assertTrue(originStack.size() != 0);
        assertFalse(originStack.isEmpty());


        List<String> firstStack = classToTest.getStacks().get(StackNames.FIRSTSTACK);
        assertTrue(firstStack != null);
        assertTrue(firstStack.size() == 0);
        assertTrue(firstStack.isEmpty());

        List<String> secondStack = classToTest.getStacks().get(StackNames.SECONDSTACK);
        assertTrue(secondStack != null);
        assertTrue(secondStack.size() == 0);
        assertTrue(secondStack.isEmpty());

    }


    @Test
    void constructoir_ensureStacksAndLabelsAreSetUp() {

        Map<StackNames, List<String>> stacks = classToTest.getStacks();

        assertTrue(stacks.keySet().size() == 3);
        assertTrue(stacks.containsKey(StackNames.ORIGINSTACK));
        assertTrue(stacks.containsKey(StackNames.FIRSTSTACK));
        assertTrue(stacks.containsKey(StackNames.SECONDSTACK));
    }

    @Test
    void moveBlock_easyTest() {

        //Testing our own object here- away from the one created for us
        List<String> initialState = Arrays.asList("A");
        StacksIF stacks = new Stacks(initialState);

        Map<StackNames, List<String>> stackMap = stacks.getStacks();
        List<String> originStack = stackMap.get(StackNames.ORIGINSTACK);
        List<String> firstStack = stackMap.get(StackNames.FIRSTSTACK);
        //we are getting this stack just to check that it remains empty
        List<String> secondStack = stackMap.get(StackNames.SECONDSTACK);

        StacksIF.moveBlock(new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK), stackMap);

        //The block should have moved from the origin to the firstStack
        assertEquals(firstStack.get(0), "A");
        assertTrue(originStack.isEmpty());
        assertEquals(0, originStack.size());
        assertTrue(secondStack.isEmpty());
        assertEquals(0, secondStack.size());

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


}
