package come.tool.newTask;

import org.come.model.TaskSet;
import org.come.model.Npctable;
import org.come.server.GameServer;
import java.util.ArrayList;
import org.come.model.TaskData;
import org.come.model.TaskProgress;
import java.util.List;

public class Task
{
    private int taskId;
    private int taskState;
    private List<TaskProgress> progress;
    private long time;
    private transient TaskData taskData;
    
    public Task(int taskId) {
        this.taskId = taskId;
    }
    
    public Task(int taskId, int taskState, String[] text) {
        this.taskId = taskId;
        this.taskState = taskState;
        this.addProgress(text);
    }
    
    public void addProgress(String[] values) {
        if (this.progress != null) {
            this.progress.clear();
        }
        else {
            this.progress = new ArrayList<>();
        }
        if (values == null) {
            return;
        }
        for (int i = 2; i < values.length; ++i) {
            if (values[i].startsWith("P")) {
                String[] vs = values[i].split("_");
                TaskProgress taskProgress = new TaskProgress();
                taskProgress.setType(Integer.parseInt(vs[0].substring(1)));
                taskProgress.setSum(0);
                taskProgress.setMax(1);
                for (int j = 1; j < vs.length; ++j) {
                    if (vs[j].startsWith("S")) {
                        String[] ts = vs[j].split("-");
                        taskProgress.setSum(Integer.parseInt(ts[0].substring(1)));
                        taskProgress.setMax(Integer.parseInt(ts[1]));
                    }
                    else if (vs[j].startsWith("M")) {
                        String[] ts = vs[j].split("-");
                        taskProgress.setMap(Integer.parseInt(ts[0].substring(1)));
                        taskProgress.setX(Integer.parseInt(ts[1]));
                        taskProgress.setY((long)Integer.parseInt(ts[2]));
                    }
                    else if (vs[j].startsWith("D")) {
                        String[] ts = vs[j].split("-");
                        taskProgress.setDId(Integer.parseInt(ts[0].substring(1)));
                        taskProgress.setDName(ts[1]);
                    }
                    else if (vs[j].startsWith("G")) {
                        String[] ts = vs[j].split("-");
                        taskProgress.setGId(Integer.parseInt(ts[0].substring(1)));
                        taskProgress.setGName(ts[1]);
                    }
                }
                this.progress.add(taskProgress);
            }
            else if (values[i].startsWith("T")) {
                this.time = Long.parseLong(values[i].substring(1)) * 1000L;
            }
        }
    }
    
    public int PartFinish(int id, int sum, String leixing) {
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress Progre = (TaskProgress)this.progress.get(i);
            if (Progre.getSum() < Progre.getMax()) {
                if ((Progre.getType() == 0 || Progre.getType() == 1 || Progre.getType() == 2) && leixing.equals("击杀")) {
                    if (Progre.getType() == 0 || Progre.getType() == 1) {
                        if (id == Progre.getDId()) {
                            Progre.addSum(sum);
                            return 3;
                        }
                    }
                    else if (Progre.getType() == 2) {
                        Npctable npctable = GameServer.getNpc(Progre.getDId() + "");
                        if (npctable != null && npctable.getRobotID() == id) {
                            Progre.addSum(sum);
                            return 3;
                        }
                    }
                }
                else if ((Progre.getType() == 3 || Progre.getType() == 5) && leixing.equals("问候")) {
                    if (id == ((Progre.getType() == 3) ? Progre.getDId() : Progre.getGId())) {
                        Progre.addSum(sum);
                        return 1;
                    }
                }
                else if (Progre.getType() == 4 && leixing.equals("给予物品") && id == Progre.getDId()) {
                    Progre.addSum(sum);
                    return 1;
                }
            }
        }
        return 0;
    }
    
    public int PartFinish(String content, int sum, String leixing) {
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress Progre = (TaskProgress)this.progress.get(i);
            if (Progre.getSum() < Progre.getMax() && content.equals((Progre.getType() != 5) ? Progre.getDName() : Progre.getGName())) {
                if ((Progre.getType() == 0 || Progre.getType() == 1 || Progre.getType() == 2) && leixing.equals("击杀")) {
                    Progre.addSum(sum);
                    return 3;
                }
                if ((Progre.getType() == 3 || Progre.getType() == 5) && leixing.equals("问候")) {
                    Progre.addSum(sum);
                    return 1;
                }
                if (Progre.getType() == 4 && leixing.equals("给予物品")) {
                    Progre.addSum(sum);
                    return 2;
                }
            }
        }
        return 0;
    }
    
    public boolean isFinish() {
        if (this.taskState == 2) {
            if (this.progress == null) {
                this.taskState = 3;
                return true;
            }
            for (int i = 0; i < this.progress.size(); ++i) {
                if (((TaskProgress)this.progress.get(i)).getSum() < ((TaskProgress)this.progress.get(i)).getMax()) {
                    return false;
                }
            }
            this.taskState = 3;
            return true;
        }
        else {
            return false;
        }
    }
    
    public int getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    public int getTaskState() {
        return this.taskState;
    }
    
    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }
    
    public List<TaskProgress> getProgress() {
        return this.progress;
    }
    
    public void setProgress(List<TaskProgress> progress) {
        this.progress = progress;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public TaskData getTaskData() {
        if (this.taskData == null) {
            this.taskData = GameServer.getTaskData(this.taskId);
        }
        return this.taskData;
    }
    
    public TaskSet getTaskSet() {
        return this.getTaskData().getTaskSet();
    }
    
    public Task FZ() {
        Task task = new Task(this.taskId);
        task.taskState = this.taskState;
        task.time = this.time;
        if (this.progress != null) {
            task.progress = new ArrayList<>();
            for (int i = 0; i < this.progress.size(); ++i) {
                task.progress.add(new TaskProgress((TaskProgress)this.progress.get(i)));
            }
        }
        return task;
    }
    
    public int getTaskSetId() {
        return this.getTaskData().getTaskSetID();
    }
}
