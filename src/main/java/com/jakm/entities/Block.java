package com.jakm.entities;

public class Block {

    private String name;

    public String getName() {
        //Just really testing some exception behavior here
        if (name == null) throw new RuntimeException("Block has not been initilised");

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
