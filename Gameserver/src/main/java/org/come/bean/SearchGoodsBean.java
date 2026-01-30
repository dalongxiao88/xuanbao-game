package org.come.bean;

public class SearchGoodsBean
{
    private Integer saletype;
    private String contiontype;
    private String salename;
    private Integer pageNum;
    private int show;
    private int order;
    
    public Integer getSaletype() {
        return this.saletype;
    }
    
    public void setSaletype(Integer saletype) {
        this.saletype = saletype;
    }
    
    public String getContiontype() {
        return this.contiontype;
    }
    
    public void setContiontype(String contiontype) {
        this.contiontype = contiontype;
    }
    
    public String getSalename() {
        return this.salename;
    }
    
    public void setSalename(String salename) {
        this.salename = salename;
    }
    
    public Integer getPageNum() {
        return this.pageNum;
    }
    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getShow() {
        return this.show;
    }
    
    public void setShow(int show) {
        this.show = show;
    }
    
    public int getOrder() {
        return this.order;
    }
    
    public void setOrder(int order) {
        this.order = order;
    }
}
