package org.come.npc;

import org.come.entity.Goodstable;
import org.come.Frame.WorldTestsmallmapJframe;
import org.come.Frame.TestsmallmapJframe;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.model.Door;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.Frame.ZhuFrame;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.action.NpcMenuAction;

public class FlightChessTP implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (!TaskMixDeal.istp()) {
            ZhuFrame.getZhuJpanel().addPrompt2("传送限制");
        }
        else if (type.equals("快送我去")) {
            this.TP1();
        }
        else if (type.equals("新增坐标")) {
            this.TP2();
        }
        else if (type.equals("删除路标")) {
            this.TP3();
        }
        else if (type.equals("重新做路标")) {
            this.TP4();
        }
    }
    
    public void TP1() {
        String[] vs = NPCJfram.getNpcJfram().getNpcjpanel().getGood().getValue().split("\\|");
        String[] cishu = vs[vs.length - 1].split("=");
        int sum = Integer.parseInt(cishu[1]);
        --sum;
        String[] path = null;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < vs.length - 1; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(vs[i]);
            if (path == null) {
                String[] pathv = vs[i].split("=")[1].split(",");
                String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                if (wz.equals(NPCJfram.getNpcJfram().getNpcjpanel().getFvalue()[0])) {
                    path = pathv;
                }
            }
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(cishu[0]);
        buffer.append("=");
        buffer.append(sum);
        if (path != null) {
            NPCJfram.getNpcJfram().getNpcjpanel().getGood().setValue(buffer.toString());
            if (sum <= 0) {
                NPCJfram.getNpcJfram().getNpcjpanel().getGood().setUsetime(Integer.valueOf(0));
            }
            GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
            if (sum <= 0) {
                GoodsListFromServerUntil.Deletebiaoid(NPCJfram.getNpcJfram().getNpcjpanel().getGood().getRgid());
            }
            Door door = new Door();
            door.setDoormap(path[0]);
            door.setDoorpoint(path[2] + "|" + path[3]);
            try {
                TP.tp(door, 1);
            }
            catch (Exception var9) {
                var9.printStackTrace();
            }
        }
    }
    
    public void TP2() {
        String[] vs = NPCJfram.getNpcJfram().getNpcjpanel().getGood().getValue().split("\\|");
        String ky = vs[vs.length - 1];
        String path = "传送=" + ImageMixDeal.userimg.getRoleShow().getMapid() + "," + Util.mapmodel.getGamemap().getMapname() + "," + ImageMixDeal.userimg.getRoleShow().getX() + "," + ImageMixDeal.userimg.getRoleShow().getY();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < vs.length - 1; ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(vs[i]);
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(path);
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(ky);
        ZhuFrame.getZhuJpanel().addPrompt2("路标已添加");
        NPCJfram.getNpcJfram().getNpcjpanel().getGood().setValue(buffer.toString());
        GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
        TestsmallmapJframe.getTestsmallmapJframe().getTestsmallmapJpanel().clearTps();
        WorldTestsmallmapJframe.getWorldTestsmallmapJframe();
        WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
    }
    
    public void TP3() {
        String[] vs = NPCJfram.getNpcJfram().getNpcjpanel().getGood().getValue().split("\\|");
        String ky = vs[vs.length - 1];
        String path = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < vs.length - 1; ++i) {
            if (path != null) {
                String[] pathv = vs[i].split("=")[1].split(",");
                String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                if (wz.equals(NPCJfram.getNpcJfram().getNpcjpanel().getFvalue()[0])) {
                    vs[i] = null;
                    path = null;
                }
            }
            if (vs[i] != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(vs[i]);
            }
        }
        if (buffer.length() != 0) {
            buffer.append("|");
        }
        buffer.append(ky);
        ZhuFrame.getZhuJpanel().addPrompt2("路标已删除");
        NPCJfram.getNpcJfram().getNpcjpanel().getGood().setValue(buffer.toString());
        GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
        TestsmallmapJframe.getTestsmallmapJframe().getTestsmallmapJpanel().clearTps();
        WorldTestsmallmapJframe.getWorldTestsmallmapJframe();
        WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
    }
    
    public void TP4() {
        FlightRedraw(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 0);
    }
    
    public static void FlightRedraw(Goodstable good, int leixing) {
        if (!TaskMixDeal.istp()) {
            ZhuFrame.getZhuJpanel().addPrompt2("传送限制");
        }
        else {
            String[] vs = NPCJfram.getNpcJfram().getNpcjpanel().getGood().getValue().split("\\|");
            String ky = null;
            if (leixing != 0) {
                ky = "可用次数=" + leixing;
            }
            else {
                ky = vs[vs.length - 1];
            }
            String path = "传送=" + ImageMixDeal.userimg.getRoleShow().getMapid() + "," + Util.mapmodel.getGamemap().getMapname() + "," + ImageMixDeal.userimg.getRoleShow().getX() + "," + ImageMixDeal.userimg.getRoleShow().getY();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < vs.length - 1; ++i) {
                if (path != null) {
                    String[] pathv = vs[i].split("=")[1].split(",");
                    String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                    if (wz.equals(NPCJfram.getNpcJfram().getNpcjpanel().getFvalue()[0])) {
                        vs[i] = path;
                        path = null;
                    }
                }
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(vs[i]);
            }
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append(ky);
            ZhuFrame.getZhuJpanel().addPrompt2("已经重新做好路标");
            NPCJfram.getNpcJfram().getNpcjpanel().getGood().setValue(buffer.toString());
            GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
            GoodsMouslisten.gooduse(NPCJfram.getNpcJfram().getNpcjpanel().getGood(), 2);
            TestsmallmapJframe.getTestsmallmapJframe().getTestsmallmapJpanel().clearTps();
            WorldTestsmallmapJframe.getWorldTestsmallmapJframe();
            WorldTestsmallmapJframe.getWorldTestsmallmapJpanel().clearTps();
        }
    }
}
