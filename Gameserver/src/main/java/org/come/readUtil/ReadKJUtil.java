package org.come.readUtil;

import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.action.wqx.KJexamQuestions;
import java.util.concurrent.ConcurrentHashMap;

public class ReadKJUtil
{
    public static ConcurrentHashMap<Integer, KJexamQuestions> getAllKJ(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, KJexamQuestions> getAllKJexamQuestions = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                KJexamQuestions kJexamQuestions = new KJexamQuestions();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(kJexamQuestions, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (StringUtils.isNotBlank(kJexamQuestions.getTopicText())) {
                    getAllKJexamQuestions.put(kJexamQuestions.getId(), kJexamQuestions);
                }
            }
        }
        return getAllKJexamQuestions;
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
