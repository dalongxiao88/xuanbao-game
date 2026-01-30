package org.come.readUtil;

import org.come.readBean.AllDoorBean;
import org.come.bean.AllNpcBean;
import java.util.ArrayList;
import org.come.bean.NpcInfoBean;
import java.util.Iterator;
import org.come.until.GsonUtil;
import org.come.readBean.AllMapBean;
import java.util.HashMap;
import org.come.model.Talk;
import java.util.Map;
import org.come.model.Door;
import org.come.server.GameServer;
import org.come.model.Npctable;
import java.util.List;
import org.come.tool.SplitStringTool;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Gamemap;
import java.util.concurrent.ConcurrentHashMap;

public class ReadMapUtil
{
    public static ConcurrentHashMap<String, Gamemap> selectAllMap(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Gamemap> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Gamemap gamemap = new Gamemap();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(gamemap, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!gamemap.getMapid().equals("")) {
                    try {
                        Long.parseLong(gamemap.getMapid());
                        if (gamemap.getMapnpc() != null && !gamemap.getMapnpc().equals("")) {
                            List<String> npcs = SplitStringTool.splitString(gamemap.getMapnpc());
                            gamemap.setNpcs(npcs);
                        }
                        map.put(gamemap.getMapid(), gamemap);
                        map.put(gamemap.getMapname(), gamemap);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
            }
        }
        return map;
    }
    
    public static ConcurrentHashMap<String, Npctable> selectallNpc(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Npctable> npctables = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Npctable npctable = new Npctable();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(npctable, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!npctable.getNpcid().equals("")) {
                    npctables.put(npctable.getNpcid(), npctable);
                }
                if (npctable.getNpcid() != null && !npctable.getNpcid().equals("")) {
                    npctable.setMap(GameServer.getMapNpc(npctable.getNpcid()));
                    npctables.put(npctable.getNpcid(), npctable);
                }
            }
        }
        return npctables;
    }
    
    public static ConcurrentHashMap<Integer, Door> selectDoors(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Door> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Door door = new Door();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(door, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (door.getDoorid() != null && !door.getDoorid().equals("")) {
                    try {
                        map.put(Integer.valueOf(Integer.parseInt(door.getDoorid())), door);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
            }
        }
        return map;
    }
    
    public static Map<String, Talk> selectTalks() {
        Map<String, Talk> talks = new HashMap<>();
        String[][] result = ReadExelTool.getResult("config/talk.xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Talk talk = new Talk();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(talk, result[i][j], j);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                talks.put(talk.getTalkid(), talk);
            }
        }
        return talks;
    }
    
    public static String createTxtMap(ConcurrentHashMap<String, Gamemap> map) {
        AllMapBean allMapBean = new AllMapBean();
        Map<String, Gamemap> map2 = new HashMap<>();
        for (Gamemap gamemap : map.values()) {
            map2.put(gamemap.getMapid(), gamemap);
        }
        allMapBean.setAllMap(map2);
        return GsonUtil.getGsonUtil().getgson().toJson(allMapBean);
    }
    
    public static String createTxtNpc(ConcurrentHashMap<String, Npctable> map) {
        Map<String, Talk> talks = selectTalks();
        Map<String, NpcInfoBean> Npcs = new HashMap<>();
        for (String key : map.keySet()) {
            Npctable npctable = (Npctable)map.get(key);
            NpcInfoBean npcInfoBean = new NpcInfoBean();
            npcInfoBean.setNpctable(npctable);
            List<Talk> talkss = new ArrayList<>();
            if (npctable.getTalkid() != null && !npctable.getTalkid().equals("")) {
                List<String> talkList = SplitStringTool.splitString(npctable.getTalkid());
                for (String string : talkList) {
                    Talk talk = (Talk)talks.get(string);
                    if (talk != null) {
                        talkss.add(talk);
                    }
                }
            }
            npcInfoBean.setTalks(talkss);
            Npcs.put(key, npcInfoBean);
        }
        AllNpcBean allNpcBean = new AllNpcBean();
        allNpcBean.setAllNpcInfo(Npcs);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allNpcBean);
        return msgString;
    }
    
    public static AllNpcBean createTxtNpc1(Npctable npctable) {
        Map<String, Talk> talks = selectTalks();
        Map<String, NpcInfoBean> Npcs = new HashMap<>();
        NpcInfoBean npcInfoBean = new NpcInfoBean();
        npcInfoBean.setNpctable(npctable);
        List<Talk> talkss = new ArrayList<>();
        if (npctable.getTalkid() != null && !npctable.getTalkid().equals("")) {
            List<String> talkList = SplitStringTool.splitString(npctable.getTalkid());
            for (String string : talkList) {
                Talk talk = (Talk)talks.get(string);
                if (talk != null) {
                    talkss.add(talk);
                }
            }
        }
        npcInfoBean.setTalks(talkss);
        Npcs.put(npctable.getNpcid(), npcInfoBean);
        AllNpcBean allNpcBean = new AllNpcBean();
        allNpcBean.setAllNpcInfo(Npcs);
        return allNpcBean;
    }
    
    public static String createTxtDoor(ConcurrentHashMap<Integer, Door> map) {
        AllDoorBean allDoorBean = new AllDoorBean();
        Map<String, Door> alldoorMap = new HashMap<>();
        for (Door door : map.values()) {
            alldoorMap.put(door.getDoorid(), door);
        }
        allDoorBean.setAlldoor(alldoorMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allDoorBean);
        return msgString;
    }
}
