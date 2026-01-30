package org.come.control;

import com.tool.ModerateTask.Hero;
import org.come.action.FromServerAction;

public class TaskNControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        Hero.getHero().addTask(mes);
    }
}
