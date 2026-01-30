package org.come.npc;

import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class SkillLearnMsg implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        NPCJfram.getNpcJfram().getNpcjpanel().showskill(Integer.parseInt(NPCJfram.getNpcJfram().getNpcjpanel().getNpctype()));
    }
}
