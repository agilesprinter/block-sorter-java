package com.jakm.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private Utils classToTest;

    @BeforeEach
    void setUp() {
        classToTest = new Utils();
    }

    @Test
    void validateEntryArray_ShouldThrowExceptionOnNullArray() {

        assertThrows(RuntimeException.class, () -> classToTest.validateEntryArray(null));
    }

    @Test
    void validateEntryArray_ShouldThrowExceptionOnOddSizedArray() {

        assertThrows(RuntimeException.class, () -> classToTest.validateEntryArray(new String[]{"A", "B", "C"}));
    }

    @Test
    void validateEntryArray_ShouldThrowNoExceptionOnEvenSizedArray() {

        classToTest.validateEntryArray(new String[]{"A", "B", "C", "D"});

        //we should reach this point
        assertTrue(true);
    }

    @Test
    void testTidyBlockSetCreator_GivesTwoLists() {
        Map<String, List> testOutput = classToTest.getListsFromArray(new String[]{"A", "B"});

        assertNotNull(testOutput);

        List currentState = testOutput.get(Utils.CURRENT_STATE);
        List targetState = testOutput.get(Utils.TARGET_STATE);

        assertEquals(currentState.size(), targetState.size());

        assertEquals(currentState.get(0), "A");
        assertEquals(targetState.get(0), "B");
    }

    @Test
    void testTidyBlockSetCreator_GivesTwoLongLists() {
        Map<String, List> testOutput = classToTest.getListsFromArray(new String[]{"A", "B", "C", "D", "D", "C", "B", "A"});

        assertNotNull(testOutput);

        List currentState = testOutput.get(Utils.CURRENT_STATE);
        List targetState = testOutput.get(Utils.TARGET_STATE);

        assertEquals(currentState.size(), targetState.size());

        assertEquals(currentState.size(), 4);
        assertEquals(targetState.size(), 4);

        assertTrue(currentState.contains("A"));
        assertTrue(currentState.contains("B"));
        assertTrue(currentState.contains("C"));
        assertTrue(currentState.contains("D"));

        assertTrue(targetState.contains("D"));
        assertTrue(targetState.contains("C"));
        assertTrue(targetState.contains("B"));
        assertTrue(targetState.contains("A"));
    }


    @Test
    void testTidyBlockSetCreator() {

        List<String> testList = new ArrayList<>();
        testList.add("A");

        List<String> targetList = new ArrayList<>();
        targetList.add("A");

        List<String> blocks = classToTest.blockSetCreator(testList);


        assertThat(blocks.get(0), is(equalTo(targetList.get(0))));

    }


    @Test
    void testEmptyOrNullGivesNull() {
        assertNull(classToTest.blockSetCreator(null));
    }
}
