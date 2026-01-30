package come.tool.Role;

import come.tool.FightingData.ManData;
import come.tool.FightingData.Ql;
import java.math.BigDecimal;

public class GBGData2
{
    private BigDecimal hp;
    private BigDecimal mp;
    private BigDecimal ap;
    private BigDecimal sp;
    private BigDecimal cd;
    private BigDecimal gg;
    private BigDecimal lx;
    private BigDecimal ll;
    private BigDecimal mj;
    private BigDecimal dl;
    private Ql ql;
    
    public GBGData2(ManData manData) {
        this.hp = new BigDecimal(manData.getHp_z());
        this.mp = new BigDecimal(manData.getMp_z());
        this.ap = new BigDecimal(manData.huoAp());
        this.sp = new BigDecimal(manData.getSp2());
        this.cd = new BigDecimal(manData.getQihe());
        this.gg = new BigDecimal(manData.getHuoyue());
        this.lx = new BigDecimal(manData.getShanghai());
        this.ll = new BigDecimal(manData.getKangluobao());
        this.mj = new BigDecimal(manData.getYuanzhu());
        this.dl = this.cd;
        this.ql = manData.getQuality();
    }
    
    public BigDecimal getHp() {
        return this.hp;
    }
    
    public void setHp(BigDecimal hp) {
        this.hp = hp;
    }
    
    public BigDecimal getMp() {
        return this.mp;
    }
    
    public void setMp(BigDecimal mp) {
        this.mp = mp;
    }
    
    public BigDecimal getAp() {
        return this.ap;
    }
    
    public void setAp(BigDecimal ap) {
        this.ap = ap;
    }
    
    public BigDecimal getSp() {
        return this.sp;
    }
    
    public void setSp(BigDecimal sp) {
        this.sp = sp;
    }
    
    public BigDecimal getCd() {
        return this.cd;
    }
    
    public void setCd(BigDecimal cd) {
        this.cd = cd;
    }
    
    public BigDecimal getGg() {
        return this.gg;
    }
    
    public void setGg(BigDecimal gg) {
        this.gg = gg;
    }
    
    public BigDecimal getLx() {
        return this.lx;
    }
    
    public void setLx(BigDecimal lx) {
        this.lx = lx;
    }
    
    public BigDecimal getLl() {
        return this.ll;
    }
    
    public void setLl(BigDecimal ll) {
        this.ll = ll;
    }
    
    public BigDecimal getMj() {
        return this.mj;
    }
    
    public void setMj(BigDecimal mj) {
        this.mj = mj;
    }
    
    public BigDecimal getDl() {
        return this.dl;
    }
    
    public void setDl(BigDecimal dl) {
        this.dl = dl;
    }
    
    public Ql getQl() {
        return this.ql;
    }
    
    public void setQl(Ql ql) {
        this.ql = ql;
    }
}
