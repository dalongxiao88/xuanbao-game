package org.come.readUtil;

import org.come.readBean.allGoodsExchange;
import org.come.model.GoodsExchange;
import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import org.come.model.GoodModel;
import java.util.HashMap;
import org.come.bean.GoodsBean;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.server.GameServer;
import org.come.entity.Goodstable;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.AuctionExchange;
import java.util.concurrent.ConcurrentHashMap;

public class ReadAuctionUtil
{
    public static ConcurrentHashMap<Integer, AuctionExchange> allAuctionGoodsExchangeMap(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, AuctionExchange> allAuctionGoodsExchange = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                AuctionExchange auctionExchange = new AuctionExchange();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(auctionExchange, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (auctionExchange.geteId() != 0) {
                    Goodstable good = (Goodstable)GameServer.getAllGoodsMap().get(auctionExchange.getGid());
                    if (good == null) {
                        buffer.append("未找到对应的GoodID:" + auctionExchange.getGid());
                        return null;
                    }
                    String[] arr = auctionExchange.getConsume().split("\\|");
                    List<Goodstable> goodstables = new ArrayList<>();
                    for (int k = 0; k < arr.length; ++k) {
                        if (!arr[k].startsWith("D")) {
                            String str = arr[k].substring(2);
                            BigDecimal id = BigDecimal.valueOf(Long.parseLong(str.split("=")[0]));
                            Goodstable goodInfo = (Goodstable)GameServer.getAllGoodsMap().get(id);
                            goodstables.add(goodInfo);
                        }
                    }
                    auctionExchange.initGood(good, goodstables);
                    allAuctionGoodsExchange.put(Integer.valueOf(auctionExchange.geteId()), auctionExchange);
                }
            }
        }
        return allAuctionGoodsExchange;
    }
    
    public static String createGoods(ConcurrentHashMap<BigDecimal, Goodstable> map) {
        GoodsBean goodsBean = new GoodsBean();
        Map<BigDecimal, GoodModel> allGoodsMap = new HashMap<>();
        for (Goodstable good : map.values()) {
            GoodModel model = new GoodModel(good);
            allGoodsMap.put(model.getGoodsid(), model);
        }
        goodsBean.setAllGoodsMap(allGoodsMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(goodsBean);
        return msgString;
    }
    
    public static String createTxtAuctionGoodsExchange(ConcurrentHashMap<Integer, GoodsExchange> map) {
        allGoodsExchange allGoodsExchange = new allGoodsExchange();
        allGoodsExchange.setAllGoodsExchange(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allGoodsExchange);
        return msgString;
    }
}
