package org.come.npc;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.bean.ChangeMapBean;
import come.tool.Fighting.FightingMixDeal;
import org.come.thread.TimeControlRunnable;
import org.come.Frame.WorldTestsmallmapJframe;
import org.come.Frame.TestsmallmapJframe;
import org.come.until.Util;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.entity.Goodstable;
import org.come.Jpanel.NPCJpanel;
import org.come.model.Door;
import org.come.Frame.NPCJfram;

public class TP
{
    public static boolean isTP(String msg) {
        NPCJpanel npcjpanel = NPCJfram.getNpcJfram().getNpcjpanel();
        if (npcjpanel.getNpcInfoBean() == null) {
            return false;
        }
        if (npcjpanel.getNpcInfoBean().getDoors() == null) {
            return false;
        }
        for (int i = 0; i < npcjpanel.getNpcInfoBean().getDoors().size(); ++i) {
            if (((Door)npcjpanel.getNpcInfoBean().getDoors().get(i)).getDoorkey().equals(msg)) {
                tp((Door)npcjpanel.getNpcInfoBean().getDoors().get(i), 2);
                return true;
            }
        }
        return false;
    }
    
    public static Door scdoor(String mapid, int x, int y) {
        Door door = new Door();
        door.setDoormap(mapid);
        door.setDoorpoint(x + "|" + y);
        return door;
    }
    
    public static void FlightRedraw(Goodstable good, int leixing) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
            return;
        }
        String flag = Util.mapmodel.getGamemap().getMapflag();
        if (flag == null || !flag.equals("1") || ImageMixDeal.userimg.getTeams() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("飞行棋使用限制");
            return;
        }
        String path = ImageMixDeal.userimg.getRoleShow().getMapid() + "," + Util.mapmodel.getGamemap().getMapname() + "," + ImageMixDeal.userimg.getRoleShow().getX() + "," + ImageMixDeal.userimg.getRoleShow().getY();
        if (leixing == 0) {
            String[] vs = good.getValue().split("\\|");
            good.setValue("传送=" + path + "|" + vs[1]);
        }
        else {
            good.setValue("传送=" + path + "|可用次数=" + leixing);
        }
        ZhuFrame.getZhuJpanel().addPrompt2("已经重新做好路标");
        TestsmallmapJframe.getTestsmallmapJframe().getTestsmallmapJpanel().clearTps();
        WorldTestsmallmapJframe.getWorldTestsmallmapJframe();
        WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
    }
    
    public static void tp(Door door, int type) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
            return;
        }
        if (door == null) {
            return;
        }
        if (type != 3 && type != 0) {
            TimeControlRunnable.removeScript(true);
        }
        if (FightingMixDeal.State != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你在战斗中");
            return;
        }
        ChangeMapBean changeMapBean = new ChangeMapBean();
        changeMapBean.setMapid(door.getDoormap());
        String dxy = door.getDoorpoint();
        String[] a = dxy.split("\\|");
        int dx = Integer.parseInt(a[0]);
        int dy = Integer.parseInt(a[1]);
        changeMapBean.setMapx(dx);
        changeMapBean.setMapy(dy);
        changeMapBean.setType(type);
        String sendMes = Agreement.getAgreement().ChangemapAgreement(GsonUtil.getGsonUtil().getgson().toJson(changeMapBean));
        SendMessageUntil.toServer(sendMes);
        TestsmallmapJframe.getTestsmallmapJframe().getTestsmallmapJpanel().clearTps();
        WorldTestsmallmapJframe.getWorldTestsmallmapJframe();
        WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
    }
    
    public static void tp(int map, int x, int y) {
        if (ImageMixDeal.userimg.getRoleShow().getBooth_id() != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("摆摊状态不可以飞行！");
            return;
        }
        if (FightingMixDeal.State != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你在战斗中");
            return;
        }
        ChangeMapBean changeMapBean = new ChangeMapBean();
        changeMapBean.setMapid(map + "");
        changeMapBean.setMapx(x);
        changeMapBean.setMapy(y);
        changeMapBean.setType(3);
        String sendMes = Agreement.getAgreement().ChangemapAgreement(GsonUtil.getGsonUtil().getgson().toJson(changeMapBean));
        SendMessageUntil.toServer(sendMes);
    }
}
