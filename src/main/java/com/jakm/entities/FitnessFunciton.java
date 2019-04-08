package com.jakm.entities;

import java.util.List;

public class FitnessFunciton {

    public int howFit(List<Block> targetState, List<Block> currentState) {
        if (targetState == null || currentState == null)
            throw new RuntimeException("Target and Current States cannot be null");
        if (targetState.size() == 0 || currentState.size() == 0)
            throw new RuntimeException("Target and Current States cannot be empty");

        int rawScore = 0;

        rawScore = getPositionSimilarity(targetState, currentState, rawScore);

        return rawScore;
    }

    public int getPositionSimilarity(List<Block> targetState, List<Block> currentState, int rawScore) {
        int examinedPosition = 0;
        for (Block currentBlock : currentState) {
            //if the target state doesn't have a block at our current position, we may as well stop checking
            if (targetState.size() - 1 < examinedPosition) break;

            //if the current state has a block at the target state position, we can give this plan a point
            if (targetState.get(examinedPosition).getName().equals(currentBlock.getName())) rawScore++;

            examinedPosition++;
        }
        return rawScore;
    }

    public float percentageFit(final int targetSize, final int rawScore) {
        if (targetSize == 0) return 0;
        if (rawScore == 0) return 0;

        float ratio;
        ratio = (float) rawScore / (float) targetSize * 100;

        return ratio;
    }

}
