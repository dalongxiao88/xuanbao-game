package come.tool.Fighting;

public class ChangeAmount
{
    private int amount;
    private int dangqian;
    
    public ChangeAmount() {
    }
    
    public ChangeAmount(int amount, int dangqian) {
        this.amount = amount;
        this.dangqian = dangqian;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public int getDangqian() {
        return this.dangqian;
    }
    
    public void setDangqian(int dangqian) {
        this.dangqian = dangqian;
    }
}
