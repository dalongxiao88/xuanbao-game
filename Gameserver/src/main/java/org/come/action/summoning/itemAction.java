package org.come.action.summoning;

import come.tool.Good.DropUtil;
import java.util.List;
import org.come.model.ItemExchange;
import org.come.action.monitor.MonitorUtil;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.come.redis.RedisCacheUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.itemBean;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import org.come.action.IAction;

public class itemAction implements IAction
{
    public static Random random;
    public static String[] kxs;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        itemBean itemBean = (itemBean)GsonUtil.getGsonUtil().getgson().fromJson(message, itemBean.class);
        if (itemBean.getOpertype() == 0) {}
        if (itemBean.getOpertype() == 100) {
            this.exchange(itemBean, ctx);
        }
        if (itemBean.getOpertype() == 101) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("一键兑换暂时关闭."));
            return;
        }
    }
    
    public void exchange(itemBean itemBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        ItemExchange exchange = GameServer.getItemExchange(itemBean.getzhuangbeiid().intValue());
        Goodstable item = (exchange != null) ? GameServer.getgoods(exchange.getGoods()) : null;
        if (item == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
            return;
        }
        item.setRgid(RedisCacheUtil.getGoods_pk());
        if (item.getUsetime() == null) {
            item.setUsetime(Integer.valueOf(1));
        }
        if (item.getStatus() == null) {
            item.setStatus(Integer.valueOf(0));
        }
        List<Goodstable> list = new ArrayList<>();
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
                Goodstable goodstable = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable == null) {
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
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够" + sum + "个" + goodstable.getGoodsname()));
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
        item.setRole_id(loginResult.getRole_id());
        if (item.getType() == 520L) {
            assetUpdate.setGood(item);
            item.setRole_id(loginResult.getRole_id());
            AllServiceUtil.getRoleSummoningService().insertitem(item);
        }
        else {
            String message = item.getGoodsid() + "," + 1 + "," + item.getGoodsname();
            this.adGoods(message, loginResult);
        }
        assetUpdate.setMsg("成功兑换#R" + item.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public void exchangeyi(itemBean itemBean, ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (loginResult == null) {
            return;
        }
        ItemExchange exchange = GameServer.getItemExchange(itemBean.getzhuangbeiid().intValue());
        Goodstable item = (exchange != null) ? GameServer.getgoods(exchange.getGoods()) : null;
        if (item == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
            return;
        }
        item.setRgid(RedisCacheUtil.getGoods_pk());
        if (item.getUsetime() == null) {
            item.setUsetime(Integer.valueOf(1));
        }
        if (item.getStatus() == null) {
            item.setStatus(Integer.valueOf(0));
        }
        List<Goodstable> list = new ArrayList<>();
        long money = 0L;
        String goodName = null;
        int count = 0;
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
                Goodstable goodstable = (Goodstable)GameServer.getAllGoodsMap().get(goodid);
                if (goodstable == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("错误兑换公式"));
                    return;
                }
                List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(loginResult.getRole_id(), goodid);
                count = this.calCount(exchange, loginResult.getGold().longValue(), (int)((Goodstable)goods.get(0)).getUsetime());
                int SYsum = sum;
                int k = 0;
                while (k < goods.size()) {
                    Goodstable good = (Goodstable)goods.get(k);
                    if (goodName == null) {
                        goodName = good.getGoodsname();
                    }
                    if ((int)good.getUsetime() >= SYsum) {
                        good.setUsetime(Integer.valueOf((int)good.getUsetime() - SYsum * count));
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
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够" + sum + "个" + goodstable.getGoodsname()));
                    return;
                }
            }
        }
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.USEGOOD);
        if (money != 0L) {
            loginResult.setGold(loginResult.getGold().add(new BigDecimal(-(money * (long)count))));
            MonitorUtil.getMoney().useD(money * (long)count);
        }
        assetUpdate.updata("D=-" + money * (long)count);
        if (list.size() != 0) {
            for (int j = 0; j < list.size(); ++j) {
                Goodstable good2 = (Goodstable)list.get(j);
                AllServiceUtil.getGoodsTableService().updateGoodRedis(good2);
                assetUpdate.updata("G" + good2.getRgid() + "=" + good2.getUsetime());
            }
        }
        item.setRole_id(loginResult.getRole_id());
        if (item.getType() == 520L) {
            assetUpdate.setGood(item);
            item.setRole_id(loginResult.getRole_id());
            AllServiceUtil.getRoleSummoningService().insertitem(item);
        }
        else {
            String message = item.getGoodsid() + "," + count + "," + item.getGoodsname();
            this.adGoods(message, loginResult);
        }
        assetUpdate.setMsg("成功兑换#R" + count + "个" + item.getGoodsname());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
    }
    
    public int calCount(ItemExchange exchange, long money, int number) {
        int count = 0;
        int countmoney = 0;
        int countnum = 0;
        int minCountnum = Integer.MAX_VALUE;
        String[] v = exchange.getConsume().split("\\|");
        for (int i = 0; i < v.length; ++i) {
            if (v[i].startsWith("D")) {
                long gold = Long.parseLong(v[i].substring(2));
                if (money >= gold) {
                    countmoney = (int)(money / gold);
                    if (countmoney == 0) {
                        return 0;
                    }
                }
            }
            else if (v[i].startsWith("G")) {
                String[] vs = v[i].split("=");
                int goodnum = Integer.parseInt(vs[2]);
                if (number >= goodnum) {
                    countnum = number / goodnum;
                    if (countnum != 0) {
                        minCountnum = Math.min(minCountnum, countnum);
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
        count = Math.min(countmoney, minCountnum);
        return count;
    }
    
    private void adGoods(String message, LoginResult loginResult) {
        String[] sm = message.split(",");
        String mes = sm[0] + "|" + sm[1];
        String msg = ":25|" + mes + "|#AAFFFO";
        DropUtil.isDH(msg, loginResult);
    }
    
    static {
        itemAction.random = new Random();
        itemAction.kxs = new String[] { "抗混乱=30", "抗封印=30", "抗昏睡=30", "抗中毒=30", "物理吸收率=30", "抗风=30", "抗火=30", "抗水=30", "抗雷=30", "抗鬼火=30", "抗遗忘=30" };
    }
}
