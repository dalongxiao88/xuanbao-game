package org.come.model;

import come.tool.Good.DropModel;
import java.util.List;
import come.tool.Good.HpSet;
import come.tool.Good.TSModel;

public class Robots
{
    private String robotid;
    private String robotname;
    private String robotskin;
    private transient int robottime;
    private transient String robotlvl;
    private transient String robotboss;
    private transient String robotmonster;
    private transient String robotcount;
    private transient String goodId;
    private transient String robotreward;
    private transient String unknow;
    private transient String lvllimit;
    private transient int robotType;
    private transient int dropLimit;
    private transient TSModel tsModel;
    private transient HpSet hpSet;
    private transient Integer isPal;
    private String robottitle;
    private transient int[] lvls;
    private transient List<Integer> taskIds;
    private transient List<String> monsterList;
    private transient DropModel dropModel;
    private transient boolean isJL;
    private transient int robotID;
    private String ewParam;
    
    public String getRobotid() {
        return this.robotid;
    }
    
    public void setRobotid(String robotid) {
        this.robotid = robotid;
    }
    
    public String getRobotname() {
        return this.robotname;
    }
    
    public void setRobotname(String robotname) {
        this.robotname = robotname;
    }
    
    public String getRobotskin() {
        return this.robotskin;
    }
    
    public void setRobotskin(String robotskin) {
        this.robotskin = robotskin;
    }
    
    public int getRobottime() {
        return this.robottime;
    }
    
    public void setRobottime(int robottime) {
        this.robottime = robottime;
    }
    
    public String getRobotlvl() {
        if (this.robotlvl == null || this.robotlvl.equals("")) {
            this.robotlvl = "0";
        }
        return this.robotlvl;
    }
    
    public void setRobotlvl(String robotlvl) {
        this.robotlvl = robotlvl;
    }
    
    public String getRobotboss() {
        return this.robotboss;
    }
    
    public void setRobotboss(String robotboss) {
        this.robotboss = robotboss;
    }
    
    public String getRobotmonster() {
        return this.robotmonster;
    }
    
    public void setRobotmonster(String robotmonster) {
        this.robotmonster = robotmonster;
    }
    
    public String getRobotcount() {
        return this.robotcount;
    }
    
    public void setRobotcount(String robotcount) {
        this.robotcount = robotcount;
    }
    
    public String getRobotreward() {
        return this.robotreward;
    }
    
    public void setRobotreward(String robotreward) {
        this.robotreward = robotreward;
    }
    
    public String getUnknow() {
        return this.unknow;
    }
    
    public void setUnknow(String unknow) {
        this.unknow = unknow;
    }
    
    public String getLvllimit() {
        return this.lvllimit;
    }
    
    public void setLvllimit(String lvllimit) {
        this.lvllimit = lvllimit;
    }
    
    public int[] getLvls() {
        return this.lvls;
    }
    
    public void setLvls(int[] lvls) {
        this.lvls = lvls;
    }
    
    public int getRobotType() {
        return this.robotType;
    }
    
    public void setRobotType(int robotType) {
        this.robotType = robotType;
    }
    
    public List<Integer> getTaskIds() {
        return this.taskIds;
    }
    
    public void setTaskIds(List<Integer> taskIds) {
        this.taskIds = taskIds;
    }
    
    public List<String> getMonsterList() {
        return this.monsterList;
    }
    
    public void setMonsterList(List<String> monsterList) {
        this.monsterList = monsterList;
    }
    
    public DropModel getDropModel() {
        return this.dropModel;
    }
    
    public void setDropModel(DropModel dropModel) {
        this.dropModel = dropModel;
        if (dropModel != null && (dropModel.getExps() != null || dropModel.getMaxGood() != null)) {
            this.isJL = true;
        }
    }
    
    public boolean isJL() {
        return this.isJL;
    }
    
    public void setJL(boolean isJL) {
        this.isJL = isJL;
    }
    
    public int getDropLimit() {
        return this.dropLimit;
    }
    
    public void setDropLimit(int dropLimit) {
        this.dropLimit = dropLimit;
    }
    
    public TSModel getTsModel() {
        return this.tsModel;
    }
    
    public void setTsModel(TSModel tsModel) {
        this.tsModel = tsModel;
    }
    
    public int getRobotID() {
        return this.robotID;
    }
    
    public void setRobotID(int robotID) {
        this.robotID = robotID;
    }
    
    public String getRobottitle() {
        return this.robottitle;
    }
    
    public void setRobottitle(String robottitle) {
        this.robottitle = robottitle;
    }
    
    public HpSet getHpSet() {
        return this.hpSet;
    }
    
    public void setHpSet(HpSet hpSet) {
        this.hpSet = hpSet;
    }
    
    public Integer getIsPal() {
        return this.isPal;
    }
    
    public void setIsPal(Integer isPal) {
        this.isPal = isPal;
    }
    
    public String getGoodId() {
        return this.goodId;
    }
    
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    
    public String getEwParam() {
        return this.ewParam;
    }
    
    public void setEwParam(String ewParam) {
        this.ewParam = ewParam;
    }
}
