package org.come.task;

import come.tool.Good.HpSet;
import java.math.BigDecimal;
import come.tool.Good.FYModel;
import come.tool.Good.TSModel;
import org.come.model.Lshop;
import java.util.concurrent.ConcurrentHashMap;

public class MapMonsterBean
{
    private Long map;
    private Integer x;
    private long y;
    private Integer robotid;
    private String robotname;
    private String robotskin;
    private String robottitle;
    private int robotType;
    private String goodId;
    private Integer i;
    private int type;
    private int maxtime;
    private String boosId;
    private int SX;
    private ConcurrentHashMap<String, Lshop> shops;
    private String shopMsg;
    private long createTime;
    private MonsterMove move;
    private MonsterMatch match;
    private MonsterFollow follow;
    private MonsterHp hp;
    private MonsterExp exp;
    private TSModel tsModel;
    private FYModel fyModel;
    private BigDecimal gangId;
    private String isMove;
    private String ewParam;
    public static long PROTECTTIME;
    
    public MapMonsterBean() {
        this.type = 0;
    }
    
    public MapMonsterBean(Long map, Integer i, int robotType, int SX) {
        this.type = 0;
        this.map = map;
        this.i = i;
        this.robotType = robotType;
        this.SX = SX;
    }
    
    public int getSX() {
        return this.SX;
    }
    
    public void setSX(int sX) {
        this.SX = sX;
    }
    
    public int getMaxtime() {
        return this.maxtime;
    }
    
    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }
    
    public boolean isMaxtime(int JG) {
        this.maxtime -= JG;
        return this.maxtime <= 0;
    }
    
    public Long getMap() {
        return this.map;
    }
    
    public void setMap(Long map) {
        this.map = map;
    }
    
    public Integer getX() {
        return this.x;
    }
    
    public void setX(Integer x) {
        if (x != null && (int)x <= 0) {
            x = Integer.valueOf(1);
        }
        this.x = x;
    }
    
    public long getY() {
        return this.y;
    }
    
    public void setY(long l) {
        if (l <= 0L) {
            l = 1L;
        }
        this.y = l;
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
    
    public Integer getI() {
        return this.i;
    }
    
    public void setI(Integer i) {
        this.i = i;
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public Integer getRobotid() {
        return this.robotid;
    }
    
    public void setRobotid(Integer robotid) {
        this.robotid = robotid;
    }
    
    public int getRobotType() {
        return this.robotType;
    }
    
    public void setRobotType(int robotType) {
        this.robotType = robotType;
    }
    
    public String getBoosId() {
        return this.boosId;
    }
    
    public void setBoosId(String boosId) {
        this.boosId = boosId;
    }
    
    public ConcurrentHashMap<String, Lshop> getShops() {
        return this.shops;
    }
    
    public void setShops(ConcurrentHashMap<String, Lshop> shops) {
        this.shops = shops;
    }
    
    public String getShopMsg() {
        return this.shopMsg;
    }
    
    public void setShopMsg(String shopMsg) {
        this.shopMsg = shopMsg;
    }
    
    public String getRobottitle() {
        return this.robottitle;
    }
    
    public void setRobottitle(String robottitle) {
        this.robottitle = robottitle;
    }
    
    public long getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
    public MonsterMatch getMatch() {
        return this.match;
    }
    
    public void setMatch(MonsterMatch match) {
        this.match = match;
    }
    
    public MonsterFollow getFollow() {
        return this.follow;
    }
    
    public void setFollow(MonsterFollow follow) {
        this.follow = follow;
    }
    
    public MonsterHp getHp() {
        return this.hp;
    }
    
    public void setHp(MonsterHp hp) {
        this.hp = hp;
    }
    
    public void setHpSet(HpSet hp) {
        if (this.hp == null) {
            this.hp = new MonsterHp();
        }
        if (hp != null) {
            this.hp.setHp(hp.getHp());
            this.hp.setHpMax(hp.getHpMax());
            this.hp.setMuch(hp.isMuch());
        }
    }
    
    public MonsterMove getMove() {
        return this.move;
    }
    
    public void setMove(MonsterMove move) {
        this.move = move;
    }
    
    public MonsterExp getExp() {
        return this.exp;
    }
    
    public void setExp(MonsterExp exp) {
        this.exp = exp;
    }
    
    public TSModel getTsModel() {
        return this.tsModel;
    }
    
    public TSModel getDieTsModel() {
        if (this.tsModel == null) {
            return null;
        }
        synchronized (this) {
            TSModel model = this.tsModel;
            this.tsModel = null;
            return model;
        }
    }
    
    public void setTsModel(TSModel tsModel) {
        this.tsModel = tsModel;
        if (this.tsModel != null) {
            this.match = this.tsModel.getMatch();
            this.exp = this.tsModel.getExp();
        }
    }
    
    public FYModel getDieFyModel() {
        if (this.fyModel == null) {
            return null;
        }
        synchronized (this) {
            FYModel model = this.fyModel;
            this.fyModel = null;
            return model;
        }
    }
    
    public void setFyModel(FYModel fyModel) {
        this.fyModel = fyModel;
    }
    
    public String isCreateTime() {
        if (this.createTime == 0L) {
            return null;
        }
        long time = MapMonsterBean.PROTECTTIME - (System.currentTimeMillis() - this.createTime) / 1000L;
        if (time > 0L) {
            return "剩余" + time + "秒的保护期";
        }
        this.createTime = 0L;
        return null;
    }
    
    public BigDecimal getGangId() {
        return this.gangId;
    }
    
    public void setGangId(BigDecimal gangId) {
        this.gangId = gangId;
    }
    
    public String getGoodId() {
        return this.goodId;
    }
    
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
    
    public String getIsMove() {
        return this.isMove;
    }
    
    public void setIsMove(String isMove) {
        this.isMove = isMove;
    }
    
    public String getEwParam() {
        return this.ewParam;
    }
    
    public void setEwParam(String ewParam) {
        this.ewParam = ewParam;
    }
    
    static {
        MapMonsterBean.PROTECTTIME = 180L;
    }
}
