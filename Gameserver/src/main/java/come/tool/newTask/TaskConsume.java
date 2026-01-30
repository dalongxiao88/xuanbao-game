package come.tool.newTask;

import org.come.entity.Goodstable;
import java.util.List;

public class TaskConsume
{
    private long money;
    private List<Goodstable> goods;
    
    public long getMoney() {
        return this.money;
    }
    
    public void setMoney(long money) {
        this.money = money;
    }
    
    public List<Goodstable> getGoods() {
        return this.goods;
    }
    
    public void setGoods(List<Goodstable> goods) {
        this.goods = goods;
    }
}
