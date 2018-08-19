package com.jakm.interfaces;

import com.jakm.entities.Block;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineStarterTest {

    private CommandLineStarter classToTest;

    @Test
    void testInputsAreRejected() {

        classToTest = new CommandLineStarter();

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

        assertThat(classToTest.blockSetCreator(testList), is(equalTo(targetList)));

    }
}
