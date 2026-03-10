package org.come.entity;

import java.math.BigDecimal;

/**
 * 服务端帮派实体。
 */
public class Gang
{
    private BigDecimal gangid;
    private String gangname;
    private BigDecimal gangnumber;
    private BigDecimal builder;
    private BigDecimal property;
    private BigDecimal ganggrade;
    private String founder;
    private String gangbelong;
    private String introduction;
    private transient String gangTxt;
    
    public BigDecimal getGangid() {
        return this.gangid;
    }

    /** 语义化别名：帮派 ID。 */
    public BigDecimal getGangId() {
        return this.gangid;
    }
    
    public void setGangid(BigDecimal gangid) {
        this.gangid = gangid;
    }

    public void setGangId(BigDecimal gangId) {
        this.gangid = gangId;
    }
    
    public String getGangname() {
        return this.gangname;
    }

    /** 语义化别名：帮派名称。 */
    public String getGangName() {
        return this.gangname;
    }
    
    public void setGangname(String gangname) {
        this.gangname = ((gangname == null) ? null : gangname.trim());
    }

    public void setGangName(String gangName) {
        this.gangname = ((gangName == null) ? null : gangName.trim());
    }
    
    public BigDecimal getGangnumber() {
        return this.gangnumber;
    }
    
    public void setGangnumber(BigDecimal gangnumber) {
        this.gangnumber = gangnumber;
    }
    
    public BigDecimal getBuilder() {
        return this.builder;
    }
    
    public void setBuilder(BigDecimal builder) {
        this.builder = builder;
    }
    
    public BigDecimal getProperty() {
        return this.property;
    }
    
    public void setProperty(BigDecimal property) {
        this.property = property;
    }
    
    public BigDecimal getGanggrade() {
        return this.ganggrade;
    }
    
    public void setGanggrade(BigDecimal ganggrade) {
        this.ganggrade = ganggrade;
    }
    
    public String getFounder() {
        return this.founder;
    }
    
    public void setFounder(String founder) {
        this.founder = ((founder == null) ? null : founder.trim());
    }
    
    public String getGangbelong() {
        return this.gangbelong;
    }
    
    public void setGangbelong(String gangbelong) {
        this.gangbelong = ((gangbelong == null) ? null : gangbelong.trim());
    }
    
    public String getIntroduction() {
        return this.introduction;
    }
    
    public void setIntroduction(String introduction) {
        this.introduction = ((introduction == null) ? null : introduction.trim());
    }
    
    public String getGangTxt() {
        return this.gangTxt;
    }
    
    public void setGangTxt(String gangTxt) {
        this.gangTxt = gangTxt;
    }
}
