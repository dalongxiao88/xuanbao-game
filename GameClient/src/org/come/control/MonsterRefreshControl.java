package org.come.control;

import org.come.thread.LoadYeguaiRunnable;
import org.come.action.FromServerAction;

public class MonsterRefreshControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        LoadYeguaiRunnable.upMonster(mes, false);
    }
}
