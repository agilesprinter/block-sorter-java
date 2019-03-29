package com.jakm.entities;

import java.util.List;
import java.util.Map;

public interface StacksIF {


    void setUpStacks(List<String> stackList);

    Map<String, List> getStackStore();


    void moveBlock(List<Block> originStack, List<Block> firstStack);
}
