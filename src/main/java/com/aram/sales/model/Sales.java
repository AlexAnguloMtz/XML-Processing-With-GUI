package com.aram.sales.model;

import java.util.Objects;

/**
 * Immutable class to represent an amount of total Sales
 *
 * @author Alex Angulo
 */
public class Sales {
    private final double total;

    public Sales(double total) {
        this.total = total;
    }

    public Sales plus(Sales sales) {
        return new Sales(getTotal() + sales.getTotal());
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Double.compare(sales.total, total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(total);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "total=" + total +
                '}';
    }
}