package org.come.readUtil;

import org.come.handler.MainServerHandler;
import org.come.model.TaskList;
import org.come.model.TaskListAll;
import org.come.tool.ReadExelTool;
import org.come.tool.SettModelMemberTool;

import java.util.ArrayList;
import java.util.List;

public class ReadTaskListUtil {
    public static TaskListAll getAllData(String path, StringBuffer buffer) {
        TaskListAll tasklistAll = new TaskListAll();
        String[][] result = ReadExelTool.getResult("config/"+path+".xls");
        List<TaskList> tasklist = new ArrayList<>();
        for (int i = 1; i < result.length; i++) {
            if (result[i][0].equals("")) {continue;}

            TaskList taskList = new TaskList();
            for (int j = 0; j < result[i].length; j++) {
                try {
                    SettModelMemberTool.setReflectRelative(taskList, result[i][j], j);
                } catch (Exception e) {
                    return null;
                }
            }
            tasklist.add(taskList);
        }
        tasklistAll.setTaskLists(tasklist);
        return tasklistAll;
    }
}
