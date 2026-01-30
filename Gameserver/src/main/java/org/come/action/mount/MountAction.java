package org.come.action.mount;

import org.come.entity.Mount;
import java.util.List;
import java.math.BigDecimal;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.MountResult;
import org.come.until.AllServiceUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal roleID = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getRole_id();
        List<Mount> mounts = AllServiceUtil.getMountService().selectMountsByRoleID(roleID);
        MountResult mountResult = new MountResult();
        mountResult.setMounts(mounts);
        String msg = Agreement.getAgreement().MountAgreement(GsonUtil.getGsonUtil().getgson().toJson(mountResult));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
