package org.come.extInterBean;

public class ShopBuyRecordReqBean
{
    private String buyType;
    private String recordTime;
    private String userName;
    private String roleName;
    private String nowPage;
    private int start;
    private int end;
    
    public ShopBuyRecordReqBean() {
        this.start = 0;
        this.end = 0;
    }
    
    public String getBuyType() {
        return this.buyType;
    }
    
    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }
    
    public String getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getNowPage() {
        return this.nowPage;
    }
    
    public void setNowPage(String nowPage) {
        this.nowPage = nowPage;
    }
    
    public int getStart() {
        return this.start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return this.end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
}
