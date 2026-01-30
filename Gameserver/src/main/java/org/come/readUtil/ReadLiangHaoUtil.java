package org.come.readUtil;

import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.model.LiangHaoBase;
import java.util.List;

public class ReadLiangHaoUtil
{
    public static List<LiangHaoBase> selectLianghao(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        if (result.length <= 1) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置靓号", "");
            return null;
        }
        List<LiangHaoBase> baseList = new ArrayList<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                LiangHaoBase liangHaoBase = new LiangHaoBase();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(liangHaoBase, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (liangHaoBase.getId() != 0) {
                    baseList.add(liangHaoBase);
                }
            }
        }
        return baseList;
    }
}
