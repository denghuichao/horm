package com.github.dhc.framework.hom.base;

/**
 * Created by hcdeng on 17-8-25.
 */
public interface HSchema {

    void createTable(String tableName, String...columnFamilies);

    void deleteTable(String tableName);

}