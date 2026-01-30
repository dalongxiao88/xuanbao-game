package come.tool.Fighting;

import java.math.BigDecimal;

public class FightingState
{
    private String proces;
    private String endState;
    private String skillskin;
    private String skillsy;
    private int Camp;
    private int man;
    private String StartState;
    private FightingManData data;
    private String state_1;
    private String state_2;
    private String state_3;
    private BigDecimal hp_c;
    private BigDecimal mp_c;
    private BigDecimal yq_c;
    private BigDecimal nq_c;
    private String text;
    private String msg;
    private String buff;
    private String up;
    private String skin;
    private String extsy;
    private Integer sglxTag;
    private Double sp;
    private BigDecimal speciesid;
    
    public String getExtsy() {
        return this.extsy;
    }
    
    public void setExtsy(String extsy) {
        this.extsy = extsy;
    }
    
    public BigDecimal getSpeciesid() {
        return this.speciesid;
    }
    
    public void setSpeciesid(BigDecimal speciesid) {
        this.speciesid = speciesid;
    }
    
    public FightingState() {
    }
    
    public FightingState(int camp, int man, String startState) {
        this.Camp = camp;
        this.man = man;
        this.StartState = startState;
    }
    
    public int getCamp() {
        return this.Camp;
    }
    
    public void setCamp(int camp) {
        this.Camp = camp;
    }
    
    public int getMan() {
        return this.man;
    }
    
    public void setMan(int man) {
        this.man = man;
    }
    
    public String getStartState() {
        if (this.StartState == null || this.StartState.equals("")) {
            this.StartState = "代价";
        }
        return this.StartState;
    }
    
    public void setStartState(String startState) {
        this.StartState = startState;
    }
    
    public String getProcessState() {
        return this.proces;
    }
    
    public void setProcessState(String ProcessState) {
        this.proces = ProcessState;
    }
    
    public String getEndState_1() {
        return this.state_1;
    }
    
    public void setEndState_1(String endState_1) {
        this.state_1 = endState_1;
    }
    
    public BigDecimal getHp_Change() {
        return this.hp_c;
    }
    
    public void setHp_Change(int hp_Change) {
        if (this.hp_c == null) {
            this.hp_c = new BigDecimal(hp_Change);
        }
        else {
            this.hp_c = new BigDecimal(this.hp_c.intValue() + hp_Change);
        }
    }
    
    public BigDecimal getMp_Change() {
        return this.mp_c;
    }
    
    public void setMp_Change(int mp_Change) {
        if (this.mp_c == null) {
            this.mp_c = new BigDecimal(mp_Change);
        }
        else {
            this.mp_c = new BigDecimal(this.mp_c.intValue() + mp_Change);
        }
    }
    
    public BigDecimal getYq_c() {
        return this.yq_c;
    }
    
    public void setYq_c(int yq_c) {
        if (this.yq_c == null) {
            this.yq_c = new BigDecimal(yq_c);
        }
        else {
            this.yq_c = new BigDecimal(this.yq_c.intValue() + yq_c);
        }
    }
    
    public BigDecimal getNq_c() {
        return this.nq_c;
    }
    
    public void setNq_c(int nq_c) {
        if (this.nq_c == null) {
            this.nq_c = new BigDecimal(nq_c);
        }
        else {
            this.nq_c = new BigDecimal(this.nq_c.intValue() + nq_c);
        }
    }
    
    public FightingManData getFightingManData() {
        return this.data;
    }
    
    public void setFightingManData(FightingManData fightingManData) {
        this.data = fightingManData;
    }
    
    public String getEndState_2() {
        return this.state_2;
    }
    
    public void setEndState_2(String endState_2) {
        this.state_2 = endState_2;
    }
    
    public String getEndState() {
        return this.endState;
    }
    
    public void setEndState(String endState) {
        this.endState = endState;
    }
    
    public String getSkillskin() {
        return this.skillskin;
    }
    
    public void setSkillskin(String skillskin) {
        this.skillskin = skillskin;
    }
    
    public String getSkillsy() {
        return this.skillsy;
    }
    
    public void setSkillsy(String skillsy) {
        this.skillsy = skillsy;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getBuff() {
        return this.buff;
    }
    
    public void setBuff(String buff) {
        this.buff = buff;
    }
    
    public String getUp() {
        return this.up;
    }
    
    public void setUp(String up) {
        this.up = up;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public String getState_3() {
        return this.state_3;
    }
    
    public void setState_3(String state_3) {
        this.state_3 = state_3;
    }
    
    public Integer getSglxTag() {
        return this.sglxTag;
    }
    
    public void setSglxTag(Integer sglxTag) {
        this.sglxTag = sglxTag;
    }
    
    public Double getSp() {
        return this.sp;
    }
    
    public void setSp(Double sp) {
        this.sp = sp;
    }
}
