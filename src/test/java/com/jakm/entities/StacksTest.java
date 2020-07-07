package com.jakm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StacksTest {

    private StacksIF classToTest;

    @BeforeEach
    void setUp() {

        Set<String> stackList = new HashSet<>();

        stackList.add("originStack");
        stackList.add("firstStack");
        stackList.add("secondStack");

        classToTest = new Stacks(stackList);
    }


    @Test
    void constructoir_ensureStacksAndLabelsAreSetUp() {

        Map<String, List> stackStore = classToTest.getStackStore();

        assertTrue(stackStore.keySet().size() == 3);
        assertTrue(stackStore.containsKey("originStack"));
        assertTrue(stackStore.containsKey("firstStack"));
        assertTrue(stackStore.containsKey("secondStack"));
    }

    @Test
    void moveBlock_easyTest() {

        Map<String, List> sackStore = classToTest.getStackStore();
        List<String> originStack = sackStore.get("originStack");
        List<String> firstStack = sackStore.get("firstStack");
        //we are getting this stack just to check that it remains empty
        List<String> secondStack = sackStore.get("secondStack");

        originStack.add("A");

        StacksIF.moveBlock(new Step("originStack", "firstStack"), sackStore);

        //The block should have moved from the origin to the firstStack
        assertEquals(firstStack.get(0), "A");
        assertTrue(originStack.isEmpty());
        assertTrue(originStack.size() == 0);
        assertTrue(secondStack.isEmpty());
        assertTrue(secondStack.size() == 0);

    }
}
