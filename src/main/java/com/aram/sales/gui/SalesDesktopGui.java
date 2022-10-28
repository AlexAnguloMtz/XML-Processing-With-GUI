package com.aram.sales.gui;

import com.aram.sales.app.SalesReportApplication;
import com.aram.sales.model.Department;
import com.aram.sales.model.Sales;
import com.aram.sales.model.SalesReport;
import com.aram.sales.model.State;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 *  Java Swing implementation of a GUI for the SalesReportApplication
 *
 *  @author Alex Angulo
 */

public class SalesDesktopGui extends JFrame {

    private static final int COLUMNS = 2;

    private final SalesReportApplication salesReportApplication;
    private final JPanel panel;

    public SalesDesktopGui(SalesReportApplication salesReportApplication) {
        this.salesReportApplication = salesReportApplication;
        this.panel = new JPanel();
        init();
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.add(tables());
        panel.add(total());
        add(panel);
        setTitle("Sales Report");
        setSize(1000,500);
        setResizable(false);
    }

    private Component tables() {
        JTable salesByStateTable = new JTable(formattedSalesByState(), columnsSalesBy("State"));
        JScrollPane firstPane = new JScrollPane(salesByStateTable);
        JTable salesByStateTable2 = new JTable(formattedSalesByDepartment(), columnsSalesBy("Department"));
        JScrollPane secondPane = new JScrollPane(salesByStateTable2);
        JPanel panel = new JPanel();
        panel.add(firstPane);
        panel.add(secondPane);
        return panel;
    }

    private String[][] formattedSalesByDepartment() {
        String[][] formattedSalesByDepartment = new String[allDepartmentsCount()][COLUMNS];
        for (int i = 0; i < allDepartments().size(); i++) {
            Department department = allDepartments().get(i);
            formattedSalesByDepartment[i] = new String[] { department.getName(), formatSales(salesFor(department)) };
        }
        return formattedSalesByDepartment;
    }

    private int allDepartmentsCount() {
        return getSalesReport().allDepartments().size();
    }

    private String[] columnsSalesBy(String criteria) {
        return new String[] {criteria, "Sales"};
    }

    private String[][] formattedSalesByState() {
        String[][] formattedSalesByState = new String[allStatesCount()][COLUMNS];
        for (int i = 0; i < allStatesCount(); i++) {
            State state = allStates().get(i);
            formattedSalesByState[i] = new String[] { state.getName(), formatSales(salesFor(state)) };
        }
        return formattedSalesByState;
    }

    private int allStatesCount() {
        return allStates().size();
    }

    private String formatSales(String sales) {
        return format("$ %s", sales);
    }

    private String salesFor(State state) {
        Sales sales = getSalesReport().salesFor(state);
        return valueOf(sales.getTotal());
    }

    private String salesFor(Department department) {
        Sales sales = getSalesReport().salesFor(department);
        return valueOf(sales.getTotal());
    }

    private List<State> allStates() {
        return getSalesReport().allStates().stream()
                .sorted(comparing(State::getName))
                .collect(toList());
    }

    private List<Department> allDepartments() {
        return getSalesReport().allDepartments().stream()
                .sorted(comparing(Department::getName))
                .collect(toList());
    }

    private SalesReport getSalesReport() {
        return salesReportApplication.computeSalesReport();
    }


    private Component total() {
        return new JLabel(formatTotalSales(totalSales()));
    }

    private String formatTotalSales(String totalSales) {
        return format("Total sales = $%s", totalSales);
    }

    private String totalSales() {
        return valueOf(getSalesReport().totalSales());
    }


}