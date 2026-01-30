package org.come.entity;

import org.come.server.GameServer;
import org.come.model.Skill;
import java.math.BigDecimal;

public class Lingbao implements Cloneable
{
    private String baoname;
    private String gethard;
    private String baotype;
    private Integer baoactive;
    private String baospeed;
    private String baoreply;
    private String baoshot;
    private String baoap;
    private String resistshot;
    private String assistance;
    private String goodskill;
    private String skin;
    private BigDecimal roleid;
    private String fushis;
    private BigDecimal lingbaolvl;
    private BigDecimal lingbaoexe;
    private long lingbaoqihe;
    private String skills;
    private String operation;
    private String kangxing;
    private BigDecimal baoid;
    private int equipment;
    private String baoquality;
    private String tianfuskill;
    private Integer skillsum;
    private Integer fusum;
    private int goodlock;
    private BigDecimal commodityId;
    
    public String skilljihe(Skill skill) {
        int type = skill.getSkilltype();
        if (type == 0 && !this.baotype.equals("攻击")) {
            return "学习技能失败,无法学习该类型的技能";
        }
        if (type == 1 && !this.baotype.equals("辅助")) {
            return "学习技能失败,无法学习该类型的技能";
        }
        if (type == 2 && !this.baotype.equals("落宝")) {
            return "学习技能失败,无法学习该类型的技能";
        }
        String skillname = skill.getSkillname();
        String lvl = skill.getSkilllevel() + "";
        int min = Integer.parseInt(lvl.substring(0, 1));
        int max = Integer.parseInt(lvl.substring(1, 2));
        if ((this.skills == null || this.skills.equals("")) && (int)this.skillsum > 0) {
            this.skills = skillname + "=" + (GameServer.random.nextInt(max - min + 1) + min);
            return null;
        }
        if (this.skills != null && !this.skills.equals("") && (int)this.skillsum > this.skills.split("\\|").length) {
            String[] v = this.skills.split("\\|");
            int sum = GameServer.random.nextInt(max - min + 1) + min;
            skillname = skillname + "=" + sum;
            for (int i = 0; i < v.length; ++i) {
                if (v[i].equals(skillname)) {
                    return "学习技能失败,技能重复";
                }
            }
            this.skills = this.skills + "|" + skillname;
            return null;
        }
        else {
            return "学习技能失败,技能格子已满";
        }
    }
    
    public String getBaoname() {
        return this.baoname;
    }
    
    public void setBaoname(String baoname) {
        this.baoname = baoname;
    }
    
    public String getGethard() {
        return this.gethard;
    }
    
    public void setGethard(String gethard) {
        this.gethard = gethard;
    }
    
    public String getBaotype() {
        return this.baotype;
    }
    
    public void setBaotype(String baotype) {
        this.baotype = baotype;
    }
    
    public Integer getBaoactive() {
        return this.baoactive;
    }
    
    public void setBaoactive(Integer baoactive) {
        this.baoactive = baoactive;
    }
    
    public String getBaospeed() {
        return this.baospeed;
    }
    
    public void setBaospeed(String baospeed) {
        this.baospeed = baospeed;
    }
    
    public String getBaoreply() {
        return this.baoreply;
    }
    
    public void setBaoreply(String baoreply) {
        this.baoreply = baoreply;
    }
    
    public String getBaoshot() {
        return this.baoshot;
    }
    
    public void setBaoshot(String baoshot) {
        this.baoshot = baoshot;
    }
    
    public String getBaoap() {
        return this.baoap;
    }
    
    public void setBaoap(String baoap) {
        this.baoap = baoap;
    }
    
    public String getResistshot() {
        return this.resistshot;
    }
    
    public void setResistshot(String resistshot) {
        this.resistshot = resistshot;
    }
    
    public String getAssistance() {
        return this.assistance;
    }
    
    public void setAssistance(String assistance) {
        this.assistance = assistance;
    }
    
    public String getGoodskill() {
        return this.goodskill;
    }
    
    public void setGoodskill(String goodskill) {
        this.goodskill = goodskill;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public BigDecimal getBaoid() {
        return this.baoid;
    }
    
    public void setBaoid(BigDecimal baoid) {
        this.baoid = baoid;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getFushis() {
        return this.fushis;
    }
    
    public void setFushis(String fushis) {
        this.fushis = fushis;
    }
    
    public BigDecimal getLingbaolvl() {
        return this.lingbaolvl;
    }
    
    public void setLingbaolvl(BigDecimal lingbaolvl) {
        this.lingbaolvl = lingbaolvl;
    }
    
    public BigDecimal getLingbaoexe() {
        return this.lingbaoexe;
    }
    
    public void setLingbaoexe(BigDecimal lingbaoexe) {
        this.lingbaoexe = lingbaoexe;
    }
    
    public long getLingbaoqihe() {
        return this.lingbaoqihe;
    }
    
    public void setLingbaoqihe(long lingbaoqihe) {
        if (lingbaoqihe > 2000000L) {
            lingbaoqihe = 2000000L;
        }
        this.lingbaoqihe = lingbaoqihe;
    }
    
    public String getSkills() {
        return this.skills;
    }
    
    public void setSkills(String skills) {
        this.skills = skills;
    }
    
    public String getOperation() {
        return this.operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getKangxing() {
        return this.kangxing;
    }
    
    public void setKangxing(String kangxing) {
        this.kangxing = kangxing;
    }
    
    public int getEquipment() {
        return this.equipment;
    }
    
    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }
    
    public String getBaoquality() {
        return this.baoquality;
    }
    
    public void setBaoquality(String baoquality) {
        this.baoquality = baoquality;
    }
    
    public String getTianfuskill() {
        return this.tianfuskill;
    }
    
    public void setTianfuskill(String tianfuskill) {
        this.tianfuskill = tianfuskill;
    }
    
    public Integer getSkillsum() {
        return this.skillsum;
    }
    
    public void setSkillsum(Integer skillsum) {
        this.skillsum = skillsum;
    }
    
    public Integer getFusum() {
        return this.fusum;
    }
    
    public void setFusum(Integer fusum) {
        this.fusum = fusum;
    }
    
    public int getGoodlock() {
        return this.goodlock;
    }
    
    public void setGoodlock(int goodlock) {
        this.goodlock = goodlock;
    }
    
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    public Lingbao clone() {
        try {
            return (Lingbao)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
