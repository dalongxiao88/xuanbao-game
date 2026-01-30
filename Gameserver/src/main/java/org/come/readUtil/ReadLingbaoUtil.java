package org.come.readUtil;

import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.Lingbao;
import java.util.concurrent.ConcurrentHashMap;

public class ReadLingbaoUtil
{
    public static ConcurrentHashMap<String, Lingbao> getAllLingbao(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Lingbao> getAllLingbao = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Lingbao lingbao = new Lingbao();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(lingbao, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (lingbao.getBaoname() != null && !lingbao.getBaoname().equals("")) {
                    getAllLingbao.put(lingbao.getBaoname(), lingbao);
                }
            }
        }
        return getAllLingbao;
    }
    
    public static ConcurrentHashMap<BigDecimal, Goodstable> getAllLingbaoFushi(String path, StringBuffer buffer) {
        ConcurrentHashMap<BigDecimal, Goodstable> getAllGoodstable = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 0; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Goodstable Goodstable = new Goodstable();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(Goodstable, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                getAllGoodstable.put(Goodstable.getGoodsid(), Goodstable);
            }
        }
        return getAllGoodstable;
    }
}
