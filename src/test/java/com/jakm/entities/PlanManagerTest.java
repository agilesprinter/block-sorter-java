package com.jakm.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanManagerTest {

    private PlanManager classToTest;

    @BeforeEach
    void setUp() {

        classToTest = new PlanManager();
    }


    @Test
    void createPlan_creates100Plans() {

       // classToTest.createPlans(100, 10, new String[]{StacksIF.ORIGIN_STACK, StacksIF.FIRST_STACK, StacksIF.SECOND_STACK});

        //assertEquals(100, classToTest.getPlans().size());

    }

}
