package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import org.come.readBean.AllGMshopItem;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.GMshopItem;
import java.util.concurrent.ConcurrentHashMap;

public class ReadGMshopItemUtil
{
    public static ConcurrentHashMap<Integer, GMshopItem> selectGMshopItem(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, GMshopItem> gMshopItemConcurrentHashMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                GMshopItem scheme = new GMshopItem();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(scheme, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                gMshopItemConcurrentHashMap.put(Integer.valueOf(Integer.parseInt(scheme.getShopId())), scheme);
            }
        }
        return gMshopItemConcurrentHashMap;
    }
    
    public static String createGMshopItem(ConcurrentHashMap<Integer, GMshopItem> map) {
        AllGMshopItem allTalent = new AllGMshopItem();
        Map<BigDecimal, GMshopItem> allMap = new HashMap<>();
        for (GMshopItem gMshopItem : map.values()) {
            allMap.put(BigDecimal.valueOf(Long.parseLong(gMshopItem.getShopId())), gMshopItem);
        }
        allTalent.setAllGMshopItem(allMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTalent);
        return msgString;
    }
}
