package org.come.readUtil;

import java.util.ArrayList;
import java.util.Iterator;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllACard;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.aCard;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.List;

public class ReadACardUtil
{
    public static List<String> pifeng;
    public static List<String> guajian;
    public static List<String> yaodai;
    public static List<String> mianju;
    public static Map<String, String> shuxing1;
    public static Map<String, String> shuxing2;
    public static List<String> jiezhi;
    
    public static ConcurrentHashMap<Integer, aCard> selectACards(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, aCard> allACard = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                aCard aCard = new aCard();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(aCard, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (aCard.getId() != 0) {
                    allACard.put(Integer.valueOf(aCard.getId()), aCard);
                }
            }
        }
        readdiancui();
        return allACard;
    }
    
    public static String createACards(ConcurrentHashMap<Integer, aCard> map) {
        AllACard aCard = new AllACard();
        Map<Integer, aCard> aMap = new HashMap<>();
        for (aCard card : map.values()) {
            aMap.put(Integer.valueOf(card.getId()), card);
        }
        aCard.setaMap(aMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(aCard);
        return msgString;
    }
    
    public static void readdiancui() {
        String[][] result = ReadExelTool.getResult("config/diancui.xls");
        if (result == null) {
            System.out.println("表格为空");
            return;
        }
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                System.out.println(result[i][0] + "\n");
                try {
                    if (i <= 13) {
                        ReadACardUtil.guajian.add(result[i][0]);
                        ReadACardUtil.yaodai.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                    else if (i <= 25) {
                        ReadACardUtil.mianju.add(result[i][0]);
                        ReadACardUtil.pifeng.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                    else if (i <= 35) {
                        ReadACardUtil.jiezhi.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                    else if (i <= 41) {
                        ReadACardUtil.guajian.add(result[i][0]);
                        ReadACardUtil.yaodai.add(result[i][0]);
                        ReadACardUtil.mianju.add(result[i][0]);
                        ReadACardUtil.pifeng.add(result[i][0]);
                        ReadACardUtil.jiezhi.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                    else if (i <= 43) {
                        ReadACardUtil.yaodai.add(result[i][0]);
                        ReadACardUtil.mianju.add(result[i][0]);
                        ReadACardUtil.pifeng.add(result[i][0]);
                        ReadACardUtil.jiezhi.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                    else {
                        ReadACardUtil.guajian.add(result[i][0]);
                        ReadACardUtil.yaodai.add(result[i][0]);
                        ReadACardUtil.mianju.add(result[i][0]);
                        ReadACardUtil.pifeng.add(result[i][0]);
                        ReadACardUtil.jiezhi.add(result[i][0]);
                        ReadACardUtil.shuxing1.put(result[i][0], result[i][7]);
                        ReadACardUtil.shuxing2.put(result[i][0], result[i][8]);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static {
        ReadACardUtil.pifeng = new ArrayList<>();
        ReadACardUtil.guajian = new ArrayList<>();
        ReadACardUtil.yaodai = new ArrayList<>();
        ReadACardUtil.mianju = new ArrayList<>();
        ReadACardUtil.shuxing1 = new HashMap<>();
        ReadACardUtil.shuxing2 = new HashMap<>();
        ReadACardUtil.jiezhi = new ArrayList<>();
    }
}
