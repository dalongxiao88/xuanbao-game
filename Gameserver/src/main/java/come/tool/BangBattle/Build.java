package come.tool.BangBattle;

public class Build
{
    public static int IDLE;
    public static int ENERGY;
    public static int BEATEN;
    public static int ATTACK;
    public static int TOWER_DOOR;
    public static int TOWER_FIRE;
    public static int TOWER_ICE;
    public static int TOWER_LONG;
    public static int DNTG_JT;
    public static int DNTG_CY;
    public static int DNTG_DBY;
    private int bh;//编号
    private int hp;//塔剩余体力值
    private int maxhp;
    private int type;//塔类型
    private int state;  //状态
    private long time;//时间
    private String RoleName;//操作人
    //判断操作到时间
    public boolean istime() {
        ++this.time;
        if (this.type == Build.TOWER_LONG) {
            if (this.time >= 30L) {
                this.time = 0L;
                this.state = Build.IDLE;
                return true;
            }
        }
        else if (this.time >= 60L) {
            this.time = 0L;
            this.state = Build.IDLE;
            return true;
        }
        return false;
    }
    //初始化
    public Build(int type, int bh) {
        this.bh = bh;
        if (type == Build.TOWER_DOOR) {
            this.hp = 6000;
        }
        else {
            this.hp = 600;
        }
        this.maxhp = this.hp;
        this.type = type;
    }
    
    public Build(int type, int bh, int hp) {
        this.bh = bh;
        this.type = type;
        this.hp = hp;
        this.maxhp = hp;
    }
    
    public int getBh() {
        return this.bh;
    }
    
    public void setBh(int bh) {
        this.bh = bh;
    }
    
    public int getHp() {
        return this.hp;
    }
    
    public boolean setHp(int hp) {
        if (hp > this.maxhp) {
            this.hp = this.maxhp;
            return false;
        }
        if (hp <= 0) {
            this.hp = 0;
        }
        else {
            this.hp = hp;
        }
        return true;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public String getRoleName() {
        if (this.RoleName == null) {
            this.RoleName = "";
        }
        return this.RoleName;
    }
    
    public void setRoleName(String roleName) {
        this.RoleName = roleName;
    }
    
    public String getName() {
        switch (this.type) {
            case 0: {
                return "城门";
            }
            case 1: {
                return "火塔";
            }
            case 2: {
                return "冰塔";
            }
            case 3: {
                return "龙神大炮";
            }
            default: {
                return "";
            }
        }
    }
    
    public int getSurvival() {
        if (this.type == 0) {
            if (this.hp > 3000) {
                return 0;
            }
            if (this.hp > 0) {
                return 1;
            }
            return 2;
        }
        else {
            if (this.hp > 300) {
                return 0;
            }
            if (this.hp > 0) {
                return 1;
            }
            return 2;
        }
    }
    
    static {
        Build.IDLE = 0;//空闲
        Build.ENERGY = 1;//充能
        Build.BEATEN = 2;//被殴打
        Build.ATTACK = 3; //攻击
        Build.TOWER_DOOR = 0;//门
        Build.TOWER_FIRE = 1;  //火
        Build.TOWER_ICE = 2; //冰
        Build.TOWER_LONG = 3;//龙门
        Build.DNTG_JT = 4;//箭塔
        Build.DNTG_CY = 5;//生产
        Build.DNTG_DBY = 6;//大本营
    }
}
