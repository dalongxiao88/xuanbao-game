package org.come.entity;

public class Keju implements Cloneable
{
    private Integer mid;
    private String wenti;
    private String A;
    private String B;
    private String C;
    private String D;
    public static String[][] result;
    
    public Integer getMid() {
        return this.mid;
    }
    
    public String getA() {
        return this.A;
    }
    
    public String getB() {
        return this.B;
    }
    
    public String getC() {
        return this.C;
    }
    
    public String getD() {
        return this.D;
    }
    
    public String getWenti() {
        return this.wenti;
    }
    
    public void setA(String a) {
        this.A = a;
    }
    
    public void setB(String b) {
        this.B = b;
    }
    
    public void setC(String c) {
        this.C = c;
    }
    
    public void setD(String d) {
        this.D = d;
    }
    
    public void setMid(Integer mid) {
        this.mid = mid;
    }
    
    public void setWenti(String wenti) {
        this.wenti = wenti;
    }
    
    public Keju clone() {
        try {
            return (Keju)super.clone();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        Keju.result = (String[][])null;
    }
}
