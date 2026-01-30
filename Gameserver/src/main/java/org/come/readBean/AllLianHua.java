package org.come.readBean;

import java.util.Iterator;
import org.come.model.LianHua;
import java.util.Map;

public class AllLianHua
{
    private Map<Integer, LianHua> lianhuas;
    
    public AllLianHua(Map<Integer, LianHua> lianhuas) {
        this.lianhuas = lianhuas;
    }
    
    public Map<Integer, LianHua> getLianhuas() {
        return this.lianhuas;
    }
    
    public LianHua get1000(int num) {
        for (LianHua e : this.lianhuas.values()) {
            if (e.getType() == 1000 && e.getNum() == num) {
                return e;
            }
        }
        return null;
    }
    
    public LianHua get2000(int num) {
        for (LianHua e : this.lianhuas.values()) {
            if (e.getType() == 2000 && e.getNum() == num) {
                return e;
            }
        }
        return null;
    }
}
