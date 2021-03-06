package com.github.dhc.framework.hom.hbase;

import com.github.dhc.framework.hom.annotation.RowKey;
import com.github.dhc.framework.hom.exception.ColumFamilyNotDefineException;
import com.github.dhc.framework.hom.annotation.Column;
import org.apache.directory.api.util.Strings;

import java.lang.reflect.Field;

/**
 * Created by hcdeng on 17-8-25.
 */
public class HColumnDefinition {

    private String colummName;
    private String columnFamily;

    private Field field;
    private Class<?> fieldType;

    private boolean isRowkey;
    private long timestamp;

    public static HColumnDefinition parse(Field field){
        if(field.isAnnotationPresent(RowKey.class) || field.isAnnotationPresent(Column.class)) {
            HColumnDefinition hdc = new HColumnDefinition();
            hdc.setIsRowkey(isRowKey(field));
            String [] familyAndName = getFamilyAndColumnName(field);
            hdc.setColumnFamily(familyAndName[0]);
            hdc.setColummName(familyAndName[1]);
            hdc.setField(field);
            hdc.setFieldType(field.getType());
            return hdc;
        }
        return null;
    }

    private static boolean isRowKey(Field f){
        return f.isAnnotationPresent(RowKey.class);
    }

    private static String[] getFamilyAndColumnName(Field field){
        field.setAccessible(true);
        if(field.isAnnotationPresent(Column.class)) {
            Column hc = field.getAnnotation(Column.class);
            String family = hc.family();
            if(Strings.isEmpty(family))
                throw new ColumFamilyNotDefineException("family must not be empty");

            String name = hc.name();
            return new String[]{family, Strings.isEmpty(name) ? field.getName() : name};
        }else{
            return new String[]{field.getName(), field.getName()};
        }
    }

    public String getColummName() {
        return colummName;
    }

    public void setColummName(String colummName) {
        this.colummName = colummName;
    }

    public String getColumnFamily() {
        return columnFamily;
    }

    public void setColumnFamily(String columnFamily) {
        this.columnFamily = columnFamily;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean getIsRowkey() {
        return isRowkey;
    }

    public void setIsRowkey(boolean isRowkey) {
        this.isRowkey = isRowkey;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }
}
