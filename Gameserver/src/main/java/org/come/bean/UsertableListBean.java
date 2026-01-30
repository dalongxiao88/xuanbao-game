package org.come.bean;

import org.come.entity.UserTable;
import java.util.List;

public class UsertableListBean
{
    private int sumpage;
    private List<UserTable> usertablelist;
    private int nowpage;
    private int number;
    
    public UsertableListBean() {
        this.number = 8;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getSumpage() {
        return this.sumpage;
    }
    
    public void setSumpage(int sumpage) {
        this.sumpage = sumpage;
    }
    
    public List<UserTable> getUsertablelist() {
        return this.usertablelist;
    }
    
    public void setUsertablelist(List<UserTable> usertablelist) {
        this.usertablelist = usertablelist;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
}
