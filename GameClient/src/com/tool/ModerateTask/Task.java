package com.tool.ModerateTask;

import org.come.until.Util;
import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Gamemap;
import com.tool.tcpimg.UIUtils;
import org.come.until.GsonUtil;
import com.tool.tcpimg.InputBean;
import java.math.BigDecimal;
import org.come.bean.Coordinates;
import org.come.until.UserMessUntil;
import com.tool.role.RoleData;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import com.tool.tcpimg.RichLabel;
import java.util.List;

public class Task
{
    private int taskId;//任务id
    private int taskState;//任务状态
    private List<TaskProgress> progress;//任务进度
    private long time;//任务超时
    private transient TaskData taskData;//任务配置表
    private transient RichLabel richLabel;//任务追踪
    private transient RichLabel richLabel1;
    
    public Task(int taskId, int taskState) {
        this.taskId = taskId;
        this.taskState = taskState;
    }
    
    public void reset() {
        if (this.progress != null) {
            this.progress.clear();
        }
        this.time = 0L;
        this.taskData = null;
    }
    
    public void addProgress(String[] values) {
        if (this.progress != null) {
            this.progress.clear();
        }
        else {
            this.progress = new ArrayList<>();
        }
        if (values != null) {
            //任务id=任务状态=T过期时间=PTYPE_S1-1_MMAP-X-Y_DID-NAME_GID-NAME;
            for (int i = 2; i < values.length; ++i) {
                if (values[i].startsWith("P")) {
                    String[] vs = values[i].split("_");
                    TaskProgress taskProgress = new TaskProgress(this);
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
                            taskProgress.setY(Integer.parseInt(ts[2]));
                        }
                        else if (vs[j].startsWith("D")) {
                            String[] ts = vs[j].split("-");
                            taskProgress.setDId(Integer.parseInt(ts[0].substring(1)));
                            if (StringUtils.isNotEmpty(ts[1]) && ts[1].indexOf("&") > 0) {
                                String[] ts2 = ts[1].split("&");
                                taskProgress.setDName(ts2[0]);
                            }
                            else {
                                taskProgress.setDName(ts[1]);
                            }
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
        if (this.progress.size() == 0) {
            this.richLabel = null;
        }
        else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#Y");
            buffer.append(this.getTaskData().getTaskName());
            buffer.append("#W[");
            if (this.getTaskData().getTaskSetID() == 600) {
                String taskComplete = RoleData.getRoleData().getPrivateData().getTaskComplete();
                ConcurrentHashMap<Integer, TaskRecord> integerTaskRecordConcurrentHashMap = initTaskRecord(taskComplete);
                TaskRecord taskRecord = (TaskRecord)integerTaskRecordConcurrentHashMap.get(Integer.valueOf(600));
                if (taskRecord != null) {
                    buffer.append("第" + (taskRecord.getcSum() + 1) + "环");
                }
            }
            else {
                buffer.append(UserMessUntil.getTaskSet(this.getTaskData().getTaskSetID()).getTaskType());
            }
            buffer.append("]");
            for (int k = 0; k < this.progress.size(); ++k) {
                TaskProgress ps = (TaskProgress)this.progress.get(k);
                buffer.append("#r");
                if (ps.getType() == 0 || ps.getType() == 1 || ps.getType() == 2) {
                    buffer.append("击杀");
                }
                else if (ps.getType() == 3) {
                    buffer.append("问候");
                }
                else if (ps.getType() == 4) {
                    buffer.append("将");
                    buffer.append(ps.getDName());
                    buffer.append("送给");
                }
                else if (ps.getType() == 5) {
                    buffer.append("护送");
                    buffer.append(ps.getDName());
                    buffer.append("到");
                }
                if (ps.getMap() == 0) {
                    buffer.append((ps.getType() == 4 || ps.getType() == 5) ? ps.getGName() : ps.getDName());
                }
                else {
                    Coordinates coordinates = new Coordinates(ps.getMap(), ps.getX(), ps.getY());
                    InputBean inputBean = new InputBean(null, ps.getType() + 20, new BigDecimal((ps.getType() == 4 || ps.getType() == 5) ? ps.getGId() : ps.getDId()), (ps.getType() == 4 || ps.getType() == 5) ? ps.getGName() : ps.getDName(), "G", coordinates);
                    buffer.append("#V");
                    buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
                    buffer.append("#L");
                }
                if (ps.getType() == 0 || ps.getType() == 1 || ps.getType() == 2 || ps.getType() == 4) {
                    buffer.append(" ");
                    buffer.append(ps.getSum());
                    buffer.append("/");
                    buffer.append(ps.getMax());
                }
            }
            if (this.richLabel == null) {
                this.richLabel = new RichLabel(buffer.toString(), UIUtils.TEXT_FONT, 165);
            }
            else {
                this.richLabel.setTextSize(buffer.toString(), 165);
            }
            StringBuffer buffer2 = new StringBuffer();
            if (this.taskData.getTaskText() != null) {
                buffer2.append("#W");
                String text = this.taskData.getTaskText();
                int value1 = -1;
                int value2 = -1;
                int size = 0;
                do {
                    value1 = text.indexOf("{");
                    if (value1 != -1) {
                        value2 = text.indexOf("}");
                    }
                    if (value1 == -1 || value2 == -1) {
                        buffer2.append(text);
                    }
                    else {
                        buffer2.append(text.substring(0, value1));
                        String type = text.substring(value1 + 1, value2);
                        if (type.startsWith("目标")) {
                            if (size < this.getProgress().size()) {
                                TaskProgress progress = (TaskProgress)this.getProgress().get(size);
                                if (progress.getType() == 0 || progress.getType() == 1 || progress.getType() == 2 || progress.getType() == 3) {
                                    if (progress.getMap() == 0) {
                                        buffer2.append(progress.getDName());
                                    }
                                    else {
                                        Coordinates coordinates2 = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean2 = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getDId()), progress.getDName(), "G", coordinates2);
                                        if (progress.getType() == 1 || progress.getType() == 2) {
                                            buffer2.append("#G");
                                            buffer2.append(UserMessUntil.getAllmapbean().getAllMap().get(progress.getMap() + "").getMapname());
                                            buffer2.append("(");
                                            buffer2.append(progress.getX() / 20);
                                            buffer2.append(",");
                                            buffer2.append(progress.getY() / 20);
                                            buffer2.append(")");
                                            buffer2.append("#W击杀");
                                            buffer2.append("#V");
                                            buffer2.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean2));
                                            buffer2.append("#L");
                                        }
                                        else {
                                            buffer.append("#V");
                                            buffer.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean2));
                                            buffer.append("#L");
                                            buffer.append("(");
                                            buffer.append(progress.getX() / 20);
                                            buffer.append(",");
                                            buffer.append(progress.getY() / 20);
                                            buffer.append(")");
                                        }
                                    }
                                }
                                else if (progress.getType() == 4) {
                                    buffer2.append("带着");
                                    buffer2.append(progress.getMax());
                                    buffer2.append("个");
                                    buffer2.append(progress.getDName());
                                    buffer2.append("探望");
                                    if (progress.getMap() == 0) {
                                        buffer2.append(progress.getGName());
                                    }
                                    else {
                                        Coordinates coordinates2 = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean2 = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getGId()), progress.getGName(), "G", coordinates2);
                                        buffer2.append("#V");
                                        buffer2.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean2));
                                        buffer2.append("#L");
                                        buffer2.append("(");
                                        buffer2.append(progress.getX() / 20);
                                        buffer2.append(",");
                                        buffer2.append(progress.getY() / 20);
                                        buffer2.append(")");
                                    }
                                }
                                else if (progress.getType() == 5) {
                                    buffer2.append("护送");
                                    buffer2.append(progress.getDName());
                                    buffer2.append("到");
                                    if (progress.getMap() == 0) {
                                        buffer2.append(progress.getGName());
                                    }
                                    else {
                                        Coordinates coordinates2 = new Coordinates(progress.getMap(), progress.getX(), progress.getY());
                                        InputBean inputBean2 = new InputBean(null, progress.getType() + 20, new BigDecimal(progress.getGId()), progress.getGName(), "G", coordinates2);
                                        buffer2.append("#V");
                                        buffer2.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean2));
                                        buffer2.append("#L");
                                        buffer2.append("(");
                                        buffer2.append(progress.getX() / 20);
                                        buffer2.append(",");
                                        buffer2.append(progress.getY() / 20);
                                        buffer2.append(")");
                                    }
                                }
                            }
                            ++size;
                        }
                        else if (type.startsWith("位置")) {
                            String[] vs2 = type.split("-");
                            Coordinates coordinates2 = new Coordinates(Integer.parseInt(vs2[1]), Integer.parseInt(vs2[2]), Integer.parseInt(vs2[3]));
                            InputBean inputBean2 = new InputBean(null, 10, null, vs2[1].substring(2), "G", coordinates2);
                            buffer2.append("#V");
                            buffer2.append(GsonUtil.getGsonUtil().getgson().toJson(inputBean2));
                            buffer2.append("#L");
                            buffer2.append("(");
                            buffer2.append(coordinates2.getX() / 20);
                            buffer2.append(",");
                            buffer2.append(coordinates2.getY() / 20);
                            buffer2.append(")");
                        }
                        text = text.substring(value2 + 1);
                    }
                } while (value1 != -1 && value2 != -1);
            }
            if (this.richLabel1 == null) {
                this.richLabel1 = new RichLabel(buffer2.toString(), UIUtils.TEXT_FONT, 524);
            }
            else {
                this.richLabel1.setTextSize(buffer2.toString(), 524);
            }
        }
    }
    
    public static ConcurrentHashMap<Integer, TaskRecord> initTaskRecord(String msg) {
        ConcurrentHashMap<Integer, TaskRecord> map = new ConcurrentHashMap<>();
        if (msg == null || msg.equals("")) {
            return map;
        }
        String[] values = msg.split("\\|");
        for (int i = 0; i < values.length; ++i) {
            try {
                String[] vs = values[i].split("-");
                TaskRecord record = new TaskRecord(Integer.parseInt(vs[0]));
                record.addRSum(Integer.parseInt(vs[1]));
                record.addCSum(Integer.parseInt(vs[2]));
                if (vs.length == 4) {
                    record.setNewID(Integer.parseInt(vs[3]));
                }
                map.put(Integer.valueOf(record.getTaskId()), record);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    
    public int PartFinish(String type, String name) {
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress Progre = (TaskProgress)this.progress.get(i);
            if (Progre.getSum() < Progre.getMax() && name.equals((Progre.getType() != 5) ? Progre.getDName() : Progre.getGName())) {
                if ((Progre.getType() == 0 || Progre.getType() == 1 || Progre.getType() == 2) && type.equals("击杀")) {
                    return 3;
                }
                if ((Progre.getType() == 3 || Progre.getType() == 5) && type.equals("问候")) {
                    return 1;
                }
                if (Progre.getType() == 4 && type.equals("给予物品")) {
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
    
    public boolean isOverTime() {
        return this.time != 0L && this.time <= Util.getTime();
    }
    
    public TaskProgress getTaskProgress() {
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress taskProgress = (TaskProgress)this.progress.get(i);
            if ((taskProgress.getType() == 1 || taskProgress.getType() == 2 || taskProgress.getType() == 3) && taskProgress.getSum() < taskProgress.getMax()) {
                return taskProgress;
            }
        }
        return null;
    }
    
    public TaskProgress getTaskProgres() {
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress taskProgress = (TaskProgress)this.progress.get(i);
            if (taskProgress.getType() == 0 && taskProgress.getSum() < taskProgress.getMax()) {
                return taskProgress;
            }
        }
        return null;
    }
    
    public int getTaskCount() {
        int count = 0;
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress taskProgress = (TaskProgress)this.progress.get(i);
            if (taskProgress.getType() == 0) {
                count += taskProgress.getSum();
            }
        }
        return count;
    }
    
    public int getTaskSum() {
        int sum = 0;
        for (int i = 0; i < this.progress.size(); ++i) {
            TaskProgress taskProgress = (TaskProgress)this.progress.get(i);
            if (taskProgress.getType() == 0) {
                sum += taskProgress.getMax();
            }
        }
        return sum;
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
            this.taskData = UserMessUntil.getTaskData(this.taskId);
        }
        return this.taskData;
    }
    
    public void setTaskData(TaskData taskData) {
        this.taskData = taskData;
    }
    
    public RichLabel getRichLabel() {
        return this.richLabel;
    }
    
    public RichLabel getRichLabel1() {
        return this.richLabel1;
    }
}
