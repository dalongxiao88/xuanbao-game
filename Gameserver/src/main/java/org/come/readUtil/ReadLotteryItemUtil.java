package org.come.readUtil;

import org.come.model.LotteryItemBasetwo;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.model.LotteryItemBase;
import java.util.List;

public class ReadLotteryItemUtil
{
    public static List<LotteryItemBase> selectLotterys(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        if (result.length <= 1) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置翻翻乐奖励", "");
            return null;
        }
        List<LotteryItemBase> baseList = new ArrayList<>();
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                LotteryItemBase lotteryItemBase = new LotteryItemBase();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(lotteryItemBase, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (lotteryItemBase.getId() != null) {
                    baseList.add(lotteryItemBase);
                }
            }
        }
        return baseList;
    }
    
    public static List<LotteryItemBasetwo> selectLotterystwo(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResulttwo("config/" + path + ".xls");
        if (result.length <= 1) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置翻翻乐奖励", "");
            return null;
        }
        List<LotteryItemBasetwo> baseList = new ArrayList<>();
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                LotteryItemBasetwo lotteryItemBase = new LotteryItemBasetwo();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(lotteryItemBase, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (lotteryItemBase.getId() != null) {
                    baseList.add(lotteryItemBase);
                }
            }
        }
        return baseList;
    }
}
