package org.come.readUtil;

import java.util.Iterator;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Xianqi;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ReadXianqiUtil
{
    public static ConcurrentHashMap<String, List<Xianqi>> getAllXianqi(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<Xianqi>> getAllXianqi = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Xianqi xianqi = new Xianqi();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(xianqi, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!xianqi.getXianid().equals("")) {
                    String type = xianqi.getXianvalue().split("\\|")[0];
                    List<Xianqi> xianqiList = (List<Xianqi>)getAllXianqi.get(type);
                    if (xianqiList == null) {
                        xianqiList = new ArrayList<>();
                        getAllXianqi.put(type, xianqiList);
                    }
                    xianqiList.add(xianqi);
                }
            }
        }
        return getAllXianqi;
    }
    
    public static ConcurrentHashMap<String, List<String>> getXianqiType(ConcurrentHashMap<String, List<Xianqi>> map) {
        ConcurrentHashMap<String, List<String>> getXianqiType = new ConcurrentHashMap<>();
        for (List<Xianqi> list : map.values()) {
            for (Xianqi xianqi : list) {
                List<String> xianqiList = (List<String>)getXianqiType.get(xianqi.getXiantype());
                if (xianqiList == null) {
                    xianqiList = new ArrayList<>();
                    getXianqiType.put(xianqi.getXiantype(), xianqiList);
                }
                xianqiList.add(xianqi.getXianvalue());
            }
        }
        return getXianqiType;
    }
}
