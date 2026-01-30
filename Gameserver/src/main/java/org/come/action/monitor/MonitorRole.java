package org.come.action.monitor;

public class MonitorRole
{
    private int BSum;
    private int XSum;
    private int KSum;
    private long addBY;
    
    public int addBSum() {
        return this.BSum++;
    }
    
    public void addXSum() {
        ++this.XSum;
    }
    
    public void addKSum() {
        ++this.KSum;
    }
    
    public int getXSum() {
        return this.XSum;
    }
    
    public int getKSum() {
        return this.KSum;
    }
    
    public long getAddBY() {
        return this.addBY;
    }
    
    public void setAddBY(long addBY) {
        this.addBY = addBY;
    }
    
    public int getBSum() {
        return this.BSum;
    }
}
