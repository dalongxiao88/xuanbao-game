package com.tool.Stall;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Stall
{
    private int id;
    private int mapid;
    private String role;
    private BigDecimal roleid;
    private String stall;
    private int state;
    private Commodity[] goodstables;
    private Commodity[] pets;
    private List<CommodityBean> goodsList;
    private List<CommodityBean> petList;
    private List<CommodityBean> baoList;
    private List<CommodityBean> collectGoodsList;
    private List<String> sellMsgs;
    private List<String> collectMsg;
    private int x;
    private int y;
    private long commodityId;
    
    public Stall() {
        this.stall = "商店";
        this.goodsList = new ArrayList<>();
        this.petList = new ArrayList<>();
        this.baoList = new ArrayList<>();
        this.collectGoodsList = new ArrayList<>();
        this.goodstables = new Commodity[24];
        this.pets = new Commodity[5];
    }
    
    public CommodityBean getCommodityById(BigDecimal id) {
        if (id != null) {
            for (int i = 0; i < this.goodsList.size(); ++i) {
                if (((CommodityBean)this.goodsList.get(i)).getId().compareTo(id) == 0) {
                    return (CommodityBean)this.goodsList.get(i);
                }
            }
            for (int i = 0; i < this.petList.size(); ++i) {
                if (((CommodityBean)this.petList.get(i)).getId().compareTo(id) == 0) {
                    return (CommodityBean)this.petList.get(i);
                }
            }
            for (int i = 0; i < this.baoList.size(); ++i) {
                if (((CommodityBean)this.baoList.get(i)).getId().compareTo(id) == 0) {
                    return (CommodityBean)this.baoList.get(i);
                }
            }
            for (int i = 0; i < this.collectGoodsList.size(); ++i) {
                if (((CommodityBean)this.collectGoodsList.get(i)).getId().compareTo(id) == 0) {
                    return (CommodityBean)this.collectGoodsList.get(i);
                }
            }
        }
        return null;
    }
    
    public CommodityBean getCommodityByCommodityId(BigDecimal commodityId) {
        if (commodityId != null) {
            for (int i = 0; i < this.goodsList.size(); ++i) {
                if (((CommodityBean)this.goodsList.get(i)).getCommodityId().compareTo(commodityId) == 0) {
                    return (CommodityBean)this.goodsList.get(i);
                }
            }
            for (int i = 0; i < this.petList.size(); ++i) {
                if (((CommodityBean)this.petList.get(i)).getCommodityId().compareTo(commodityId) == 0) {
                    return (CommodityBean)this.petList.get(i);
                }
            }
            for (int i = 0; i < this.baoList.size(); ++i) {
                if (((CommodityBean)this.baoList.get(i)).getCommodityId().compareTo(commodityId) == 0) {
                    return (CommodityBean)this.baoList.get(i);
                }
            }
            for (int i = 0; i < this.collectGoodsList.size(); ++i) {
                if (((CommodityBean)this.collectGoodsList.get(i)).getCommodityId().compareTo(commodityId) == 0) {
                    return (CommodityBean)this.collectGoodsList.get(i);
                }
            }
        }
        return null;
    }
    
    public boolean removeCommodity(CommodityBean commodityBean) {
        if (this.goodsList.contains(commodityBean)) {
            this.goodsList.remove(commodityBean);
            return true;
        }
        if (this.petList.contains(commodityBean)) {
            this.petList.remove(commodityBean);
            return true;
        }
        if (this.baoList.contains(commodityBean)) {
            this.baoList.remove(commodityBean);
            return true;
        }
        return false;
    }
    
    public Commodity getCommodity(int type, int index) {
        if (type == 0) {
            return this.goodstables[index];
        }
        if (type == 1) {
            return this.pets[index];
        }
        return null;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Commodity[] getGoodstables() {
        return this.goodstables;
    }
    
    public void setGoodstables(Commodity[] goodstables) {
        this.goodstables = goodstables;
    }
    
    public Commodity[] getPets() {
        return this.pets;
    }
    
    public void setPets(Commodity[] pets) {
        this.pets = pets;
    }
    
    public List<CommodityBean> getGoodsList() {
        return this.goodsList;
    }
    
    public void setGoodsList(List<CommodityBean> goodsList) {
        this.goodsList = goodsList;
    }
    
    public List<CommodityBean> getPetList() {
        return this.petList;
    }
    
    public void setPetList(List<CommodityBean> petList) {
        this.petList = petList;
    }
    
    public List<CommodityBean> getBaoList() {
        return this.baoList;
    }
    
    public void setBaoList(List<CommodityBean> baoList) {
        this.baoList = baoList;
    }
    
    public List<CommodityBean> getCollectGoodsList() {
        return this.collectGoodsList;
    }
    
    public void setCollectGoodsList(List<CommodityBean> collectGoodsList) {
        this.collectGoodsList = collectGoodsList;
    }
    
    public List<String> getSellMsgs() {
        return this.sellMsgs;
    }
    
    public void setSellMsgs(List<String> sellMsgs) {
        this.sellMsgs = sellMsgs;
    }
    
    public List<String> getCollectMsg() {
        return this.collectMsg;
    }
    
    public void setCollectMsg(List<String> collectMsg) {
        this.collectMsg = collectMsg;
    }
    
    public int getMapid() {
        return this.mapid;
    }
    
    public void setMapid(int mapid) {
        this.mapid = mapid;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }
    
    public String getStall() {
        return this.stall;
    }
    
    public void setStall(String stall) {
        this.stall = stall;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public long getCommodityId() {
        return this.commodityId;
    }
    
    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }
    
    public int Buy(Commodity commodity) {
        return -1;
    }
}
