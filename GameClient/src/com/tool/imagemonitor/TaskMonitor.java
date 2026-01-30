package com.tool.imagemonitor;

import org.come.Jpanel.NPCJpanel;
import org.come.Frame.NPCJfram;
import com.tool.ModerateTask.TaskProgress;

public class TaskMonitor
{
    public static void TaskCreeps(TaskProgress taskdata) {
        if (taskdata.getType() == 1 || taskdata.getType() == 0) {
            NPCJpanel npcjpanel = NPCJfram.getNpcJfram().getNpcjpanel();
            npcjpanel.task(taskdata);
        }
    }
}
