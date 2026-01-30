package org.come.bean;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ShaoXiangLimitResultBean
{
    ConcurrentHashMap<String, ShaoXiangLimit> allshaoxianglimit;
    List<String> allshaoxiang;
    
    public ConcurrentHashMap<String, ShaoXiangLimit> getAllshaoxianglimit() {
        return this.allshaoxianglimit;
    }
    
    public void setAllshaoxianglimit(ConcurrentHashMap<String, ShaoXiangLimit> allshaoxianglimit) {
        this.allshaoxianglimit = allshaoxianglimit;
    }
    
    public List<String> getAllshaoxiang() {
        return this.allshaoxiang;
    }
    
    public void setAllshaoxiang(List<String> allshaoxiang) {
        this.allshaoxiang = allshaoxiang;
    }
}
