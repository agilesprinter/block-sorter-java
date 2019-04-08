package com.jakm.entities;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanTest {

    private Plan classToTest;
    private int planSize;

    @BeforeEach
    void setUp() {

        classToTest = new Plan(10, new String[]{"originStack", "firstStack", "secondStack"});


    }

    @Test
    void generatePlan_shouldGenerateARandomPlan() {

        classToTest.generatePlan();

        Assert.assertEquals(10, classToTest.getSteps().size());
    }

}
