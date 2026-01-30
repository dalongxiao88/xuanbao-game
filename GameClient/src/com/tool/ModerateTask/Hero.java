package com.tool.ModerateTask;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.NPCJfram;
import org.come.model.InternalForm;
import org.come.Jpanel.TesttaskJapnel;
import org.come.thread.TimeControlRunnable;
import org.come.Frame.TesttaskJframe;
import org.come.until.FormsManagement;
import org.come.bean.PrivateData;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import java.util.ArrayList;
import java.util.List;

public class Hero
{
    public static Hero hero;
    private List<Task> doingTasks;
    private Task BYTask;
    
    public Hero() {
        this.doingTasks = new ArrayList<>();
    }
    
    public static List<Task> getList() {
        if (Hero.hero != null) {
            return Hero.hero.doingTasks;
        }
        return null;
    }
    
    public static Hero getHero() {
        if (Hero.hero == null) {
            initial();
        }
        return Hero.hero;
    }
    
    public static void initial() {
        Hero.hero = new Hero();
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String msg = data.getTaskData();
        if (msg == null || msg.equals("")) {
            return;
        }
        String[] tasks = msg.split("\\|");
        for (int i = 0; i < tasks.length; ++i) {
            String[] values = tasks[i].split("=");
            int taskid = Integer.parseInt(values[0]);
            int state = Integer.parseInt(values[1]);
            Task task = new Task(taskid, state);
            try {
                task.addProgress(values);
            }
            catch (Exception e) {
                continue;
            }
            for (int j = 0; j < task.getProgress().size(); ++j) {
                ImageMixDeal.addMonster((TaskProgress)task.getProgress().get(j));
            }
            Hero.hero.doingTasks.add(task);
        }
        ZhuFrame.getZhuJpanel().getTaskGuideView().guideSX(Hero.hero.doingTasks);
    }
    
    public Task addTask(int taskid, int state, String[] values) {
        if (state == 8 || state == 9 || state == 4) {
            return this.removeTask(taskid, state);
        }
        Task task = this.getTaskId(taskid);
        if (task != null) {
            this.removeYG(task);
            task.addProgress(values);
        }
        else {
            task = this.createTask(taskid, state);
            task.addProgress(values);
            Hero.hero.doingTasks.add(task);
            if (taskid != 2300) {
                ZhuFrame.getZhuJpanel().addPrompt2("领取了" + task.getTaskData().getTaskName());
                FormsManagement.showForm(642);
            }
        }
        for (int j = 0; j < task.getProgress().size(); ++j) {
            ImageMixDeal.addMonster((TaskProgress)task.getProgress().get(j));
        }
        return task;
    }
    
    public void addTask(String msg) {
        if (msg == null || msg.equals("")) {
            return;
        }
        TesttaskJapnel taskJapnel = null;
        boolean is = false;
        InternalForm form = FormsManagement.getInternalForm2(3);
        if (form != null) {
            taskJapnel = ((TesttaskJframe)form.getFrame()).getJtask();
        }
        String[] mes = msg.split("\\|");
        for (int z = 0; z < mes.length; ++z) {
            String[] values = mes[z].split("=");
            if (values[0].startsWith("C")) {
                values[0] = values[0].substring(1);
                TaskRoleData.upTaskComplete(values);
            }
            else {
                int taskid = Integer.parseInt(values[0]);
                int state = Integer.parseInt(values[1]);
                Task task = this.addTask(taskid, state, values);
                if (taskJapnel != null && taskJapnel.getTaskId() == taskid) {
                    is = true;
                }
                if (task != null) {
                    TimeControlRunnable.upTask(task, state, TimeControlRunnable.isVip);
                }
            }
        }
        if (taskJapnel != null) {
            if (form.getFrame().isVisible()) {
                taskJapnel.showTaskMethod();
            }
            if (is) {
                taskJapnel.taskShow(null, null);
            }
        }
        ZhuFrame.getZhuJpanel().getTaskGuideView().guideSX(this.doingTasks);
    }
    
