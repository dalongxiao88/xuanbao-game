package org.come.readUtil;

import java.util.ArrayList;
import java.util.List;
import org.come.until.GsonUtil;
import org.come.readBean.allItemExchange;
import org.come.server.GameServer;
import org.come.model.ItemExchange;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class ReadItemUtil
{
    public static ConcurrentHashMap<BigDecimal, Goodstable> allitemId(String path, StringBuffer buffer) {
        ConcurrentHashMap<BigDecimal, Goodstable> allitem = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Goodstable item = new Goodstable();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(item, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!item.getGoodsid().equals("")) {
                    item.toString();
                    allitem.put(new BigDecimal(String.valueOf(item.getGoodsid())), item);
                }
            }
        }
        return allitem;
    }
    
    public static ConcurrentHashMap<Integer, ItemExchange> allPetExchangeMap(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, ItemExchange> allItemExchange = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                ItemExchange ItemExchange = new ItemExchange();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(ItemExchange, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (ItemExchange.geteId() != 0) {
                    Goodstable pet = GameServer.getgoods(ItemExchange.getGoods());
                    if (pet == null) {
                        buffer.append("未找到对应的装备ID:" + ItemExchange.getGoods());
                        return null;
                    }
                    ItemExchange.initPet(pet);
                    allItemExchange.put(Integer.valueOf(ItemExchange.geteId()), ItemExchange);
                }
            }
        }
        return allItemExchange;
    }
    
    public static String createTxtPetExchange(ConcurrentHashMap<Integer, ItemExchange> map) {
        allItemExchange allItemExchange = new allItemExchange();
        allItemExchange.setAllItemExchange(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allItemExchange);
        return msgString;
    }
    
    public static List<Goodstable> selectRoleSummonings(String path, StringBuffer buffer) {
        List<Goodstable> item = new ArrayList<>();
        String[][] result = ReadExelTool.getResultRelative(path);
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Goodstable g = new Goodstable();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(g, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                item.add(g);
            }
        }
        return item;
    }
}
