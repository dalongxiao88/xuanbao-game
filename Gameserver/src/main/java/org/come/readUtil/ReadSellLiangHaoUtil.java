package org.come.readUtil;

import java.util.Map;
import org.come.until.AllServiceUtil;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import java.util.ArrayList;
import org.come.server.GameServer;
import java.util.HashMap;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.model.SellLiangHaoBase;
import java.util.List;

public class ReadSellLiangHaoUtil
{
    public static List<SellLiangHaoBase> selectLianghao(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResultChooseSheet("config/" + path + ".xls", 0);
        if (result.length <= 1) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置靓号", "");
            return null;
        }
        String startDate = null;
        String endDate = null;
        if (result[0] != null && result[0].length == 4 && result[0][1] != null) {
            startDate = result[0][1];
            endDate = result[0][3];
        }
        if (result[1] != null && result[1].length == 4 && result[1][1] != null) {
            Map<String, String> xjDate = new HashMap<>();
            xjDate.put("start", result[1][1]);
            xjDate.put("end", result[1][3]);
            GameServer.setXjDate(xjDate);
        }
        List<SellLiangHaoBase> baseList = new ArrayList<>();
        for (int i = 3; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                SellLiangHaoBase selllianghaobase = new SellLiangHaoBase();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(selllianghaobase, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                List<String> clhs = AllServiceUtil.getRoleTableService().allLiangHao();
                if (startDate != null && endDate != null && selllianghaobase.getId() != 0 && !clhs.contains(selllianghaobase.getLianghao())) {
                    selllianghaobase.setAucStartTime(startDate);
                    selllianghaobase.setAucEndTime(endDate);
                    baseList.add(selllianghaobase);
                }
            }
        }
        return baseList;
    }
}
