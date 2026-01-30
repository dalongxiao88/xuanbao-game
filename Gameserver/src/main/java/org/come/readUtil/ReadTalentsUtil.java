package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllTalent;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Talent;
import java.util.concurrent.ConcurrentHashMap;

public class ReadTalentsUtil
{
    public static ConcurrentHashMap<Integer, Talent> selectTalents(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Talent> alltMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Talent talent = new Talent();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(talent, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (talent.getId() != 0) {
                    alltMap.put(Integer.valueOf(talent.getId()), talent);
                }
            }
        }
        return alltMap;
    }
    
    public static String createTalent(ConcurrentHashMap<Integer, Talent> map) {
        AllTalent allTalent = new AllTalent();
        Map<Integer, Talent> allMap = new HashMap<>();
        for (Talent talent : map.values()) {
            allMap.put(Integer.valueOf(talent.getId()), talent);
        }
        allTalent.setAllTalent(allMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTalent);
        return msgString;
    }
}
