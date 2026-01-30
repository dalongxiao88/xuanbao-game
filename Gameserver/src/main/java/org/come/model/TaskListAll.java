package org.come.model;

import java.util.List;

public class TaskListAll {
    private List<TaskList> taskLists;

    public List<TaskList> getTaskLists() {
        return taskLists;
    }
    public void setTaskLists(List<TaskList> taskLists) {
        this.taskLists = taskLists;
    }
    public TaskList getTaskByID(int id){
        for (TaskList taskList : taskLists) {
            if (taskList.getTaskID()==id) {
                return taskList;
            }
        }
        return null;
    }
    public TaskList getTaskBysetID(int id){
        for (TaskList taskList : taskLists) {
            if (taskList.getTaskSetID()==id) {
                return taskList;
            }
        }
        return null;
    }
}
