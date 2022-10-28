package com.aram.sales.model;

/**
 *  Immutable class to represent a Name
 *
 * @author Angulo Alex
 */

public class Name {

    private final String name;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
