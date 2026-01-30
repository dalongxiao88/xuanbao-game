package come.tool.Scene.DNTG;

import org.come.model.Skill;
import java.math.BigDecimal;

public class DNTGRole
{
    private static final long TIME = 20000L;
    private BigDecimal roleId;
    private String roleName;
    private int camp;
    private int DNJB;
    private int DNJF;
    private int useDNJF;
    private String SLJC;
    private int NVNum;
    private long time;
    private boolean isA;
    
    public DNTGRole(BigDecimal roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.camp = -1;
        this.isA = false;
        this.time = System.currentTimeMillis() + 20000L;
    }
    
    public BigDecimal getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(BigDecimal roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getDNJB() {
        return this.DNJB;
    }
    
    public void setDNJB(int dNJB) {
        this.DNJB = dNJB;
    }
    
    public int getDNJF() {
        return this.DNJF;
    }
    
    public void setDNJF(int dNJF) {
        this.DNJF = dNJF;
    }
    
    public String getSLJC() {
        return this.SLJC;
    }
    
    public void setSLJC(String sLJC) {
        this.SLJC = sLJC;
    }
    
    public String upSLJC(Skill skill, int KJValue) {
        if (this.SLJC == null || this.SLJC.equals("")) {
            if (skill.getDielectric() > (double)this.DNJB) {
                return "金币不足";
            }
            if (skill.getCamp() > KJValue) {
                return "科技值不足" + skill.getCamp();
            }
            this.DNJB = (int)((double)this.DNJB - skill.getDielectric());
            this.SLJC = skill.getSkillid() + "$1";
            return null;
        }
        else {
            boolean is = true;
            StringBuffer buffer = new StringBuffer();
            String[] vs = this.SLJC.split("&");
            for (int i = 0; i < vs.length; ++i) {
                if (buffer.length() != 0) {
                    buffer.append("&");
                }
                if (vs[i].startsWith(skill.getSkillid() + "$")) {
                    is = false;
                    String[] v = vs[i].split("\\$");
                    int lvl = Integer.parseInt(v[1]);
                    if (lvl >= skill.getSkilllevel()) {
                        return "已达到等级上限";
                    }
                    if ((double)(++lvl) * skill.getDielectric() > (double)this.DNJB) {
                        return "金币不足";
                    }
                    if (lvl * skill.getCamp() > KJValue) {
                        return "科技值不足" + lvl * skill.getCamp();
                    }
                    this.DNJB = (int)((double)this.DNJB - (double)lvl * skill.getDielectric());
                    buffer.append(skill.getSkillid());
                    buffer.append("$");
                    buffer.append(lvl);
                }
                else {
                    buffer.append(vs[i]);
                }
            }
            if (is) {
                if (skill.getDielectric() > (double)this.DNJB) {
                    return "金币不足";
                }
                if (skill.getCamp() > KJValue) {
                    return "科技值不足" + skill.getCamp();
                }
                this.DNJB = (int)((double)this.DNJB - skill.getDielectric());
                if (buffer.length() != 0) {
                    buffer.append("&");
                }
                buffer.append(skill.getSkillid());
                buffer.append("$1");
            }
            this.SLJC = buffer.toString();
            return null;
        }
    }
    
    public void addDNJB(int add) {
        this.DNJB += add;
    }
    
    public void addDNJF(int add) {
        this.DNJF += add;
        this.useDNJF += add;
    }
    
    public int getNVNum() {
        return this.NVNum;
    }
    
    public void setNVNum(int nVNum) {
        this.NVNum = nVNum;
    }
    
    public boolean isA() {
        return this.isA;
    }
    
    public void setA(boolean isA) {
        this.isA = isA;
    }
    
    public String isTime(long nTime) {
        nTime = this.time - nTime;
        nTime /= 1000L;
        if (nTime > 0L) {
            return this.roleName + "还需要休息" + nTime + "秒";
        }
        return null;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time + 20000L;
    }
    
    public int getUseDNJF() {
        return this.useDNJF;
    }
    
    public void setUseDNJF(int useDNJF) {
        this.useDNJF = useDNJF;
    }
}
