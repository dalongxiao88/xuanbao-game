package org.come.bean;

import java.util.Map;

public class TableSpaceBean
{
    private String path;
    private Map<String, String> tableName;
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public Map<String, String> getTableName() {
        return this.tableName;
    }
    
    public void setTableName(Map<String, String> tableName) {
        this.tableName = tableName;
    }
}
