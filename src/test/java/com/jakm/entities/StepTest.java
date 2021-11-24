package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StepTest {

    @Test
    void simpleMutate() {
        Step classToTest = new Step(StackNames.ORIGINSTACK, StackNames.FIRSTSTACK);

        assertEquals(StackNames.ORIGINSTACK, classToTest.getFrom());
        assertEquals(StackNames.FIRSTSTACK, classToTest.getTo());

        classToTest.mutate();

        assertEquals(StackNames.FIRSTSTACK, classToTest.getFrom());
        assertEquals(StackNames.ORIGINSTACK, classToTest.getTo());
    }
}
