package come.tool.Calculation;

public class BaseQl
{
    private String key;
    private double value;
    private String value1;
    
    public BaseQl(String key, double value) {
        this.key = key;
        this.value = value;
    }
    
    public BaseQl(String key, String value1) {
        this.key = key;
        this.value1 = value1;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public String getValue1() {
        return this.value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }
}
