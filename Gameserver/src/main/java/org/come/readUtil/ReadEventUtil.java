package org.come.readUtil;

import java.util.Iterator;
import org.come.readBean.AllEventModelBean;
import java.util.Map;
import come.tool.Role.RoleCard;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.until.GsonUtil;
import org.come.action.lottery.EventRanking;
import java.util.HashMap;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import org.come.model.EventModel;
import java.util.concurrent.ConcurrentHashMap;

public class ReadEventUtil
{
    public static ConcurrentHashMap<Integer, EventModel> selectEvents(String path, StringBuffer buffer) {
        String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "event.txt");
        Map<Integer, RoleCard[]> map = null;
        if (text == null || text.equals("")) {
            map = new HashMap<>();
        }
        else {
            EventRanking eventRanking = (EventRanking)GsonUtil.getGsonUtil().getgson().fromJson(text, EventRanking.class);
            map = eventRanking.getMap();
        }
        ConcurrentHashMap<Integer, EventModel> eMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                EventModel eventModel = new EventModel();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(eventModel, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (eventModel.getgId() != 0) {
                    eventModel.setRoles((RoleCard[])map.get(Integer.valueOf(eventModel.getgId())));
                    eventModel.resetRanking(null, null);
                    eMap.put(Integer.valueOf(eventModel.getgId()), eventModel);
                }
            }
        }
        return eMap;
    }
    
    public static String createEvent(ConcurrentHashMap<Integer, EventModel> map) {
        AllEventModelBean allEventModelBean = new AllEventModelBean();
        Map<Integer, EventModel> allEventModelMap = new HashMap<>();
        for (EventModel model : map.values()) {
            allEventModelMap.put(Integer.valueOf(model.getgId()), model);
        }
        allEventModelBean.setAllEventModelMap(allEventModelMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allEventModelBean);
        return msgString;
    }
}
