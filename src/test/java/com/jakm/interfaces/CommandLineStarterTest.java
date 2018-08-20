package com.jakm.interfaces;

import com.jakm.entities.Block;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineStarterTest {

    private CommandLineStarter classToTest;

    @BeforeEach
    void setUp() {

        classToTest = new CommandLineStarter();
    }

    @Test
    void testInputsAreRejected() {

        assertThrows(RuntimeException.class, () -> classToTest.begin(null, null));
    }

    @Test
    void testTidyBlockSetCreator() {

        List<String> testList = new ArrayList<>();
        testList.add("A");

        Block targetBlock = new Block();
        targetBlock.setName("A");
        List<Block> targetList = new ArrayList();
        targetList.add(targetBlock);

        List<Block> blocks = classToTest.blockSetCreator(testList);


        assertThat(blocks.get(0).getName(), is(equalTo(targetList.get(0).getName())));

    }

    @Test
    void testEmptyOrNullGivesNull() {
        assertNull(classToTest.blockSetCreator(null));
    }
}
