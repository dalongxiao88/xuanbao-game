package org.come.model;

import java.math.BigDecimal;

public class Linbaoskill
{
    private String skilltype;
    private String skillname;
    private String describe;
    private String skilllimite;
    private BigDecimal baoid;
    private BigDecimal baoskillid;
    
    public String getSkilltype() {
        return this.skilltype;
    }
    
    public void setSkilltype(String skilltype) {
        this.skilltype = skilltype;
    }
    
    public String getSkillname() {
        return this.skillname;
    }
    
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }
    
    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    
    public String getSkilllimite() {
        return this.skilllimite;
    }
    
    public void setSkilllimite(String skilllimite) {
        this.skilllimite = skilllimite;
    }
    
    public BigDecimal getBaoid() {
        return this.baoid;
    }
    
    public void setBaoid(BigDecimal baoid) {
        this.baoid = baoid;
    }
    
    public BigDecimal getBaoskillid() {
        return this.baoskillid;
    }
    
    public void setBaoskillid(BigDecimal baoskillid) {
        this.baoskillid = baoskillid;
    }
}
