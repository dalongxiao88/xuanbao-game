package come.tool.Stall;

import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.math.BigDecimal;

public class CommodityBean<T>
{
    private BigDecimal id;
    private BigDecimal commodityId;
    private BigDecimal money;
    private String commodityName;
    private String commodityLvl;
    private String commoditySkin;
    private String currency;
    private int type;
    private int state;
    private int sum;
    private Goodstable goods;
    private RoleSummoning pet;
    private Lingbao lingbao;
    private Object lock;
    
    public Object getLock() {
        if (this.lock == null) {
            this.lock = new Object();
        }
        return this.lock;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public BigDecimal getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(BigDecimal commodityId) {
        this.commodityId = commodityId;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public String getCommodityName() {
        return this.commodityName;
    }
    
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    
    public String getCommodityLvl() {
        return this.commodityLvl;
    }
    
    public void setCommodityLvl(String commodityLvl) {
        this.commodityLvl = commodityLvl;
    }
    
    public String getCommoditySkin() {
        return this.commoditySkin;
    }
    
    public void setCommoditySkin(String commoditySkin) {
        this.commoditySkin = commoditySkin;
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
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
    
    public int getSum() {
        return this.sum;
    }
    
    public void setSum(int sum) {
        this.sum = sum;
    }
    
    public Goodstable getGoods() {
        return this.goods;
    }
    
    public void setGoods(Goodstable goods) {
        this.goods = goods;
    }
    
    public RoleSummoning getPet() {
        return this.pet;
    }
    
    public void setPet(RoleSummoning pet) {
        this.pet = pet;
    }
    
    public Lingbao getLingbao() {
        return this.lingbao;
    }
    
    public void setLingbao(Lingbao lingbao) {
        this.lingbao = lingbao;
    }
}
