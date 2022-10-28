package com.aram.sales.service;

import com.aram.sales.model.SaleRecord;

import java.util.List;

/**
 * Service interface to find Sale Records
 *
 * @author Alex Angulo
 */

public interface SaleRecordService {

    List<SaleRecord> findAll();

}
