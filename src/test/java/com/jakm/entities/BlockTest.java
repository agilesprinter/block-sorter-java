package com.jakm.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BlockTest {

    Block classToTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAccess() {
        Block classToTest = new Block();

        classToTest.setName("A");
        assertEquals(classToTest.getName(), "A");
    }

}
