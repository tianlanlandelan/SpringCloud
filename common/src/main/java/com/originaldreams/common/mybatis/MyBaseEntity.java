package com.originaldreams.common.mybatis;

/**
 * MyBaseEntity对象，用于MyBaseMapper的增删改查操作
 * @author yangkaile
 * @date 2018-11-29 14:10:24
 */
public class MyBaseEntity {
    private int id;
    private int pageSize ;
    private int startRows ;
    private String allFields;
    private String tableName;

    public MyBaseEntity(String tableName,String allFields){
        this.tableName = tableName;
        this.allFields = allFields;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRows() {
        return startRows;
    }

    public void setStartRows(int startRows) {
        this.startRows = startRows;
    }

    public String getAllFields() {
        return allFields;
    }

    public String getTableName() {
        return tableName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyBaseEntity{" +
                "id=" + id +
                ", pageSize=" + pageSize +
                ", startRows=" + startRows +
                ", allFields='" + allFields + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
