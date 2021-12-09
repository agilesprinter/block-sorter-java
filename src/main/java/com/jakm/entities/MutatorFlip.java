package com.jakm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutatorFlip implements MutatorIF {

    public void mutate(List<Step> steps, int numberOfMutations) {

        if (steps == null || steps.isEmpty()) throw new RuntimeException("I cannot mutate a list of empty Steps");

        Random rand = new Random();
        List<Integer> flipPositions = new ArrayList();
        int numberOfFlips = numberOfMutations;

        while (numberOfFlips > 0) {

            Integer positionToFlip = rand.nextInt(steps.size());

            if (flipPositions.contains(positionToFlip)) continue;

            flipPositions.add(positionToFlip);

            numberOfFlips--;
        }

        for (Integer flipIndex : flipPositions) {

            Step stepToFlip = steps.get(flipIndex);

            //flip the order for the relevant step
            stepToFlip.mutate();

        }

    }
}
