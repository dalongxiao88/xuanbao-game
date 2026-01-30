package com.gl.model;
//前端请求参数封装
public class Param
{
    private String roleName;
    private Integer roleId;
    private int pageNum;
    private int pageSize;
    private int status;
    private String userName;
    private Integer type;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    
    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public int getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getValue1() {
        return this.value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }
    
    public String getValue2() {
        return this.value2;
    }
    
    public void setValue2(String value2) {
        this.value2 = value2;
    }
    
    public String getValue3() {
        return this.value3;
    }
    
    public void setValue3(String value3) {
        this.value3 = value3;
    }
    
    public String getValue4() {
        return this.value4;
    }
    
    public void setValue4(String value4) {
        this.value4 = value4;
    }
    
    public String getValue5() {
        return this.value5;
    }
    
    public void setValue5(String value5) {
        this.value5 = value5;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public Integer getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
