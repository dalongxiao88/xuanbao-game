package org.come.readBean;

import org.come.model.TaskSet;
import org.come.model.TaskData;
import java.util.concurrent.ConcurrentHashMap;

public class AllTask
{
    private ConcurrentHashMap<Integer, TaskData> allTaskData;
    private ConcurrentHashMap<Integer, TaskSet> allTaskSet;
    
    public ConcurrentHashMap<Integer, TaskData> getAllTaskData() {
        return this.allTaskData;
    }
    
    public void setAllTaskData(ConcurrentHashMap<Integer, TaskData> allTaskData) {
        this.allTaskData = allTaskData;
    }
    
    public ConcurrentHashMap<Integer, TaskSet> getAllTaskSet() {
        return this.allTaskSet;
    }
    
    public void setAllTaskSet(ConcurrentHashMap<Integer, TaskSet> allTaskSet) {
        this.allTaskSet = allTaskSet;
    }
}
