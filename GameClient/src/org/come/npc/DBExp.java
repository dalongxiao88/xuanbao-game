package org.come.npc;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.action.NpcMenuAction;

public class DBExp implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要领取一小时双倍时间")) {
            String senmes = Agreement.getAgreement().usercardAgreement("R3600");
            SendMessageUntil.toServer(senmes);
        }
        else if (type.equals("我要领取二小时双倍时间")) {
            String senmes = Agreement.getAgreement().usercardAgreement("R7200");
            SendMessageUntil.toServer(senmes);
        }
        else if (type.equals("我要领取四小时双倍时间")) {
            String senmes = Agreement.getAgreement().usercardAgreement("R10800");
            SendMessageUntil.toServer(senmes);
        }
        else if (type.equals("我要冻结双倍时间")) {
            String senmes = Agreement.getAgreement().usercardAgreement("S");
            SendMessageUntil.toServer(senmes);
        }
        else if (type.equals("我要查询剩余双倍时间")) {
            String senmes = Agreement.getAgreement().usercardAgreement("Q");
            SendMessageUntil.toServer(senmes);
        }
    }
}
