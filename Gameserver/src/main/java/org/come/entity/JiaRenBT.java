package org.come.entity;

import java.math.BigDecimal;

public class JiaRenBT {
    private Integer id;
    private Integer stallId;
    private String stallName;
    private BigDecimal goodsId;
    private String goodsName;
    private Long money;
    // 使用次数
    private Integer useTime;

    private BigDecimal acquiredId;//出售物品id
    private String acquiredName;//出售物品名字
    private Long acquiredmoney;//出售物品单价
    private Integer acquirednum;// 使用次数////出售物品数量

    //摊位所在的地图id
    private int mapId;

    private int map_x;

    private int map_y;
    //货币
    private String currency;

    private String auto_sj;

    //刷新口令
    private int shuaxin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStallId() {
        return stallId;
    }

    public void setStallId(Integer stallId) {
        this.stallId = stallId;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }


    public BigDecimal getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(BigDecimal goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }

    public BigDecimal getAcquiredId() {
        return acquiredId;
    }

    public void setAcquiredId(BigDecimal acquiredId) {
        this.acquiredId = acquiredId;
    }

    public String getAcquiredName() {
        return acquiredName;
    }

    public void setAcquiredName(String acquiredName) {
        this.acquiredName = acquiredName;
    }

    public Long getAcquiredmoney() {
        return acquiredmoney;
    }

    public void setAcquiredmoney(Long acquiredmoney) {
        this.acquiredmoney = acquiredmoney;
    }

    public Integer getAcquirednum() {
        return acquirednum;
    }

    public void setAcquirednum(Integer acquirednum) {
        this.acquirednum = acquirednum;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getMap_x() {
        return map_x;
    }

    public void setMap_x(int map_x) {
        this.map_x = map_x;
    }

    public int getMap_y() {
        return map_y;
    }

    public void setMap_y(int map_y) {
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
