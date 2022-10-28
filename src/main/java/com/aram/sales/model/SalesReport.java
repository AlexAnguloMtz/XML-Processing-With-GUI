package com.aram.sales.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

/**
 * Immutable class to represent a Sales Report.
 * The information in a Sales Report includes:
 *  1) Sales by State
 *  2) Sales by Department
 *  3) Total Sales
 *
 * @author Alex Angulo
 */

public class SalesReport {

    private final Map<State, Sales> salesByState;
    private final Map<Department, Sales> salesByDepartment;
    private final Sales totalSales;

    public SalesReport(Collection<? extends SaleRecord> saleRecords) {
        this.salesByState = salesByState(saleRecords);
        this.salesByDepartment = salesByDepartment(saleRecords);
        this.totalSales = totalSales(saleRecords);
    }

    public Set<State> allStates() {
        return salesByState.keySet();
    }

    public Set<Department> allDepartments() {
        return salesByDepartment.keySet();
    }

    public Sales salesFor(State state) {
        return salesByState.get(state);
    }

    public Sales salesFor(Department department) {
        return salesByDepartment.get(department);
    }

    public double totalSales() {
        return totalSales.getTotal();
    }

    private Map<State, Sales> salesByState(Collection<? extends SaleRecord> saleRecords) {
        Set<State> allStates = extractAllFrom(saleRecords, SaleRecord::getState);
        var salesByState = new HashMap<State, Sales>();
        allStates.forEach(state -> putSalesFor(state, salesByState, saleRecords));
        return salesByState;
    }

    private Map<Department, Sales> salesByDepartment(Collection<? extends SaleRecord> saleRecords) {
        Set<Department> allDepartments = extractAllFrom(saleRecords, SaleRecord::getDepartment);
        var salesByDepartment = new HashMap<Department, Sales>();
        allDepartments.forEach(department -> putSalesFor(department, salesByDepartment, saleRecords));
        return salesByDepartment;
    }

    private <T> Set<T> extractAllFrom(Collection<? extends SaleRecord> saleRecords,
                                      Function<SaleRecord, T> extractStrategy) {
        return saleRecords.stream()
                .map(extractStrategy)
                .collect(toSet());
    }

    private void putSalesFor(Department department,
                             Map<Department, Sales> salesByDepartment,
                             Collection<? extends SaleRecord> saleRecords) {

        salesByDepartment.put(department, sales(by(department), saleRecords));

    }

    private void putSalesFor(State state,
                             Map<State, Sales> salesByState,
                             Collection<? extends SaleRecord> saleRecords) {

        salesByState.put(state, sales(by(state), saleRecords));

    }


    private Sales totalSales(Collection<? extends SaleRecord> saleRecords) {
        return saleRecords.stream()
                .map(SaleRecord::getSales)
                .reduce(new Sales(0), Sales::plus);
    }

    private Predicate<SaleRecord> by(State state) {
        return record -> record.hasState(state);
    }

    private Predicate<SaleRecord> by(Department department) {
        return record -> record.hasDepartment(department);
    }

    private Sales sales(Predicate<SaleRecord> selector, Collection<? extends SaleRecord> saleRecords) {
        return saleRecords.stream()
                .filter(selector)
                .map(SaleRecord::getSales)
                .reduce(new Sales(0), Sales::plus);
    }

}