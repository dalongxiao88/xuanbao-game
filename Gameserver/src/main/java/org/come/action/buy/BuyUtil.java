package org.come.action.buy;

import org.come.protocol.Agreement;
import java.util.Iterator;
import org.come.server.GameServer;
import org.come.model.Eshop;
import come.tool.Battle.BattleMixDeal;
import org.come.bean.BuyShopBean;
import org.come.model.Shop;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class BuyUtil
{
    private static ConcurrentHashMap<String, ConcurrentHashMap<BigDecimal, Integer>> shops;
    private static ConcurrentHashMap<String, ConcurrentHashMap<BigDecimal, Integer>> eshops;
    static String CHECKTS1;
    static String CHECKTS2;
    
    public static String isLimit(LoginResult loginResult, Shop shop, BuyShopBean buyShopBean) {
        if (shop.getNum() == 0) {
            return null;
        }
        ConcurrentHashMap<BigDecimal, Integer> map = (ConcurrentHashMap<BigDecimal, Integer>)BuyUtil.shops.get(shop.getShopid());
        if (map == null) {
            map = new ConcurrentHashMap<>();
            BuyUtil.shops.put(shop.getShopid(), map);
        }
        if (shop.getLvls() != null) {
            String value = BattleMixDeal.isLvl((int)loginResult.getGrade(), shop.getLvls());
            if (value != null) {
                return value;
            }
        }
        Integer num = (Integer)map.get(loginResult.getRole_id());
        if (num == null) {
            num = Integer.valueOf(0);
        }
        if ((int)num >= shop.getNum()) {
            return (buyShopBean.getAte() == 2) ? BuyUtil.CHECKTS2 : BuyUtil.CHECKTS1;
        }
        if ((int)num + buyShopBean.getSum() > shop.getNum()) {
            buyShopBean.setSum(shop.getNum() - (int)num);
        }
        num = Integer.valueOf((int)num + buyShopBean.getSum());
        map.put(loginResult.getRole_id(), num);
        return null;
    }
    
    public static String isLimit(LoginResult loginResult, Eshop eshop, BuyShopBean buyShopBean) {
        if (eshop.getNum() == 0) {
            return null;
        }
        ConcurrentHashMap<BigDecimal, Integer> map = (ConcurrentHashMap<BigDecimal, Integer>)BuyUtil.eshops.get(eshop.getEshopid());
        if (map == null) {
            map = new ConcurrentHashMap<>();
            BuyUtil.eshops.put(eshop.getEshopid(), map);
        }
        if (eshop.getLvls() != null) {
            String value = BattleMixDeal.isLvl((int)loginResult.getGrade(), eshop.getLvls());
            if (value != null) {
                return value;
            }
        }
        Integer num = (Integer)map.get(loginResult.getRole_id());
        if (num == null) {
            num = Integer.valueOf(0);
        }
        if ((int)num >= eshop.getNum()) {
            return BuyUtil.CHECKTS1;
        }
        if ((int)num + buyShopBean.getSum() > eshop.getNum()) {
            buyShopBean.setSum(eshop.getNum() - (int)num);
        }
        num = Integer.valueOf((int)num + buyShopBean.getSum());
        map.put(loginResult.getRole_id(), num);
        return null;
    }
    
    public static void reset() {
        BuyUtil.eshops.clear();
        Iterator<String> it = BuyUtil.shops.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            Shop shop = (Shop)GameServer.getAllShopGoods().get(key);
            if (shop == null) {
                it.remove();
            }
            else if (shop.getShoptype() != 605) {
                it.remove();
            }
            else {
                continue;
            }
        }
    }
    
    public static void resetShopType(int shopType) {
        Iterator<String> it = BuyUtil.shops.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            Shop shop = (Shop)GameServer.getAllShopGoods().get(key);
            if (shop != null && shop.getShoptype() == shopType) {
                it.remove();
            }
        }
    }
    
    static {
        BuyUtil.shops = new ConcurrentHashMap<>();
        BuyUtil.eshops = new ConcurrentHashMap<>();
        BuyUtil.CHECKTS1 = Agreement.getAgreement().PromptAgreement("达到最大购买次数");
        BuyUtil.CHECKTS2 = Agreement.getAgreement().PromptAgreement("达到最大兑换次数");
    }
}
