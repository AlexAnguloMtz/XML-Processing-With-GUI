package com.aram.sales.app;

import com.aram.sales.model.SaleRecord;
import com.aram.sales.model.SalesReport;
import com.aram.sales.service.SaleRecordService;

import java.util.List;

/**
 *  Application to act as a Facade (Design Patterns, Gamma et al.)
 *  to handle operations with Sale Records.
 *
 * @author Alex Angulo
 */

public class SalesReportApplication {
    
    private final SaleRecordService saleRecordService;
    
    public SalesReportApplication(SaleRecordService saleRecordService) {
        this.saleRecordService = saleRecordService;
    }

    public SalesReport computeSalesReport() {
        return new SalesReport(allSaleRecords());
    }

    private List<SaleRecord> allSaleRecords() {
        return saleRecordService.findAll();
    }

}