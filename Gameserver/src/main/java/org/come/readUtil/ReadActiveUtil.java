package org.come.readUtil;

import java.util.List;
import org.come.tool.SettModelMemberTool;
import org.come.model.ActiveBase;
import org.come.handler.MainServerHandler;
import come.tool.Good.DropModel;
import org.come.model.ActiveAward;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.readBean.AllActive;

public class ReadActiveUtil
{
    public static AllActive selectActives(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        if (result.length <= 1 || !result[1][0].equals("活跃奖励设置")) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置活跃奖励", "");
            return null;
        }
        List<ActiveAward> awardList = new ArrayList<>();
        for (int i = 1; i < result[1].length; ++i) {
            if (!result[1][i].equals("")) {
                try {
                    String[] vs = result[1][i].split("\\|");
                    ActiveAward activeAward = new ActiveAward(Integer.parseInt(vs[0].split("=")[1]));
                    StringBuffer activeBuffer = new StringBuffer();
                    activeAward.setDropModel(new DropModel(activeBuffer, vs));
                    activeAward.setValue(activeBuffer.toString());
                    activeAward.setJvValue(vs[1]);
                    awardList.add(activeAward);
                }
                catch (Exception e) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, i, result[1][i], MainServerHandler.getErrorMessage(e));
                    return null;
                }
            }
        }
        List<ActiveBase> baseList = new ArrayList<>();
        for (int j = 2; j < result.length; ++j) {
            if (!result[j][0].equals("")) {
                ActiveBase activeBase = new ActiveBase();
                for (int k = 0; k < result[j].length; ++k) {
                    try {
                        SettModelMemberTool.setReflectRelative(activeBase, result[j][k], k);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, j, k, result[j][k], MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
                if (activeBase.getId() != 0) {
                    baseList.add(activeBase);
                }
            }
        }
        AllActive allActive = new AllActive(awardList, baseList);
        return allActive;
    }
    
    public static AllActive selectVipActives(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        List<ActiveBase> baseList = new ArrayList<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                ActiveBase activeBase = new ActiveBase();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(activeBase, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (activeBase.getId() != 0) {
                    baseList.add(activeBase);
                }
            }
        }
        AllActive allActive = new AllActive(baseList);
        return allActive;
    }
}
