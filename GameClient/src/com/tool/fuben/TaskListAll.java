package com.tool.fuben;

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
    public TaskList getTaskByName(String name){
        for (TaskList taskList : taskLists) {
            if (taskList.getTaskName().equals(name)) {
                return taskList;
            }
        }
        return null;
    }
}
