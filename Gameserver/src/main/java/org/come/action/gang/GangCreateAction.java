package org.come.action.gang;

import java.util.List;
import org.come.bean.GangChangeBean;
import org.come.entity.RoleTable;
import come.tool.newGang.GangUtil;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.Gang;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GangCreateAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo.getGang_id().intValue() != 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经有帮派了"));
            return;
        }
        Gang gang = (Gang)GsonUtil.getGsonUtil().getgson().fromJson(message, Gang.class);
        if (gang.getGangname().length() > 10) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮派名称过长"));
            return;
        }
        if (gang.getIntroduction().length() > 140) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮派宗旨过长"));
            return;
        }
        Gang roleGang = AllServiceUtil.getGangService().findGangByGangName(gang.getGangname());
        if (roleGang != null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("帮派名称已存在"));
            return;
        }
        List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(roleInfo.getRole_id(), new BigDecimal(501));
        Goodstable good = null;
        int i = 0;
        while (i < goods.size()) {
            if ((int)((Goodstable)goods.get(i)).getUsetime() > 0) {
                good = (Goodstable)goods.get(i);
                break;
            }
            else {
                ++i;
            }
        }
        if (good == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你背包没有三界召集令"));
            return;
        }
        AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
        good.goodxh(1);
        AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
        assetUpdate.updata("G" + good.getRgid() + "=" + good.getUsetime());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        gang.setGangnumber(new BigDecimal(1));
        gang.setBuilder(new BigDecimal(0));
        gang.setGanggrade(new BigDecimal(1));
        gang.setFounder(roleInfo.getRolename());
        gang.setGangbelong(roleInfo.getRolename());
        boolean isSuccess = AllServiceUtil.getGangService().createGang(gang);
        if (isSuccess) {
            GangUtil.addGangDomain(gang, roleInfo.getRole_id(), ctx);
            RoleTable roleTable = new RoleTable(0, roleInfo);
            roleTable.setGang_id(gang.getGangid());
            roleTable.setGangpost("帮主");
            roleTable.setGangname(gang.getGangname());
            AllServiceUtil.getRoleTableService().updateGang(roleTable);
            roleInfo.setGang_id(gang.getGangid());
            roleInfo.setGangpost("帮主");
            roleInfo.setGangname(gang.getGangname());
            String sendMes = Agreement.getAgreement().upRoleShowAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleInfo.getRoleShow()));
            SendMessage.sendMessageToMapRoles(ctx, roleInfo.getMapid(), sendMes);
            GangChangeBean gangChangeBean = new GangChangeBean(roleInfo, "创建帮派成功");
            SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.GangChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(gangChangeBean)));
        }
    }
}
