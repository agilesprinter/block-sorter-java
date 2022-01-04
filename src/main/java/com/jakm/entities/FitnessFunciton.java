package com.jakm.entities;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class FitnessFunciton {

    public int howFit(List<String> targetState, List<String> currentState) {

        if (CollectionUtils.isEmpty(targetState))
            throw new RuntimeException("Target and Current States cannot be null");

        //if the current state is empty then the score is always zero.
        if (CollectionUtils.isEmpty(currentState)) return 0;

        int rawScore = 0;

        rawScore = getPositionSimilarity(targetState, currentState, rawScore);

        int rawPercentageFit = Math.round(percentageFit(targetState.size(), rawScore));

        //now we can start assigning bonus points for other positive features of incorrect plans
        if (rawPercentageFit < 100) {
            // if it's a correct plan, don't bother with bonus points
            if (currentState.size() > 0) rawPercentageFit++;
        }

        if (rawPercentageFit < 100) {
            // if it's a correct plan, don't bother with bonus points
            if (currentState.size() == targetState.size()) rawPercentageFit++;
        }

        return rawPercentageFit;
    }

    public int getPositionSimilarity(List<String> targetState, List<String> currentState, int rawScore) {
        int examinedPosition = 0;
        //Loop through the current state and see if each block matches the block at the target position
        for (String currentBlock : currentState) {
            //if the target state doesn't have a block at our current position, we may as well stop checking
            if (targetState.size() - 1 < examinedPosition) break;

            //if the current state has a block at the target state position, we can give this plan a point
            if (targetState.get(examinedPosition).equals(currentBlock)) rawScore++;

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
