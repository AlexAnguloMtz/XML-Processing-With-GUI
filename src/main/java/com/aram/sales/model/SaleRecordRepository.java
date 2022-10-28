package com.aram.sales.model;

import java.util.List;

/**
 *  Interface to load SaleRecord objects
 *  from the preferred persistence provider.
 *
 * @author Alex Angulo
 */
public interface SaleRecordRepository {

    List<SaleRecord> findAll();

}
