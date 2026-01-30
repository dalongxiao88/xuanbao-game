package come.tool.PK;

import java.math.BigDecimal;

public class PalacePkBean
{
    private int PId;
    private String username;
    private int type;
    private int Ntype;
    private BigDecimal gold;
    private BigDecimal xianyu;
    private BigDecimal exp;
    private String sendStr;
    private int choices;
    
    public PalacePkBean() {
        this.choices = 0;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public BigDecimal getXianyu() {
        return this.xianyu;
    }
    
    public void setXianyu(BigDecimal xianyu) {
        this.xianyu = xianyu;
    }
    
    public BigDecimal getExp() {
        return this.exp;
    }
    
    public void setExp(BigDecimal exp) {
        this.exp = exp;
    }
    
    public int getNtype() {
        return this.Ntype;
    }
    
    public void setNtype(int ntype) {
        this.Ntype = ntype;
    }
    
    public int getPId() {
        return this.PId;
    }
    
    public void setPId(int pId) {
        this.PId = pId;
    }
    
    public String getSendStr() {
        return this.sendStr;
    }
    
    public void setSendStr(String sendStr) {
        this.sendStr = sendStr;
    }
    
    public int getChoices() {
        return this.choices;
    }
    
    public void setChoices(int choices) {
        this.choices = choices;
    }
}
