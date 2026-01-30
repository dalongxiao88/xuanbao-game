package org.come.model;

import java.util.List;
import org.come.entity.Goodstable;
import java.math.BigDecimal;

public class AuctionExchange
{
    private int eId;
    private int type;
    private String consume;
    private BigDecimal gid;
    private Integer moneyType;
    private String week;
    private String time;
    private String goodssname;
    private String instruction;
    private String[] instructions;
    private String[] value;
    
    public void initGood(Goodstable goodstable, List<Goodstable> goodstables) {
        this.goodssname = goodstable.getGoodsname();
        this.instruction = goodstable.getInstruction();
        this.value = new String[goodstables.size()];
        this.instructions = new String[goodstables.size()];
        for (int i = 0; i < goodstables.size(); ++i) {
            this.instructions[i] = ((Goodstable)goodstables.get(i)).getInstruction();
        }
        for (int i = 0; i < goodstables.size(); ++i) {
            this.value[i] = ((Goodstable)goodstables.get(i)).getValue();
        }
    }
    
    public int geteId() {
        return this.eId;
    }
    
    public void seteId(int eId) {
        this.eId = eId;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getConsume() {
        return this.consume;
    }
    
    public void setConsume(String consume) {
        this.consume = consume;
    }
    
    public BigDecimal getGid() {
        return this.gid;
    }
    
    public void setGid(BigDecimal gid) {
        this.gid = gid;
    }
    
    public String getGoodssname() {
        return this.goodssname;
    }
    
    public void setGoodssname(String goodssname) {
        this.goodssname = goodssname;
    }
    
    public String getInstruction() {
        return this.instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
    
    public String[] getInstructions() {
        return this.instructions;
    }
    
    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }
    
    public String[] getValue() {
        return this.value;
    }
    
    public void setValue(String[] value) {
        this.value = value;
    }
    
    public String getWeek() {
        return this.week;
    }
    
    public void setWeek(String week) {
        this.week = week;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public Integer getMoneyType() {
        return this.moneyType;
    }
    
    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }
}
