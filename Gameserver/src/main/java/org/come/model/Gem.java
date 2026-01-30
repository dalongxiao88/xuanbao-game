package org.come.model;

import come.tool.FightingData.Battlefield;
import java.util.ArrayList;
import java.util.List;

public class Gem
{
    private int bid;
    private String bname;
    private String value;
    private List<GemBase> gemBases;
    
    public void initGemBase() {
        this.gemBases = new ArrayList<>();
        String[] values = this.value.split("\\|");
        for (int i = 0; i < values.length; ++i) {
            String[] vs = values[i].split("=");
            double zhi = Double.parseDouble(vs[1]);
            vs = vs[0].split("#");
            for (int j = 0; j < vs.length; ++j) {
                this.gemBases.add(new GemBase(vs[j], zhi));
            }
        }
    }
    
    public GemBase getGemBase(String type) {
        if (type == null) {
            return this.rGemBase();
        }
        for (int i = this.gemBases.size() - 1; i >= 0; --i) {
            GemBase base = (GemBase)this.gemBases.get(i);
            if (base.getType().equals(type)) {
                return base;
            }
        }
        return (GemBase)this.gemBases.get(0);
    }
    
    public GemBase rGemBase() {
        return (GemBase)this.gemBases.get(Battlefield.random.nextInt(this.gemBases.size()));
    }
    
    public int getBid() {
        return this.bid;
    }
    
    public void setBid(int bid) {
        this.bid = bid;
    }
    
    public String getBname() {
        return this.bname;
    }
    
    public void setBname(String bname) {
        this.bname = bname;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
