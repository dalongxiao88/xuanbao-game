package org.come.bean;

public class AddState
{
    private String statename;
    private double stateEffect;
    private int Surplus;
    
    public String getStatename() {
        return this.statename;
    }
    
    public void setStatename(String statename) {
        this.statename = statename;
    }
    
    public int getSurplus() {
        return this.Surplus;
    }
    
    public void setSurplus(int surplus) {
        this.Surplus = surplus;
    }
    
    public double getStateEffect() {
        return this.stateEffect;
    }
    
    public void setStateEffect(double stateEffect) {
        this.stateEffect = stateEffect;
    }
}
