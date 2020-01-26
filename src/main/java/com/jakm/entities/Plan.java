package com.jakm.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;


@Setter
@Getter
public class Plan {

    int planSize;
    int planScore;
    String[] stackNames;
    LinkedHashMap<Integer, String> steps;

    public Plan(int planSize, String[] stackNames) {
        this.planSize = planSize;
        this.stackNames = stackNames;

        this.steps = generatePlan();
    }

    LinkedHashMap<Integer, String> generatePlan() {

        LinkedHashMap<Integer, String> plan = new LinkedHashMap();

/*
        for (int i = 0; i < planSize; i++) {
            int from = (int)Math.round(Math.random() * (stackNames.length) -1);
            int to = (int)Math.round(Math.random() * (stackNames.length -1));

            String fromStack = stackNames[from];
            String toStack = stackNames[to];

            plan.put(new Integer(i), fromStack +"|"+ toStack);
        }
*/
        return plan;
    }


}
