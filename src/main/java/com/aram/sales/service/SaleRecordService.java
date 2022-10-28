package com.aram.sales.service;

import com.aram.sales.model.SaleRecord;

import java.util.List;

/**
 * Service class to find Sale Records
 *
 * @author Alex Angulo
 */

public interface SaleRecordService {

    List<SaleRecord> findAll();

}
