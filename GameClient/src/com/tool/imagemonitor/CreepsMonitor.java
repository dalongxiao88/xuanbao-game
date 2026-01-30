package com.tool.imagemonitor;

import java.util.Iterator;
import org.come.bean.MapMonsterBean;
import org.apache.commons.lang.math.NumberUtils;
import com.tool.ModerateTask.Task;
import com.tool.ModerateTask.Hero;
import org.come.Frame.NPCJfram;
import org.come.until.MessagrFlagUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import com.tool.image.ManimgAttribute;

public class CreepsMonitor
{
    public static void Creeps(ManimgAttribute attribute) {
        MapMonsterBean monsterBean = attribute.getMapMonsterBean();
        if (monsterBean.getRobottype() == 3) {
            PlayerMonitor.give(attribute);
        }
        else if (monsterBean.getRobottype() == 1) {
            int packNumber = GoodsListFromServerUntil.Surplussum("-1", "-1", 999);
            if (packNumber <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt("背包已满！！！");
                return;
            }
            String senmes = Agreement.getAgreement().clickMonstersAgreement(monsterBean.getI() + "");
            SendMessageUntil.toServer(senmes);
        }
        else if (monsterBean.getRobottype() >= 100 && monsterBean.getRobottype() <= 199) {
            if (monsterBean.getRobottype() == 120 || monsterBean.getRobottype() == 121) {
                if (MessagrFlagUntil.MOUSE6.equals(MessagrFlagUntil.ImgFlagImg)) {
                    SendMessageUntil.toServer(Agreement.getAgreement().gangbattle("M" + monsterBean.getI() + "|攻击"));
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                return;
            }
            else if (monsterBean.getRobottype() == 132) {
                NPCJfram.getNpcJfram().getNpcjpanel().yeguai(monsterBean);
            }
            else {
                String sendmes = Agreement.getAgreement().gangmonitor("M" + monsterBean.getI());
                SendMessageUntil.toServer(sendmes);
            }
        }
        else if (attribute.getTaskIds() != null && attribute.getTaskIds().length > 0) {
            for (String taskId : attribute.getTaskIds()) {
                if (!taskId.equals("0")) {
                    for (Task task : Hero.getHero().getDoingTasks()) {
                        if (NumberUtils.isDigits(taskId) && task.getTaskId() == Integer.parseInt(taskId)) {
                            NPCJfram.getNpcJfram().getNpcjpanel().yeguai(monsterBean);
                            return;
                        }
                    }
                    NPCJfram.getNpcJfram().getNpcjpanel().yeguai2(monsterBean);
                }
                else {
                    NPCJfram.getNpcJfram().getNpcjpanel().yeguai(monsterBean);
                }
            }
        }
        else {
            NPCJfram.getNpcJfram().getNpcjpanel().yeguai(monsterBean);
        }
    }
}
