package org.come.entity;

import java.math.BigDecimal;

public class Baby
{
    private BigDecimal babyid;
    private BigDecimal roleid;
    private String babyname;
    private Integer qizhi;
    private Integer neili;
    private Integer zhili;
    private Integer naili;
    private Integer mingqi;
    private Integer daode;
    private Integer panni;
    private Integer wanxing;
    private Integer qingmi;
    private Integer xiaoxin;
    private Integer wenbao;
    private Integer pilao;
    private Integer yangyujin;
    private Integer childSex;
    private Integer babyage;
    private String outcome;
    private String Talents;
    private String parts;
    private String state;
    
    /**
     * 获取指定装备槽位的道具 ID。
     */
    public BigDecimal getEquipPart(int type) {
        String[] v = this.getParts().split("\\|");
        if (type < v.length) {
            return new BigDecimal(v[type]);
        }
        return new BigDecimal(-1);
    }
    
    /**
     * 获取全部宝宝装备槽位。
     */
    public BigDecimal[] getAllEquipParts() {
        BigDecimal[] bigs = new BigDecimal[4];
        String[] v = this.getParts().split("\\|");
        for (int i = 0; i < 4; ++i) {
            if (i < v.length) {
                bigs[i] = new BigDecimal(v[i]);
            }
            else {
                bigs[i] = new BigDecimal(-1);
            }
        }
        return bigs;
    }
    
    /**
     * 更换指定装备槽位，并返回被替换下来的原装备 ID。
     */
    public BigDecimal changeEquipPart(BigDecimal id, int type) {
        BigDecimal[] bigs = this.getAllEquipParts();
        BigDecimal yid = bigs[type];
        bigs[type] = id;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bigs.length; ++i) {
            if (i != 0) {
                buffer.append("|");
            }
            buffer.append(bigs[i]);
        }
        this.parts = buffer.toString();
        return yid;
    }
    
    /**
     * 学习或替换指定位置的天资条目。
     *
     * @param type 天资槽位
     * @param talent 天资标识或天资升级后的值
     * @return true 表示写入成功；false 表示该天资已存在，无需重复写入
     */
    public boolean changeTalent(int type, String talent) {
        String[] v = this.getTalents().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].split("=")[0].equals(talent)) {
                return false;
            }
        }
        v[type] = talent + "=1";
        StringBuffer buffer = new StringBuffer();
        buffer.append(v[0]);
        buffer.append("|");
        buffer.append(v[1]);
        buffer.append("|");
        buffer.append(v[2]);
        this.Talents = buffer.toString();
        return true;
    }

    /** 兼容旧命名：获取指定装备槽位。 */
    public BigDecimal getpart(int type) {
        return this.getEquipPart(type);
    }

    /** 兼容旧命名：获取全部装备槽位。 */
    public BigDecimal[] getpartAll() {
        return this.getAllEquipParts();
    }

    /** 兼容旧命名：更换装备槽位。 */
    public BigDecimal ChangePart(BigDecimal id, int type) {
        return this.changeEquipPart(id, type);
    }

    /** 兼容旧命名：学习或替换天资。 */
    public boolean ChangeTalent(int type, String talent) {
        return this.changeTalent(type, talent);
    }
    
    public String getOutcome() {
        return this.outcome;
    }
    
    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
    
    /**
     * 获取宝宝天资串。
     * 当服务端未持久化该值时，返回默认的三条占位天资，保证旧逻辑可继续运行。
     */
    public String getTalents() {
        return this.Talents;
    }
    
    public void setTalents(String talents) {
        this.Talents = talents;
    }
    
    /**
     * 获取宝宝装备槽位串。
     * 当槽位信息为空时，按四个空槽位初始化，避免客户端与服务端解析时出现空指针。
     */
    public String getParts() {
        if (this.parts == null || this.parts.equals("")) {
            this.parts = "-1|-1|-1|-1";
        }
        return this.parts;
    }
    
    public void setParts(String parts) {
        this.parts = parts;
    }
    
    public BigDecimal getBabyid() {
        return this.babyid;
    }
    
    public void setBabyid(BigDecimal babyid) {
        this.babyid = babyid;
    }
    
    public String getBabyname() {
        return this.babyname;
    }
    
    public void setBabyname(String babyname) {
        this.babyname = babyname;
    }
    
    public Integer getQizhi() {
        return this.qizhi;
    }
    
    public void setQizhi(Integer qizhi) {
        this.qizhi = qizhi;
    }
    
    public Integer getNeili() {
        return this.neili;
    }
    
    public void setNeili(Integer neili) {
        this.neili = neili;
    }
    
    public Integer getZhili() {
        return this.zhili;
    }
    
    public void setZhili(Integer zhili) {
        this.zhili = zhili;
    }
    
    public Integer getNaili() {
        return this.naili;
    }
    
    public void setNaili(Integer naili) {
        this.naili = naili;
    }
    
    public Integer getMingqi() {
        return this.mingqi;
    }
    
    public void setMingqi(Integer mingqi) {
        this.mingqi = mingqi;
    }
    
    public Integer getDaode() {
        return this.daode;
    }
    
    public void setDaode(Integer daode) {
        this.daode = daode;
    }
    
    public Integer getPanni() {
        return this.panni;
    }
    
    public void setPanni(Integer panni) {
        this.panni = panni;
    }
    
    public Integer getWanxing() {
        return this.wanxing;
    }
    
    public void setWanxing(Integer wanxing) {
        this.wanxing = wanxing;
    }
    
    public Integer getQingmi() {
        return this.qingmi;
    }
    
    public void setQingmi(Integer qingmi) {
        this.qingmi = qingmi;
    }
    
    public Integer getXiaoxin() {
        return this.xiaoxin;
    }
    
    public void setXiaoxin(Integer xiaoxin) {
        this.xiaoxin = xiaoxin;
    }
    
    public Integer getWenbao() {
        return this.wenbao;
    }
    
    public void setWenbao(Integer wenbao) {
        this.wenbao = wenbao;
    }
    
    public Integer getPilao() {
        return this.pilao;
    }
    
    public void setPilao(Integer pilao) {
        this.pilao = pilao;
    }
    
    public Integer getYangyujin() {
        return this.yangyujin;
    }
    
    public void setYangyujin(Integer yangyujin) {
        this.yangyujin = yangyujin;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public Integer getChildSex() {
        return this.childSex;
    }
    
    public void setChildSex(Integer childSex) {
        this.childSex = childSex;
    }
    
    public Integer getBabyage() {
        return this.babyage;
    }
    
    public void setBabyage(Integer babyage) {
        this.babyage = babyage;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
}
