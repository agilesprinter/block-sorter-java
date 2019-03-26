

package com.jakm.workflow;

import com.jakm.entities.Block;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneticAlgorithmTest {

    GeneticAlgorithmIF classToTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSimpleSort() {

        Block aBlock = new Block();
        aBlock.setName("A");
        Block bBlock = new Block();
        aBlock.setName("B");
        Block cBlock = new Block();
        aBlock.setName("C");

        List<Block> target = new ArrayList<Block>();

        target.add(aBlock);
        target.add(bBlock);
        target.add(cBlock);

        List<Block> current = new ArrayList<Block>();
        current.add(cBlock);
        current.add(bBlock);
        current.add(aBlock);

        classToTest = new GeneticAlgorithm();
        classToTest.setUpProblem(current, target);
        List plan = classToTest.runAlgorithm();

        assertTrue(plan instanceof List);

    }


}
