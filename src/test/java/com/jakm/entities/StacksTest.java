package com.jakm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StacksTest {

    StacksIF classToTest;

    @BeforeEach
    void setUp() {
        classToTest = new Stacks();
    }


    @Test
    void setUpStacks_ensureStacksAndLabelsAreSetUp() {
        List<String> stackList = new ArrayList<>();

        stackList.add("origin");
        stackList.add("firstStack");
        stackList.add("secondStack");

        classToTest.setUpStacks(stackList);

        Map<String, List> stackStore = classToTest.getStackStore();

        assertTrue(stackStore.keySet().size() == 3);
        assertTrue(stackStore.keySet().contains("origin"));
        assertTrue(stackStore.keySet().contains("firstStack"));
        assertTrue(stackStore.keySet().contains("secondStack"));
    }

    @Test
    void moveBlock_easyTest() {

        List<Block> originStack = new ArrayList<>();
        List<Block> firstStack = new ArrayList<>();
        List<Block> secondStack = new ArrayList<>();

        Block blockA = new Block();
        blockA.setName("A");
        originStack.add(blockA);

        classToTest.moveBlock(originStack, firstStack);

        //The block should have moved from the origin to the firstStack
        assertEquals(firstStack.get(0), blockA);
        assertTrue(originStack.isEmpty());
        assertTrue(secondStack.isEmpty());

    }
}
