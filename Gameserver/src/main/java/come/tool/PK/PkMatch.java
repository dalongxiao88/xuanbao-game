package come.tool.PK;

import java.math.BigDecimal;
import come.tool.Role.RoleCard;

public class PkMatch
{
    public static long OTHERTIME;
    public static long OVERTIME;
    public static long OVERTIME2;
    private int pid;
    private int type;
    private int state;
    private long time;
    private int battleNumber;
    private RoleCard pkMan1;
    private PKStake pKStake1;
    private RoleCard pkMan2;
    private PKStake pKStake2;
    
    public PkMatch(int pid, int type, RoleCard pkMan1, RoleCard pkMan2, PKStake pKStake1) {
        this.pid = pid;
        this.type = type;
        this.pkMan1 = pkMan1;
        this.pkMan2 = pkMan2;
        this.pKStake1 = pKStake1;
        this.state = 0;
        this.time = System.currentTimeMillis();
    }
    
    public void PKAgree(PKStake pKStake2) {
        this.pKStake2 = pKStake2;
        this.state = 1;
    }
    
    public int isPKStart() {
        if (this.pKStake1 == null || this.pKStake2 == null) {
            return 0;
        }
        if (this.state == 1) {
            this.state = 2;
            return 1;
        }
        if (this.state == 2) {
            return 2;
        }
        return 0;
    }
    
    public boolean isPK(BigDecimal role_id1, BigDecimal role_id2) {
        return this.isPK(role_id1) != 0 && this.isPK(role_id2) != 0;
    }
    
    public int isPK(BigDecimal role_id) {
        if (role_id.compareTo(this.pkMan1.getRoleId()) == 0) {
            return 1;
        }
        if (this.pkMan2 == null) {
            return 0;
        }
        if (role_id.compareTo(this.pkMan2.getRoleId()) == 0) {
            return 2;
        }
        return 0;
    }
    
    public boolean isOverTime(long OverTime) {
        return (this.state == 0 || this.state == 1) && OverTime - this.time > PkMatch.OVERTIME;
    }
    
    public boolean isOverTime2(long OverTime) {
        return (this.state == 0 || this.state == 1) && OverTime - this.time > PkMatch.OVERTIME2;
    }
    
    public int getPid() {
        return this.pid;
    }
    
    public void setPid(int pid) {
        this.pid = pid;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public RoleCard getPkMan1() {
        return this.pkMan1;
    }
    
    public void setPkMan1(RoleCard pkMan1) {
        this.pkMan1 = pkMan1;
    }
    
    public RoleCard getPkMan2() {
        return this.pkMan2;
    }
    
    public void setPkMan2(RoleCard pkMan2) {
        this.pkMan2 = pkMan2;
    }
    
    public PKStake getpKStake1() {
        return this.pKStake1;
    }
    
    public void setpKStake1(PKStake pKStake1) {
        this.pKStake1 = pKStake1;
    }
    
    public PKStake getpKStake2() {
        return this.pKStake2;
    }
    
    public void setpKStake2(PKStake pKStake2) {
        this.pKStake2 = pKStake2;
    }
    
    public int getState() {
        return this.state;
    }
    
    public int getBattleNumber() {
        return this.battleNumber;
    }
    
    public void setBattleNumber(int battleNumber) {
        this.battleNumber = battleNumber;
    }
    
    static {
        PkMatch.OTHERTIME = 18000000L;
        PkMatch.OVERTIME = 600000L;
        PkMatch.OVERTIME2 = 300000L;
    }
}
