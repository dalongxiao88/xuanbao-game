package com.tool.ModerateTask;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllTask
{
    private Map<Integer, TaskData> allTaskData;
    private Map<Integer, TaskSet> allTaskSet;
    private List<Integer> setList;
    private List<String> typeList;
    
    public void getSetId(List<Integer> list) {
        this.getSetList();
    LOOP:
        for (int i = this.setList.size() - 1; i >= 0; --i) {
            int setId = (int)this.setList.get(i);
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
    
    private void init() {
        this.setList = new ArrayList<>();
        this.typeList = new ArrayList<>();
        LOOP:
        for (Integer value : this.allTaskSet.keySet()) {
            int i = 0;
            while (i < this.setList.size()) {
                if ((int)value < (int)this.setList.get(i)) {
                    this.setList.add(i, value);
                    continue LOOP;
                }
                else {
                    ++i;
                }
            }
            this.setList.add(value);
        }
        for (int j = this.setList.size() - 1; j >= 0; --j) {
            TaskSet taskSet = (TaskSet)this.allTaskSet.get(this.setList.get(j));
            if (taskSet.getTaskMsgID() == 0) {
                this.setList.remove(j);
            }
            if (!this.typeList.contains(taskSet.getTaskType())) {
                this.typeList.add(taskSet.getTaskType());
            }
        }
    }
    
    public List<String> getTypeList() {
        if (this.setList == null) {
            this.init();
        }
        return this.typeList;
    }
    
    public List<Integer> getSetList() {
        if (this.setList == null) {
            this.init();
        }
        return this.setList;
    }
    
    public Map<Integer, TaskData> getAllTaskData() {
        return this.allTaskData;
    }
    
    public void setAllTaskData(Map<Integer, TaskData> allTaskData) {
        this.allTaskData = allTaskData;
    }
    
    public Map<Integer, TaskSet> getAllTaskSet() {
        return this.allTaskSet;
    }
    
    public void setAllTaskSet(Map<Integer, TaskSet> allTaskSet) {
        this.allTaskSet = allTaskSet;
    }
}
