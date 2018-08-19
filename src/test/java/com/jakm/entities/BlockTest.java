package com.jakm.entities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

        assertThat(classToTest.getName(), is(equalTo("A")));

    }

    @Test
    void testNullThrowsException() {

        Block classToTest = new Block();
        assertThrows(Exception.class, () -> classToTest.getName());

    }

}
