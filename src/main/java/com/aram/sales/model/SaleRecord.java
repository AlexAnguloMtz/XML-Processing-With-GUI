package com.aram.sales.model;

/**
 * Immutable class to represent a Sale Record.
 *
 * Instances of this class can only be created with a Builder.
 *
 * To get a Builder, a client needs to call the static builder()
 * method of this class. (Adapted from Builder Pattern in: Joshua Bloch. 'Effective Java').
 *
 * @author Alex Angulo
 */

public class SaleRecord {

    private final SaleRecordId id;
    private final Name firstName;
    private final Name lastName;
    private final Sales sales;
    private final State state;
    private final Department department;

    private SaleRecord(SaleRecordId id,
                      Name firstName,
                      Name lastName,
                      Sales sales,
                      State state,
                      Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sales = sales;
        this.state = state;
        this.department = department;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getId() {
        return id.getId();
    }

    public String getFirstName() {
        return firstName.getName();
    }

    public String getLastName() {
        return lastName.getName();
    }

    public Sales getSales() {
        return sales;
    }

    public State getState() {
        return state;
    }

    public Department getDepartment() {
        return department;
    }

    public String getStateName() {
        return state.getName();
    }

    public String getDepartmentName() {
        return department.getName();
    }

    public boolean hasState(State state) {
        return getState().equals(state);
    }

    public boolean hasDepartment(Department department) {
        return getDepartment().equals(department);
    }

    @Override
    public String toString() {
        return "SaleRecord{" +
                "id=" + getId() +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", sales=" + getSales() +
                ", state=" + getStateName() +
                ", department=" + getDepartmentName() +
                '}';
    }

    public static class Builder {

        private int id;
        private String firstName;
        private String lastName;
        private double sales;
        private String state;
        private String department;

        private Builder() { /* Private constructor */ }

        public SaleRecord build() {
            return new SaleRecord(
                    new SaleRecordId(id),
                    new Name(firstName),
                    new Name(lastName),
                    new Sales(sales),
                    new State(state),
                    new Department(department)
            );
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder sales(double sales) {
            this.sales = sales;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

    }

}