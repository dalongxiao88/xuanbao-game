package org.come.readUtil;

import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.WitchComposeAttr;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ReadSwitchComposeAttrUtil
{
    public static ConcurrentHashMap<String, List<WitchComposeAttr>> getAllData(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<WitchComposeAttr>> getAlldata = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                WitchComposeAttr witchComposeAttr = new WitchComposeAttr();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(witchComposeAttr, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (getAlldata.containsKey(witchComposeAttr.getType())) {
                        ((List<WitchComposeAttr>)getAlldata.get(witchComposeAttr.getType())).add(witchComposeAttr);
                    }
                    else {
                        List<WitchComposeAttr> witchComposeAttrs = new ArrayList<>();
                        witchComposeAttrs.add(witchComposeAttr);
                        getAlldata.put(witchComposeAttr.getType(), witchComposeAttrs);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAlldata;
    }
}
