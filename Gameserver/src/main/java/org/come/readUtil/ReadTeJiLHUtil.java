package org.come.readUtil;

import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.TeJiLH;
import java.util.concurrent.ConcurrentHashMap;

public class ReadTeJiLHUtil
{
    public static ConcurrentHashMap<Integer, TeJiLH> getAlllhtj(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, TeJiLH> getAlllhtj = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                TeJiLH TeJiLH = new TeJiLH();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(TeJiLH, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (TeJiLH.getId() != 0) {
                        getAlllhtj.put(Integer.valueOf(TeJiLH.getId()), TeJiLH);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAlllhtj;
    }
}
