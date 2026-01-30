package org.come.control;

import org.come.bean.RoleShow;
import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.role.RoleData;
import org.come.action.NpcMenuAction;

public class MakeLoveControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getMarryObject() == null) {
            FrameMessageChangeJpanel.addtext(6, "你还未婚！！跟谁洞房？", null, null);
            ImageMixDeal.userimg.Dialogue("你还未婚！！跟谁洞房？");
            return;
        }
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getTroop_id() == null) {
            FrameMessageChangeJpanel.addtext(6, "一个人跟谁洞房？？", null, null);
            ImageMixDeal.userimg.Dialogue("一个人跟谁洞房？？");
            return;
        }
        String[] teamRole = ImageMixDeal.userimg.getTeams();
        if (teamRole == null) {
            FrameMessageChangeJpanel.addtext(6, "你不是队长", null, null);
            ImageMixDeal.userimg.Dialogue("你不是队长");
            return;
        }
        if (teamRole.length != 2) {
            FrameMessageChangeJpanel.addtext(6, "2人组好队伍", null, null);
            ImageMixDeal.userimg.Dialogue("2人组好队伍");
            return;
        }
        if (teamRole[1].equals(loginResult.getMarryObject())) {
            String serverMes = Agreement.makeloveAgreement(teamRole[1]);
            SendMessageUntil.toServer(serverMes);
            ImageMixDeal.userimg.Dialogue("已经在洞房！！");
        }
        else {
            ImageMixDeal.userimg.Dialogue("这个不是你对象");
        }
    }
}