    public synchronized Task createTask(int id, int state) {
        Task task = null;
        if (this.BYTask != null) {
            task = this.BYTask;
            this.BYTask = null;
        }
        else {
            task = new Task(id, state);
        }
        task.setTaskId(id);
        task.setTaskState(state);
        task.reset();
        return task;
    }
    
    public Task removeTask(int id, int type) {
        int i = 0;
        while (i < this.doingTasks.size()) {
            Task task = (Task)this.doingTasks.get(i);
            if (task.getTaskId() == id) {
                this.removeYG(task);
                this.BYTask = (Task)this.doingTasks.remove(i);
                if (type == 9) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你的" + task.getTaskData().getTaskName() + "任务因为超时导致失败");
                }
                else if (type == 8) {
                    ZhuFrame.getZhuJpanel().addPrompt2("取消了" + task.getTaskData().getTaskName());
                }
                else if (type == 4) {
                    ZhuFrame.getZhuJpanel().addPrompt2("完成了" + task.getTaskData().getTaskName());
                    if (task.getTaskData().getTaskEnd() != null && !task.getTaskData().getTaskEnd().equals("")) {
                        NPCJfram.getNpcJfram().getNpcjpanel().taskend(task.getTaskData().getTaskEnd());
                    }
                }
                return task;
            }
            else {
                ++i;
            }
        }
        return null;
    }
    
    public void removeYG(Task task) {
        for (int j = 0; j < task.getProgress().size(); ++j) {
            TaskProgress progress = (TaskProgress)task.getProgress().get(j);
            ImageMixDeal.removeMonster(progress);
        }
    }
    
    public void OverTime() {
        StringBuffer buffer = null;
        for (int i = this.doingTasks.size() - 1; i >= 0; --i) {
            Task task = (Task)this.doingTasks.get(i);
            if (task.isOverTime()) {
                if (buffer == null) {
                    buffer = new StringBuffer("T");
                }
                else {
                    buffer.append("|");
                }
                buffer.append(task.getTaskId());
            }
        }
        if (buffer != null) {
            String mes = Agreement.getAgreement().TaskNAgreement(buffer.toString());
            SendMessageUntil.toServer(mes);
        }
    }
    
    public Task getTaskId(int id) {
        for (int i = this.doingTasks.size() - 1; i >= 0; --i) {
            if (((Task)this.doingTasks.get(i)).getTaskId() == id) {
                return (Task)this.doingTasks.get(i);
            }
        }
        return null;
    }
    
    public Task getSetTask(int setID) {
        for (int i = 0; i < this.doingTasks.size(); ++i) {
            int setId = ((Task)this.doingTasks.get(i)).getTaskData().getTaskSetID();
            if (setId == setID) {
                return (Task)this.doingTasks.get(i);
            }
        }
        return null;
    }
    
    public Task PartFinish(String type, String name) {
        for (int i = this.doingTasks.size() - 1; i >= 0; --i) {
            Task task = (Task)this.doingTasks.get(i);
            int v = task.PartFinish(type, name);
            if (v != 0) {
                return task;
            }
        }
        return null;
    }
    
    public List<Task> getDoingTasks() {
        return this.doingTasks;
    }
    
    public void getSetId(List<Integer> list) {
    	LOOP:
        for (int i = 0; i < this.doingTasks.size(); ++i) {
            int setId = ((Task)this.doingTasks.get(i)).getTaskData().getTaskSetID();
            if (!list.contains(Integer.valueOf(setId))) {
                int j = 0;
                while (j < list.size()) {
                    if ((int)list.get(j) < setId) {
                        list.add(j, Integer.valueOf(setId));
                        continue LOOP;
                    }
                    else {
                        ++j;
                    }
                }
                list.add(Integer.valueOf(setId));
            }
        }
    }
    
    public Task getTaskSet(int setID) {
        for (int i = this.doingTasks.size() - 1; i >= 0; --i) {
            if (((Task)this.doingTasks.get(i)).getTaskData().getTaskSetID() == setID) {
                return (Task)this.doingTasks.get(i);
            }
        }
        return null;
    }
}
