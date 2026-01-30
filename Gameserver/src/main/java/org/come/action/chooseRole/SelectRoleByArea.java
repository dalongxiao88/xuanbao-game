package org.come.action.chooseRole;

import java.util.List;
import org.come.entity.Openareatable;
import org.come.entity.RegionResult;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.entity.AreaQuery;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SelectRoleByArea implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        AreaQuery area = (AreaQuery)GsonUtil.getGsonUtil().getgson().fromJson(message, AreaQuery.class);
        String userId = area.getUserId();
        List<RegionResult> result = new ArrayList<>();
        if (userId == null) {
            String msg = Agreement.getAgreement().returnAgreement(GsonUtil.getGsonUtil().getgson().toJson(result));
            SendMessage.sendMessageToSlef(ctx, msg);
        }
        String[] userIdArr = userId.split("\\|");
        if (userIdArr.length > 1) {
            String msg2 = Agreement.getAgreement().returnAgreement(GsonUtil.getGsonUtil().getgson().toJson(result));
            SendMessage.sendMessageToSlef(ctx, msg2);
            return;
        }
        List<Openareatable> allArea = AllServiceUtil.getOpenareatableService().selectAllArea(new BigDecimal(userId));
        if (allArea == null) {
            String msg3 = Agreement.getAgreement().returnAgreement(GsonUtil.getGsonUtil().getgson().toJson(result));
            SendMessage.sendMessageToSlef(ctx, msg3);
            return;
        }
        for (int i = 0; i < allArea.size(); ++i) {
            RegionResult res = new RegionResult();
            res.setRE_NAME("天界");
            res.setRA_ID(((Openareatable)allArea.get(i)).getOt_areaid());
            res.setRA_NAME(((Openareatable)allArea.get(i)).getOt_areaname());
            res.setOT_BELONG(((Openareatable)allArea.get(i)).getOt_belong() + "");
            result.add(res);
        }
        String json = GsonUtil.getGsonUtil().getgson().toJson(result);
        String msg4 = Agreement.getAgreement().returnAgreement(json);
        SendMessage.sendMessageToSlef(ctx, msg4);
    }
}
