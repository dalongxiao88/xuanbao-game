package come.tool.JDialog;

import java.util.List;
import org.come.bean.RoleShow;
import org.come.thread.TimeControlRunnable;
import come.tool.map.XLPath;
import com.tool.image.ImageMixDeal;
import org.come.until.FormsManagement;
import org.come.npc.TP;
import org.come.model.Door;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.GoodsMouslisten;
import org.come.Frame.NPCJfram;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.WorldMapJpanel;

public class SFCSJDialog implements TiShiChuLi
{
    @Override
    public void tipBox(boolean tishi, Object object) {
        if (tishi) {
            WorldMapJpanel.setNumber();
            ZhuFrame.getZhuJpanel().addPrompt2("#W消耗了一次#R任我行#W次数");
            if (WorldMapJpanel.npcfeiji1 == null || WorldMapJpanel.npcfeiji1.length() == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先搜索NPC!!!");
            }
            else {
                String kkf = "传送=" + WorldMapJpanel.npcfeiji1 + ", 可用次数=10";
                String kkf2 = "传送=" + WorldMapJpanel.npcfeiji1;
                String kkf3 = WorldMapJpanel.npcfeiji1;
                String[] vs = kkf.split(",");
                String[] cishu = vs[vs.length - 1].split("=");
                int sum = Integer.parseInt(cishu[1]);
                --sum;
                String[] path = null;
                StringBuffer buffer = new StringBuffer();
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(kkf2);
                if (path == null) {
                    String[] pathv = kkf3.split(",");
                    String wz = pathv[1] + "(" + pathv[2] + "," + pathv[3] + ")";
                    path = pathv;
                }
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(cishu[0]);
                buffer.append("=");
                buffer.append(sum);
                if (path != null) {
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
            WorldMapJpanel.npcfeiji1 = "";
            FormsManagement.HideForm(1102);
            FormsManagement.HideForm(1103);
            return;
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("#G开始进行自动寻路");
            String[] zd = WorldMapJpanel.npcfeiji1.split(",");
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            List<Object> list = XLPath.ZDXL(roleShow.getX(), roleShow.getY(), roleShow.getMapid().intValue(), Integer.parseInt(zd[2]), Integer.parseInt(zd[3]), Integer.parseInt(zd[0]));
            if (list == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你所在位置无法达到目的地");
                return;
            }
            TimeControlRunnable.addScript(list);
            FormsManagement.HideForm(1102);
            FormsManagement.HideForm(1103);
            return;
        }
    }
}
