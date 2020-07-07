package com.jakm.entities;

import lombok.Getter;

@Getter
public class Step {

    String from;
    String to;

    public Step(String from, String to) {

        if (from == null || to == null)
            throw new UnsupportedOperationException("You must provide from and to directions");

        this.from = from;
        this.to = to;
    }

}
