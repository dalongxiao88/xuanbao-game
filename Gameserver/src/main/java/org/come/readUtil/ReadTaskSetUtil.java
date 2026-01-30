package org.come.readUtil;

import java.util.Iterator;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import org.come.model.TaskTime;
import come.tool.Good.DropModel;
import org.come.model.TaskTerm;
import org.come.server.GameServer;
import java.util.ArrayList;
import org.come.model.TaskData;
import java.util.List;
import org.come.tool.SplitStringTool;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.TaskSet;
import java.util.concurrent.ConcurrentHashMap;

public class ReadTaskSetUtil
{
    public static ConcurrentHashMap<Integer, TaskSet> selectTaskSet(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, TaskSet> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                TaskSet taskSet = new TaskSet();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(taskSet, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (taskSet.getTaskSetID() != 0) {
                    if (taskSet.getTaskMsg() != null && taskSet.getTaskMsg().equals("")) {
                        taskSet.setTaskMsg(null);
                    }
                    map.put(Integer.valueOf(taskSet.getTaskSetID()), taskSet);
                    try {
                        if (taskSet.getRobots() != null && !taskSet.getRobots().equals("")) {
                            List<String> list = SplitStringTool.splitString(taskSet.getRobots());
                            for (int k = 0, length = list.size(); k < length; ++k) {
                                int robotID = -Integer.parseInt((String)list.get(k));
                                if (robotID >= 0) {
                                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, k, "", "robot出现负数ID");
                                    return null;
                                }
                                TaskSet set = (TaskSet)map.get(Integer.valueOf(robotID));
                                if (set != null) {
                                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, k, "", "robot出现重复");
                                    return null;
                                }
                                map.put(Integer.valueOf(robotID), taskSet);
                            }
                        }
                    }
                    catch (Exception e2) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, taskSet.getRobots(), "taskset的robot数据解析错误");
                        return null;
                    }
                }
            }
        }
        TaskSet taskSet2 = new TaskSet();
        taskSet2.setTaskSetID(2);
        taskSet2.setTaskType("日常活跃");
        taskSet2.setResetcycle(1);
        map.put(Integer.valueOf(taskSet2.getTaskSetID()), taskSet2);
        TaskSet taskSet3 = new TaskSet();
        taskSet3.setTaskSetID(3);
        taskSet3.setTaskType("单人竞技场");
        taskSet3.setResetcycle(1);
        map.put(Integer.valueOf(taskSet3.getTaskSetID()), taskSet3);
        TaskSet taskSet4 = new TaskSet();
        taskSet4.setTaskSetID(4);
        taskSet4.setTaskType("单人竞技场");
        map.put(Integer.valueOf(taskSet4.getTaskSetID()), taskSet4);
        return map;
    }
    
    public static ConcurrentHashMap<Integer, TaskData> selectTaskData(String path, StringBuffer buffer) {
        GameServer.taskData = new ArrayList<>();
        ConcurrentHashMap<Integer, TaskData> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                TaskData taskData = new TaskData();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(taskData, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (taskData.getTaskID() != 0) {
                        if (taskData.getLvl() != null && !taskData.getLvl().equals("")) {
                            int[] lvls = { 0, 0, 4, 200, 200 };
                            String[] lvls2 = taskData.getLvl().split("\\|");
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
                            taskData.setLvls(lvls);
                        }
                        if (taskData.getConsume() != null && !taskData.getConsume().equals("")) {
                            taskData.setConsumes(taskData.getConsume().split("\\|"));
                        }
                        if (taskData.getPostTaskId() != null && !taskData.getPostTaskId().equals("") && !taskData.getPostTaskId().equals("0")) {
                            List<String> list = SplitStringTool.splitString(taskData.getPostTaskId());
                            if (list.size() != 0) {
                                List<Integer> integers = new ArrayList<>();
                                for (int k = 0; k < list.size(); ++k) {
                                    integers.add(Integer.valueOf(Integer.parseInt((String)list.get(k))));
                                }
                                taskData.setPostTaskIds(integers);
                            }
                        }
                        if (taskData.getFinishTerm() != null && !taskData.getFinishTerm().equals("")) {
                            String[] vs2 = taskData.getFinishTerm().split(" ");
                            TaskTerm[] taskTerms = new TaskTerm[vs2.length];
                            for (int k = 0; k < taskTerms.length; ++k) {
                                taskTerms[k] = new TaskTerm(vs2[k]);
                            }
                            taskData.setTaskTerms(taskTerms);
                        }
                        if (taskData.getTaskAward() != null && !taskData.getTaskAward().equals("")) {
                            taskData.setDropModel(new DropModel(taskData.getTaskAward().split("\\|")));
                        }
                        if (taskData.getOpenTime() != null && !taskData.getOpenTime().equals("")) {
                            String[] vs2 = taskData.getOpenTime().split("\\|");
                            TaskTime[] taskTimes = new TaskTime[vs2.length];
                            for (int k = 0; k < taskTimes.length; ++k) {
                                taskTimes[k] = new TaskTime();
                                String[] times = vs2[k].split("-");
                                int week = Integer.parseInt(times[0]);
                                if (week == 7) {
                                    week = 0;
                                }
                                taskTimes[k].setWeek(week);
                                taskTimes[k].setStartTime(Integer.parseInt(times[1]));
                                taskTimes[k].setEndTime(Integer.parseInt(times[2]));
                            }
                            taskData.setTaskTimes(taskTimes);
                        }
                        if (taskData.getTaskText() != null && taskData.getTaskText().equals("")) {
                            taskData.setTaskText(null);
                        }
                        if (taskData.getTaskSet() == null) {
                            UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", "找不到对应的系列id任务id为:" + taskData.getTaskID());
                        }
                        if (taskData.getTaskSet() != null && taskData.getTaskSet().getTaskSetID() == 600) {
                            GameServer.taskData.add(taskData);
                        }
                        map.put(Integer.valueOf(taskData.getTaskID()), taskData);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return map;
    }
    
    public static ConcurrentHashMap<String, List<String>> getTaskDrop(ConcurrentHashMap<String, List<String>> goodsByRobot) {
        ConcurrentHashMap<BigDecimal, Goodstable> map2 = GameServer.getAllGoodsMap();
        for (TaskData data : GameServer.getAllTaskData().values()) {
            String itemStr = data.getTaskAward();
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
                                        if (!robotName.contains(data.getTaskName())) {
                                            robotName.add(data.getTaskName());
                                        }
                                        else {
                                            continue;
                                        }
                                    }
                                    else {
                                        robotName = new ArrayList<>();
                                        robotName.add(data.getTaskName());
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
}
