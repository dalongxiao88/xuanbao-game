package come.tool.Scene.BWZ;

import org.come.task.MapMonsterBean;

public class BWZMonster
{
    private MapMonsterBean bean;
    private int I;
    
    public BWZMonster(MapMonsterBean bean, int I) {
        this.bean = bean;
        this.I = I;
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
    
    public int getI() {
        return this.I;
    }
    
    public void setI(int i) {
        this.I = i;
    }
}
