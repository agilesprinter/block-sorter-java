package com.jakm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {


    public static final String CURRENT_STATE = "currentState";
    public static final String TARGET_STATE = "targetState";

    public Map getListsFromArray(String[] args) {

        List<String> currentState = new ArrayList<>();
        List<String> targetState = new ArrayList<>();

        int counter = 0;

        for (String block : args) {
            counter++;
            if (counter <= args.length / 2) {
                currentState.add(block);
            } else {
                targetState.add(block);
            }
        }

        Map<String, List> lists = new HashMap<>();

        lists.put(CURRENT_STATE, currentState);
        lists.put(TARGET_STATE, targetState);

        return lists;


    }

    public void validateEntryArray(String[] args) {
        if (args == null || args.length == 0) {
            throw new RuntimeException("Please pass a single argument showing initial and target state");
        }

        if (args.length % 2 != 0) {
            throw new RuntimeException("Please pass a single argument showing initial and target state");
        }

    }
}
