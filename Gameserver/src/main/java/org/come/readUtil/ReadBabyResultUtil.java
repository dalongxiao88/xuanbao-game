package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.readBean.AllBabyResult;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import org.come.readBean.BabyResult;
import java.util.List;

public class ReadBabyResultUtil
{
    private static List<BabyResult> allBabyResults;
    
    public static List<BabyResult> selectBabyResult(String path, StringBuffer buffer) {
        ReadBabyResultUtil.allBabyResults = new ArrayList<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                BabyResult babyResult = new BabyResult();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(babyResult, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                ReadBabyResultUtil.allBabyResults.add(babyResult);
            }
        }
        return ReadBabyResultUtil.allBabyResults;
    }
    
    public static String creatbabyresult(List<BabyResult> list) {
        AllBabyResult allBabyResult = new AllBabyResult();
        allBabyResult.setAllBabyResults(list);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allBabyResult);
        return msgString;
    }
}
