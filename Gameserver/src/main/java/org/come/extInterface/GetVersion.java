package org.come.extInterface;

import org.come.entity.AppVersion;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.extInterBean.GetVersionReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GetVersion implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        GetVersionReqBean request = (GetVersionReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GetVersionReqBean.class);
        String version = request.getVersion();
        String sign = request.getSign();
        if (!"1".equals(sign) && "2".equals(sign)) {
            List<AppVersion> appVersion = AllServiceUtil.getAppVersionService().selectVersionUrl(version, sign);
            String appVerJson = GsonUtil.getGsonUtil().getgson().toJson(appVersion);
            String msg = Agreement.getAgreement().getVersionAgreement(appVerJson);
            SendMessage.sendMessageToSlef(ctx, msg);
        }
    }
}
