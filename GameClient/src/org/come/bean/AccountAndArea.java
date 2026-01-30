package org.come.bean;

import java.util.List;

public class AccountAndArea
{
    private Account myAcc;
    private List<OpenArea> areaList;
    private String flag;
    
    public Account getMyAcc() {
        return this.myAcc;
    }
    
    public void setMyAcc(Account myAcc) {
        this.myAcc = myAcc;
    }
    
    public List<OpenArea> getAreaList() {
        return this.areaList;
    }
    
    public void setAreaList(List<OpenArea> areaList) {
        this.areaList = areaList;
    }
    
    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
