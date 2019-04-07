

package com.jakm.workflow;

import com.jakm.entities.Block;
import com.jakm.entities.Stacks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneticAlgorithmTest {

    private GeneticAlgorithmIF classToTest;

    @BeforeEach
    void setUp() {
        classToTest = new GeneticAlgorithm(new Stacks());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSimpleSortWithStacksNotSetUpThrowsException() {

        Block aBlock = new Block();
        aBlock.setName("A");
        Block bBlock = new Block();
        aBlock.setName("B");
        Block cBlock = new Block();
        aBlock.setName("C");

        List<Block> target = new ArrayList<>();

        target.add(aBlock);
        target.add(bBlock);
        target.add(cBlock);

        List<Block> current = new ArrayList<>();

        classToTest.setUpProblem(current, target);
        //this is the wrong place for this test but basically we haven't set up the stacks properly
        //the current state must have some blocks in it or there is nothing to do
        assertThrows(RuntimeException.class, () -> classToTest.runAlgorithm());

    }


    @Test
    void testSimpleSortWithStacksSetUp() {

        Block aBlock = new Block();
        aBlock.setName("A");
        Block bBlock = new Block();
        aBlock.setName("B");
        Block cBlock = new Block();
        aBlock.setName("C");

        List<Block> target = new ArrayList<>();

        target.add(aBlock);
        target.add(bBlock);
        target.add(cBlock);

        List<Block> current = new ArrayList<>();
        current.add(cBlock);
        current.add(bBlock);
        current.add(aBlock);

        classToTest.setUpProblem(current, target);

        classToTest.runAlgorithm();

        //TODO: no assertions here, this is more of a debug test- need to fix this
    }


}
