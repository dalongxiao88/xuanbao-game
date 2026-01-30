package come.tool.Transplant;

import org.come.entity.Gang;
import java.util.List;

public class GangTransplant
{
    private List<Gang> gangs;
    
    public GangTransplant(List<Gang> gangs) {
        this.gangs = gangs;
    }
    
    public List<Gang> getGangs() {
        return this.gangs;
    }
    
    public void setGangs(List<Gang> gangs) {
        this.gangs = gangs;
    }
}
