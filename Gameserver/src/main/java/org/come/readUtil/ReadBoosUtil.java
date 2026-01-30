package org.come.readUtil;

import org.come.model.ShaoXiang;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.model.TaskTerm;
import java.util.Iterator;
import org.come.model.Npctable;
import org.come.model.TaskData;
import java.util.HashMap;
import org.come.server.GameServer;
import java.util.Map;
import come.tool.Good.DropModel;
import org.come.tool.SplitStringTool;
import come.tool.Good.HpSet;
import come.tool.Good.TSModel;
import org.come.model.Robots;
import come.tool.FightingData.Ql;
import come.tool.FightingData.GetqualityUntil;
import org.come.model.Monster;
import java.util.concurrent.ConcurrentHashMap;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import org.come.model.Boos;
import java.util.List;
import java.util.Random;

public class ReadBoosUtil
{
    public static Random random;
    
    public static List<Boos> selectBoos(String path, StringBuffer buffer) {
        List<Boos> booses = new ArrayList<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Boos boos = new Boos();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(boos, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (!boos.getBoosid().equals("")) {
                    if (boos.getBoosstime() < 0 || boos.getBoosstime() > 24) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, result[i][0], "开始时间违规");
                        return null;
                    }
                    if (boos.getBoosetime() < 0 || boos.getBoosetime() > 24) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, result[i][0], "结束时间违规");
                        return null;
                    }
                    int value = boos.getBoosrtime() / 5;
                    if (value <= 0) {
                        value = 24;
                    }
                    boos.setBoosrtime(value * 5);
                    if (boos.getBoosDrop() == null || boos.getBoosDrop().equals("")) {
                        boos.setBoosDropMax(0);
                    }
                    booses.add(boos);
                }
            }
        }
        return booses;
    }
    
    public static ConcurrentHashMap<String, Monster> getMonster(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Monster> monsterMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Monster monster = new Monster();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(monster, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (monster.getMonsterid() != 0) {
                    try {
                        Ql ql = GetqualityUntil.getMonsterQl(monster);
                        ql.setRoleklb(ql.getRoleklb() - 100.0);
                        ql.setKzds(ql.getKzds() - 4000.0);
                        monster.setQl(ql);
                        monsterMap.put(monster.getMonsterid() + "", monster);
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                        return null;
                    }
                }
            }
        }
        return monsterMap;
    }
    
    public static ConcurrentHashMap<String, Robots> getRobot(String path, StringBuffer buffer) {
        Map<Integer, List<Integer>> robotMap = getTaskIDs();
        ConcurrentHashMap<String, Robots> allRobotMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Robots robot = new Robots();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        if (j == 14) {
                            if (!result[i][j].equals("")) {
                                if (result[i][j].startsWith("读条")) {
                                    robot.setEwParam(result[i][j]);
                                }
                                else {
                                    robot.setTsModel(new TSModel(result[i][j]));
                                }
                            }
                        }
                        else if (j == 15) {
                            if (!result[i][j].equals("")) {
                                robot.setHpSet(new HpSet(result[i][j]));
                            }
                        }
                        else {
                            SettModelMemberTool.setReflect(robot, result[i][j], j);
                        }
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (robot.getRobotid() != null && !robot.getRobotid().equals("")) {
                        robot.setRobotID(Integer.parseInt(robot.getRobotid()));
                        if (robot.getLvllimit() != null && !robot.getLvllimit().equals("")) {
                            int[] lvls = { 0, 0, 4, 200, 200 };
                            String[] lvls2 = robot.getLvllimit().split("\\|");
                            String[] vs = lvls2[0].split("\\-");
                            lvls[1] = Integer.parseInt(vs[0]);
                            lvls[3] = Integer.parseInt(vs[1]);
                            if (lvls2.length >= 2) {
                                lvls[0] = Integer.parseInt(lvls2[1]);
                            }
                            if (lvls2.length == 3) {
                                vs = lvls2[2].split("\\-");
                                lvls[2] = Integer.parseInt(vs[0]);
                                lvls[4] = Integer.parseInt(vs[1]);
                            }
                            robot.setLvls(lvls);
                        }
                        robot.setTaskIds((List<Integer>)robotMap.get(Integer.valueOf(Integer.parseInt(robot.getRobotid()))));
                        if (robot.getRobotmonster() != null && !robot.getRobotmonster().equals("")) {
                            robot.setMonsterList(SplitStringTool.splitString(robot.getRobotmonster()));
                        }
                        if (robot.getRobotreward() != null && !robot.getRobotreward().equals("")) {
                            robot.setDropModel(new DropModel(robot.getRobotreward().split("\\|")));
                        }
                        if (robot.getRobotname().indexOf("#") != -1) {
                            robot.setRobotname(robot.getRobotname().replace("#", " "));
                        }
                        if (robot.getRobotname().indexOf("-") != -1) {
                            robot.setRobotname(robot.getRobotname().replace("-", " "));
                        }
                        if (robot.getRobotname().indexOf("|") != -1) {
                            robot.setRobotname(robot.getRobotname().replace("|", " "));
                        }
                        allRobotMap.put(robot.getRobotid(), robot);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return allRobotMap;
    }
    
    public static Map<Integer, List<Integer>> getTaskIDs() {
        ConcurrentHashMap<String, Npctable> npcMap = GameServer.getNpcMap();
        ConcurrentHashMap<Integer, TaskData> taskMap = GameServer.getAllTaskData();
        Map<Integer, List<Integer>> robotMap = new HashMap<>();
        for (TaskData taskData : taskMap.values()) {
            if (taskData.getTaskTerms() == null) {
                continue;
            }
            else {
                TaskTerm[] taskTerms = taskData.getTaskTerms();
                for (int i = 0; i < taskTerms.length; ++i) {
                    TaskTerm taskTerm = taskTerms[i];
                    if (taskTerm.getType() == 0 || taskTerm.getType() == 1) {
                        for (int j = 0; j < taskTerm.getdList().size(); ++j) {
                            int robotId = Integer.parseInt((String)taskTerm.getdList().get(j));
                            List<Integer> list = (List<Integer>)robotMap.get(Integer.valueOf(robotId));
                            if (list == null) {
                                list = new ArrayList<>();
                                robotMap.put(Integer.valueOf(robotId), list);
                            }
                            if (!list.contains(Integer.valueOf(taskData.getTaskID()))) {
                                list.add(Integer.valueOf(taskData.getTaskID()));
                                robotMap.put(Integer.valueOf(robotId), list);
                            }
                        }
                    }
                    else if (taskTerm.getType() == 2) {
                        for (int j = 0; j < taskTerm.getdList().size(); ++j) {
                            Npctable npctable = (Npctable)npcMap.get(taskTerm.getdList().get(j));
                            if (npctable != null && npctable.getBinding() != null && !npctable.getBinding().equals("")) {
                                int robotId2 = Integer.parseInt(npctable.getBinding());
                                List<Integer> list2 = (List<Integer>)robotMap.get(Integer.valueOf(robotId2));
                                if (list2 == null) {
                                    list2 = new ArrayList<>();
                                    robotMap.put(Integer.valueOf(robotId2), list2);
                                }
                                if (!list2.contains(Integer.valueOf(taskData.getTaskID()))) {
                                    list2.add(Integer.valueOf(taskData.getTaskID()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return robotMap;
    }
    
    public static List<String> removeDuplicate(List<String> list) {
        for (int i = 0; i < list.size() - 1; ++i) {
            for (int j = list.size() - 1; j > i; --j) {
                if (((String)list.get(j)).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }
    
    public static ConcurrentHashMap<String, Boos> boosesMap(List<Boos> booses) {
        ConcurrentHashMap<String, Boos> boosMap = new ConcurrentHashMap<>();
        for (Boos boos : booses) {
            boosMap.put(boos.getBoosid(), boos);
        }
        return boosMap;
    }
    
    public static List<Integer> randomArray(int max, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int ran = ReadBoosUtil.random.nextInt(max);
            result.add(Integer.valueOf(ran));
        }
        return result;
    }
    
    public static ConcurrentHashMap<String, List<String>> getRobotByGoods(ConcurrentHashMap<String, Robots> map) {
        ConcurrentHashMap<String, List<String>> goodsByRobot = new ConcurrentHashMap<>();
        ConcurrentHashMap<BigDecimal, Goodstable> map2 = GameServer.getAllGoodsMap();
        for (Robots robot : map.values()) {
            String itemStr = robot.getRobotreward();
            String[] items;
            for (String item : items = itemStr.split("\\|")) {
                if (item.startsWith("物品")) {
                    String[] n;
                    for (String nn : n = item.split("&")) {
                        if (!nn.startsWith("物品")) {
                            String[] m = nn.split("\\$");
                            String[] ids;
                            for (String id : ids = m[0].split("-")) {
                                Goodstable goods = (Goodstable)map2.get(new BigDecimal(id));
                                if (goods != null) {
                                    List<String> robotName;
                                    if (goodsByRobot.containsKey(goods.getGoodsname())) {
                                        robotName = (List<String>)goodsByRobot.get(goods.getGoodsname());
                                        if (!robotName.contains(robot.getRobotname())) {
                                            robotName.add(robot.getRobotname());
                                        }
                                        else {
                                            continue;
                                        }
                                    }
                                    else {
                                        robotName = new ArrayList<>();
                                        robotName.add(robot.getRobotname());
                                    }
                                    goodsByRobot.put(goods.getGoodsname(), robotName);
                                }
                            }
                        }
                    }
                }
            }
        }
        return goodsByRobot;
    }
    
    public static ConcurrentHashMap<String, List<String>> setDrop(ConcurrentHashMap<String, List<String>> goodsByRobot, String itemStr, String dropName) {
        ConcurrentHashMap<BigDecimal, Goodstable> map2 = GameServer.getAllGoodsMap();
        String[] items;
        for (String item : items = itemStr.split("\\|")) {
            if (item.startsWith("物品")) {
                String[] n;
                for (String nn : n = item.split("&")) {
                    if (!nn.startsWith("物品")) {
                        String[] m = nn.split("\\$");
                        String[] ids;
                        for (String id : ids = m[0].split("-")) {
                            Goodstable goods = (Goodstable)map2.get(new BigDecimal(id));
                            if (goods != null) {
                                List<String> robotName;
                                if (goodsByRobot.containsKey(goods.getGoodsname())) {
                                    robotName = (List<String>)goodsByRobot.get(goods.getGoodsname());
                                    if (!robotName.contains(dropName)) {
                                        robotName.add(dropName);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                else {
                                    robotName = new ArrayList<>();
                                    robotName.add(dropName);
                                }
                                goodsByRobot.put(goods.getGoodsname(), robotName);
                            }
                        }
                    }
                }
            }
        }
        return goodsByRobot;
    }
    
    public static ConcurrentHashMap<String, ShaoXiang> getShaoXiang(String path, StringBuffer buffer) {
        Map<Integer, List<Integer>> robotMap = getTaskIDs();
        ConcurrentHashMap<String, ShaoXiang> allRobotMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                ShaoXiang shaoXiang = new ShaoXiang();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(shaoXiang, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (shaoXiang.getId() != null && !shaoXiang.getId().equals("")) {
                        allRobotMap.put(shaoXiang.getName(), shaoXiang);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return allRobotMap;
    }
    
    static {
        ReadBoosUtil.random = new Random();
    }
}
