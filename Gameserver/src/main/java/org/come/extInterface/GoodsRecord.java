package org.come.extInterface;

import org.come.entity.Goodsrecord;
import com.github.pagehelper.PageInfo;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.extInterBean.GoodsRecordReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.service.IGoodsrecordService;
import org.come.action.IAction;

public class GoodsRecord implements IAction
{
    private IGoodsrecordService goodsrecordService;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        GoodsRecordReqBean request = (GoodsRecordReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodsRecordReqBean.class);
        String condition = request.getCondition();
        Integer pageNum = request.getPageNum();
        PageInfo<Goodsrecord> list = this.goodsrecordService.selectGoodsRecord(pageNum, condition);
        String msg = Agreement.getAgreement().goodsRecordAgreement(GsonUtil.getGsonUtil().getgson().toJson(list));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
}
