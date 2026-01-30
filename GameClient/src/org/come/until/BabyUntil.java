package org.come.until;

import com.tool.image.ImageMixDeal;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;

public class BabyUntil
{
    public static void calculateBaby() {
        babyBirth();
    }
    
    public static void babyBirth() {
        if ((int)RoleData.getRoleData().getLoginResult().getHavebaby() > 0) {
            RoleData.getRoleData().getLoginResult().setHavebaby(Integer.valueOf((int)RoleData.getRoleData().getLoginResult().getHavebaby() - 500));
            if ((int)RoleData.getRoleData().getLoginResult().getHavebaby() <= 0) {
                String mesServer = Agreement.getAgreement().babyborn(" ");
                SendMessageUntil.toServer(mesServer);
                ImageMixDeal.userimg.Dialogue("生孩子啦！！！");
            }
        }
        else {
            String mesServer = Agreement.getAgreement().babyborn(" ");
            SendMessageUntil.toServer(mesServer);
            ImageMixDeal.userimg.Dialogue("生孩子啦！！！");
        }
    }
}
