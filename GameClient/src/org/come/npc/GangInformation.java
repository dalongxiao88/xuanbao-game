package org.come.npc;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.action.NpcMenuAction;

public class GangInformation implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        try {
            String sendMes = Agreement.getAgreement().ganglistAgreement("aaaa");
            SendMessageUntil.toServer(sendMes);
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
    }
}
