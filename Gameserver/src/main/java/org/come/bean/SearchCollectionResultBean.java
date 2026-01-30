package org.come.bean;

import org.come.entity.Collection;
import java.util.List;

public class SearchCollectionResultBean
{
    private List<Collection> collections;
    private Integer total;
    
    public List<Collection> getCollections() {
        return this.collections;
    }
    
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }
    
    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }
}
