package com.jakm.workflow;

import com.jakm.entities.Block;
import com.jakm.entities.Stacks;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GeneticAlgorithm implements GeneticAlgorithmIF {

    private List blocks;
    private List target;
    @Autowired
    private Stacks stacks;


    public void setUpProblem(List currentState, List target) {
        this.blocks = currentState;
        this.target = target;
    }


    public List<String> runAlgorithm() {


        //for now let's just create a couple of blocks and see them move
        Map<String, List> stackStore = stacks.getStackStore();
        //add a couple of blocks then move them
        List<Block> originBlocks = stackStore.get("originStack");
        Block blockA = new Block();
        blockA.setName("A");
        Block blockB = new Block();
        blockB.setName("B");
        Block blockC = new Block();
        blockC.setName("B");

        originBlocks.add(blockA);
        originBlocks.add(blockB);
        originBlocks.add(blockC);

        stacks.moveBlock("originStack", "firstStack");
        stacks.moveBlock("originStack", "firstStack");
        stacks.moveBlock("originStack", "secondStack");

        System.out.println("Origin Stack is length: " + stacks.getStackStore().get("firstStack").size());
        System.out.println("First Stack is length: " + stacks.getStackStore().get("firstStack").size());
        System.out.println("Second Stack is length: " + stacks.getStackStore().get("firstStack").size());

        return null;

    }


}
