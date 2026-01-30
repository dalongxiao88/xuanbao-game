package org.come.control;

import org.come.bean.NpcInfoBean;
import org.come.Frame.NPCJfram;
import org.come.bean.NpcSureMenuBean;
import org.come.until.ControlNpcXmlUntil;
import org.come.bean.NpcMenuBean;
import org.come.until.UserMessUntil;
import org.come.action.FromServerAction;

public class NPCDialogControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        NpcInfoBean infoBean = UserMessUntil.getnpc(mes);
        if (infoBean != null) {
            ControlNpcXmlUntil.setNpcmenubean(new NpcMenuBean());
            ControlNpcXmlUntil.setSureBean(new NpcSureMenuBean());
            ControlNpcXmlUntil.setType(infoBean.getNpctable().getNpctype());
            try {
                ControlNpcXmlUntil.GetXmlPath("npcMenu.xml");
            }
            catch (Exception var5) {
                var5.printStackTrace();
            }
            NPCJfram.getNpcJfram().getNpcjpanel().npc(ControlNpcXmlUntil.getNpcmenubean(), infoBean);
        }
    }
}
