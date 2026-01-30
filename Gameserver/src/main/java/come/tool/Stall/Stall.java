package come.tool.Stall;

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
    private List<CommodityBean> goodsList;
    private List<CommodityBean> petList;
    private List<CommodityBean> baoList;
    private List<CommodityBean> collectGoodsList;
    private List<String> sellMsgs;
    private List<String> collectMsg;
    private long commodityId;
    private int x;
    private int y;
    
    public Stall() {
        this.commodityId = 1L;
    }
    
    public BigDecimal getCommodityAddId() {
        if (this.commodityId >= Long.MAX_VALUE) {
            this.commodityId = 0L;
        }
        ++this.commodityId;
        return BigDecimal.valueOf(this.commodityId);
    }
    
    public static boolean updateCommodityBean(List<CommodityBean> commodityList, CommodityBean commodity) {
        if (commodity != null) {
            for (int i = 0; i < commodityList.size(); ++i) {
                if (((CommodityBean)commodityList.get(i)).getId().compareTo(commodity.getId()) == 0) {
                    commodityList.set(i, commodity);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void addCommodity(CommodityBean commodity) {
        if (commodity == null) {
            return;
        }
        if (commodity.getType() == 0) {
            synchronized (this.goodsList) {
                if (!updateCommodityBean(this.goodsList, commodity)) {
                    this.goodsList.add(commodity);
                }
            }
        }
        else if (commodity.getType() == 1) {
            synchronized (this.petList) {
                if (!updateCommodityBean(this.petList, commodity)) {
                    this.petList.add(commodity);
                }
            }
        }
        else if (commodity.getType() == 2) {
            synchronized (this.baoList) {
                if (!updateCommodityBean(this.baoList, commodity)) {
                    this.baoList.add(commodity);
                }
            }
        }
        else if (commodity.getType() == 3) {
            synchronized (this.collectGoodsList) {
                if (!updateCommodityBean(this.collectGoodsList, commodity)) {
                    this.collectGoodsList.add(commodity);
                }
            }
        }
    }
    
    public void removeCommodity(CommodityBean commodity) {
        if (commodity.getType() == 0) {
            synchronized (this.goodsList) {
                for (int i = 0; i < this.goodsList.size(); ++i) {
                    if (((CommodityBean)this.goodsList.get(i)).getId().compareTo(commodity.getId()) == 0) {
                        this.goodsList.remove(i);
                        return;
                    }
                }
            }
        }
        else if (commodity.getType() == 1) {
            synchronized (this.petList) {
                for (int i = 0; i < this.petList.size(); ++i) {
                    if (((CommodityBean)this.petList.get(i)).getId().compareTo(commodity.getId()) == 0) {
                        this.petList.remove(i);
                        return;
                    }
                }
            }
        }
        else if (commodity.getType() == 2) {
            synchronized (this.baoList) {
                for (int i = 0; i < this.baoList.size(); ++i) {
                    if (((CommodityBean)this.baoList.get(i)).getId().compareTo(commodity.getId()) == 0) {
                        this.baoList.remove(i);
                        return;
                    }
                }
            }
        }
        else if (commodity.getType() == 3) {
            synchronized (this.collectGoodsList) {
                for (int i = 0; i < this.collectGoodsList.size(); ++i) {
                    if (((CommodityBean)this.collectGoodsList.get(i)).getId().compareTo(commodity.getId()) == 0) {
                        this.collectGoodsList.remove(i);
                        return;
                    }
                }
            }
        }
    }
    
    public CommodityBean getCommodity(CommodityBean commodity) {
        if (commodity.getType() == 0) {
            return this.getGoods(commodity.getCommodityId());
        }
        if (commodity.getType() == 1) {
            return this.getGoods(commodity.getCommodityId());
        }
        if (commodity.getType() == 2) {
            return this.getGoods(commodity.getCommodityId());
        }
        if (commodity.getType() == 3) {
            return this.getCollectGoods(commodity.getCommodityId());
        }
        return null;
    }
    
    public CommodityBean getGoods(BigDecimal rgid) {
        synchronized (this.goodsList) {
            for (int i = 0; i < this.goodsList.size(); ++i) {
                if (this.goodsList.get(i) != null && ((CommodityBean)this.goodsList.get(i)).getCommodityId().compareTo(rgid) == 0) {
                    return (CommodityBean)this.goodsList.get(i);
                }
            }
        }
        return null;
    }
    
    public CommodityBean getPet(BigDecimal rgid) {
        synchronized (this.petList) {
            for (int i = 0; i < this.petList.size(); ++i) {
                if (this.petList.get(i) != null && ((CommodityBean)this.petList.get(i)).getCommodityId().compareTo(rgid) == 0) {
                    return (CommodityBean)this.petList.get(i);
                }
            }
        }
        return null;
    }
    
    public CommodityBean getBao(BigDecimal rgid) {
        synchronized (this.baoList) {
            for (int i = 0; i < this.baoList.size(); ++i) {
                if (this.baoList.get(i) != null && ((CommodityBean)this.baoList.get(i)).getCommodityId().compareTo(rgid) == 0) {
                    return (CommodityBean)this.baoList.get(i);
                }
            }
        }
        return null;
    }
    
    public CommodityBean getCollectGoods(BigDecimal rgid) {
        synchronized (this.collectGoodsList) {
            for (int i = 0; i < this.collectGoodsList.size(); ++i) {
                if (this.collectGoodsList.get(i) != null && ((CommodityBean)this.collectGoodsList.get(i)).getCommodityId().compareTo(rgid) == 0) {
                    return (CommodityBean)this.collectGoodsList.get(i);
                }
            }
        }
        return null;
    }
    
    public void removeGoods(CommodityBean commodity) {
        synchronized (this.goodsList) {
            for (int i = 0; i < this.goodsList.size(); ++i) {
                if (this.goodsList.get(i) != null && this.goodsList.get(i) == commodity) {
                    this.goodsList.remove(commodity);
                    return;
                }
            }
        }
    }
    
    public void removePet(CommodityBean commodity) {
        synchronized (this.petList) {
            for (int i = 0; i < this.petList.size(); ++i) {
                if (this.petList.get(i) != null && this.petList.get(i) == commodity) {
                    this.petList.remove(commodity);
                    return;
                }
            }
        }
    }
    
    public void removeBao(CommodityBean commodity) {
        synchronized (this.baoList) {
            for (int i = 0; i < this.baoList.size(); ++i) {
                if (this.baoList.get(i) != null && this.baoList.get(i) == commodity) {
                    this.baoList.remove(commodity);
                    return;
                }
            }
        }
    }
    
    public void removeCollectGoodsList(CommodityBean commodity) {
        synchronized (this.collectGoodsList) {
            for (int i = 0; i < this.collectGoodsList.size(); ++i) {
                if (this.collectGoodsList.get(i) != null && this.collectGoodsList.get(i) == commodity) {
                    this.collectGoodsList.remove(commodity);
                    return;
                }
            }
        }
    }
    
    public synchronized void addSellMsg(String msg) {
        if (this.sellMsgs == null) {
            this.sellMsgs = new ArrayList<>();
        }
        this.sellMsgs.add(msg);
    }
    
    public synchronized void addCollectMsg(String msg) {
        if (this.collectMsg == null) {
            this.collectMsg = new ArrayList<>();
        }
        this.collectMsg.add(msg);
    }
    
    public boolean pointContains(int mapId, int x, int y) {
        return this.mapid == mapId && (this.x == x & this.y == y);
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
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
}
