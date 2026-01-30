

package org.come.model;

import java.util.Map;

import org.come.bean.XuanBao;

public class AllXuanbao {
    private Map<Integer, XuanBao> aMap;

    public AllXuanbao() {
    }

    public Map<Integer, XuanBao> getaMap() {
        return this.aMap;
    }

    public void setaMap(Map<Integer, XuanBao> aMap) {
        this.aMap = aMap;
    }
}
