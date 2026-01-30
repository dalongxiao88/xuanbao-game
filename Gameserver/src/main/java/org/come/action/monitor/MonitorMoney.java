package org.come.action.monitor;

public class MonitorMoney
{
    private long add_X_CZ;
    private long add_X_VIP;
    private long add_X_Other;
    private long add_D_Robot;
    private long add_D_Task;
    private long add_D_GGL;
    private long add_D_Other;
    private long add_C_CZ;
    private long use_X;
    private long use_D;
    private long use_C;
    private long use_Z;
    private long add_Z_Other;
    
    public void toString(StringBuffer buffer) {
        buffer.append("__今日充值总量:");
        buffer.append(this.add_C_CZ);
        buffer.append("__今日充值积分消耗:");
        buffer.append(this.use_C);
        buffer.append("__今日仙玉增量:");
        buffer.append(this.add_X_CZ + this.add_X_VIP + this.add_X_Other);
        buffer.append("__来源:充值=");
        buffer.append(this.add_X_CZ);
        buffer.append(",VIP系统=");
        buffer.append(this.add_X_VIP);
        buffer.append(",其他游戏行为=");
        buffer.append(this.add_X_Other);
        buffer.append("__今日大话币增量:");
        buffer.append((this.add_D_Robot + this.add_D_Task + this.add_D_GGL + this.add_D_Other) / 10000L);
        buffer.append("W__来源:robot=");
        buffer.append(this.add_D_Robot / 10000L);
        buffer.append("W,任务=");
        buffer.append(this.add_D_Task / 10000L);
        buffer.append("W,刮刮乐=");
        buffer.append(this.add_D_GGL / 10000L);
        buffer.append("W,其他游戏行为=");
        buffer.append(this.add_D_Other / 10000L);
        buffer.append("__今日转区币增量:");
        buffer.append(this.add_Z_Other);
        buffer.append("W__今日仙玉消耗:");
        buffer.append(this.use_X);
        buffer.append("__今日大话币消耗:");
        buffer.append(this.use_D / 10000L);
        buffer.append("__今日转区币消耗:");
        buffer.append(this.use_Z / 10000L);
        buffer.append("W");
    }
    
    public void reset() {
        this.add_X_CZ = 0L;
        this.add_X_VIP = 0L;
        this.add_X_Other = 0L;
        this.add_D_Robot = 0L;
        this.add_D_Task = 0L;
        this.add_D_GGL = 0L;
        this.add_D_Other = 0L;
        this.add_C_CZ = 0L;
        this.use_X = 0L;
        this.use_D = 0L;
        this.use_C = 0L;
        this.add_Z_Other = 0L;
        this.use_Z = 0L;
    }
    
    public synchronized void addX(long add, int type) {
        if (type == 0) {
            this.add_X_CZ += add;
        }
        else if (type == 1) {
            this.add_X_VIP += add;
        }
        else if (type == 3) {
            this.add_C_CZ += add;
        }
        else {
            this.add_X_Other += add;
        }
    }
    
    public synchronized void addD(long add, int type) {
        if (type == 0) {
            this.add_D_Robot += add;
        }
        else if (type == 1) {
            this.add_D_Task += add;
        }
        else if (type == 2) {
            this.add_D_GGL += add;
        }
        else {
            this.add_D_Other += add;
        }
    }
    
    public synchronized void addC(long add) {
        this.add_C_CZ += add;
    }
    
    public synchronized void useX(long use) {
        this.use_X += use;
    }
    
    public synchronized void useD(long use) {
        this.use_D += use;
    }
    
    public synchronized void useC(long use) {
        this.use_C += use;
    }
    
    public synchronized void addZ(long add) {
        this.add_Z_Other += add;
    }
    
    public synchronized void useZ(long use) {
        this.use_Z += use;
    }
    
    public long getAdd_X_CZ() {
        return this.add_X_CZ;
    }
    
    public void setAdd_X_CZ(long add_X_CZ) {
        this.add_X_CZ = add_X_CZ;
    }
    
    public long getAdd_X_VIP() {
        return this.add_X_VIP;
    }
    
    public void setAdd_X_VIP(long add_X_VIP) {
        this.add_X_VIP = add_X_VIP;
    }
    
    public long getAdd_X_Other() {
        return this.add_X_Other;
    }
    
    public void setAdd_X_Other(long add_X_Other) {
        this.add_X_Other = add_X_Other;
    }
    
    public long getAdd_D_Robot() {
        return this.add_D_Robot;
    }
    
    public void setAdd_D_Robot(long add_D_Robot) {
        this.add_D_Robot = add_D_Robot;
    }
    
    public long getAdd_D_Task() {
        return this.add_D_Task;
    }
    
    public void setAdd_D_Task(long add_D_Task) {
        this.add_D_Task = add_D_Task;
    }
    
    public long getAdd_D_GGL() {
        return this.add_D_GGL;
    }
    
    public void setAdd_D_GGL(long add_D_GGL) {
        this.add_D_GGL = add_D_GGL;
    }
    
    public long getAdd_D_Other() {
        return this.add_D_Other;
    }
    
    public void setAdd_D_Other(long add_D_Other) {
        this.add_D_Other = add_D_Other;
    }
    
    public long getAdd_C_CZ() {
        return this.add_C_CZ;
    }
    
    public void setAdd_C_CZ(long add_C_CZ) {
        this.add_C_CZ = add_C_CZ;
    }
    
    public long getUse_X() {
        return this.use_X;
    }
    
    public void setUse_X(long use_X) {
        this.use_X = use_X;
    }
    
    public long getUse_D() {
        return this.use_D;
    }
    
    public void setUse_D(long use_D) {
        this.use_D = use_D;
    }
    
    public long getUse_C() {
        return this.use_C;
    }
    
    public void setUse_C(long use_C) {
        this.use_C = use_C;
    }
    
    public long getAdd_Z_Other() {
        return this.add_Z_Other;
    }
    
    public void setAdd_Z_Other(long add_Z_Other) {
        this.add_Z_Other = add_Z_Other;
    }
}
