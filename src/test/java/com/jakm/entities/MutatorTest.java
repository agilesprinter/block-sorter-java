package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class MutatorTest {

    @Test
    void canWeFlipOneStep() {
        Step step1 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step2 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step3 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step4 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);

        List<Step> steps = new ArrayList<>(Arrays.asList(step1, step2, step3, step4));

        MutatorIF mutatorFlip = new MutatorFlip();

        mutatorFlip.mutate(steps, 1);

        assertEquals(4, steps.size());

        int flipped = 0;
        int notFlipped = 0;

        for (Step step : steps) {
            if (step.from.equals(StackNames.ORIGINSTACK)
                    && step.to.equals(StackNames.FIRSTSTACK)) {
                flipped++;
            } else {
                notFlipped++;
            }
        }

        assertEquals(3, notFlipped);
        assertEquals(1, flipped);
    }

    @Test
    void canWeFlipOTwoSteps() {
        Step step1 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step2 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step3 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step4 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);

        List<Step> steps = new ArrayList<>(Arrays.asList(step1, step2, step3, step4));

        MutatorIF mutatorFlip = new MutatorFlip();

        mutatorFlip.mutate(steps, 2);

        assertEquals(4, steps.size());

        int flipped = 0;
        int notFlipped = 0;

        for (Step step : steps) {
            if (step.from.equals(StackNames.ORIGINSTACK)
                    && step.to.equals(StackNames.FIRSTSTACK)) {
                flipped++;
            } else {
                notFlipped++;
            }
        }

        assertEquals(2, notFlipped);
        assertEquals(2, flipped);
    }
}
