package org.come.entity;

import java.util.List;

public class SearchLingbaoList
{
    private int sumpage;
    private List<LingbaoRoleUser> lingbaolist;
    
    public List<LingbaoRoleUser> getLingbaolist() {
        return this.lingbaolist;
    }
    
    public void setLingbaolist(List<LingbaoRoleUser> lingbaolist) {
        this.lingbaolist = lingbaolist;
    }
    
    public int getSumpage() {
        return this.sumpage;
    }
    
    public void setSumpage(int sumpage) {
        this.sumpage = sumpage;
    }
}
