package org.come.readUtil;

import java.util.Map;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.LianHua;
import java.util.concurrent.ConcurrentHashMap;
import org.come.tool.ReadExelTool;
import org.come.readBean.AllLianHua;

public class ReadLianHuaUtil
{
    public static AllLianHua selectLianHuas(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        Map<Integer, LianHua> list = new ConcurrentHashMap<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                LianHua obj = new LianHua();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(obj, result[i][j], j);
                        list.put(Integer.valueOf(obj.getId()), obj);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
            }
        }
        AllLianHua all = new AllLianHua(list);
        return all;
    }
}
