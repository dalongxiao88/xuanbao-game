package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import org.come.readBean.Allalchemy;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Alchemy;
import java.util.concurrent.ConcurrentHashMap;

public class ReadAlchemyUtil
{
    public static ConcurrentHashMap<Integer, Alchemy> selectGMshopItem(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Alchemy> alchemyItemConcurrentHashMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Alchemy scheme = new Alchemy();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(scheme, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                alchemyItemConcurrentHashMap.put(Integer.valueOf(Integer.parseInt(scheme.getAlchemyid())), scheme);
            }
        }
        return alchemyItemConcurrentHashMap;
    }
    
    public static String createAllalchemy(ConcurrentHashMap<Integer, Alchemy> map) {
        Allalchemy allTalent = new Allalchemy();
        Map<BigDecimal, Alchemy> allMap = new HashMap<>();
        for (Alchemy alchemy : map.values()) {
            allMap.put(BigDecimal.valueOf(Long.parseLong(alchemy.getAlchemyid())), alchemy);
        }
        allTalent.setAllalchemy(allMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTalent);
        return msgString;
    }
}
