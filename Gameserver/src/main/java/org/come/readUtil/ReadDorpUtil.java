package org.come.readUtil;

import come.tool.Scene.DNTG.DNTGAward;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Dorp;
import java.util.concurrent.ConcurrentHashMap;

public class ReadDorpUtil
{
    public static ConcurrentHashMap<String, Dorp> allDorpInfoByID(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Dorp> dorpInfo = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Dorp dorp = new Dorp();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(dorp, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (dorp.getDorpId() != null) {
                    dorpInfo.put(dorp.getDorpType(), dorp);
                }
            }
        }
        return dorpInfo;
    }
    
    public static ConcurrentHashMap<Integer, DNTGAward> selectDNTGAwards(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, DNTGAward> allDntg = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/dntg.xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                DNTGAward dntgAward = new DNTGAward();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(dntgAward, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (dntgAward.getBh() != 0) {
                    allDntg.put(Integer.valueOf(dntgAward.getBh()), dntgAward);
                }
            }
        }
        return allDntg;
    }
}
