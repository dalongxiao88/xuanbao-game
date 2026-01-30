package org.come.action.gl;

import java.util.List;
import org.come.bean.NChatBean;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.redis.RedisCacheUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Goodstable;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import org.come.model.GoodsExchange;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class GoodsExchangeAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {//修复兑换盗刷
//        GoodsExchange goodsExchange = (GoodsExchange)GsonUtil.getGsonUtil().getgson().fromJson(message, GoodsExchange.class);
//        this.goodschange(goodsExchange, ctx);
    }
    
    public void goodschange(GoodsExchange goodsExchange, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        GoodsExchange exchange = goodsExchange;
        Goodstable goodstable;
        Goodstable goodss = goodstable = ((exchange != null) ? ((Goodstable)GameServer.getAllGoodsMap().get(exchange.getGid())) : null);
        if (goodss == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
            return;
        }
        goodss.setRole_id(loginResult.getRole_id());
        goodss.setRgid(RedisCacheUtil.getGoods_pk());
        goodss.setStatus(Integer.valueOf(0));
        ArrayList<Goodstable> list = new ArrayList<>();
        long money = 0L;
        String goodName = null;
        String[] v = exchange.getConsume().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith("D")) {
                money = Long.parseLong(v[i].substring(2));
                if (loginResult.getGold().longValue() < money) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("金钱不够" + money));
                    return;
                }
            }
            else if (v[i].startsWith("G")) {
                String[] vs = v[i].split("=");
                BigDecimal goodid = new BigDecimal(vs[1]);
                int sum = Integer.parseInt(vs[2]);
                Goodstable goodstable2 = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable2 == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
                    return;
                }
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodid);
                int SYsum = sum;
                int k = 0;
                while (k < goods.size()) {
                    Goodstable good = (Goodstable)goods.get(k);
                    if (goodName == null) {
                        goodName = good.getGoodsname();
                    }
                    if ((int)good.getUsetime() >= SYsum) {
                        good.setUsetime(Integer.valueOf((int)good.getUsetime() - SYsum));
                        SYsum = 0;
                        list.add(good);
                        break;
                    }
                    else {
                        SYsum -= (int)good.getUsetime();
                        good.setUsetime(Integer.valueOf(0));
                        list.add(good);
                        ++k;
                    }
                }
                if (SYsum > 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够" + sum + "个" + goodstable2.getGoodsname()));
                    return;
                }
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (money != 0L) {
            loginResult.setGold(loginResult.getGold().add(new BigDecimal(-money)));
            MonitorUtil.getMoney().useD(money);
        }
        assetUpdate.updata("D=-" + money);
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); ++j) {
                Goodstable good2 = (Goodstable)list.get(j);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good2);
                assetUpdate.updata("G" + good2.getRgid() + "=" + good2.getUsetime());
            }
        }
        goodss.setGoodsid(loginResult.getRole_id());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodss);
        assetUpdate.setGood(goodss);
        assetUpdate.setMsg("成功兑换#R" + goodss.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y天空一声巨响,#G");
        buffer.append(loginResult.getRolename());
        buffer.append("#Y终于集齐#G");
        buffer.append(goodName);
        buffer.append("#Y,获得#G");
        buffer.append(goodss.getGoodsname());
        buffer.append("#Y！#89");
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
}
