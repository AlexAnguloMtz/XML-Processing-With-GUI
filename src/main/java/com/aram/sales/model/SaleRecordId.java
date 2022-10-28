package com.aram.sales.model;

/**
 *  Immutable class to represent a Sale Record's Id
 *
 * @author Alex Angulo
 */

public class SaleRecordId {

    private final int id;


    public SaleRecordId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}