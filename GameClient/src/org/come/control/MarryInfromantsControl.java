package org.come.control;

import org.come.Frame.NPCJfram;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.role.RoleData;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Util;
import com.tool.image.ManimgAttribute;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.action.NpcMenuAction;

public class MarryInfromantsControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要结婚")) {
            this.marryWith();
        }
        else if (type.equals("我要离婚")) {
            this.UnMarry();
        }
    }
    
    public void marryWith() {
        String[] vs = ImageMixDeal.userimg.getRoleShow().getTeam().split("\\|");
        if (vs.length != 2) {
            ZhuFrame.getZhuJpanel().addPrompt2("2人组成队伍前来结婚");
            return;
        }
        if (vs[0].equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
            ManimgAttribute attribute = (ManimgAttribute)ImageMixDeal.Playerimgmap.get(vs[1]);
            if (attribute != null) {
                if (Util.getSex(attribute.getRoleShow().getSpecies_id()) == Util.getSex(ImageMixDeal.userimg.getRoleShow().getSpecies_id())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("结婚法不允许同性登记");
                    return;
                }
                SendMessageUntil.toServer(Agreement.marryAgreement(vs[1]));
            }
        }
    }
    
    public void UnMarry() {
        if (RoleData.getRoleData().getLoginResult().getMarryObject() != null) {
            this.doUnmarry();
        }
        else {
            FrameMessageChangeJpanel.addtext(6, "您还没有对象！！", null, null);
            ImageMixDeal.userimg.Dialogue("您还没有对象！！");
        }
    }
    
    public void doUnmarry() {
        if (ImageMixDeal.userimg.getRoleShow().getTeamInfo() != null) {
            String marrOtherName = null;
            String mes = null;
            String[] mesMarrName = ImageMixDeal.userimg.getRoleShow().getTeamInfo().split("\\|");
            if (mesMarrName.length == 2) {
                if (ImageMixDeal.userimg.getRoleShow().getRolename().equals(mesMarrName[0])) {
                    if (mesMarrName[1].equals(RoleData.getRoleData().getLoginResult().getMarryObject())) {
                        mes = Agreement.getAgreement().unMarry(((ManimgAttribute)ImageMixDeal.Playerimgmap.get(mesMarrName[1])).getRoleShow().getRole_id() + "");
                    }
                    else {
                        FrameMessageChangeJpanel.addtext(6, "离婚应该跟你对象一起来！！", null, null);
                        ImageMixDeal.userimg.Dialogue("离婚应该跟你对象一起来！！");
                    }
                }
                else if (mesMarrName[0].equals(RoleData.getRoleData().getLoginResult().getMarryObject())) {
                    mes = Agreement.getAgreement().unMarry(((ManimgAttribute)ImageMixDeal.Playerimgmap.get(mesMarrName[0])).getRoleShow().getRole_id() + "");
                }
                else {
                    FrameMessageChangeJpanel.addtext(6, "离婚应该跟你对象一起来！！", null, null);
                    ImageMixDeal.userimg.Dialogue("离婚应该跟你对象一起来！！");
                }
                SendMessageUntil.toServer(mes);
            }
            else {
                if (mesMarrName.length > 2) {
                    FrameMessageChangeJpanel.addtext(6, "离婚就你跟你对象的事，多带的人算什么！！", null, null);
                    ImageMixDeal.userimg.Dialogue("离婚就你跟你对象的事，多带的人算什么！！");
                    return;
                }
                NPCJfram.getNpcJfram().getNpcjpanel().compulsoryDivorce();
            }
        }
        else {
            NPCJfram.getNpcJfram().getNpcjpanel().compulsoryDivorce();
        }
    }
}
