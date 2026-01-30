package org.come.model;
/**
 * 自动机器人
 * */
public class InitBaiTan {
    private String id;//id
    private String stallId;//摊位id
    private String stallName;//摊位名字
    private String goodsId;//出售物品id
    private String goodsName;//出售物品名字
    private String money;//出售物品单价
    private String useTime;// 使用次数////出售物品数量

    private String acquiredId;//出售物品id
    private String acquiredName;//出售物品名字
    private String acquiredmoney;//出售物品单价
    private String acquirednum;// 使用次数////出售物品数量

    //摊位所在的地图id
    private String mapId;

    private String map_x;

    private String map_y;
    //货币
    private String currency;

    private String auto_sj;

    //自动捕获周期 单位分钟
    private int shuaxin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStallId() {
        return stallId;
    }

    public void setStallId(String stallId) {
        this.stallId = stallId;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(String acquiredId) {
        this.acquiredId = acquiredId;
    }

    public String getAcquiredName() {
        return acquiredName;
    }

    public void setAcquiredName(String acquiredName) {
        this.acquiredName = acquiredName;
    }

    public String getAcquiredmoney() {
        return acquiredmoney;
    }
    public void setAcquiredmoney(String acquiredmoney) {
        this.acquiredmoney = acquiredmoney;
    }

    public String getAcquirednum() {
        return acquirednum;
    }

    public void setAcquirednum(String acquirednum) {
        this.acquirednum = acquirednum;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getMap_x() {
        return map_x;
    }

    public void setMap_x(String map_x) {
        this.map_x = map_x;
    }

    public String getMap_y() {
        return map_y;
    }

    public void setMap_y(String map_y) {
        this.map_y = map_y;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuto_sj() {
        return auto_sj;
    }

    public void setAuto_sj(String auto_sj) {
        this.auto_sj = auto_sj;
    }

    public int getShuaxin() {
        return shuaxin;
    }

    public void setShuaxin(int shuaxin) {
        this.shuaxin = shuaxin;
    }
}
