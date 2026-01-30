package org.come.bean;

import org.come.entity.Wechatrecord;
import java.util.List;

public class WechatFriendListBean
{
    private List<Wechatrecord> wechatFriendList;
    private int searchPage;
    private String time;
    private int sumPage;
    private String sendId;
    private String getId;
    private int pageNumber;
    
    public List<Wechatrecord> getWechatFriendList() {
        return this.wechatFriendList;
    }
    
    public void setWechatFriendList(List<Wechatrecord> wechatFriendList) {
        this.wechatFriendList = wechatFriendList;
    }
    
    public int getSearchPage() {
        return this.searchPage;
    }
    
    public void setSearchPage(int searchPage) {
        this.searchPage = searchPage;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public int getSumPage() {
        return this.sumPage;
    }
    
    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }
    
    public String getSendId() {
        return this.sendId;
    }
    
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }
    
    public String getGetId() {
        return this.getId;
    }
    
    public void setGetId(String getId) {
        this.getId = getId;
    }
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
