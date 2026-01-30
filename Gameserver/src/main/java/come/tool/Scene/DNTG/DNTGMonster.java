package come.tool.Scene.DNTG;

import org.come.task.MapMonsterBean;

public class DNTGMonster
{
    private MapMonsterBean bean;
    private int camp;
    private int value;
    private int hurt;
    
    public DNTGMonster(MapMonsterBean bean, int camp, int value, int hurt) {
        this.bean = bean;
        this.camp = camp;
        this.value = value;
        this.hurt = hurt;
    }
    
    public boolean move() {
        return this.bean.getType() == 0 && this.bean.getMove() != null && this.bean.getMove().isMove(1000);
    }
    
    public MapMonsterBean getBean() {
        return this.bean;
    }
    
    public void setBean(MapMonsterBean bean) {
        this.bean = bean;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getHurt() {
        return this.hurt;
    }
    
    public void setHurt(int hurt) {
        this.hurt = hurt;
    }
}
