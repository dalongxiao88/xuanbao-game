package org.come.npc;

import org.come.model.Door;
import org.come.action.NpcMenuAction;

public class Gotochangan implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        Door door = new Door();
        door.setDoormap("1207");
        door.setDoorpoint("4294|2887");
        if (type.equals("我想去死牢探监")) {
            door.setDoormap("3312");
            door.setDoorpoint("2373|1737");
        }
        else if (type.equals("我想去地牢探监")) {
            door.setDoormap("3313");
            door.setDoorpoint("162|1734");
        }
        else if (type.equals("我想去天牢探监")) {
            door.setDoormap("3314");
            door.setDoorpoint("361|1657");
        }
        else if (type.equals("我要回家")) {
            door.setDoormap("3204");
            door.setDoorpoint("3020|2240");
        }
        else if (type.equals("继续挑战孤竹城")) {
            door.setDoormap("3306");
            door.setDoorpoint("160|2220");
        }
        try {
            TP.tp(door, 2);
        }
        catch (Exception var4) {
            var4.printStackTrace();
        }
    }
}
