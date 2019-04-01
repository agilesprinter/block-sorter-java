package com.jakm.entities;

import java.util.List;
import java.util.Map;

public interface StacksIF {

    Map<String, List> getStackStore();

    void moveBlock(String fromStack, String toStack);
}
