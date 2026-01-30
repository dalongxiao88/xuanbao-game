package come.tool.Battle;

import org.come.model.Robots;
import java.util.List;

public class BattleEndMonitor
{
    private int fightingId;//战斗id
    private int robotId;//战斗robotID
    private List<Integer> taskIds;//该战斗可完成的任务集合
    
    public BattleEndMonitor(int fightingId, Robots robots) {
        this.fightingId = fightingId;
        if (robots != null) {
            this.robotId = robots.getRobotID();
            this.taskIds = robots.getTaskIds();
        }
    }
    
    public int getFightingId() {
        return this.fightingId;
    }
    
    public void setFightingId(int fightingId) {
        this.fightingId = fightingId;
    }
    
    public int getRobotId() {
        return this.robotId;
    }
    
    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }
    
    public List<Integer> getTaskIds() {
        return this.taskIds;
    }
    
    public void setTaskIds(List<Integer> taskIds) {
        this.taskIds = taskIds;
    }
}
