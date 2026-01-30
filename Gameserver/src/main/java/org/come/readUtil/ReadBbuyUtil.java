package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllBbuy;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.bean.Bbuy;
import java.util.concurrent.ConcurrentHashMap;

public class ReadBbuyUtil
{
    public static ConcurrentHashMap<Integer, Bbuy> selecBbuys(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Bbuy> allbbuy = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Bbuy bbuy = new Bbuy();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(bbuy, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                allbbuy.put(bbuy.getGid(), bbuy);
            }
        }
        return allbbuy;
    }
    
    public static String createBbuy(ConcurrentHashMap<Integer, Bbuy> map) {
        AllBbuy allBbuy = new AllBbuy();
        Map<Integer, Bbuy> allbbuy = new HashMap<>();
        for (Bbuy bbuy : map.values()) {
            allbbuy.put(bbuy.getGid(), bbuy);
        }
        allBbuy.setAllbbuy(allbbuy);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allBbuy);
        return msgString;
    }
}
