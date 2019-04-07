package com.jakm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    void testTidyBlockSetCreator() {

        List<String> testList = new ArrayList<>();
        testList.add("A");

        Block targetBlock = new Block();
        targetBlock.setName("A");
        List<Block> targetList = new ArrayList<>();
        targetList.add(targetBlock);

        List<Block> blocks = classToTest.blockSetCreator(testList);


        assertThat(blocks.get(0).getName(), is(equalTo(targetList.get(0).getName())));

    }


    @Test
    void testEmptyOrNullGivesNull() {
        assertNull(classToTest.blockSetCreator(null));
    }

    @Test
    void constructoir_ensureStacksAndLabelsAreSetUp() {

        Map<String, List> stackStore = classToTest.getStackStore();

        assertTrue(stackStore.keySet().size() == 3);
        assertTrue(stackStore.keySet().contains("originStack"));
        assertTrue(stackStore.keySet().contains("firstStack"));
        assertTrue(stackStore.keySet().contains("secondStack"));
    }

    @Test
    void moveBlock_easyTest() {

        Map<String, List> sackStore = classToTest.getStackStore();
        List<Block> originStack = sackStore.get("originStack");
        List<Block> firstStack = sackStore.get("firstStack");
        //we are getting this stack just to check that it remains empty
        List<Block> secondStack = sackStore.get("secondStack");

        Block blockA = new Block();
        blockA.setName("A");
        originStack.add(blockA);

        classToTest.moveBlock("originStack", "firstStack");

        //The block should have moved from the origin to the firstStack
        assertEquals(firstStack.get(0), blockA);
        assertTrue(originStack.isEmpty());
        assertTrue(originStack.size() == 0);
        assertTrue(secondStack.isEmpty());
        assertTrue(secondStack.size() == 0);

    }
}
