package org.come.readUtil;

import java.util.List;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.bean.Meridians;
import java.util.ArrayList;
import org.come.tool.ReadExelTool;
import org.come.readBean.AllMeridians;

public class ReadMeridiansUtil
{
    public static AllMeridians selectMeridians(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        AllMeridians all = new AllMeridians();
        List<Meridians> list = new ArrayList<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Meridians obj = new Meridians();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(obj, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                list.add(obj);
            }
        }
        all.setMeridians(list);
        return all;
    }
}
