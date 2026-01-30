package org.come.redis;

import come.tool.FightingData.Battlefield;
import come.tool.Stall.*;
import org.apache.commons.lang.StringUtils;
import org.come.bean.NChatBean;
import org.come.jiaren.People;
import org.come.bean.LoginResult;
import org.come.entity.Goodstable;
import org.come.entity.JiaRenBT;
import org.come.server.GameServer;
import org.come.server.GolemConfig;
import org.come.server.Point;
import org.come.until.AllServiceUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StallBotTask implements Runnable {

    @Override
    public void run() {

        StallSendMessage.setBotROLES(null);//先重置假人喊话

        Map<Integer, List<JiaRenBT>> integerListMap = initStallGoods();
        int x = 0;
        for (Map.Entry<Integer, List<JiaRenBT>> integerListEntry : integerListMap.entrySet()) {

            Integer key = StallPool.getPool().getIncreasesum();

            if (People.SELL_BOT == null){
                People.SELL_BOT = new HashMap<>();
            }
            if (People.BOT_RID == null){
                People.BOT_RID = new ArrayList<>();//摆摊id
            }
            if(People.SELL_BOT.get(key) != null){
                continue;
            }
            List<JiaRenBT> value = integerListEntry.getValue();
            JiaRenBT baiTan = value.get(0);
            LoginResult loginResult = People.addStallBot((long) baiTan.getMapId(), baiTan.getMap_x(), baiTan.getMap_y());
//            RedisCacheUtil.STALL_BOT.add(loginResult);
//            LoginResult loginResult = stallBot.get(x);
            Stall stall = new Stall();
            stall.setStall(StringUtils.isBlank(baiTan.getStallName()) ? loginResult.getRolename() : baiTan.getStallName());
            stall.setMapid(baiTan.getMapId());
            stall.setState(1);
            stall.setX((int)(loginResult.getX()*20-50));
            stall.setY((int)(loginResult.getY()*20-120));
            System.out.printf("摆摊BOT信息 地图id = %s 坐标 x = %s 坐标 y = %s%n", stall.getMapid() ,stall.getX() ,stall.getY());
            stall.setId(key);
            stall.setGoodsList(new ArrayList<>());
            List<CommodityBean> goodsTables = initCommodityBeanList(loginResult.getRole_id(), value,stall);
            for (CommodityBean commodity:goodsTables){
                stall.addCommodity(commodity);
            }
            stall.setCollectGoodsList(new ArrayList<>());
            List<CommodityBean> shougou = initAcquiredList(loginResult.getRole_id(), value,stall);
            for (CommodityBean commodity:shougou){
                stall.addCommodity(commodity);
            }

            Commodity[] pets = new Commodity[5];
            stall.setPetList(null);
            stall.setRoleid(loginResult.getRole_id());
            stall.setRole(loginResult.getRolename());
            StallPool.getPool().addStall(stall, null);
            loginResult.setBooth_id(new BigDecimal(stall.getId()));
            StallPool.getPool().updateState(loginResult.getBooth_id(), StallPool.MANAGE, loginResult.getRole_id());
            People.SELL_BOT.put(stall.getId(),loginResult.getRolename());

            if (goodsTables.size() != 0) {
                NChatBean nchat = new NChatBean();
                nchat.setId(3);//世界
                nchat.setRoleId(loginResult.getRole_id());
                nchat.setRole(loginResult.getRolename());
                int status= 0;
                if (goodsTables.size()>1) {
                    status= Battlefield.random.nextInt(goodsTables.size());
                }
                String gname = goodsTables.get(status).getCommodityName();
                String message = StallSendMessage.creatMessage(gname, stall.getStall(), stall.getId(),value);
                if (message != null) {
                    nchat.setMessage(message);
                    StallSendMessage.getBotROLES().add(nchat);
                }
            }
        }
    }
    private Point[] stallPoints;
    public Point[] getStallPoints() {
        if (stallPoints == null) {
            GolemConfig configs = GameServer.getGolemConfig();
            String value = configs.get("摆摊坐标");
            if (StringUtils.isNotBlank(value)) {
                String[] vals = value.split("\\|");
                stallPoints = new Point[vals.length];
                for (int i = 0; i < vals.length; i++) {
                    stallPoints[i] = new Point();
                    String[] vs = vals[i].split("=");
                    stallPoints[i].setMapId(Integer.parseInt(vs[0]));
                    for (int j = 1; j < vs.length; j++) {
                        String[] p = vs[j].split(",");
                        stallPoints[i].addPoint(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
                    }
                }
            } else {
                System.out.println("摆摊坐标配置为空");
            }
        }
        return stallPoints;
    }
    private Map<Integer,List<JiaRenBT>> initStallGoods(){
        Map<Integer,List<JiaRenBT>> stMap = new HashMap<>();
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, JiaRenBT>> allBaiTan = GameServer.getAllBaiTan();
        for (Map.Entry<Integer, ConcurrentHashMap<Integer, JiaRenBT>> integerConcurrentHashMapEntry : allBaiTan.entrySet()) {
            ConcurrentHashMap<Integer, JiaRenBT> value = integerConcurrentHashMapEntry.getValue();
            for (Map.Entry<Integer, JiaRenBT> integerBaiTanEntry : value.entrySet()) {
                JiaRenBT value1 = integerBaiTanEntry.getValue();
                List<JiaRenBT> baiTans = stMap.get(value1.getStallId());
                if(baiTans == null || baiTans.isEmpty()){

                    List<JiaRenBT> list = new ArrayList<>();
                    list.add(value1);

                    stMap.put(value1.getStallId(),list);
                }else{
                    baiTans.add(value1);
                    stMap.put(value1.getStallId(),baiTans);
                }
            }
        }

        return stMap;
    }

    private List<CommodityBean> initCommodityBeanList(BigDecimal roleId, List<JiaRenBT> value, Stall stall){
        List<CommodityBean> goodsTables = new ArrayList<>();
        for (int i = 0; i < value.size(); i++) {
            CommodityBean a=createGood1(roleId, value.get(i),stall);
            if (a != null) {
                goodsTables.add(a);
            }
        }
        return goodsTables;
    }

    private CommodityBean createGood1(BigDecimal roleId, JiaRenBT baiTan, Stall stall) {
        if (baiTan.getGoodsId() == null) {
            return null;
        }
        Goodstable goodstable = GameServer.getAllGoodsMap().get(baiTan.getGoodsId());
        if (goodstable == null) {
            return null;
        }
        goodstable.setRole_id(roleId);
        BigDecimal goods_pk = RedisCacheUtil.getGoods_pk();
        goodstable.setUsetime(baiTan.getUseTime());
        goodstable.setRgid(goods_pk);
        People.BOT_RID.add(goods_pk);
        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
        CommodityBean commodity = new CommodityBean();
        commodity.setCommodityName(goodstable.getGoodsname());
        commodity.setCommodityId(goodstable.getRgid());
        commodity.setId(stall.getCommodityAddId());
        commodity.setCommoditySkin(goodstable.getSkin());
        commodity.setType(0);
        commodity.setState(1);
        commodity.setSum(baiTan.getUseTime());
        commodity.setCurrency("金钱");
        commodity.setGoods(goodstable);
        commodity.setMoney(new BigDecimal(baiTan.getMoney()));
        return commodity;
    }



    private Commodity[] initCommodityList(BigDecimal roleId, List<JiaRenBT> value){
        Commodity[] goodsTables = new Commodity[24];
        for (int i = 0; i < value.size(); i++) {
            Commodity a=createGood(roleId, value.get(i));
            if (a != null) {
                goodsTables[i] = a;
            }
        }
        return goodsTables;
    }

    private Commodity createGood(BigDecimal roleId, JiaRenBT baiTan) {
        if (baiTan.getGoodsId() == null) {
            return null;
        }
        Goodstable goodstable = GameServer.getAllGoodsMap().get(baiTan.getGoodsId());
        if (goodstable == null) {
            return null;
        }
        goodstable.setRole_id(roleId);
        BigDecimal goods_pk = RedisCacheUtil.getGoods_pk();
        goodstable.setUsetime(baiTan.getUseTime());
        goodstable.setRgid(goods_pk);
        People.BOT_RID.add(goods_pk);
        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
        Commodity commodity = new Commodity();
        commodity.setGood(goodstable);
        commodity.setCurrency("金钱");
        commodity.setMoney(baiTan.getMoney());
        return commodity;
    }

    private List<CommodityBean> initAcquiredList(BigDecimal roleId, List<JiaRenBT> value, Stall stall){
        List<CommodityBean> acquireds = new ArrayList<>();
        for (int i = 0; i < value.size(); i++) {
            acquireds.add(createAcquired(value.get(i),stall));
        }
        return acquireds;
    }

    private CommodityBean createAcquired(JiaRenBT baiTan, Stall stall) {
        if (baiTan.getAcquiredId() == null) {
            return null;
        }
        Goodstable goodstable = GameServer.getAllGoodsMap().get(baiTan.getAcquiredId());
        if (goodstable == null) {
            return null;
        }

        CommodityBean commodity = new CommodityBean();
        commodity.setCommodityName(goodstable.getGoodsname());
        commodity.setCommodityId(goodstable.getGoodsid());
        commodity.setId(stall.getCommodityAddId());
        commodity.setCommoditySkin(goodstable.getSkin());
        commodity.setType(3);
        commodity.setState(1);
        commodity.setSum(baiTan.getAcquirednum());
        commodity.setCurrency("金钱");
        commodity.setGoods(goodstable);
        commodity.setMoney(new BigDecimal(baiTan.getAcquiredmoney()));

        return commodity;
    }
}
