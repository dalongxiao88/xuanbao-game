package come.tool.FightingData;

public class Original
{
    private int hp_z;
    private double hpxs;
    private int mp_z;
    private double mpxs;
    private String model;
    
    public Original(int hp_z, int mp_z, String model) {
        this.hp_z = hp_z;
        this.mp_z = mp_z;
        this.model = model;
        this.hpxs = 1.0;
        this.mpxs = 1.0;
    }
    
    public double upXS(int type, double xs) {
        if (type == 0) {
            return this.hpxs += xs;
        }
        if (type == 1) {
            return this.mpxs += xs;
        }
        return 1.0;
    }
    
    public int getHp_z() {
        return this.hp_z;
    }
    
    public void setHp_z(int hp_z) {
        this.hp_z = hp_z;
    }
    
    public int getMp_z() {
        return this.mp_z;
    }
    
    public void setMp_z(int mp_z) {
        this.mp_z = mp_z;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
}
