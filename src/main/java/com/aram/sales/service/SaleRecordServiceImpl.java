package com.aram.sales.service;

import com.aram.sales.model.SaleRecord;
import com.aram.sales.model.SaleRecordRepository;

import java.util.List;

/**
 *  Implementation of a SaleRecordService
 *  that uses a SaleRecordRepository
 *  to achieve its responsibilities.
 *
 *  @author Alex Angulo
 */

public class SaleRecordServiceImpl implements SaleRecordService {

    private final SaleRecordRepository saleRecordRepository;

    public SaleRecordServiceImpl(SaleRecordRepository saleRecordRepository) {
        this.saleRecordRepository = saleRecordRepository;
    }

    @Override
    public List<SaleRecord> findAll() {
        return saleRecordRepository.findAll();
    }

}
