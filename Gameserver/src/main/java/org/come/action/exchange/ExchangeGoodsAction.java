package org.come.action.exchange;

import java.text.DateFormat;
import org.come.entity.Goodsexchange;
import java.math.BigDecimal;
import org.come.entity.Goodstable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import com.gl.service.PlayerService;
import org.come.action.IAction;

public class ExchangeGoodsAction implements IAction
{
    PlayerService playerService;
    static String CHECKTS1;
    static String CHECKTS2;
    static String CHECKTS3;
    static String CHECKTS4;
    
    @Override
    public synchronized void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        Compensation compensation = ExchangeUtil.getCompensation(message);
        if (compensation != null) {
            int v = compensation.receive(roleInfo.getRole_id());
            if (v == 0) {
                SendMessage.sendMessageToSlef(ctx, ExchangeGoodsAction.CHECKTS1);
            }
            else if (v == 2) {
                SendMessage.sendMessageToSlef(ctx, ExchangeGoodsAction.CHECKTS2);
            }
            else if (v == 1) {
                Goodstable goodstable = GameServer.getGood(compensation.getGoodId());
                goodstable.setRole_id(roleInfo.getRole_id());
                goodstable.setUsetime(Integer.valueOf(1));
                AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setGood(goodstable);
                assetUpdate.setMsg("1个" + goodstable.getGoodsname());
                assetUpdate.setType(AssetUpdate.INTEGRATION);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            }
            return;
        }
        else {
            Goodsexchange exchange = AllServiceUtil.getGoodsExchangeService().selectByPrimaryKey(message);
            if (exchange != null) {
                if ((int)exchange.getFlag() == 0) {
                    exchange.setFlag(Integer.valueOf(1));
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String nowdayTime = dateFormat.format(new Date());
                    try {
                        Date nowDate = dateFormat.parse(nowdayTime);
                        exchange.setOuttime(nowDate);
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                    AllServiceUtil.getGoodsExchangeService().updateByPrimaryKeySelective(exchange);
                    Goodstable goodstable2 = (Goodstable)GameServer.getAllGoodsMap().get(new BigDecimal(exchange.getGoodsid()));
                    goodstable2 = goodstable2.clone();
                    goodstable2.setRole_id(roleInfo.getRole_id());
                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable2);
                    AssetUpdate assetUpdate2 = new AssetUpdate();
                    assetUpdate2.setGood(goodstable2);
                    assetUpdate2.setMsg("1个" + goodstable2.getGoodsname());
                    assetUpdate2.setType(AssetUpdate.INTEGRATION);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                }
                else {
                    String msg = Agreement.getAgreement().tipAgreement("该兑换码已失效！");
                    SendMessage.sendMessageToSlef(ctx, msg);
                }
            }
            else {
                String msg = Agreement.getAgreement().tipAgreement("无效的兑换码！");
                SendMessage.sendMessageToSlef(ctx, msg);
            }
            return;
        }
    }
    
    static {
        ExchangeGoodsAction.CHECKTS1 = Agreement.getAgreement().PromptAgreement("你不属于补偿领取范围内");
        ExchangeGoodsAction.CHECKTS2 = Agreement.getAgreement().PromptAgreement("你已经领取过补偿");
        ExchangeGoodsAction.CHECKTS3 = Agreement.getAgreement().PromptAgreement("你获得补偿礼包");
        ExchangeGoodsAction.CHECKTS4 = Agreement.getAgreement().PromptAgreement("系统检测到您正在非法操作，3秒后封号！！！");
    }
}
