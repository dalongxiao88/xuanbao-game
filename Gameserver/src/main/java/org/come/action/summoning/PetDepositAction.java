package org.come.action.summoning;

import come.tool.Role.RoleData;
import org.come.entity.RoleSummoning;
import org.come.until.GsonUtil;
import come.tool.Stall.AssetUpdate;
import come.tool.Role.Hang;
import java.util.stream.IntStream;
import come.tool.Role.RolePool;
import org.come.protocol.ParamTool;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class PetDepositAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if ((int)roleInfo.getFighting() != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你还在战斗中"));
            return;
        }
        if (StringUtils.isBlank(message)) {
            return;
        }
        if (message.startsWith("deposit")) {
            String[] split = message.split("=");
            RoleSummoning exRoleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(split[1]));
            if (exRoleSummoning == null) {
                return;
            }
            if (roleInfo.getSummoning_id() != null && roleInfo.getSummoning_id().compareTo(exRoleSummoning.getSid()) == 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("这只召唤兽已在参战中！无法寄存！"));
                return;
            }
            if (exRoleSummoning.getRoleid().compareTo(roleInfo.getRole_id()) != 0) {
                ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action(ctx, roleInfo.getUserName());
                ctx.close();
                return;
            }
            exRoleSummoning.setDeposit(Integer.valueOf(1));
            AllServiceUtil.getRoleSummoningService().updateRoleSummoning(exRoleSummoning);
            RoleData roleData = RolePool.getRoleData(roleInfo.getRole_id());
            if (roleData.getPets() != null && roleData.getPets().size() > 0) {
                IntStream.range(0, roleData.getPets().size()).filter(i/* int, */ -> ((Hang)roleData.getPets().get(i)).getId().equals(exRoleSummoning.getSid())).boxed().findFirst().map(i/* java.lang.Integer, */ -> (Hang)roleData.getPets().remove((int)i));
            }
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(99);
            assetUpdate.updata("#DelSid=" + exRoleSummoning.getSid());
            assetUpdate.setMsg("寄存成功！");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
        else if (!message.startsWith("depositPetList") && message.startsWith("retrieve")) {
            String[] split = message.split("=");
            RoleSummoning exRoleSummoning = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(new BigDecimal(split[1]));
            if (exRoleSummoning == null) {
                return;
            }
            if (exRoleSummoning.getRoleid().compareTo(roleInfo.getRole_id()) != 0) {
                ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action(ctx, roleInfo.getUserName());
                ctx.close();
                return;
            }
            if ((int)exRoleSummoning.getDeposit() == 0 || exRoleSummoning.getDeposit() == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("这只召唤兽已取回！"));
                return;
            }
            RoleData data = RolePool.getRoleData(roleInfo.getRole_id());
            data.getPets().add(new Hang(exRoleSummoning.getSid()));
            exRoleSummoning.setDeposit(Integer.valueOf(0));
            AllServiceUtil.getRoleSummoningService().updateRoleSummoning(exRoleSummoning);
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(99);
            assetUpdate.updata("#retrieve=" + exRoleSummoning.getSid());
            assetUpdate.setMsg("取回成功！");
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
}
