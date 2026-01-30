package come.tool.FightingData;

import org.come.bean.OneArenaBean;
import com.tool.Stall.AssetUpdate;

public class BattleEnd
{
    private int FightingNumber;
    private int camp;
    private AssetUpdate assetUpdate;
    private OneArenaBean arenaBean;
    private String taskn;
    private String taskDaily;
    private String msg;
    
    public int getFightingNumber() {
        return this.FightingNumber;
    }
    
    public void setFightingNumber(int fightingNumber) {
        this.FightingNumber = fightingNumber;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public AssetUpdate getAssetUpdate() {
        return this.assetUpdate;
    }
    
    public void setAssetUpdate(AssetUpdate assetUpdate) {
        this.assetUpdate = assetUpdate;
    }
    
    public String getTaskn() {
        return this.taskn;
    }
    
    public void setTaskn(String taskn) {
        this.taskn = taskn;
    }
    
    public String getTaskDaily() {
        return this.taskDaily;
    }
    
    public void setTaskDaily(String taskDaily) {
        this.taskDaily = taskDaily;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public OneArenaBean getArenaBean() {
        return this.arenaBean;
    }
    
    public void setArenaBean(OneArenaBean arenaBean) {
        this.arenaBean = arenaBean;
    }
}
