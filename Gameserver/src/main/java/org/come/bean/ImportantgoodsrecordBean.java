package org.come.bean;

import java.util.ArrayList;
import java.util.List;

public class ImportantgoodsrecordBean
{
    private String goodsid;
    private String name;
    private List<String> recorddate;
    private List<String> time;
    private int page;
    
    public ImportantgoodsrecordBean() {
    }
    
    public ImportantgoodsrecordBean(String goodsid, String name, String recorddate, String time, int page) {
        this.goodsid = goodsid;
        this.name = name;
        this.getRecorddate().add(recorddate);
        this.getTime().add(time);
    }
    
    public String getGoodsid() {
        return this.goodsid;
    }
    
    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<String> getRecorddate() {
        if (this.recorddate == null) {
            this.recorddate = new ArrayList<>();
        }
        return this.recorddate;
    }
    
    public void setRecorddate(List<String> recorddate) {
        this.recorddate = recorddate;
    }
    
    public int getPage() {
        return this.page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public List<String> getTime() {
        if (this.time == null) {
            this.time = new ArrayList<>();
        }
        return this.time;
    }
    
    public void setTime(List<String> time) {
        this.time = time;
    }
}
