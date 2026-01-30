package org.come.action.sellxianyu;

import org.come.bean.SearchSellXianYuResultBean;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.BasePageHelper;
import org.come.bean.NChatBean;
import java.util.Iterator;
import java.util.List;
import come.tool.Role.RoleData;
import java.text.DateFormat;
import come.tool.Role.RolePool;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.entity.SellXianYuOrder;
import come.tool.Stall.AssetUpdate;
import org.come.until.AllServiceUtil;
import org.come.action.monitor.MonitorUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import org.come.until.GsonUtil;
import org.come.bean.SellXianyu;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SellXianYuAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (message.startsWith("ADD")) {
            String[] result = message.split("\\|");
            if (result[1] != null) {
                SellXianyu sellXianyu = (SellXianyu)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SellXianyu.class);
                if (sellXianyu == null || sellXianyu.getXianYuPoint().compareTo(BigDecimal.ZERO) <= 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请输入正确的点数和价格。"));
                    return;
                }
                if (sellXianyu.getXianYuPoint().compareTo(loginResult.getCodecard()) >= 0) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你仙玉不足" + sellXianyu.getXianYuPoint()));
                    return;
                }
                MonitorUtil.getMoney().useX(sellXianyu.getXianYuPoint().longValue());
                loginResult.setCodecard(new BigDecimal(loginResult.getCodecard().longValue() - sellXianyu.getXianYuPoint().longValue()));
                sellXianyu.setRoleId(loginResult.getRole_id());
                AllServiceUtil.getSellXianYuOrderService().addReidsSellxx(sellXianyu);
                AssetUpdate assetUpdate = new AssetUpdate();
                assetUpdate.setData("X=" + -sellXianyu.getXianYuPoint().longValue());
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                this.sendChuanYin(loginResult, "#R" + loginResult.getRolename() + "#W已经以每点#R" + sellXianyu.getPricePoint() + "#W的价格寄售了#R" + sellXianyu.getXianYuPoint().toString() + "#W点仙玉。");
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你寄售了" + sellXianyu.getXianYuPoint().toString() + "仙玉"));
                this.refreshAllList(ctx);
            }
        }
        else if (message.startsWith("MYLIST")) {
            String[] result = message.split("\\|");
            if (result[1] != null) {
                this.refreshMyList(ctx);
            }
        }
        else if (message.startsWith("ALLLIST")) {
            String[] result = message.split("\\|");
            if (result[1] != null) {
                this.refreshMyBuyList(ctx);
            }
        }
        else if (message.startsWith("BUY")) {
            String[] result = message.split("\\|");
            if (result[1] != null) {
                SellXianYuOrder sellXianyuorder = (SellXianYuOrder)GsonUtil.getGsonUtil().getgson().fromJson(result[1], SellXianYuOrder.class);
                if (sellXianyuorder != null) {
                    SellXianyu sellXianyuRedis = AllServiceUtil.getSellXianYuOrderService().selectSellByIdNotDeposit(sellXianyuorder.getSellId());
                    if (sellXianyuorder.getXianYuPoint().compareTo(sellXianyuRedis.getXianYuPoint()) > 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("购买数量超过总量，无法购买！"));
                        return;
                    }
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        Date expireTime = dateFormat.parse(sellXianyuRedis.getExpireTime());
                        if (expireTime.before(new Date())) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已经过期无法购买！"));
                            return;
                        }
                    }
                    catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    if (sellXianyuorder.getXianYuPoint().compareTo(BigDecimal.ZERO) <= 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("购买数量不以小于0！"));
                        return;
                    }
                    if (sellXianyuRedis != null && sellXianyuRedis.getRoleId().compareTo(loginResult.getRole_id()) == 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不能购自己发布的商品！"));
                        return;
                    }
                    BigDecimal jg = sellXianyuorder.getTotalPrice();
                    if (loginResult.getGold().compareTo(jg) < 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没有足够的大话币！"));
                        return;
                    }
                    sellXianyuRedis.setXianYuPoint(sellXianyuRedis.getXianYuPoint().subtract(sellXianyuorder.getXianYuPoint()));
                    sellXianyuRedis.setTotalPrice(sellXianyuRedis.getXianYuPoint().multiply(sellXianyuRedis.getPricePoint()));
                    sellXianyuorder.setBuyRoleId(loginResult.getRole_id());
                    sellXianyuorder.setSellRoleId(sellXianyuRedis.getRoleId());
                    loginResult.setGold(loginResult.getGold().subtract(jg));
                    MonitorUtil.getMoney().useD(jg.longValue());
                    loginResult.setCodecard(loginResult.getCodecard().add(sellXianyuorder.getXianYuPoint()));
                    MonitorUtil.getMoney().addX(sellXianyuorder.getXianYuPoint().longValue(), 0);
                    AllServiceUtil.getSellXianYuOrderService().updateRedisSellxx(sellXianyuRedis);
                    RoleData roleData = RolePool.getRoleData(sellXianyuRedis.getRoleId());
                    if (roleData != null) {
                        LoginResult sellLoingResult = roleData.getLoginResult();
                        sellLoingResult.setGold(sellLoingResult.getGold().add(jg));
                        MonitorUtil.getMoney().addD(sellXianyuRedis.getTotalPrice().longValue(), 3);
                        sellXianyuorder.setOrderStatus(2);
                        AssetUpdate assetUpdate2 = new AssetUpdate();
                        String msg = "";
                        if (sellXianyuRedis.getXianYuPoint().compareTo(BigDecimal.ZERO) <= 0) {
                            assetUpdate2.setData("D=" + jg.longValue());
                            msg = "有人购买了你的仙玉，你获得" + jg.longValue() + "大话币。";
                        }
                        else {
                            assetUpdate2.setData("D=" + jg.longValue());
                            msg = "有人购买了你的仙玉，你获得" + jg.longValue() + "大话币。";
                        }
                        MonitorUtil.getStallM().add(jg.longValue());
                        SendMessage.sendMessageByRoleName(sellLoingResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                        SendMessage.sendMessageByRoleName(sellLoingResult.getRolename(), Agreement.getAgreement().PromptAgreement(msg));
                    }
                    else {
                        sellXianyuorder.setOrderStatus(1);
                    }
                    AllServiceUtil.getSellXianYuOrderService().insertOrder(sellXianyuorder);
                    AssetUpdate assetUpdate3 = new AssetUpdate();
                    assetUpdate3.setData("X=" + sellXianyuorder.getXianYuPoint());
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate3)));
                    AssetUpdate assetUpdate2 = new AssetUpdate();
                    assetUpdate2.setData("D=" + -jg.longValue());
                    String msg2 = "你购买了" + sellXianyuorder.getXianYuPoint() + "仙玉,花费了" + jg.longValue() + "大话币。";
                    this.sendChuanYin(loginResult, "#Y" + loginResult.getRolename() + "#W成功购得其他玩家出售的#Y" + sellXianyuorder.getXianYuPoint() + "#Y点#W的点卡售价#Y" + jg.longValue() + "#W两银子。");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(msg2));
                    this.refreshAllBuyList();
                }
                else {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("请输入正确的购买数量！"));
                    return;
                }
            }
        }
        else if (message.startsWith("DOWN")) {
            String[] result = message.split("\\|");
            if (result[1] != null) {
                List<AssetUpdate> assets = AllServiceUtil.getSellXianYuOrderService().downSellXianyu(result[1], loginResult, ctx);
                if (assets != null && assets.size() > 0) {
                    for (AssetUpdate assetupdate : assets) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetupdate)));
                    }
                }
            }
            this.refreshMyList(ctx);
            this.refreshAllBuyList();
        }
    }
    
    public void sendChuanYin(LoginResult loginResult, String message) {
        NChatBean nChatBean = new NChatBean();
        nChatBean.setId(5);
        nChatBean.setMessage(message);
        nChatBean.setRoleId(loginResult.getRole_id());
        nChatBean.setRole(loginResult.getRolename());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(nChatBean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public void refreshAllList(ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BasePageHelper.startPage(1, 90000);
        List<SellXianyu> list = AllServiceUtil.getSellXianYuOrderService().selectSellListByRoleId(loginResult.getRole_id());
        PageInfo<SellXianyu> pageInfo = new PageInfo(list);
        SearchSellXianYuResultBean resultBean = new SearchSellXianYuResultBean();
        resultBean.setSellxianyus(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().searchSellXianYuAgreement("MYLIST|" + GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
        List<SellXianyu> list2 = AllServiceUtil.getSellXianYuOrderService().selectSellListNotDeposit();
        PageInfo<SellXianyu> pageInfo2 = new PageInfo(list2);
        SearchSellXianYuResultBean resultBean2 = new SearchSellXianYuResultBean();
        resultBean2.setSellxianyus(pageInfo2.getList());
        resultBean2.setTotal(pageInfo2.getPages());
        String msg2 = Agreement.getAgreement().searchSellXianYuAgreement("ALLLIST|" + GsonUtil.getGsonUtil().getgson().toJson(resultBean2));
        SendMessage.sendMessageToAllRoles(msg2);
    }
    
    public void refreshMyList(ChannelHandlerContext ctx) {
        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        BasePageHelper.startPage(1, 90000);
        List<SellXianyu> list = AllServiceUtil.getSellXianYuOrderService().selectSellListByRoleId(loginResult.getRole_id());
        PageInfo<SellXianyu> pageInfo = new PageInfo(list);
        SearchSellXianYuResultBean resultBean = new SearchSellXianYuResultBean();
        resultBean.setSellxianyus(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().searchSellXianYuAgreement("MYLIST|" + GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public void refreshMyBuyList(ChannelHandlerContext ctx) {
        BasePageHelper.startPage(1, 90000);
        List<SellXianyu> list = AllServiceUtil.getSellXianYuOrderService().selectSellListNotDeposit();
        PageInfo<SellXianyu> pageInfo = new PageInfo(list);
        SearchSellXianYuResultBean resultBean = new SearchSellXianYuResultBean();
        resultBean.setSellxianyus(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().searchSellXianYuAgreement("ALLLIST|" + GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToSlef(ctx, msg);
    }
    
    public void refreshAllBuyList() {
        BasePageHelper.startPage(1, 90000);
        List<SellXianyu> list = AllServiceUtil.getSellXianYuOrderService().selectSellListNotDeposit();
        PageInfo<SellXianyu> pageInfo = new PageInfo(list);
        SearchSellXianYuResultBean resultBean = new SearchSellXianYuResultBean();
        resultBean.setSellxianyus(pageInfo.getList());
        resultBean.setTotal(pageInfo.getPages());
        String msg = Agreement.getAgreement().searchSellXianYuAgreement("ALLLIST|" + GsonUtil.getGsonUtil().getgson().toJson(resultBean));
        SendMessage.sendMessageToAllRoles(msg);
    }
}
