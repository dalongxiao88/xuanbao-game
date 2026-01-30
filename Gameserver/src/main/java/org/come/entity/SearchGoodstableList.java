package org.come.entity;

import java.util.List;

public class SearchGoodstableList
{
    private int sumpage;
    private List<GoodsRoleUser> listGoodsTable;
    
    public int getSumpage() {
        return this.sumpage;
    }
    
    public void setSumpage(int sumpage) {
        this.sumpage = sumpage;
    }
    
    public List<GoodsRoleUser> getListGoodsTable() {
        return this.listGoodsTable;
    }
    
    public void setListGoodsTable(List<GoodsRoleUser> listGoodsTable) {
        this.listGoodsTable = listGoodsTable;
    }
}
