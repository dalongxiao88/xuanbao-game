package come.tool.FightingData;

import org.come.entity.RoleSummoning;
import org.come.until.AllServiceUtil;
import come.tool.Calculation.BaseQl;
import come.tool.Role.Hang;

public class FightingSummon
{
    private ManData SummonData;//召唤兽数据
    private int Play;//召唤兽是否出场 0未上场 1正在上场 2已经下场
    private Hang hang;
    private Double helpV;
    private BaseQl[] vs;
    private double starXS;
    private int camp;
    private int man;
    private boolean isBB;
    
    public FightingSummon() {
    }
    
    public FightingSummon(int play, Hang hang, int camp, int man) {
        this.Play = play;
        this.hang = hang;
        this.camp = camp;
        this.man = man + 5;
    }
    
    public int getPlay() {
        return this.Play;
    }
    
    public void setPlay(int play) {
        this.Play = play;
    }
    
    public Hang getHang() {
        return this.hang;
    }
    
    public void setHang(Hang hang) {
        this.hang = hang;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public boolean isBB() {
        return this.isBB;
    }
    
    public void setBB(boolean isBB) {
        this.isBB = isBB;
    }
    
    public Double getHelpV() {
        return this.helpV;
    }
    
    public void setHelpV(Double helpV) {
        this.helpV = helpV;
    }
    
    public void setStar(BaseQl[] vs, double starXS) {
        this.vs = vs;
        this.starXS = starXS;
        if (this.vs != null && this.SummonData != null) {
            for (int i = 0; i < vs.length; ++i) {
                GetqualityUntil.AddR(this.SummonData.getQuality(), vs[i].getKey(), vs[i].getValue() * this.starXS);
            }
        }
    }
    /**判断是否触发闪现0没闪现 1闪失败 2闪成功*/
    public int getsx(double jc) {
        if (this.getPet() == null) {
            return 0;
        }
        if (this.helpV == null) {
            return 0;
        }
        if ((double)this.helpV + jc > (double)Battlefield.random.nextInt(100)) {
            return (this.getPet() != null) ? 2 : 1;
        }
        return 1;
    }
    /**获取召唤兽战斗对象*/
    public ManData getPet() {
        if (this.SummonData != null) {
            return this.SummonData;
        }
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(this.hang.getId());
        if (pet == null) {
            this.Play = 2;
            return null;
        }
        if (this.isBB && (long)pet.getFriendliness() < 200000L) {
            this.Play = 2;
            return null;
        }
        return this.SummonData = new ManData(pet, this);
    }
    
    public ManData getPet(boolean isAi) {
        if (this.SummonData != null) {
            return this.SummonData;
        }
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(this.hang.getId());
        if (pet == null) {
            this.Play = 2;
            return null;
        }
        if (this.isBB && (long)pet.getFriendliness() < 200000L) {
            this.Play = 2;
            return null;
        }
        //开始加载战斗对象
        this.SummonData = new ManData(pet, this);
        this.SummonData.isAi = isAi;
        return this.SummonData;
    }
    /**参战时判断 0是正常   1忠诚不够 2亲密不够*/
    public int getAttendPet(boolean isAi) {
        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(this.hang.getId());
        if (pet == null) {
            this.Play = 2;
            return 1;
        }
        if (this.isBB && (long)pet.getFriendliness() < 200000L) {
            return this.Play = 2;
        }
        if ((int)pet.getFaithful() < 70 || (Battlefield.random.nextBoolean() && (int)pet.getFaithful() == 70)) {
            this.Play = 2;
            return 1;
        }
        //开始加载战斗对象
        this.SummonData = new ManData(pet, this);
        this.SummonData.isAi = isAi;
        return 0;
    }
}
