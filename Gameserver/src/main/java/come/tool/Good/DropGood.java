package come.tool.Good;

import org.come.action.lottery.DrawBase;

public class DropGood
{
    private double empty;
    private DrawBase[] draws;
    
    public double getEmpty() {
        return this.empty;
    }
    
    public void setEmpty(double empty) {
        this.empty = empty;
    }
    
    public DrawBase[] getDraws() {
        return this.draws;
    }
    
    public void setDraws(DrawBase[] draws) {
        this.draws = draws;
    }
}
