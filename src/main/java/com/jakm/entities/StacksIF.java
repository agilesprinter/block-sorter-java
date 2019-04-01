package com.jakm.entities;

import java.util.List;
import java.util.Map;

public interface StacksIF {


    public final String[] defaultStackNameList = new String[]{"originStack", "firstStack", "secondStack"};

    Map<String, List> getStackStore();

    void moveBlock(String fromStack, String toStack);

    public List<Block> blockSetCreator(List<String> input);
}
