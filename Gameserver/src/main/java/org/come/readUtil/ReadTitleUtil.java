package org.come.readUtil;

import java.util.Map;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllTitleBean;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import org.come.server.GameServer;
import org.come.model.Title;
import java.util.List;

public class ReadTitleUtil
{
    public static List<Title> selectTitles(String path, StringBuffer buffer) {
        GameServer.setAlltitleColor(null);
        List<Title> titles = new ArrayList<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Title title = new Title();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(title, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (title.getSkin().equals("")) {
                    title.setSkin(null);
                }
                if (title.getText().equals("")) {
                    title.setText(null);
                }
                if (title.getValue().equals("")) {
                    title.setValue(null);
                }
                titles.add(title);
                if (StringUtils.isNotBlank(title.getColor())) {
                    GameServer.getAlltitleColor().put(title.getTitlename(), title.getColor());
                }
            }
        }
        return titles;
    }
    
    public static ConcurrentHashMap<String, Title> getTitle2(List<Title> titles) {
        ConcurrentHashMap<String, Title> getTitle = new ConcurrentHashMap<>();
        for (int i = titles.size() - 1; i >= 0; --i) {
            Title title = (Title)titles.get(i);
            getTitle.put(title.getTitlename(), title);
            getTitle.put(title.getId() + "", title);
            if (title.getExist().indexOf("充值") == -1) {
                titles.remove(i);
            }
        }
        GameServer.setMoneyTitles(titles);
        return getTitle;
    }
    
    public static String createTitle(List<Title> titles) {
        AllTitleBean allTitleBean = new AllTitleBean();
        Map<String, Title> map = new HashMap<>();
        for (int i = 0; i < titles.size(); ++i) {
            Title title = (Title)titles.get(i);
            map.put(title.getTitlename(), title);
        }
        allTitleBean.setTitleMap(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTitleBean);
        return msgString;
    }
}
