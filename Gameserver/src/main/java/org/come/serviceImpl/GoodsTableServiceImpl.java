package org.come.serviceImpl;

import org.come.entity.GoodsbuyrecordEntity;
import org.come.entity.GoodssaledayrecordEntity;
import org.come.entity.ShangchengshopEntity;
import com.github.pagehelper.BasePageHelper;
import org.come.bean.Goodsbuyrecordsumbean;
import org.come.action.monitor.MonitorUtil;
import org.come.tool.Goodtype;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.redis.RedisCacheUtil;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.entity.Goodstable;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.GoodstableMapper;
import org.come.service.IGoodsTableService;

public class GoodsTableServiceImpl implements IGoodsTableService
{
    private GoodstableMapper goodstableMapper;
    
    public GoodsTableServiceImpl() {
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.goodstableMapper = (GoodstableMapper)ctx.getBean("goodstableMapper");
    }
    
    @Override
    public List<Goodstable> getGoodsByRoleID(BigDecimal role_id) {
        List<Goodstable> list = RedisControl.getS(RedisParameterUtil.GOODS, role_id.toString(), Goodstable.class);
        return list;
    }
    
    @Override
    public void insertGoods(Goodstable goodstable) {
        goodstable.setRgid(RedisCacheUtil.getGoods_pk());
        goodstable.setCreateTime(Long.valueOf(System.currentTimeMillis()));
        if (goodstable.getUsetime() == null) {
            goodstable.setUsetime(Integer.valueOf(1));
        }
        if (goodstable.getStatus() == null) {
            goodstable.setStatus(Integer.valueOf(0));
        }
        RedisControl.insertKeyT(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), goodstable);
        RedisControl.insertController(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), "1");
        RedisControl.insertListRedis(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), goodstable.getRgid().toString());
        RedisControl.insertListRedis(RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString(), goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
        RedisControl.insertListRedis(RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString(), goodstable.getStatus().toString(), goodstable.getRgid().toString());
        if ((int)goodstable.getStatus() == 0 && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
            RoleData data = RolePool.getRoleData(goodstable.getRole_id());
            if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                data.upGoodNum(1);
            }
        }
    }
    
    @Override
    public void updateGoodsIndex(Goodstable goodstable, BigDecimal role_id, BigDecimal goodsid, Integer status) {
        BigDecimal yrid = goodstable.getRole_id();
        BigDecimal ygid = goodstable.getGoodsid();
        Integer yss = goodstable.getStatus();
        BigDecimal nrid = (role_id != null) ? role_id : yrid;
        BigDecimal ngid = (goodsid != null) ? goodsid : ygid;
        Integer nss = (status != null) ? status : yss;
        goodstable.setRole_id(nrid);
        goodstable.setGoodsid(ngid);
        goodstable.setStatus(nss);
        RedisControl.insertKeyT(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), goodstable);
        RedisControl.insertController(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), "2");
        if (nrid != null && nrid.compareTo(yrid) != 0) {
            RedisControl.deletrValue(RedisParameterUtil.GOODS, yrid.toString(), goodstable.getRgid().toString());
            RedisControl.insertListRedis(RedisParameterUtil.GOODS, nrid.toString(), goodstable.getRgid().toString());
            RedisControl.deletrValue(RedisParameterUtil.GOODSID + "_" + yrid, ygid.toString(), goodstable.getRgid().toString());
            RedisControl.insertListRedis(RedisParameterUtil.GOODSID + "_" + nrid, ngid.toString(), goodstable.getRgid().toString());
            RedisControl.deletrValue(RedisParameterUtil.GOODSST + "_" + yrid, yss.toString(), goodstable.getRgid().toString());
            RedisControl.insertListRedis(RedisParameterUtil.GOODSST + "_" + nrid, nss.toString(), goodstable.getRgid().toString());
            if (((int)nss == 0 || (int)yss == 0) && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                RoleData data = RolePool.getRoleData(yrid);
                if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                    data.upGoodNum(-1);
                }
                data = RolePool.getRoleData(nrid);
                if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                    data.upGoodNum(1);
                }
            }
        }
        else {
            if (ngid != null && ngid.compareTo(ygid) != 0) {
                String key = RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString();
                RedisControl.deletrValue(key, ygid.toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(key, ngid.toString(), goodstable.getRgid().toString());
            }
            if (nss != null && nss != yss) {
                String key = RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString();
                RedisControl.deletrValue(key, yss.toString(), goodstable.getRgid().toString());
                RedisControl.insertListRedis(key, nss.toString(), goodstable.getRgid().toString());
                if ((int)nss == 1 || (int)yss == 1) {
                    int type = Goodtype.EquipmentType(goodstable.getType());
                    if (type != -1) {
                        RoleData data2 = RolePool.getRoleData(goodstable.getRole_id());
                        if (data2 != null) {
                            data2.CEquip(goodstable.getRgid(), type, (int)nss == 1);
                        }
                    }
                }
                if (((int)nss == 0 || (int)yss == 0) && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                    RoleData data3 = RolePool.getRoleData(goodstable.getRole_id());
                    if (data3 != null && data3.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                        data3.upGoodNum(((int)yss == 0) ? -1 : 1);
                    }
                }
            }
        }
    }
    
    @Override
    public String updateGoodsNum(Goodstable goodstable, int I) {
        Goodstable good = (Goodstable)RedisControl.getV(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), Goodstable.class);
        if (I == 2 && good.getType() != 80156L && good.getType() != 2012L && good.getType() != 729L && good.getType() != 2010L && good.getType() != 2011L && good.getType() != 2125L) {
            I = 1;
        }
        String monitor = MonitorUtil.isGoodMonitor(good, goodstable, I);
        if (monitor != null) {
            return monitor;
        }
        Integer yss = good.getStatus();
        Integer nss = goodstable.getStatus();
        int nUse = (int)goodstable.getUsetime();
        if (nUse <= 0) {
            RedisControl.deletrValue(RedisParameterUtil.GOODS, good.getRole_id().toString(), good.getRgid().toString());
            String key = RedisParameterUtil.GOODSID + "_" + good.getRole_id().toString();
            RedisControl.deletrValue(key, good.getGoodsid().toString(), good.getRgid().toString());
            key = RedisParameterUtil.GOODSST + "_" + good.getRole_id().toString();
            RedisControl.deletrValue(key, good.getStatus().toString(), good.getRgid().toString());
            RedisControl.delForKey(RedisParameterUtil.GOODS, good.getRgid().toString());
            RedisControl.insertController(RedisParameterUtil.GOODS, good.getRgid().toString(), "3");
            if ((int)yss == 0 && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                RoleData data = RolePool.getRoleData(good.getRole_id());
                if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                    data.upGoodNum(-1);
                }
            }
            return null;
        }
        else {
            good.setUsetime(Integer.valueOf(nUse));
            good.setStatus(nss);
            good.setGoodlock(goodstable.getGoodlock());
            if (I == 2) {
                good.setGoodsname(goodstable.getGoodsname());
                good.setValue(goodstable.getValue());
                good.setSkin(goodstable.getSkin());
            }
            RedisControl.insertKeyT(RedisParameterUtil.GOODS, good.getRgid().toString(), good);
            RedisControl.insertController(RedisParameterUtil.GOODS, good.getRgid().toString(), "2");
            if (yss != nss) {
                String key = RedisParameterUtil.GOODSST + "_" + good.getRole_id().toString();
                RedisControl.deletrValue(key, yss.toString(), good.getRgid().toString());
                RedisControl.insertListRedis(key, nss.toString(), good.getRgid().toString());
                if ((int)nss == 1 || (int)yss == 1) {
                    int type = Goodtype.EquipmentType(good.getType());
                    if (type != -1) {
                        RoleData data2 = RolePool.getRoleData(good.getRole_id());
                        if (data2 != null) {
                            data2.CEquip(good.getRgid(), type, (int)nss == 1);
                        }
                    }
                }
                if (((int)nss == 0 || (int)yss == 0) && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                    RoleData data = RolePool.getRoleData(good.getRole_id());
                    if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                        data.upGoodNum(((int)yss == 0) ? -1 : 1);
                    }
                }
            }
            return null;
        }
    }
    
    @Override
    public void deleteGoodsByRgid(BigDecimal rgid) {
        Goodstable goodstable = (Goodstable)RedisControl.getV(RedisParameterUtil.GOODS, rgid.toString(), Goodstable.class);
        if (goodstable != null) {
            RedisControl.deletrValue(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), rgid.toString());
            String key = RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString();
            RedisControl.deletrValue(key, goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
            key = RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString();
            RedisControl.deletrValue(key, goodstable.getStatus().toString(), goodstable.getRgid().toString());
            if ((int)goodstable.getStatus() == 0 && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                RoleData data = RolePool.getRoleData(goodstable.getRole_id());
                if (data != null && data.getLoginResult().isSuitEquipment(goodstable.getRgid())) {
                    data.upGoodNum(-1);
                }
            }
        }
        RedisControl.delForKey(RedisParameterUtil.GOODS, rgid.toString());
        RedisControl.insertController(RedisParameterUtil.GOODS, rgid.toString(), "3");
    }
    
    @Override
    public void updateGoodRedis(Goodstable goodstable) {
        if ((int)goodstable.getUsetime() > 0) {
            RedisControl.insertKeyT(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), goodstable);
            RedisControl.insertController(RedisParameterUtil.GOODS, goodstable.getRgid().toString(), "2");
        }
        else {
            BigDecimal rgid = goodstable.getRgid();
            RedisControl.deletrValue(RedisParameterUtil.GOODS, goodstable.getRole_id().toString(), rgid.toString());
            String key = RedisParameterUtil.GOODSID + "_" + goodstable.getRole_id().toString();
            RedisControl.deletrValue(key, goodstable.getGoodsid().toString(), goodstable.getRgid().toString());
            key = RedisParameterUtil.GOODSST + "_" + goodstable.getRole_id().toString();
            RedisControl.deletrValue(key, goodstable.getStatus().toString(), goodstable.getRgid().toString());
            RedisControl.delForKey(RedisParameterUtil.GOODS, rgid.toString());
            RedisControl.insertController(RedisParameterUtil.GOODS, rgid.toString(), "3");
            if ((int)goodstable.getStatus() == 0 && goodstable.getType() != 8888L && goodstable.getType() != 8003L) {
                RoleData data = RolePool.getRoleData(goodstable.getRole_id());
                if (data != null && data.getLoginResult().isSuitEquipment(rgid)) {
                    data.upGoodNum(-1);
                }
            }
        }
    }
    
    @Override
    public Goodstable getGoodsByRgID(BigDecimal rgid) {
        Goodstable goodstable = (Goodstable)RedisControl.getV(RedisParameterUtil.GOODS, rgid.toString(), Goodstable.class);
        return goodstable;
    }
    
    @Override
    public List<Goodstable> selectGoodsByRoleIDAndGoodsID(BigDecimal roleid, BigDecimal goodsid) {
        String key = RedisParameterUtil.GOODS + "_" + roleid.toString();
        String key2 = RedisParameterUtil.GOODSID + "_" + roleid.toString() + "_" + goodsid.toString();
        String key3 = RedisParameterUtil.GOODSST + "_" + roleid.toString() + "_0";
        List<Goodstable> goodstables = RedisControl.getS(RedisParameterUtil.GOODS, RedisControl.sinterJiao(new String[] { key2, key3, key }), Goodstable.class);
        return goodstables;
    }
    
    @Override
    public List<Goodstable> selectGoodsByRoleIDAndGoodsIDAndState(BigDecimal roleid, BigDecimal goodsid, int state) {
        String key = RedisParameterUtil.GOODS + "_" + roleid.toString();
        String key2 = RedisParameterUtil.GOODSID + "_" + roleid.toString() + "_" + goodsid.toString();
        String key3 = RedisParameterUtil.GOODSST + "_" + roleid.toString() + "_" + state;
        List<Goodstable> goodstables = RedisControl.getS(RedisParameterUtil.GOODS, RedisControl.sinterJiao(new String[] { key2, key3, key }), Goodstable.class);
        return goodstables;
    }
    
    @Override
    public List<Goodstable> getAllGoods() {
        return this.goodstableMapper.getAllGoods();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.goodstableMapper.selectMaxID();
    }
    
    @Override
    public void insertGoodssql(Goodstable goodstable) {
        this.goodstableMapper.insertGoods(goodstable);
    }
    
    @Override
    public void updateGoodssql(Goodstable goodstable) {
        this.goodstableMapper.updateGoods(goodstable);
    }
    
    @Override
    public void deleteGoodsByRgidsql(BigDecimal rgid) {
        this.goodstableMapper.deleteGoodsByRgid(rgid);
    }
    
    @Override
    public void insertGoodssqlS(List<Goodstable> goods) {
        this.goodstableMapper.insertGoodssqlS(goods);
    }
    
    @Override
    public List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy(String time, String goodsname, int pageNum, String type) {
        BasePageHelper.startPage(pageNum, 10);
        return this.goodstableMapper.selectXianYuGoodsbuy(time, goodsname, type);
    }
    
    @Override
    public List<Goodsbuyrecordsumbean> selectXianYuGoodsbuyZhuZhuangTu(BigDecimal gid) {
        return this.goodstableMapper.selectXianYuGoodsbuyZhuZhuangTu(gid);
    }
    
    @Override
    public List<ShangchengshopEntity> selectShangChengShopList(String goodsid, String goodsname, int page) {
        BasePageHelper.startPage(page, 10);
        return this.goodstableMapper.selectShangChengShopList(goodsid, goodsname);
    }
    
    @Override
    public int updateShangChengShop(ShangchengshopEntity shangchengshopEntity) {
        return this.goodstableMapper.updateShangChengShop(shangchengshopEntity);
    }
    
    @Override
    public int deleteShangChengShop(ShangchengshopEntity shangchengshopEntity) {
        return this.goodstableMapper.deleteShangChengShop(shangchengshopEntity);
    }
    
    @Override
    public int addShangChengShop(ShangchengshopEntity shangchengshopEntity) {
        return this.goodstableMapper.addShangChengShop(shangchengshopEntity);
    }
    
    @Override
    public List<GoodssaledayrecordEntity> selectGoodsBuyRecordSumList() {
        return this.goodstableMapper.selectGoodsBuyRecordSumList();
    }
    
    @Override
    public int addGoodssaledayrecord(GoodssaledayrecordEntity goodssaledayrecordEntity) {
        return this.goodstableMapper.addGoodssaledayrecord(goodssaledayrecordEntity);
    }
    
    @Override
    public int addGoodsBuyRecord(GoodsbuyrecordEntity goodsBuy) {
        return this.goodstableMapper.addGoodsBuyRecord(goodsBuy);
    }
    
    @Override
    public int updateGoodssqlS(List<Goodstable> list) {
        return this.goodstableMapper.updateGoodsList(list);
    }
    
    @Override
    public void deleteGoodsByRgidsqlS(List<BigDecimal> rgid) {
        this.goodstableMapper.deleteGoodsByRgids(rgid);
    }
    
    @Override
    public Goodstable getGoodsByHashKey(String key) {
        Goodstable goodstable = (Goodstable)RedisControl.getV(RedisParameterUtil.GOODS, key, Goodstable.class);
        return goodstable;
    }
}
