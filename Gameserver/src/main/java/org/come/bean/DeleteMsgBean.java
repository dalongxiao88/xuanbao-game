package org.come.bean;

import java.math.BigDecimal;
import java.util.List;

public class DeleteMsgBean
{
    private List<BigDecimal> ids;
    
    public List<BigDecimal> getIds() {
        return this.ids;
    }
    
    public void setIds(List<BigDecimal> ids) {
        this.ids = ids;
    }
}
