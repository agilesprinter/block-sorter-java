package com.jakm.util;

import com.jakm.entities.Block;

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

    /**
     * A simple utility method. Give me a list of strings, I'll make it into a list of Blocks
     *
     * @param input
     * @return
     */
    public List<Block> blockSetCreator(List<String> input) {

        if (input == null || input.size() == 0) return null;

        List<Block> blocks = new ArrayList<>();

        for (String anInput : input) {
            Block b = new Block();
            b.setName(anInput);
            blocks.add(b);
        }

        return blocks;

    }
}
