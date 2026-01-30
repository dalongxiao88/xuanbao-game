package org.come.control;

import org.come.Frame.NPCJfram;
import org.come.until.GsonUtil;
import org.come.bean.NPCDialog;
import org.come.action.FromServerAction;

public class NPCDialogControl2 implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        NPCDialog npcDialog = (NPCDialog)GsonUtil.getGsonUtil().getgson().fromJson(mes, NPCDialog.class);
        if (npcDialog != null) {
            NPCJfram.getNpcJfram().getNpcjpanel().npc(npcDialog);
        }
    }
}
