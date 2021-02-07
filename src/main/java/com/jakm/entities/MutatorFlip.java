package com.jakm.entities;

import com.jakm.interfaces.StackNames;

import java.util.List;
import java.util.Random;

public class MutatorFlip implements MutatorIF {

    public void mutate(List<Step> steps, int numberOfMutations) {

        if (steps == null || steps.isEmpty()) throw new RuntimeException("I cannot mutate a list of empty Steps");

        Random rand = new Random();

        while (numberOfMutations > 0) {

            Step stepToFlip = steps.get(rand.nextInt(steps.size()));

            StackNames from = stepToFlip.getFrom();
            StackNames to = stepToFlip.getTo();

            //flip the order for the relevant step
            stepToFlip.setFrom(to);
            stepToFlip.setTo(from);

            numberOfMutations--;
        }

    }
}
