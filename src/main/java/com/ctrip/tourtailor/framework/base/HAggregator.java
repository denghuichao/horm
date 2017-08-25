package com.ctrip.tourtailor.framework.base;

import com.ctrip.tourtailor.framework.exception.HOrmException;
import org.apache.hadoop.hbase.filter.Filter;

/**
 * Created by hcdeng on 17-8-25.
 */
public interface HAggregator {

    long count(byte[] startRow, byte[] endRow, Class<?> po)throws HOrmException;

    long count(byte[] startRow, byte[] endRow, Class<?> po, Filter... filters)throws HOrmException;

    double sum(byte[] startRow, byte[] endRow, Class<?> po, String columnName)throws HOrmException;

    double sum(byte[] startRow, byte[] endRow, Class<?> po, String columnName, Filter ... filters)throws HOrmException;
}
