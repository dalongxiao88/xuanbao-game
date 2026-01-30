package com.tool.ModerateTask;

import java.math.BigDecimal;
import com.tool.role.SkillUtil;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.util.List;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.Util;
import org.come.until.SplitStringTool;
import org.come.until.UserMessUntil;

public class TaskMixDeal
{
    public static long time;
    
    public static String taskoption(String id) {
        TaskData taskData = UserMessUntil.getTaskData(Integer.parseInt(id));
        if (taskData == null) {
            return null;
        }
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            Task task = (Task)hero.getDoingTasks().get(i);
            if (task.getTaskData().getTaskName().equals(taskData.getTaskName())) {
                if (task.getTaskState() == 2) {
                    return "我要取消" + taskData.getTaskName();
                }
                if (task.getTaskState() == 3) {
                    return "我要领取" + taskData.getTaskName() + "奖励";
                }
            }
        }
        if (taskData.getTaskID() >= 3157 && taskData.getTaskID() <= 3500) {
            int ID = TaskRoleData.SumReceive(taskData.getTaskSetID(), 3);
            if (taskData.getTaskID() != ID + 1) {
                return null;
            }
        }
        return taskData.getTaskOpen();
    }
    
    public static TaskProgress KillNpc(int npcid) {
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            Task task = (Task)hero.getDoingTasks().get(i);
            for (int j = 0; j < task.getProgress().size(); ++j) {
                TaskProgress progress = (TaskProgress)task.getProgress().get(j);
                if (progress.getType() == 2 && progress.getSum() < progress.getMax() && progress.getDId() == npcid) {
                    return progress;
                }
            }
        }
        return null;
    }
    
	public static boolean isoption(int taskSetID, String msg, String npcway) {
		if (npcway == null || npcway.equals("")) {
			return false;
		}
		Hero hero = Hero.getHero();
		String[] v = npcway.split(" ");
		int i = 0;
		LOOP: for (i = 0; i < v.length; i++) {
			List<String> taskids = SplitStringTool.splitString(v[i]);
			for (int t = 0; t < taskids.size(); t++) {
				TaskData taskData1 = UserMessUntil.getTaskData(Integer.parseInt(taskids.get(t)));
				if (taskData1 != null)
					for (int j = 0; j < hero.getDoingTasks().size(); j++) {
						Task task = hero.getDoingTasks().get(j);
						if (task.getTaskData().getTaskID() == taskData1.getTaskID() && task.getTaskState() == 2) {
							if (msg == null)
								continue LOOP;
							if (msg.equals("我要取消" + taskData1.getTaskName())) {
								if (Util.getTime() < time + 1000L) {
									ZhuFrame.getZhuJpanel().addPrompt2("你需要等待1分钟才能再一次取消任务");
									return true;
								}
								time = Util.getTime();
								String mes = Agreement.getAgreement().TaskNAgreement("E" + task.getTaskId());
								SendMessageUntil.toServer(mes);
								return true;
							}
						}
					}
			}
			TaskData taskData2 = UserMessUntil.getTaskData(Integer.parseInt((String) taskids.get(0)));
			if (taskData2 != null) {
				if (!canGetTask(taskData2)) {
					ZhuFrame.getZhuJpanel().addPrompt2("当前角色无法获取这个任务");
					return false;
				}
				if (taskSetID != 0) {
					if (taskSetID == taskData2.getTaskSetID()) {
						String taskId = (String) taskids.get(Util.random.nextInt(taskids.size()));
						String mes2 = Agreement.getAgreement().TaskNAgreement("L" + taskId);
						SendMessageUntil.toServer(mes2);
						return true;
					}
				} else if (taskData2.getTaskOpen().equals(msg)) {
					String taskId = (String) taskids.get(Util.random.nextInt(taskids.size()));
					String mes2 = Agreement.getAgreement().TaskNAgreement("L" + taskId);
					SendMessageUntil.toServer(mes2);
					return true;
				}
			}
		}

		return false;
	}
    
    public static boolean isoptionTaskId(int taskSetID, String msg, String npcway) {
    	if (npcway == null || npcway.equals(""))
			return false;
		Hero hero = Hero.getHero();
		String[] v = npcway.split(" ");
		int i;
		LOOP: for (i = 0; i < v.length; i++) {
			List<String> taskids = SplitStringTool.splitString(v[i]);
			for (int t = 0; t < taskids.size(); t++) {
				TaskData taskData1 = UserMessUntil.getTaskData(Integer.parseInt(taskids.get(t)));
				if (taskData1 != null)
					for (int j = 0; j < hero.getDoingTasks().size(); j++) {
						Task task = hero.getDoingTasks().get(j);
						if (task.getTaskData().getTaskID() == taskData1.getTaskID() && task.getTaskState() == 2) {
							if (msg == null)
								continue LOOP;
							if (msg.equals("我要取消" + taskData1.getTaskName())) {
								if (Util.getTime() < time + 1000L) {
									ZhuFrame.getZhuJpanel().addPrompt2("你需要等待1分钟才能再一次取消任务");
									return true;
								}
								time = Util.getTime();
								String mes = Agreement.getAgreement().TaskNAgreement("E" + task.getTaskId());
								SendMessageUntil.toServer(mes);
								return true;
							}
						}
					}
			}
			TaskData taskData = UserMessUntil.getTaskData(Integer.parseInt(taskids.get(0)));
			if (taskData != null) {
				if (!canGetTask(taskData)) {
					ZhuFrame.getZhuJpanel().addPrompt2("当前角色无法获取这个任务");
					return false;
				}
				if (taskSetID != 0) {
					if (taskSetID == taskData.getTaskID()) {
						String taskId = taskids.get(Util.random.nextInt(taskids.size()));
						String mes = Agreement.getAgreement().TaskNAgreement("L" + taskId);
						SendMessageUntil.toServer(mes);
						return true;
					}
				} else if (taskData.getTaskOpen().equals(msg)) {
					String taskId = taskids.get(Util.random.nextInt(taskids.size()));
					String mes = Agreement.getAgreement().TaskNAgreement("L" + taskId);
					SendMessageUntil.toServer(mes);
					return true;
				}
			}
		}
		return false;
    }
    
    public static boolean canGetTask(TaskData taskData) {
        RoleData roleData = RoleData.getRoleData();
        BigDecimal speciesId = roleData.getLoginResult().getSpecies_id();
        if (StringUtils.isNotBlank(taskData.getSnames())) {
            String[] snames = taskData.getSnames().split("\\|");
            String sn = SkillUtil.getSepciesN(speciesId);
            for (String sname : snames) {
                if (StringUtils.isNotBlank(sn) && StringUtils.isNotBlank(sname) && sn.equals(sname)) {
                    return true;
                }
            }
        }
        return StringUtils.isEmpty(taskData.getSnames());
    }
    
    public static Task isnpc(int npcid) {
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            Task task = (Task)hero.getDoingTasks().get(i);
            for (int j = 0; j < task.getProgress().size(); ++j) {
                TaskProgress progress = (TaskProgress)task.getProgress().get(j);
                if (progress.getType() == 2 && progress.getDId() == npcid) {
                    return task;
                }
            }
        }
        return null;
    }
    
    public static Task isrobot(TaskProgress taskProgress) {
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            Task task = (Task)hero.getDoingTasks().get(i);
            for (int j = 0; j < task.getProgress().size(); ++j) {
                TaskProgress progress = (TaskProgress)task.getProgress().get(j);
                if ((progress.getType() == 0 || progress.getType() == 1) && taskProgress.getDId() == progress.getDId()) {
                    return task;
                }
            }
        }
        return null;
    }
    
    public static boolean istrigger(int npcid, TaskProgress taskProgress) {
        Task task = null;
        if (taskProgress != null) {
            task = isrobot(taskProgress);
        }
        else {
            task = isnpc(npcid);
        }
        return task != null && CreateTask.getCreateTask().isReceive2(task);
    }
    
    public static boolean istp() {
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            TaskData taskData = ((Task)hero.getDoingTasks().get(i)).getTaskData();
            if (taskData.getIsTP() == 1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isgive(String npc) {
        Hero hero = Hero.getHero();
        for (int i = 0; i < hero.getDoingTasks().size(); ++i) {
            Task task = (Task)hero.getDoingTasks().get(i);
            for (int j = 0; j < task.getProgress().size(); ++j) {
                TaskProgress taskProgress = (TaskProgress)task.getProgress().get(j);
                if (taskProgress.getType() == 4 && taskProgress.getGName().equals(npc)) {
                    return true;
                }
            }
        }
        return false;
    }
}
