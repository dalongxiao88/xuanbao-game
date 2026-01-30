package org.come.bean;

import java.math.BigDecimal;

public class ChatBean
{
    private BigDecimal gangid;
    private BigDecimal troop_id;
    private Long mapid;
    private BigDecimal SendRoleId;
    private String friendName;
    private String rolename;
    private String race_name;
    private Integer grade;
    private String message;
    private long time;
    
    public BigDecimal getGangid() {
        return this.gangid;
    }
    
    public void setGangid(BigDecimal gangid) {
        this.gangid = gangid;
    }
    
    public BigDecimal getTroop_id() {
        return this.troop_id;
    }
    
    public void setTroop_id(BigDecimal troop_id) {
        this.troop_id = troop_id;
    }
    
    public Long getMapid() {
        return this.mapid;
    }
    
    public void setMapid(Long mapid) {
        this.mapid = mapid;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getFriendName() {
        return this.friendName;
    }
    
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    
    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    
    public String getRace_name() {
        return this.race_name;
    }
    
    public void setRace_name(String race_name) {
        this.race_name = race_name;
    }
    
    public Integer getGrade() {
        return this.grade;
    }
    
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    
    public BigDecimal getSendRoleId() {
        return this.SendRoleId;
    }
    
    public void setSendRoleId(BigDecimal sendRoleId) {
        this.SendRoleId = sendRoleId;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
}
