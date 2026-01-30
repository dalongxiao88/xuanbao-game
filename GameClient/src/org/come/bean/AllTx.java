package org.come.bean;

import java.util.Map;

public class AllTx
{
    private Map<Integer, RoleTxBean> txmap;
    
    public Map<Integer, RoleTxBean> getTxmap() {
        return this.txmap;
    }
    
    public void setTxmap(Map<Integer, RoleTxBean> txmap) {
        this.txmap = txmap;
    }
}
