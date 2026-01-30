package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.readBean.AllGuide;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.HashMap;
import org.come.readBean.RookieGuideBean;
import java.util.Map;

public class ReadGuideUtil
{
    public static Map<Integer, RookieGuideBean> selectSkills(String path, StringBuffer buffer) {
        Map<Integer, RookieGuideBean> map = new HashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                RookieGuideBean bean = new RookieGuideBean();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(bean, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                map.put(Integer.valueOf(bean.getGid()), bean);
            }
        }
        return map;
    }
    
    public static String createSkill(Map<Integer, RookieGuideBean> map) {
        AllGuide allSuit = new AllGuide();
        allSuit.setRookieguide(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allSuit);
        return msgString;
    }
}
