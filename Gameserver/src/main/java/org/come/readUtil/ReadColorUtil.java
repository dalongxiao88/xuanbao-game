package org.come.readUtil;

import java.util.Iterator;
import java.util.Set;
import org.come.until.GsonUtil;
import java.util.Map;
import java.util.HashMap;
import org.come.readBean.AllColorScheme;
import org.come.server.GameServer;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.ColorScheme;
import java.util.concurrent.ConcurrentHashMap;

public class ReadColorUtil
{
    public static ConcurrentHashMap<String, ColorScheme> selectcolors(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, ColorScheme> allCMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                ColorScheme scheme = new ColorScheme();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(scheme, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                allCMap.put(scheme.getId() + "", scheme);
                allCMap.put(scheme.getName(), scheme);
                if (!scheme.getName().equals("原色")) {
                    GameServer.getAllListColor().add(scheme);
                }
            }
        }
        return allCMap;
    }
    
    public static String createcolor(ConcurrentHashMap<String, ColorScheme> map) {
        AllColorScheme allScheme = new AllColorScheme();
        Map<Integer, ColorScheme> allMap = new HashMap<>();
        Set<Map.Entry<String, ColorScheme>> entrySet = map.entrySet();
        for (Map.Entry<String, ColorScheme> entry : entrySet) {
            allMap.put(Integer.valueOf(((ColorScheme)entry.getValue()).getId()), entry.getValue());
        }
        allScheme.setAllMap(allMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allScheme);
        return msgString;
    }
}
