package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import org.junit.jupiter.api.Test;

class MutatorTest {

    @Test
    void canWeFlipSomeSteps() {
        Step step1 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step2 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step3 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
        Step step4 = new Step(StackNames.FIRSTSTACK, StackNames.ORIGINSTACK);
    }
}
