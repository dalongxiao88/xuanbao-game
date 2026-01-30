package org.come.npc;

import java.util.List;
import org.come.until.UserMessUntil;
import org.come.action.NpcMenuAction;

public class skillCX implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        try {
            String name = type;
            List<Integer> integers = null;
            int[] goodids = null;
            String pet = UserMessUntil.getChosePetMes().getSummoningname();
            String s = name;
            int n = -1;
            switch (s.hashCode()) {
                case 2130152393: {
                    if (s.equals("我想重新修炼终极技能")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    pet = "";
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
