package com.jakm.entities;

import com.jakm.interfaces.StackNames;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Step {

    StackNames from;
    StackNames to;

    public Step(StackNames from, StackNames to) {

        if (from == null || to == null)
            throw new UnsupportedOperationException("You must provide from and to directions");

        this.from = from;
        this.to = to;
    }

    public String toString() {
        return from + " -> " + to;
    }

}
