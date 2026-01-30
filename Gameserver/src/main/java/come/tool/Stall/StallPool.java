package come.tool.Stall;

import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import org.come.bean.StallPurchase;
import org.apache.commons.lang.StringUtils;
import java.util.Iterator;

import org.come.jiaren.People;
import org.come.tool.EquipTool;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.entity.Record;
import come.tool.Mixdeal.AnalysisString;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.until.GsonUtil;
import org.come.handler.SendMessage;
import org.come.until.StringUtil;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StallPool
{
    private static double TaxXs;
    public static int PREPARE;
    public static int NO;
    public static int MANAGE;
    public static int OFF;
    private static StallPool pool;
    private int increasesum;
    private ConcurrentHashMap<Integer, Stall> allStall;
    private ConcurrentHashMap<Integer, StallBean> allStallBean;
    private ConcurrentHashMap<Integer, List<StallBean>> mapStallBean;
    
    public static StallPool getPool() {
        if (StallPool.pool == null) {
            StallPool.pool = new StallPool();
        }
        return StallPool.pool;
    }
    
    public StallPool() {
        this.increasesum = 100;
        this.allStall = new ConcurrentHashMap<>();
        this.allStallBean = new ConcurrentHashMap<>();
        this.mapStallBean = new ConcurrentHashMap<>();
    }
    
    public synchronized List<StallBean> getmap(int mapid) {
        List<StallBean> beans = (List<StallBean>)this.mapStallBean.get(Integer.valueOf(mapid));
        if (beans == null) {
            beans = new ArrayList<>();
            this.mapStallBean.put(Integer.valueOf(mapid), beans);
        }
        return beans;
    }
    
    public StallBean addStall(Stall stall, ChannelHandlerContext ctx) {
        stall.setId(this.getIncreasesum());
        this.allStall.put(Integer.valueOf(stall.getId()), stall);
        StallBean bean = new StallBean(stall);
        this.allStallBean.put(Integer.valueOf(bean.getId()), bean);
        this.getmap(bean.getMapid()).add(bean);
        this.updatePurchaseState(stall);
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
        List<CommodityBean> goodsCommoditys = stall.getGoodsList();
        if (goodsCommoditys != null) {
            for (int i = goodsCommoditys.size() - 1; i >= 0; --i) {
                CommodityBean commodity = (CommodityBean)goodsCommoditys.get(i);
                Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(commodity.getCommodityId());
                if (goods != null && (int)goods.getStatus() == 0 && (int)goods.getStatus() == 0 && goods.getGoodlock() == 0) {
                    goods.setCommodityId(commodity.getId());
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                    assetUpdate.updata(this.getDataInfo(goods, (int)goods.getUsetime()));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你把#G" + goods.getGoodsname() + "#Y上架了，单价为" + StringUtil.toMoneyString(commodity.getMoney()) + "#Y两"));
                }
                else {
                    goodsCommoditys.remove(i);
                }
            }
        }
        List<CommodityBean> petCommoditys = stall.getPetList();
        if (petCommoditys != null) {
            for (int j = petCommoditys.size() - 1; j >= 0; --j) {
                CommodityBean commodity2 = (CommodityBean)petCommoditys.get(j);
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodity2.getCommodityId());
                if (pet != null && pet.getRoleid().compareTo(stall.getRoleid()) == 0 && pet.getPetlock() == 0 && (pet.getDeposit() == null || (int)pet.getDeposit() == 0)) {
                    pet.setCommodityId(commodity2.getId());
                    AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                    assetUpdate.updata(this.getDataInfo(pet, 1));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W你把召唤兽#G" + pet.getSummoningname() + "#W上架了，单价为" + StringUtil.toMoneyString(commodity2.getMoney()) + "#W两"));
                }
                else {
                    petCommoditys.remove(j);
                }
            }
        }
        List<CommodityBean> baoCommoditys = stall.getBaoList();
        if (baoCommoditys != null) {
            for (int k = baoCommoditys.size() - 1; k >= 0; --k) {
                CommodityBean commodity3 = (CommodityBean)baoCommoditys.get(k);
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodity3.getCommodityId());
                if (lingbao != null && lingbao.getRoleid().compareTo(stall.getRoleid()) == 0) {
                    lingbao.setCommodityId(commodity3.getId());
                    AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                    assetUpdate.updata(this.getDataInfo(lingbao, 1));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("#W你把灵宝#G" + lingbao.getBaoname() + "#W上架了，单价为" + StringUtil.toMoneyString(commodity3.getMoney()) + "#W两"));
                }
                else {
                    baoCommoditys.remove(k);
                }
            }
        }
        List<CommodityBean> collectGoodsList = stall.getCollectGoodsList();
        if (collectGoodsList != null) {
            for (int l = 0; l < collectGoodsList.size(); ++l) {
                CommodityBean commodity4 = (CommodityBean)collectGoodsList.get(l);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你要收购的物品#G" + commodity4.getCommodityName() + "#Y上架了，收购单价为" + StringUtil.toMoneyString(commodity4.getMoney()) + "#G两"));
            }
        }
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        return bean;
    }
    
    public void addCommodity(ChannelHandlerContext ctx, CommodityBean commodity, int id) {
        Stall stall = (Stall)this.allStall.get(Integer.valueOf(id));
        try {
            CommodityBean commodityBean = stall.getCommodity(commodity);
            if (commodityBean != null) {
                commodityBean.setSum(commodity.getSum());
            }
            else {
                commodityBean = commodity;
                commodityBean.setId(stall.getCommodityAddId());
            }
            synchronized (commodityBean.getLock()) {
                if (commodityBean.getSum() > 0) {
                    AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
                    if (commodityBean.getType() == 0) {
                        Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(commodityBean.getCommodityId());
                        if (goods != null && goods.getRole_id().compareTo(stall.getRoleid()) == 0 && (int)goods.getUsetime() >= commodityBean.getSum()) {
                            if (!AnalysisString.jiaoyi((long)goods.getQuality())) {
                                goods.setCommodityId(commodityBean.getId());
                                AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                                stall.addCommodity(commodityBean);
                                assetUpdate.updata(this.getDataInfo(goods, (int)goods.getUsetime()));
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你把#G" + goods.getGoodsname() + "#Y上架了，单价为" + StringUtil.toMoneyString(commodityBean.getMoney()) + "#Y两"));
                                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                                SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                            }
                            else {
                                StringBuffer buffer = new StringBuffer();
                                buffer.append(stall.getRoleid());
                                buffer.append("欲摆摊禁交易物品:");
                                buffer.append(goods.getRgid());
                                buffer.append(":");
                                buffer.append(goods.getGoodsname());
                                AllServiceUtil.getRecordService().insert(new Record(5, "角色Id" + stall.getRoleid() + "欲摆摊禁交易物品ID:" + goods.getRgid() + "物品名" + goods.getGoodsname()));
                            }
                        }
                    }
                    else if (commodityBean.getType() == 1) {
                        RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodityBean.getCommodityId());
                        if (pet != null && pet.getRoleid().compareTo(stall.getRoleid()) == 0) {
                            pet.setCommodityId(commodityBean.getId());
                            AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                            stall.addCommodity(commodityBean);
                            assetUpdate.updata(this.getDataInfo(pet, 1));
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你把召唤兽#G" + pet.getSummoningname() + "#Y上架了，单价为" + StringUtil.toMoneyString(commodityBean.getMoney()) + "#Y两"));
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                            SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                        }
                    }
                    else if (commodityBean.getType() == 2) {
                        Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodityBean.getCommodityId());
                        if (lingbao != null && lingbao.getRoleid().compareTo(stall.getRoleid()) == 0) {
                            lingbao.setCommodityId(commodityBean.getId());
                            AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                            stall.addCommodity(commodityBean);
                            assetUpdate.updata(this.getDataInfo(lingbao, 1));
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你把灵宝#G" + lingbao.getBaoname() + "#Y上架了，单价为" + StringUtil.toMoneyString(commodityBean.getMoney()) + "#Y两"));
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                            SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                        }
                    }
                    else if (commodityBean.getType() == 3) {
                        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                        BigDecimal totalPrice = commodityBean.getMoney().multiply(BigDecimal.valueOf((long)commodityBean.getSum()));
                        if (totalPrice.compareTo(loginResult.getGold()) > 0) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("余额不足"));
                            return;
                        }
                        stall.addCommodity(commodityBean);
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你要收购的物品#G" + commodityBean.getCommodityName() + "#Y上架了，收购单价为" + StringUtil.toMoneyString(commodity.getMoney()) + "#G两"));
                        SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.updatePurchaseState(stall);
    }
    
    public void removeCommodity(ChannelHandlerContext ctx, CommodityBean commodity, int id) {
        Stall stall = (Stall)this.allStall.get(Integer.valueOf(id));
        try {
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
            if (commodity.getType() == 0) {
                Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(commodity.getCommodityId());
                goods.setCommodityId(null);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                stall.removeCommodity(commodity);
                assetUpdate.updata(this.getDataInfo(goods, (int)goods.getUsetime()));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的#G" + goods.getGoodsname() + "#Y下架了"));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else if (commodity.getType() == 1) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodity.getCommodityId());
                pet.setCommodityId(null);
                AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                stall.removeCommodity(commodity);
                assetUpdate.updata(this.getDataInfo(pet, 1));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的" + pet.getSummoningname() + "#Y下架了"));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else if (commodity.getType() == 2) {
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodity.getCommodityId());
                lingbao.setCommodityId(null);
                AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                stall.removeCommodity(commodity);
                assetUpdate.updata(this.getDataInfo(lingbao, 1));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的#G" + lingbao.getBaoname() + "#Y下架了"));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else if (commodity.getType() == 3) {
                stall.removeCommodity(commodity);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你停止收购了#G" + commodity.getCommodityName()));
                SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.updatePurchaseState(stall);
    }
    
    public synchronized int getIncreasesum() {
        ++this.increasesum;
        if (this.increasesum > 999999) {
            this.increasesum = 100;
        }
        return this.increasesum;
    }
    
    public void updateStall(Stall stall) {
        if (stall == null) {
            return;
        }
        Stall currentStall = (Stall)this.allStall.get(Integer.valueOf(stall.getId()));
        StallBean bean = (StallBean)this.allStallBean.get(Integer.valueOf(stall.getId()));
        currentStall.setStall(stall.getStall());
        bean.setStall(stall.getStall());
        SendMessage.sendMessageToMapRoles(Long.valueOf((long)stall.getMapid()), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(currentStall)));
        SendMessage.sendMessageToMapRoles(Long.valueOf((long)stall.getMapid()), Agreement.getAgreement().stallstateAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
    }
    
    public boolean updateState(BigDecimal booth_id, int state, BigDecimal roleid) {
        if (booth_id == null) {
            return false;
        }
        int id = booth_id.intValue();
        Stall stall = (Stall)this.allStall.get(Integer.valueOf(id));
        StallBean bean = (StallBean)this.allStallBean.get(Integer.valueOf(id));
        if (bean == null || stall == null || stall.getRoleid().compareTo(roleid) != 0) {
            return false;
        }
        stall.setState(state);
        bean.setState(state);
        if (state == StallPool.OFF) {
            this.allStall.remove(Integer.valueOf(id));
            List<StallBean> list = this.getmap(stall.getMapid());
            list.remove(this.allStallBean.remove(Integer.valueOf(id)));
        }
        SendMessage.sendMessageToMapRoles(new Long((long)bean.getMapid()), Agreement.getAgreement().stallstateAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        return true;
    }
    
    public void updatePurchaseState(Stall stall) {
        StallBean bean = (StallBean)this.allStallBean.get(Integer.valueOf(stall.getId()));
        if (stall.getCollectGoodsList() != null && stall.getCollectGoodsList().size() > 0) {
            bean.setPurchase(true);
        }
        else {
            bean.setPurchase(false);
        }
        SendMessage.sendMessageToMapRoles(new Long((long)bean.getMapid()), Agreement.getAgreement().stallstateAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
    }
    
    public void BuyStall(LoginResult loginResult, StallBuy buy) {
        Stall stall = (Stall)this.allStall.get(Integer.valueOf(buy.getId()));
        if (stall == null) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("该摊位已经不存在了"));
            return;
        }
        if (stall.getRoleid().compareTo(loginResult.getRole_id()) == 0) {
            SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().PromptAgreement("不能向自己出售物品！"));
            return;
        }
        switch (buy.getType()) {
            case 0: {
                this.BuyGood(loginResult, buy, stall);
                break;
            }
            case 1: {
                this.BuyPet(loginResult, buy, stall);
                break;
            }
            case 2: {
                this.BuyLing(loginResult, buy, stall);
                break;
            }
            case 3: {
                if (this.sellGoods(loginResult, buy, stall)) {
                    Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(buy.getCommodityId());
                    if (goods != null) {
                        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
                        assetUpdate.updata("G" + goods.getRgid() + "=" + goods.getUsetime());
                        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
    }
    
    public void BuyGood(LoginResult buyRole, StallBuy buy, Stall stall) {
        CommodityBean commodity = stall.getGoods(buy.getBuyid());
        if (buy.getSum() <= 0) {
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("购买数量不能为0"));
            return;
        }
        if (commodity == null) {
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了或者数量不足"));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            return;
        }
        synchronized (commodity.getLock()) {
            Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(commodity.getCommodityId());
            if (goods == null) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了或者数量不足"));
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                return;
            }
            int buySum = buy.getSum();
            buySum = ((commodity.getSum() < buySum) ? commodity.getSum() : buySum);
            buySum = (((int)goods.getUsetime() < buySum) ? ((int)goods.getUsetime()) : buySum);
            if (goods.getCommodityId() == null || goods.getRole_id().compareTo(stall.getRoleid()) != 0 || buySum < 1) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了或者数量不足"));
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                return;
            }
            BigDecimal price = commodity.getMoney().multiply(BigDecimal.valueOf((long)buySum));
            if (price.compareTo(buyRole.getGold()) > 0) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足"));
                return;
            }
            buyRole.setGold(buyRole.getGold().subtract(price));
            BigDecimal s = price.multiply(BigDecimal.valueOf(StallPool.TaxXs)).setScale(0, 4);
            MonitorUtil.getStallM().add(s.longValue());
            MonitorUtil.getMoney().useD(s.longValue());
            boolean add = true;
            boolean fold = EquipTool.canSuper(goods.getType());
            commodity.setSum(commodity.getSum() - buySum);
            goods.setUsetime(Integer.valueOf((int)goods.getUsetime() - buySum));
            Goodstable good = goods.clone();
            good.setCommodityId(null);
            good.setUsetime(Integer.valueOf(buySum));
            if (commodity.getSum() <= 0 || !fold) {
                goods.setCommodityId(null);
                stall.removeGoods(commodity);
                add = false;
            }
            AllServiceUtil.getGoodsrecordService().insert(goods, buyRole.getRole_id(), Integer.valueOf(buySum), Integer.valueOf(1));
            if (fold) {
                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(buyRole.getRole_id(), good.getGoodsid());
                if (sameGoodstable.size() != 0) {
                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + (int)good.getUsetime()));
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                    good = (Goodstable)sameGoodstable.get(0);
                }
                else if (add) {
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                    good.setRole_id(buyRole.getRole_id());
                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                }
                else {
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, buyRole.getRole_id(), null, null);
                }
            }
            else {
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, buyRole.getRole_id(), null, null);
            }
            StringBuffer msg = new StringBuffer();
            msg.append("#Y玩家").append("#R").append(buyRole.getRolename()).append("#Y花费").append(StringUtil.toMoneyString(price));
            msg.append("#Y购买了").append("#R").append(buySum).append("#Y个").append("#G").append(good.getGoodsname());
            stall.addSellMsg(msg.toString());
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLGET);
            assetUpdate.setMsg(buySum + "个" + good.getGoodsname());
            LoginResult loginResult = null;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(stall.getRole());
            if (ctx != null) {
                loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            }
            if (loginResult != null) {
                assetUpdate.updata(this.getDataInfo(goods, (int)goods.getUsetime()));
                assetUpdate.updata("D=" + price.subtract(s).longValue());
                loginResult.setGold(loginResult.getGold().add(price).subtract(s));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else {
                BigDecimal gold = AllServiceUtil.getRoleTableService().selectMoneyRoleID(stall.getRoleid());
                if (gold == null) {
                    gold = BigDecimal.ZERO;
                }
                gold = gold.add(price.subtract(s));
                AllServiceUtil.getRoleTableService().updateMoneyRoleID(stall.getRoleid(), gold);
            }
            if (MonitorUtil.isUpMoney(3, price.longValue())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("摆摊大宗金额流动:");
                buffer.append(stall.getRoleid());
                buffer.append("卖给");
                buffer.append(buyRole.getRole_id());
                buffer.append(buySum + "个" + good.getGoodsname());
                buffer.append("物品id:" + good.getRgid());
                buffer.append("金额");
                buffer.append(price);
                AllServiceUtil.getRecordService().insert(new Record(3, buffer.toString()));
            }
            assetUpdate.setType(AssetUpdate.STALLBUY);
            assetUpdate.setData(null);
            assetUpdate.updata("D=" + -price.longValue());
            assetUpdate.setGood(good);
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        }
    }
    
    public void BuyPet(LoginResult buyRole, StallBuy buy, Stall stall) {
        CommodityBean commodity = stall.getPet(buy.getBuyid());
        if (commodity == null) {
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了"));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            return;
        }
        synchronized (commodity.getLock()) {
            RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodity.getCommodityId());
            if (pet == null || pet.getCommodityId() == null || pet.getRoleid().compareTo(stall.getRoleid()) != 0) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了"));
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                return;
            }
            BigDecimal price = commodity.getMoney();
            if (price.compareTo(buyRole.getGold()) > 0) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足"));
                return;
            }
            buyRole.setGold(buyRole.getGold().subtract(price));
            BigDecimal s = price.multiply(BigDecimal.valueOf(StallPool.TaxXs)).setScale(0, 4);
            MonitorUtil.getStallM().add(s.longValue());
            MonitorUtil.getMoney().useD(s.longValue());
            pet.setFriendliness(Long.valueOf(0L));
            pet.setCommodityId(null);
            stall.removePet(commodity);
            AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, buyRole.getRole_id());
            StringBuffer msg = new StringBuffer();
            msg.append("#Y玩家").append("#R").append(buyRole.getRolename()).append("#Y花费").append(StringUtil.toMoneyString(price));
            msg.append("#Y购买了").append("#G").append(pet.getSummoningname());
            stall.addSellMsg(msg.toString());
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLGET);
            List<BigDecimal> goods = pet.getGoods();
            if (goods != null) {
                for (BigDecimal v : goods) {
                    Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(v);
                    if (good != null) {
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, buyRole.getRole_id(), null, null);
                        assetUpdate.setGood(good);
                    }
                    else {
                        System.out.println("不见的id:" + v);
                    }
                }
            }
            LoginResult loginResult = null;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(stall.getRole());
            if (ctx != null) {
                loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            }
            if (loginResult != null) {
                loginResult.setGold(loginResult.getGold().add(price.subtract(s)));
                assetUpdate.updata(this.getDataInfo(pet, 0));
                assetUpdate.updata("D=" + price.subtract(s).longValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else {
                BigDecimal gold = AllServiceUtil.getRoleTableService().selectMoneyRoleID(stall.getRoleid());
                gold = gold.add(price.subtract(s));
                AllServiceUtil.getRoleTableService().updateMoneyRoleID(stall.getRoleid(), gold);
            }
            if (MonitorUtil.isUpMoney(3, price.longValue())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("摆摊大宗金额流动:");
                buffer.append(stall.getRoleid());
                buffer.append("卖给");
                buffer.append(buyRole.getRole_id());
                buffer.append("宠物id:" + pet.getSid());
                buffer.append("金额");
                buffer.append(price);
                AllServiceUtil.getRecordService().insert(new Record(3, buffer.toString()));
            }
            assetUpdate.setType(AssetUpdate.STALLBUY);
            assetUpdate.setData(null);
            assetUpdate.setMsg(pet.getSummoningname());
            assetUpdate.setPet(pet);
            assetUpdate.updata("D=" + -price.longValue());
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        }
    }
    
    public void BuyLing(LoginResult buyRole, StallBuy buy, Stall stall) {
        CommodityBean commodity = stall.getBao(buy.getBuyid());
        if (commodity == null) {
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了"));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            return;
        }
        synchronized (commodity.getLock()) {
            Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodity.getCommodityId());
            if (lingbao == null || lingbao.getCommodityId() == null || lingbao.getRoleid().compareTo(stall.getRoleid()) != 0) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("下手慢了"));
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                return;
            }
            BigDecimal price = commodity.getMoney();
            if (price.compareTo(buyRole.getGold()) > 0) {
                SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().PromptAgreement("金钱不足"));
                return;
            }
            buyRole.setGold(buyRole.getGold().subtract(price));
            BigDecimal s = price.multiply(BigDecimal.valueOf(StallPool.TaxXs)).setScale(0, 4);
            MonitorUtil.getStallM().add(s.longValue());
            MonitorUtil.getMoney().useD(s.longValue());
            lingbao.setCommodityId(null);
            stall.removeBao(commodity);
            AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, buyRole.getRole_id());
            StringBuffer msg = new StringBuffer();
            msg.append("#Y玩家").append("#R").append(buyRole.getRolename()).append("#Y花费").append(StringUtil.toMoneyString(price));
            msg.append("#Y购买了").append("#G").append(lingbao.getBaoname());
            stall.addSellMsg(msg.toString());
            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLGET);
            if (StringUtils.isNotBlank(lingbao.getFushis())) {
                String[] fsIds;
                for (String fsId : fsIds = lingbao.getFushis().split("\\|")) {
                    Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(fsId));
                    if (good != null) {
                        AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, buyRole.getRole_id(), null, null);
                        assetUpdate.setGood(good);
                    }
                    else {
                        System.out.println("不见的id:" + fsId);
                    }
                }
            }
            LoginResult loginResult = null;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(stall.getRole());
            if (ctx != null) {
                loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            }
            if (loginResult != null) {
                loginResult.setGold(loginResult.getGold().add(price.subtract(s)));
                assetUpdate.updata(this.getDataInfo(lingbao, 0));
                assetUpdate.updata("D=" + price.subtract(s).longValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            else {
                BigDecimal gold = AllServiceUtil.getRoleTableService().selectMoneyRoleID(stall.getRoleid());
                gold = gold.add(price.subtract(s));
                AllServiceUtil.getRoleTableService().updateMoneyRoleID(stall.getRoleid(), gold);
            }
            if (MonitorUtil.isUpMoney(3, price.longValue())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("摆摊大宗金额流动:");
                buffer.append(stall.getRoleid());
                buffer.append("卖给");
                buffer.append(buyRole.getRole_id());
                buffer.append("灵宝id:" + lingbao.getBaoid());
                buffer.append("金额");
                buffer.append(price);
                AllServiceUtil.getRecordService().insert(new Record(3, buffer.toString()));
            }
            assetUpdate.setType(AssetUpdate.STALLBUY);
            assetUpdate.setData(null);
            assetUpdate.setMsg(lingbao.getBaoname());
            assetUpdate.setLingbao(lingbao);
            assetUpdate.updata("D=" + -price.longValue());
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            SendMessage.sendMessageByRoleName(buyRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        }
    }
    
    public boolean sellGoods(LoginResult sellRole, StallBuy sell, Stall stall) {
        CommodityBean commodity = stall.getCollectGoods(sell.getBuyid());
        if (sell.getSum() <= 0) {
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("出售数量不能为0"));
            return true;
        }
        if (commodity == null) {
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("已经收够了"));
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            return true;
        }
        StallPurchase stallPurchase = (StallPurchase)GameServer.getStallPurchases().get(commodity.getCommodityId());
        if (stallPurchase == null) {
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("已经收够了"));
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            return true;
        }
        RoleData roleData = RolePool.getRoleData(stall.getRoleid());
        boolean isGoodFull;
        if (roleData != null) {
            isGoodFull = roleData.isGoodFull();
        }
        else {
            List<Goodstable> goods = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(stall.getRoleid());
            LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(stall.getRoleid());
            int max = 24 + (loginResult.getAttachPack() + ((loginResult.getTurnAround() >= 4) ? 4 : loginResult.getTurnAround())) * 24;
            int num = 0;
            for (int i = goods.size() - 1; i >= 0; --i) {
                if ((int)((Goodstable)goods.get(i)).getStatus() == 0 && ((Goodstable)goods.get(i)).getType() != 8888L) {
                    ++num;
                }
            }
            isGoodFull = (num >= max);
        }
        if (isGoodFull) {
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("摊主物品栏已满。"));
            return true;
        }
        if (!stallPurchase.getContainsGoodsIds().contains(sell.getBuyid())) {
            return true;
        }
        synchronized (commodity.getLock()) {
            int sellSum = sell.getSum();
            sellSum = ((commodity.getSum() < sellSum) ? commodity.getSum() : sellSum);
            Goodstable goods2 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(sell.getCommodityId());
            if (goods2 == null) {
                AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
                assetUpdate.updata("G" + sell.getCommodityId() + "=0");
                SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                return true;
            }
            sellSum = (((int)goods2.getUsetime() < sellSum) ? ((int)goods2.getUsetime()) : sellSum);
            if (goods2 == null || sellSum < 1) {
                SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("数量不足"));
                SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
                return true;
            }
            BigDecimal price = commodity.getMoney().multiply(BigDecimal.valueOf((long)sellSum));
            BigDecimal s = price.multiply(BigDecimal.valueOf(StallPool.TaxXs)).setScale(0, 4);
            LoginResult stallResult = null;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(stall.getRole());
            if (ctx != null) {
                stallResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            }
            if (stallResult != null) {
                BigDecimal gold = stallResult.getGold();
                if (price.compareTo(gold) > 0) {
                    SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("摊主金钱不足"));
                    return true;
                }
                stallResult.setGold(stallResult.getGold().subtract(price));
            }
            else {
                BigDecimal gold = AllServiceUtil.getRoleTableService().selectMoneyRoleID(stall.getRoleid());
                if (gold==null||gold.compareTo(new BigDecimal(0))==0) {
                    for (Integer key : People.SELL_BOT.keySet()) {
                        if (key == stall.getId()) {
                            gold = new BigDecimal(99999999999L);
                        }
                    }
                }
                if (gold == null) {
                    gold = BigDecimal.ZERO;
                }
                if (price.compareTo(gold) > 0) {
                    SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().PromptAgreement("摊主金钱不足"));
                    return true;
                }
                gold = gold.subtract(price);
                AllServiceUtil.getRoleTableService().updateMoneyRoleID(stall.getRoleid(), gold);
            }
            boolean add = true;
            boolean fold = EquipTool.canSuper(goods2.getType());
            commodity.setSum(commodity.getSum() - sellSum);
            goods2.setUsetime(Integer.valueOf((int)goods2.getUsetime() - sellSum));
            Goodstable good = goods2.clone();
            good.setCommodityId(null);
            good.setUsetime(Integer.valueOf(sellSum));
            if ((int)goods2.getUsetime() <= 0 || !fold) {
                add = false;
            }
            if (commodity.getSum() <= 0) {
                stall.removeCollectGoodsList(commodity);
            }
            AllServiceUtil.getGoodsrecordService().insert(goods2, stall.getRoleid(), Integer.valueOf(sellSum), Integer.valueOf(1));
            if (fold) {
                List<Goodstable> sameGoodstable = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(stall.getRoleid(), good.getGoodsid());
                if (sameGoodstable.size() != 0) {
                    ((Goodstable)sameGoodstable.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)sameGoodstable.get(0)).getUsetime() + (int)good.getUsetime()));
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods2);
                    AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)sameGoodstable.get(0));
                    good = (Goodstable)sameGoodstable.get(0);
                }
                else if (add) {
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods2);
                    good.setRole_id(stall.getRoleid());
                    AllServiceUtil.getGoodsTableService().insertGoods(good);
                }
                else {
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, stall.getRoleid(), null, null);
                }
            }
            else {
                AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, stall.getRoleid(), null, null);
            }
            StringBuffer msg = new StringBuffer();
            msg.append("#Y店主以").append(StringUtil.toMoneyString(price)).append("#Y的价格收购了玩家").append("#R").append(sellRole.getRolename());
            msg.append("#Y的").append("#R").append(sellSum).append("#Y个").append("#G").append(good.getGoodsname());
            stall.addCollectMsg(msg.toString());
            if (stallResult != null) {
                AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.STALLBUY);
                assetUpdate2.setMsg(sellSum + "个" + good.getGoodsname());
                assetUpdate2.setGood(good);
                assetUpdate2.updata("D=" + -price.longValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
            }
            sellRole.setGold(sellRole.getGold().add(price).subtract(s));
            MonitorUtil.getStallM().add(s.longValue());
            MonitorUtil.getMoney().useD(s.longValue());
            if (MonitorUtil.isUpMoney(3, price.longValue())) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("摆摊大宗金额流动:");
                buffer.append(sellRole.getRole_id());
                buffer.append("卖给");
                buffer.append(stall.getRoleid());
                buffer.append(sellSum + "个" + good.getGoodsname());
                buffer.append("物品id:" + good.getRgid());
                buffer.append("金额");
                buffer.append(price);
                AllServiceUtil.getRecordService().insert(new Record(3, buffer.toString()));
            }
            AssetUpdate assetUpdate2 = new AssetUpdate(AssetUpdate.STALLGET);
            assetUpdate2.setMsg(sellSum + "个" + goods2.getGoodsname());
            assetUpdate2.updata("G" + goods2.getRgid() + "=" + goods2.getUsetime());
            assetUpdate2.updata("D=" + price.subtract(s).longValue());
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
            SendMessage.sendMessageByRoleName(sellRole.getRolename(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
        }
        this.updatePurchaseState(stall);
        return false;
    }
    
    private String getDataInfo(Object object, int sum) {
        StringBuffer buffer = new StringBuffer();
        if (object instanceof Goodstable) {
            Goodstable goods = (Goodstable)object;
            buffer.append("STALL=0=");
            buffer.append(goods.getRgid());
            buffer.append("=");
            buffer.append((goods.getCommodityId() == null) ? 0L : goods.getCommodityId().longValue());
            buffer.append("=");
            buffer.append(sum);
        }
        else if (object instanceof RoleSummoning) {
            RoleSummoning pet = (RoleSummoning)object;
            buffer.append("STALL=1=");
            buffer.append(pet.getSid());
            buffer.append("=");
            buffer.append((pet.getCommodityId() == null) ? 0L : pet.getCommodityId().longValue());
            buffer.append("=");
            buffer.append(sum);
        }
        else if (object instanceof Lingbao) {
            Lingbao lingbao = (Lingbao)object;
            buffer.append("STALL=2=");
            buffer.append(lingbao.getBaoid());
            buffer.append("=");
            buffer.append((lingbao.getCommodityId() == null) ? 0L : lingbao.getCommodityId().longValue());
            buffer.append("=");
            buffer.append(sum);
        }
        return buffer.toString();
    }
    
    public void RetreatStall(int id) {
        Stall stall = (Stall)this.allStall.remove(Integer.valueOf(id));
        StallBean bean = (StallBean)this.allStallBean.remove(Integer.valueOf(id));
        if (stall == null && bean == null) {
            return;
        }
        stall.setState(StallPool.OFF);
        bean.setState(StallPool.OFF);
        List<StallBean> list = this.getmap(stall.getMapid());
        list.remove(bean);
        SendMessage.sendMessageToMapRoles(new Long((long)stall.getMapid()), Agreement.getAgreement().stallstateAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.STALLRET);
        List<CommodityBean> goodsCommoditys = stall.getGoodsList();
        if (goodsCommoditys != null) {
            for (int i = 0; i < goodsCommoditys.size(); ++i) {
                CommodityBean commodity = (CommodityBean)goodsCommoditys.get(i);
                Goodstable goods = AllServiceUtil.getGoodsTableService().getGoodsByRgID(commodity.getCommodityId());
                if (goods != null) {
                    goods.setCommodityId(null);
                    AllServiceUtil.getGoodsTableService().updateGoodRedis(goods);
                    assetUpdate.updata(this.getDataInfo(goods, (int)goods.getUsetime()));
                }
            }
        }
        List<CommodityBean> petCommoditys = stall.getPetList();
        if (petCommoditys != null) {
            for (int j = 0; j < petCommoditys.size(); ++j) {
                CommodityBean commodity2 = (CommodityBean)petCommoditys.get(j);
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodity2.getCommodityId());
                if (pet != null && pet.getRoleid().compareTo(stall.getRoleid()) == 0) {
                    pet.setCommodityId(null);
                    AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                    assetUpdate.updata(this.getDataInfo(pet, 1));
                }
            }
        }
        List<CommodityBean> baoCommoditys = stall.getBaoList();
        if (baoCommoditys != null) {
            for (int k = 0; k < baoCommoditys.size(); ++k) {
                CommodityBean commodity3 = (CommodityBean)baoCommoditys.get(k);
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodity3.getCommodityId());
                if (lingbao != null && lingbao.getRoleid().compareTo(stall.getRoleid()) == 0) {
                    lingbao.setCommodityId(null);
                    AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                    assetUpdate.updata(this.getDataInfo(lingbao, 1));
                }
            }
        }
        SendMessage.sendMessageByRoleName(stall.getRole(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        SendMessage.sendMessageByRoleName(stall.getRole(), Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall)));
    }
    
    public ConcurrentHashMap<Integer, Stall> getAllStall() {
        return this.allStall;
    }
    
    public Stall getStallByRoleName(String roleName) {
        Stall retStall = null;
        for (Integer integer : this.allStall.keySet()) {
            Stall stall = (Stall)this.allStall.get(integer);
            if (stall.getRole().equals(roleName)) {
                retStall = stall;
                break;
            }
        }
        return retStall;
    }
    
    public void guanbi() {
        for (Stall stall : this.allStall.values()) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(AssetUpdate.STALLRET);
            List<CommodityBean> goodsCommoditys = stall.getGoodsList();
            if (goodsCommoditys != null) {
                for (int i = 0; i < goodsCommoditys.size(); ++i) {
                    CommodityBean commodity = (CommodityBean)goodsCommoditys.get(i);
                    List<Goodstable> goodsList = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(stall.getRoleid(), commodity.getCommodityId());
                    if (goodsList.size() > 0) {
                        ((Goodstable)goodsList.get(0)).setCommodityId(null);
                        assetUpdate.setGood((Goodstable)goodsList.get(0));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)goodsList.get(0));
                    }
                }
            }
            List<CommodityBean> petCommoditys = stall.getPetList();
            if (petCommoditys != null) {
                for (int j = 0; j < petCommoditys.size(); ++j) {
                    CommodityBean commodity2 = (CommodityBean)petCommoditys.get(j);
                    RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(commodity2.getCommodityId());
                    if (pet != null && pet.getRoleid().compareTo(stall.getRoleid()) == 0) {
                        pet.setCommodityId(null);
                        assetUpdate.setPet(pet);
                        AllServiceUtil.getRoleSummoningService().updateRoleSummoning(pet);
                    }
                }
            }
            List<CommodityBean> baoCommoditys = stall.getBaoList();
            if (baoCommoditys != null) {
                for (int k = 0; k < baoCommoditys.size(); ++k) {
                    CommodityBean commodity3 = (CommodityBean)baoCommoditys.get(k);
                    Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(commodity3.getCommodityId());
                    if (lingbao != null && lingbao.getRoleid().compareTo(stall.getRoleid()) == 0) {
                        lingbao.setCommodityId(null);
                        assetUpdate.setLingbao(lingbao);
                        AllServiceUtil.getLingbaoService().updateLingbaoRedis(lingbao);
                    }
                }
            }
        }
    }
    
    static {
        StallPool.TaxXs = 0.0;
        StallPool.PREPARE = 0;
        StallPool.NO = 1;
        StallPool.MANAGE = 2;
        StallPool.OFF = 3;
    }
}
