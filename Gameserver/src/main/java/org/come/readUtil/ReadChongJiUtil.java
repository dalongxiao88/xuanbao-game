package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.readBean.AllTitleBean;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Title;
import org.come.model.Lshop;
import java.util.Map;
import org.come.model.Shop;
import org.come.entity.Goodstable;
import org.come.server.GameServer;
import java.util.HashMap;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import org.come.entity.ChongjipackBean;
import java.util.List;

public class ReadChongJiUtil
{
    public static List<ChongjipackBean> selectChongJi(String path, StringBuffer buffer) {
        List<ChongjipackBean> chongjipackBeans = new ArrayList<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("") && !result[i][1].equals("")) {
                ChongjipackBean chongjipackBean = new ChongjipackBean();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(chongjipackBean, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if ((int)chongjipackBean.getPacktype() == 8) {
                    String packgoods = chongjipackBean.getPackgoods();
                    String[] shopIds = packgoods.split("=")[1].split("&");
                    Map<Integer, Lshop> lshopMap = new HashMap<>();
                    List<Shop> shops = new ArrayList<>();
                    for (int k = 0; k < shopIds.length; ++k) {
                        Lshop lshop = GameServer.getLshop(shopIds[k]);
                        Goodstable good = (Goodstable)GameServer.getAllGoodsMap().get(lshop.getGid());
                        if (good != null && lshop != null) {
                            Shop shop = new Shop();
                            shop.setNid(chongjipackBean.getId());
                            shop.setShopid(lshop.getId() + "");
                            shop.setShopiid(lshop.getGid());
                            shop.setShopname(good.getGoodsname());
                            shop.setShoptype(lshop.getType());
                            shop.setShopprice(lshop.getMoney().longValue());
                            shop.setShopskin(good.getSkin());
                            shop.setShoptext(good.getInstruction());
                            shop.setLimitTime(chongjipackBean.getEndtime());
                            shops.add(shop);
                            lshopMap.put(Integer.valueOf(lshop.getId()), lshop);
                        }
                    }
                    chongjipackBean.setLimitedTimeShop(lshopMap);
                    chongjipackBean.setShops(shops);
                }
                chongjipackBeans.add(chongjipackBean);
            }
        }
        return chongjipackBeans;
    }
    
    public static ConcurrentHashMap<String, Title> getTitle2(List<Title> titles) {
        ConcurrentHashMap<String, Title> getTitle = new ConcurrentHashMap<>();
        for (int i = titles.size() - 1; i >= 0; --i) {
            Title title = (Title)titles.get(i);
            getTitle.put(title.getTitlename(), title);
            getTitle.put(title.getId() + "", title);
            if (title.getExist().indexOf("充值") == -1) {
                titles.remove(i);
            }
        }
        GameServer.setMoneyTitles(titles);
        return getTitle;
    }
    
    public static String createTitle(List<Title> titles) {
        AllTitleBean allTitleBean = new AllTitleBean();
        Map<String, Title> map = new HashMap<>();
        for (int i = 0; i < titles.size(); ++i) {
            Title title = (Title)titles.get(i);
            map.put(title.getTitlename(), title);
        }
        allTitleBean.setTitleMap(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTitleBean);
        return msgString;
    }
}
