package org.come.bean;

import org.come.entity.PackRecord;
import java.util.List;

public class FreshPackBean
{
    private List goods;
    private PackRecord packRecord;
    
    public List getGoods() {
        return this.goods;
    }
    
    public void setGoods(List goods) {
        this.goods = goods;
    }
    
    public PackRecord getPackRecord() {
        return this.packRecord;
    }
    
    public void setPackRecord(PackRecord packRecord) {
        this.packRecord = packRecord;
    }
}
