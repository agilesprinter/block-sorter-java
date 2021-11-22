package com.jakm.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@Setter
public class Generation {

    List<Plan> plans;
    List<String> targetState;
    List<String> initialState;
    int howManyPlans;
    int maxLengthOfEachPlan;

    public Generation(List<Plan> plans, List<String> targetState, List<String> initialState, int howManyPlans, int maxLengthOfEachPlan) {

        if (targetState == null || initialState == null || howManyPlans == 0 || maxLengthOfEachPlan == 0) {
            throw new RuntimeException("Your problem configuration is incorrect. Program cannot continue");
        }

        this.plans = plans;
        this.targetState = targetState;
        this.initialState = initialState;
        this.howManyPlans = howManyPlans;
        this.maxLengthOfEachPlan = maxLengthOfEachPlan;

        //this is the first generation, so we need to create an object for the Plans
        if (this.plans == null) this.plans = new ArrayList<Plan>();

    }

    public void createPlans() {

        //checking that the plans really are empty. If so, create them- this is the first generation
        if (plans.isEmpty()) {
            IntStream.range(0, howManyPlans).forEach(i -> plans.add(
                    new Plan(maxLengthOfEachPlan, initialState, targetState)));
        }
    }

    public void runGeneration(int percentageOfBest, int percentageOfWorst, int numberOfMutations, int percentageToMutate) {

        List<Plan> selectedPlans = new ArrayList<Plan>();

        //sort the plans first.
        this.plans.sort(Comparator.comparing(Plan::getPlanScore));

        List<Plan> bestPlans = getBestPlans(percentageOfBest);

        //select specified percentage of worst plans
        List<Plan> worstPlans = getWorstPlans(percentageOfWorst);

        List<Plan> parents = new ArrayList<>();
        parents.addAll(bestPlans);
        parents.addAll(worstPlans);

        //mate the selected plans
        List<Plan> nextGeneration = matePlans(parents);

        //perform mutations on a percentage of the children

    }

    List<Plan> matePlans(List<Plan> parents) {
        List<Plan> matedPlans = new ArrayList<>();

        return matedPlans;
    }


    List<Plan> getBestPlans(int percentageOfBest) {

        if (this.plans == null)
            throw new UnsupportedOperationException("There are no plans set on this object, thus I cannot return the best");

        if (this.plans.size() == 0)
            throw new UnsupportedOperationException("There are no plans set on this object, thus I cannot return the best");

        if (percentageOfBest == 0) return new ArrayList<Plan>();

        List<Plan> bestPlans = new ArrayList<Plan>();
        //select specified percentage of best plans
        float numberOfPlansToPull = (float) percentageOfBest / 100 * this.plans.size();

        //We are assuming at this point that the plans are already sorted so the best plans are first in the list
        for (int i = 0; i < numberOfPlansToPull; i++) {
            bestPlans.add(this.plans.get(i));
        }

        return bestPlans;
    }

    List<Plan> getWorstPlans(int percentageOfWorst) {

        if (this.plans == null)
            throw new UnsupportedOperationException("There are no plans set on this object, thus I cannot return the best");

        if (this.plans.size() == 0)
            throw new UnsupportedOperationException("There are no plans set on this object, thus I cannot return the best");

        if (percentageOfWorst == 0) return new ArrayList<Plan>();

        List<Plan> worstPlans = new ArrayList<Plan>();
        //select specified percentage of best plans
        float numberOfPlansToPull = (float) percentageOfWorst / 100 * this.plans.size();

        //We are assuming at this point that the plans are already sorted so the best plans are first in the list
        for (int i = this.plans.size() - 1; i >= (this.plans.size() - numberOfPlansToPull); i--) {
            worstPlans.add(this.plans.get(i));
        }

        return worstPlans;
    }

}
