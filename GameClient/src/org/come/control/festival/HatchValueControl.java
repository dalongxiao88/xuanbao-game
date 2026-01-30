package org.come.control.festival;

import org.come.until.Util;
import org.come.model.Talk;
import org.come.Frame.NPCJfram;
import org.come.action.FromServerAction;

public class HatchValueControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        int hatch = Integer.parseInt(mes);
        NPCJfram.getNpcJfram().getNpcjpanel().getTestMes().setText(((Talk)NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean().getTalks().get(Util.random.nextInt(NPCJfram.getNpcJfram().getNpcjpanel().getNpcInfoBean().getTalks().size()))).getTalktext() + "\r\n瀛靛寲杩涘害锛�" + hatch + "/500");
    }
}
