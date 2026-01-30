package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllSuit;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.Suit;
import java.util.concurrent.ConcurrentHashMap;

public class ReadSuitUtil
{
    public static ConcurrentHashMap<Integer, Suit> selecSuits(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Suit> allbbuy = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Suit suit = new Suit();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(suit, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (suit.getSuitID() != 0) {
                        String[] v = suit.getHaveParts().split("\\|");
                        int[] parts = new int[v.length];
                        for (int k = 0; k < parts.length; ++k) {
                            parts[k] = Integer.parseInt(v[k]);
                        }
                        suit.setParts(parts);
                        allbbuy.put(Integer.valueOf(suit.getSuitID()), suit);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return allbbuy;
    }
    
    public static String createSkill(ConcurrentHashMap<Integer, Suit> map) {
        AllSuit allSuit = new AllSuit();
        Map<Integer, Suit> suitmap = new HashMap<>();
        for (Suit suit : map.values()) {
            suitmap.put(Integer.valueOf(suit.getSuitID()), suit);
        }
        allSuit.setRolesuit(suitmap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allSuit);
        return msgString;
    }
}
