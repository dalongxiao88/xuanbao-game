package come.tool.Calculation;

public class BaseXingpans
{
    //	星盘编号_数量_等级_属性1_值_属性2_值
    private int bh;//星盘编号
    private String exp;//数量
    private int xs;//等级
    private String key;//属性1
    private double value;//值
    private String key1;//属性2
    private double value1;//值
    private String skill;//技能

    public BaseXingpans(int bh, String exp) {
        this.bh = bh;
        this.exp = exp;
    }
    
    public BaseXingpans(int bh, String exp, int xs, String key, double value, String key1, double value1) {
        this.bh = bh;
        this.exp = exp;
        this.init(xs, key, value, key1, value1);
    }
    
    public void init(int xs, String key, double value, String key1, double value1) {
        this.xs = xs;
        this.key = key;
        this.value = value;
        this.key1 = key1;
        this.value1 = value1;
    }
    /**获取星盘编号*/
    public int getBh() {
        return this.bh;
    }
    /**等级*/
    public int getXs() {
        return this.xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }
    /**获取key*/
    public String getKey() {
        return this.key;
    }
    
    public String getKey1() {
        return this.key1;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public void setKey1(String key1) {
        this.key1 = key1;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public void setValue1(double value1) {
        this.value1 = value1;
    }
    /**获取属性*/
    public double getKeyValue() {
        return this.value * 0.4 + this.value * (double)this.xs * 0.04;
    }
    /**获取属性1*/
    public double getKeyValue1() {
        return this.value1 * 0.4 + this.value1 * (double)this.xs * 0.04;
    }
    
    public String getExp() {
        return this.exp;
    }
    
    public void setExp(String exp) {
        this.exp = exp;
    }
    /**获取数量*/
    @Override
    public String toString() {
        return this.bh + "_" + this.exp + "_" + this.xs + "_" + this.key + "_" + this.value + "_" + this.key1 + "_" + this.value1;
    }
}
