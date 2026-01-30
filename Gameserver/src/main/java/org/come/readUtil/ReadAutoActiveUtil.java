package org.come.readUtil;

import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.model.AutoActiveBase;
import java.util.List;

public class ReadAutoActiveUtil
{
    public static List<AutoActiveBase> selectActives(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        if (result.length <= 1) {
            UpXlsAndTxtFile.addStringBufferMessage(buffer, 1, 0, "未设置自动任务", "");
            return null;
        }
        List<AutoActiveBase> baseList = new ArrayList<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                AutoActiveBase activeBase = new AutoActiveBase();
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
        return baseList;
    }
}
