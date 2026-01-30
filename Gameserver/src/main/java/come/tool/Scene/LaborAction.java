package come.tool.Scene;

import come.tool.Good.DropUtil;
import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Scene.LaborDay.LaborScene;
import come.tool.Stall.AssetUpdate;
import come.tool.newTask.TaskRecord;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.bean.LoginResult;
import org.come.bean.UseCardBean;
import org.come.handler.SendMessage;
import org.come.model.ActiveAward;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.until.GsonUtil;

public class LaborAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login = GameServer.getAllLoginRole().get(ctx);
        if (login==null) {return;}

        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (roleData == null) {
            return;
        }

        if (message != null && message.contains("113")) {
            UseCardBean limit = roleData.getLimit("JVIP");
            if (limit != null) {
                TaskRecord taskRecord2 = roleData.getTaskRecord(2222);
                if (taskRecord2 != null && (taskRecord2.getNewID() >> 6 & 0x1) == 0x1) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您今日已领取过该奖励，请明日再来领取！"));
                    return;
                }
                if (taskRecord2 == null) {
                    taskRecord2 = new TaskRecord(2222);
                }
                taskRecord2.setNewID(taskRecord2.getNewID() | 1 << 6);
                roleData.addTaskRecord(taskRecord2);
                AssetUpdate assetUpdate = null;
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USERGOOD);
                }
                assetUpdate.setTask("C2222=N" + taskRecord2.getNewID());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                String msg = LaborScene.request(login, message);
                if (msg != null) {
                    SendMessage.sendMessageToSlef(ctx, msg);
                }
            } else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您未激活月卡，无法领取该礼包！请联系群主购买月卡！"));
                return;
            }
        } else if (message!=null&&message.contains("14")) {
            String msg = LaborScene.request(login, message);
            if (msg != null) {
                SendMessage.sendMessageToSlef(ctx, msg);
            }

         }else{
            UseCardBean limit = roleData.getLimit("VIP");
            if (limit != null) {
                TaskRecord taskRecord2 = roleData.getTaskRecord(2);
                if (taskRecord2 != null && (taskRecord2.getNewID() >> 6 & 0x1) == 0x1) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您今日已领取过该奖励，请明日再来领取！"));
                    return;
                }
                if (taskRecord2 == null) {
                    taskRecord2 = new TaskRecord(2);
                }
                taskRecord2.setNewID(taskRecord2.getNewID() | 1 << 6);
                roleData.addTaskRecord(taskRecord2);
                AssetUpdate assetUpdate = null;
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USERGOOD);
                }
                assetUpdate.setTask("C2=N" + taskRecord2.getNewID());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                String msg= LaborScene.request(login,message);
                if (msg!=null) {
                    SendMessage.sendMessageToSlef(ctx,msg);
                }
            }
            else {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("您未激活月卡，无法领取该礼包！请联系群主购买月卡！"));
                return;
            }
        }



    }
}
