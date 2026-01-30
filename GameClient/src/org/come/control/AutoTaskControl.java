package org.come.control;

import java.util.List;
import org.come.thread.TimeControlRunnable;
import com.tool.imagemonitor.ScriptOpen;
import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import org.come.action.FromServerAction;

public class AutoTaskControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (StringUtils.isNotBlank(mes)) {
            String[] vls = mes.split("-");
            if (vls.length == 4) {
                List<Object> list = new ArrayList<>();
                ScriptOpen openDoor = new ScriptOpen(101);
                openDoor.setDoor(Integer.parseInt(vls[0]));
                openDoor.setX(Integer.parseInt(vls[1]));
                openDoor.setY(Integer.parseInt(vls[2]));
                list.add(0, openDoor);
                ScriptOpen open = new ScriptOpen(104);
                open.setNpc(Integer.parseInt(vls[3]));
                list.add(0, open);
                ScriptOpen jiSha = new ScriptOpen(105);
                jiSha.setNpc(Integer.parseInt(vls[3]));
                list.add(0, jiSha);
                TimeControlRunnable.addScript(list);
            }
            else if (vls.length == 1) {
                TimeControlRunnable.sortActiveBases(Integer.parseInt(vls[0]));
                TimeControlRunnable.addAllTask(null);
            }
        }
        else {
            TimeControlRunnable.addAllTask(null);
        }
    }
}
