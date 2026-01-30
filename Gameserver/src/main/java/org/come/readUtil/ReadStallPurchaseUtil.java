package org.come.readUtil;

import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.bean.StallPurchase;
import java.util.LinkedList;
import org.come.tool.ReadExelTool;
import java.util.LinkedHashMap;
import org.come.bean.StallPurchaseBean;

public class ReadStallPurchaseUtil
{
    public static StallPurchaseBean getStallPurchaseBean(String path, StringBuffer buffer) {
        LinkedHashMap<String, LinkedHashMap<String, LinkedList<StallPurchase>>> stallPurchaseTree = new LinkedHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                LinkedHashMap<String, LinkedList<StallPurchase>> nodeMap = (LinkedHashMap<String, LinkedList<StallPurchase>>)stallPurchaseTree.get(result[i][1]);
                if (nodeMap == null) {
                    stallPurchaseTree.put(result[i][1], nodeMap = new LinkedHashMap<>());
                }
                LinkedList<StallPurchase> nodeList = (LinkedList<StallPurchase>)nodeMap.get(result[i][2]);
                if (nodeList == null) {
                    nodeMap.put(result[i][2], nodeList = new LinkedList<>());
                }
                StallPurchase stallPurchase = new StallPurchase();
                for (int j = 3; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(stallPurchase, result[i][j], j - 3);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                nodeList.add(stallPurchase);
            }
        }
        StallPurchaseBean stallPurchaseBean = new StallPurchaseBean();
        stallPurchaseBean.setStallPurchaseTree(stallPurchaseTree);
        return stallPurchaseBean;
    }
}
