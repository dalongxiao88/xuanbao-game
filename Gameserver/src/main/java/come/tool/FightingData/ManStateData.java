package come.tool.FightingData;

import java.util.List;

public class ManStateData implements Cloneable
{
    private int type;
    private int id;
    private int camp;
    private int man;
    private String manname;
    private int States;
    private List<AddState> addStates;
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCamp() {
        return this.camp;
    }
    
    public void setCamp(int camp) {
        this.camp = camp;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public String getManname() {
        return this.manname;
    }
    
    public void setManname(String manname) {
        this.manname = manname;
    }
    
    public int getStates() {
        return this.States;
    }
    
    public void setStates(int states) {
        this.States = states;
    }
    
    public List<AddState> getAddStates() {
        return this.addStates;
    }
    
    public void setAddStates(List<AddState> addStates) {
        this.addStates = addStates;
    }
}
