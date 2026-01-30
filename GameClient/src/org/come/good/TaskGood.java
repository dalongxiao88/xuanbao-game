package org.come.good;

import com.tool.ModerateTask.Task;
import org.come.mouslisten.GoodsMouslisten;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.ModerateTask.Hero;
import org.come.entity.Goodstable;

public class TaskGood
{
    public static void gainTask(Goodstable goodstable) {
        Task task = null;
        int[] tasks = TaskIdS(goodstable.getValue());
        int i = 0;
        while (i < tasks.length) {
            task = Hero.getHero().getTaskId(tasks[i]);
            if (task != null) {
                String mes = Agreement.getAgreement().TaskNAgreement("E" + task.getTaskId());
                SendMessageUntil.toServer(mes);
                goodstable.goodxh(1);
                GoodsMouslisten.gooduse(goodstable, 1);
                break;
            }
            else {
                ++i;
            }
        }
        if (task == null) {
            int taskid = accessTaskId(goodstable.getValue());
            String mes = Agreement.getAgreement().TaskNAgreement("L" + taskid);
            SendMessageUntil.toServer(mes);
            goodstable.goodxh(1);
        }
    }
    
    public static int[] TaskIdS(String value) {
        String mes = value.split("=")[1];
        String[] listTask = mes.split("\\|");
        int[] tasks = new int[listTask.length];
        for (int i = 0; i < listTask.length; ++i) {
            String flag0 = listTask[i].split("\\&")[0];
            tasks[i] = Integer.parseInt(flag0);
        }
        return tasks;
    }
    
    public static int accessTaskId(String value) {
        String mes = value.split("=")[1];
        String[] listTask = mes.split("\\|");
        int index = (int)(Math.random() * 100.0 + 1.0);
        int taskId = 0;
        int gl = 0;
        for (int i = 0; i < listTask.length; ++i) {
            String flag0 = listTask[i].split("\\&")[0];
            String flag2 = listTask[i].split("\\&")[1];
            if (index > gl && index <= gl + Integer.parseInt(flag2)) {
                taskId = Integer.parseInt(flag0);
            }
            gl += Integer.parseInt(flag2);
        }
        return taskId;
    }
}
