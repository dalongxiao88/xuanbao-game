package org.come.entity;

import java.util.Date;
import java.math.BigDecimal;

public class Region
{
    private BigDecimal regionId;
    private String regionName;
    private Date regionCreTime;
    private Date regionModTime;
    private BigDecimal regionAllId;
    private String regionAllName;
    
    public BigDecimal getRegionId() {
        return this.regionId;
    }
    
    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }
    
    public String getRegionName() {
        return this.regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    public Date getRegionCreTime() {
        return this.regionCreTime;
    }
    
    public void setRegionCreTime(Date regionCreTime) {
        this.regionCreTime = regionCreTime;
    }
    
    public Date getRegionModTime() {
        return this.regionModTime;
    }
    
    public void setRegionModTime(Date regionModTime) {
        this.regionModTime = regionModTime;
    }
    
    public BigDecimal getRegionAllId() {
        return this.regionAllId;
    }
    
    public void setRegionAllId(BigDecimal regionAllId) {
        this.regionAllId = regionAllId;
    }
    
    public String getRegionAllName() {
        return this.regionAllName;
    }
    
    public void setRegionAllName(String regionAllName) {
        this.regionAllName = regionAllName;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Region [regionId=");
        builder.append(this.regionId);
        builder.append(", regionName=");
        builder.append(this.regionName);
        builder.append(", regionCreTime=");
        builder.append(this.regionCreTime);
        builder.append(", regionModTime=");
        builder.append(this.regionModTime);
        builder.append(", regionAllId=");
        builder.append(this.regionAllId);
        builder.append(", regionAllName=");
        builder.append(this.regionAllName);
        builder.append("]");
        return builder.toString();
    }
}
