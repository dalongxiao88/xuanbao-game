package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.bean.EshopBean;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.come.model.Lshop;
import org.come.model.Eshop;
import org.come.entity.BuyCount;
import org.come.until.AllServiceUtil;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Shop;
import java.util.concurrent.ConcurrentHashMap;

public class ReadShopUtil
{
    public static ConcurrentHashMap<String, Shop> getAllShop(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Shop> allShopGood = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Shop shop = new Shop();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(shop, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!shop.getShopid().equals("")) {
                        if (shop.getLvl() != null && !shop.getLvl().equals("")) {
                            int[] lvls = { 0, 0, 4, 200, 200 };
                            String[] lvls2 = shop.getLvl().split("\\|");
                            String[] vs = lvls2[0].split("\\-");
                            lvls[1] = Integer.parseInt(vs[0]);
                            lvls[3] = Integer.parseInt(vs[1]);
                            if (lvls2.length >= 2) {
                                lvls[0] = Integer.parseInt(lvls2[1]);
                            }
                            if (lvls2.length == 3) {
                                vs = lvls2[2].split("\\-");
                                lvls[2] = Integer.parseInt(vs[0]);
                                lvls[4] = Integer.parseInt(vs[1]);
                            }
                            shop.setLvls(lvls);
                        }
                        if (shop.getPriceNum() != 0) {
                            shop.setIsPrice(Boolean.valueOf(true));
                        }
                        BuyCount buyCount = AllServiceUtil.getBuyCountServeice().selectBuyCount(Long.parseLong(shop.getShopid()) * 100L + 1L);
                        if (buyCount == null) {
                            buyCount = new BuyCount();
                            buyCount.setBid(Long.parseLong(shop.getShopid()) * 100L + 1L);
                            buyCount.setPtype(1);
                            buyCount.setShopId(Integer.parseInt(shop.getShopid()));
                            buyCount.setShopType(shop.getShoptype());
                            AllServiceUtil.getBuyCountServeice().insertBuyCount(buyCount);
                        }
                        shop.setBuyCount(buyCount);
                        allShopGood.put(shop.getShopid(), shop);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return allShopGood;
    }
    
    public static ConcurrentHashMap<String, Eshop> getAllEshopGoods(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Eshop> allShopGoodsMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Eshop eshop = new Eshop();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(eshop, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!eshop.getEshopid().equals("")) {
                        if (eshop.getLvl() != null && !eshop.getLvl().equals("")) {
                            int[] lvls = { 0, 0, 4, 200, 200 };
                            String[] lvls2 = eshop.getLvl().split("\\|");
                            String[] vs = lvls2[0].split("\\-");
                            lvls[1] = Integer.parseInt(vs[0]);
                            lvls[3] = Integer.parseInt(vs[1]);
                            if (lvls2.length >= 2) {
                                lvls[0] = Integer.parseInt(lvls2[1]);
                            }
                            if (lvls2.length == 3) {
                                vs = lvls2[2].split("\\-");
                                lvls[2] = Integer.parseInt(vs[0]);
                                lvls[4] = Integer.parseInt(vs[1]);
                            }
                            eshop.setLvls(lvls);
                        }
                        BuyCount buyCount = AllServiceUtil.getBuyCountServeice().selectBuyCount(Long.parseLong(eshop.getEshopid()) * 100L);
                        if (buyCount == null) {
                            buyCount = new BuyCount();
                            buyCount.setBid(Long.parseLong(eshop.getEshopid()) * 100L);
                            buyCount.setPtype(0);
                            buyCount.setShopId(Integer.parseInt(eshop.getEshopid()));
                            buyCount.setShopType(Integer.parseInt(eshop.getEshoptype()));
                            AllServiceUtil.getBuyCountServeice().insertBuyCount(buyCount);
                        }
                        eshop.setBuyCount(buyCount);
                        allShopGoodsMap.put(eshop.getEshopid(), eshop);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return allShopGoodsMap;
    }
    
    public static ConcurrentHashMap<String, Lshop> selectLShops(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Lshop> shops = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Lshop lshop = new Lshop();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(lshop, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (lshop.getId() != 0) {
                        BuyCount buyCount = AllServiceUtil.getBuyCountServeice().selectBuyCount((long)(lshop.getId() * 100 + 3));
                        if (buyCount == null) {
                            buyCount = new BuyCount();
                            buyCount.setBid((long)(lshop.getId() * 100 + 3));
                            buyCount.setPtype(3);
                            buyCount.setShopId(lshop.getId());
                            buyCount.setShopType(lshop.getType());
                            AllServiceUtil.getBuyCountServeice().insertBuyCount(buyCount);
                        }
                        lshop.setBuyCount(buyCount);
                        shops.put(lshop.getId() + "", lshop);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return shops;
    }
    
    public static Map<String, List<Shop>> getShop(ConcurrentHashMap<String, Shop> map) {
        List<Shop> list = new ArrayList<>();
    LOOP:
        for (Shop shop : map.values()) {
            int sid = Integer.parseInt(shop.getShopid());
            int i = 0;
            int length = list.size();
            while (i < length) {
                Shop shop2 = (Shop)list.get(i);
                if (sid < Integer.parseInt(shop2.getShopid())) {
                    list.add(i, shop);
                    continue LOOP;
                }
                else {
                    ++i;
                }
            }
            list.add(shop);
        }
        Map<String, List<Shop>> shopGoods = new HashMap<>();
        for (Shop shop3 : list) {
            List<Shop> shops = (List<Shop>)shopGoods.get(shop3.getShoptype() + "");
            if (shops == null) {
                shops = new ArrayList<>();
                shopGoods.put(shop3.getShoptype() + "", shops);
            }
            shops.add(shop3);
        }
        return shopGoods;
    }
    
    public static String getEShop(ConcurrentHashMap<String, Eshop> allShopGoodsMap) {
        EshopBean eshopBean = new EshopBean();
        List<Eshop> eshops = new ArrayList<>();
        LOOP:
        for (Eshop eshop : allShopGoodsMap.values()) {
            int id = Integer.parseInt(eshop.getEshopid());
            int i = 0;
            int length = eshops.size();
            while (i < length) {
                int oid = Integer.parseInt(((Eshop)eshops.get(i)).getEshopid());
                if (id < oid) {
                    eshops.add(i, eshop);
                    continue LOOP;
                }
                else {
                    ++i;
                }
            }
            eshops.add(eshop);
        }
        eshopBean.setEshops(eshops);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(eshopBean);
        return msgString;
    }
}
