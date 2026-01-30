package org.come.action.mount;

import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;
import org.come.bean.CarResult;
import org.come.bean.LoginResult;
import org.come.bean.MountResult;
import org.come.entity.Car;
import org.come.entity.Mount;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;

import java.math.BigDecimal;
import java.util.List;

public class CarAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        BigDecimal roleID = ((LoginResult)GameServer.getAllLoginRole().get(ctx)).getRole_id();
        List<Car> mounts = AllServiceUtil.getCarService().selectMountsByRoleID(roleID);
        CarResult mountResult = new CarResult();
        mountResult.setCars(mounts);
        String msg = Agreement.getAgreement().CarAgreement(GsonUtil.getGsonUtil().getgson().toJson(mountResult));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
