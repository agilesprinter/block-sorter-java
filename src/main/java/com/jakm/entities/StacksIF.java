package com.jakm.entities;

import java.util.List;
import java.util.Map;

public interface StacksIF {


    String ORIGIN_STACK = "originStack";
    String FIRST_STACK = "firstStack";
    String SECOND_STACK = "secondStack";

    String[] defaultStackNameList = new String[]{ORIGIN_STACK, FIRST_STACK, SECOND_STACK};

    Map<String, List> getStackStore();

    void moveBlock(String fromStack, String toStack);

    List<Block> blockSetCreator(List<String> input);
}
